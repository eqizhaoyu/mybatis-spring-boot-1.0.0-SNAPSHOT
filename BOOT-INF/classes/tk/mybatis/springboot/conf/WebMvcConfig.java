/*    */ package tk.mybatis.springboot.conf;
/*    */ 
/*    */ import org.springframework.context.annotation.Configuration;
/*    */ import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistration;
/*    */ import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
/*    */ import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/*    */ 
/*    */ @Configuration
/*    */ public class WebMvcConfig extends WebMvcConfigurerAdapter
/*    */ {
/*    */   public void addResourceHandlers(ResourceHandlerRegistry registry)
/*    */   {
/* 40 */     registry.addResourceHandler(new String[] { "/static/**" }).addResourceLocations(new String[] { "classpath:/static/" });
/*    */   }
/*    */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.conf.WebMvcConfig
 * JD-Core Version:    0.6.0
 */