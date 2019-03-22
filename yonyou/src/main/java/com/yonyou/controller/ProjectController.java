package com.yonyou.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yonyou.domain.dto.ProjectDTO;
import com.yonyou.domain.json.BaseJSON;
import com.yonyou.service.ProjectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/project")
@Api("project模块")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;

	@ApiOperation("通过人员id查询项目列表")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType="query",name="token",dataType="String",value="token",defaultValue="123456"),
		@ApiImplicitParam(paramType = "query", name = "userId", dataType = "Integer", required = true, value = "userId", defaultValue = "1")
	})
	@RequestMapping(value = "/listByUserId", method = RequestMethod.GET)
	public BaseJSON listByUserId(String token,int userId) {
		BaseJSON baseJSON = new BaseJSON();
		List<ProjectDTO> projectDTO=projectService.listByUserId(token, userId);
		if(projectDTO.size()==0) {
			baseJSON.setCode(1);
			baseJSON.setMessage("查询失败");
		}
		baseJSON.setResult(projectDTO);
		return baseJSON;
	}
	
	@ApiOperation("通过项目id查询项目详情")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType="query",name="token",dataType="String",value="token",defaultValue="123456"),
		@ApiImplicitParam(paramType="query",name="projectId",dataType="Integer",required=true,value="项目Id",defaultValue="1")
	})
	@RequestMapping(value="/get",method=RequestMethod.GET)
	public BaseJSON get(String token,int projectId) {
		BaseJSON baseJSON=new BaseJSON();
		ProjectDTO projectDTO=projectService.get(token,projectId);
		if(projectDTO==null) {
			baseJSON.setCode(1);
			baseJSON.setMessage("查询失败");
		}
		baseJSON.setResult(projectDTO);
		return baseJSON;
	}
	@ApiOperation("添加项目")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType="query",name="token",dataType="String",value="token",defaultValue="123456"),
		@ApiImplicitParam(paramType="query",name="projectName",dataType="String",value="项目名称",defaultValue="gan"),
		@ApiImplicitParam(paramType="query",name="beginTime",dataType="Date",value="开始时间",defaultValue="2018-12-01"),
		@ApiImplicitParam(paramType="query",name="endTime",dataType="Date",value="结束时间",defaultValue="2018-12-30"),
		@ApiImplicitParam(paramType="query",name="desc",dataType="String",value="备注描述",defaultValue="no description"),
	})
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public BaseJSON add(String token,String projectName,String projectManager,String beginTime,String endTime) {
		BaseJSON baseJSON=new BaseJSON();
		ProjectDTO projectDTO=projectService.add(token,projectName,projectManager,beginTime,endTime);
		if(projectDTO==null) {
			baseJSON.setCode(1);
			baseJSON.setMessage("添加失败");
		}	
		baseJSON.setResult(projectDTO);	
		return baseJSON;
	}
	
	@ApiOperation("删除项目")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType="query",name="token",dataType="String",value="token",defaultValue="123456"),
		@ApiImplicitParam(paramType="query",name="projectId",dataType="int",required=true,value="项目Id",defaultValue="1")
	})	
	@RequestMapping(value="/delete",method=RequestMethod.GET)
	public BaseJSON delete(String token,int projectId) {
		BaseJSON baseJSON=new BaseJSON();
		if(!projectService.delete(token,projectId)) {	//修改两个表
			baseJSON.setCode(1);
			baseJSON.setMessage("删除失败");
		}
		baseJSON.setResult(null);
		return baseJSON;
	}
	
	@ApiOperation("修改项目")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType="query",name="token",dataType="String",value="token",defaultValue="123456"),
		@ApiImplicitParam(paramType="query",name="projectId",dataType="int",value="项目id",defaultValue="1"),
		@ApiImplicitParam(paramType="query",name="projectName",dataType="String",value="项目名称",defaultValue="yonyou"),
		@ApiImplicitParam(paramType="query",name="projectManager",dataType="String",value="项目组长",defaultValue="yonyou"),
		@ApiImplicitParam(paramType="query",name="beginTime",dataType="String",value="开始时间",defaultValue="2019-01-01"),
		@ApiImplicitParam(paramType="query",name="endTime",dataType="String",value="结束时间",defaultValue="2019-01-30"),
		@ApiImplicitParam(paramType="query",name="desc",dataType="String",value="备注描述",defaultValue="had already fixed"),
	})
	@RequestMapping(value="/update",method=RequestMethod.GET)
	public BaseJSON update(String token,int projectId,String projectName,String projectManager,String beginTime,String endTime,String desc) {
		BaseJSON baseJSON=new BaseJSON();
		ProjectDTO projectDTO=projectService.update(token,projectId,projectName,projectManager,beginTime,endTime,desc);
		if(projectDTO==null) {
			baseJSON.setCode(1);
			baseJSON.setMessage("修改失败");
		}
		baseJSON.setResult(projectDTO);
		return baseJSON;
	}
	@ApiOperation("添加人员到项目")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType="query",name="token",dataType="String",value="token",defaultValue="123456"),
		@ApiImplicitParam(paramType="query",name="projectId",dataType="int",required=true,value="项目Id",defaultValue="1"),
		@ApiImplicitParam(paramType="query",name="userIds",dataType="String",required=true,value="人员Id",defaultValue="1,2")
	})
	@RequestMapping(value="/addUsers",method=RequestMethod.GET)
	public BaseJSON addUsers(String token,int projectId,String userIds) {
		BaseJSON baseJSON=new BaseJSON();
		Map<String,Object> map=projectService.addUsers(token,projectId,userIds);
		if(map==null) {
			baseJSON.setCode(1);
			baseJSON.setMessage("添加失败");
		}
		baseJSON.setResult(map);
		return baseJSON;
	}
	@ApiOperation("查询项目详情以及人员")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType="query",name="token",dataType="String",value="token",defaultValue="123456"),
		//@ApiImplicitParam(paramType="query",name="projectId",dataType="Integer",required=true,value="项目Id",defaultValue="1")
	})
	@RequestMapping(value="/getAll",method=RequestMethod.GET)
	public BaseJSON getAll(String token) {
		BaseJSON baseJSON=new BaseJSON();
		List<ProjectDTO> projectDTO=projectService.getAll(token);
		if(projectDTO==null) {
			baseJSON.setCode(1);
			baseJSON.setMessage("查询失败");
		}
		baseJSON.setResult(projectDTO);
		return baseJSON;
	}
	/*@ApiOperation("导入")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "file", dataType = "file", required = true, value = "导入", defaultValue = ""),
	})
	@RequestMapping(value="/file",method=RequestMethod.POST)
	public BaseJSON file(MultipartFile file) {
		System.out.println(file);
		BaseJSON baseJSON=new BaseJSON();
		List<ProjectDTO> projectDTO=projectService.file(file);
		if(projectDTO==null) {
			baseJSON.setCode(1);
			baseJSON.setMessage("查询失败");
		}
		baseJSON.setResult(projectDTO);
		return baseJSON;
	}*/
}