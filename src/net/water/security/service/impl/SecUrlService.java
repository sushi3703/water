package net.water.security.service.impl;

import java.util.List;
import java.util.Map;

import net.kuakao.core.exception.DataBaseException;
import net.water.security.dao.ISecUrlDAO;
import net.water.security.dto.SecUrlDto;
import net.water.security.entity.SecUrlEntity;
import net.water.security.service.ISecUrlService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import su.tool.SuIntUtils;

public class SecUrlService implements ISecUrlService {
	
	@Autowired
	private ISecUrlDAO secUrlDAO;
	
	public List<SecUrlEntity> querySecUrlByPage(SecUrlDto secUrlDto, Model model) throws Exception {
		return secUrlDAO.querySecUrlByPage(secUrlDto);
	}
	
	public List<SecUrlEntity> querySecUrls(SecUrlDto secUrlDto) throws DataBaseException{
		return secUrlDAO.querySecUrls(secUrlDto);
	}

	public SecUrlEntity getSecUrlById(SecUrlDto secUrlDto, Model model) throws Exception {
		if(secUrlDto == null || StringUtils.isBlank(secUrlDto.getUrlId())){
			return null;
		}
		SecUrlEntity secUrlEntity = secUrlDAO.getSecUrlById(SuIntUtils.getInt(secUrlDto.getUrlId()));
		if(secUrlEntity != null) {
			secUrlEntity.toSecUrlDto(secUrlDto);
		}
		return secUrlEntity;
	}

	public void saveSecUrl(SecUrlDto secUrlDto, Model model) throws Exception {
		if(StringUtils.isNotBlank(secUrlDto.getUrlName())){
			secUrlDto.setUrlName(secUrlDto.getUrlName().trim());
		}
		if(StringUtils.isNotBlank(secUrlDto.getUrlPath())){
			secUrlDto.setUrlPath(secUrlDto.getUrlPath().trim());
		}
		if(StringUtils.isNotBlank(secUrlDto.getUrlId())) {
			secUrlDAO.updateSecUrl(secUrlDto);
		} else {
			secUrlDAO.createSecUrl(secUrlDto.toSecUrlEntity());
		}
	}

	public void destroySecUrl(SecUrlDto secUrlDto, Model model, Map<String,String> msgs) throws Exception {
		if(secUrlDto == null || StringUtils.isBlank(secUrlDto.getUrlId())) {
			msgs.put("error","请选择要删除的urlId");
			return;
		}
		secUrlDAO.destroySecUrl(Integer.parseInt(secUrlDto.getUrlId()));
		msgs.put("success","删除成功!");
	}

}

