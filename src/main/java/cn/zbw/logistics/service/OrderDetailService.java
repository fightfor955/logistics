package cn.zbw.logistics.service;

import java.util.List;

import cn.zbw.logistics.pojo.Order;
import cn.zbw.logistics.pojo.OrderDetail;
import cn.zbw.logistics.pojo.OrderDetailExample;
import cn.zbw.logistics.pojo.OrderExample;

public interface OrderDetailService {

    List<OrderDetail> selectByExample(OrderDetailExample example);

    OrderDetail selectByPrimaryKey(Long orderDetailId);

    int updateByPrimaryKeySelective(OrderDetail record);
    
}
