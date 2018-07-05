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
/*    */ import tk.mybatis.springboot.cms.model.CmsLink;
/*    */ import tk.mybatis.springboot.cms.service.CmsLinkService;
/*    */ import tk.mybatis.springboot.util.Menu;
/*    */ import tk.mybatis.springboot.util.StringUtil;
/*    */ 
/*    */ @Controller
/*    */ @RequestMapping({"/cms/link"})
/*    */ public class CmsLinkController
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private CmsLinkService CmslinkService;
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
/*    */   @Menu(type="cms", subtype="link")
/*    */   public ModelAndView index(ModelMap map, HttpServletRequest request) { ModelAndView mv = new ModelAndView();
/* 49 */     mv.setViewName("/cms/link/link");
/* 50 */     PageInfo linkList = this.CmslinkService.selectPage(getP(request), 10, new CmsLink());
/* 51 */     mv.addObject("linkList", linkList);
/* 52 */     return mv; }
/*    */ 
/*    */   @RequestMapping({"/add"})
/*    */   @Menu(type="cms", subtype="link")
/*    */   public ModelAndView add(ModelMap map, HttpServletRequest request) {
/* 59 */     ModelAndView mv = new ModelAndView();
/* 60 */     mv.setViewName("/cms/link/add");
/* 61 */     return mv;
/*    */   }
/* 67 */   @RequestMapping({"/save"})
/*    */   @Menu(type="cms", subtype="link")
/*    */   public String save(HttpServletRequest request, @Valid CmsLink link) { this.CmslinkService.insertSelective(link);
/* 68 */     String msg = "cms_link_delete";
/* 69 */     return "redirect:/cms/link/index.html?msg=" + msg; } 
/*    */   @RequestMapping({"/edit"})
/*    */   @Menu(type="cms", subtype="link")
/*    */   public ModelAndView edit(ModelMap map, @Valid String id) {
/* 75 */     ModelAndView mv = new ModelAndView();
/* 76 */     mv.addObject("linkData", this.CmslinkService.selectByPrimaryKey(Long.valueOf(Long.parseLong(id))));
/* 77 */     mv.setViewName("/cms/link/edit");
/* 78 */     return mv;
/*    */   }
/* 84 */   @RequestMapping({"/update"})
/*    */   @Menu(type="cms", subtype="link", admin=true)
/*    */   public String update(HttpServletRequest request, @Valid CmsLink link) { this.CmslinkService.updateByPrimaryKeySelective(link);
/* 85 */     String msg = "cms_link_update";
/* 86 */     return "redirect:/cms/link/index.html?msg=" + msg; }
/*    */ 
/*    */   @RequestMapping({"/delete"})
/*    */   @Menu(type="cms", subtype="link")
/*    */   public String delete(HttpServletRequest request, @Valid CmsLink link) {
/* 93 */     this.CmslinkService.delete(link);
/* 94 */     String msg = "cms_link_delete";
/* 95 */     return "redirect:/cms/link/index.html?msg=" + msg;
/*    */   }
/*    */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.cms.controller.CmsLinkController
 * JD-Core Version:    0.6.0
 */