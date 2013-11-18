package net.water.security.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.kuakao.core.base.util.FormTokenUtil;
import net.sf.json.JSONObject;
import net.water.Constants;
import net.water.security.dto.SecUrlDto;
import net.water.security.entity.SecUrlEntity;
import net.water.security.service.ISecUrlService;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/secUrl")
public class SecUrlAdminController {
	
	@Autowired
	private ISecUrlService secUrlService;
	protected final Log log = LogFactory.getLog(getClass());

	@RequestMapping("index")
	public String index(HttpServletRequest request,@ModelAttribute("_page") SecUrlDto secUrlDto, Model model) throws Exception {
		secUrlDto.setPerPage(Constants.NUM_PAGE_PER);
		secUrlDto.setQueryStr(request.getQueryString() == null ? "" : request.getQueryString());
		if(StringUtils.isNotBlank(secUrlDto.getUrlName())){
			secUrlDto.setUrlName(secUrlDto.getUrlName().trim());
		}
		if(StringUtils.isNotBlank(secUrlDto.getUrlPath())){
			secUrlDto.setUrlPath(secUrlDto.getUrlPath().trim());
		}
		List<SecUrlEntity> secUrlEntitys = secUrlService.querySecUrlByPage(secUrlDto ,model);
		model.addAttribute("secUrlEntitys", secUrlEntitys);
		return "admin/security/secUrl_index";
	}
	
	@RequestMapping("edit")
	public String edit(HttpServletRequest request,@ModelAttribute("secUrlDto") SecUrlDto secUrlDto, Model model) throws Exception {
		secUrlDto.setFormTokenCode(request);
		secUrlService.getSecUrlById(secUrlDto ,model);
		return "admin/security/secUrl_edit";
	}
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(@ModelAttribute("secUrlDto") SecUrlDto secUrlDto, Model model,HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(FormTokenUtil.validFormToken(request, response, secUrlDto, true)) {
			return "admin/security/secUrl_edit";
		}
		secUrlService.saveSecUrl(secUrlDto ,model);
		return "redirect:/admin/secUrl/index.action?" + secUrlDto.getDecodeQueryStr();
	}
	
	
	@RequestMapping(value="destroy",method=RequestMethod.POST)
	public void destroy(SecUrlDto secUrlDto, Model model, HttpServletResponse response) throws Exception {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		Map<String,String> msgs = new HashMap<String,String>();
		
		try {
			secUrlService.destroySecUrl(secUrlDto ,model, msgs);
		} catch (Exception e) {
			msgs.put("error", e.getMessage());
		}
		
		writer.print(JSONObject.fromObject(msgs));
		writer.flush();
	}

}
