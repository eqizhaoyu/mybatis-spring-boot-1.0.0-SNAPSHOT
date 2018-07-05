/*    */ package tk.mybatis.springboot;
/*    */ 
/*    */ import org.mybatis.spring.annotation.MapperScan;
/*    */ import org.springframework.boot.SpringApplication;
/*    */ import org.springframework.boot.autoconfigure.SpringBootApplication;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ import org.springframework.web.servlet.config.annotation.EnableWebMvc;
/*    */ import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/*    */ 
/*    */ @Controller
/*    */ @EnableWebMvc
/*    */ @SpringBootApplication
/*    */ @MapperScan(basePackages={"tk.mybatis.springboot.*.mapper"})
/*    */ public class Application extends WebMvcConfigurerAdapter
/*    */ {
/*    */   public static void main(String[] args)
/*    */   {
/* 22 */     SpringApplication.run(Application.class, args);
/*    */   }
/*    */   @RequestMapping({"/"})
/*    */   String home() {
/* 27 */     return "redirect:login";
/*    */   }
/*    */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.Application
 * JD-Core Version:    0.6.0
 */