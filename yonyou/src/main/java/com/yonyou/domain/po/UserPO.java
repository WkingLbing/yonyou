package com.yonyou.domain.po;

import java.util.Date;

public class UserPO {
	private Integer UserId;
	private String username;
	private String password;
	private Integer sex;
	private String account;
	private Integer positionId;
	private Date createTime;
	private Integer createBy;
	private Date updateTime;
	private Integer updateBy;
	private Integer isDelete;
	private ProjectPO prijectPO;
	
	public ProjectPO getPrijectPO() {
		return prijectPO;
	}
	public void setPrijectPO(ProjectPO prijectPO) {
		this.prijectPO = prijectPO;
	}
	public Integer getUserId() {
		return UserId;
	}
	public void setUserId(Integer userId) {
		UserId = userId;
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
	
	public Integer getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Integer createBy) {
		this.createBy = createBy;
	}
	public Integer getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Integer updateBy) {
		this.updateBy = updateBy;
	}
	
	public Integer getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "UserPO [UserId=" + UserId + ", username=" + username + ", password=" + password + ", sex=" + sex
				+ ", account=" + account + ", positionId=" + positionId + ", createTime=" + createTime + ", createBy="
				+ createBy + ", updateTime=" + updateTime + ", updateBy=" + updateBy + ", isDelete=" + isDelete
				+ ", prijectPO=" + prijectPO + "]";
	}
	
	
}
