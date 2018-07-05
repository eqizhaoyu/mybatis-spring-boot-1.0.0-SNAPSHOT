/*     */ package tk.mybatis.springboot.sys.model;
/*     */ 
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Table;
/*     */ import tk.mybatis.springboot.base.BaseEntity;
/*     */ 
/*     */ @Table(name="sys_user")
/*     */ public class SysUser extends BaseEntity
/*     */ {
/*     */ 
/*     */   @Column(name="company_id")
/*     */   private Long companyId;
/*     */ 
/*     */   @Column(name="office_id")
/*     */   private Long officeId;
/*     */   private String username;
/*     */   private String password;
/*     */   private String no;
/*     */   private String name;
/*     */   private String email;
/*     */   private String phone;
/*     */   private String mobile;
/*     */ 
/*     */   @Column(name="user_type")
/*     */   private String userType;
/*     */ 
/*     */   @Column(name="login_ip")
/*     */   private String loginIp;
/*     */ 
/*     */   @Column(name="login_date")
/*     */   private Date loginDate;
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
/*     */   private String status;
/*     */ 
/*     */   public Long getCompanyId()
/*     */   {
/* 124 */     return this.companyId;
/*     */   }
/*     */ 
/*     */   public void setCompanyId(Long companyId)
/*     */   {
/* 133 */     this.companyId = companyId;
/*     */   }
/*     */ 
/*     */   public Long getOfficeId()
/*     */   {
/* 142 */     return this.officeId;
/*     */   }
/*     */ 
/*     */   public void setOfficeId(Long officeId)
/*     */   {
/* 151 */     this.officeId = officeId;
/*     */   }
/*     */ 
/*     */   public String getUsername()
/*     */   {
/* 160 */     return this.username;
/*     */   }
/*     */ 
/*     */   public void setUsername(String username)
/*     */   {
/* 169 */     this.username = username;
/*     */   }
/*     */ 
/*     */   public String getPassword()
/*     */   {
/* 178 */     return this.password;
/*     */   }
/*     */ 
/*     */   public void setPassword(String password)
/*     */   {
/* 187 */     this.password = password;
/*     */   }
/*     */ 
/*     */   public String getNo()
/*     */   {
/* 196 */     return this.no;
/*     */   }
/*     */ 
/*     */   public void setNo(String no)
/*     */   {
/* 205 */     this.no = no;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/* 214 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name)
/*     */   {
/* 223 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getEmail()
/*     */   {
/* 232 */     return this.email;
/*     */   }
/*     */ 
/*     */   public void setEmail(String email)
/*     */   {
/* 241 */     this.email = email;
/*     */   }
/*     */ 
/*     */   public String getPhone()
/*     */   {
/* 250 */     return this.phone;
/*     */   }
/*     */ 
/*     */   public void setPhone(String phone)
/*     */   {
/* 259 */     this.phone = phone;
/*     */   }
/*     */ 
/*     */   public String getMobile()
/*     */   {
/* 268 */     return this.mobile;
/*     */   }
/*     */ 
/*     */   public void setMobile(String mobile)
/*     */   {
/* 277 */     this.mobile = mobile;
/*     */   }
/*     */ 
/*     */   public String getUserType()
/*     */   {
/* 286 */     return this.userType;
/*     */   }
/*     */ 
/*     */   public void setUserType(String userType)
/*     */   {
/* 295 */     this.userType = userType;
/*     */   }
/*     */ 
/*     */   public String getLoginIp()
/*     */   {
/* 304 */     return this.loginIp;
/*     */   }
/*     */ 
/*     */   public void setLoginIp(String loginIp)
/*     */   {
/* 313 */     this.loginIp = loginIp;
/*     */   }
/*     */ 
/*     */   public Date getLoginDate()
/*     */   {
/* 322 */     return this.loginDate;
/*     */   }
/*     */ 
/*     */   public void setLoginDate(Date loginDate)
/*     */   {
/* 331 */     this.loginDate = loginDate;
/*     */   }
/*     */ 
/*     */   public String getCreateBy()
/*     */   {
/* 340 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy)
/*     */   {
/* 349 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   public Date getCreateDate()
/*     */   {
/* 358 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/* 367 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   public String getUpdateBy()
/*     */   {
/* 376 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   public void setUpdateBy(String updateBy)
/*     */   {
/* 385 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   public Date getUpdateDate()
/*     */   {
/* 394 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate)
/*     */   {
/* 403 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   public String getRemarks()
/*     */   {
/* 412 */     return this.remarks;
/*     */   }
/*     */ 
/*     */   public void setRemarks(String remarks)
/*     */   {
/* 421 */     this.remarks = remarks;
/*     */   }
/*     */ 
/*     */   public String getDelFlag()
/*     */   {
/* 430 */     return this.delFlag;
/*     */   }
/*     */ 
/*     */   public void setDelFlag(String delFlag)
/*     */   {
/* 439 */     this.delFlag = delFlag;
/*     */   }
/*     */ 
/*     */   public String getStatus()
/*     */   {
/* 446 */     return this.status;
/*     */   }
/*     */ 
/*     */   public void setStatus(String status)
/*     */   {
/* 453 */     this.status = status;
/*     */   }
/*     */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.sys.model.SysUser
 * JD-Core Version:    0.6.0
 */