package net.water;

/**
 * User: su
 * <br/>项目常量定义
 */
public class Constants {

    //================常量定义begin==========
    /**常数定义，默认每页显示多少条记录(25)*/
    public final static int NUM_PAGE_PER = 25;
    /**数据库数据状态,逻辑删除*/
    public final static int STATUS_DISABLE = 0;
    /**数据库数据状态,有效*/
    public final static int STATUS_ENABLE = 1;
    
    /**模板文件根路径(相对WebRoot)*/
    public final static String PATH_TEMPLATE = "/WEB-INF/templates";
    
    /**网站首页*/
    public final static String SYS_INDEX = "/front/login/index.action";
    //================常量定义end==========

	//===================参数名begin==========
    /**参数名，用户基本信息entity*/
    public final static String PARAM_USER_BASE_INFO = "userLoginBaseInfo";
    /**参数名，当前登录用户的ID*/
    public final static String PARAM_USER_LOGIN_ID = "loginUserId";
    /**参数名，当前登录用户的用户名*/
    public final static String PARAM_USER_LOGIN_NAME = "loginUserName";
    /**参数名，错误提示信息*/
    public final static String PARAM_ERROR_MSG = "errorMsg";
    /**参数名，操作提示信息*/
    public final static String PARAM_SHOW_MSG = "showMsg";
    //===================参数名end==========

    //====================提示信息begin=========
    /**提示信息,操作成功*/
    public final static String INFO_SUC = "suc";
    /**提示信息,程序异常*/
    public final static String INFO_EXCEPTION = "程序异常，请联系QQ305371288";
    /**提示信息,登录超时*/
    public final static String INFO_USERNOLOGIN = "请重新登录";
    /**提示信息,无权限*/
    public final static String INFO_USERNOSECURITY = "当前登录账户没有该操作权限";
  //====================提示信息end=========
}
