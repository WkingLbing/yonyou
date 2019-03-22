/**
 * 
 */
package com.yonyou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yonyou.domain.po.TokenPO;


/**
 * 功能说明：
 * 创建者：梅琳  
 * E-mail: 2253967185@qq.com 
 * 修改时间:2018年11月15日 下午5:27:34                
 * 修改内容：
 * 修改者:梅琳
 * 版本：0.1
 */
@Mapper
public interface TokenMapper {
	
	public int add(TokenPO tokenPO);
	public List<TokenPO> getByUserId(@Param("userId")int userId);
	public List<TokenPO> getByToken(@Param("token")String token);
	

}
