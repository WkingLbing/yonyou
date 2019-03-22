package com.yonyou.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yonyou.domain.dto.ProjectDTO;
import com.yonyou.domain.dto.TokenDTO;
import com.yonyou.domain.dto.UserDTO;
import com.yonyou.domain.po.ProjectPO;
import com.yonyou.domain.po.UserProjectRelationPO;
import com.yonyou.mapper.ProjectMapper;
import com.yonyou.mapper.UserProjectRelationMapper;
import com.yonyou.service.ProjectService;
import com.yonyou.service.TokenService;
import com.yonyou.service.UserProjectService;
import com.yonyou.service.UserService;






@Service
public class PorjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectMapper projectmapper;
	@Autowired
	private UserService userService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	TokenService tokenService;
	@Autowired
	private UserProjectRelationMapper userProMapper;
	@Autowired
	private UserProjectService userProjectService;
	
	public List<ProjectDTO> listByUserId(String token,int userId) {
		List<ProjectDTO> result = new ArrayList<ProjectDTO>();
		TokenDTO tokenDTO = tokenService.getByToken(token);
		System.out.println(tokenDTO);
		if (tokenDTO == null) {
			return null;
		}
			List<UserProjectRelationPO> userProjectPOList = projectmapper.getUserProjectPOByUserId(userId);
			System.out.println(userProjectPOList);
			for (UserProjectRelationPO userProjectPO : userProjectPOList) {
				List<ProjectPO> projectPOList = projectmapper.get(userProjectPO.getProjectId());
				System.out.println(projectPOList);
				if (projectPOList.size() > 0) {
					ProjectDTO projectDTO = new ProjectDTO();
					BeanUtils.copyProperties(projectPOList.get(0), projectDTO);
					projectDTO.setUserList(this.getUserList(projectDTO.getProjectId()));
					System.out.println(projectDTO);
					result.add(projectDTO);
				}
			}
		return result;
	}

	

	public ProjectDTO get(String token,int projectId) {
		TokenDTO tokenDTO = tokenService.getByToken(token);
		System.out.println(tokenDTO);
		if (tokenDTO == null) {
			return null;
		}
			ProjectDTO projectDTO=new ProjectDTO();
			List<ProjectPO> projectPOList=projectmapper.get(projectId);
			System.out.println(projectPOList);
			if(projectPOList.size()>0) {
				BeanUtils.copyProperties(projectPOList.get(0), projectDTO);
				projectDTO.setUserList(this.getUserList(projectId));
				return projectDTO;
			}
		return null;
	}

	private List<UserDTO> getUserList(int projectId) {
		List<UserDTO> result = new ArrayList<UserDTO>();
		List<UserProjectRelationPO> userProjectPOList = projectmapper.getUserProjectPOByProjectId(projectId);
		for (UserProjectRelationPO userProjectPO : userProjectPOList) {
			UserDTO userDTO = userService.getByUserId(userProjectPO.getUserId());
			System.out.println(userDTO);
			result.add(userDTO);
		}
		return result;
	}
	/*@SuppressWarnings("unused")
	private List<ProjectDTO> getProjectList(int projectId) {
		List<ProjectDTO> result = new ArrayList<ProjectDTO>();
		List<UserProjectRelationPO> userProjectPOList = projectmapper.getUserProjectPOByUserId(userId);
		for (UserProjectRelationPO userProjectPO : userProjectPOList) {
			ProjectDTO projectDTO = projectService.getByProjectId(userProjectPO.getProjectId());
			System.out.println(projectDTO);
			result.add(projectDTO);
		}
		return result;
	}*/


	public ProjectDTO add(String token, String projectName, String projectManager, String beginTime, String endTime) {
		TokenDTO tokenDTO = tokenService.getByToken(token);
		if (tokenDTO == null) {
			return null;
		}
		List<ProjectPO> projectPOlList=projectmapper.getByProjectName(projectName);
		if(projectPOlList.size()==0){
			ProjectPO projectPO=new ProjectPO();
			ProjectDTO projectDTO2=new ProjectDTO();
			projectPO.setProjectName(projectName);
			projectPO.setProjectManager(projectManager);
			projectPO.setBeginTime(beginTime);
			projectPO.setEndTime(endTime);
			projectPO.setCreateTime(new Date());
			projectmapper.add(projectPO);
			List<ProjectPO> projectPO2=projectmapper.getByProjectName(projectName);
			System.out.println(projectPO2.toString());
			BeanUtils.copyProperties(projectPO2.get(0), projectDTO2);
			System.out.println(projectDTO2.toString());
			return projectDTO2;
		}
		else {
			return null;
		}
	}
	public boolean delete(String token,int projectId) {
		UserDTO userDTO=userService.get(token);
		if(userDTO!=null) {
			List<ProjectPO> projectPOList=projectmapper.get(projectId);			
			if(projectPOList.size()>0) {
				ProjectPO projectPO=projectPOList.get(0);
				projectPO.setUpdateBy(userDTO.getUserId());
				projectPO.setUpdateTime(new Date());
				projectPO.setIsDelete(1);
				int updateBy=userDTO.getUserId();
				Date updateTime=new Date();
				ProjectDTO projectDTO=new ProjectDTO();projectDTO.setUserList(this.getUserList(projectId));
				if(projectmapper.deleteProject(projectPO)>0||(projectmapper.deleteProject(projectPO)>0 && userProMapper.deleteUserProject(projectId,updateBy,updateTime)>0)) {
					BeanUtils.copyProperties(projectPO, projectDTO);
					return true;
				}
			}
		}		
		return false;
	}



	public ProjectDTO update(String token,int projectId, String projectName, String projectManager, String beginTime, String endTime,
			String desc) {
		UserDTO userDTO=userService.get(token);
		if(userDTO!=null) {
			List<ProjectPO> projectPOList=projectmapper.get(projectId);
			if(projectPOList.size()>0) {
				ProjectPO projectPO=new ProjectPO();
				ProjectDTO projectDTO=new ProjectDTO();
				projectPO.setProjectId(projectId);
				projectPO.setProjectName(projectName);
				projectPO.setProjectManager(projectManager);
				projectPO.setBeginTime(beginTime);
				projectPO.setEndTime(endTime);
				projectPO.setUpdateTime(new Date());
				projectPO.setUpdateBy(projectId);
				projectPO.setDesc(desc);
				BeanUtils.copyProperties( projectPO,projectDTO);
				if(projectmapper.update(projectPO)>0) {
					return projectDTO;
				}
			}
		}
		return null;
	}
	public Map<String, Object> addUsers(String token, int projectId, String ids) {
		UserDTO userDTO = userService.get(token);
		if(userDTO!=null) {
			int operaterUserId=userDTO.getUserId();
			if (userDTO != null) {
				List<ProjectPO> projectDTOList = projectmapper.get(projectId);
				if (projectDTOList.size()>0) {
					List<UserDTO> userDTOList = new ArrayList<UserDTO>();
					String[] userIds = ids.split(",");
					for (String id : userIds) {
						userDTO = userService.getByUserId(Integer.parseInt(id));
						UserProjectRelationPO userProPO = userProjectService.getByUserProId(Integer.parseInt(id),projectId);
						if (userDTO != null && userProPO == null
								&& userProjectService.add(projectId, Integer.parseInt(id),operaterUserId)) {
							userDTOList.add(userDTO);
						}
					}
					if (userDTOList.size() == 0)
						return null;
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("new members", userDTOList);
					map.put("proejct", this.get(token,projectId));
					return map;
				}
			}
		}
		return null;
	}
	public List<ProjectDTO> getAll(String token) {
		TokenDTO tokenDTO = tokenService.getByToken(token);
		System.out.println(tokenDTO);
		if (tokenDTO == null) {
			return null;
		}
		List<ProjectPO> projectPOList=projectmapper.getAll();
		List<ProjectDTO> projectDTOList=new ArrayList<ProjectDTO>();
		if(projectPOList.size()==0) {
			return null;
		}
		for (int i = 0; i < projectPOList.size(); i++) {
			ProjectDTO projectDTO=new ProjectDTO();
				BeanUtils.copyProperties(projectPOList.get(i), projectDTO);
				projectDTO.setUserList(this.getUserList(projectPOList.get(i).getProjectId()));
				System.out.println(projectDTO);
				projectDTOList.add(projectDTO);
		}
		
			return projectDTOList;
		
	}



	public ProjectDTO getByProjectId(int projectId) {
		List<ProjectDTO> projectPOList=projectmapper.getByProjectId(projectId);
		if(projectPOList.size()>0) {
			ProjectDTO projectDTO=new ProjectDTO();
			BeanUtils.copyProperties(projectPOList.get(0), projectDTO);
			return projectDTO;
		}
		return null;
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



	/*public List<ProjectDTO> file(MultipartFile file) {
		//PoiUtil.getDataFromExcel(file);
		return null;
	}*/
}
