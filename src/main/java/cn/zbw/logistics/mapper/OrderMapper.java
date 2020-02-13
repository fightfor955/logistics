package cn.zbw.logistics.mapper;

import cn.zbw.logistics.pojo.Order;
import cn.zbw.logistics.pojo.OrderExample;
import java.util.List;

public interface OrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Sat Oct 12 17:49:48 CST 2019
     */
    int deleteByPrimaryKey(Long orderId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Sat Oct 12 17:49:48 CST 2019
     */
    int insert(Order record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Sat Oct 12 17:49:48 CST 2019
     */
    int insertSelective(Order record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Sat Oct 12 17:49:48 CST 2019
     */
    List<Order> selectByExample(OrderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Sat Oct 12 17:49:48 CST 2019
     */
    Order selectByPrimaryKey(Long orderId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Sat Oct 12 17:49:48 CST 2019
     */
    int updateByPrimaryKeySelective(Order record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_order
     *
     * @mbg.generated Sat Oct 12 17:49:48 CST 2019
     */
    int updateByPrimaryKey(Order record);
}