package com.yonyou.service;

import java.util.List;
import java.util.Map;

import com.yonyou.domain.dto.ProjectDTO;
import com.yonyou.domain.dto.UserDTO;

public interface UserService {

	public Map<String, Object> login(String account, String password);

	public UserDTO add(String username, String account, String password, int sex, int position);

	public UserDTO get(String token);

	public int delete(int userId,String token);

	public UserDTO updatePassword(int userId, String password, String newPassword,String token);

	public UserDTO update(int userId,String username, int sex, int position,String token);

	public List<UserDTO> list(String token);

	public Map<String, Object> reftoken(String token, String refToken);

	public UserDTO getByUserId(int userId);

	public List<UserDTO> listAll(String token);

	List<ProjectDTO> getProjectList(int userId);
	
	//public Object listAll(String token);



}
