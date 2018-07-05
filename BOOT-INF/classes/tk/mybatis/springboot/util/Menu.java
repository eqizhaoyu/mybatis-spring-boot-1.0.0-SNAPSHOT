package tk.mybatis.springboot.util;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({java.lang.annotation.ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Menu
{
  public abstract String type();

  public abstract String subtype();

  public abstract boolean access();

  public abstract boolean admin();

  public abstract String name();
}

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.util.Menu
 * JD-Core Version:    0.6.0
 */