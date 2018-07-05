/*    */ package tk.mybatis.springboot.util;
/*    */ 
/*    */ import java.io.PrintStream;
/*    */ 
/*    */ public class PasswordEncoder
/*    */ {
/*    */   public static String encrypt(String src, Object salt)
/*    */   {
/*  7 */     return encrypt(src, String.valueOf(salt));
/*    */   }
/*    */ 
/*    */   public static String encrypt(String src, String salt)
/*    */   {
/* 17 */     return EncryptUtils.MD5_HEX(EncryptUtils.MD5_HEX(src) + salt);
/*    */   }
/*    */ 
/*    */   public static void main(String[] args) {
/* 21 */     System.out.println(encrypt("admin", "admin"));
/*    */   }
/*    */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.util.PasswordEncoder
 * JD-Core Version:    0.6.0
 */