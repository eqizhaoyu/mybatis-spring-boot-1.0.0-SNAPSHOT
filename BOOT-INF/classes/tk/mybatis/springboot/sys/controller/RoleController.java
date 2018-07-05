/*     */ package tk.mybatis.springboot.sys.controller;
/*     */ 
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.validation.Valid;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ import tk.mybatis.springboot.sys.model.SysRole;
/*     */ import tk.mybatis.springboot.sys.model.SysUser;
/*     */ import tk.mybatis.springboot.sys.model.SysUserRole;
/*     */ import tk.mybatis.springboot.sys.service.SysResService;
/*     */ import tk.mybatis.springboot.sys.service.SysRoleService;
/*     */ import tk.mybatis.springboot.sys.service.SysUserRoleService;
/*     */ import tk.mybatis.springboot.sys.service.SysUserService;
/*     */ import tk.mybatis.springboot.util.Menu;
/*     */ import tk.mybatis.springboot.util.StringUtil;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/admin/role"})
/*     */ public class RoleController
/*     */ {
/*     */ 
/*     */   @Autowired
/*     */   private SysRoleService SysRoleService;
/*     */ 
/*     */   @Autowired
/*     */   private SysUserService SysUserService;
/*     */ 
/*     */   @Autowired
/*     */   private SysUserRoleService SysUserRoleService;
/*     */ 
/*     */   @Autowired
/*     */   private SysResService SysResService;
/*     */ 
/*     */   @RequestMapping({"/index"})
/*     */   @Menu(type="admin", subtype="role")
/*     */   public ModelAndView index(ModelMap map, HttpServletRequest request, @Valid Long role)
/*     */   {
/*  43 */     List roleList = this.SysRoleService.select(new SysRole());
/*  44 */     map.addAttribute("roleList", roleList);
/*  45 */     if (roleList.size() > 0) {
/*  46 */       SysRole roleData = null;
/*  47 */       if (!StringUtil.isBlank(role)) {
/*  48 */         for (SysRole data : roleList)
/*  49 */           if (data.getId().equals(role)) {
/*  50 */             roleData = data;
/*  51 */             map.addAttribute("roleData", data);
/*     */           }
/*     */       }
/*     */       else {
/*  55 */         roleData = (SysRole)roleList.get(0);
/*  56 */         map.addAttribute("roleData", roleData);
/*     */       }
/*     */ 
/*  59 */       if (roleData != null) {
/*  60 */         if (role == null) {
/*  61 */           map.addAttribute("userRoleList", this.SysRoleService.findUserByRoleId(roleData.getId()));
/*     */         }
/*  63 */         map.addAttribute("userRoleList", this.SysRoleService.findUserByRoleId(role));
/*     */       }
/*     */     }
/*  66 */     return new ModelAndView("admin/role/role");
/*     */   }
/*  72 */   @RequestMapping({"/add"})
/*     */   @Menu(type="admin", subtype="role")
/*     */   public ModelAndView add(ModelMap map, HttpServletRequest request) { return new ModelAndView("admin/role/add"); } 
/*     */   @RequestMapping({"/save"})
/*     */   @Menu(type="admin", subtype="role")
/*     */   public String save(HttpServletRequest request, @Valid SysRole role) {
/*  78 */     SysRole r = new SysRole();
/*  79 */     r.setName(role.getName());
/*  80 */     SysRole tempRole = (SysRole)this.SysRoleService.selectOne(r);
/*  81 */     String msg = "admin_role_save_success";
/*  82 */     if (tempRole != null)
/*  83 */       msg = "admin_role_save_exist";
/*     */     else {
/*  85 */       this.SysRoleService.insertSelective(role);
/*     */     }
/*  87 */     return "redirect:/admin/role/index.html?msg=" + msg;
/*     */   }
/*  93 */   @RequestMapping({"/seluser"})
/*     */   @Menu(type="admin", subtype="seluser", admin=true)
/*     */   public ModelAndView seluser(ModelMap map, HttpServletRequest request, @Valid Long role) { map.addAttribute("userList", this.SysUserService.select(new SysUser()));
/*  94 */     SysRole roleData = (SysRole)this.SysRoleService.selectByPrimaryKey(role);
/*     */ 
/*  96 */     map.addAttribute("role", roleData);
/*  97 */     return new ModelAndView("admin/role/seluser"); }
/*     */ 
/*     */   @RequestMapping({"/saveuser"})
/*     */   @Menu(type="admin", subtype="saveuser", admin=true)
/*     */   public String saveuser(HttpServletRequest request, @Valid Long[] users, @Valid Long role) {
/* 104 */     SysRole roleData = (SysRole)this.SysRoleService.selectByPrimaryKey(role);
/* 105 */     SysUserRole ur = new SysUserRole();
/* 106 */     ur.setRoleId(role);
/* 107 */     List userRoleList = this.SysUserRoleService.select(ur);
/*     */ 
/* 109 */     for (Long user : users) {
/* 110 */       boolean exist = false;
/* 111 */       for (SysUserRole userRole : userRoleList) {
/* 112 */         if (user.equals(userRole.getUserId())) {
/* 113 */           exist = true; continue;
/*     */         }
/*     */       }
/* 116 */       if (!exist) {
/* 117 */         SysUserRole userRole = new SysUserRole();
/* 118 */         userRole.setUserId(user);
/* 119 */         userRole.setRoleId(role);
/* 120 */         this.SysUserRoleService.insertSelective(userRole);
/*     */       }
/*     */     }
/* 123 */     return "redirect:/admin/role/index.html?role=" + role;
/*     */   }
/* 129 */   @RequestMapping({"/user/delete"})
/*     */   @Menu(type="admin", subtype="role")
/*     */   public String userroledelete(HttpServletRequest request, @Valid Long id, @Valid String role) { if (role != null) {
/* 130 */       this.SysUserRoleService.deleteByPrimaryKey(id);
/*     */     }
/* 132 */     return "redirect:/admin/role/index.html?role=" + role; } 
/*     */   @RequestMapping({"/edit"})
/*     */   @Menu(type="admin", subtype="role")
/*     */   public ModelAndView edit(ModelMap map, @Valid Long id) {
/* 138 */     ModelAndView view = new ModelAndView();
/* 139 */     view.setViewName("admin/role/edit");
/* 140 */     view.addObject("roleData", this.SysRoleService.selectByPrimaryKey(id));
/* 141 */     return view;
/*     */   }
/* 147 */   @RequestMapping({"/update"})
/*     */   @Menu(type="admin", subtype="role")
/*     */   public String update(HttpServletRequest request, @Valid SysRole role) { SysRole tempRole = (SysRole)this.SysRoleService.selectByPrimaryKey(role.getId());
/* 148 */     String msg = "admin_role_update_success";
/* 149 */     if (tempRole != null) {
/* 150 */       tempRole.setName(role.getName());
/* 151 */       this.SysRoleService.updateByPrimaryKeySelective(tempRole);
/*     */     } else {
/* 153 */       msg = "admin_role_update_not_exist";
/*     */     }
/* 155 */     return "redirect:/admin/role/index.html?msg=" + msg; } 
/*     */   @RequestMapping({"/delete"})
/*     */   @Menu(type="admin", subtype="role")
/*     */   public String delete(HttpServletRequest request, @Valid SysRole role) {
/* 161 */     String msg = "admin_role_delete";
/* 162 */     if (role != null)
/* 163 */       this.SysRoleService.deleteRoleAndUserRole(role);
/*     */     else {
/* 165 */       msg = "admin_role_not_exist";
/*     */     }
/* 167 */     return "redirect:/admin/role/index.html?msg=" + msg;
/*     */   }
/*     */ }

/* Location:           D:\BaiduNetdiskDownload\springboot_mapper3\mybatis-spring-boot-1.0.0-SNAPSHOT.jar
 * Qualified Name:     BOOT-INF.classes.tk.mybatis.springboot.sys.controller.RoleController
 * JD-Core Version:    0.6.0
 */