package cn.zbw.logistics.service;

import java.util.List;

import cn.zbw.logistics.pojo.User;
import cn.zbw.logistics.pojo.UserExample;

public interface UserService {
	   int deleteByPrimaryKey(Long userId);

	   
	    int insert(User record);

	    
	    int insertSelective(User record);

	   
	    List<User> selectByExample(UserExample example);

	   
	    User selectByPrimaryKey(Long userId);

	   
	    int updateByPrimaryKeySelective(User record);
}
