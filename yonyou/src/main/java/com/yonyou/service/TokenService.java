/**
 * 
 */
package com.yonyou.service;

import com.yonyou.domain.dto.TokenDTO;


public interface TokenService {

	public TokenDTO add(int userId);

	TokenDTO getByUserId(int userId);

	public TokenDTO getByToken( String token);
}
