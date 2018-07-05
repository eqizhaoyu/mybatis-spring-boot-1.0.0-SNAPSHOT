/*     */ package tk.mybatis.springboot.cms.model;
/*     */ 
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Table;
/*     */ import tk.mybatis.springboot.base.BaseEntity;
/*     */ 
/*     */ @Table(name="cms_link")
/*     */ public class CmsLink extends BaseEntity
/*     */ {
/*     */   private String color;
/*     */   private String href;
/*     */   private String image;
/*     */   private String title;
/*     */   private Integer weight;
/*     */ 
/*     */   @Column(name="weightDate")
/*     */   private Date weightdate;
/*     */ 
/*     */   @Column(name="CATEGORY_ID")
/*     */   private Long categoryId;
/*     */ 
/*     */   public String getColor()
/*     */   {
/*  52 */     return this.color;
/*     */   }
/*     */ 
/*     */   public void setColor(String color)
/*     */   {
/*  61 */     this.color = color;
/*     */   }
/*     */ 
/*     */   public String getHref()
/*     */   {
/*  70 */     return this.href;
/*     */   }
/*     */ 
/*     */   public void setHref(String href)
/*     */   {
/*  79 */     this.href = href;
/*     */   }
/*     */ 
/*     */   public String getImage()
/*     */   {
/*  88 */     return this.image;
/*     */   }
/*     */ 
/*     */   public void setImage(String image)
/*     */   {
/*  97 */     this.image = image;
/*     */   }
/*     */ 
/*     */   public String getTitle()
/*     */   {
/* 106 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title)
/*     */   {
/* 115 */     this.title = title;
/*     */   }
/*     */ 
/*     */   public Integer getWeight()
/*     */   {
/* 124 */     return this.weight;
/*     */   }
/*     */ 
/*     */   public void setWeight(Integer weight)
/*     */   {
/* 133 */     this.weight = weight;
/*     */   }
/*     */ 
/*     */   public Date getWeightdate()
/*     */   {
/* 140 */     return this.weightdate;
/*     */   }
/*     */ 
/*     */   public void setWeightdate(Date weightdate)
/*     */   {
/* 147 */     this.weightdate = weightdate;
/*     */   }
/*     */ 
/*     */   public Long getCategoryId()
/*     */   {
/* 154 */     return this.categoryId;
/*     */   }
/*     */ 
/*     */   public void setCategoryId(Long categoryId)
/*     */   {
/* 161 */     this.categoryId = categoryId;
/*     */   }
/*     */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.cms.model.CmsLink
 * JD-Core Version:    0.6.0
 */