package cn.zbw.logistics.mapper;

import cn.zbw.logistics.pojo.Customer;
import cn.zbw.logistics.pojo.CustomerExample;
import java.util.List;

public interface CustomerMapper {
	  int deleteByPrimaryKey(Long customerId);

	    int insert(Customer record);

	    int updateByPrimaryKeySelective(Customer record);
	    
	    Customer selectByPrimaryKey(Long customerId);

	    List<Customer> selectByExample(CustomerExample example);
}