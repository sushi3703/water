package net.water.security.service;
import java.util.List;
import java.util.Map;

import net.kuakao.core.exception.DataBaseException;
import net.water.security.dto.SecResourceDto;
import net.water.security.entity.SecResourceEntity;
import net.water.security.entity.SecUrlEntity;

import org.springframework.ui.Model;


public interface ISecResourceService {
	
	/**
	 * 权限资源(分页)查询
	 * @param secResourceDto 权限资源Dto
	 * @return 权限资源列表
	 * @throws Exception
	 */
	public List<SecResourceEntity> querySecResources(SecResourceDto secResourceDto, Model model) throws Exception;

	/**
	 * 取全部url列表（按菜单分组）（并确定资源已选择的url）
	 * @param resId
	 * @return
	 */
	public List<Map<String,Object>> queryAllUrls(String resId);
	
	/**
	 * 查询单个权限资源对象
	 * @param secResourceDto 权限资源Dto
	 * @return 权限资源
	 * @throws Exception
	 */
	public SecResourceEntity getSecResourceById(SecResourceDto secResourceDto, Model model) throws Exception;
	
	
	/**
	 * 保存或更新权限资源
	 * @param secResourceDto
	 * @throws Exception
	 */
	public void saveSecResource(SecResourceDto secResourceDto, Model model) throws Exception;
	
	/**
	 * 删除权限资源
	 * @param secResourceDto
	 * @param model
	 * @throws Exception
	 */
	public void destroySecResource(SecResourceDto secResourceDto, Model model, Map<String,String> msgs) throws Exception;
	
	/**
	 * 取用户的权限菜单
	 * @param userId
	 * @return map的key是菜单id，value是该菜单下的所有可见的urlEntity
	 */
	public Map<Integer, List<SecUrlEntity>> getUserSecMenu(String userId);
	
	/**
	 * 修改用户权限时的资源数据
	 * @param adminId 管理员id
	 * @param userId 待修改的用户id
	 * @return List<菜单属性>,Map<菜单名,资源列表等属性>,List<Map<resId/resName等要展示的资源属性,key的相应值>>
	 */
	public List<Map<String,Object>> getUserResourceOfTeam(String adminId,String userId);
	
	/**
	 * 修改用户的资源数据
	 * @param userId
	 * @param selRes
	 * @throws DataBaseException
	 */
	public void updateUserRes(String userId,String selRes) throws DataBaseException;

}
