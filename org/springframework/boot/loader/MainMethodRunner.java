/*    */ package org.springframework.boot.loader;
/*    */ 
/*    */ import java.lang.reflect.Method;
/*    */ 
/*    */ public class MainMethodRunner
/*    */ {
/*    */   private final String mainClassName;
/*    */   private final String[] args;
/*    */ 
/*    */   public MainMethodRunner(String mainClass, String[] args)
/*    */   {
/* 40 */     this.mainClassName = mainClass;
/* 41 */     this.args = (args == null ? null : (String[])args.clone());
/*    */   }
/*    */ 
/*    */   public void run() throws Exception
/*    */   {
/* 46 */     Class mainClass = Thread.currentThread().getContextClassLoader()
/* 46 */       .loadClass(this.mainClassName);
/*    */ 
/* 47 */     Method mainMethod = mainClass.getDeclaredMethod("main", new Class[] { [Ljava.lang.String.class });
/* 48 */     mainMethod.invoke(null, new Object[] { this.args });
/*    */   }
/*    */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     org.springframework.boot.loader.MainMethodRunner
 * JD-Core Version:    0.6.0
 */