/**
 * SeaxConstants.java 
 * Copyright © 2009-2015, 杭州跃度网络科技有限公司
 * 
 * @author Liu Tao
 * @create 2014年11月1日
 */
package com.chuck.shop.constant;

/**
 * 常量类
 */
public interface ShopConstants {

	String MASTER_INSTANCE = "MASTER_INSTANCE";
	long MASTER_HEARTBEAT_TIME = 600000;

	String SYSTEM_USER = "SYSTEM";
	String EMPTY = "";
	String BLANK = " ";
	String COMMA = ",";
	String SEMICOLON = ";";
	String COLON = ":";
	String HYPHEN = "-";
	String POINT = ".";
	String SLASH = "/";
	String BLACKSLASH = "\\";
	String PLUS = "+";
	String UNDERLINE = "_";
	String SHARP = "#";
	String DOLLAR = "$";
	String AT = "@";
	String IMG_SUM_PREFIX_NAME = "_sum";
	String IMG_ROTATE_PREFIX_NAME = "_rotate";
	String IMG_CROP_PREFIX_NAME = "_crop";
	String NULL_VALUE = "null";
	String UNKNOWN = "NON";
	String SEPERATE_STR = "=SEPERATE=";

	Integer ACTION_ADD = 1;
	Integer ACTION_UPDATE = 2;
	Integer ACTION_SUBSTRACT = -1;
	
	
	/**
	 * 配置文件相关
	 */
	String SYS_LOC = "sys.loc";
	String APPLICATION_CONF_LOC = "/conf/application.properties";
	String DB_CONF_LOC = "/conf/db.properties";
	String CACHE_CONF_LOC = "/conf/cache.properties";
	String EMAIL_CONF_LOC = "/conf/email.properties";
	
	

	/**
	 * 统一Session相关属性
	 */
	String SESSION_USER_ATTRIBUTE_NAME = "user";
	String SESSION_MENU = "menus";
	String SESSION_POSITION = "position";
	String COOKIE_KEY = "TOKEN";

	String APPSESSION_ATTR_ACCOUNT_ID = "userid";
	String APPSESSION_ATTR_ACCOUNT = "user";
	String APPSESSION_ATTR_ADMIN = "admin";
	String APPSESSION_ATTR_USER_TYPE = "user_type";
	String APPSESSION_ATTR_OSTYPE = "OS_TYPE";
	String APPSESSION_ATTR_IDCLIENTTYPE = "idClientType";
	String APPSESSION_ATTR_ACCOUNTID = "uid";
	String APPSESSION_ATTR_LOGINNAME = "loginName";
	String APPSESSION_ATTR_AUTHMODULES = "authModules";
	String APPSESSION_ATTR_AUTHMODULECODES = "authModuleCodes";
	String APPSESSION_ATTR_ALLOWPUSH = "allowPush";
	String APPSESSION_ATTR_IMEI = "imei";
	String APPSESSION_ATTR_DEVICETOKEN = "deviceToken";
	String APPSESSION_ATTR_FAMILY_ID = "familyid";
	String APPSESSION_ATTR_ORG_ID = "orgid";
	String APPSESSION_ATTR_ORGS = "orgs";
	String APPSESSION_ATTR_CURRENT_ORG = "org";
	String APPSESSION_ATTR_BRAND = "brand";
	String APPSESSION_ATTR_USER_POSITION = "userPosition";
	
	Integer DEFAULT_CACHE_EXPIRATION = 30 * 24 * 3600;

	String CACHE_ACCESSTOKEN = "ACCESSTOKEN_";

	String CACHE_USER_FREEZED="freezed";
	String CACHE_USER_SESSIONID="sessionId";

	/**
	 * JSON
	 */
	String JSON_SUCCESS = "success";
	String JSON_MESSAGE = "message";
	String JSON_DATA = "data";

	/**
	 * 
	 */
	Integer DEFAULT_DEPOSIT_RATE = 150;

	/**
	 * PUSH
	 */
	String VOICE_MESSAGE = "您有一条语音消息";

	String UPDATE_SUM_PIC = "updateSumPic";
	String SUM_PIC_URL = "sumImgUrl";

	String CACHE_USER_SESSION = "user_session_";
	String CACHE_USER_DEVICE = "user_device_";

	String KEY_RESULT = "result";
	String KEY_MSG = "msg";
	String KEY_ERR = "err";
	String PARAMS_ERR = "参数错误";

	String PAGE_ACTION_KEY = "act";
	String PAGE_ACTION_SHOW = "show";
	String PAGE_ACTION_ADD = "add";
	String PAGE_ACTION_UPDATE = "update";

	String KEY_PAGE_CONTENT = "contentpage";
	String KEY_VIEW_NAME = "templet";
	
	String PUSH_APNS_TEST_END = "_test.p12";
	String PUSH_TYPE_UMENG = "umeng";
	String PUSH_TYPE_JPUSH = "jpush";
	
	Integer DEFAULT_PAGE_SIZE = 20;

	
	String URL_REDIRECT = "redirect:";
	String URL_ORIGINAL = "original";
	String URL_MAIN_ACTION = "/main";
	String URL_LOGIN_ACTION = "/login";
	String KEY_SESSION_LOGINNAME = "loginName";
	
	String PAGE_KEY_LOGINNAME = "loginName";
	String PAGE_KEY_REMEMBER = "remember";
	String PAGE_KEY_ERROR = "err";
	String PAGE_KEY_SUCCESS = "success";
	String KEY_NAME_SESSION_MENUS = "sessionMenus";
	String KEY_NAME_REQ_MENUS = "menus";
	String KEY_NAME_AUTHMODULE = "authModules";
	String KEY_NAME_TYPE = "type";
	
	Integer STATUS_INACTIVE = 0;
	Integer STATUS_ACTIVE = 1;
	Integer STATUS_FREEZE = 2;// 冻结状态
	
	String CACHE_VERIFYCODE = "VerifyCode_";
	String CACHE_UUID = "UUID_";
	String CACHE_WRONG_PWD_RECORD = "WRONG_PWD_INPUT_TIMES_";
	String CACHE_ACCOUNT_LOCK = "LOCK_USER_";
	String CACHE_ANDROID_CLIENT_VERSION = "ANDROID_CLIENT_VERSION";
	
	// 缓存过期时间
	int EXPIRE_TIME_LOCK = 60 * 5;   // 账户冻结时间，单位秒
	String ERROR_404_VIEW_NAME = "common/404";
}
