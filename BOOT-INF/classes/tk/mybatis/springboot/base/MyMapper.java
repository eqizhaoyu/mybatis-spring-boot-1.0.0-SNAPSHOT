package tk.mybatis.springboot.base;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public abstract interface MyMapper<T> extends Mapper<T>, MySqlMapper<T>
{
}

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.base.MyMapper
 * JD-Core Version:    0.6.0
 */