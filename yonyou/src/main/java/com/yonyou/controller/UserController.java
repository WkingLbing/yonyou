package com.yonyou.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yonyou.domain.dto.UserDTO;
import com.yonyou.domain.json.BaseJSON;
import com.yonyou.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@ApiOperation("添加人员信息")
	@ApiImplicitParams({
		//@ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "token", defaultValue = "1") ,
		@ApiImplicitParam(paramType = "query", name = "username", dataType = "String", required = true, value = "用户名", defaultValue = ""),
		@ApiImplicitParam(paramType = "query", name = "account", dataType = "String", required = true, value = "账号", defaultValue = ""),
		@ApiImplicitParam(paramType = "query", name = "password", dataType = "String", required = true, value = "密码", defaultValue = ""),
		@ApiImplicitParam(paramType = "query", name = "sex", dataType = "Integer", required = true, value = "性别", defaultValue = ""),
		@ApiImplicitParam(paramType = "query", name = "positionId", dataType = "Integer", required = true, value = "职位", defaultValue = "")
	})
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public BaseJSON add(String username,String account,String password,int sex,int positionId) {
		BaseJSON baseJSON = new BaseJSON();
		UserDTO userDTO=userService.add(username,account, password,sex,positionId);
		if(userDTO!=null){
			baseJSON.setResult(userDTO);
		}
		else {
			baseJSON.setCode(1);
			baseJSON.setMessage("用户已存在");
		}
		return baseJSON;
	}
	@ApiOperation("查询所有用户")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "token", dataType = "string", required = true, value = "token", defaultValue = "123456")
			})
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public BaseJSON list(String token) {
		BaseJSON baseJSON=new BaseJSON();
		baseJSON.setResult(userService.list(token));
		return baseJSON;
	}
	@ApiOperation("查询所有用户以及项目详情")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "token", dataType = "string", required = true, value = "token", defaultValue = "123456")
			})
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public BaseJSON listAll(String token) {
		BaseJSON baseJSON=new BaseJSON();
		baseJSON.setResult(userService.listAll(token));
		return baseJSON;
	}
	
	@ApiOperation("修改密码")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", name = "userId", dataType = "string", required = true, value = "用户Id", defaultValue = "123"),
			@ApiImplicitParam(paramType = "query", name = "password", dataType = "string", required = true, value = "密码", defaultValue = "123"),
			@ApiImplicitParam(paramType = "query", name = "newPassword", dataType = "string", required = true, value = "新密码", defaultValue = "123456"),
			@ApiImplicitParam(paramType = "query", name = "token", dataType = "string", required = true, value = "token", defaultValue = "123456")
	})
	@RequestMapping(value = "/updatePassword", method = RequestMethod.GET)
	public BaseJSON updatePassword(int userId,String password,String newPassword,String token) {
		BaseJSON baseJSON=new BaseJSON();
		UserDTO userDTO=userService.updatePassword(userId,password, newPassword,token);
		if(userDTO!=null){
			baseJSON.setCode(0);
			baseJSON.setResult(userDTO);
			
		}
		else {
			baseJSON.setCode(1);
			baseJSON.setMessage("密码验证不正确");
		}
		
		return baseJSON;
		
	}
	@ApiOperation("删除用户信息")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "userId", dataType = "string", required = true, value = "userId", defaultValue = "123456"),
			@ApiImplicitParam(paramType = "query", name = "token", dataType = "string", required = true, value = "token", defaultValue = "123456")
	})
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public BaseJSON delete(int userId,String token) {
		BaseJSON baseJSON=new BaseJSON();
		int count=userService.delete(userId,token);
		System.out.println(count);
		if (count!=0) {
			baseJSON.setCode(0);
			baseJSON.setMessage("用户删除成功");
		}
		else {
			baseJSON.setCode(1);
			baseJSON.setMessage("用户不存在");
		}
		return baseJSON;
	}
	@ApiOperation("修改用户信息")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "userId", dataType = "string", required = true, value = "用户Id", defaultValue = "123"),
			@ApiImplicitParam(paramType = "query", name = "username", dataType = "string", required = true, value = "用户名", defaultValue = "123"),
			@ApiImplicitParam(paramType = "query", name = "sex", dataType = "string", required = true, value = "性别", defaultValue = "123456"),
			@ApiImplicitParam(paramType = "query", name = "positionId", dataType = "Integer", required = true, value = "职位", defaultValue = "123456"),
			@ApiImplicitParam(paramType = "query", name = "token", dataType = "string", required = true, value = "token", defaultValue = "123456")
	})
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public BaseJSON update( int userId,String username,int sex,int positionId,String token) {
		BaseJSON baseJSON=new BaseJSON();
		UserDTO userDTO=userService.update(userId,username, sex,positionId,token);
		if(userDTO!=null){
			baseJSON.setCode(0);
			baseJSON.setResult(userDTO);
			
		}
		else {
			baseJSON.setCode(1);
			baseJSON.setMessage("修改失败");
		}
		
		return baseJSON;
		
	}
	@ApiOperation("通过token获取用户信息")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "过期的token", defaultValue = "123456"),
			})
	@RequestMapping(value="getUserByToken",method=RequestMethod.GET)
	public BaseJSON getUserByToken(String token) {
		UserDTO userDTO=userService.get(token);
		BaseJSON baseJSON=new BaseJSON();
		if(userDTO!=null){
			baseJSON.setResult(userDTO);
		}
		else {
			baseJSON.setCode(1);
			baseJSON.setMessage("失败");
		}
		
		return baseJSON;
	}
	@ApiOperation("帐号密码登陆")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "account", dataType = "String", required = true, value = "账号", defaultValue = "111"),
			@ApiImplicitParam(paramType = "query", name = "password", dataType = "String", required = true, value = "密码", defaultValue = "111") })
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public BaseJSON login(String account, String password) {
		BaseJSON baseJSON = new BaseJSON();
		Map<String, Object> map = userService.login(account, password);
		if (map == null) {
			baseJSON.setCode(1);
			baseJSON.setMessage("登陆失败");
		} else {
			baseJSON.setResult(map);
		}
		return baseJSON;
	}
	@ApiOperation("刷新token")
	@ApiImplicitParams({
			@ApiImplicitParam(paramType = "query", name = "token", dataType = "String", required = true, value = "过期的token", defaultValue = "123456"),
			@ApiImplicitParam(paramType = "query", name = "refToken", dataType = "String", required = true, value = "刷新token", defaultValue = "654321")})
	@RequestMapping(value="/token",method=RequestMethod.GET)
	public BaseJSON token(String token,String refToken) {
		BaseJSON baseJSON=new BaseJSON();
		Map<String,Object> map=userService.reftoken(token, refToken);
		if(map==null) {
			baseJSON.setCode(1);
			baseJSON.setMessage("操作失败");
		}else {
			baseJSON.setResult(map);
		}
		
		return baseJSON;
	}
}
