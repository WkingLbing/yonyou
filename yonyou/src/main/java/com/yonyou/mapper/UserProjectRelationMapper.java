package com.yonyou.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yonyou.domain.po.UserProjectRelationPO;


@Mapper
public interface UserProjectRelationMapper {

	/**
	 * ���
	 * @param userProjectPO
	 * @return
	 */
	public int add(UserProjectRelationPO userProjectPO);
	
	/**
	 * ����byUserProId
	 * @param userId
	 * @return
	 */
	public UserProjectRelationPO getByUserProId(@Param("userId")int userId,@Param("projectId")int projectId);
	
	/**
	 * ɾ��byId
	 * @param userProPO
	 * @return
	 */
	public int deleteById(UserProjectRelationPO userProPO);
	
	/**
	 * is_delete=1ɾ��
	 * @param userProPO
	 * @return
	 */
	public int deleteUserProject(@Param("projectId")int projectId, @Param("updateBy")int updateBy, @Param("updateTime")Date updateTime);
}
