/*    */ package tk.mybatis.springboot.cms.controller;
/*    */ 
/*    */ import com.github.pagehelper.PageInfo;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.validation.Valid;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.servlet.ModelAndView;
/*    */ import tk.mybatis.springboot.cms.model.CmsSite;
/*    */ import tk.mybatis.springboot.cms.service.CmsSiteService;
/*    */ import tk.mybatis.springboot.util.Menu;
/*    */ import tk.mybatis.springboot.util.StringUtil;
/*    */ 
/*    */ @Controller
/*    */ @RequestMapping({"/cms/site"})
/*    */ public class CmsSiteController
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private CmsSiteService CmssiteService;
/*    */ 
/*    */   public int getP(HttpServletRequest request)
/*    */   {
/* 34 */     int page = 0;
/* 35 */     String p = request.getParameter("p");
/* 36 */     if ((!StringUtil.isBlank(p)) && (p.matches("[\\d]*"))) {
/* 37 */       page = Integer.parseInt(p);
/* 38 */       if (page > 0) {
/* 39 */         page = page;
/*    */       }
/*    */     }
/* 42 */     return page;
/*    */   }
/* 48 */   @RequestMapping({"/index"})
/*    */   @Menu(type="cms", subtype="site")
/*    */   public ModelAndView index(ModelMap map, HttpServletRequest request) { ModelAndView mv = new ModelAndView();
/* 49 */     mv.setViewName("/cms/site/site");
/* 50 */     PageInfo siteList = this.CmssiteService.selectPage(getP(request), 10, new CmsSite());
/* 51 */     mv.addObject("siteList", siteList);
/* 52 */     return mv; }
/*    */ 
/*    */   @RequestMapping({"/add"})
/*    */   @Menu(type="cms", subtype="site")
/*    */   public ModelAndView add(ModelMap map, HttpServletRequest request) {
/* 59 */     ModelAndView mv = new ModelAndView();
/* 60 */     mv.setViewName("/cms/site/add");
/* 61 */     return mv;
/*    */   }
/* 67 */   @RequestMapping({"/save"})
/*    */   @Menu(type="cms", subtype="site")
/*    */   public String save(HttpServletRequest request, @Valid CmsSite site) { this.CmssiteService.insertSelective(site);
/* 68 */     String msg = "cms_site_delete";
/* 69 */     return "redirect:/cms/site/index.html?msg=" + msg; } 
/*    */   @RequestMapping({"/edit"})
/*    */   @Menu(type="cms", subtype="site")
/*    */   public ModelAndView edit(ModelMap map, @Valid String id) {
/* 75 */     ModelAndView mv = new ModelAndView();
/* 76 */     mv.addObject("siteData", this.CmssiteService.selectByPrimaryKey(Long.valueOf(Long.parseLong(id))));
/* 77 */     mv.setViewName("/cms/site/edit");
/* 78 */     return mv;
/*    */   }
/* 84 */   @RequestMapping({"/update"})
/*    */   @Menu(type="cms", subtype="site", admin=true)
/*    */   public String update(HttpServletRequest request, @Valid CmsSite site) { this.CmssiteService.updateByPrimaryKeySelective(site);
/* 85 */     String msg = "cms_site_update";
/* 86 */     return "redirect:/cms/site/index.html?msg=" + msg; }
/*    */ 
/*    */   @RequestMapping({"/delete"})
/*    */   @Menu(type="cms", subtype="site")
/*    */   public String delete(HttpServletRequest request, @Valid CmsSite site) {
/* 93 */     this.CmssiteService.delete(site);
/* 94 */     String msg = "cms_site_delete";
/* 95 */     return "redirect:/cms/site/index.html?msg=" + msg;
/*    */   }
/*    */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.cms.controller.CmsSiteController
 * JD-Core Version:    0.6.0
 */