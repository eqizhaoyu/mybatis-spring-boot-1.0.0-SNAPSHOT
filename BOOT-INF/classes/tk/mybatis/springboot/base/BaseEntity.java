/*    */ package tk.mybatis.springboot.base;
/*    */ 
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.GenerationType;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.Transient;
/*    */ 
/*    */ public class BaseEntity
/*    */ {
/*    */ 
/*    */   @Id
/*    */   @Column(name="Id")
/*    */   @GeneratedValue(strategy=GenerationType.IDENTITY)
/*    */   private Long id;
/*    */ 
/*    */   @Transient
/* 41 */   private Integer page = Integer.valueOf(1);
/*    */ 
/*    */   @Transient
/* 44 */   private Integer rows = Integer.valueOf(10);
/*    */ 
/*    */   public Long getId()
/*    */   {
/* 48 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Long id) {
/* 52 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public Integer getPage() {
/* 56 */     return this.page;
/*    */   }
/*    */ 
/*    */   public void setPage(Integer page) {
/* 60 */     this.page = page;
/*    */   }
/*    */ 
/*    */   public Integer getRows() {
/* 64 */     return this.rows;
/*    */   }
/*    */ 
/*    */   public void setRows(Integer rows) {
/* 68 */     this.rows = rows;
/*    */   }
/*    */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.base.BaseEntity
 * JD-Core Version:    0.6.0
 */