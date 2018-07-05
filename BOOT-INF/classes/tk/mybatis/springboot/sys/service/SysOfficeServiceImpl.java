package tk.mybatis.springboot.sys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.springboot.base.ServiceMybatis;
import tk.mybatis.springboot.sys.mapper.SysOfficeMapper;
import tk.mybatis.springboot.sys.model.SysOffice;

@Service
public class SysOfficeServiceImpl extends ServiceMybatis<SysOffice>
  implements SysOfficeService
{

  @Autowired
  private SysOfficeMapper SysOfficeMapper;
}

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.sys.service.SysOfficeServiceImpl
 * JD-Core Version:    0.6.0
 */