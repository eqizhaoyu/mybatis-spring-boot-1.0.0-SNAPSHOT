/*    */ package tk.mybatis.springboot.sys.util;
/*    */ 
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpSession;
/*    */ import org.springframework.web.context.request.RequestAttributes;
/*    */ import org.springframework.web.context.request.RequestContextHolder;
/*    */ import org.springframework.web.context.request.ServletRequestAttributes;
/*    */ import tk.mybatis.springboot.sys.model.SysUser;
/*    */ 
/*    */ public class SysUserUtils
/*    */ {
/*    */   public static SysUser getSessionLoginUser()
/*    */   {
/* 19 */     return (SysUser)getSession().getAttribute("LOGIN_USER");
/*    */   }
/*    */ 
/*    */   public static HttpSession getSession()
/*    */   {
/* 26 */     HttpSession session = getCurRequest().getSession();
/* 27 */     return session;
/*    */   }
/*    */ 
/*    */   public static HttpServletRequest getCurRequest()
/*    */   {
/* 36 */     RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
/* 37 */     if ((requestAttributes != null) && ((requestAttributes instanceof ServletRequestAttributes))) {
/* 38 */       ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)requestAttributes;
/* 39 */       return servletRequestAttributes.getRequest();
/*    */     }
/* 41 */     return null;
/*    */   }
/*    */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.sys.util.SysUserUtils
 * JD-Core Version:    0.6.0
 */