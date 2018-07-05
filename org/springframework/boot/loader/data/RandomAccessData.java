/*    */ package org.springframework.boot.loader.data;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ 
/*    */ public abstract interface RandomAccessData
/*    */ {
/*    */   public abstract InputStream getInputStream(ResourceAccess paramResourceAccess)
/*    */     throws IOException;
/*    */ 
/*    */   public abstract RandomAccessData getSubsection(long paramLong1, long paramLong2);
/*    */ 
/*    */   public abstract long getSize();
/*    */ 
/*    */   public static enum ResourceAccess
/*    */   {
/* 62 */     ONCE, 
/*    */ 
/* 67 */     PER_READ;
/*    */   }
/*    */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     org.springframework.boot.loader.data.RandomAccessData
 * JD-Core Version:    0.6.0
 */