package com.yonyou.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.yonyou.domain.dto.ProjectDTO;


public interface ProjectService {

	public List<ProjectDTO> listByUserId(String token,int userId);
	
	public ProjectDTO get(String token,int projectId);

	public ProjectDTO add(String token, String projectName, String projectManager, String beginTime, String endTIme);

	public boolean delete(String token, int projectId);

	public ProjectDTO update(String token,int projectId, String projectName, String projectManager, String beginTime, String endTime,
			String desc);

	public Map<String, Object> addUsers(String token, int projectId, String userIds);

	public List<ProjectDTO> getAll(String token);

	public List<ProjectDTO> getProjectList(int userId);

	ProjectDTO getByProjectId(int projectId);

	//public List<ProjectDTO> file(MultipartFile file);
}
