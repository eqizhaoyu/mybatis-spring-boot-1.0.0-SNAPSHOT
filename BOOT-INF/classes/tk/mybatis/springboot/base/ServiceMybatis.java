/*     */ package tk.mybatis.springboot.base;
/*     */ 
/*     */ import com.github.pagehelper.PageHelper;
/*     */ import com.github.pagehelper.PageInfo;
/*     */ import java.util.List;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import tk.mybatis.mapper.entity.Example;
/*     */ import tk.mybatis.mapper.entity.Example.Criteria;
/*     */ 
/*     */ public class ServiceMybatis<T extends BaseEntity>
/*     */   implements BaseService<T>
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   protected MyMapper<T> mapper;
/*     */ 
/*     */   public T selectOne(T record)
/*     */   {
/*  23 */     if ((this.mapper.select(record) != null) && (this.mapper.select(record).size() > 0)) {
/*  24 */       return (BaseEntity)this.mapper.select(record).get(0);
/*     */     }
/*  26 */     return null;
/*     */   }
/*     */ 
/*     */   public List<T> select(T record)
/*     */   {
/*  35 */     return this.mapper.select(record);
/*     */   }
/*     */ 
/*     */   public List<T> select(T record, String orderSqlStr) {
/*  39 */     Example example = new Example(record.getClass(), false);
/*  40 */     Example.Criteria criteria = example.createCriteria();
/*     */ 
/*  46 */     example.setOrderByClause(orderSqlStr);
/*  47 */     return this.mapper.selectByExample(example);
/*     */   }
/*     */ 
/*     */   public int selectCount(T record)
/*     */   {
/*  56 */     return this.mapper.selectCount(record);
/*     */   }
/*     */ 
/*     */   public T selectByPrimaryKey(Object key)
/*     */   {
/*  65 */     return (BaseEntity)this.mapper.selectByPrimaryKey(key);
/*     */   }
/*     */ 
/*     */   public int insertSelective(T record)
/*     */   {
/*  78 */     return this.mapper.insertSelective(record);
/*     */   }
/*     */ 
/*     */   public int delete(T key)
/*     */   {
/*  87 */     return this.mapper.delete(key);
/*     */   }
/*     */ 
/*     */   public int deleteByPrimaryKey(Object key)
/*     */   {
/*  96 */     return this.mapper.deleteByPrimaryKey(key);
/*     */   }
/*     */ 
/*     */   public int updateByPrimaryKeySelective(T record)
/*     */   {
/* 108 */     return this.mapper.updateByPrimaryKeySelective(record);
/*     */   }
/*     */ 
/*     */   public PageInfo<T> selectPage(int pageNum, int pageSize, T record)
/*     */   {
/* 123 */     PageHelper.startPage(pageNum, pageSize);
/* 124 */     return new PageInfo(this.mapper.select(record));
/*     */   }
/*     */ 
/*     */   public PageInfo<T> selectPage(int pageNum, int pageSize, T record, String orderSqlStr)
/*     */   {
/* 136 */     Example example = new Example(record.getClass(), false);
/* 137 */     Example.Criteria criteria = example.createCriteria();
/*     */ 
/* 145 */     example.setOrderByClause(orderSqlStr);
/* 146 */     PageHelper.startPage(pageNum, pageSize);
/* 147 */     List list = this.mapper.selectByExample(example);
/* 148 */     return new PageInfo(list);
/*     */   }
/*     */ 
/*     */   public int insertList(List<T> list) {
/* 152 */     return this.mapper.insertList(list);
/*     */   }
/*     */ 
/*     */   public int insertOrUpdate(T record)
/*     */   {
/* 163 */     int count = 0;
/* 164 */     if (record.getId() == null)
/* 165 */       count = insertSelective(record);
/*     */     else {
/* 167 */       count = updateByPrimaryKeySelective(record);
/*     */     }
/* 169 */     return count;
/*     */   }
/*     */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.base.ServiceMybatis
 * JD-Core Version:    0.6.0
 */