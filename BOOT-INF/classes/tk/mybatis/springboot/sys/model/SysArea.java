/*     */ package tk.mybatis.springboot.sys.model;
/*     */ 
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Table;
/*     */ import tk.mybatis.springboot.base.BaseEntity;
/*     */ 
/*     */ @Table(name="sys_area")
/*     */ public class SysArea extends BaseEntity
/*     */ {
/*     */ 
/*     */   @Column(name="parent_id")
/*     */   private Long parentId;
/*     */ 
/*     */   @Column(name="parent_ids")
/*     */   private String parentIds;
/*     */   private String code;
/*     */   private String name;
/*     */   private String type;
/*     */ 
/*     */   @Column(name="create_by")
/*     */   private String createBy;
/*     */ 
/*     */   @Column(name="create_date")
/*     */   private Date createDate;
/*     */ 
/*     */   @Column(name="update_by")
/*     */   private String updateBy;
/*     */ 
/*     */   @Column(name="update_date")
/*     */   private Date updateDate;
/*     */   private String remarks;
/*     */ 
/*     */   @Column(name="del_flag")
/*     */   private String delFlag;
/*     */   private String icon;
/*     */ 
/*     */   public Long getParentId()
/*     */   {
/*  86 */     return this.parentId;
/*     */   }
/*     */ 
/*     */   public void setParentId(Long parentId)
/*     */   {
/*  95 */     this.parentId = parentId;
/*     */   }
/*     */ 
/*     */   public String getParentIds()
/*     */   {
/* 104 */     return this.parentIds;
/*     */   }
/*     */ 
/*     */   public void setParentIds(String parentIds)
/*     */   {
/* 113 */     this.parentIds = parentIds;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/* 122 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/* 131 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 140 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 149 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getType()
/*     */   {
/* 158 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(String type)
/*     */   {
/* 167 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public String getCreateBy()
/*     */   {
/* 176 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy)
/*     */   {
/* 185 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   public Date getCreateDate()
/*     */   {
/* 194 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/* 203 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   public String getUpdateBy()
/*     */   {
/* 212 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   public void setUpdateBy(String updateBy)
/*     */   {
/* 221 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   public Date getUpdateDate()
/*     */   {
/* 230 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate)
/*     */   {
/* 239 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   public String getRemarks()
/*     */   {
/* 248 */     return this.remarks;
/*     */   }
/*     */ 
/*     */   public void setRemarks(String remarks)
/*     */   {
/* 257 */     this.remarks = remarks;
/*     */   }
/*     */ 
/*     */   public String getDelFlag()
/*     */   {
/* 266 */     return this.delFlag;
/*     */   }
/*     */ 
/*     */   public void setDelFlag(String delFlag)
/*     */   {
/* 275 */     this.delFlag = delFlag;
/*     */   }
/*     */ 
/*     */   public String getIcon()
/*     */   {
/* 282 */     return this.icon;
/*     */   }
/*     */ 
/*     */   public void setIcon(String icon)
/*     */   {
/* 289 */     this.icon = icon;
/*     */   }
/*     */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.sys.model.SysArea
 * JD-Core Version:    0.6.0
 */