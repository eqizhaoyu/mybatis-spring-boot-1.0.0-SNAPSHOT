/*     */ package org.springframework.boot.loader.jar;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FilePermission;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.net.URLEncoder;
/*     */ import java.net.URLStreamHandler;
/*     */ import java.security.Permission;
/*     */ import org.springframework.boot.loader.data.RandomAccessData;
/*     */ import org.springframework.boot.loader.data.RandomAccessData.ResourceAccess;
/*     */ import org.springframework.boot.loader.data.RandomAccessDataFile;
/*     */ 
/*     */ final class JarURLConnection extends java.net.JarURLConnection
/*     */ {
/*  42 */   private static ThreadLocal<Boolean> useFastExceptions = new ThreadLocal();
/*     */ 
/*  44 */   private static final FileNotFoundException FILE_NOT_FOUND_EXCEPTION = new FileNotFoundException("Jar file or entry not found");
/*     */ 
/*  47 */   private static final IllegalStateException NOT_FOUND_CONNECTION_EXCEPTION = new IllegalStateException(FILE_NOT_FOUND_EXCEPTION);
/*     */   private static final String SEPARATOR = "!/";
/*     */   private static final URL EMPTY_JAR_URL;
/*     */   private static final JarEntryName EMPTY_JAR_ENTRY_NAME;
/*     */   private static final String READ_ACTION = "read";
/*     */   private static final JarURLConnection NOT_FOUND_CONNECTION;
/*     */   private final JarFile jarFile;
/*     */   private Permission permission;
/*     */   private URL jarFileUrl;
/*     */   private final JarEntryName jarEntryName;
/*     */   private JarEntry jarEntry;
/*     */ 
/*     */   private JarURLConnection(URL url, JarFile jarFile, JarEntryName jarEntryName)
/*     */     throws IOException
/*     */   {
/*  90 */     super(EMPTY_JAR_URL);
/*  91 */     this.url = url;
/*  92 */     this.jarFile = jarFile;
/*  93 */     this.jarEntryName = jarEntryName;
/*     */   }
/*     */ 
/*     */   public void connect() throws IOException
/*     */   {
/*  98 */     if (this.jarFile == null) {
/*  99 */       throw FILE_NOT_FOUND_EXCEPTION;
/*     */     }
/* 101 */     if ((!this.jarEntryName.isEmpty()) && (this.jarEntry == null)) {
/* 102 */       this.jarEntry = this.jarFile.getJarEntry(getEntryName());
/* 103 */       if (this.jarEntry == null) {
/* 104 */         throwFileNotFound(this.jarEntryName, this.jarFile);
/*     */       }
/*     */     }
/* 107 */     this.connected = true;
/*     */   }
/*     */ 
/*     */   public JarFile getJarFile() throws IOException
/*     */   {
/* 112 */     connect();
/* 113 */     return this.jarFile;
/*     */   }
/*     */ 
/*     */   public URL getJarFileURL()
/*     */   {
/* 118 */     if (this.jarFile == null) {
/* 119 */       throw NOT_FOUND_CONNECTION_EXCEPTION;
/*     */     }
/* 121 */     if (this.jarFileUrl == null) {
/* 122 */       this.jarFileUrl = buildJarFileUrl();
/*     */     }
/* 124 */     return this.jarFileUrl;
/*     */   }
/*     */ 
/*     */   private URL buildJarFileUrl() {
/*     */     try {
/* 129 */       String spec = this.jarFile.getUrl().getFile();
/* 130 */       if (spec.endsWith("!/")) {
/* 131 */         spec = spec.substring(0, spec.length() - "!/".length());
/*     */       }
/* 133 */       if (spec.indexOf("!/") == -1) {
/* 134 */         return new URL(spec);
/*     */       }
/* 136 */       return new URL("jar:" + spec);
/*     */     } catch (MalformedURLException ex) {
/*     */     }
/* 139 */     throw new IllegalStateException(ex);
/*     */   }
/*     */ 
/*     */   public JarEntry getJarEntry()
/*     */     throws IOException
/*     */   {
/* 145 */     if ((this.jarEntryName == null) || (this.jarEntryName.isEmpty())) {
/* 146 */       return null;
/*     */     }
/* 148 */     connect();
/* 149 */     return this.jarEntry;
/*     */   }
/*     */ 
/*     */   public String getEntryName()
/*     */   {
/* 154 */     if (this.jarFile == null) {
/* 155 */       throw NOT_FOUND_CONNECTION_EXCEPTION;
/*     */     }
/* 157 */     return this.jarEntryName.toString();
/*     */   }
/*     */ 
/*     */   public InputStream getInputStream() throws IOException
/*     */   {
/* 162 */     if (this.jarFile == null) {
/* 163 */       throw FILE_NOT_FOUND_EXCEPTION;
/*     */     }
/* 165 */     if ((this.jarEntryName.isEmpty()) && 
/* 166 */       (this.jarFile
/* 166 */       .getType() == JarFile.JarFileType.DIRECT)) {
/* 167 */       throw new IOException("no entry name specified");
/*     */     }
/* 169 */     connect();
/*     */ 
/* 172 */     InputStream inputStream = this.jarEntryName.isEmpty() ? this.jarFile
/* 171 */       .getData().getInputStream(RandomAccessData.ResourceAccess.ONCE) : this.jarFile
/* 172 */       .getInputStream(this.jarEntry);
/*     */ 
/* 173 */     if (inputStream == null) {
/* 174 */       throwFileNotFound(this.jarEntryName, this.jarFile);
/*     */     }
/* 176 */     return inputStream;
/*     */   }
/*     */ 
/*     */   private void throwFileNotFound(Object entry, JarFile jarFile) throws FileNotFoundException
/*     */   {
/* 181 */     if (Boolean.TRUE.equals(useFastExceptions.get())) {
/* 182 */       throw FILE_NOT_FOUND_EXCEPTION;
/*     */     }
/*     */ 
/* 185 */     throw new FileNotFoundException("JAR entry " + entry + " not found in " + jarFile
/* 185 */       .getName());
/*     */   }
/*     */ 
/*     */   public int getContentLength()
/*     */   {
/* 190 */     if (this.jarFile == null)
/* 191 */       return -1;
/*     */     try
/*     */     {
/* 194 */       if (this.jarEntryName.isEmpty()) {
/* 195 */         return this.jarFile.size();
/*     */       }
/* 197 */       JarEntry entry = getJarEntry();
/* 198 */       return entry == null ? -1 : (int)entry.getSize();
/*     */     } catch (IOException ex) {
/*     */     }
/* 201 */     return -1;
/*     */   }
/*     */ 
/*     */   public Object getContent()
/*     */     throws IOException
/*     */   {
/* 207 */     connect();
/* 208 */     return this.jarEntryName.isEmpty() ? this.jarFile : super.getContent();
/*     */   }
/*     */ 
/*     */   public String getContentType()
/*     */   {
/* 213 */     return this.jarEntryName == null ? null : this.jarEntryName.getContentType();
/*     */   }
/*     */ 
/*     */   public Permission getPermission() throws IOException
/*     */   {
/* 218 */     if (this.jarFile == null) {
/* 219 */       throw FILE_NOT_FOUND_EXCEPTION;
/*     */     }
/* 221 */     if (this.permission == null) {
/* 222 */       this.permission = 
/* 223 */         new FilePermission(this.jarFile
/* 223 */         .getRootJarFile().getFile().getPath(), "read");
/*     */     }
/* 225 */     return this.permission;
/*     */   }
/*     */ 
/*     */   static void setUseFastExceptions(boolean useFastExceptions) {
/* 229 */     useFastExceptions.set(Boolean.valueOf(useFastExceptions));
/*     */   }
/*     */ 
/*     */   static JarURLConnection get(URL url, JarFile jarFile) throws IOException {
/* 233 */     String spec = extractFullSpec(url, jarFile.getPathFromRoot());
/*     */ 
/* 235 */     int index = 0;
/*     */     int separator;
/* 236 */     while ((separator = spec.indexOf("!/", index)) > 0) {
/* 237 */       String entryName = spec.substring(index, separator);
/* 238 */       JarEntry jarEntry = jarFile.getJarEntry(entryName);
/* 239 */       if (jarEntry == null) {
/* 240 */         return notFound(jarFile, JarEntryName.get(entryName));
/*     */       }
/* 242 */       jarFile = jarFile.getNestedJarFile(jarEntry);
/* 243 */       index += separator + "!/".length();
/*     */     }
/* 245 */     JarEntryName jarEntryName = JarEntryName.get(spec, index);
/* 246 */     if ((Boolean.TRUE.equals(useFastExceptions.get())) && 
/* 247 */       (!jarEntryName.isEmpty()) && 
/* 248 */       (!jarFile
/* 248 */       .containsEntry(jarEntryName
/* 248 */       .toString()))) {
/* 249 */       return NOT_FOUND_CONNECTION;
/*     */     }
/*     */ 
/* 252 */     return new JarURLConnection(url, jarFile, jarEntryName);
/*     */   }
/*     */ 
/*     */   private static String extractFullSpec(URL url, String pathFromRoot) {
/* 256 */     String file = url.getFile();
/* 257 */     int separatorIndex = file.indexOf("!/");
/* 258 */     if (separatorIndex < 0) {
/* 259 */       return "";
/*     */     }
/* 261 */     int specIndex = separatorIndex + "!/".length() + pathFromRoot.length();
/* 262 */     return file.substring(specIndex);
/*     */   }
/*     */ 
/*     */   private static JarURLConnection notFound() {
/*     */     try {
/* 267 */       return notFound(null, null);
/*     */     } catch (IOException ex) {
/*     */     }
/* 270 */     throw new IllegalStateException(ex);
/*     */   }
/*     */ 
/*     */   private static JarURLConnection notFound(JarFile jarFile, JarEntryName jarEntryName)
/*     */     throws IOException
/*     */   {
/* 276 */     if (Boolean.TRUE.equals(useFastExceptions.get())) {
/* 277 */       return NOT_FOUND_CONNECTION;
/*     */     }
/* 279 */     return new JarURLConnection(null, jarFile, jarEntryName);
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*     */     try
/*     */     {
/*  56 */       EMPTY_JAR_URL = new URL("jar:", null, 0, "file:!/", new URLStreamHandler()
/*     */       {
/*     */         protected URLConnection openConnection(URL u)
/*     */           throws IOException
/*     */         {
/*  61 */           return null;
/*     */         } } );
/*     */     }
/*     */     catch (MalformedURLException ex) {
/*  66 */       throw new IllegalStateException(ex);
/*     */     }
/*     */ 
/*  70 */     EMPTY_JAR_ENTRY_NAME = new JarEntryName("");
/*     */ 
/*  75 */     NOT_FOUND_CONNECTION = notFound();
/*     */   }
/*     */ 
/*     */   static class JarEntryName
/*     */   {
/*     */     private final String name;
/*     */     private String contentType;
/*     */ 
/*     */     JarEntryName(String spec)
/*     */     {
/* 292 */       this.name = decode(spec);
/*     */     }
/*     */ 
/*     */     private String decode(String source) {
/* 296 */       if ((source.isEmpty()) || (source.indexOf('%') < 0)) {
/* 297 */         return source;
/*     */       }
/* 299 */       ByteArrayOutputStream bos = new ByteArrayOutputStream(source.length());
/* 300 */       write(source, bos);
/*     */ 
/* 302 */       return AsciiBytes.toString(bos.toByteArray());
/*     */     }
/*     */ 
/*     */     private void write(String source, ByteArrayOutputStream outputStream) {
/* 306 */       int length = source.length();
/* 307 */       for (int i = 0; i < length; i++) {
/* 308 */         int c = source.charAt(i);
/* 309 */         if (c > 127) {
/*     */           try {
/* 311 */             String encoded = URLEncoder.encode(String.valueOf((char)c), "UTF-8");
/*     */ 
/* 313 */             write(encoded, outputStream);
/*     */           }
/*     */           catch (UnsupportedEncodingException ex) {
/* 316 */             throw new IllegalStateException(ex);
/*     */           }
/*     */         }
/*     */         else {
/* 320 */           if (c == 37) {
/* 321 */             if (i + 2 >= length)
/*     */             {
/* 323 */               throw new IllegalArgumentException("Invalid encoded sequence \"" + source
/* 323 */                 .substring(i) + 
/* 323 */                 "\"");
/*     */             }
/*     */ 
/* 326 */             c = decodeEscapeSequence(source, i);
/* 327 */             i += 2;
/*     */           }
/* 329 */           outputStream.write(c);
/*     */         }
/*     */       }
/*     */     }
/*     */ 
/*     */     private char decodeEscapeSequence(String source, int i) {
/* 335 */       int hi = Character.digit(source.charAt(i + 1), 16);
/* 336 */       int lo = Character.digit(source.charAt(i + 2), 16);
/* 337 */       if ((hi == -1) || (lo == -1))
/*     */       {
/* 339 */         throw new IllegalArgumentException("Invalid encoded sequence \"" + source
/* 339 */           .substring(i) + 
/* 339 */           "\"");
/*     */       }
/* 341 */       return (char)((hi << 4) + lo);
/*     */     }
/*     */ 
/*     */     public String toString()
/*     */     {
/* 346 */       return this.name;
/*     */     }
/*     */ 
/*     */     public boolean isEmpty() {
/* 350 */       return this.name.isEmpty();
/*     */     }
/*     */ 
/*     */     public String getContentType() {
/* 354 */       if (this.contentType == null) {
/* 355 */         this.contentType = deduceContentType();
/*     */       }
/* 357 */       return this.contentType;
/*     */     }
/*     */ 
/*     */     private String deduceContentType()
/*     */     {
/* 362 */       String type = isEmpty() ? "x-java/jar" : null;
/* 363 */       type = type != null ? type : URLConnection.guessContentTypeFromName(toString());
/* 364 */       type = type != null ? type : "content/unknown";
/* 365 */       return type;
/*     */     }
/*     */ 
/*     */     public static JarEntryName get(String spec) {
/* 369 */       return get(spec, 0);
/*     */     }
/*     */ 
/*     */     public static JarEntryName get(String spec, int beginIndex) {
/* 373 */       if (spec.length() <= beginIndex) {
/* 374 */         return JarURLConnection.EMPTY_JAR_ENTRY_NAME;
/*     */       }
/* 376 */       return new JarEntryName(spec.substring(beginIndex));
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     org.springframework.boot.loader.jar.JarURLConnection
 * JD-Core Version:    0.6.0
 */