package tk.mybatis.springboot.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.springboot.base.ServiceMybatis;
import tk.mybatis.springboot.sys.mapper.SysDictMapper;
import tk.mybatis.springboot.sys.model.SysDict;

@Service
public class SysDictServiceImpl extends ServiceMybatis<SysDict>
  implements SysDictService
{

  @Autowired
  private SysDictMapper SysDictMapper;
}

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.sys.service.SysDictServiceImpl
 * JD-Core Version:    0.6.0
 */