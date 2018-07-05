/*     */ package org.springframework.boot.loader.data;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.util.Queue;
/*     */ import java.util.concurrent.ConcurrentLinkedQueue;
/*     */ import java.util.concurrent.Semaphore;
/*     */ 
/*     */ public class RandomAccessDataFile
/*     */   implements RandomAccessData
/*     */ {
/*     */   private static final int DEFAULT_CONCURRENT_READS = 4;
/*     */   private final File file;
/*     */   private final FilePool filePool;
/*     */   private final long offset;
/*     */   private final long length;
/*     */ 
/*     */   public RandomAccessDataFile(File file)
/*     */   {
/*  51 */     this(file, 4);
/*     */   }
/*     */ 
/*     */   public RandomAccessDataFile(File file, int concurrentReads)
/*     */   {
/*  63 */     if (file == null) {
/*  64 */       throw new IllegalArgumentException("File must not be null");
/*     */     }
/*  66 */     if (!file.exists()) {
/*  67 */       throw new IllegalArgumentException("File must exist");
/*     */     }
/*  69 */     this.file = file;
/*  70 */     this.filePool = new FilePool(concurrentReads);
/*  71 */     this.offset = 0L;
/*  72 */     this.length = file.length();
/*     */   }
/*     */ 
/*     */   private RandomAccessDataFile(File file, FilePool pool, long offset, long length)
/*     */   {
/*  83 */     this.file = file;
/*  84 */     this.filePool = pool;
/*  85 */     this.offset = offset;
/*  86 */     this.length = length;
/*     */   }
/*     */ 
/*     */   public File getFile()
/*     */   {
/*  94 */     return this.file;
/*     */   }
/*     */ 
/*     */   public InputStream getInputStream(RandomAccessData.ResourceAccess access) throws IOException
/*     */   {
/*  99 */     return new DataInputStream(access);
/*     */   }
/*     */ 
/*     */   public RandomAccessData getSubsection(long offset, long length)
/*     */   {
/* 104 */     if ((offset < 0L) || (length < 0L) || (offset + length > this.length)) {
/* 105 */       throw new IndexOutOfBoundsException();
/*     */     }
/* 107 */     return new RandomAccessDataFile(this.file, this.filePool, this.offset + offset, length);
/*     */   }
/*     */ 
/*     */   public long getSize()
/*     */   {
/* 113 */     return this.length;
/*     */   }
/*     */ 
/*     */   public void close() throws IOException {
/* 117 */     this.filePool.close();
/*     */   }
/*     */ 
/*     */   private class FilePool
/*     */   {
/*     */     private final int size;
/*     */     private final Semaphore available;
/*     */     private final Queue<RandomAccessFile> files;
/*     */ 
/*     */     FilePool(int size)
/*     */     {
/* 241 */       this.size = size;
/* 242 */       this.available = new Semaphore(size);
/* 243 */       this.files = new ConcurrentLinkedQueue();
/*     */     }
/*     */ 
/*     */     public RandomAccessFile acquire() throws IOException {
/* 247 */       this.available.acquireUninterruptibly();
/* 248 */       RandomAccessFile file = (RandomAccessFile)this.files.poll();
/* 249 */       if (file != null) {
/* 250 */         return file;
/*     */       }
/* 252 */       return new RandomAccessFile(RandomAccessDataFile.this.file, "r");
/*     */     }
/*     */ 
/*     */     public void release(RandomAccessFile file) {
/* 256 */       this.files.add(file);
/* 257 */       this.available.release();
/*     */     }
/*     */ 
/*     */     public void close() throws IOException {
/* 261 */       this.available.acquireUninterruptibly(this.size);
/*     */       try {
/* 263 */         RandomAccessFile pooledFile = (RandomAccessFile)this.files.poll();
/* 264 */         while (pooledFile != null) {
/* 265 */           pooledFile.close();
/* 266 */           pooledFile = (RandomAccessFile)this.files.poll();
/*     */         }
/*     */ 
/* 270 */         this.available.release(this.size); } finally { this.available.release(this.size);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private class DataInputStream extends InputStream
/*     */   {
/*     */     private RandomAccessFile file;
/*     */     private int position;
/*     */ 
/*     */     DataInputStream(RandomAccessData.ResourceAccess access)
/*     */       throws IOException
/*     */     {
/* 131 */       if (access == RandomAccessData.ResourceAccess.ONCE) {
/* 132 */         this.file = new RandomAccessFile(RandomAccessDataFile.this.file, "r");
/* 133 */         this.file.seek(RandomAccessDataFile.this.offset);
/*     */       }
/*     */     }
/*     */ 
/*     */     public int read() throws IOException
/*     */     {
/* 139 */       return doRead(null, 0, 1);
/*     */     }
/*     */ 
/*     */     public int read(byte[] b) throws IOException
/*     */     {
/* 144 */       return read(b, 0, b == null ? 0 : b.length);
/*     */     }
/*     */ 
/*     */     public int read(byte[] b, int off, int len) throws IOException
/*     */     {
/* 149 */       if (b == null) {
/* 150 */         throw new NullPointerException("Bytes must not be null");
/*     */       }
/* 152 */       return doRead(b, off, len);
/*     */     }
/*     */ 
/*     */     public int doRead(byte[] b, int off, int len)
/*     */       throws IOException
/*     */     {
/* 165 */       if (len == 0) {
/* 166 */         return 0;
/*     */       }
/* 168 */       int cappedLen = cap(len);
/* 169 */       if (cappedLen <= 0) {
/* 170 */         return -1;
/*     */       }
/* 172 */       RandomAccessFile file = this.file;
/* 173 */       if (file == null) {
/* 174 */         file = RandomAccessDataFile.this.filePool.acquire();
/* 175 */         file.seek(RandomAccessDataFile.this.offset + this.position);
/*     */       }
/*     */       try {
/* 178 */         if (b == null) {
/* 179 */           rtn = file.read();
/* 180 */           moveOn(rtn == -1 ? 0 : 1);
/* 181 */           int i = rtn;
/*     */           return i;
/*     */         }
/*     */ 
/* 184 */         int rtn = (int)moveOn(file.read(b, off, cappedLen));
/*     */         return rtn;
/*     */       }
/*     */       finally
/*     */       {
/* 188 */         if (this.file == null)
/* 189 */           RandomAccessDataFile.this.filePool.release(file); 
/* 189 */       }throw localObject;
/*     */     }
/*     */ 
/*     */     public long skip(long n)
/*     */       throws IOException
/*     */     {
/* 196 */       return n <= 0L ? 0L : moveOn(cap(n));
/*     */     }
/*     */ 
/*     */     public void close() throws IOException
/*     */     {
/* 201 */       if (this.file != null)
/* 202 */         this.file.close();
/*     */     }
/*     */ 
/*     */     private int cap(long n)
/*     */     {
/* 213 */       return (int)Math.min(RandomAccessDataFile.this.length - this.position, n);
/*     */     }
/*     */ 
/*     */     private long moveOn(int amount)
/*     */     {
/* 222 */       this.position += amount;
/* 223 */       return amount;
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     org.springframework.boot.loader.data.RandomAccessDataFile
 * JD-Core Version:    0.6.0
 */