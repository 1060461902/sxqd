package edu.zjgsu.ito.utils;

/**
 * 常量类
 * 
 * @author sawei
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
	 * 1.2.3.4管理员，学生，教师，公司
	 */

	public final static Integer ADMIN =  1;
	public final static Integer STUDENT =  2;
	public final static Integer TEACHER =  3;
	public final static Integer COMPANY =  4;

    public final static String EXCEL2003L =".xls";    //2003- 版本的excel
    public final static String EXCEL2007L =".xlsx";   //2007+ 版本的excel

    public final static String fontName ="宋体";   //2007+ 版本的excel


	public static final String DEFAULTPWD = Md5Util.getMD5("123456");

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

	public final static String EXCEL_FILE_NAME = "计算机与信息工程学院学生毕业实习成绩.xlsx";


	/**
	 * 文件上传路径
	 */
	public final static String UPLOAD_DIR =  getXmlPath().split("WEB-INF")[0];

	/**
	 * Excel存放路径
	 */
	public final static String EXCEL_DIR = UPLOAD_DIR + "excel/";

	/**
	 * zip存放目录
	 */
	public final static String ZIP_DIR = UPLOAD_DIR + "zip/";

	/**
	 * pdf存放目录
	 */
	public final static String PDF_TMP_DIR = UPLOAD_DIR + "pdf/";


	/**
	 * 党员照片存放文件夹
	 */

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


	/**
	 * 获取WEB-INF目录下面server.xml文件的路径
	 * @return
	 */
	public static String getXmlPath()
	{
		//file:/D:/JavaWeb/.metadata/.me_tcat/webapps/TestBeanUtils/WEB-INF/classes/
		String path=Thread.currentThread().getContextClassLoader().getResource("").toString();
//		path=path.replace('/', '\\'); // 将/换成\
		path=path.replace("file:", ""); //去掉file:
		path=path.replace("classes\\", ""); //去掉class\
		path=path.substring(1); //去掉第一个\,如 \D:\JavaWeb...
		path+="web.xml";
		//System.out.println(path);
		return path;
	}

}