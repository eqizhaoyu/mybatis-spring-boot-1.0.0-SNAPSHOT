package tk.mybatis.springboot.base;

import com.github.pagehelper.PageInfo;
import java.util.List;

public abstract interface BaseService<T extends BaseEntity>
{
  public abstract List<T> select(T paramT);

  public abstract List<T> select(T paramT, String paramString);

  public abstract int selectCount(T paramT);

  public abstract T selectByPrimaryKey(Object paramObject);

  public abstract T selectOne(T paramT);

  public abstract int insertSelective(T paramT);

  public abstract int insertOrUpdate(T paramT);

  public abstract int delete(T paramT);

  public abstract int deleteByPrimaryKey(Object paramObject);

  public abstract int updateByPrimaryKeySelective(T paramT);

  public abstract int insertList(List<T> paramList);

  public abstract PageInfo<T> selectPage(int paramInt1, int paramInt2, T paramT);

  public abstract PageInfo<T> selectPage(int paramInt1, int paramInt2, T paramT, String paramString);
}

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.base.BaseService
 * JD-Core Version:    0.6.0
 */