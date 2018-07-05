/*     */ package tk.mybatis.springboot.cms.model;
/*     */ 
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Table;
/*     */ import tk.mybatis.springboot.base.BaseEntity;
/*     */ 
/*     */ @Table(name="cms_comment")
/*     */ public class CmsComment extends BaseEntity
/*     */ {
/*     */ 
/*     */   @Column(name="auditDate")
/*     */   private Date auditdate;
/*     */   private String content;
/*     */ 
/*     */   @Column(name="createDate")
/*     */   private Date createdate;
/*     */ 
/*     */   @Column(name="delFlag")
/*     */   private Integer delflag;
/*     */   private String email;
/*     */   private String ip;
/*     */   private String name;
/*     */   private String url;
/*     */ 
/*     */   @Column(name="article_id")
/*     */   private Long articleId;
/*     */ 
/*     */   @Column(name="auditer_id")
/*     */   private Long auditerId;
/*     */ 
/*     */   @Column(name="headPath")
/*     */   private String headpath;
/*     */ 
/*     */   public Date getAuditdate()
/*     */   {
/*  46 */     return this.auditdate;
/*     */   }
/*     */ 
/*     */   public void setAuditdate(Date auditdate)
/*     */   {
/*  53 */     this.auditdate = auditdate;
/*     */   }
/*     */ 
/*     */   public String getContent()
/*     */   {
/*  60 */     return this.content;
/*     */   }
/*     */ 
/*     */   public void setContent(String content)
/*     */   {
/*  67 */     this.content = content;
/*     */   }
/*     */ 
/*     */   public Date getCreatedate()
/*     */   {
/*  74 */     return this.createdate;
/*     */   }
/*     */ 
/*     */   public void setCreatedate(Date createdate)
/*     */   {
/*  81 */     this.createdate = createdate;
/*     */   }
/*     */ 
/*     */   public Integer getDelflag()
/*     */   {
/*  88 */     return this.delflag;
/*     */   }
/*     */ 
/*     */   public void setDelflag(Integer delflag)
/*     */   {
/*  95 */     this.delflag = delflag;
/*     */   }
/*     */ 
/*     */   public String getEmail()
/*     */   {
/* 102 */     return this.email;
/*     */   }
/*     */ 
/*     */   public void setEmail(String email)
/*     */   {
/* 109 */     this.email = email;
/*     */   }
/*     */ 
/*     */   public String getIp()
/*     */   {
/* 116 */     return this.ip;
/*     */   }
/*     */ 
/*     */   public void setIp(String ip)
/*     */   {
/* 123 */     this.ip = ip;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 130 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 137 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getUrl()
/*     */   {
/* 144 */     return this.url;
/*     */   }
/*     */ 
/*     */   public void setUrl(String url)
/*     */   {
/* 151 */     this.url = url;
/*     */   }
/*     */ 
/*     */   public Long getArticleId()
/*     */   {
/* 158 */     return this.articleId;
/*     */   }
/*     */ 
/*     */   public void setArticleId(Long articleId)
/*     */   {
/* 165 */     this.articleId = articleId;
/*     */   }
/*     */ 
/*     */   public Long getAuditerId()
/*     */   {
/* 172 */     return this.auditerId;
/*     */   }
/*     */ 
/*     */   public void setAuditerId(Long auditerId)
/*     */   {
/* 179 */     this.auditerId = auditerId;
/*     */   }
/*     */ 
/*     */   public String getHeadpath()
/*     */   {
/* 186 */     return this.headpath;
/*     */   }
/*     */ 
/*     */   public void setHeadpath(String headpath)
/*     */   {
/* 193 */     this.headpath = headpath;
/*     */   }
/*     */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.cms.model.CmsComment
 * JD-Core Version:    0.6.0
 */