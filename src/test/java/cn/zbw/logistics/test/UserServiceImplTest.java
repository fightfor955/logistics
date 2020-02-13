package cn.zbw.logistics.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.zbw.logistics.pojo.User;
import cn.zbw.logistics.pojo.UserExample;
import cn.zbw.logistics.service.UserService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-data.xml")
public class UserServiceImplTest {
	@Autowired
	private UserService userService;
	
	@Test
	public void testDeleteByPrimaryKey() {
	}

	@Test
	public void testInsert() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertSelective() {
		User user = new User();
		for(int i=0;i<101;i++) {
			user.setUsername("zbw"+i);
			user.setPassword("123465");
			user.setRoleId(1L);
			userService.insert(user);
		}
		
	}

	@Test
	public void testSelectByExample() {
		/*
		 * 分页插件的使用
		 * 
		 * PageHelper.startPage(pageNum, pageSize)
		 * pageNum : 页码，默认 第一页
		 * pageSize ： 每页条数
		 */
		int pageNum = 5;
		int pageSize = 10;
		PageHelper.startPage(pageNum, pageSize);
		
		UserExample example = new UserExample();
		List<User> users = userService.selectByExample(example);
		
		//创建分页对象，将查询的list集合存放到分页对象中
		//pageinfo存放了分页所需要的全部信息
		
		PageInfo<User> pageInfo = new PageInfo<User>(users);
		System.out.println(pageInfo);
	}

	@Test
	public void testSelectByPrimaryKey() {
		userService.selectByPrimaryKey(1L);
	}

	@Test
	public void testUpdateByPrimaryKeySelective() throws ParseException {
		User user = new User();
		for(int i=0;i<101;i++) {
			String string = "2019-11-20";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = simpleDateFormat.parse(string);
			user.setUserId(i+1L);
			user.setCreateDate(date);
			user.setRealname("周博文"+i);
			user.setStatus(2);
			user.setSalt("dascoim121161561cs");
			userService.updateByPrimaryKeySelective(user);
		}
	}

}
