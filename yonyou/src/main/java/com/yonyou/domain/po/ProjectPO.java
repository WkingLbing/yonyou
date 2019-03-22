package com.yonyou.domain.po;

import java.util.Date;

public class ProjectPO {
	private int projectId;
	private String projectName;
	private String projectManager;
	private String beginTime;
	private String endTime;
	private String desc;
	private Date createTime;
	private int createBy;
	private Date updateTime;
	private int updateBy;
	private int isDelete;
	private UserPO userPO;
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectManager() {
		return projectManager;
	}
	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}
	
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public int getCreateBy() {
		return createBy;
	}
	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public int getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(int updateBy) {
		this.updateBy = updateBy;
	}
	public int getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(int isDelete) {
		this.isDelete = isDelete;
	}
	public UserPO getUserPO() {
		return userPO;
	}
	public void setUserPO(UserPO userPO) {
		this.userPO = userPO;
	}
	@Override
	public String toString() {
		return "ProjectPO [projectId=" + projectId + ", projectName=" + projectName + ", projectManager="
				+ projectManager + ", beginTime=" + beginTime + ", endTime=" + endTime + ", desc=" + desc
				+ ", createTime=" + createTime + ", createBy=" + createBy + ", updateTime=" + updateTime + ", updateBy="
				+ updateBy + ", isDelete=" + isDelete + ", userPO=" + userPO + "]";
	}
	
}
