package cn.zbw.logistics.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.zbw.logistics.pojo.BaseData;
import cn.zbw.logistics.pojo.Customer;
import cn.zbw.logistics.pojo.CustomerExample;

public interface CustomerService {
    int deleteByPrimaryKey(Long customerId);

    int insert(Customer record);

    int updateByPrimaryKeySelective(Customer record);
    
    Customer selectByPrimaryKey(Long customerId);

    List<Customer> selectByExample(CustomerExample example);
}
