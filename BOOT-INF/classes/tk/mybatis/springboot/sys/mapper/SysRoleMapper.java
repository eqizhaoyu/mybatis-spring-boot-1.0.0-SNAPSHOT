package tk.mybatis.springboot.sys.mapper;

import java.util.List;
import java.util.Map;
import tk.mybatis.springboot.base.MyMapper;
import tk.mybatis.springboot.sys.model.SysResource;
import tk.mybatis.springboot.sys.model.SysRole;
import tk.mybatis.springboot.sys.model.SysUser;

public abstract interface SysRoleMapper extends MyMapper<SysRole>
{
  public abstract int insertRoleOffice(SysRole paramSysRole);

  public abstract int insertRoleResource(SysRole paramSysRole);

  public abstract int insertUserRoleByRoleId(SysRole paramSysRole);

  public abstract int insertUserRoleByUserId(SysUser paramSysUser);

  public abstract int deleteRoleOfficeByRoleId(Long paramLong);

  public abstract int deleteRoleResourceByRoleId(Long paramLong);

  public abstract int deleteUserRoleByRoleId(Long paramLong);

  public abstract int deleteUserRoleByUserId(Long paramLong);

  public abstract List<Long> findOfficeIdsByRoleId(Long paramLong);

  public abstract List<Long> findResourceIdsByRoleId(Long paramLong);

  public abstract List<Long> findUserIdsByRoleId(Long paramLong);

  public abstract List<SysResource> findResourceByRoleId(Long paramLong);

  public abstract List<SysUser> findUserByRoleId(Long paramLong);

  public abstract List<SysRole> findUserRoleListByUserId(Long paramLong);

  public abstract List<SysRole> findPageInfo(Map<String, Object> paramMap);
}

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.sys.mapper.SysRoleMapper
 * JD-Core Version:    0.6.0
 */