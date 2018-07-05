/*     */ package tk.mybatis.springboot.sys.model;
/*     */ 
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Table;
/*     */ import tk.mybatis.springboot.base.BaseEntity;
/*     */ 
/*     */ @Table(name="sys_log")
/*     */ public class SysLog extends BaseEntity
/*     */ {
/*     */   private String type;
/*     */ 
/*     */   @Column(name="create_by")
/*     */   private String createBy;
/*     */ 
/*     */   @Column(name="create_date")
/*     */   private Date createDate;
/*     */ 
/*     */   @Column(name="remote_addr")
/*     */   private String remoteAddr;
/*     */ 
/*     */   @Column(name="user_agent")
/*     */   private String userAgent;
/*     */ 
/*     */   @Column(name="request_uri")
/*     */   private String requestUri;
/*     */   private String method;
/*     */   private String params;
/*     */   private String exception;
/*     */   private String description;
/*     */ 
/*     */   public String getType()
/*     */   {
/*  77 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(String type)
/*     */   {
/*  86 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public String getCreateBy()
/*     */   {
/*  95 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy)
/*     */   {
/* 104 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   public Date getCreateDate()
/*     */   {
/* 113 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/* 122 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   public String getRemoteAddr()
/*     */   {
/* 131 */     return this.remoteAddr;
/*     */   }
/*     */ 
/*     */   public void setRemoteAddr(String remoteAddr)
/*     */   {
/* 140 */     this.remoteAddr = remoteAddr;
/*     */   }
/*     */ 
/*     */   public String getUserAgent()
/*     */   {
/* 149 */     return this.userAgent;
/*     */   }
/*     */ 
/*     */   public void setUserAgent(String userAgent)
/*     */   {
/* 158 */     this.userAgent = userAgent;
/*     */   }
/*     */ 
/*     */   public String getRequestUri()
/*     */   {
/* 167 */     return this.requestUri;
/*     */   }
/*     */ 
/*     */   public void setRequestUri(String requestUri)
/*     */   {
/* 176 */     this.requestUri = requestUri;
/*     */   }
/*     */ 
/*     */   public String getMethod()
/*     */   {
/* 185 */     return this.method;
/*     */   }
/*     */ 
/*     */   public void setMethod(String method)
/*     */   {
/* 194 */     this.method = method;
/*     */   }
/*     */ 
/*     */   public String getParams()
/*     */   {
/* 203 */     return this.params;
/*     */   }
/*     */ 
/*     */   public void setParams(String params)
/*     */   {
/* 212 */     this.params = params;
/*     */   }
/*     */ 
/*     */   public String getException()
/*     */   {
/* 221 */     return this.exception;
/*     */   }
/*     */ 
/*     */   public void setException(String exception)
/*     */   {
/* 230 */     this.exception = exception;
/*     */   }
/*     */ 
/*     */   public String getDescription()
/*     */   {
/* 239 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description)
/*     */   {
/* 248 */     this.description = description;
/*     */   }
/*     */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.sys.model.SysLog
 * JD-Core Version:    0.6.0
 */