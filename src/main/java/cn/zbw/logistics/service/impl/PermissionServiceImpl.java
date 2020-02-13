package cn.zbw.logistics.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zbw.logistics.mapper.PermissionMapper;
import cn.zbw.logistics.pojo.Permission;
import cn.zbw.logistics.pojo.PermissionExample;
import cn.zbw.logistics.service.PermissionService;
@Service
public class PermissionServiceImpl implements PermissionService {
	@Autowired
	private PermissionMapper permissionmapper;

	@Override
	public int deleteByPrimaryKey(Long permissionId) {
		return permissionmapper.deleteByPrimaryKey(permissionId);
	}

	@Override
	public int insert(Permission record) {
		return permissionmapper.insert(record);
	}

	@Override
	public int insertSelective(Permission record) {
		return permissionmapper.insertSelective(record);
	}

	@Override
	public List<Permission> selectByExample(PermissionExample example) {
		return permissionmapper.selectByExample(example);
	}

	@Override
	public Permission selectByPrimaryKey(Long permissionId) {
		return permissionmapper.selectByPrimaryKey(permissionId);
	}

	@Override
	public int updateByPrimaryKeySelective(Permission record) {
		return permissionmapper.updateByPrimaryKeySelective(record);
	}

}
