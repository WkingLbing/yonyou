package com.yonyou.domain.dto;

import java.util.List;

public class UserDTO {
	private Integer userId;
	private String username;
	private String account;
	private String password;
	private Integer sex;
	private Integer positionId;
	private Integer projectId;
	private List<ProjectDTO> projectList;
	public Integer getPositionId() {
		return positionId;
	}
	public void setPositionId(Integer positionId) {
		this.positionId = positionId;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	
	public List<ProjectDTO> getProjectList() {
		return projectList;
	}
	public void setProjectList(List<ProjectDTO> projectList) {
		this.projectList = projectList;
	}
	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", username=" + username + ", account=" + account + ", password="
				+ password + ", sex=" + sex + ", positionId=" + positionId + ", projectId=" + projectId
				+ ", projectList=" + projectList + "]";
	}
	
}
