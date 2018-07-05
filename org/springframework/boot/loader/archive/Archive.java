package org.springframework.boot.loader.archive;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.jar.Manifest;

public abstract interface Archive extends Iterable<Entry>
{
  public abstract URL getUrl()
    throws MalformedURLException;

  public abstract Manifest getManifest()
    throws IOException;

  public abstract List<Archive> getNestedArchives(EntryFilter paramEntryFilter)
    throws IOException;

  public static abstract interface EntryFilter
  {
    public abstract boolean matches(Archive.Entry paramEntry);
  }

  public static abstract interface Entry
  {
    public abstract boolean isDirectory();

    public abstract String getName();
  }
}

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     org.springframework.boot.loader.archive.Archive
 * JD-Core Version:    0.6.0
 */