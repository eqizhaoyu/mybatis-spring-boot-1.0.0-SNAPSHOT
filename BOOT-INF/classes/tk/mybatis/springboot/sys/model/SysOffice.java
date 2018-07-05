/*     */ package tk.mybatis.springboot.sys.model;
/*     */ 
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Table;
/*     */ import tk.mybatis.springboot.base.BaseEntity;
/*     */ 
/*     */ @Table(name="sys_office")
/*     */ public class SysOffice extends BaseEntity
/*     */ {
/*     */ 
/*     */   @Column(name="parent_id")
/*     */   private Long parentId;
/*     */ 
/*     */   @Column(name="parent_ids")
/*     */   private String parentIds;
/*     */ 
/*     */   @Column(name="area_id")
/*     */   private Long areaId;
/*     */   private String code;
/*     */   private String name;
/*     */   private String type;
/*     */   private String grade;
/*     */   private String address;
/*     */ 
/*     */   @Column(name="zip_code")
/*     */   private String zipCode;
/*     */   private String master;
/*     */   private String phone;
/*     */   private String fax;
/*     */   private String email;
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
/* 127 */     return this.parentId;
/*     */   }
/*     */ 
/*     */   public void setParentId(Long parentId)
/*     */   {
/* 136 */     this.parentId = parentId;
/*     */   }
/*     */ 
/*     */   public String getParentIds()
/*     */   {
/* 145 */     return this.parentIds;
/*     */   }
/*     */ 
/*     */   public void setParentIds(String parentIds)
/*     */   {
/* 154 */     this.parentIds = parentIds;
/*     */   }
/*     */ 
/*     */   public Long getAreaId()
/*     */   {
/* 163 */     return this.areaId;
/*     */   }
/*     */ 
/*     */   public void setAreaId(Long areaId)
/*     */   {
/* 172 */     this.areaId = areaId;
/*     */   }
/*     */ 
/*     */   public String getCode()
/*     */   {
/* 181 */     return this.code;
/*     */   }
/*     */ 
/*     */   public void setCode(String code)
/*     */   {
/* 190 */     this.code = code;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 199 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 208 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getType()
/*     */   {
/* 217 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(String type)
/*     */   {
/* 226 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public String getGrade()
/*     */   {
/* 235 */     return this.grade;
/*     */   }
/*     */ 
/*     */   public void setGrade(String grade)
/*     */   {
/* 244 */     this.grade = grade;
/*     */   }
/*     */ 
/*     */   public String getAddress()
/*     */   {
/* 253 */     return this.address;
/*     */   }
/*     */ 
/*     */   public void setAddress(String address)
/*     */   {
/* 262 */     this.address = address;
/*     */   }
/*     */ 
/*     */   public String getZipCode()
/*     */   {
/* 271 */     return this.zipCode;
/*     */   }
/*     */ 
/*     */   public void setZipCode(String zipCode)
/*     */   {
/* 280 */     this.zipCode = zipCode;
/*     */   }
/*     */ 
/*     */   public String getMaster()
/*     */   {
/* 289 */     return this.master;
/*     */   }
/*     */ 
/*     */   public void setMaster(String master)
/*     */   {
/* 298 */     this.master = master;
/*     */   }
/*     */ 
/*     */   public String getPhone()
/*     */   {
/* 307 */     return this.phone;
/*     */   }
/*     */ 
/*     */   public void setPhone(String phone)
/*     */   {
/* 316 */     this.phone = phone;
/*     */   }
/*     */ 
/*     */   public String getFax()
/*     */   {
/* 325 */     return this.fax;
/*     */   }
/*     */ 
/*     */   public void setFax(String fax)
/*     */   {
/* 334 */     this.fax = fax;
/*     */   }
/*     */ 
/*     */   public String getEmail()
/*     */   {
/* 343 */     return this.email;
/*     */   }
/*     */ 
/*     */   public void setEmail(String email)
/*     */   {
/* 352 */     this.email = email;
/*     */   }
/*     */ 
/*     */   public String getCreateBy()
/*     */   {
/* 361 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy)
/*     */   {
/* 370 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   public Date getCreateDate()
/*     */   {
/* 379 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/* 388 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   public String getUpdateBy()
/*     */   {
/* 397 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   public void setUpdateBy(String updateBy)
/*     */   {
/* 406 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   public Date getUpdateDate()
/*     */   {
/* 415 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate)
/*     */   {
/* 424 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   public String getRemarks()
/*     */   {
/* 433 */     return this.remarks;
/*     */   }
/*     */ 
/*     */   public void setRemarks(String remarks)
/*     */   {
/* 442 */     this.remarks = remarks;
/*     */   }
/*     */ 
/*     */   public String getDelFlag()
/*     */   {
/* 451 */     return this.delFlag;
/*     */   }
/*     */ 
/*     */   public void setDelFlag(String delFlag)
/*     */   {
/* 460 */     this.delFlag = delFlag;
/*     */   }
/*     */ 
/*     */   public String getIcon()
/*     */   {
/* 467 */     return this.icon;
/*     */   }
/*     */ 
/*     */   public void setIcon(String icon)
/*     */   {
/* 474 */     this.icon = icon;
/*     */   }
/*     */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.sys.model.SysOffice
 * JD-Core Version:    0.6.0
 */