/*    */ package tk.mybatis.springboot.druid;
/*    */ 
/*    */ import org.springframework.boot.context.properties.ConfigurationProperties;
/*    */ 
/*    */ @ConfigurationProperties(prefix="druid")
/*    */ public class DruidProperties
/*    */ {
/*    */   private String url;
/*    */   private String username;
/*    */   private String password;
/*    */   private String driverClass;
/*    */   private int maxActive;
/*    */   private int minIdle;
/*    */   private int initialSize;
/*    */   private boolean testOnBorrow;
/*    */ 
/*    */   public String getUrl()
/*    */   {
/* 24 */     return this.url;
/*    */   }
/*    */ 
/*    */   public void setUrl(String url) {
/* 28 */     this.url = url;
/*    */   }
/*    */ 
/*    */   public String getUsername() {
/* 32 */     return this.username;
/*    */   }
/*    */ 
/*    */   public void setUsername(String username) {
/* 36 */     this.username = username;
/*    */   }
/*    */ 
/*    */   public String getPassword() {
/* 40 */     return this.password;
/*    */   }
/*    */ 
/*    */   public void setPassword(String password) {
/* 44 */     this.password = password;
/*    */   }
/*    */ 
/*    */   public String getDriverClass() {
/* 48 */     return this.driverClass;
/*    */   }
/*    */ 
/*    */   public void setDriverClass(String driverClass) {
/* 52 */     this.driverClass = driverClass;
/*    */   }
/*    */ 
/*    */   public int getMaxActive() {
/* 56 */     return this.maxActive;
/*    */   }
/*    */ 
/*    */   public void setMaxActive(int maxActive) {
/* 60 */     this.maxActive = maxActive;
/*    */   }
/*    */ 
/*    */   public int getMinIdle() {
/* 64 */     return this.minIdle;
/*    */   }
/*    */ 
/*    */   public void setMinIdle(int minIdle) {
/* 68 */     this.minIdle = minIdle;
/*    */   }
/*    */ 
/*    */   public int getInitialSize() {
/* 72 */     return this.initialSize;
/*    */   }
/*    */ 
/*    */   public void setInitialSize(int initialSize) {
/* 76 */     this.initialSize = initialSize;
/*    */   }
/*    */ 
/*    */   public boolean isTestOnBorrow() {
/* 80 */     return this.testOnBorrow;
/*    */   }
/*    */ 
/*    */   public void setTestOnBorrow(boolean testOnBorrow) {
/* 84 */     this.testOnBorrow = testOnBorrow;
/*    */   }
/*    */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.druid.DruidProperties
 * JD-Core Version:    0.6.0
 */