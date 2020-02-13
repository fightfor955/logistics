package cn.zbw.logistics.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zbw.logistics.mapper.OrderDetailMapper;
import cn.zbw.logistics.pojo.OrderDetail;
import cn.zbw.logistics.pojo.OrderDetailExample;
import cn.zbw.logistics.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
	
	@Autowired
	private OrderDetailMapper orderDetailMapper;

	@Override
	public List<OrderDetail> selectByExample(OrderDetailExample example) {
		return orderDetailMapper.selectByExample(example);
	}

	@Override
	public OrderDetail selectByPrimaryKey(Long orderDetailId) {
		return orderDetailMapper.selectByPrimaryKey(orderDetailId);
	}

	@Override
	public int updateByPrimaryKeySelective(OrderDetail record) {
		return orderDetailMapper.updateByPrimaryKeySelective(record);
	}

}
