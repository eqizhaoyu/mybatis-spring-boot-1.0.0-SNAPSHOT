/*     */ package tk.mybatis.springboot.cms.model;
/*     */ 
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Table;
/*     */ import tk.mybatis.springboot.base.BaseEntity;
/*     */ 
/*     */ @Table(name="cms_category")
/*     */ public class CmsCategory extends BaseEntity
/*     */ {
/*     */   private String description;
/*     */   private String image;
/*     */   private String keywords;
/*     */   private String module;
/*     */   private String name;
/*     */ 
/*     */   @Column(name="parent_id")
/*     */   private Long parentId;
/*     */   private Integer grade;
/*     */ 
/*     */   @Column(name="isShow")
/*     */   private Integer isshow;
/*     */ 
/*     */   @Column(name="orderNo")
/*     */   private Integer orderno;
/*     */ 
/*     */   @Column(name="site_id")
/*     */   private Long siteId;
/*     */   private String url;
/*     */ 
/*     */   @Column(name="parent_ids")
/*     */   private String parentIds;
/*     */ 
/*     */   public String getDescription()
/*     */   {
/*  74 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description)
/*     */   {
/*  83 */     this.description = description;
/*     */   }
/*     */ 
/*     */   public String getImage()
/*     */   {
/*  92 */     return this.image;
/*     */   }
/*     */ 
/*     */   public void setImage(String image)
/*     */   {
/* 101 */     this.image = image;
/*     */   }
/*     */ 
/*     */   public String getKeywords()
/*     */   {
/* 110 */     return this.keywords;
/*     */   }
/*     */ 
/*     */   public void setKeywords(String keywords)
/*     */   {
/* 119 */     this.keywords = keywords;
/*     */   }
/*     */ 
/*     */   public String getModule()
/*     */   {
/* 128 */     return this.module;
/*     */   }
/*     */ 
/*     */   public void setModule(String module)
/*     */   {
/* 137 */     this.module = module;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 146 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 155 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public Long getParentId()
/*     */   {
/* 162 */     return this.parentId;
/*     */   }
/*     */ 
/*     */   public void setParentId(Long parentId)
/*     */   {
/* 169 */     this.parentId = parentId;
/*     */   }
/*     */ 
/*     */   public Integer getGrade()
/*     */   {
/* 178 */     return this.grade;
/*     */   }
/*     */ 
/*     */   public void setGrade(Integer grade)
/*     */   {
/* 187 */     this.grade = grade;
/*     */   }
/*     */ 
/*     */   public Integer getIsshow()
/*     */   {
/* 196 */     return this.isshow;
/*     */   }
/*     */ 
/*     */   public void setIsshow(Integer isshow)
/*     */   {
/* 205 */     this.isshow = isshow;
/*     */   }
/*     */ 
/*     */   public Integer getOrderno()
/*     */   {
/* 212 */     return this.orderno;
/*     */   }
/*     */ 
/*     */   public void setOrderno(Integer orderno)
/*     */   {
/* 219 */     this.orderno = orderno;
/*     */   }
/*     */ 
/*     */   public Long getSiteId()
/*     */   {
/* 228 */     return this.siteId;
/*     */   }
/*     */ 
/*     */   public void setSiteId(Long siteId)
/*     */   {
/* 237 */     this.siteId = siteId;
/*     */   }
/*     */ 
/*     */   public String getUrl()
/*     */   {
/* 246 */     return this.url;
/*     */   }
/*     */ 
/*     */   public void setUrl(String url)
/*     */   {
/* 255 */     this.url = url;
/*     */   }
/*     */ 
/*     */   public String getParentIds()
/*     */   {
/* 262 */     return this.parentIds;
/*     */   }
/*     */ 
/*     */   public void setParentIds(String parentIds)
/*     */   {
/* 269 */     this.parentIds = parentIds;
/*     */   }
/*     */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.cms.model.CmsCategory
 * JD-Core Version:    0.6.0
 */