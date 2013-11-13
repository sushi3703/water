<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8" trimDirectiveWhitespaces="true"%>
<%@ page import="net.sf.json.JSONArray" %>
<%
List list = new ArrayList();
Map<String ,String> map=new HashMap<String, String>();
map.put("id", "21");
map.put("label", "label21");
list.add(map);
map=new HashMap<String, String>();
map.put("id", "43");
map.put("label", "label43");
list.add(map);

out.print(JSONArray.fromObject(list.toArray()).toString());
%>