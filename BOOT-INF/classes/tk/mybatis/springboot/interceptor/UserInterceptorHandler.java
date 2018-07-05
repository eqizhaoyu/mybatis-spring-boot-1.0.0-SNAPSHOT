/*    */ package tk.mybatis.springboot.interceptor;
/*    */ 
/*    */ import java.lang.reflect.Method;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import javax.servlet.http.HttpSession;
/*    */ import org.springframework.boot.autoconfigure.web.BasicErrorController;
/*    */ import org.springframework.web.method.HandlerMethod;
/*    */ import org.springframework.web.servlet.ModelAndView;
/*    */ import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/*    */ import tk.mybatis.springboot.sys.model.SysUser;
/*    */ import tk.mybatis.springboot.util.Menu;
/*    */ 
/*    */ public class UserInterceptorHandler extends HandlerInterceptorAdapter
/*    */ {
/*    */   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
/*    */     throws Exception
/*    */   {
/* 21 */     boolean filter = false;
/* 22 */     SysUser user = (SysUser)request.getSession(true).getAttribute("LOGIN_USER");
/* 23 */     HandlerMethod handlerMethod = (HandlerMethod)handler;
/* 24 */     Menu menu = (Menu)handlerMethod.getMethod().getAnnotation(Menu.class);
/* 25 */     if ((user != null) || ((menu != null) && (menu.access())) || ((handlerMethod.getBean() instanceof BasicErrorController))) {
/* 26 */       filter = true;
/*    */     }
/*    */ 
/* 29 */     if (!filter) {
/* 30 */       response.sendRedirect("/login.html");
/*    */     }
/* 32 */     return filter;
/*    */   }
/*    */ 
/*    */   public void postHandle(HttpServletRequest arg0, HttpServletResponse response, Object arg2, ModelAndView view) throws Exception
/*    */   {
/* 37 */     SysUser user = (SysUser)arg0.getSession().getAttribute("LOGIN_USER");
/* 38 */     if (view != null) {
/* 39 */       if (user != null) {
/* 40 */         view.addObject("user", user);
/* 41 */         view.addObject("schema", arg0.getScheme());
/* 42 */         view.addObject("hostname", arg0.getServerName());
/* 43 */         view.addObject("port", Integer.valueOf(arg0.getServerPort()));
/*    */ 
/* 45 */         HandlerMethod handlerMethod = (HandlerMethod)arg2;
/* 46 */         Menu menu = (Menu)handlerMethod.getMethod().getAnnotation(Menu.class);
/* 47 */         if (menu != null) {
/* 48 */           view.addObject("subtype", menu.subtype());
/* 49 */           view.addObject("maintype", menu.type());
/* 50 */           view.addObject("typename", menu.name());
/*    */         }
/*    */ 
/*    */       }
/*    */ 
/* 61 */       SysUser imUser = (SysUser)arg0.getSession().getAttribute("LOGIN_USER");
/* 62 */       if ((imUser == null) && (view != null)) {
/* 63 */         imUser = new SysUser();
/* 64 */         imUser.setUsername("guest");
/* 65 */         imUser.setId(Long.valueOf(1L));
/*    */ 
/* 67 */         view.addObject("imuser", imUser);
/*    */       }
/*    */ 
/* 70 */       if (arg0.getParameter("msg") != null)
/* 71 */         view.addObject("msg", arg0.getParameter("msg"));
/*    */     }
/*    */   }
/*    */ 
/*    */   public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
/*    */     throws Exception
/*    */   {
/*    */   }
/*    */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.interceptor.UserInterceptorHandler
 * JD-Core Version:    0.6.0
 */