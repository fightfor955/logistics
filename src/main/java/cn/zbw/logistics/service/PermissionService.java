package cn.zbw.logistics.service;

import java.util.List;

import cn.zbw.logistics.pojo.Permission;
import cn.zbw.logistics.pojo.PermissionExample;

public interface PermissionService {
	   int deleteByPrimaryKey(Long permissionId);

	   
	    int insert(Permission record);

	    
	    int insertSelective(Permission record);

	   
	    List<Permission> selectByExample(PermissionExample example);

	   
	    Permission selectByPrimaryKey(Long permissionId);

	   
	    int updateByPrimaryKeySelective(Permission record);
}
