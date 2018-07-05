/*     */ package tk.mybatis.springboot.sys.model;
/*     */ 
/*     */ import java.util.Date;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Table;
/*     */ import tk.mybatis.springboot.base.BaseEntity;
/*     */ 
/*     */ @Table(name="sys_dict")
/*     */ public class SysDict extends BaseEntity
/*     */ {
/*     */   private String label;
/*     */   private String value;
/*     */   private String type;
/*     */   private String description;
/*     */   private Integer sort;
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
/*     */   public String getLabel()
/*     */   {
/*  82 */     return this.label;
/*     */   }
/*     */ 
/*     */   public void setLabel(String label)
/*     */   {
/*  91 */     this.label = label;
/*     */   }
/*     */ 
/*     */   public String getValue()
/*     */   {
/* 100 */     return this.value;
/*     */   }
/*     */ 
/*     */   public void setValue(String value)
/*     */   {
/* 109 */     this.value = value;
/*     */   }
/*     */ 
/*     */   public String getType()
/*     */   {
/* 118 */     return this.type;
/*     */   }
/*     */ 
/*     */   public void setType(String type)
/*     */   {
/* 127 */     this.type = type;
/*     */   }
/*     */ 
/*     */   public String getDescription()
/*     */   {
/* 136 */     return this.description;
/*     */   }
/*     */ 
/*     */   public void setDescription(String description)
/*     */   {
/* 145 */     this.description = description;
/*     */   }
/*     */ 
/*     */   public Integer getSort()
/*     */   {
/* 154 */     return this.sort;
/*     */   }
/*     */ 
/*     */   public void setSort(Integer sort)
/*     */   {
/* 163 */     this.sort = sort;
/*     */   }
/*     */ 
/*     */   public String getCreateBy()
/*     */   {
/* 172 */     return this.createBy;
/*     */   }
/*     */ 
/*     */   public void setCreateBy(String createBy)
/*     */   {
/* 181 */     this.createBy = createBy;
/*     */   }
/*     */ 
/*     */   public Date getCreateDate()
/*     */   {
/* 190 */     return this.createDate;
/*     */   }
/*     */ 
/*     */   public void setCreateDate(Date createDate)
/*     */   {
/* 199 */     this.createDate = createDate;
/*     */   }
/*     */ 
/*     */   public String getUpdateBy()
/*     */   {
/* 208 */     return this.updateBy;
/*     */   }
/*     */ 
/*     */   public void setUpdateBy(String updateBy)
/*     */   {
/* 217 */     this.updateBy = updateBy;
/*     */   }
/*     */ 
/*     */   public Date getUpdateDate()
/*     */   {
/* 226 */     return this.updateDate;
/*     */   }
/*     */ 
/*     */   public void setUpdateDate(Date updateDate)
/*     */   {
/* 235 */     this.updateDate = updateDate;
/*     */   }
/*     */ 
/*     */   public String getRemarks()
/*     */   {
/* 244 */     return this.remarks;
/*     */   }
/*     */ 
/*     */   public void setRemarks(String remarks)
/*     */   {
/* 253 */     this.remarks = remarks;
/*     */   }
/*     */ 
/*     */   public String getDelFlag()
/*     */   {
/* 262 */     return this.delFlag;
/*     */   }
/*     */ 
/*     */   public void setDelFlag(String delFlag)
/*     */   {
/* 271 */     this.delFlag = delFlag;
/*     */   }
/*     */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.sys.model.SysDict
 * JD-Core Version:    0.6.0
 */