package cn.zbw.logistics.service;

import java.util.List;

import cn.zbw.logistics.pojo.Role;
import cn.zbw.logistics.pojo.RoleExample;

public interface RoleService {
    int deleteByPrimaryKey(Long roleId);

    int insert(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(Role record);
}
