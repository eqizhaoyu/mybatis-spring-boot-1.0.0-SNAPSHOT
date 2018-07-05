package tk.mybatis.springboot.sys.service;

import java.util.List;
import tk.mybatis.springboot.base.BaseService;
import tk.mybatis.springboot.sys.model.SysRole;
import tk.mybatis.springboot.sys.model.SysUser;

public abstract interface SysRoleService extends BaseService<SysRole>
{
  public abstract void deleteRoleAndUserRole(SysRole paramSysRole);

  public abstract List<SysUser> findUserByRoleId(Long paramLong);
}

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.sys.service.SysRoleService
 * JD-Core Version:    0.6.0
 */