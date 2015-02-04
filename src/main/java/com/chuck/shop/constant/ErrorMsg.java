/**
 * ErrorMsg.java 
 * Copyright © 2009-2015, 杭州跃度网络科技有限公司
 * 
 * @author liao
 * @create 2015年2月3日
 */
package com.chuck.shop.constant;

/**
 * 错误信息类
 */
public interface ErrorMsg {
	
	String NO_USER = "不存在该用户";
	String NO_LOGIN = "请登录";
	
    String SYSTEM_ERROR = "系统当前繁忙，请稍候再试。";
    
    String PARAMETER_ERROR = "对不起，您的请求没有操作成功，因为您请求的参数不符合要求。";

    String ILLEGAL_REQUEST = PARAMETER_ERROR;
    
     String PARAMETER_XSS_SECURITY = "对不起，您的请求没有操作成功，因为您的请求中包含非法的参数值。";
    
    String PHONE_CODE_NOT_PASS = "对不起，您的手机校验码不正确。";
    
    String SESSION_TIME_OUT = "对不起，您的登录会话已经超时，请重新登录。";
    
    String CELLPHONE_CONFLICT = "对不起，当前手机号码已经被使用。";
    
    String CELLPHONE_CONFLICT_NO_ACTIVE = "对不起，当前手机号码已经被注册但未激活。";
    String LOGINNAME_IS_NULL = "登录名不能为空";
    String LOGINNAME_CONFLICT = "对不起，当前登录名已经被使用。";
    
    String LOGINNAME_CONFLICT_NO_ACTIVE = "对不起，当前登录名已经被注册但未激活。";
    
    String NAME_CONFLICT = "对不起，当前名称已经被使用。";
    
    String UNSUPPORT_EMAIL_TYPE = "不支持的邮件类型";
    
    String MAIL_IS_NULL = "邮件消息对象不能为空";
    
    String MAIL_FROM_ADDR_EMPTY = "发件人地址不能为空";
    
    String MAIL_TO_ADDR_EMPTY = "收件人地址不能为空";
    
    String SMTP_HOST_NULL = "邮件服务器地址不能为空";
    
    String SEND_MAIL_ERROR = "发送邮件失败";
    
    String ADD_URL_ATTACHMENT_ERROR = "添加网络链接类型的邮件附件时发生异常";
    
    String ADD_ATTACHMENT_ERROR = "添加邮件附件时发生异常";
    

    String NO_ACCOUNT = "该用户不存在";
    String WRONG_PWD = "密码错误";
    String EMPTY_PWD = "密码不能为空";
    String EMPTY_LOGINNAME = "登录名不能为空";
    
    String USER_NOT_EXISTS = "不存在该用户";
//    String USER_NOT_ALLOWED = "您无权登录本客户端";
    String USER_FREEZE="您的账号已被冻结";
//    String KICKOUT_TITLE = "下线通知";
//    String KICKOUT_CONTENT = "您的账号在其他设备登录，您已下线";
    
	// 用户信息相关
//	String USER_LOGIN_NAME_CANNOT_BLANK = "用户名不能为空";
//	String USER_LOGIN_NAME_LENGTH = "用户名长度应在3到20位之间";
//	String USER_LOGIN_NAME_WORD = "用户名只允许使用英文和数字";
//	String USER_LOGIN_NAME_EXISTS = "用户名已被使用";
//	String USER_PWD_CANNOT_BLANK = "密码不能为空";
//	String USER_PWD_LENGTH = "密码长度应在6到20位之间";
//	String USER_NAME_CANNOT_BLANK = "姓名不能为空";
//	String USER_NAME_LENGTH = "姓名不能超过20位";
//	String USER_LOCKED = "您的账号因密码连续输错三次，被冻结30分钟";
//
//	
//
//	String CELLPHONE_CANNOT_BLANK = "手机号码不能为空";
//	String CELLPHONE_ALREADY_BINDED = "该手机号码已被注册";
//	String CELLPHONE_ALREADY_BINDED_OTHER = "该手机号码已被其他账号绑定";
//	String CELLPHONE_ALREADY_BINDED_SELF = "该手机号码已经是您绑定好的号码，不需要重复操作";
//	String CELLPHONE_NOT_BINDED = "该手机号码未被绑定";
//	String WRONG_REG_UUID = "请按正常流程进行";
//
//	String ORDER_BLANK = "请选择要购买的商品";
//	String ORDER_NULL = "请选择订单";
//	String ORDER_WITH_ERR = "订单有误，请重新选择商品";
//	String PRODUCT_NOT_EXISTS = "商品不存在或已下架";
//
//	String ORDER_NO_PERMISSION = "这不是您的订单，您无权处理";
//
//	String VERIFYCODE_BLANK = "验证码错误";
//	String VERIFYCODE_WRONG = "验证码错误";
//	String FAILED_SEND_SMS = "短信发送失败，请重试";
//
//	String WRONG_CELLPHONE_FORMAT = "手机号码格式有误";
//	String WRONG_TELEPHONE_FORMAT = "电话号码格式有误";
//	String PWD_BLANK = "密码不能为空";
//	String PWD_CONTAIN_BLANK = "密码不能包含空格";
 
}
