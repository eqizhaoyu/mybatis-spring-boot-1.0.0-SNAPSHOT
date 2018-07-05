/*     */ package tk.mybatis.springboot.sys.model;
/*     */ 
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Table;
/*     */ import tk.mybatis.springboot.base.BaseEntity;
/*     */ 
/*     */ @Table(name="sys_resource")
/*     */ public class SysResource extends BaseEntity
/*     */ {
/*     */   private String name;
/*     */   private String common;
/*     */   private String icon;
/*     */   private Integer sort;
/*     */ 
/*     */   @Column(name="parent_id")
/*     */   private Long parentId;
/*     */   private String type;
/*     */   private String url;
/*     */   private String description;
/*     */   private String status;
/*     */ 
/*     */   @Column(name="parent_ids")
/*     */   private String parentIds;
/*     */ 
/*     */   @Column(name="create_date")
/*     */   private Date createDate;
/*     */ 
/*     */   @Column(name="update_date")
/*     */   private Date updateDate;
/*     */ 
/*     */   @Column(name="create_by")
/*     */   private String createBy;
/*     */ 
/*     */   @Column(name="update_by")
/*     */   private String updateBy;
/*     */ 
/*     */   @Column(name="del_flag")
/*     */   private String delFlag;
/*     */ 
/*     */   @Column(name="permission_str")
/*     */   private String permissionStr;
/*     */ 
/*     */   public String getName()
/*     */   {
/*  92 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 101 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getCommon()
/*     */   {
/* 110 */     return this.common;
/*     */   }
/*     */ 
/*     */   public void setCommon(String common)
/*     */   {
/* 119 */     this.common = common;
/*     */   }
/*     */ 
/*     */   public String getIcon()
/*     */   {
/* 128 */     return this.icon;
/*     */   }
/*     */ 
/*     */   public void setIcon(String icon)
/*     */   {
/* 137 */     this.icon = icon;
/*     */   }
/*     */ 
/*     */   public Integer getSort()
/*     */   {
/* 146 */     return this.sort;
/*     */   }
/*     */ 
/*     */   public void setSort(Integer sort)
/*     */   {
/* 155 */     this.sort = sort;
/*     */   }
/*     */ 
/*     */   public Long getParentId()
/*     */   {
/* 164 */     return this.parentId;
/*     */   }
/*     */ 
/*     */   public void setParentId(Long parentId)
/*     */   {
/* 173 */     this.parentId = parentId;
/*     */   }
/*     */ 
/*     */   public String getType()
/*     */   {
/* 182 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(String type)
/*     */   {
/* 191 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public String getUrl()
/*     */   {
/* 200 */     return this.url;
/*     */   }
/*     */ 
/*     */   public void setUrl(String url)
/*     */   {
/* 209 */     this.url = url;
/*     */   }
/*     */ 
/*     */   public String getDescription()
/*     */   {
/* 218 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description)
/*     */   {
/* 227 */     this.description = description;
/*     */   }
/*     */ 
/*     */   public String getStatus()
/*     */   {
/* 236 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(String status)
/*     */   {
/* 245 */     this.status = status;
/*     */   }
/*     */ 
/*     */   public String getParentIds()
/*     */   {
/* 254 */     return this.parentIds;
/*     */   }
/*     */ 
/*     */   public void setParentIds(String parentIds)
/*     */   {
/* 263 */     this.parentIds = parentIds;
/*     */   }
/*     */ 
/*     */   public Date getCreateDate()
/*     */   {
/* 270 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/* 277 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   public Date getUpdateDate()
/*     */   {
/* 284 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate)
/*     */   {
/* 291 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   public String getCreateBy()
/*     */   {
/* 298 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy)
/*     */   {
/* 305 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   public String getUpdateBy()
/*     */   {
/* 312 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   public void setUpdateBy(String updateBy)
/*     */   {
/* 319 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   public String getDelFlag()
/*     */   {
/* 326 */     return this.delFlag;
/*     */   }
/*     */ 
/*     */   public void setDelFlag(String delFlag)
/*     */   {
/* 333 */     this.delFlag = delFlag;
/*     */   }
/*     */ 
/*     */   public String getPermissionStr()
/*     */   {
/* 340 */     return this.permissionStr;
/*     */   }
/*     */ 
/*     */   public void setPermissionStr(String permissionStr)
/*     */   {
/* 347 */     this.permissionStr = permissionStr;
/*     */   }
/*     */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.sys.model.SysResource
 * JD-Core Version:    0.6.0
 */