/*    */ package tk.mybatis.springboot.druid;
/*    */ 
/*    */ import com.alibaba.druid.pool.DruidDataSource;
/*    */ import java.sql.SQLException;
/*    */ import javax.sql.DataSource;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.boot.autoconfigure.AutoConfigureBefore;
/*    */ import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
/*    */ import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
/*    */ import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
/*    */ import org.springframework.boot.context.properties.EnableConfigurationProperties;
/*    */ import org.springframework.context.annotation.Bean;
/*    */ import org.springframework.context.annotation.Configuration;
/*    */ 
/*    */ @Configuration
/*    */ @EnableConfigurationProperties({DruidProperties.class})
/*    */ @ConditionalOnClass({DruidDataSource.class})
/*    */ @ConditionalOnProperty(prefix="druid", name={"url"})
/*    */ @AutoConfigureBefore({DataSourceAutoConfiguration.class})
/*    */ public class DruidAutoConfiguration
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private DruidProperties properties;
/*    */ 
/*    */   @Bean
/*    */   public DataSource dataSource()
/*    */   {
/* 32 */     DruidDataSource dataSource = new DruidDataSource();
/* 33 */     dataSource.setUrl(this.properties.getUrl());
/* 34 */     dataSource.setUsername(this.properties.getUsername());
/* 35 */     dataSource.setPassword(this.properties.getPassword());
/* 36 */     if (this.properties.getInitialSize() > 0) {
/* 37 */       dataSource.setInitialSize(this.properties.getInitialSize());
/*    */     }
/* 39 */     if (this.properties.getMinIdle() > 0) {
/* 40 */       dataSource.setMinIdle(this.properties.getMinIdle());
/*    */     }
/* 42 */     if (this.properties.getMaxActive() > 0) {
/* 43 */       dataSource.setMaxActive(this.properties.getMaxActive());
/*    */     }
/* 45 */     dataSource.setTestOnBorrow(this.properties.isTestOnBorrow());
/*    */     try {
/* 47 */       dataSource.init();
/*    */     } catch (SQLException e) {
/* 49 */       throw new RuntimeException(e);
/*    */     }
/* 51 */     return dataSource;
/*    */   }
/*    */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.druid.DruidAutoConfiguration
 * JD-Core Version:    0.6.0
 */