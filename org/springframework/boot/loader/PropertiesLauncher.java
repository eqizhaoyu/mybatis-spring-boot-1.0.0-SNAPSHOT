/*     */ package org.springframework.boot.loader;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PrintStream;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URI;
/*     */ import java.net.URL;
/*     */ import java.net.URLConnection;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Properties;
/*     */ import java.util.jar.Attributes;
/*     */ import java.util.jar.Manifest;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.springframework.boot.loader.archive.Archive;
/*     */ import org.springframework.boot.loader.archive.Archive.Entry;
/*     */ import org.springframework.boot.loader.archive.Archive.EntryFilter;
/*     */ import org.springframework.boot.loader.archive.ExplodedArchive;
/*     */ import org.springframework.boot.loader.archive.JarFileArchive;
/*     */ import org.springframework.boot.loader.util.SystemPropertyUtils;
/*     */ 
/*     */ public class PropertiesLauncher extends Launcher
/*     */ {
/*     */   private static final String DEBUG = "loader.debug";
/*     */   public static final String MAIN = "loader.main";
/*     */   public static final String PATH = "loader.path";
/*     */   public static final String HOME = "loader.home";
/*     */   public static final String ARGS = "loader.args";
/*     */   public static final String CONFIG_NAME = "loader.config.name";
/*     */   public static final String CONFIG_LOCATION = "loader.config.location";
/*     */   public static final String SET_SYSTEM_PROPERTIES = "loader.system";
/* 122 */   private static final Pattern WORD_SEPARATOR = Pattern.compile("\\W+");
/*     */   private final File home;
/* 126 */   private List<String> paths = new ArrayList();
/*     */ 
/* 128 */   private final Properties properties = new Properties();
/*     */   private Archive parent;
/*     */ 
/*     */   public PropertiesLauncher()
/*     */   {
/*     */     try
/*     */     {
/* 134 */       this.home = getHomeDirectory();
/* 135 */       initializeProperties();
/* 136 */       initializePaths();
/* 137 */       this.parent = createArchive();
/*     */     }
/*     */     catch (Exception ex) {
/* 140 */       throw new IllegalStateException(ex);
/*     */     }
/*     */   }
/*     */ 
/*     */   protected File getHomeDirectory() {
/* 145 */     return 
/* 146 */       new File(SystemPropertyUtils.resolvePlaceholders(System.getProperty("loader.home", "${user.dir}")));
/*     */   }
/*     */ 
/*     */   private void initializeProperties()
/*     */     throws Exception, IOException
/*     */   {
/* 151 */     String config = new StringBuilder().append("classpath:BOOT-INF/classes/")
/* 151 */       .append(SystemPropertyUtils.resolvePlaceholders(
/* 152 */       SystemPropertyUtils.getProperty("loader.config.name", "application")))
/* 151 */       .append(".properties").toString();
/*     */ 
/* 154 */     config = SystemPropertyUtils.resolvePlaceholders(
/* 155 */       SystemPropertyUtils.getProperty("loader.config.location", config));
/*     */ 
/* 156 */     InputStream resource = getResource(config);
/*     */     Iterator localIterator;
/* 157 */     if (resource != null) {
/* 158 */       log(new StringBuilder().append("Found: ").append(config).toString());
/*     */       try {
/* 160 */         this.properties.load(resource);
/*     */ 
/* 163 */         resource.close(); } finally { resource.close();
/*     */       }
/* 165 */       while (localIterator.hasNext()) { Object key = localIterator.next();
/* 166 */         String text = this.properties.getProperty((String)key);
/* 167 */         String value = SystemPropertyUtils.resolvePlaceholders(this.properties, text);
/*     */ 
/* 169 */         if (value != null) {
/* 170 */           this.properties.put(key, value);
/*     */         }
/*     */ 
/*     */       }
/*     */ 
/* 175 */       if (SystemPropertyUtils.resolvePlaceholders("${loader.system:false}")
/* 175 */         .equals("true"))
/*     */       {
/* 176 */         log("Adding resolved properties to System properties");
/* 177 */         for (localIterator = Collections.list(this.properties.propertyNames()).iterator(); localIterator.hasNext(); ) { Object key = localIterator.next();
/* 178 */           String value = this.properties.getProperty((String)key);
/* 179 */           System.setProperty((String)key, value); }
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 184 */       log(new StringBuilder().append("Not found: ").append(config).toString());
/*     */     }
/*     */   }
/*     */ 
/*     */   private InputStream getResource(String config) throws Exception
/*     */   {
/* 190 */     if (config.startsWith("classpath:")) {
/* 191 */       return getClasspathResource(config.substring("classpath:".length()));
/*     */     }
/* 193 */     config = stripFileUrlPrefix(config);
/* 194 */     if (isUrl(config)) {
/* 195 */       return getURLResource(config);
/*     */     }
/* 197 */     return getFileResource(config);
/*     */   }
/*     */ 
/*     */   private String stripFileUrlPrefix(String config) {
/* 201 */     if (config.startsWith("file:")) {
/* 202 */       config = config.substring("file:".length());
/* 203 */       if (config.startsWith("//")) {
/* 204 */         config = config.substring(2);
/*     */       }
/*     */     }
/* 207 */     return config;
/*     */   }
/*     */ 
/*     */   private boolean isUrl(String config) {
/* 211 */     return config.contains("://");
/*     */   }
/*     */ 
/*     */   private InputStream getClasspathResource(String config) {
/* 215 */     while (config.startsWith("/")) {
/* 216 */       config = config.substring(1);
/*     */     }
/* 218 */     config = new StringBuilder().append("/").append(config).toString();
/* 219 */     log(new StringBuilder().append("Trying classpath: ").append(config).toString());
/* 220 */     return getClass().getResourceAsStream(config);
/*     */   }
/*     */ 
/*     */   private InputStream getFileResource(String config) throws Exception {
/* 224 */     File file = new File(config);
/* 225 */     log(new StringBuilder().append("Trying file: ").append(config).toString());
/* 226 */     if (file.canRead()) {
/* 227 */       return new FileInputStream(file);
/*     */     }
/* 229 */     return null;
/*     */   }
/*     */ 
/*     */   private InputStream getURLResource(String config) throws Exception {
/* 233 */     URL url = new URL(config);
/* 234 */     if (exists(url)) {
/* 235 */       URLConnection con = url.openConnection();
/*     */       try {
/* 237 */         return con.getInputStream();
/*     */       }
/*     */       catch (IOException ex)
/*     */       {
/* 241 */         if ((con instanceof HttpURLConnection)) {
/* 242 */           ((HttpURLConnection)con).disconnect();
/*     */         }
/* 244 */         throw ex;
/*     */       }
/*     */     }
/* 247 */     return null;
/*     */   }
/*     */ 
/*     */   private boolean exists(URL url) throws IOException
/*     */   {
/* 252 */     URLConnection connection = url.openConnection();
/*     */     try {
/* 254 */       connection.setUseCaches(connection
/* 255 */         .getClass().getSimpleName().startsWith("JNLP"));
/* 256 */       if ((connection instanceof HttpURLConnection)) {
/* 257 */         httpConnection = (HttpURLConnection)connection;
/* 258 */         httpConnection.setRequestMethod("HEAD");
/* 259 */         int responseCode = httpConnection.getResponseCode();
/*     */         int i;
/* 260 */         if (responseCode == 200) {
/* 261 */           i = 1;
/*     */           return i;
/*     */         }
/* 263 */         if (responseCode == 404) {
/* 264 */           i = 0;
/*     */           return i;
/*     */         }
/*     */       }
/* 267 */       HttpURLConnection httpConnection = connection.getContentLength() >= 0 ? 1 : 0;
/*     */       return httpConnection;
/*     */     }
/*     */     finally {
/* 270 */       if ((connection instanceof HttpURLConnection))
/* 271 */         ((HttpURLConnection)connection).disconnect(); 
/* 271 */     }throw localObject;
/*     */   }
/*     */ 
/*     */   private void initializePaths()
/*     */     throws Exception
/*     */   {
/* 277 */     String path = getProperty("loader.path");
/* 278 */     if (path != null) {
/* 279 */       this.paths = parsePathsProperty(path);
/*     */     }
/* 281 */     log(new StringBuilder().append("Nested archive paths: ").append(this.paths).toString());
/*     */   }
/*     */ 
/*     */   private List<String> parsePathsProperty(String commaSeparatedPaths) {
/* 285 */     List paths = new ArrayList();
/* 286 */     for (String path : commaSeparatedPaths.split(",")) {
/* 287 */       path = cleanupPath(path);
/*     */ 
/* 290 */       if (!path.equals("")) {
/* 291 */         paths.add(path);
/*     */       }
/*     */     }
/* 294 */     if (paths.isEmpty()) {
/* 295 */       paths.add("lib");
/*     */     }
/* 297 */     return paths;
/*     */   }
/*     */ 
/*     */   protected String[] getArgs(String[] args) throws Exception {
/* 301 */     String loaderArgs = getProperty("loader.args");
/* 302 */     if (loaderArgs != null) {
/* 303 */       String[] defaultArgs = loaderArgs.split("\\s+");
/* 304 */       String[] additionalArgs = args;
/* 305 */       args = new String[defaultArgs.length + additionalArgs.length];
/* 306 */       System.arraycopy(defaultArgs, 0, args, 0, defaultArgs.length);
/* 307 */       System.arraycopy(additionalArgs, 0, args, defaultArgs.length, additionalArgs.length);
/*     */     }
/*     */ 
/* 310 */     return args;
/*     */   }
/*     */ 
/*     */   protected String getMainClass() throws Exception
/*     */   {
/* 315 */     String mainClass = getProperty("loader.main", "Start-Class");
/* 316 */     if (mainClass == null) {
/* 317 */       throw new IllegalStateException("No 'loader.main' or 'Start-Class' specified");
/*     */     }
/*     */ 
/* 320 */     return mainClass;
/*     */   }
/*     */ 
/*     */   protected ClassLoader createClassLoader(List<Archive> archives) throws Exception
/*     */   {
/* 325 */     ClassLoader loader = super.createClassLoader(archives);
/* 326 */     String customLoaderClassName = getProperty("loader.classLoader");
/* 327 */     if (customLoaderClassName != null) {
/* 328 */       loader = wrapWithCustomClassLoader(loader, customLoaderClassName);
/* 329 */       log(new StringBuilder().append("Using custom class loader: ").append(customLoaderClassName).toString());
/*     */     }
/* 331 */     return loader;
/*     */   }
/*     */ 
/*     */   private ClassLoader wrapWithCustomClassLoader(ClassLoader parent, String loaderClassName)
/*     */     throws Exception
/*     */   {
/* 338 */     Class loaderClass = Class.forName(loaderClassName, true, parent);
/*     */     try
/*     */     {
/* 341 */       return (ClassLoader)loaderClass.getConstructor(new Class[] { ClassLoader.class }).newInstance(new Object[] { parent });
/*     */     }
/*     */     catch (NoSuchMethodException localNoSuchMethodException1)
/*     */     {
/*     */       try
/*     */       {
/* 347 */         return 
/* 348 */           (ClassLoader)loaderClass.getConstructor(new Class[] { [Ljava.net.URL.class, ClassLoader.class })
/* 348 */           .newInstance(new Object[] { new URL[0], parent });
/*     */       }
/*     */       catch (NoSuchMethodException localNoSuchMethodException1)
/*     */       {
/*     */       }
/*     */     }
/* 353 */     return (ClassLoader)loaderClass.newInstance();
/*     */   }
/*     */ 
/*     */   private String getProperty(String propertyKey) throws Exception {
/* 357 */     return getProperty(propertyKey, null);
/*     */   }
/*     */ 
/*     */   private String getProperty(String propertyKey, String manifestKey) throws Exception {
/* 361 */     if (manifestKey == null) {
/* 362 */       manifestKey = propertyKey.replace('.', '-');
/* 363 */       manifestKey = toCamelCase(manifestKey);
/*     */     }
/* 365 */     String property = SystemPropertyUtils.getProperty(propertyKey);
/* 366 */     if (property != null) {
/* 367 */       String value = SystemPropertyUtils.resolvePlaceholders(property);
/* 368 */       log(new StringBuilder().append("Property '").append(propertyKey).append("' from environment: ").append(value).toString());
/* 369 */       return value;
/*     */     }
/* 371 */     if (this.properties.containsKey(propertyKey))
/*     */     {
/* 373 */       String value = SystemPropertyUtils.resolvePlaceholders(this.properties
/* 373 */         .getProperty(propertyKey));
/*     */ 
/* 374 */       log(new StringBuilder().append("Property '").append(propertyKey).append("' from properties: ").append(value).toString());
/* 375 */       return value;
/*     */     }
/*     */     try
/*     */     {
/* 379 */       Manifest manifest = new ExplodedArchive(this.home, false).getManifest();
/* 380 */       if (manifest != null) {
/* 381 */         String value = manifest.getMainAttributes().getValue(manifestKey);
/* 382 */         log(new StringBuilder().append("Property '").append(manifestKey).append("' from home directory manifest: ").append(value).toString());
/*     */ 
/* 384 */         return value;
/*     */       }
/*     */     }
/*     */     catch (IllegalStateException localIllegalStateException)
/*     */     {
/*     */     }
/*     */ 
/* 391 */     Manifest manifest = createArchive().getManifest();
/* 392 */     if (manifest != null) {
/* 393 */       String value = manifest.getMainAttributes().getValue(manifestKey);
/* 394 */       if (value != null) {
/* 395 */         log(new StringBuilder().append("Property '").append(manifestKey).append("' from archive manifest: ").append(value).toString());
/* 396 */         return value;
/*     */       }
/*     */     }
/* 399 */     return null;
/*     */   }
/*     */ 
/*     */   protected List<Archive> getClassPathArchives() throws Exception
/*     */   {
/* 404 */     List lib = new ArrayList();
/* 405 */     for (String path : this.paths) {
/* 406 */       for (Archive archive : getClassPathArchives(path)) {
/* 407 */         if ((archive instanceof ExplodedArchive))
/*     */         {
/* 409 */           List nested = new ArrayList(archive
/* 409 */             .getNestedArchives(new ArchiveEntryFilter(null)));
/*     */ 
/* 410 */           nested.add(0, archive);
/* 411 */           lib.addAll(nested);
/*     */         }
/*     */         else {
/* 414 */           lib.add(archive);
/*     */         }
/*     */       }
/*     */     }
/* 418 */     addNestedEntries(lib);
/* 419 */     return lib;
/*     */   }
/*     */ 
/*     */   private List<Archive> getClassPathArchives(String path) throws Exception {
/* 423 */     String root = cleanupPath(stripFileUrlPrefix(path));
/* 424 */     List lib = new ArrayList();
/* 425 */     File file = new File(root);
/* 426 */     if (!isAbsolutePath(root)) {
/* 427 */       file = new File(this.home, root);
/*     */     }
/* 429 */     if (file.isDirectory()) {
/* 430 */       log(new StringBuilder().append("Adding classpath entries from ").append(file).toString());
/* 431 */       Archive archive = new ExplodedArchive(file, false);
/* 432 */       lib.add(archive);
/*     */     }
/* 434 */     Archive archive = getArchive(file);
/* 435 */     if (archive != null) {
/* 436 */       log(new StringBuilder().append("Adding classpath entries from archive ").append(archive.getUrl()).append(root).toString());
/* 437 */       lib.add(archive);
/*     */     }
/* 439 */     Archive nested = getNestedArchive(root);
/* 440 */     if (nested != null) {
/* 441 */       log(new StringBuilder().append("Adding classpath entries from nested ").append(nested.getUrl()).append(root).toString());
/* 442 */       lib.add(nested);
/*     */     }
/* 444 */     return lib;
/*     */   }
/*     */ 
/*     */   private boolean isAbsolutePath(String root)
/*     */   {
/* 449 */     return (root.contains(":")) || (root.startsWith("/"));
/*     */   }
/*     */ 
/*     */   private Archive getArchive(File file) throws IOException {
/* 453 */     String name = file.getName().toLowerCase();
/* 454 */     if ((name.endsWith(".jar")) || (name.endsWith(".zip"))) {
/* 455 */       return new JarFileArchive(file);
/*     */     }
/* 457 */     return null;
/*     */   }
/*     */ 
/*     */   private Archive getNestedArchive(String root) throws Exception {
/* 461 */     if ((root.startsWith("/")) || 
/* 462 */       (this.parent
/* 462 */       .getUrl().equals(this.home.toURI().toURL())))
/*     */     {
/* 464 */       return null;
/*     */     }
/* 466 */     Archive.EntryFilter filter = new PrefixMatchingArchiveFilter(root, null);
/* 467 */     if (this.parent.getNestedArchives(filter).isEmpty()) {
/* 468 */       return null;
/*     */     }
/*     */ 
/* 472 */     return new FilteredArchive(this.parent, filter);
/*     */   }
/*     */ 
/*     */   private void addNestedEntries(List<Archive> lib)
/*     */   {
/*     */     try
/*     */     {
/* 480 */       lib.addAll(this.parent.getNestedArchives(new Archive.EntryFilter()
/*     */       {
/*     */         public boolean matches(Archive.Entry entry)
/*     */         {
/* 484 */           if (entry.isDirectory()) {
/* 485 */             return entry.getName().startsWith("BOOT-INF/classes/");
/*     */           }
/* 487 */           return entry.getName().startsWith("BOOT-INF/lib/");
/*     */         }
/*     */       }));
/*     */     }
/*     */     catch (IOException localIOException)
/*     */     {
/*     */     }
/*     */   }
/*     */ 
/*     */   private String cleanupPath(String path) {
/* 498 */     path = path.trim();
/*     */ 
/* 500 */     if (path.startsWith("./")) {
/* 501 */       path = path.substring(2);
/*     */     }
/* 503 */     if ((path.toLowerCase().endsWith(".jar")) || (path.toLowerCase().endsWith(".zip"))) {
/* 504 */       return path;
/*     */     }
/* 506 */     if (path.endsWith("/*")) {
/* 507 */       path = path.substring(0, path.length() - 1);
/*     */     }
/* 511 */     else if ((!path.endsWith("/")) && (!path.equals("."))) {
/* 512 */       path = new StringBuilder().append(path).append("/").toString();
/*     */     }
/*     */ 
/* 515 */     return path;
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) throws Exception {
/* 519 */     PropertiesLauncher launcher = new PropertiesLauncher();
/* 520 */     args = launcher.getArgs(args);
/* 521 */     launcher.launch(args);
/*     */   }
/*     */ 
/*     */   public static String toCamelCase(CharSequence string) {
/* 525 */     if (string == null) {
/* 526 */       return null;
/*     */     }
/* 528 */     StringBuilder builder = new StringBuilder();
/* 529 */     Matcher matcher = WORD_SEPARATOR.matcher(string);
/* 530 */     int pos = 0;
/* 531 */     while (matcher.find()) {
/* 532 */       builder.append(capitalize(string.subSequence(pos, matcher.end()).toString()));
/* 533 */       pos = matcher.end();
/*     */     }
/* 535 */     builder.append(capitalize(string.subSequence(pos, string.length()).toString()));
/* 536 */     return builder.toString();
/*     */   }
/*     */ 
/*     */   private static String capitalize(String str) {
/* 540 */     return new StringBuilder().append(Character.toUpperCase(str.charAt(0))).append(str.substring(1)).toString();
/*     */   }
/*     */ 
/*     */   private void log(String message) {
/* 544 */     if (Boolean.getBoolean("loader.debug"))
/*     */     {
/* 547 */       System.out.println(message);
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class FilteredArchive
/*     */     implements Archive
/*     */   {
/*     */     private final Archive parent;
/*     */     private final Archive.EntryFilter filter;
/*     */ 
/*     */     FilteredArchive(Archive parent, Archive.EntryFilter filter)
/*     */     {
/* 599 */       this.parent = parent;
/* 600 */       this.filter = filter;
/*     */     }
/*     */ 
/*     */     public URL getUrl() throws MalformedURLException
/*     */     {
/* 605 */       return this.parent.getUrl();
/*     */     }
/*     */ 
/*     */     public Manifest getManifest() throws IOException
/*     */     {
/* 610 */       return this.parent.getManifest();
/*     */     }
/*     */ 
/*     */     public Iterator<Archive.Entry> iterator()
/*     */     {
/* 615 */       throw new UnsupportedOperationException();
/*     */     }
/*     */ 
/*     */     public List<Archive> getNestedArchives(Archive.EntryFilter filter)
/*     */       throws IOException
/*     */     {
/* 621 */       return this.parent.getNestedArchives(new Archive.EntryFilter(filter)
/*     */       {
/*     */         public boolean matches(Archive.Entry entry) {
/* 624 */           return (PropertiesLauncher.FilteredArchive.this.filter.matches(entry)) && 
/* 625 */             (this.val$filter
/* 625 */             .matches(entry));
/*     */         }
/*     */       });
/*     */     }
/*     */   }
/*     */ 
/*     */   private static final class ArchiveEntryFilter
/*     */     implements Archive.EntryFilter
/*     */   {
/*     */     private static final String DOT_JAR = ".jar";
/*     */     private static final String DOT_ZIP = ".zip";
/*     */ 
/*     */     public boolean matches(Archive.Entry entry)
/*     */     {
/* 584 */       return (entry.getName().endsWith(".jar")) || (entry.getName().endsWith(".zip"));
/*     */     }
/*     */   }
/*     */ 
/*     */   private static final class PrefixMatchingArchiveFilter
/*     */     implements Archive.EntryFilter
/*     */   {
/*     */     private final String prefix;
/* 559 */     private final PropertiesLauncher.ArchiveEntryFilter filter = new PropertiesLauncher.ArchiveEntryFilter(null);
/*     */ 
/*     */     private PrefixMatchingArchiveFilter(String prefix) {
/* 562 */       this.prefix = prefix;
/*     */     }
/*     */ 
/*     */     public boolean matches(Archive.Entry entry)
/*     */     {
/* 567 */       return (entry.getName().startsWith(this.prefix)) && (this.filter.matches(entry));
/*     */     }
/*     */   }
/*     */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     org.springframework.boot.loader.PropertiesLauncher
 * JD-Core Version:    0.6.0
 */