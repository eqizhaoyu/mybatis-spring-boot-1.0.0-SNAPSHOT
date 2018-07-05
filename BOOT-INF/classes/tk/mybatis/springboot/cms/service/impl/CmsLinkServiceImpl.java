package tk.mybatis.springboot.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.springboot.base.ServiceMybatis;
import tk.mybatis.springboot.cms.model.CmsLink;
import tk.mybatis.springboot.cms.service.CmsLinkService;
import tk.mybatis.springboot.sys.mapper.SysAreaMapper;

@Service
public class CmsLinkServiceImpl extends ServiceMybatis<CmsLink>
  implements CmsLinkService
{

  @Autowired
  private SysAreaMapper SysAreaMapper;
}

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.cms.service.impl.CmsLinkServiceImpl
 * JD-Core Version:    0.6.0
 */