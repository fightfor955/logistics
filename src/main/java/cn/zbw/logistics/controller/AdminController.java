package cn.zbw.logistics.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.security.auth.login.LoginContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.zbw.logistics.pojo.MessageObject;
import cn.zbw.logistics.pojo.Order;
import cn.zbw.logistics.pojo.Role;
import cn.zbw.logistics.pojo.RoleExample;
import cn.zbw.logistics.pojo.User;
import cn.zbw.logistics.pojo.UserExample;
import cn.zbw.logistics.pojo.UserExample.Criteria;
import cn.zbw.logistics.service.RoleService;
import cn.zbw.logistics.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	//认证shiro失败后跳转的页面

		@RequestMapping("/login")
		public String login(HttpServletRequest req,Model m) {
			//获取认证失败的错误信息，在shiro框架的formAuthenticationFilter过滤器中共享
			String shiroLoginFailure = (String) req.getAttribute("shiroLoginFailure");
			System.out.println(shiroLoginFailure);
			if(shiroLoginFailure!=null) {
				if(UnknownAccountException.class.getName().equals(shiroLoginFailure)) {
					System.out.println("执行");
					m.addAttribute("errorMsg", "账号不存在");
				}else if(IncorrectCredentialsException.class.getName().equals(shiroLoginFailure)){
					m.addAttribute("errorMsg", "密码错误");
				}
			}
			return "forward:/login.jsp";
		}
	
	@RequestMapping("/admin-list")
	@RequiresPermissions("admin:adminPage")
	public String adminPage() {
		return "admin-list";
	}
	
	@RequestMapping("delete")
	@RequiresPermissions("admin:delete")
	@ResponseBody
	public MessageObject delete(long userId) {
		int row = userService.deleteByPrimaryKey(userId);
		System.out.println(userId);
		MessageObject messageObject=null;
		if(row ==1) {
			messageObject = new MessageObject(1, "删除成功");
		}else {
			messageObject = new MessageObject(0,"删除管理员失败");
		}
		return messageObject;
	}
	
	@RequestMapping("/list")
	@RequiresPermissions("admin:list")
	@ResponseBody
	public PageInfo pageInfoList(@RequestParam(defaultValue = "5")int pageNum,@RequestParam(defaultValue = "10")int pageSize,String keyword){
		PageHelper.startPage(pageNum, pageSize);
		
		UserExample example = new UserExample();
		boolean isNum = keyword.matches("[0-9]+"); 
		if(isNum) {
			//username模糊查询
			Criteria criteria = example.createCriteria();
			criteria.andUsernameLike("%"+keyword+"%");
			//真实姓名模糊查询
			Criteria criteria2 = example.createCriteria();
			criteria2.andRealnameLike("%"+keyword+"%");
			//角色
			Criteria criteria3 = example.createCriteria();
			criteria3.andRoleNameLike("%"+keyword+"%");
			Criteria criteria4 = example.createCriteria();
			criteria4.andUserIdLike("%"+keyword+"%");
			//或者关系
			example.or(criteria2);
			example.or(criteria3);
			example.or(criteria4);
			List<User> users = userService.selectByExample(example); 
			PageInfo<User> pageInfo = new PageInfo<User>(users);
			return pageInfo; 
		 }
		if(StringUtils.isNotBlank(keyword)) {
			//username模糊查询
			Criteria criteria = example.createCriteria();
			criteria.andUsernameLike("%"+keyword+"%");
			//真实姓名模糊查询
			Criteria criteria2 = example.createCriteria();
			criteria2.andRealnameLike("%"+keyword+"%");
			//角色
			Criteria criteria3 = example.createCriteria();
			criteria3.andRoleNameLike("%"+keyword+"%");
			//或者关系
			example.or(criteria2);
			example.or(criteria3);
		}
		 
		List<User> users = userService.selectByExample(example);
		
		//创建分页对象，将查询的list集合存放到分页对象中
		//pageinfo存放了分页所需要的全部信息
		
		PageInfo<User> pageInfo = new PageInfo<User>(users);
		return pageInfo;
	}
	
	@RequestMapping("/edit")
	public String edit(Model m,Long userId) {
		if(userId !=null) {
			User user = userService.selectByPrimaryKey(userId);
			m.addAttribute("user", user);
			System.out.println(user);
		}
		RoleExample example = new RoleExample();
		List<Role> roles = roleService.selectByExample(example);
		m.addAttribute("roles", roles);
		System.out.println(roles);
		return "admin-Edit";
	}

	@RequestMapping("/checkUsername")
	@RequiresPermissions("admin:checkUsername")
	@ResponseBody
	public boolean checkUsername(String username) {
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<User> users = userService.selectByExample(example);
		return users.size()>0? false:true;
	}
	
	@RequestMapping("/insert")
	@RequiresPermissions("admin:insert")
	@ResponseBody
	public MessageObject insertUser(User user) {
		user.setCreateDate(new Date());
		//随机生成盐
		String salt = UUID.randomUUID().toString().substring(0, 5);
		user.setSalt(salt);
		//使用md5加密
		Md5Hash saltPassword = new Md5Hash(user.getPassword(), salt, 3);
		user.setPassword(saltPassword.toString());
		MessageObject messageObject = new MessageObject(0, "添加失败");
		int row = userService.insert(user);
		if(row ==1) {
			messageObject = new MessageObject(1, "添加成功");
		}
		return messageObject;
	}
	
	@RequestMapping("/update")
	@RequiresPermissions("admin:update")
	@ResponseBody
	public MessageObject updateUser(User user) {
		if(StringUtils.isBlank(user.getPassword())) {
			user.setPassword(null);
		}
		if(StringUtils.isNotBlank(user.getPassword())) {
			String salt = UUID.randomUUID().toString().substring(0, 5);
			Md5Hash saltPassword = new Md5Hash(user.getPassword(), salt, 3);
			user.setSalt(salt);
			user.setPassword(saltPassword.toString());
		}
		MessageObject messageObject = new MessageObject(0,"修改失败");
		int row = userService.updateByPrimaryKeySelective(user);
		if(row==1) {
			 messageObject = new MessageObject(1, "修改成功");
		}
		return messageObject;
		
	}
	@RequestMapping("/deleteAll")
	@RequiresPermissions("admin:delete")
	@ResponseBody
	public MessageObject deleteAllUser(String ids) {
		MessageObject messageObject=null;
		String[] userIds = ids.split(",");
		int count = 0;
		for (String userId : userIds) {
			userService.deleteByPrimaryKey(Long.valueOf(userId));
			count++;
		}
		if(count>0) {
			messageObject = new MessageObject(1,"删除成功");
		}else {
			messageObject = new MessageObject(0,"删除失败");
		}
		return messageObject;
	}
}
