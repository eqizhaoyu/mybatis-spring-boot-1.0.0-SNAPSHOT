/*     */ package tk.mybatis.springboot.sys.controller;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Resource;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpSession;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.Model;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import tk.mybatis.springboot.sys.model.SysUser;
/*     */ import tk.mybatis.springboot.sys.service.SysResService;
/*     */ import tk.mybatis.springboot.sys.service.SysUserService;
/*     */ import tk.mybatis.springboot.sys.util.SysUserUtils;
/*     */ import tk.mybatis.springboot.util.StringUtil;
/*     */ 
/*     */ @Controller
/*     */ public class LoginController
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private SysResService sysResourceService;
/*     */ 
/*     */   @Resource
/*     */   private SysUserService sysUserService;
/*     */ 
/*     */   @RequestMapping({"/index"})
/*     */   public String toIndex(Model model, HttpServletRequest request)
/*     */   {
/*  46 */     request.getSession().removeAttribute("code");
/*  47 */     if (SysUserUtils.getSessionLoginUser() == null) {
/*  48 */       return "login";
/*     */     }
/*     */ 
/*  66 */     return "index";
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"login"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
/*     */   public String toLogin()
/*     */   {
/*  76 */     if (SysUserUtils.getSessionLoginUser() != null) {
/*  77 */       return "redirect:/index";
/*     */     }
/*  79 */     return "login";
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"login"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public Map<String, Object> checkLogin(String username, String password, String code, HttpServletRequest request)
/*     */   {
/*  94 */     Map msg = new HashMap();
/*  95 */     HttpSession session = request.getSession();
/*  96 */     code = StringUtil.trim(code);
/*  97 */     username = StringUtil.trim(username);
/*  98 */     password = StringUtil.trim(password);
/*  99 */     Object scode = session.getAttribute("code");
/* 100 */     String sessionCode = null;
/* 101 */     if (scode != null)
/* 102 */       sessionCode = scode.toString();
/* 103 */     if (!StringUtil.equalsIgnoreCase(code, sessionCode)) {
/* 104 */       msg.put("error", "验证码错误");
/* 105 */       return msg;
/*     */     }
/* 107 */     SysUser user = this.sysUserService.checkUser(username, password);
/* 108 */     if (null != user)
/*     */     {
/* 110 */       session.setAttribute("LOGIN_USER", user);
/*     */     }
/*     */     else
/*     */     {
/* 120 */       msg.put("error", "用户名或密码错误");
/*     */     }
/* 122 */     return msg;
/*     */   }
/*     */ 
/*     */   @RequestMapping({"logout"})
/*     */   public String logout(HttpServletRequest request)
/*     */   {
/* 133 */     request.getSession().invalidate();
/* 134 */     return "redirect:/login";
/*     */   }
/*     */   @RequestMapping({"notauth"})
/*     */   public String notAuth() {
/* 139 */     return "notauth";
/*     */   }
/*     */   @RequestMapping({"notlogin"})
/*     */   public String notLogin() {
/* 144 */     return "notlogin";
/*     */   }
/*     */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.sys.controller.LoginController
 * JD-Core Version:    0.6.0
 */