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
/*    */ import tk.mybatis.springboot.cms.model.CmsArticle;
/*    */ import tk.mybatis.springboot.cms.service.CmsArticleService;
/*    */ import tk.mybatis.springboot.util.Menu;
/*    */ import tk.mybatis.springboot.util.StringUtil;
/*    */ 
/*    */ @Controller
/*    */ @RequestMapping({"/cms/article"})
/*    */ public class CmsArticleController
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private CmsArticleService CmsArticleService;
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
/*    */   @Menu(type="cms", subtype="article")
/*    */   public ModelAndView index(ModelMap map, HttpServletRequest request) { ModelAndView mv = new ModelAndView();
/* 49 */     mv.setViewName("/cms/article/article");
/* 50 */     PageInfo articleList = this.CmsArticleService.selectPage(getP(request), 10, new CmsArticle());
/* 51 */     mv.addObject("articleList", articleList);
/* 52 */     return mv; }
/*    */ 
/*    */   @RequestMapping({"/add"})
/*    */   @Menu(type="cms", subtype="article")
/*    */   public ModelAndView add(ModelMap map, HttpServletRequest request) {
/* 59 */     ModelAndView mv = new ModelAndView();
/* 60 */     mv.setViewName("/cms/article/add");
/* 61 */     return mv;
/*    */   }
/* 67 */   @RequestMapping({"/save"})
/*    */   @Menu(type="cms", subtype="article")
/*    */   public String save(HttpServletRequest request, @Valid CmsArticle article) { this.CmsArticleService.insertSelective(article);
/* 68 */     String msg = "cms_article_delete";
/* 69 */     return "redirect:/cms/article/index.html?msg=" + msg; } 
/*    */   @RequestMapping({"/edit"})
/*    */   @Menu(type="cms", subtype="article")
/*    */   public ModelAndView edit(ModelMap map, @Valid String id) {
/* 75 */     ModelAndView mv = new ModelAndView();
/* 76 */     mv.addObject("articleData", this.CmsArticleService.selectByPrimaryKey(Long.valueOf(Long.parseLong(id))));
/* 77 */     mv.setViewName("/cms/article/edit");
/* 78 */     return mv;
/*    */   }
/* 84 */   @RequestMapping({"/update"})
/*    */   @Menu(type="cms", subtype="article", admin=true)
/*    */   public String update(HttpServletRequest request, @Valid CmsArticle article) { this.CmsArticleService.updateByPrimaryKeySelective(article);
/* 85 */     String msg = "cms_article_update";
/* 86 */     return "redirect:/cms/article/index.html?msg=" + msg; }
/*    */ 
/*    */   @RequestMapping({"/delete"})
/*    */   @Menu(type="cms", subtype="article")
/*    */   public String delete(HttpServletRequest request, @Valid CmsArticle article) {
/* 93 */     this.CmsArticleService.delete(article);
/* 94 */     String msg = "cms_article_delete";
/* 95 */     return "redirect:/cms/article/index.html?msg=" + msg;
/*    */   }
/*    */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.cms.controller.CmsArticleController
 * JD-Core Version:    0.6.0
 */