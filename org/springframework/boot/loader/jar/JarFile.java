/*     */ package org.springframework.boot.loader.jar;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Iterator;
/*     */ import java.util.jar.JarInputStream;
/*     */ import java.util.jar.Manifest;
/*     */ import java.util.zip.ZipEntry;
/*     */ import org.springframework.boot.loader.data.RandomAccessData;
/*     */ import org.springframework.boot.loader.data.RandomAccessData.ResourceAccess;
/*     */ import org.springframework.boot.loader.data.RandomAccessDataFile;
/*     */ 
/*     */ public class JarFile extends java.util.jar.JarFile
/*     */ {
/*     */   private static final String MANIFEST_NAME = "META-INF/MANIFEST.MF";
/*     */   private static final String PROTOCOL_HANDLER = "java.protocol.handler.pkgs";
/*     */   private static final String HANDLERS_PACKAGE = "org.springframework.boot.loader";
/*  57 */   private static final AsciiBytes META_INF = new AsciiBytes("META-INF/");
/*     */ 
/*  59 */   private static final AsciiBytes SIGNATURE_FILE_EXTENSION = new AsciiBytes(".SF");
/*     */   private final RandomAccessDataFile rootFile;
/*     */   private final String pathFromRoot;
/*     */   private final RandomAccessData data;
/*     */   private final JarFileType type;
/*     */   private URL url;
/*     */   private JarFileEntries entries;
/*     */   private SoftReference<Manifest> manifest;
/*     */   private boolean signed;
/*     */ 
/*     */   public JarFile(File file)
/*     */     throws IOException
/*     */   {
/*  83 */     this(new RandomAccessDataFile(file));
/*     */   }
/*     */ 
/*     */   JarFile(RandomAccessDataFile file)
/*     */     throws IOException
/*     */   {
/*  92 */     this(file, "", file, JarFileType.DIRECT);
/*     */   }
/*     */ 
/*     */   private JarFile(RandomAccessDataFile rootFile, String pathFromRoot, RandomAccessData data, JarFileType type)
/*     */     throws IOException
/*     */   {
/* 106 */     this(rootFile, pathFromRoot, data, null, type);
/*     */   }
/*     */ 
/*     */   private JarFile(RandomAccessDataFile rootFile, String pathFromRoot, RandomAccessData data, JarEntryFilter filter, JarFileType type)
/*     */     throws IOException
/*     */   {
/* 112 */     super(rootFile.getFile());
/* 113 */     this.rootFile = rootFile;
/* 114 */     this.pathFromRoot = pathFromRoot;
/* 115 */     CentralDirectoryParser parser = new CentralDirectoryParser();
/* 116 */     this.entries = ((JarFileEntries)parser.addVisitor(new JarFileEntries(this, filter)));
/* 117 */     parser.addVisitor(centralDirectoryVisitor());
/* 118 */     this.data = parser.parse(data, filter == null);
/* 119 */     this.type = type;
/*     */   }
/*     */ 
/*     */   private CentralDirectoryVisitor centralDirectoryVisitor() {
/* 123 */     return new CentralDirectoryVisitor()
/*     */     {
/*     */       public void visitStart(CentralDirectoryEndRecord endRecord, RandomAccessData centralDirectoryData)
/*     */       {
/*     */       }
/*     */ 
/*     */       public void visitFileHeader(CentralDirectoryFileHeader fileHeader, int dataOffset)
/*     */       {
/* 133 */         AsciiBytes name = fileHeader.getName();
/* 134 */         if ((name.startsWith(JarFile.META_INF)) && 
/* 135 */           (name
/* 135 */           .endsWith(JarFile.SIGNATURE_FILE_EXTENSION)))
/* 136 */           JarFile.access$202(JarFile.this, true);
/*     */       }
/*     */ 
/*     */       public void visitEnd()
/*     */       {
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   protected final RandomAccessDataFile getRootJarFile()
/*     */   {
/* 148 */     return this.rootFile;
/*     */   }
/*     */ 
/*     */   RandomAccessData getData() {
/* 152 */     return this.data;
/*     */   }
/*     */ 
/*     */   public Manifest getManifest() throws IOException
/*     */   {
/* 157 */     Manifest manifest = this.manifest == null ? null : (Manifest)this.manifest.get();
/* 158 */     if (manifest == null)
/*     */     {
/*     */       InputStream inputStream;
/* 159 */       if (this.type == JarFileType.NESTED_DIRECTORY) {
/* 160 */         manifest = new JarFile(getRootJarFile()).getManifest();
/*     */       }
/*     */       else {
/* 163 */         inputStream = getInputStream("META-INF/MANIFEST.MF", RandomAccessData.ResourceAccess.ONCE);
/*     */ 
/* 165 */         if (inputStream == null)
/* 166 */           return null;
/*     */       }
/*     */       try {
/* 169 */         manifest = new Manifest(inputStream);
/*     */ 
/* 172 */         inputStream.close(); } finally { inputStream.close();
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 177 */     return manifest;
/*     */   }
/*     */ 
/*     */   public Enumeration<java.util.jar.JarEntry> entries()
/*     */   {
/* 182 */     Iterator iterator = this.entries.iterator();
/* 183 */     return new Enumeration(iterator)
/*     */     {
/*     */       public boolean hasMoreElements()
/*     */       {
/* 187 */         return this.val$iterator.hasNext();
/*     */       }
/*     */ 
/*     */       public java.util.jar.JarEntry nextElement()
/*     */       {
/* 192 */         return (java.util.jar.JarEntry)this.val$iterator.next();
/*     */       }
/*     */     };
/*     */   }
/*     */ 
/*     */   public JarEntry getJarEntry(String name)
/*     */   {
/* 200 */     return (JarEntry)getEntry(name);
/*     */   }
/*     */ 
/*     */   public boolean containsEntry(String name) {
/* 204 */     return this.entries.containsEntry(name);
/*     */   }
/*     */ 
/*     */   public ZipEntry getEntry(String name)
/*     */   {
/* 209 */     return this.entries.getEntry(name);
/*     */   }
/*     */ 
/*     */   public synchronized InputStream getInputStream(ZipEntry ze) throws IOException
/*     */   {
/* 214 */     return getInputStream(ze, RandomAccessData.ResourceAccess.PER_READ);
/*     */   }
/*     */ 
/*     */   public InputStream getInputStream(ZipEntry ze, RandomAccessData.ResourceAccess access) throws IOException
/*     */   {
/* 219 */     if ((ze instanceof JarEntry)) {
/* 220 */       return this.entries.getInputStream((JarEntry)ze, access);
/*     */     }
/* 222 */     return getInputStream(ze == null ? null : ze.getName(), access);
/*     */   }
/*     */ 
/*     */   InputStream getInputStream(String name, RandomAccessData.ResourceAccess access) throws IOException {
/* 226 */     return this.entries.getInputStream(name, access);
/*     */   }
/*     */ 
/*     */   public synchronized JarFile getNestedJarFile(ZipEntry entry)
/*     */     throws IOException
/*     */   {
/* 237 */     return getNestedJarFile((JarEntry)entry);
/*     */   }
/*     */ 
/*     */   public synchronized JarFile getNestedJarFile(JarEntry entry)
/*     */     throws IOException
/*     */   {
/*     */     try
/*     */     {
/* 248 */       return createJarFileFromEntry(entry);
/*     */     }
/*     */     catch (IOException ex) {
/*     */     }
/* 252 */     throw new IOException("Unable to open nested jar file '" + entry
/* 252 */       .getName() + "'", ex);
/*     */   }
/*     */ 
/*     */   private JarFile createJarFileFromEntry(JarEntry entry) throws IOException
/*     */   {
/* 257 */     if (entry.isDirectory()) {
/* 258 */       return createJarFileFromDirectoryEntry(entry);
/*     */     }
/* 260 */     return createJarFileFromFileEntry(entry);
/*     */   }
/*     */ 
/*     */   private JarFile createJarFileFromDirectoryEntry(JarEntry entry) throws IOException {
/* 264 */     AsciiBytes sourceName = new AsciiBytes(entry.getName());
/* 265 */     JarEntryFilter filter = new JarEntryFilter(sourceName)
/*     */     {
/*     */       public AsciiBytes apply(AsciiBytes name)
/*     */       {
/* 269 */         if ((name.startsWith(this.val$sourceName)) && (!name.equals(this.val$sourceName))) {
/* 270 */           return name.substring(this.val$sourceName.length());
/*     */         }
/* 272 */         return null;
/*     */       }
/*     */     };
/* 276 */     return 
/* 278 */       new JarFile(this.rootFile, this.pathFromRoot + "!/" + entry
/* 278 */       .getName().substring(0, sourceName.length() - 1), this.data, filter, JarFileType.NESTED_DIRECTORY);
/*     */   }
/*     */ 
/*     */   private JarFile createJarFileFromFileEntry(JarEntry entry) throws IOException
/*     */   {
/* 283 */     if (entry.getMethod() != 0)
/*     */     {
/* 285 */       throw new IllegalStateException("Unable to open nested entry '" + entry
/* 285 */         .getName() + "'. It has been compressed and nested jar files must be stored without compression. Please check the mechanism used to create your executable jar file");
/*     */     }
/*     */ 
/* 289 */     RandomAccessData entryData = this.entries.getEntryData(entry.getName());
/* 290 */     return new JarFile(this.rootFile, this.pathFromRoot + "!/" + entry.getName(), entryData, JarFileType.NESTED_JAR);
/*     */   }
/*     */ 
/*     */   public int size()
/*     */   {
/* 296 */     return (int)this.data.getSize();
/*     */   }
/*     */ 
/*     */   public void close() throws IOException
/*     */   {
/* 301 */     this.rootFile.close();
/*     */   }
/*     */ 
/*     */   public URL getUrl()
/*     */     throws MalformedURLException
/*     */   {
/* 311 */     if (this.url == null) {
/* 312 */       Handler handler = new Handler(this);
/* 313 */       String file = this.rootFile.getFile().toURI() + this.pathFromRoot + "!/";
/* 314 */       file = file.replace("file:////", "file://");
/* 315 */       this.url = new URL("jar", "", -1, file, handler);
/*     */     }
/* 317 */     return this.url;
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/* 322 */     return getName();
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 327 */     return this.rootFile.getFile() + this.pathFromRoot;
/*     */   }
/*     */ 
/*     */   boolean isSigned() {
/* 331 */     return this.signed;
/*     */   }
/*     */ 
/*     */   void setupEntryCertificates(JarEntry entry)
/*     */   {
/*     */     try
/*     */     {
/* 339 */       JarInputStream inputStream = new JarInputStream(getData().getInputStream(RandomAccessData.ResourceAccess.ONCE));
/*     */       try {
/* 341 */         java.util.jar.JarEntry certEntry = inputStream.getNextJarEntry();
/* 342 */         while (certEntry != null) {
/* 343 */           inputStream.closeEntry();
/* 344 */           if (entry.getName().equals(certEntry.getName())) {
/* 345 */             setCertificates(entry, certEntry);
/*     */           }
/* 347 */           setCertificates(getJarEntry(certEntry.getName()), certEntry);
/* 348 */           certEntry = inputStream.getNextJarEntry();
/*     */         }
/*     */       }
/*     */       finally {
/* 352 */         inputStream.close();
/*     */       }
/*     */     }
/*     */     catch (IOException ex) {
/* 356 */       throw new IllegalStateException(ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   private void setCertificates(JarEntry entry, java.util.jar.JarEntry certEntry) {
/* 361 */     if (entry != null)
/* 362 */       entry.setCertificates(certEntry);
/*     */   }
/*     */ 
/*     */   public void clearCache()
/*     */   {
/* 367 */     this.entries.clearCache();
/*     */   }
/*     */ 
/*     */   protected String getPathFromRoot() {
/* 371 */     return this.pathFromRoot;
/*     */   }
/*     */ 
/*     */   JarFileType getType() {
/* 375 */     return this.type;
/*     */   }
/*     */ 
/*     */   public static void registerUrlProtocolHandler()
/*     */   {
/* 383 */     String handlers = System.getProperty("java.protocol.handler.pkgs", "");
/* 384 */     System.setProperty("java.protocol.handler.pkgs", handlers + "|" + "org.springframework.boot.loader");
/*     */ 
/* 386 */     resetCachedUrlHandlers();
/*     */   }
/*     */ 
/*     */   private static void resetCachedUrlHandlers()
/*     */   {
/*     */     try
/*     */     {
/* 396 */       URL.setURLStreamHandlerFactory(null);
/*     */     }
/*     */     catch (Error localError)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   static enum JarFileType
/*     */   {
/* 408 */     DIRECT, NESTED_DIRECTORY, NESTED_JAR;
/*     */   }
/*     */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     org.springframework.boot.loader.jar.JarFile
 * JD-Core Version:    0.6.0
 */