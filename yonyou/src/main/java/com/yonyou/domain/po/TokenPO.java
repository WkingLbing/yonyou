package com.yonyou.domain.po;


import java.util.Date;
public class TokenPO {
		private Integer userId;
		private String token;
		private String refToken;
		private Date expireTime;
		private Date createTime;
		private Integer isDelete;
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public String getRefToken() {
			return refToken;
		}
		public void setRefToken(String refToken) {
			this.refToken = refToken;
		}
		public Date getExpireTime() {
			return expireTime;
		}
		public void setExpireTime(Date expireTime) {
			this.expireTime = expireTime;
		}
		public Date getCreateTime() {
			return createTime;
		}
		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}
		public Integer getIsDelete() {
			return isDelete;
		}
		public void setIsDelete(Integer isDelete) {
			this.isDelete = isDelete;
		}
		public void setUserId(Integer userId) {
			this.userId = userId;
		}
		
		
	}

