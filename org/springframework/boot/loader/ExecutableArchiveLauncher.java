/*    */ package org.springframework.boot.loader;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.jar.Attributes;
/*    */ import java.util.jar.Manifest;
/*    */ import org.springframework.boot.loader.archive.Archive;
/*    */ import org.springframework.boot.loader.archive.Archive.Entry;
/*    */ import org.springframework.boot.loader.archive.Archive.EntryFilter;
/*    */ 
/*    */ public abstract class ExecutableArchiveLauncher extends Launcher
/*    */ {
/*    */   private final Archive archive;
/*    */ 
/*    */   public ExecutableArchiveLauncher()
/*    */   {
/*    */     try
/*    */     {
/* 40 */       this.archive = createArchive();
/*    */     }
/*    */     catch (Exception ex) {
/* 43 */       throw new IllegalStateException(ex);
/*    */     }
/*    */   }
/*    */ 
/*    */   protected ExecutableArchiveLauncher(Archive archive) {
/* 48 */     this.archive = archive;
/*    */   }
/*    */ 
/*    */   protected final Archive getArchive() {
/* 52 */     return this.archive;
/*    */   }
/*    */ 
/*    */   protected String getMainClass() throws Exception
/*    */   {
/* 57 */     Manifest manifest = this.archive.getManifest();
/* 58 */     String mainClass = null;
/* 59 */     if (manifest != null) {
/* 60 */       mainClass = manifest.getMainAttributes().getValue("Start-Class");
/*    */     }
/* 62 */     if (mainClass == null) {
/* 63 */       throw new IllegalStateException("No 'Start-Class' manifest entry specified in " + this);
/*    */     }
/*    */ 
/* 66 */     return mainClass;
/*    */   }
/*    */ 
/*    */   protected List<Archive> getClassPathArchives()
/*    */     throws Exception
/*    */   {
/* 72 */     List archives = new ArrayList(this.archive
/* 72 */       .getNestedArchives(new Archive.EntryFilter()
/*    */     {
/*    */       public boolean matches(Archive.Entry entry)
/*    */       {
/* 76 */         return ExecutableArchiveLauncher.this.isNestedArchive(entry);
/*    */       }
/*    */     }));
/* 80 */     postProcessClassPathArchives(archives);
/* 81 */     return archives;
/*    */   }
/*    */ 
/*    */   protected abstract boolean isNestedArchive(Archive.Entry paramEntry);
/*    */ 
/*    */   protected void postProcessClassPathArchives(List<Archive> archives)
/*    */     throws Exception
/*    */   {
/*    */   }
/*    */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     org.springframework.boot.loader.ExecutableArchiveLauncher
 * JD-Core Version:    0.6.0
 */