package net.water.base.controller;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.water.Constants;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import su.tool.Configuration;

@Controller
@RequestMapping("/common")
public class CommonController {
	
	/**
	 * 公用的css和js
	 * @param response
	 * @param request
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping("include_css_and_js")
	public void includeCssAndJs(HttpServletResponse response,HttpServletRequest request, Model model) throws Exception {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer = response.getWriter();
		
		String filePath = Configuration.getValue("path_WebRoot")+Constants.PATH_TEMPLATE+"/common/common_include_css_js.htm";
		String includeInfo = FileUtils.readFileToString(new File(filePath), "utf-8");
		writer.println(includeInfo);
		writer.flush();
	}

}

