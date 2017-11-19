package edu.zjgsu.ito.utils;

/**
 * 常量类
 * 
 * @author Jenson_Zhou
 * 
 */
public class Constant {

	/**
	 * 成功返回码
	 */
	public final static int OK = 200;
	
	/**
	 * 失败返回码
	 */
	public final static int FAIL = 500;
	
	/**
	 * 文件随机部分长度
	 */
	public final static int FILE_RAND_LENGTH = 4;
	
	/**
	 * 登录成功
	 */
	public final static int LOGIN_SUCCESS = 1;
	
	/**
	 * 登录成功响应消息
	 */
	public final static String LOGIN_SUCCESS_MESSAGE = "登录成功";
	
	/**
	 * 密码错误响应消息
	 */
	public final static String WRONG_PASSWORD_MESSAGE = "密码错误";
	
	/**
	 * 用户不存在响应消息
	 */
	public final static String USER_NOT_EXIST_MESSAGE = "用户不存在";
	
	/**
	 * 用户不存在
	 */
	public final static int USER_NOT_EXIST = 0;
	
	/**
	 * 密码错误
	 */
	public final static int WRONG_PASSWORD = 2;
	
	/**
	 * 文件上传路径
	 */
	public final static String UPLOAD_DIR = System.getProperty("user.dir").replace("\\", "/").replace("bin", "");
	
	/**
	 * 党员照片存放文件夹
	 */
//	public final static String PHOTO_DIR = UPLOAD_DIR + "/photo/";
	
	/**
	 * 党员照片存放文件夹
	 */
	public final static String PHOTO_DIR = "C:/Program Files/Apache Software Foundation/Tomcat 8.0/webapps/upload/photo/";
	
	/**
	 * 信息正文文件存储目录
	 */
	public final static String CONTENT_DIR = UPLOAD_DIR + "/content/";
	
	/**
	 * 视频存放路径
	 */
	public final static String VIDEO_DIR = UPLOAD_DIR + "/video/";
	
	/**
	 * 重置密码失败消息
	 */
	public final static String RESET_PWD_FAIL_MESSAGE = "您没有重置密码的权限，请联系管理员";
	
	/**
	 * 重置密码成功消息
	 */
	public final static String RESET_PWD_SUCCESS_MESSAGE = "重置成功";
}