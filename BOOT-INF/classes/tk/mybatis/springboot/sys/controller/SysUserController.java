/*     */ package tk.mybatis.springboot.sys.controller;
/*     */ 
/*     */ import com.github.pagehelper.PageInfo;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.validation.Valid;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ import tk.mybatis.springboot.sys.model.SysUser;
/*     */ import tk.mybatis.springboot.sys.service.SysUserService;
/*     */ import tk.mybatis.springboot.util.Menu;
/*     */ import tk.mybatis.springboot.util.StringUtil;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/admin/user"})
/*     */ public class SysUserController
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private SysUserService SysUserService;
/*     */ 
/*     */   public int getP(HttpServletRequest request)
/*     */   {
/*  36 */     int page = 0;
/*  37 */     String p = request.getParameter("p");
/*  38 */     if ((!StringUtil.isBlank(p)) && (p.matches("[\\d]*"))) {
/*  39 */       page = Integer.parseInt(p);
/*  40 */       if (page > 0) {
/*  41 */         page = page;
/*     */       }
/*     */     }
/*  44 */     return page;
/*     */   }
/*  50 */   @RequestMapping({"/index"})
/*     */   @Menu(type="admin", subtype="user")
/*     */   public ModelAndView index(ModelMap map, HttpServletRequest request) { ModelAndView mv = new ModelAndView();
/*  51 */     mv.setViewName("/admin/user/user");
/*  52 */     PageInfo userList = this.SysUserService.selectPage(getP(request), 10, new SysUser());
/*  53 */     mv.addObject("userList", userList);
/*  54 */     return mv; }
/*     */ 
/*     */   @RequestMapping({"/add"})
/*     */   @Menu(type="admin", subtype="user")
/*     */   public ModelAndView add(ModelMap map, HttpServletRequest request) {
/*  61 */     ModelAndView mv = new ModelAndView();
/*  62 */     mv.setViewName("/admin/user/add");
/*  63 */     return mv;
/*     */   }
/*  69 */   @RequestMapping({"/save"})
/*     */   @Menu(type="admin", subtype="user")
/*     */   public String save(HttpServletRequest request, @Valid SysUser user) { this.SysUserService.insertSelective(user);
/*  70 */     String msg = "admin_user_delete";
/*  71 */     return "redirect:/admin/user/index.html?msg=" + msg; } 
/*     */   @RequestMapping({"/edit"})
/*     */   @Menu(type="admin", subtype="user")
/*     */   public ModelAndView edit(ModelMap map, @Valid String id) {
/*  77 */     ModelAndView mv = new ModelAndView();
/*  78 */     mv.addObject("userData", this.SysUserService.selectByPrimaryKey(Long.valueOf(Long.parseLong(id))));
/*  79 */     mv.setViewName("/admin/user/edit");
/*  80 */     return mv;
/*     */   }
/*  86 */   @RequestMapping({"/update"})
/*     */   @Menu(type="admin", subtype="user", admin=true)
/*     */   public String update(HttpServletRequest request, @Valid SysUser user) { this.SysUserService.updateByPrimaryKeySelective(user);
/*  87 */     String msg = "admin_user_delete";
/*  88 */     return "redirect:/admin/user/index.html?msg=" + msg;
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/delete"})
/*     */   @Menu(type="admin", subtype="user")
/*     */   public String delete(HttpServletRequest request, @Valid SysUser user)
/*     */   {
/* 115 */     this.SysUserService.delete(user);
/* 116 */     String msg = "admin_user_delete";
/*     */ 
/* 128 */     return "redirect:/admin/user/index.html?msg=" + msg;
/*     */   }
/*     */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.sys.controller.SysUserController
 * JD-Core Version:    0.6.0
 */