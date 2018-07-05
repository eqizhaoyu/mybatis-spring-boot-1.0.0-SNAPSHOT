package tk.mybatis.springboot.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.springboot.base.ServiceMybatis;
import tk.mybatis.springboot.sys.mapper.SysAreaMapper;
import tk.mybatis.springboot.sys.model.SysArea;

@Service
public class SysAreaServiceImpl extends ServiceMybatis<SysArea>
  implements SysAreaService
{

  @Autowired
  private SysAreaMapper SysAreaMapper;
}

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.sys.service.SysAreaServiceImpl
 * JD-Core Version:    0.6.0
 */