package org.springframework.boot.loader.jar;

abstract interface FileHeader
{
  public abstract boolean hasName(String paramString1, String paramString2);

  public abstract long getLocalHeaderOffset();

  public abstract long getCompressedSize();

  public abstract long getSize();

  public abstract int getMethod();
}

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     org.springframework.boot.loader.jar.FileHeader
 * JD-Core Version:    0.6.0
 */