package tk.mybatis.springboot.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.springboot.base.ServiceMybatis;
import tk.mybatis.springboot.cms.model.CmsSite;
import tk.mybatis.springboot.cms.service.CmsSiteService;
import tk.mybatis.springboot.sys.mapper.SysAreaMapper;

@Service
public class CmsSiteServiceImpl extends ServiceMybatis<CmsSite>
  implements CmsSiteService
{

  @Autowired
  private SysAreaMapper SysAreaMapper;
}

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.cms.service.impl.CmsSiteServiceImpl
 * JD-Core Version:    0.6.0
 */