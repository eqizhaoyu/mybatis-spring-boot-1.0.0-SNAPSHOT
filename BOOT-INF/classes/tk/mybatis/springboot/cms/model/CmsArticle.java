/*     */ package tk.mybatis.springboot.cms.model;
/*     */ 
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Table;
/*     */ import tk.mybatis.springboot.base.BaseEntity;
/*     */ 
/*     */ @Table(name="cms_article")
/*     */ public class CmsArticle extends BaseEntity
/*     */ {
/*     */   private String color;
/*     */ 
/*     */   @Column(name="createDate")
/*     */   private Date createdate;
/*     */   private String description;
/*     */   private Integer hits;
/*     */   private String htmlid;
/*     */   private String image;
/*     */ 
/*     */   @Column(name="isWord")
/*     */   private Integer isword;
/*     */   private String keywords;
/*     */   private String title;
/*     */   private Integer weight;
/*     */ 
/*     */   @Column(name="CATEGORY_ID")
/*     */   private Long categoryId;
/*     */ 
/*     */   @Column(name="delFlag")
/*     */   private Integer delflag;
/*     */ 
/*     */   @Column(name="updateDate")
/*     */   private Date updatedate;
/*     */ 
/*     */   @Column(name="createby_id")
/*     */   private Long createbyId;
/*     */ 
/*     */   @Column(name="updateby_id")
/*     */   private Long updatebyId;
/*     */   private String moreimage;
/*     */ 
/*     */   @Column(name="CATEGORYNAME")
/*     */   private String categoryname;
/*     */   private Long siteid;
/*     */   private String content;
/*     */ 
/*     */   public String getColor()
/*     */   {
/*  94 */     return this.color;
/*     */   }
/*     */ 
/*     */   public void setColor(String color)
/*     */   {
/* 103 */     this.color = color;
/*     */   }
/*     */ 
/*     */   public Date getCreatedate()
/*     */   {
/* 110 */     return this.createdate;
/*     */   }
/*     */ 
/*     */   public void setCreatedate(Date createdate)
/*     */   {
/* 117 */     this.createdate = createdate;
/*     */   }
/*     */ 
/*     */   public String getDescription()
/*     */   {
/* 126 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description)
/*     */   {
/* 135 */     this.description = description;
/*     */   }
/*     */ 
/*     */   public Integer getHits()
/*     */   {
/* 144 */     return this.hits;
/*     */   }
/*     */ 
/*     */   public void setHits(Integer hits)
/*     */   {
/* 153 */     this.hits = hits;
/*     */   }
/*     */ 
/*     */   public String getHtmlid()
/*     */   {
/* 162 */     return this.htmlid;
/*     */   }
/*     */ 
/*     */   public void setHtmlid(String htmlid)
/*     */   {
/* 171 */     this.htmlid = htmlid;
/*     */   }
/*     */ 
/*     */   public String getImage()
/*     */   {
/* 180 */     return this.image;
/*     */   }
/*     */ 
/*     */   public void setImage(String image)
/*     */   {
/* 189 */     this.image = image;
/*     */   }
/*     */ 
/*     */   public Integer getIsword()
/*     */   {
/* 198 */     return this.isword;
/*     */   }
/*     */ 
/*     */   public void setIsword(Integer isword)
/*     */   {
/* 207 */     this.isword = isword;
/*     */   }
/*     */ 
/*     */   public String getKeywords()
/*     */   {
/* 214 */     return this.keywords;
/*     */   }
/*     */ 
/*     */   public void setKeywords(String keywords)
/*     */   {
/* 221 */     this.keywords = keywords;
/*     */   }
/*     */ 
/*     */   public String getTitle()
/*     */   {
/* 230 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title)
/*     */   {
/* 239 */     this.title = title;
/*     */   }
/*     */ 
/*     */   public Integer getWeight()
/*     */   {
/* 248 */     return this.weight;
/*     */   }
/*     */ 
/*     */   public void setWeight(Integer weight)
/*     */   {
/* 257 */     this.weight = weight;
/*     */   }
/*     */ 
/*     */   public Long getCategoryId()
/*     */   {
/* 264 */     return this.categoryId;
/*     */   }
/*     */ 
/*     */   public void setCategoryId(Long categoryId)
/*     */   {
/* 271 */     this.categoryId = categoryId;
/*     */   }
/*     */ 
/*     */   public Integer getDelflag()
/*     */   {
/* 278 */     return this.delflag;
/*     */   }
/*     */ 
/*     */   public void setDelflag(Integer delflag)
/*     */   {
/* 285 */     this.delflag = delflag;
/*     */   }
/*     */ 
/*     */   public Date getUpdatedate()
/*     */   {
/* 292 */     return this.updatedate;
/*     */   }
/*     */ 
/*     */   public void setUpdatedate(Date updatedate)
/*     */   {
/* 299 */     this.updatedate = updatedate;
/*     */   }
/*     */ 
/*     */   public Long getCreatebyId()
/*     */   {
/* 306 */     return this.createbyId;
/*     */   }
/*     */ 
/*     */   public void setCreatebyId(Long createbyId)
/*     */   {
/* 313 */     this.createbyId = createbyId;
/*     */   }
/*     */ 
/*     */   public Long getUpdatebyId()
/*     */   {
/* 320 */     return this.updatebyId;
/*     */   }
/*     */ 
/*     */   public void setUpdatebyId(Long updatebyId)
/*     */   {
/* 327 */     this.updatebyId = updatebyId;
/*     */   }
/*     */ 
/*     */   public String getMoreimage()
/*     */   {
/* 334 */     return this.moreimage;
/*     */   }
/*     */ 
/*     */   public void setMoreimage(String moreimage)
/*     */   {
/* 341 */     this.moreimage = moreimage;
/*     */   }
/*     */ 
/*     */   public String getCategoryname()
/*     */   {
/* 348 */     return this.categoryname;
/*     */   }
/*     */ 
/*     */   public void setCategoryname(String categoryname)
/*     */   {
/* 355 */     this.categoryname = categoryname;
/*     */   }
/*     */ 
/*     */   public Long getSiteid()
/*     */   {
/* 362 */     return this.siteid;
/*     */   }
/*     */ 
/*     */   public void setSiteid(Long siteid)
/*     */   {
/* 369 */     this.siteid = siteid;
/*     */   }
/*     */ 
/*     */   public String getContent()
/*     */   {
/* 378 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content)
/*     */   {
/* 387 */     this.content = content;
/*     */   }
/*     */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.cms.model.CmsArticle
 * JD-Core Version:    0.6.0
 */