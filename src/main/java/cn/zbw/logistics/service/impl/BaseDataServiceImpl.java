package cn.zbw.logistics.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.zbw.logistics.mapper.BaseDataMapper;
import cn.zbw.logistics.pojo.BaseData;
import cn.zbw.logistics.pojo.BaseDataExample;
import cn.zbw.logistics.service.BaseDataService;

@Service
public class BaseDataServiceImpl implements BaseDataService{

	@Autowired
	private BaseDataMapper baseDataMapper;
	
	@Override
	public int deleteByPrimaryKey(Long baseDataId) {
		// TODO Auto-generated method stub
		
		return baseDataMapper.deleteByPrimaryKey(baseDataId);
	}

	@Override
	public int insert(BaseData record) {
		// TODO Auto-generated method stub
		return baseDataMapper.insert(record);
	}

	@Override
	public int insertSelective(BaseData record) {
		// TODO Auto-generated method stub
		return baseDataMapper.insertSelective(record);
	}

	@Override
	public List<BaseData> selectByExample(BaseDataExample example) {
		// TODO Auto-generated method stub
		return baseDataMapper.selectByExample(example);
	}

	@Override
	public BaseData selectByPrimaryKey(Long baseDataId) {
		// TODO Auto-generated method stub
		return baseDataMapper.selectByPrimaryKey(baseDataId);
	}

	@Override
	public int updateByPrimaryKeySelective(BaseData record) {
		// TODO Auto-generated method stub
		return baseDataMapper.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public List<BaseData> selecBaseDatasByParentName(String parentName) {
		return baseDataMapper.selecBaseDatasByParentName(parentName);
	}
}
