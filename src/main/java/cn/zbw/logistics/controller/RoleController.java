
package cn.zbw.logistics.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.zbw.logistics.pojo.MessageObject;
import cn.zbw.logistics.pojo.Role;
import cn.zbw.logistics.pojo.RoleExample;
import cn.zbw.logistics.pojo.Role;
import cn.zbw.logistics.pojo.RoleExample;
import cn.zbw.logistics.pojo.RoleExample.Criteria;
import cn.zbw.logistics.pojo.User;
import cn.zbw.logistics.pojo.UserExample;
import cn.zbw.logistics.service.RoleService;
import cn.zbw.logistics.service.UserService;
import cn.zbw.logistics.service.RoleService;


@Controller
@RequestMapping("/role")
public class RoleController {
	
	@RequestMapping("/role-list")
	public String rolePage() {
		return "role-list";
	}
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;

	
	@RequestMapping("delete")
	@ResponseBody
	public MessageObject delete(long roleId) {
		/*
		 * //判断当前角色是否由用户 UserExample userExample = new UserExample();
		 * cn.zbw.logistics.pojo.UserExample.Criteria createCriteria =
		 * userExample.createCriteria(); createCriteria.andRoleIdEqualTo(roleId);
		 * List<User> users = userService.selectByExample(userExample);
		 * System.out.println(users); if(users.size()>0) { return new MessageObject(0,
		 * "此角色已关联用户，不能删除"); } int row = roleService.deleteByPrimaryKey(roleId);
		 * System.out.println(roleId); MessageObject messageObject=null; if(row ==1) {
		 * messageObject = new MessageObject(1, "删除成功"); }else { messageObject = new
		 * MessageObject(0,"删除权限失败"); } return messageObject;
		 */
		//判断当前角色是否还有用户
				UserExample example = new UserExample();
				cn.zbw.logistics.pojo.UserExample.Criteria criteria = example.createCriteria();
				criteria.andRoleIdEqualTo(roleId);
				List<User> users = userService.selectByExample(example );
				if(users.size() >0) {
					return new MessageObject(0, "此角色关联有管理员，不能删除！");
				}
				
				MessageObject mo = new MessageObject(0, "删除数据失败，请联系管理员");

				// 执行删除功能
				int row = roleService.deleteByPrimaryKey(roleId);
				if (row == 1) {
					mo = new MessageObject(1, "删除数据成功");
				}
				return mo;
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public PageInfo pageInfoList(@RequestParam(defaultValue = "5")int pageNum,@RequestParam(defaultValue = "10")int pageSize,String keyword){
		PageHelper.startPage(pageNum, pageSize);
		
		RoleExample example = new RoleExample();
		if(StringUtils.isNotBlank(keyword)) {
			Criteria criteria = example.createCriteria();
			criteria.andRolenameLike("%"+keyword+"%");
		}
		List<Role> roles = roleService.selectByExample(example);
		
		//创建分页对象，将查询的list集合存放到分页对象中
		//pageinfo存放了分页所需要的全部信息
		
		PageInfo<Role> pageInfo = new PageInfo<Role>(roles);
		return pageInfo;
	}


	/*
	 * @RequestMapping("/checkUsername")
	 * 
	 * @ResponseBody public boolean checkUsername(String name) { UserExample example
	 * = new UserExample(); Criteria criteria = example.createCriteria();
	 * criteria.andUsernameEqualTo(name); List<User> roles =
	 * roleService.selectByExample(example); return roles.size()>0?
	 * false:true; }
	 */
	
	@RequestMapping("/insert")
	@ResponseBody
	public MessageObject insertRole(Role role) {
		MessageObject messageObject = new MessageObject(0, "添加失败");
		int row = roleService.insert(role);
		if(row ==1) {
			messageObject = new MessageObject(1, "添加成功");
		}
		return messageObject;
	}
	//修改功能
	@RequestMapping("/update")
	@ResponseBody
	public MessageObject update(Role role) {
		System.out.println("role :"+role);
		
		MessageObject mo = new MessageObject(0, "修改数据失败，请联系管理员");
		
		
		// 执行删除功能
		int row = roleService.updateByPrimaryKeySelective(role);
		if (row == 1) {
			mo = new MessageObject(1, "修改数据成功");
		}
		return mo;
		
	}

	// 编辑：新增和修改公共

	@RequestMapping("/edit")
	public String edit(Model m,Long roleId) {

		//编辑数据，回显
		if(roleId !=null) {
			Role role = roleService.selectByPrimaryKey(roleId);
			m.addAttribute("role", role);
		}
		
		
		// 查询所有的权限，以供新增或者修改权限的时候选择父权限
		RoleExample example = new RoleExample();
		List<Role> roles = roleService.selectByExample(example);
		m.addAttribute("roles", roles);

		return "role-Edit";
	}

}
