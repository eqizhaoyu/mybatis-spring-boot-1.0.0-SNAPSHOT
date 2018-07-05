/*     */ package tk.mybatis.springboot.sys.model;
/*     */ 
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Table;
/*     */ import tk.mybatis.springboot.base.BaseEntity;
/*     */ 
/*     */ @Table(name="sys_role")
/*     */ public class SysRole extends BaseEntity
/*     */ {
/*     */ 
/*     */   @Column(name="office_id")
/*     */   private Long officeId;
/*     */   private String name;
/*     */ 
/*     */   @Column(name="data_scope")
/*     */   private String dataScope;
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
/*     */ 
/*     */   public Long getOfficeId()
/*     */   {
/*  74 */     return this.officeId;
/*     */   }
/*     */ 
/*     */   public void setOfficeId(Long officeId)
/*     */   {
/*  83 */     this.officeId = officeId;
/*     */   }
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
/*     */   public String getDataScope()
/*     */   {
/* 110 */     return this.dataScope;
/*     */   }
/*     */ 
/*     */   public void setDataScope(String dataScope)
/*     */   {
/* 119 */     this.dataScope = dataScope;
/*     */   }
/*     */ 
/*     */   public String getCreateBy()
/*     */   {
/* 128 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy)
/*     */   {
/* 137 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   public Date getCreateDate()
/*     */   {
/* 146 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/* 155 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   public String getUpdateBy()
/*     */   {
/* 164 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   public void setUpdateBy(String updateBy)
/*     */   {
/* 173 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   public Date getUpdateDate()
/*     */   {
/* 182 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate)
/*     */   {
/* 191 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   public String getRemarks()
/*     */   {
/* 200 */     return this.remarks;
/*     */   }
/*     */ 
/*     */   public void setRemarks(String remarks)
/*     */   {
/* 209 */     this.remarks = remarks;
/*     */   }
/*     */ 
/*     */   public String getDelFlag()
/*     */   {
/* 218 */     return this.delFlag;
/*     */   }
/*     */ 
/*     */   public void setDelFlag(String delFlag)
/*     */   {
/* 227 */     this.delFlag = delFlag;
/*     */   }
/*     */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.sys.model.SysRole
 * JD-Core Version:    0.6.0
 */