package com.yonyou.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yonyou.domain.po.UserProjectRelationPO;
import com.yonyou.mapper.UserProjectRelationMapper;
import com.yonyou.service.UserProjectService;

@Service
public class UserProjectServiceImpl implements UserProjectService {

	@Autowired
	private UserProjectRelationMapper mapper;
	
	public boolean add(int projectId, int userId,int createBy) {
		UserProjectRelationPO userProjectPO=new UserProjectRelationPO();
		userProjectPO.setUserId(userId);
		userProjectPO.setProjectId(projectId);
		userProjectPO.setCreateTime(new Date());
		userProjectPO.setCreateBy(createBy);
		if(mapper.add(userProjectPO)>0)
			return true;
		return false;
	}

	/*public boolean delete(int userId,int projectId) {
		UserProjectRelationPO userPro=mapper.getByUserProId(userId,projectId);
		if(userPro!=null)
			if(mapper.delete(userId,projectId)>0)
				return true;
		return false;
	}*/

	public UserProjectRelationPO getByUserProId(int userId,int projectId) {
		UserProjectRelationPO userProPO=mapper.getByUserProId(userId,projectId);
		if(userProPO!=null) {
			return userProPO;
		}		
		return null;
	}

	public boolean alter(UserProjectRelationPO userProPO) {
		if(mapper.deleteById(userProPO)>0)
			return true;
		return false;
	}

}
