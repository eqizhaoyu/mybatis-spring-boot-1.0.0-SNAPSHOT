/*    */ package org.springframework.boot.loader.data;
/*    */ 
/*    */ import java.io.ByteArrayInputStream;
/*    */ import java.io.InputStream;
/*    */ 
/*    */ public class ByteArrayRandomAccessData
/*    */   implements RandomAccessData
/*    */ {
/*    */   private final byte[] bytes;
/*    */   private final long offset;
/*    */   private final long length;
/*    */ 
/*    */   public ByteArrayRandomAccessData(byte[] bytes)
/*    */   {
/* 36 */     this(bytes, 0L, bytes == null ? 0 : bytes.length);
/*    */   }
/*    */ 
/*    */   public ByteArrayRandomAccessData(byte[] bytes, long offset, long length) {
/* 40 */     this.bytes = (bytes == null ? new byte[0] : bytes);
/* 41 */     this.offset = offset;
/* 42 */     this.length = length;
/*    */   }
/*    */ 
/*    */   public InputStream getInputStream(RandomAccessData.ResourceAccess access)
/*    */   {
/* 47 */     return new ByteArrayInputStream(this.bytes, (int)this.offset, (int)this.length);
/*    */   }
/*    */ 
/*    */   public RandomAccessData getSubsection(long offset, long length)
/*    */   {
/* 52 */     return new ByteArrayRandomAccessData(this.bytes, this.offset + offset, length);
/*    */   }
/*    */ 
/*    */   public long getSize()
/*    */   {
/* 57 */     return this.length;
/*    */   }
/*    */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     org.springframework.boot.loader.data.ByteArrayRandomAccessData
 * JD-Core Version:    0.6.0
 */