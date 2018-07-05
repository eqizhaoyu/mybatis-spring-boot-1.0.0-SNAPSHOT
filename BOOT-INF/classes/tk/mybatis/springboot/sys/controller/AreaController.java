/*     */ package tk.mybatis.springboot.sys.controller;
/*     */ 
/*     */ import com.github.pagehelper.PageHelper;
/*     */ import com.github.pagehelper.PageInfo;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Resource;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.Model;
/*     */ import org.springframework.web.bind.annotation.ModelAttribute;
/*     */ import org.springframework.web.bind.annotation.PathVariable;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.bind.annotation.RequestParam;
/*     */ import org.springframework.web.bind.annotation.ResponseBody;
/*     */ import tk.mybatis.springboot.sys.model.SysArea;
/*     */ import tk.mybatis.springboot.sys.service.SysAreaService;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"area"})
/*     */ public class AreaController
/*     */ {
/*     */ 
/*     */   @Resource
/*     */   private SysAreaService sysAreaService;
/*     */ 
/*     */   @RequestMapping
/*     */   public String toArea(Model model)
/*     */   {
/*  40 */     return "sys/area/area";
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"tree"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public List<SysArea> getAreaTreeList()
/*     */   {
/*  49 */     List list = this.sysAreaService.select(new SysArea());
/*  50 */     return list;
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"save"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public Integer save(@ModelAttribute SysArea sysArea)
/*     */   {
/*  60 */     return Integer.valueOf(this.sysAreaService.insertOrUpdate(sysArea));
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"delete"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   @ResponseBody
/*     */   public Integer dels(Long id)
/*     */   {
/*  72 */     Integer count = Integer.valueOf(0);
/*  73 */     if (null != id) {
/*  74 */       count = Integer.valueOf(this.sysAreaService.deleteByPrimaryKey(id));
/*     */     }
/*  76 */     return count;
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"list"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String list(@RequestParam Map<String, Object> params, Model model)
/*     */   {
/*  87 */     PageHelper.startPage(params);
/*  88 */     List list = this.sysAreaService.select(new SysArea());
/*  89 */     model.addAttribute("page", new PageInfo(list));
/*  90 */     return "sys/area/area-list";
/*     */   }
/*     */ 
/*     */   @RequestMapping(value={"{mode}/showlayer"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
/*     */   public String showLayer(Long id, Long parentId, @PathVariable("mode") String mode, Model model)
/*     */   {
/* 107 */     SysArea area = null; SysArea pArea = null;
/*     */ 
/* 109 */     model.addAttribute("pArea", pArea).addAttribute("area", area);
/* 110 */     return mode.equals("detail") ? "sys/area/area-detail" : "sys/area/area-save";
/*     */   }
/*     */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.sys.controller.AreaController
 * JD-Core Version:    0.6.0
 */