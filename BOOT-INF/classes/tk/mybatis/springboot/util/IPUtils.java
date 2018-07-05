/*    */ package tk.mybatis.springboot.util;
/*    */ 
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ 
/*    */ public class IPUtils
/*    */ {
/*    */   public static String getClientAddress(HttpServletRequest request)
/*    */   {
/* 11 */     if (request == null) {
/* 12 */       return "unknown";
/*    */     }
/* 14 */     String ip = request.getHeader("x-forwarded-for");
/* 15 */     if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
/* 16 */       ip = request.getHeader("Proxy-Client-IP");
/*    */     }
/* 18 */     if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
/* 19 */       ip = request.getHeader("X-Forwarded-For");
/*    */     }
/* 21 */     if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
/* 22 */       ip = request.getHeader("WL-Proxy-Client-IP");
/*    */     }
/* 24 */     if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
/* 25 */       ip = request.getHeader("X-Real-IP");
/*    */     }
/*    */ 
/* 28 */     if ((ip == null) || (ip.length() == 0) || ("unknown".equalsIgnoreCase(ip))) {
/* 29 */       ip = request.getRemoteAddr();
/*    */     }
/* 31 */     return ip;
/*    */   }
/*    */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.util.IPUtils
 * JD-Core Version:    0.6.0
 */