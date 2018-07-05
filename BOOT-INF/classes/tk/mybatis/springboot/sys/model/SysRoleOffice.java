/*     */ package tk.mybatis.springboot.sys.model;
/*     */ 
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Table;
/*     */ import tk.mybatis.springboot.base.BaseEntity;
/*     */ 
/*     */ @Table(name="sys_role_office")
/*     */ public class SysRoleOffice extends BaseEntity
/*     */ {
/*     */ 
/*     */   @Column(name="role_id")
/*     */   private Long roleId;
/*     */ 
/*     */   @Column(name="office_id")
/*     */   private Long officeId;
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
/*  49 */     return this.roleId;
/*     */   }
/*     */ 
/*     */   public void setRoleId(Long roleId)
/*     */   {
/*  58 */     this.roleId = roleId;
/*     */   }
/*     */ 
/*     */   public Long getOfficeId()
/*     */   {
/*  67 */     return this.officeId;
/*     */   }
/*     */ 
/*     */   public void setOfficeId(Long officeId)
/*     */   {
/*  76 */     this.officeId = officeId;
/*     */   }
/*     */ 
/*     */   public String getCreateBy()
/*     */   {
/*  83 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy)
/*     */   {
/*  90 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   public Date getCreateDate()
/*     */   {
/*  97 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/* 104 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   public String getUpdateBy()
/*     */   {
/* 111 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   public void setUpdateBy(String updateBy)
/*     */   {
/* 118 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   public Date getUpdateDate()
/*     */   {
/* 125 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate)
/*     */   {
/* 132 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   public String getDelFlag()
/*     */   {
/* 139 */     return this.delFlag;
/*     */   }
/*     */ 
/*     */   public void setDelFlag(String delFlag)
/*     */   {
/* 146 */     this.delFlag = delFlag;
/*     */   }
/*     */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.sys.model.SysRoleOffice
 * JD-Core Version:    0.6.0
 */