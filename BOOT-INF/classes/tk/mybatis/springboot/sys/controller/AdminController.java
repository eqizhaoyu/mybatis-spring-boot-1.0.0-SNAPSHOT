/*    */ package tk.mybatis.springboot.sys.controller;
/*    */ 
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import tk.mybatis.springboot.sys.service.SysUserService;
/*    */ import tk.mybatis.springboot.util.Menu;
/*    */ 
/*    */ @Controller
/*    */ public class AdminController
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private SysUserService SysUserService;
/*    */ 
/*    */   @RequestMapping({"/admin"})
/*    */   public String index(ModelMap map, HttpServletRequest request)
/*    */   {
/* 24 */     return "admin/index";
/*    */   }
/*    */   @RequestMapping({"/admin/content"})
/*    */   @Menu(type="admin", subtype="content")
/*    */   public String content(ModelMap map, HttpServletRequest request) {
/* 31 */     return "admin/content";
/*    */   }
/*    */   @RequestMapping({"/cms"})
/*    */   public String cmsIndex(ModelMap map, HttpServletRequest request) {
/* 36 */     return "cms/index";
/*    */   }
/*    */   @RequestMapping({"/cms/content"})
/*    */   @Menu(type="cms", subtype="content")
/*    */   public String cmsContent(ModelMap map, HttpServletRequest request) {
/* 43 */     return "cms/content";
/*    */   }
/*    */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.sys.controller.AdminController
 * JD-Core Version:    0.6.0
 */