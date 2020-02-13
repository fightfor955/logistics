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
import cn.zbw.logistics.pojo.Role;
import cn.zbw.logistics.pojo.RoleExample;
import cn.zbw.logistics.pojo.User;
import cn.zbw.logistics.pojo.UserExample;
import cn.zbw.logistics.pojo.BaseData;
import cn.zbw.logistics.pojo.Customer;
import cn.zbw.logistics.pojo.CustomerExample;
import cn.zbw.logistics.pojo.CustomerExample.Criteria;
import cn.zbw.logistics.service.UserService;
import cn.zbw.logistics.service.BaseDataService;
import cn.zbw.logistics.service.CustomerService;
import cn.zbw.logistics.service.PermissionService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private BaseDataService baseDataService;
	//认证shiro失败后跳转的页面
	
	@RequestMapping("/customer-list")
	@RequiresPermissions("customer:customerPage")
	public String customerPage() {
		return "customer-list";
	}
	
	@RequestMapping("delete")
	@RequiresPermissions("customer:delete")
	@ResponseBody
	public MessageObject delete(long customerId) {
		int row = customerService.deleteByPrimaryKey(customerId);
		System.out.println(customerId);
		MessageObject messageObject=null;
		if(row ==1) {
			messageObject = new MessageObject(1, "删除用户成功");
		}else {
			messageObject = new MessageObject(0,"删除用户失败");
		}
		return messageObject;
	}
	
	@RequestMapping("/list")
	@RequiresPermissions("customer:list")
	@ResponseBody
	public PageInfo pageInfoList(@RequestParam(defaultValue = "5")int pageNum,@RequestParam(defaultValue = "10")int pageSize,String keyword){
		PageHelper.startPage(pageNum, pageSize);
		
		CustomerExample example = new CustomerExample();
		if(StringUtils.isNotBlank(keyword)) {
			//customername模糊查询
			Criteria criteria = example.createCriteria();
			criteria.andCustomerNameLike("%"+keyword+"%");
			//业务员真实姓名模糊查询
			Criteria criteria2 = example.createCriteria();
			criteria2.andRealnameLike("%"+keyword+"%");
			//根据地区来查
			Criteria criteria3 = example.createCriteria();
			criteria3.andBaseNameLike("%"+keyword+"%");
			//或者关系
			example.or(criteria2);
			example.or(criteria3);
		}
		List<Customer> customers = customerService.selectByExample(example);
		
		//创建分页对象，将查询的list集合存放到分页对象中
		//pageinfo存放了分页所需要的全部信息
		
		PageInfo<Customer> pageInfo = new PageInfo<Customer>(customers);
		return pageInfo;
	}
	
	@RequestMapping("/edit")
	public String edit(Model m,Long customerId) {
		if(customerId !=null) {
			Customer customer = customerService.selectByPrimaryKey(customerId);
			m.addAttribute("customer", customer);
			System.out.println(customer);
		}
		//查询出所有的业务员
		UserExample example = new UserExample();
		cn.zbw.logistics.pojo.UserExample.Criteria criteria = example.createCriteria();
		criteria.andRoleNameEqualTo("业务员");
		List<User> users = userService.selectByExample(example);
		m.addAttribute("users", users);
		
		//查询出所有的区间管理数据
		List<BaseData> baseDatas = baseDataService.selecBaseDatasByParentName("区间管理");
		m.addAttribute("baseDatas", baseDatas);
		
		
		return "customer-Edit";
	}
	
	@RequestMapping("/insert")
	@RequiresPermissions("customer:insert")
	@ResponseBody
	public MessageObject insertCustomer(Customer customer) {
		MessageObject messageObject = new MessageObject(0, "添加失败");
		int row = customerService.insert(customer);
		if(row ==1) {
			messageObject = new MessageObject(1, "添加成功");
		}
		return messageObject;
	}
	
	@RequestMapping("/update")
	@RequiresPermissions("customer:update")
	@ResponseBody
	public MessageObject updateCustomer(Customer customer) {
		MessageObject messageObject = new MessageObject(0,"修改失败");
		int row = customerService.updateByPrimaryKeySelective(customer);
		if(row==1) {
			 messageObject = new MessageObject(1, "修改成功");
		}
		return messageObject;
		
	}
	
}
