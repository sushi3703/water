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
import net.water.security.dto.SecResGroupDto;
import net.water.security.entity.SecResGroupEntity;
import net.water.security.service.ISecResGroupService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin/secResGroup")
public class SecResGroupAdminController {
	
	@Autowired
	private ISecResGroupService secResGroupService;
	
	@RequestMapping("index")
	public String index(HttpServletRequest request,@ModelAttribute("_page") SecResGroupDto secResGroupDto, Model model) throws Exception {
		secResGroupDto.setQueryStr(request.getQueryString() == null ? "" : request.getQueryString());
		secResGroupDto.setPerPage(Constants.NUM_PAGE_PER);
		secResGroupDto.setOwnerId((Integer)request.getAttribute(Constants.PARAM_USER_LOGIN_ID)+"");
		secResGroupDto.setNeedResInfos(true);
		List<SecResGroupEntity> secResGroupEntitys = secResGroupService.querySecResGroupByPage(secResGroupDto ,model);
		model.addAttribute("secResGroupEntitys", secResGroupEntitys);
		return "admin/security/secResGroup_index";
	}
	
	@RequestMapping("edit")
	public String edit(@ModelAttribute("secResGroupDto") SecResGroupDto secResGroupDto, Model model,HttpServletRequest request) throws Exception {
		secResGroupDto.setFormTokenCode(request);
		//基本信息
		secResGroupService.getSecResGroupById(secResGroupDto ,model);
		//资源列表
		List<Map<String, Object>> menuResources = secResGroupService.queryAllResourcesGroupByMenu(secResGroupDto.getGroupId(), (Integer)request.getAttribute(Constants.PARAM_USER_LOGIN_ID));
		model.addAttribute("menuResources", menuResources);
		return "admin/security/secResGroup_edit";
	}
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(@ModelAttribute("secResGroupDto") SecResGroupDto secResGroupDto, Model model,HttpServletRequest request, HttpServletResponse response) throws Exception {
		if(FormTokenUtil.validFormToken(request, response, secResGroupDto, true)) {
			return "admin/security/secResGroup_edit";
		}
		secResGroupDto.setOwnerId((Integer)request.getAttribute(Constants.PARAM_USER_LOGIN_ID)+"");
		secResGroupService.saveSecResGroup(secResGroupDto ,model);
		return "redirect:/admin/secResGroup/index.action?" + secResGroupDto.getQueryStr();
	}
	
	
	@RequestMapping(value="destroy",method=RequestMethod.POST)
	public void destroy(SecResGroupDto secResGroupDto, Model model, HttpServletResponse response) {
		PrintWriter writer = null;
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(writer == null)return;
		Map<String,String> msgs = new HashMap<String,String>();
		try {
			secResGroupService.destroySecResGroup(secResGroupDto ,model, msgs);
		} catch (Exception e) {
			e.printStackTrace();
			msgs.put("error",e.getMessage());
		}
		writer.print(JSONObject.fromObject(msgs));
		writer.flush();
	}

}

