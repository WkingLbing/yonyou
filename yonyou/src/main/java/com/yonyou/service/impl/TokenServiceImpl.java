/**
 * 
 */
package com.yonyou.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.Utils;
import com.yonyou.domain.dto.TokenDTO;
import com.yonyou.domain.po.TokenPO;
import com.yonyou.mapper.TokenMapper;
import com.yonyou.service.TokenService;


/**
 * 功能说明：
 * 创建者：梅琳  
 * E-mail: 2253967185@qq.com 
 * 修改时间:2018年11月15日 下午5:20:14                
 * 修改内容：
 * 修改者:梅琳
 * 版本：0.1
 */
@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	private TokenMapper tokenMapper;
	
	public TokenDTO add(int userId) {
		TokenPO tokenPO=new TokenPO();
		tokenPO.setToken(Utils.md5(System.currentTimeMillis()+"token"));
		tokenPO.setRefToken(Utils.md5(System.currentTimeMillis()+"ref_token"));
		tokenPO.setUserId(userId);
		tokenPO.setExpireTime(new Date(new Date().getTime()+10*3600*1000));
		tokenPO.setCreateTime(new Date());
		if(tokenMapper.add(tokenPO)>0) {
			TokenDTO tokenDTO=new TokenDTO();
			BeanUtils.copyProperties(tokenPO, tokenDTO);
			return tokenDTO;
		}
		return null;
		
	}

	
	public TokenDTO getByUserId(int userId) {
		TokenDTO tokenDTO=new TokenDTO();
		List<TokenPO> list=tokenMapper.getByUserId(userId);
		if(list.size()>0) {
			BeanUtils.copyProperties(list.get(0), tokenDTO);
			return tokenDTO;
		}
		return null;
		
	}

	
	public TokenDTO getByToken(String token) {
		TokenDTO tokenDTO=new TokenDTO();
		List<TokenPO> list=tokenMapper.getByToken(token);
		if(list.size()>0) {
			BeanUtils.copyProperties(list.get(0), tokenDTO);
			return tokenDTO;
		}
		return null;
	}

	

}
