/*     */ package org.springframework.boot.loader.jar;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.lang.ref.SoftReference;
/*     */ import java.lang.reflect.Method;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.net.URLDecoder;
/*     */ import java.net.URLStreamHandler;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ 
/*     */ public class Handler extends URLStreamHandler
/*     */ {
/*     */   private static final String JAR_PROTOCOL = "jar:";
/*     */   private static final String FILE_PROTOCOL = "file:";
/*     */   private static final String SEPARATOR = "!/";
/*  51 */   private static final String[] FALLBACK_HANDLERS = { "sun.net.www.protocol.jar.Handler" };
/*     */   private static final Method OPEN_CONNECTION_METHOD;
/*     */   private static SoftReference<Map<File, JarFile>> rootFileCache;
/*  74 */   private final Logger logger = Logger.getLogger(getClass().getName());
/*     */   private final JarFile jarFile;
/*     */   private URLStreamHandler fallbackHandler;
/*     */ 
/*     */   public Handler()
/*     */   {
/*  81 */     this(null);
/*     */   }
/*     */ 
/*     */   public Handler(JarFile jarFile) {
/*  85 */     this.jarFile = jarFile;
/*     */   }
/*     */ 
/*     */   protected URLConnection openConnection(URL url) throws IOException
/*     */   {
/*  90 */     if (this.jarFile != null)
/*  91 */       return JarURLConnection.get(url, this.jarFile);
/*     */     try
/*     */     {
/*  94 */       return JarURLConnection.get(url, getRootJarFileFromUrl(url));
/*     */     } catch (Exception ex) {
/*     */     }
/*  97 */     return openFallbackConnection(url, ex);
/*     */   }
/*     */ 
/*     */   private URLConnection openFallbackConnection(URL url, Exception reason) throws IOException
/*     */   {
/*     */     try
/*     */     {
/* 104 */       return openConnection(getFallbackHandler(), url);
/*     */     }
/*     */     catch (Exception ex) {
/* 107 */       if ((reason instanceof IOException)) {
/* 108 */         this.logger.log(Level.FINEST, "Unable to open fallback handler", ex);
/* 109 */         throw ((IOException)reason);
/*     */       }
/* 111 */       this.logger.log(Level.WARNING, "Unable to open fallback handler", ex);
/* 112 */       if ((reason instanceof RuntimeException))
/* 113 */         throw ((RuntimeException)reason);
/*     */     }
/* 115 */     throw new IllegalStateException(reason);
/*     */   }
/*     */ 
/*     */   private URLStreamHandler getFallbackHandler()
/*     */   {
/* 120 */     if (this.fallbackHandler != null) {
/* 121 */       return this.fallbackHandler;
/*     */     }
/* 123 */     for (String handlerClassName : FALLBACK_HANDLERS) {
/*     */       try {
/* 125 */         Class handlerClass = Class.forName(handlerClassName);
/* 126 */         this.fallbackHandler = ((URLStreamHandler)handlerClass.newInstance());
/* 127 */         return this.fallbackHandler;
/*     */       }
/*     */       catch (Exception localException)
/*     */       {
/*     */       }
/*     */     }
/* 133 */     throw new IllegalStateException("Unable to find fallback handler");
/*     */   }
/*     */ 
/*     */   private URLConnection openConnection(URLStreamHandler handler, URL url) throws Exception
/*     */   {
/* 138 */     if (OPEN_CONNECTION_METHOD == null) {
/* 139 */       throw new IllegalStateException("Unable to invoke fallback open connection method");
/*     */     }
/*     */ 
/* 142 */     OPEN_CONNECTION_METHOD.setAccessible(true);
/* 143 */     return (URLConnection)OPEN_CONNECTION_METHOD.invoke(handler, new Object[] { url });
/*     */   }
/*     */ 
/*     */   protected void parseURL(URL context, String spec, int start, int limit)
/*     */   {
/* 148 */     if (spec.toLowerCase().startsWith("jar:")) {
/* 149 */       setFile(context, getFileFromSpec(spec.substring(start, limit)));
/*     */     }
/*     */     else
/* 152 */       setFile(context, getFileFromContext(context, spec.substring(start, limit)));
/*     */   }
/*     */ 
/*     */   private String getFileFromSpec(String spec)
/*     */   {
/* 157 */     int separatorIndex = spec.lastIndexOf("!/");
/* 158 */     if (separatorIndex == -1)
/* 159 */       throw new IllegalArgumentException("No !/ in spec '" + spec + "'");
/*     */     try
/*     */     {
/* 162 */       new URL(spec.substring(0, separatorIndex));
/* 163 */       return spec;
/*     */     } catch (MalformedURLException ex) {
/*     */     }
/* 166 */     throw new IllegalArgumentException("Invalid spec URL '" + spec + "'", ex);
/*     */   }
/*     */ 
/*     */   private String getFileFromContext(URL context, String spec)
/*     */   {
/* 171 */     String file = context.getFile();
/* 172 */     if (spec.startsWith("/")) {
/* 173 */       return trimToJarRoot(file) + "!/" + spec.substring(1);
/*     */     }
/* 175 */     if (file.endsWith("/")) {
/* 176 */       return file + spec;
/*     */     }
/* 178 */     int lastSlashIndex = file.lastIndexOf(47);
/* 179 */     if (lastSlashIndex == -1) {
/* 180 */       throw new IllegalArgumentException("No / found in context URL's file '" + file + "'");
/*     */     }
/*     */ 
/* 183 */     return file.substring(0, lastSlashIndex + 1) + spec;
/*     */   }
/*     */ 
/*     */   private String trimToJarRoot(String file) {
/* 187 */     int lastSeparatorIndex = file.lastIndexOf("!/");
/* 188 */     if (lastSeparatorIndex == -1) {
/* 189 */       throw new IllegalArgumentException("No !/ found in context URL's file '" + file + "'");
/*     */     }
/*     */ 
/* 192 */     return file.substring(0, lastSeparatorIndex);
/*     */   }
/*     */ 
/*     */   private void setFile(URL context, String file) {
/* 196 */     setURL(context, "jar:", null, -1, null, null, file, null, null);
/*     */   }
/*     */ 
/*     */   protected int hashCode(URL u)
/*     */   {
/* 201 */     return hashCode(u.getProtocol(), u.getFile());
/*     */   }
/*     */ 
/*     */   private int hashCode(String protocol, String file) {
/* 205 */     int result = protocol == null ? 0 : protocol.hashCode();
/* 206 */     int separatorIndex = file.indexOf("!/");
/* 207 */     if (separatorIndex == -1) {
/* 208 */       return result + file.hashCode();
/*     */     }
/* 210 */     String source = file.substring(0, separatorIndex);
/* 211 */     String entry = canonicalize(file.substring(separatorIndex + 2));
/*     */     try {
/* 213 */       result += new URL(source).hashCode();
/*     */     }
/*     */     catch (MalformedURLException ex) {
/* 216 */       result += source.hashCode();
/*     */     }
/* 218 */     result += entry.hashCode();
/* 219 */     return result;
/*     */   }
/*     */ 
/*     */   protected boolean sameFile(URL u1, URL u2)
/*     */   {
/* 224 */     if ((!u1.getProtocol().equals("jar")) || (!u2.getProtocol().equals("jar"))) {
/* 225 */       return false;
/*     */     }
/* 227 */     int separator1 = u1.getFile().indexOf("!/");
/* 228 */     int separator2 = u2.getFile().indexOf("!/");
/* 229 */     if ((separator1 == -1) || (separator2 == -1)) {
/* 230 */       return super.sameFile(u1, u2);
/*     */     }
/* 232 */     String nested1 = u1.getFile().substring(separator1 + "!/".length());
/* 233 */     String nested2 = u2.getFile().substring(separator2 + "!/".length());
/* 234 */     if (!nested1.equals(nested2)) {
/* 235 */       String canonical1 = canonicalize(nested1);
/* 236 */       String canonical2 = canonicalize(nested2);
/* 237 */       if (!canonical1.equals(canonical2)) {
/* 238 */         return false;
/*     */       }
/*     */     }
/* 241 */     String root1 = u1.getFile().substring(0, separator1);
/* 242 */     String root2 = u2.getFile().substring(0, separator2);
/*     */     try {
/* 244 */       return super.sameFile(new URL(root1), new URL(root2));
/*     */     }
/*     */     catch (MalformedURLException localMalformedURLException)
/*     */     {
/*     */     }
/* 249 */     return super.sameFile(u1, u2);
/*     */   }
/*     */ 
/*     */   private String canonicalize(String path) {
/* 253 */     return path.replace("!/", "/");
/*     */   }
/*     */ 
/*     */   public JarFile getRootJarFileFromUrl(URL url) throws IOException {
/* 257 */     String spec = url.getFile();
/* 258 */     int separatorIndex = spec.indexOf("!/");
/* 259 */     if (separatorIndex == -1) {
/* 260 */       throw new MalformedURLException("Jar URL does not contain !/ separator");
/*     */     }
/* 262 */     String name = spec.substring(0, separatorIndex);
/* 263 */     return getRootJarFile(name);
/*     */   }
/*     */ 
/*     */   private JarFile getRootJarFile(String name) throws IOException {
/*     */     try {
/* 268 */       if (!name.startsWith("file:")) {
/* 269 */         throw new IllegalStateException("Not a file URL");
/*     */       }
/* 271 */       String path = name.substring("file:".length());
/* 272 */       File file = new File(URLDecoder.decode(path, "UTF-8"));
/* 273 */       Map cache = (Map)rootFileCache.get();
/* 274 */       JarFile result = cache == null ? null : (JarFile)cache.get(file);
/* 275 */       if (result == null) {
/* 276 */         result = new JarFile(file);
/* 277 */         addToRootFileCache(file, result);
/*     */       }
/* 279 */       return result;
/*     */     } catch (Exception ex) {
/*     */     }
/* 282 */     throw new IOException("Unable to open root Jar file '" + name + "'", ex);
/*     */   }
/*     */ 
/*     */   static void addToRootFileCache(File sourceFile, JarFile jarFile)
/*     */   {
/* 292 */     Map cache = (Map)rootFileCache.get();
/* 293 */     if (cache == null) {
/* 294 */       cache = new ConcurrentHashMap();
/* 295 */       rootFileCache = new SoftReference(cache);
/*     */     }
/* 297 */     cache.put(sourceFile, jarFile);
/*     */   }
/*     */ 
/*     */   public static void setUseFastConnectionExceptions(boolean useFastConnectionExceptions)
/*     */   {
/* 308 */     JarURLConnection.setUseFastExceptions(useFastConnectionExceptions);
/*     */   }
/*     */ 
/*     */   static
/*     */   {
/*  57 */     Method method = null;
/*     */     try {
/*  59 */       method = URLStreamHandler.class.getDeclaredMethod("openConnection", new Class[] { URL.class });
/*     */     }
/*     */     catch (Exception localException)
/*     */     {
/*     */     }
/*     */ 
/*  65 */     OPEN_CONNECTION_METHOD = method;
/*     */ 
/*  71 */     rootFileCache = new SoftReference(null);
/*     */   }
/*     */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     org.springframework.boot.loader.jar.Handler
 * JD-Core Version:    0.6.0
 */