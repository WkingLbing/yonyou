/**
 * 功能说明 :
 * 创建者 : 段明磊
 * E-mail : duanml1@yonyou.com
 */
package com.yonyou.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yonyou.domain.dto.ProjectDTO;
import com.yonyou.domain.dto.TokenDTO;
import com.yonyou.domain.dto.UserDTO;
import com.yonyou.domain.po.UserPO;
import com.yonyou.domain.po.UserProjectRelationPO;
import com.yonyou.mapper.ProjectMapper;
import com.yonyou.mapper.UserMapper;
import com.yonyou.service.ProjectService;
import com.yonyou.service.TokenService;
import com.yonyou.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	 UserMapper userMapper;
	@Autowired
	private ProjectMapper projectmapper;
	@Autowired
	UserService us;
	@Autowired
	TokenService tokenService;
	@Autowired
	private ProjectService projectService;
	
	public UserDTO add(String username,String account,String password,int sex,int positionId) {

		List<UserPO> userPOlList=userMapper.getByAccount(account);
		if(userPOlList.size()==0){
			UserPO userPO=new UserPO();
			UserDTO userDTO2=new UserDTO();
			userPO.setUsername(username);
			userPO.setAccount(account);
			userPO.setPassword(password);
			userPO.setSex(sex);
			userPO.setPositionId(positionId);
			userPO.setCreateTime(new Date());
			userMapper.add(userPO);
			List<UserPO> userPO2=userMapper.getByAccount(account);
			System.out.println(userPO2.toString());
			BeanUtils.copyProperties(userPO2.get(0), userDTO2);
			System.out.println(userDTO2.toString());
			return userDTO2;
		}
		else {
			return null;
		}
	}
	
	public Map<String, Object> login(String account, String password) {
		List<UserPO> userList=userMapper.getByAccount(account);
		if(userList.size()>0) {
			//判断密码是否相等
			if(userList.get(0).getPassword().equals(password)==true) {
				TokenDTO tokenDTO=tokenService.add(userList.get(0).getUserId());
				UserDTO userDTO=new UserDTO();
				BeanUtils.copyProperties(userList.get(0), userDTO);
				Map<String,Object> result=new HashMap<String,Object>();
				result.put("user", userDTO);
				result.put("token", tokenDTO);
				return result;
			}
		}
		return null;
	}

	public List<UserDTO> list(String token) {
		TokenDTO tokenDTO = tokenService.getByToken(token);
		if (tokenDTO == null) {
			return null;
		}
		List<UserDTO> userPOList=userMapper.get();
		List<UserDTO> userDTOList=new ArrayList<UserDTO>();
		if(userPOList.size()>0) {
			for(int i=0;i<userPOList.size();i++) {
				UserDTO userDTO=new UserDTO();
					BeanUtils.copyProperties(userPOList.get(i), userDTO);
					System.out.println(userDTO);
					userDTOList.add(userDTO);
			}
			return userDTOList;
		}
		else {
			return null;
		}
	}

	public int delete(int userId,String token) {
		TokenDTO tokenDTO = tokenService.getByToken(token);
		if (tokenDTO == null) {
			return 0;
		}
		List<UserPO> UserPOList=userMapper.getByUserId(userId);
		if(UserPOList.size()>0) {
			UserDTO newsDTO=new UserDTO();
			UserPO userPO=new UserPO();
			userPO.setUserId(userId);
			userPO.setUpdateBy(tokenDTO.getUserId());
			userPO.setUpdateTime(new Date());
			BeanUtils.copyProperties(UserPOList.get(0), newsDTO);
			if (userMapper.delete(userPO) > 0) {
				return 1;
			}
		}
		return 0;
	}

	public UserDTO updatePassword(int userId,String password, String newPassword,String token) {
		TokenDTO tokenDTO = tokenService.getByToken(token);
		if (tokenDTO == null) {
			return null;
		}
		List<UserPO> userDTO=userMapper.getByUserId(userId);
		UserDTO userDTO1=new UserDTO();
		if(userDTO==null) {
			return null;
		}
		if(userDTO.get(0).getPassword().equals(password)){
			UserPO userPO=new UserPO();
			userPO.setUserId(userId);
			userPO.setPassword(newPassword);
			userPO.setUpdateBy(userId);
			userPO.setUpdateTime(new Date());
			userMapper.updatePassword(userPO);
			List<UserPO> userPOs=userMapper.getByUserId(userId);
			BeanUtils.copyProperties(userPOs.get(0),userDTO1 );
		}
		return userDTO1;
	}
	public UserDTO update(int userId,String username,int sex, int positionId,String token) {
		TokenDTO tokenDTO = tokenService.getByToken(token);
		if (tokenDTO == null) {
			return null;
		}
		List<UserPO> userDTO=userMapper.getByUserId(tokenDTO.getUserId());
		UserDTO userDTO1=new UserDTO();
		if(userDTO!=null) {
				UserPO userPO=new UserPO();
				userPO.setUserId(userId);
				userPO.setUsername(username);
				userPO.setSex(sex);
				userPO.setPositionId(positionId);
				userPO.setUpdateBy(tokenDTO.getUserId());
				userPO.setUpdateTime(new Date());
				userMapper.update(userPO);
				System.out.println(userPO);
				List<UserPO> userPOs=userMapper.getByUserId(userId);
				System.out.println(userPOs);
				BeanUtils.copyProperties(userPOs.get(0),userDTO1 );
				System.out.println(userDTO1);
			return userDTO1;
		}
		else {
			return null;
		}
	}
	public UserDTO get(String token) {
		TokenDTO tokenDTO = tokenService.getByToken(token);
		if (tokenDTO == null) {
			return null;
		}
		List<UserPO> userPOList=userMapper.getByUserId(tokenDTO.getUserId());
		if(userPOList.size()>0) {
			UserDTO userDTO=new UserDTO();
			BeanUtils.copyProperties(userPOList.get(0), userDTO);
			return userDTO;
		}
		return null;
	}

	public Map<String, Object> reftoken(String token, String refToken) {
		TokenDTO tokenDTO = new TokenDTO();
		tokenDTO = tokenService.getByToken(token);
		if (tokenDTO == null || (!tokenDTO.getRefToken().equals(refToken))) {
			System.out.print("999");
			return null;
			
		} else{
			UserDTO userDTO = this.get(token);
		    tokenDTO = tokenService.add(userDTO.getUserId());
		    Map<String, Object> result = new HashMap<String, Object>();
		    result.put("user", userDTO);
		    result.put("token", tokenDTO);
		    return result;
		    }
	}

	public UserDTO getByUserId(int userId) {
		List<UserPO> userPOList=userMapper.getByUserId(userId);
		if(userPOList.size()>0) {
			UserDTO userDTO=new UserDTO();
			BeanUtils.copyProperties(userPOList.get(0), userDTO);
			return userDTO;
		}
		return null;
	}

	public List<UserDTO> listAll(String token) {
		TokenDTO tokenDTO = tokenService.getByToken(token);
		if (tokenDTO == null) {
			return null;
		}
		List<UserDTO> userPOList=userMapper.get();
		List<UserDTO> userDTOList=new ArrayList<UserDTO>();
		if(userPOList.size()==0) {
			return null;
		}
			for(int i=0;i<userPOList.size();i++) {
				UserDTO userDTO=new UserDTO();
					BeanUtils.copyProperties(userPOList.get(i), userDTO);
					System.out.println(userDTO);
					userDTO.setProjectList(this.getProjectList(userDTO.getUserId()));
					userDTOList.add(userDTO);
			}
			return userDTOList;
	}
	public List<ProjectDTO> getProjectList(int userId) {
		System.out.println("aaa");
		List<ProjectDTO> result = new ArrayList<ProjectDTO>();
		List<UserProjectRelationPO> userProjectPOList = projectmapper.getUserProjectPOByUserId(userId);
		System.out.println(userProjectPOList);
		for (UserProjectRelationPO userProjectPO : userProjectPOList) {
			ProjectDTO projectDTO = projectService.getByProjectId(userProjectPO.getProjectId());
			System.out.println(projectDTO);
			result.add(projectDTO);
		}
		return result;
	}
}