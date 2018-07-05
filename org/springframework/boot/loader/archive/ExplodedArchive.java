/*     */ package org.springframework.boot.loader.archive;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URI;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.Deque;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import java.util.jar.Manifest;
/*     */ 
/*     */ public class ExplodedArchive
/*     */   implements Archive
/*     */ {
/*  46 */   private static final Set<String> SKIPPED_NAMES = new HashSet(Arrays.asList(new String[] { ".", ".." }))
/*  46 */     ;
/*     */   private final File root;
/*     */   private final boolean recursive;
/*     */   private File manifestFile;
/*     */   private Manifest manifest;
/*     */ 
/*     */   public ExplodedArchive(File root)
/*     */   {
/*  61 */     this(root, true);
/*     */   }
/*     */ 
/*     */   public ExplodedArchive(File root, boolean recursive)
/*     */   {
/*  73 */     if ((!root.exists()) || (!root.isDirectory())) {
/*  74 */       throw new IllegalArgumentException("Invalid source folder " + root);
/*     */     }
/*  76 */     this.root = root;
/*  77 */     this.recursive = recursive;
/*  78 */     this.manifestFile = getManifestFile(root);
/*     */   }
/*     */ 
/*     */   private File getManifestFile(File root) {
/*  82 */     File metaInf = new File(root, "META-INF");
/*  83 */     return new File(metaInf, "MANIFEST.MF");
/*     */   }
/*     */ 
/*     */   public URL getUrl() throws MalformedURLException
/*     */   {
/*  88 */     return this.root.toURI().toURL();
/*     */   }
/*     */ 
/*     */   public Manifest getManifest() throws IOException
/*     */   {
/*  93 */     if ((this.manifest == null) && (this.manifestFile.exists())) {
/*  94 */       FileInputStream inputStream = new FileInputStream(this.manifestFile);
/*     */       try {
/*  96 */         this.manifest = new Manifest(inputStream);
/*     */ 
/*  99 */         inputStream.close(); } finally { inputStream.close();
/*     */       }
/*     */     }
/* 102 */     return this.manifest;
/*     */   }
/*     */ 
/*     */   public List<Archive> getNestedArchives(Archive.EntryFilter filter) throws IOException
/*     */   {
/* 107 */     List nestedArchives = new ArrayList();
/* 108 */     for (Archive.Entry entry : this) {
/* 109 */       if (filter.matches(entry)) {
/* 110 */         nestedArchives.add(getNestedArchive(entry));
/*     */       }
/*     */     }
/* 113 */     return Collections.unmodifiableList(nestedArchives);
/*     */   }
/*     */ 
/*     */   public Iterator<Archive.Entry> iterator()
/*     */   {
/* 118 */     return new FileEntryIterator(this.root, this.recursive);
/*     */   }
/*     */ 
/*     */   protected Archive getNestedArchive(Archive.Entry entry) throws IOException {
/* 122 */     File file = ((FileEntry)entry).getFile();
/* 123 */     return file.isDirectory() ? new ExplodedArchive(file) : new JarFileArchive(file);
/*     */   }
/*     */ 
/*     */   public String toString()
/*     */   {
/*     */     try
/*     */     {
/* 130 */       return getUrl().toString();
/*     */     } catch (Exception ex) {
/*     */     }
/* 133 */     return "exploded archive";
/*     */   }
/*     */ 
/*     */   private static class FileEntry
/*     */     implements Archive.Entry
/*     */   {
/*     */     private final String name;
/*     */     private final File file;
/*     */ 
/*     */     FileEntry(String name, File file)
/*     */     {
/* 231 */       this.name = name;
/* 232 */       this.file = file;
/*     */     }
/*     */ 
/*     */     public File getFile() {
/* 236 */       return this.file;
/*     */     }
/*     */ 
/*     */     public boolean isDirectory()
/*     */     {
/* 241 */       return this.file.isDirectory();
/*     */     }
/*     */ 
/*     */     public String getName()
/*     */     {
/* 246 */       return this.name;
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class FileEntryIterator
/*     */     implements Iterator<Archive.Entry>
/*     */   {
/* 142 */     private final Comparator<File> entryComparator = new EntryComparator(null);
/*     */     private final File root;
/*     */     private final boolean recursive;
/* 148 */     private final Deque<Iterator<File>> stack = new LinkedList();
/*     */     private File current;
/*     */ 
/*     */     FileEntryIterator(File root, boolean recursive)
/*     */     {
/* 153 */       this.root = root;
/* 154 */       this.recursive = recursive;
/* 155 */       this.stack.add(listFiles(root));
/* 156 */       this.current = poll();
/*     */     }
/*     */ 
/*     */     public boolean hasNext()
/*     */     {
/* 161 */       return this.current != null;
/*     */     }
/*     */ 
/*     */     public Archive.Entry next()
/*     */     {
/* 166 */       if (this.current == null) {
/* 167 */         throw new NoSuchElementException();
/*     */       }
/* 169 */       File file = this.current;
/* 170 */       if ((file.isDirectory()) && ((this.recursive) || 
/* 171 */         (file
/* 171 */         .getParentFile().equals(this.root)))) {
/* 172 */         this.stack.addFirst(listFiles(file));
/*     */       }
/* 174 */       this.current = poll();
/*     */ 
/* 176 */       String name = file.toURI().getPath()
/* 176 */         .substring(this.root
/* 176 */         .toURI().getPath().length());
/* 177 */       return new ExplodedArchive.FileEntry(name, file);
/*     */     }
/*     */ 
/*     */     private Iterator<File> listFiles(File file) {
/* 181 */       File[] files = file.listFiles();
/* 182 */       if (files == null) {
/* 183 */         return Collections.emptyList().iterator();
/*     */       }
/* 185 */       Arrays.sort(files, this.entryComparator);
/* 186 */       return Arrays.asList(files).iterator();
/*     */     }
/*     */ 
/*     */     private File poll() {
/* 190 */       while (!this.stack.isEmpty()) {
/* 191 */         while (((Iterator)this.stack.peek()).hasNext()) {
/* 192 */           File file = (File)((Iterator)this.stack.peek()).next();
/* 193 */           if (!ExplodedArchive.SKIPPED_NAMES.contains(file.getName())) {
/* 194 */             return file;
/*     */           }
/*     */         }
/* 197 */         this.stack.poll();
/*     */       }
/* 199 */       return null;
/*     */     }
/*     */ 
/*     */     public void remove()
/*     */     {
/* 204 */       throw new UnsupportedOperationException("remove");
/*     */     }
/*     */ 
/*     */     private static class EntryComparator
/*     */       implements Comparator<File>
/*     */     {
/*     */       public int compare(File o1, File o2)
/*     */       {
/* 214 */         return o1.getAbsolutePath().compareTo(o2.getAbsolutePath());
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     org.springframework.boot.loader.archive.ExplodedArchive
 * JD-Core Version:    0.6.0
 */