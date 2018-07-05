/*    */ package tk.mybatis.springboot.util;
/*    */ 
/*    */ public class StringUtil
/*    */ {
/*    */   public static boolean equalsIgnoreCase(CharSequence str1, CharSequence str2)
/*    */   {
/*  6 */     if ((str1 == null) || (str2 == null))
/*  7 */       return str1 == str2;
/*  8 */     if (str1 == str2)
/*  9 */       return true;
/* 10 */     if (str1.length() != str2.length()) {
/* 11 */       return false;
/*    */     }
/* 13 */     return regionMatches(str1, true, 0, str2, 0, str1.length());
/*    */   }
/*    */ 
/*    */   public static String trim(String str)
/*    */   {
/* 19 */     return str == null ? null : str.trim();
/*    */   }
/*    */ 
/*    */   static boolean regionMatches(CharSequence cs, boolean ignoreCase, int thisStart, CharSequence substring, int start, int length) {
/* 23 */     if (((cs instanceof String)) && ((substring instanceof String))) {
/* 24 */       return ((String)cs).regionMatches(ignoreCase, thisStart, (String)substring, start, length);
/*    */     }
/*    */ 
/* 27 */     int index1 = thisStart;
/* 28 */     int index2 = start;
/* 29 */     int tmpLen = length;
/*    */ 
/* 31 */     while (tmpLen-- > 0) {
/* 32 */       char c1 = cs.charAt(index1++);
/* 33 */       char c2 = substring.charAt(index2++);
/*    */ 
/* 35 */       if (c1 == c2)
/*    */       {
/*    */         continue;
/*    */       }
/* 39 */       if (!ignoreCase) {
/* 40 */         return false;
/*    */       }
/*    */ 
/* 43 */       if ((Character.toUpperCase(c1) != Character.toUpperCase(c2)) && (Character.toLowerCase(c1) != Character.toLowerCase(c2)))
/*    */       {
/* 45 */         return false;
/*    */       }
/*    */     }
/*    */ 
/* 49 */     return true;
/*    */   }
/*    */ 
/*    */   public static boolean isBlank(String str)
/*    */   {
/*    */     int strLen;
/* 53 */     if ((str != null) && ((strLen = str.length()) != 0)) {
/* 54 */       for (int i = 0; i < strLen; i++) {
/* 55 */         if (!Character.isWhitespace(str.charAt(i))) {
/* 56 */           return false;
/*    */         }
/*    */       }
/* 59 */       return true;
/*    */     }
/* 61 */     return true;
/*    */   }
/*    */ 
/*    */   public static boolean isBlank(Long str)
/*    */   {
/* 68 */     return str != null;
/*    */   }
/*    */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.util.StringUtil
 * JD-Core Version:    0.6.0
 */