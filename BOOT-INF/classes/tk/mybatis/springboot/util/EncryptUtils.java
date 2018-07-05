/*    */ package tk.mybatis.springboot.util;
/*    */ 
/*    */ import java.security.MessageDigest;
/*    */ import java.security.NoSuchAlgorithmException;
/*    */ import java.util.UUID;
/*    */ 
/*    */ public class EncryptUtils
/*    */ {
/*  9 */   public static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
/*    */ 
/*    */   public static String toHexString(byte[] bs)
/*    */   {
/*    */     int len;
/* 14 */     if ((bs != null) && ((len = bs.length) != 0)) {
/* 15 */       char[] cs = new char[len << 1];
/* 16 */       char[] myDigits = DIGITS;
/*    */ 
/* 18 */       int i = 0; for (int j = 0; i < len; i++)
/*    */       {
/*    */         byte tmp44_43 = bs[i]; byte b = tmp44_43; cs[(j++)] = myDigits[(tmp44_43 >>> 4 & 0xF)];
/* 20 */         cs[(j++)] = myDigits[(b & 0xF)];
/*    */       }
/* 22 */       return String.valueOf(cs);
/*    */     }
/* 24 */     return null;
/*    */   }
/*    */ 
/*    */   private static String digest(String name, String source) throws NoSuchAlgorithmException
/*    */   {
/* 29 */     if (source != null) {
/* 30 */       MessageDigest md = MessageDigest.getInstance(name);
/* 31 */       md.update(source.getBytes());
/* 32 */       return toHexString(md.digest());
/*    */     }
/* 34 */     return null;
/*    */   }
/*    */ 
/*    */   private static String digest(String name, byte[] bytes) throws NoSuchAlgorithmException
/*    */   {
/* 39 */     if (bytes != null) {
/* 40 */       MessageDigest md = MessageDigest.getInstance(name);
/* 41 */       md.update(bytes);
/* 42 */       return toHexString(md.digest());
/*    */     }
/* 44 */     return null;
/*    */   }
/*    */ 
/*    */   public static String SHA1_HEX(String source) {
/*    */     try {
/* 49 */       return digest("SHA-1", source);
/*    */     } catch (NoSuchAlgorithmException ignore) {
/*    */     }
/* 52 */     return null;
/*    */   }
/*    */ 
/*    */   public static String SHA1_HEX(byte[] bytes) {
/*    */     try {
/* 57 */       return digest("SHA-1", bytes);
/*    */     } catch (NoSuchAlgorithmException bytesz) {
/*    */     }
/* 60 */     return null;
/*    */   }
/*    */ 
/*    */   public static String MD5_HEX(String source) {
/*    */     try {
/* 65 */       return digest("MD5", source);
/*    */     } catch (NoSuchAlgorithmException ignore) {
/*    */     }
/* 68 */     return null;
/*    */   }
/*    */ 
/*    */   public static String MD5_HEX(byte[] bytes) {
/*    */     try {
/* 73 */       return digest("MD5", bytes);
/*    */     } catch (NoSuchAlgorithmException ignore) {
/*    */     }
/* 76 */     return null;
/*    */   }
/*    */ 
/*    */   public static String uuid() {
/* 80 */     String uuid = UUID.randomUUID().toString();
/* 81 */     return uuid.replaceAll("-", "");
/*    */   }
/*    */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.util.EncryptUtils
 * JD-Core Version:    0.6.0
 */