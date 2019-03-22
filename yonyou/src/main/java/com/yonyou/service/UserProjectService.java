package com.yonyou.service;


import com.yonyou.domain.po.UserProjectRelationPO;

public interface UserProjectService {

	/**
	 * ���
	 * @param projectId
	 * @param userId
	 * @return
	 */
	public boolean add(int projectId,int userId,int createBy);
	
	/**
	 * ɾ��
	 * @param userId
	 * @return
	 */
	//public boolean delete(int userId,int projectId);
	
	/**
	 * ����byUserId
	 * @param userId
	 * @return
	 */
	public UserProjectRelationPO getByUserProId(int userId,int projectId);
	
	/**
	 * ����
	 * @param userProPO
	 * @return
	 */
	public boolean alter(UserProjectRelationPO userProPO);
}
