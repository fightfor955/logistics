package cn.zbw.logistics.controller;

import java.util.Date;
import java.util.List;

import javax.xml.soap.Detail;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.zbw.logistics.pojo.BaseData;
import cn.zbw.logistics.pojo.Customer;
import cn.zbw.logistics.pojo.CustomerExample;
import cn.zbw.logistics.pojo.MessageObject;
import cn.zbw.logistics.pojo.Order;
import cn.zbw.logistics.pojo.OrderDetail;
import cn.zbw.logistics.pojo.OrderDetailExample;
import cn.zbw.logistics.pojo.OrderExample;
import cn.zbw.logistics.pojo.Order;
import cn.zbw.logistics.pojo.OrderExample;
import cn.zbw.logistics.pojo.OrderExample.Criteria;
import cn.zbw.logistics.pojo.User;
import cn.zbw.logistics.pojo.UserExample;
import cn.zbw.logistics.service.BaseDataService;
import cn.zbw.logistics.service.CustomerService;
import cn.zbw.logistics.service.OrderDetailService;
import cn.zbw.logistics.service.OrderService;
import cn.zbw.logistics.service.UserService;
import cn.zbw.logistics.utils.Constant;
import cn.zbw.logistics.service.OrderService;


@Controller
@RequestMapping("/order")
public class OrderController {
	
	@RequestMapping("/order-list")
	public String orderPage() {
		return "order-list";
	}
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private BaseDataService baseDataService;
	
	
	
	@RequestMapping("delete")
	@ResponseBody
	public MessageObject delete(long orderId) {
		/*
		 * //判断当前角色是否由用户 UserExample userExample = new UserExample();
		 * cn.zbw.logistics.pojo.UserExample.Criteria createCriteria =
		 * userExample.createCriteria(); createCriteria.andOrderIdEqualTo(orderId);
		 * List<User> users = userService.selectByExample(userExample);
		 * System.out.println(users); if(users.size()>0) { return new MessageObject(0,
		 * "此角色已关联用户，不能删除"); } int row = orderService.deleteByPrimaryKey(orderId);
		 * System.out.println(orderId); MessageObject messageObject=null; if(row ==1) {
		 * messageObject = new MessageObject(1, "删除成功"); }else { messageObject = new
		 * MessageObject(0,"删除权限失败"); } return messageObject;
		 */
		//判断当前角色是否还有用户
				
				MessageObject mo = new MessageObject(0, "删除数据失败，请联系管理员");

				// 执行删除功能
				int row = orderService.deleteByPrimaryKey(orderId);
				if (row == 1) {
					mo = new MessageObject(1, "删除数据成功");
				}
				return mo;
	}
	
	@RequestMapping("/list")
	@ResponseBody
	public PageInfo pageInfoList(@RequestParam(defaultValue = "5")int pageNum,@RequestParam(defaultValue = "10")int pageSize,String keyword){
		PageHelper.startPage(pageNum, pageSize);
		
		OrderExample example = new OrderExample();
		
		if(StringUtils.isNotBlank(keyword)) {
			
			  boolean isNum = keyword.matches("[0-9]+"); 
			  if(isNum) {
				System.out.println("进来了？"); 
				Criteria criteria2 = example.createCriteria();
				Long key = Long.valueOf(keyword); criteria2.andOrderIdEqualTo(key);
				List<Order> orders = orderService.selectByExample(example); 
				PageInfo<Order> pageInfo = new PageInfo<Order>(orders);
				return pageInfo; 
			  }
			 
			Criteria criteria = example.createCriteria();
			criteria.andTakeAddressLike("%"+keyword+"%");
			Criteria criteria2 = example.createCriteria();
		}
		List<Order> orders = orderService.selectByExample(example);
		
		//创建分页对象，将查询的list集合存放到分页对象中
		//pageinfo存放了分页所需要的全部信息
		
		PageInfo<Order> pageInfo = new PageInfo<Order>(orders);
		return pageInfo;
	}
	
