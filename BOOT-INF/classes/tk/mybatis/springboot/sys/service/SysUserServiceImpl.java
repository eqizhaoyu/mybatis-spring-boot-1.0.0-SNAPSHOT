/*    */ package tk.mybatis.springboot.sys.service;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import tk.mybatis.springboot.base.ServiceMybatis;
/*    */ import tk.mybatis.springboot.sys.mapper.SysUserMapper;
/*    */ import tk.mybatis.springboot.sys.model.SysUser;
/*    */ import tk.mybatis.springboot.util.PasswordEncoder;
/*    */ 
/*    */ @Service
/*    */ public class SysUserServiceImpl extends ServiceMybatis<SysUser>
/*    */   implements SysUserService
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private SysUserMapper sysUserMapper;
/*    */ 
/*    */   public SysUser checkUser(String username, String password)
/*    */   {
/* 30 */     SysUser sysUser = new SysUser();
/* 31 */     String secPwd = PasswordEncoder.encrypt(password, username);
/* 32 */     sysUser.setUsername(username);
/* 33 */     sysUser.setPassword(secPwd);
/* 34 */     List users = select(sysUser);
/* 35 */     if ((users != null) && (users.size() == 1) && (users.get(0) != null)) {
/* 36 */       return (SysUser)users.get(0);
/*    */     }
/* 38 */     return null;
/*    */   }
/*    */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.sys.service.SysUserServiceImpl
 * JD-Core Version:    0.6.0
 */