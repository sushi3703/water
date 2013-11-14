package net.water.security.service;
import java.util.List;
import java.util.Map;

import net.water.security.dto.SecResourceDto;
import net.water.security.entity.SecResourceEntity;

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

}
