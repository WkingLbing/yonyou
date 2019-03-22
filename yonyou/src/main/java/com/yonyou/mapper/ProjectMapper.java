package com.yonyou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yonyou.domain.dto.ProjectDTO;
import com.yonyou.domain.po.ProjectPO;
import com.yonyou.domain.po.UserProjectRelationPO;



@Mapper
public interface ProjectMapper {

	public List<UserProjectRelationPO> getUserProjectPOByProjectId(@Param("projectId") int projectId);

	public List<UserProjectRelationPO> getUserProjectPOByUserId(@Param("userId") int userId);

	public List<ProjectPO> get(@Param("projectId") int projectId);

	public int add(ProjectPO projectPO);

	public List<ProjectPO> getByProjectName(String projectName);
	
	public int update(ProjectPO projectPO);
	
	public int deleteProject(ProjectPO projectPO);

	public List<ProjectPO> getAll();

	public List<ProjectDTO> getByProjectId(int projectId);

}
