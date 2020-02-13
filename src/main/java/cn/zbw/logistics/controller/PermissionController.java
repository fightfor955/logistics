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
import cn.zbw.logistics.pojo.Permission;
import cn.zbw.logistics.pojo.PermissionExample;
import cn.zbw.logistics.pojo.PermissionExample.Criteria;
import cn.zbw.logistics.service.RoleService;
import cn.zbw.logistics.service.PermissionService;


@Controller
@RequestMapping("/permission")
public class PermissionController {
	
	@RequestMapping("/permission-list")
	public String permissionPage() {
		return "permission-list";
	}
	
	@Autowired
	private PermissionService permissionService;
	

	
	@RequestMapping("delete")
	@ResponseBody
	public MessageObject delete(long permissionId) {
		int row = permissionService.deleteByPrimaryKey(permissionId);
		System.out.println(permissionId);
		MessageObject messageObject=null;
		if(row ==1) {
			messageObject = new MessageObject(1, "删除成功");
		}else {
			messageObject = new MessageObject(0,"删除权限失败");
		}
		return messageObject;
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public PageInfo pageInfoList(@RequestParam(defaultValue = "5")int pageNum,@RequestParam(defaultValue = "10")int pageSize,String keyword){
		PageHelper.startPage(pageNum, pageSize);
		
		PermissionExample example = new PermissionExample();
		if(StringUtils.isNotBlank(keyword)) {
			Criteria criteria = example.createCriteria();
			criteria.andNameLike("%"+keyword+"%");
		}
		List<Permission> permissions = permissionService.selectByExample(example);
		
		//创建分页对象，将查询的list集合存放到分页对象中
		//pageinfo存放了分页所需要的全部信息
		
		PageInfo<Permission> pageInfo = new PageInfo<Permission>(permissions);
		return pageInfo;
	}
	
	@RequestMapping("/edit")
	public String edit(Model m,Long permissionId) {
		if(permissionId !=null) {
			Permission permission = permissionService.selectByPrimaryKey(permissionId);
			m.addAttribute("permission", permission);
			System.out.println(permission);
		}
		PermissionExample example = new PermissionExample();
		List<Permission> permissions = permissionService.selectByExample(example);
		m.addAttribute("permissions", permissions);
		System.out.println(permissions);
		return "permission-Edit";
	}

	/*
	 * @RequestMapping("/checkUsername")
	 * 
	 * @ResponseBody public boolean checkUsername(String name) { UserExample example
	 * = new UserExample(); Criteria criteria = example.createCriteria();
	 * criteria.andUsernameEqualTo(name); List<User> permissions =
	 * permissionService.selectByExample(example); return permissions.size()>0?
	 * false:true; }
	 */
	
	@RequestMapping("/insert")
	@ResponseBody
	public MessageObject insertPermission(Permission permission) {
		MessageObject messageObject = new MessageObject(0, "添加失败");
		int row = permissionService.insert(permission);
		if(row ==1) {
			messageObject = new MessageObject(1, "添加成功");
		}
		return messageObject;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public MessageObject updatePermission(Permission permission) {
		MessageObject messageObject = new MessageObject(0,"修改失败");
		int row = permissionService.updateByPrimaryKeySelective(permission);
		if(row==1) {
			 messageObject = new MessageObject(1, "修改成功");
		}
		return messageObject;
		
	}
	//给ztree使用的数据
	@RequestMapping("/getAllPermission")
	@ResponseBody
	public List<Permission> getAllPermission(){
		PermissionExample example = new PermissionExample();
		List<Permission> permissions = permissionService.selectByExample(example);
		return permissions;
	}
}
