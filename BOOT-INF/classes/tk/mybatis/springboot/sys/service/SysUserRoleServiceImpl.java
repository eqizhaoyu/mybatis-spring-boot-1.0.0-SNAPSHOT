package tk.mybatis.springboot.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.springboot.base.ServiceMybatis;
import tk.mybatis.springboot.sys.mapper.SysUserMapper;
import tk.mybatis.springboot.sys.model.SysUserRole;

@Service
public class SysUserRoleServiceImpl extends ServiceMybatis<SysUserRole>
  implements SysUserRoleService
{

  @Autowired
  private SysUserMapper sysUserMapper;
}

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.sys.service.SysUserRoleServiceImpl
 * JD-Core Version:    0.6.0
 */