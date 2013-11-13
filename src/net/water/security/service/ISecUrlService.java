package net.water.security.service;
import java.util.List;
import java.util.Map;

import net.kuakao.core.exception.DataBaseException;
import net.water.security.dto.SecUrlDto;
import net.water.security.entity.SecUrlEntity;

import org.springframework.ui.Model;


public interface ISecUrlService {
	
	/**
	 * 分页查询
	 * @param secUrlDto Dto
	 * @return 列表
	 * @throws Exception
	 */
	public List<SecUrlEntity> querySecUrlByPage(SecUrlDto secUrlDto, Model model) throws Exception;
	
	/**
	 * 查询全部url
	 * @param secUrlDto
	 * @return
	 * @throws DataBaseException
	 */
	public List<SecUrlEntity> querySecUrls(SecUrlDto secUrlDto) throws DataBaseException;
	
	/**
	 * 查询单个对象
	 * @param secUrlDto Dto
	 * @return 
	 * @throws Exception
	 */
	public SecUrlEntity getSecUrlById(SecUrlDto secUrlDto, Model model) throws Exception;
	
	
	/**
	 * 保存或更新
	 * @param secUrlDto
	 * @throws Exception
	 */
	public void saveSecUrl(SecUrlDto secUrlDto, Model model) throws Exception;
	
	/**
	 * 删除
	 * @param secUrlDto
	 * @param model
	 * @throws Exception
	 */
	public void destroySecUrl(SecUrlDto secUrlDto, Model model, Map<String,String> msgs) throws Exception;

}
