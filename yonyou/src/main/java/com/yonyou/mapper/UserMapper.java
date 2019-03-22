/**
 * 功能说明 :
 * 创建者 : 段明磊
 * E-mail : duanml1@yonyou.com
 */
package com.yonyou.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.yonyou.domain.dto.UserDTO;
import com.yonyou.domain.po.UserPO;


/**
* 功能说明：
* 创建者：段明磊
* E-mail: duanml1@yonyou.com
* 创建时间：2018年8月7日
* <pre>
* 修改时间:       修改者:            
* 修改内容：
* </pre>
* 版本：0.1
*/
@Mapper
public interface UserMapper {
	
	public int add(UserPO userPO);
	public int remove(int id);
	public List<UserPO> getByAccount(String account);
	public List<UserDTO> get();
	public List<UserPO> getByUserId(int userId);
	public int delete(UserPO userPO);
	public void updatePassword(UserPO userPO);
	public void update(UserPO userPO);
	public List<UserDTO> list(int userId);


}
