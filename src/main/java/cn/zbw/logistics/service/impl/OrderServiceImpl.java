package cn.zbw.logistics.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import cn.zbw.logistics.mapper.OrderDetailMapper;
import cn.zbw.logistics.mapper.OrderMapper;
import cn.zbw.logistics.pojo.Order;
import cn.zbw.logistics.pojo.OrderDetail;
import cn.zbw.logistics.pojo.OrderDetailExample;
import cn.zbw.logistics.pojo.OrderDetailExample.Criteria;
import cn.zbw.logistics.pojo.OrderExample;
import cn.zbw.logistics.service.OrderService;
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderDetailMapper orderDetailMapper;
	
	
	@Override
	public int deleteByPrimaryKey(Long orderId) {
		OrderDetailExample example = new OrderDetailExample();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andOrderIdEqualTo(orderId);
		List<OrderDetail> list = orderDetailMapper.selectByExample(example);
		for (OrderDetail orderDetail : list) {
			Long orderDetailId = orderDetail.getOrderDetailId();
			orderDetailMapper.deleteByPrimaryKey(orderDetailId);
		}
		return orderMapper.deleteByPrimaryKey(orderId);
	}

	@Override
	public int insert(@RequestBody Order record) {
		/*
		 * 1.先插入订单信息，
		 * 2.循环orderDetails集合，把订单id插入金orderDetail
		 * 插入订单明细
		 * 
		 * */
		int row = orderMapper.insert(record);
		List<OrderDetail> orderDetails = record.getOrderDetails();
		if(record.getOrderDetails()!=null) {
			for (OrderDetail orderDetail : orderDetails) {
				orderDetail.setOrderId(record.getOrderId());
				orderDetailMapper.insert(orderDetail);
			}
		}
		
		return row;
	}

	@Override
	public List<Order> selectByExample(OrderExample example) {
		return orderMapper.selectByExample(example);
	}

	@Override
	public Order selectByPrimaryKey(Long orderId) {
		return orderMapper.selectByPrimaryKey(orderId);
	}

	@Override
	public int updateByPrimaryKeySelective(Order record) {
		return orderMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<OrderDetail> selectByExample(OrderDetailExample example) {
		return orderDetailMapper.selectByExample(example);
	}

}
