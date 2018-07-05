/*     */ package tk.mybatis.springboot.sys.model;
/*     */ 
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Table;
/*     */ import tk.mybatis.springboot.base.BaseEntity;
/*     */ 
/*     */ @Table(name="sys_role_resource")
/*     */ public class SysRoleResource extends BaseEntity
/*     */ {
/*     */ 
/*     */   @Column(name="role_id")
/*     */   private Long roleId;
/*     */ 
/*     */   @Column(name="resource_id")
/*     */   private Long resourceId;
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
/*     */ 
/*     */   @Column(name="del_flag")
/*     */   private String delFlag;
/*     */ 
/*     */   public Long getRoleId()
/*     */   {
/*  40 */     return this.roleId;
/*     */   }
/*     */ 
/*     */   public void setRoleId(Long roleId)
/*     */   {
/*  47 */     this.roleId = roleId;
/*     */   }
/*     */ 
/*     */   public Long getResourceId()
/*     */   {
/*  54 */     return this.resourceId;
/*     */   }
/*     */ 
/*     */   public void setResourceId(Long resourceId)
/*     */   {
/*  61 */     this.resourceId = resourceId;
/*     */   }
/*     */ 
/*     */   public String getCreateBy()
/*     */   {
/*  68 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy)
/*     */   {
/*  75 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   public Date getCreateDate()
/*     */   {
/*  82 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/*  89 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   public String getUpdateBy()
/*     */   {
/*  96 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   public void setUpdateBy(String updateBy)
/*     */   {
/* 103 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   public Date getUpdateDate()
/*     */   {
/* 110 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate)
/*     */   {
/* 117 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   public String getDelFlag()
/*     */   {
/* 124 */     return this.delFlag;
/*     */   }
/*     */ 
/*     */   public void setDelFlag(String delFlag)
/*     */   {
/* 131 */     this.delFlag = delFlag;
/*     */   }
/*     */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.sys.model.SysRoleResource
 * JD-Core Version:    0.6.0
 */