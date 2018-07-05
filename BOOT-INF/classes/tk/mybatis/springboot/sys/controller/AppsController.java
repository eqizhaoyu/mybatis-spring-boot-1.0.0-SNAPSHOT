/*    */ package tk.mybatis.springboot.sys.controller;
/*    */ 
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.servlet.ModelAndView;
/*    */ import tk.mybatis.springboot.sys.model.SysUser;
/*    */ import tk.mybatis.springboot.sys.service.SysUserService;
/*    */ import tk.mybatis.springboot.util.Menu;
/*    */ 
/*    */ @Controller
/*    */ public class AppsController
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private SysUserService SysUserService;
/*    */ 
/*    */   @RequestMapping({"/apps/content"})
/*    */   @Menu(type="apps", subtype="content")
/*    */   public ModelAndView content(HttpServletRequest request)
/*    */   {
/* 27 */     ModelAndView mv = new ModelAndView();
/* 28 */     mv.setViewName("apps/index");
/* 29 */     List userList = this.SysUserService.select(new SysUser());
/* 30 */     mv.addObject("page", userList);
/* 31 */     return mv;
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/apps/onlineuser"})
/*    */   @Menu(type="apps", subtype="onlineuser")
/*    */   public ModelAndView onlineuser(ModelMap map, HttpServletRequest request) {
/* 39 */     ModelAndView mv = new ModelAndView();
/* 40 */     mv.setViewName("apps/index");
/* 41 */     List userList = this.SysUserService.select(new SysUser());
/* 42 */     mv.addObject("page", userList);
/* 43 */     return mv;
/*    */   }
/*    */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.sys.controller.AppsController
 * JD-Core Version:    0.6.0
 */