package org.springframework.boot.loader.jar;

import org.springframework.boot.loader.data.RandomAccessData;

abstract interface CentralDirectoryVisitor
{
  public abstract void visitStart(CentralDirectoryEndRecord paramCentralDirectoryEndRecord, RandomAccessData paramRandomAccessData);

  public abstract void visitFileHeader(CentralDirectoryFileHeader paramCentralDirectoryFileHeader, int paramInt);

  public abstract void visitEnd();
}

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     org.springframework.boot.loader.jar.CentralDirectoryVisitor
 * JD-Core Version:    0.6.0
 */