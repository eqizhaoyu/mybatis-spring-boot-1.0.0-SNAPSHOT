/*    */ package tk.mybatis.springboot.sys.model;
/*    */ 
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.Id;
/*    */ 
/*    */ public class Country
/*    */ {
/*    */ 
/*    */   @Id
/*    */   @Column(name="Id")
/*    */   @GeneratedValue(generator="JDBC")
/*    */   private Long id;
/*    */   private String countryname;
/*    */   private String countrycode;
/*    */ 
/*    */   public Long getId()
/*    */   {
/* 30 */     return this.id;
/*    */   }
/*    */ 
/*    */   public void setId(Long id)
/*    */   {
/* 39 */     this.id = id;
/*    */   }
/*    */ 
/*    */   public String getCountryname()
/*    */   {
/* 48 */     return this.countryname;
/*    */   }
/*    */ 
/*    */   public void setCountryname(String countryname)
/*    */   {
/* 57 */     this.countryname = countryname;
/*    */   }
/*    */ 
/*    */   public String getCountrycode()
/*    */   {
/* 66 */     return this.countrycode;
/*    */   }
/*    */ 
/*    */   public void setCountrycode(String countrycode)
/*    */   {
/* 75 */     this.countrycode = countrycode;
/*    */   }
/*    */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.sys.model.Country
 * JD-Core Version:    0.6.0
 */