package net.water.security.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.kuakao.core.base.util.FormTokenUtil;
import net.sf.json.JSONObject;
import net.water.Constants;
import net.water.security.dto.SecResourceDto;
import net.water.security.entity.SecResourceEntity;
import net.water.security.service.ISecResourceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/secResource")
public class SecResourceAdminController {
	
	@Autowired
	private ISecResourceService secResourceService;

	@RequestMapping("index")
	public String index(HttpServletRequest request,@ModelAttribute("_page") SecResourceDto secResourceDto, Model model) throws Exception {
		secResourceDto.setPerPage(Constants.NUM_PAGE_PER);
		secResourceDto.setQueryStr(request.getQueryString() == null ? "" : request.getQueryString());
		secResourceDto.setNeedUrlInfos(true);
		List<SecResourceEntity> secResourceEntitys = secResourceService.querySecResources(secResourceDto ,model);
		model.addAttribute("secResourceEntitys", secResourceEntitys);
		return "admin/security/secResource_index";
	}
	
	@RequestMapping("edit")
	public String edit(@ModelAttribute("secResourceDto") SecResourceDto secResourceDto, Model model,HttpServletRequest request) throws Exception {
		secResourceDto.setFormTokenCode(request);
		//基本信息
		secResourceService.getSecResourceById(secResourceDto ,model);
		//url信息
		List<Map<String,Object>> menuUrls = secResourceService.queryAllUrls(secResourceDto.getResId());
		model.addAttribute("menuUrls", menuUrls);
		return "admin/security/secResource_edit";
	}
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(@ModelAttribute("secResourceDto") SecResourceDto secResourceDto, Model model,HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(FormTokenUtil.validFormToken(request, response, secResourceDto, true)) {
			return "admin/security/secResource_edit";
		}
		secResourceService.saveSecResource(secResourceDto ,model);
		return "redirect:/admin/secResource/index.action?" + secResourceDto.getQueryStr();
	}
	
	
	@RequestMapping(value="destroy",method=RequestMethod.POST)
	public void destroy(SecResourceDto secResourceDto, Model model, HttpServletResponse response) {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if(writer == null)return;
		Map<String,String> msgs = new HashMap<String,String>();
		
		try {
			secResourceService.destroySecResource(secResourceDto ,model,msgs);
		} catch (Exception e) {
			msgs.put("error", e.getMessage());
		}
		
		writer.print(JSONObject.fromObject(msgs));
		writer.flush();
	}

}

