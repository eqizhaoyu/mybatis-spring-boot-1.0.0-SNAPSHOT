/*    */ package tk.mybatis.springboot.util;
/*    */ 
/*    */ import java.net.InetAddress;
/*    */ import java.net.NetworkInterface;
/*    */ import java.util.Enumeration;
/*    */ 
/*    */ public class IpGetter
/*    */ {
/*    */   public static String getLocalIP()
/*    */   {
/* 30 */     String sIP = "";
/* 31 */     InetAddress ip = null;
/*    */     try {
/* 33 */       boolean bFindIP = false;
/* 34 */       Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
/* 35 */       while ((netInterfaces.hasMoreElements()) && 
/* 36 */         (!bFindIP))
/*    */       {
/* 39 */         NetworkInterface ni = (NetworkInterface)netInterfaces.nextElement();
/*    */ 
/* 42 */         Enumeration ips = ni.getInetAddresses();
/* 43 */         while (ips.hasMoreElements()) {
/* 44 */           ip = (InetAddress)ips.nextElement();
/* 45 */           if ((!ip.isSiteLocalAddress()) || (ip.isLoopbackAddress()) || (ip.getHostAddress().indexOf(":") != -1))
/*    */             continue;
/* 47 */           bFindIP = true;
/*    */         }
/*    */       }
/*    */     }
/*    */     catch (Exception e)
/*    */     {
/* 53 */       e.printStackTrace();
/*    */     }
/* 55 */     if (null != ip) {
/* 56 */       sIP = ip.getHostAddress();
/*    */     }
/* 58 */     return sIP;
/*    */   }
/*    */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.util.IpGetter
 * JD-Core Version:    0.6.0
 */