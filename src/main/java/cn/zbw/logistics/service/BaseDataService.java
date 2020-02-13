package cn.zbw.logistics.service;

import java.util.List;

import cn.zbw.logistics.pojo.BaseData;
import cn.zbw.logistics.pojo.BaseDataExample;

public interface BaseDataService {
	   int deleteByPrimaryKey(Long baseDataId);

	   
	    int insert(BaseData record);

	    
	    int insertSelective(BaseData record);

	   
	    List<BaseData> selectByExample(BaseDataExample example);

	   
	    BaseData selectByPrimaryKey(Long baseDataId);

	   
	    int updateByPrimaryKeySelective(BaseData record);


		List<BaseData> selecBaseDatasByParentName(String parentName);
	    
	    
}
