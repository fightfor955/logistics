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
import cn.zbw.logistics.pojo.BaseData;
import cn.zbw.logistics.pojo.BaseDataExample;
import cn.zbw.logistics.pojo.BaseDataExample.Criteria;
import cn.zbw.logistics.service.RoleService;
import cn.zbw.logistics.service.BaseDataService;


@Controller
@RequestMapping("/baseData")
public class BaseDataController {
	
	@RequestMapping("/baseData-list")
	public String baseDataPage() {
		return "baseData-list";
	}
	
	@Autowired
	private BaseDataService baseDataService;
	

	
	@RequestMapping("delete")
	@ResponseBody
	public MessageObject delete(long baseId) {
		int row = baseDataService.deleteByPrimaryKey(baseId);
		System.out.println(baseId);
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
		
		BaseDataExample example = new BaseDataExample();
		if(StringUtils.isNotBlank(keyword)) {
			Criteria criteria = example.createCriteria();
			criteria.andBaseNameLike("%"+keyword+"%");
			Criteria criteria2 = example.createCriteria();
			criteria2.andParentNameLike("%"+keyword+"%");
			example.or(criteria2);
		}
		List<BaseData> baseDatas = baseDataService.selectByExample(example);
		
		//创建分页对象，将查询的list集合存放到分页对象中
		//pageinfo存放了分页所需要的全部信息
		
		PageInfo<BaseData> pageInfo = new PageInfo<BaseData>(baseDatas);
		return pageInfo;
	}
	
	@RequestMapping("/edit")
	public String edit(Model m,Long baseId) {
		if(baseId !=null) {
			BaseData baseData = baseDataService.selectByPrimaryKey(baseId);
			m.addAttribute("baseData", baseData);
		}
		BaseDataExample example = new BaseDataExample();
		List<BaseData> parents = baseDataService.selectByExample(example);
		m.addAttribute("parents", parents);
		return "baseData-Edit";
	}

	/*
	 * @RequestMapping("/checkUsername")
	 * 
	 * @ResponseBody public boolean checkUsername(String name) { UserExample example
	 * = new UserExample(); Criteria criteria = example.createCriteria();
	 * criteria.andUsernameEqualTo(name); List<User> baseDatas =
	 * baseDataService.selectByExample(example); return baseDatas.size()>0?
	 * false:true; }
	 */
	
	@RequestMapping("/insert")
	@ResponseBody
	public MessageObject insertBaseData(BaseData baseData) {
		MessageObject messageObject = new MessageObject(0, "添加失败");
		int row = baseDataService.insert(baseData);
		if(row ==1) {
			messageObject = new MessageObject(1, "添加成功");
		}
		return messageObject;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public MessageObject updateBaseData(BaseData baseData) {
		MessageObject messageObject = new MessageObject(0,"修改失败");
		int row = baseDataService.updateByPrimaryKeySelective(baseData);
		if(row==1) {
			 messageObject = new MessageObject(1, "修改成功");
		}
		return messageObject;
		
	}
}
