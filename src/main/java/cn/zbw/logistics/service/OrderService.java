package cn.zbw.logistics.service;

import java.util.List;

import cn.zbw.logistics.pojo.Order;
import cn.zbw.logistics.pojo.OrderDetail;
import cn.zbw.logistics.pojo.OrderDetailExample;
import cn.zbw.logistics.pojo.OrderExample;

public interface OrderService {
    int deleteByPrimaryKey(Long orderId);

    int insert(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(Order record);
    
    List<OrderDetail> selectByExample(OrderDetailExample example);
}
