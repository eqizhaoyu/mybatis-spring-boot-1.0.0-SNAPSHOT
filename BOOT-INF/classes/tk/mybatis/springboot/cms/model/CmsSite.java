/*     */ package tk.mybatis.springboot.cms.model;
/*     */ 
/*     */ import javax.persistence.Table;
/*     */ import tk.mybatis.springboot.base.BaseEntity;
/*     */ 
/*     */ @Table(name="cms_site")
/*     */ public class CmsSite extends BaseEntity
/*     */ {
/*     */   private String copyright;
/*     */   private String description;
/*     */   private String keywords;
/*     */   private String logo;
/*     */   private String name;
/*     */   private String theme;
/*     */   private String title;
/*     */ 
/*     */   public String getCopyright()
/*     */   {
/*  54 */     return this.copyright;
/*     */   }
/*     */ 
/*     */   public void setCopyright(String copyright)
/*     */   {
/*  63 */     this.copyright = copyright;
/*     */   }
/*     */ 
/*     */   public String getDescription()
/*     */   {
/*  72 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description)
/*     */   {
/*  81 */     this.description = description;
/*     */   }
/*     */ 
/*     */   public String getKeywords()
/*     */   {
/*  90 */     return this.keywords;
/*     */   }
/*     */ 
/*     */   public void setKeywords(String keywords)
/*     */   {
/*  99 */     this.keywords = keywords;
/*     */   }
/*     */ 
/*     */   public String getLogo()
/*     */   {
/* 108 */     return this.logo;
/*     */   }
/*     */ 
/*     */   public void setLogo(String logo)
/*     */   {
/* 117 */     this.logo = logo;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 126 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 135 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getTheme()
/*     */   {
/* 144 */     return this.theme;
/*     */   }
/*     */ 
/*     */   public void setTheme(String theme)
/*     */   {
/* 153 */     this.theme = theme;
/*     */   }
/*     */ 
/*     */   public String getTitle()
/*     */   {
/* 162 */     return this.title;
/*     */   }
/*     */ 
/*     */   public void setTitle(String title)
/*     */   {
/* 171 */     this.title = title;
/*     */   }
/*     */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.cms.model.CmsSite
 * JD-Core Version:    0.6.0
 */