	//根据订单id查询订单明细
	@RequestMapping("/detail")
	@ResponseBody
	public List<OrderDetail> detail(long orderId){
		OrderDetailExample example = new OrderDetailExample();
		cn.zbw.logistics.pojo.OrderDetailExample.Criteria criteria = example.createCriteria();
		criteria.andOrderIdEqualTo(orderId);
		List<OrderDetail> orderDetails = orderDetailService.selectByExample(example);
		return orderDetails;
	}
	/*
	 * @RequestMapping("/checkUsername")
	 * 
	 * @ResponseBody public boolean checkUsername(String name) { UserExample example
	 * = new UserExample(); Criteria criteria = example.createCriteria();
	 * criteria.andUsernameEqualTo(name); List<User> orders =
	 * orderService.selectByExample(example); return orders.size()>0?
	 * false:true; }
	 */
	
	@RequestMapping("/insert")
	@ResponseBody
	public MessageObject insertOrder(@RequestBody Order order) {
		//@requestBody 将json数据接收
		MessageObject messageObject = new MessageObject(0, "添加失败");
		Long customerId = order.getCustomerId();
		System.out.println(customerId);
		int row = orderService.insert(order);
		System.out.println("order:+"+order);
		System.out.println("order:+"+order.getOrderDetails());
		if(row ==1) {
			messageObject = new MessageObject(1, "添加成功");
		}
		return messageObject;
	}
	//修改功能
	@RequestMapping("/update")
	@ResponseBody
	public MessageObject update(@RequestBody Order order) {
		System.out.println("修改中！！！");
		MessageObject mo = new MessageObject(0, "修改数据失败，请联系管理员");
		
		
		// 执行删除功能
		int row = orderService.updateByPrimaryKeySelective(order);
		if (row == 1) {
			mo = new MessageObject(1, "修改数据成功");
		}
		return mo;
		
	}

	// 编辑：新增和修改公共

	@RequestMapping("/edit")
	public String edit(Model m,Long orderId) {

		//编辑数据，回显
		if(orderId !=null) {
			Order order = orderService.selectByPrimaryKey(orderId);
			OrderDetailExample example = new OrderDetailExample();
			cn.zbw.logistics.pojo.OrderDetailExample.Criteria createCriteria = example.createCriteria();
			createCriteria.andOrderIdEqualTo(orderId);
			System.out.println(orderId);
			List<OrderDetail> orderDetailList = orderDetailService.selectByExample(example);
			m.addAttribute("orderDetailList",orderDetailList);
			System.out.println("详情"+orderDetailList);
			m.addAttribute("order", order);
			System.out.println(order);
		}
		
		UserExample userExample = new UserExample();
		cn.zbw.logistics.pojo.UserExample.Criteria userCriteria = userExample.createCriteria();
		userCriteria.andRoleNameEqualTo(Constant.ROLE_SALESMAN);
		List<User> users = userService.selectByExample(userExample);
		CustomerExample customerExample = new CustomerExample();
		List<Customer> customers = customerService.selectByExample(customerExample);
		List<BaseData> intervals = baseDataService.selecBaseDatasByParentName(Constant.BASIC_COMMON_INTERVAL);
		List<BaseData> payments = baseDataService.selecBaseDatasByParentName(Constant.BASIC_PAYMENT_TYPE);
		List<BaseData> freights = baseDataService.selecBaseDatasByParentName(Constant.BASIC_FREIGHT_TYPE);
		List<BaseData> fetchTypes = baseDataService.selecBaseDatasByParentName(Constant.BASIC_FETCH_TYPE);
		List<BaseData> units = baseDataService.selecBaseDatasByParentName(Constant.BASIC_UNIT);
		m.addAttribute("units", units);
		m.addAttribute("users",users);
		m.addAttribute("customers",customers);
		m.addAttribute("intervals",intervals);
		m.addAttribute("payments",payments);
		m.addAttribute("freights",freights);
		m.addAttribute("fetchTypes",fetchTypes);
		return "order-Edit";
	}

}
