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
/*    */ import tk.mybatis.springboot.cms.model.CmsComment;
/*    */ import tk.mybatis.springboot.cms.service.CmsCommentService;
/*    */ import tk.mybatis.springboot.util.Menu;
/*    */ import tk.mybatis.springboot.util.StringUtil;
/*    */ 
/*    */ @Controller
/*    */ @RequestMapping({"/cms/comment"})
/*    */ public class CmsCommentController
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private CmsCommentService CmscommentService;
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
/*    */   @Menu(type="cms", subtype="comment")
/*    */   public ModelAndView index(ModelMap map, HttpServletRequest request) { ModelAndView mv = new ModelAndView();
/* 49 */     mv.setViewName("/cms/comment/comment");
/* 50 */     PageInfo commentList = this.CmscommentService.selectPage(getP(request), 10, new CmsComment());
/* 51 */     mv.addObject("commentList", commentList);
/* 52 */     return mv; }
/*    */ 
/*    */   @RequestMapping({"/delete"})
/*    */   @Menu(type="cms", subtype="comment")
/*    */   public String delete(HttpServletRequest request, @Valid CmsComment comment)
/*    */   {
/* 60 */     this.CmscommentService.delete(comment);
/* 61 */     String msg = "cms_comment_delete";
/* 62 */     return "redirect:/cms/comment/index.html?msg=" + msg;
/*    */   }
/*    */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.cms.controller.CmsCommentController
 * JD-Core Version:    0.6.0
 */