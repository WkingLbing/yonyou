package com.yonyou.domain.dto;

import com.yonyou.domain.po.UserProjectRelationPO;

public class UserProjcetRelationDTO extends UserProjectRelationPO{

	private int projectId;
	private int userId;
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}	
