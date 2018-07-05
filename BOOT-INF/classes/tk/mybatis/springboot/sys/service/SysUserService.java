package tk.mybatis.springboot.sys.service;

import tk.mybatis.springboot.base.BaseService;
import tk.mybatis.springboot.sys.model.SysUser;

public abstract interface SysUserService extends BaseService<SysUser>
{
  public abstract SysUser checkUser(String paramString1, String paramString2);
}

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.sys.service.SysUserService
 * JD-Core Version:    0.6.0
 */