package cn.zbw.logistics.mapper;

import cn.zbw.logistics.pojo.Permission;
import cn.zbw.logistics.pojo.PermissionExample;
import java.util.List;

public interface PermissionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_permission
     *
     * @mbg.generated Fri Sep 27 17:41:39 CST 2019
     */
    int deleteByPrimaryKey(Long permissionId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_permission
     *
     * @mbg.generated Fri Sep 27 17:41:39 CST 2019
     */
    int insert(Permission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_permission
     *
     * @mbg.generated Fri Sep 27 17:41:39 CST 2019
     */
    int insertSelective(Permission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_permission
     *
     * @mbg.generated Fri Sep 27 17:41:39 CST 2019
     */
    List<Permission> selectByExample(PermissionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_permission
     *
     * @mbg.generated Fri Sep 27 17:41:39 CST 2019
     */
    Permission selectByPrimaryKey(Long permissionId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_permission
     *
     * @mbg.generated Fri Sep 27 17:41:39 CST 2019
     */
    int updateByPrimaryKeySelective(Permission record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_permission
     *
     * @mbg.generated Fri Sep 27 17:41:39 CST 2019
     */
    int updateByPrimaryKey(Permission record);
}