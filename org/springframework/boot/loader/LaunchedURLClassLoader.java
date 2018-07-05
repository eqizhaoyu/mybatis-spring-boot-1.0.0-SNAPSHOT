/*     */ package org.springframework.boot.loader;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.net.JarURLConnection;
/*     */ import java.net.URL;
/*     */ import java.net.URLClassLoader;
/*     */ import java.net.URLConnection;
/*     */ import java.security.AccessController;
/*     */ import java.security.PrivilegedActionException;
/*     */ import java.security.PrivilegedExceptionAction;
/*     */ import java.util.Enumeration;
/*     */ import org.springframework.boot.loader.jar.Handler;
/*     */ import org.springframework.lang.UsesJava7;
/*     */ 
/*     */ public class LaunchedURLClassLoader extends URLClassLoader
/*     */ {
/*     */   public LaunchedURLClassLoader(URL[] urls, ClassLoader parent)
/*     */   {
/*  51 */     super(urls, parent);
/*     */   }
/*     */ 
/*     */   public URL findResource(String name)
/*     */   {
/*  56 */     Handler.setUseFastConnectionExceptions(true);
/*     */     try {
/*  58 */       URL localURL = super.findResource(name);
/*     */       return localURL;
/*     */     }
/*     */     finally {
/*  61 */       Handler.setUseFastConnectionExceptions(false); } throw localObject;
/*     */   }
/*     */ 
/*     */   public Enumeration<URL> findResources(String name)
/*     */     throws IOException
/*     */   {
/*  67 */     Handler.setUseFastConnectionExceptions(true);
/*     */     try {
/*  69 */       Enumeration localEnumeration = super.findResources(name);
/*     */       return localEnumeration;
/*     */     }
/*     */     finally {
/*  72 */       Handler.setUseFastConnectionExceptions(false); } throw localObject;
/*     */   }
/*     */ 
/*     */   protected Class<?> loadClass(String name, boolean resolve)
/*     */     throws ClassNotFoundException
/*     */   {
/*  79 */     Handler.setUseFastConnectionExceptions(true);
/*     */     try {
/*     */       try {
/*  82 */         definePackageIfNecessary(name);
/*     */       }
/*     */       catch (IllegalArgumentException ex)
/*     */       {
/*  86 */         if (getPackage(name) == null)
/*     */         {
/*  90 */           throw new AssertionError("Package " + name + " has already been defined but it could not be found");
/*     */         }
/*     */       }
/*     */ 
/*  94 */       ex = super.loadClass(name, resolve);
/*     */       return ex;
/*     */     }
/*     */     finally {
/*  97 */       Handler.setUseFastConnectionExceptions(false); } throw localObject;
/*     */   }
/*     */ 
/*     */   private void definePackageIfNecessary(String className)
/*     */   {
/* 108 */     int lastDot = className.lastIndexOf('.');
/* 109 */     if (lastDot >= 0) {
/* 110 */       String packageName = className.substring(0, lastDot);
/* 111 */       if (getPackage(packageName) == null)
/*     */         try {
/* 113 */           definePackage(className, packageName);
/*     */         }
/*     */         catch (IllegalArgumentException ex)
/*     */         {
/* 117 */           if (getPackage(packageName) == null)
/*     */           {
/* 121 */             throw new AssertionError("Package " + packageName + " has already been defined but it could not be found");
/*     */           }
/*     */         }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void definePackage(String className, String packageName)
/*     */   {
/*     */     try
/*     */     {
/* 132 */       AccessController.doPrivileged(new PrivilegedExceptionAction(packageName, className)
/*     */       {
/*     */         public Object run() throws ClassNotFoundException {
/* 135 */           String packageEntryName = this.val$packageName.replace('.', '/') + "/";
/* 136 */           String classEntryName = this.val$className.replace('.', '/') + ".class";
/* 137 */           for (URL url : LaunchedURLClassLoader.this.getURLs()) {
/*     */             try {
/* 139 */               URLConnection connection = url.openConnection();
/* 140 */               if ((connection instanceof JarURLConnection))
/*     */               {
/* 142 */                 java.util.jar.JarFile jarFile = ((JarURLConnection)connection)
/* 142 */                   .getJarFile();
/* 143 */                 if ((jarFile.getEntry(classEntryName) != null) && 
/* 144 */                   (jarFile
/* 144 */                   .getEntry(packageEntryName) != null) && 
/* 145 */                   (jarFile
/* 145 */                   .getManifest() != null)) {
/* 146 */                   LaunchedURLClassLoader.this.definePackage(this.val$packageName, jarFile.getManifest(), url);
/*     */ 
/* 148 */                   return null;
/*     */                 }
/*     */               }
/*     */             }
/*     */             catch (IOException localIOException)
/*     */             {
/*     */             }
/*     */           }
/* 156 */           return null;
/*     */         }
/*     */       }
/*     */       , AccessController.getContext());
/*     */     }
/*     */     catch (PrivilegedActionException localPrivilegedActionException)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   public void clearCache()
/*     */   {
/* 169 */     for (URL url : getURLs())
/*     */       try {
/* 171 */         URLConnection connection = url.openConnection();
/* 172 */         if ((connection instanceof JarURLConnection))
/* 173 */           clearCache(connection);
/*     */       }
/*     */       catch (IOException localIOException)
/*     */       {
/*     */       }
/*     */   }
/*     */ 
/*     */   private void clearCache(URLConnection connection)
/*     */     throws IOException
/*     */   {
/* 184 */     Object jarFile = ((JarURLConnection)connection).getJarFile();
/* 185 */     if ((jarFile instanceof org.springframework.boot.loader.jar.JarFile))
/* 186 */       ((org.springframework.boot.loader.jar.JarFile)jarFile).clearCache();
/*     */   }
/*     */ 
/*     */   @UsesJava7
/*     */   private static void performParallelCapableRegistration() {
/*     */     try {
/* 193 */       ClassLoader.registerAsParallelCapable();
/*     */     }
/*     */     catch (NoSuchMethodError localNoSuchMethodError)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  42 */     performParallelCapableRegistration();
/*     */   }
/*     */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     org.springframework.boot.loader.LaunchedURLClassLoader
 * JD-Core Version:    0.6.0
 */