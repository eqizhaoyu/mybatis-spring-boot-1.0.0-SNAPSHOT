/*    */ package tk.mybatis.springboot.sys.service;
/*    */ 
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import tk.mybatis.springboot.base.ServiceMybatis;
/*    */ import tk.mybatis.springboot.sys.mapper.SysRoleMapper;
/*    */ import tk.mybatis.springboot.sys.mapper.SysUserRoleMapper;
/*    */ import tk.mybatis.springboot.sys.model.SysRole;
/*    */ import tk.mybatis.springboot.sys.model.SysUser;
/*    */ import tk.mybatis.springboot.sys.model.SysUserRole;
/*    */ 
/*    */ @Service
/*    */ public class SysRoleServiceImpl extends ServiceMybatis<SysRole>
/*    */   implements SysRoleService
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private SysRoleMapper SysRoleMapper;
/*    */ 
/*    */   @Autowired
/*    */   private SysUserRoleMapper SysUserRoleMapper;
/*    */ 
/*    */   public void deleteRoleAndUserRole(SysRole role)
/*    */   {
/* 34 */     SysUserRole ur = new SysUserRole();
/* 35 */     ur.setRoleId(role.getId());
/* 36 */     this.SysUserRoleMapper.delete(ur);
/* 37 */     this.SysRoleMapper.delete(role);
/*    */   }
/*    */ 
/*    */   public List<SysUser> findUserByRoleId(Long roleId)
/*    */   {
/* 43 */     return this.SysRoleMapper.findUserByRoleId(roleId);
/*    */   }
/*    */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.sys.service.SysRoleServiceImpl
 * JD-Core Version:    0.6.0
 */