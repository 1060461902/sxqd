package edu.zjgsu.ito.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

	/**
	 * 将字符串保存为txt文件
	 * @param str 字符串
	 * @param fileName 文件名称
	 * @param path 保存文件夹的路径
	 * @throws FileNotFoundException
	 */
	public static void writeTxt(String str, String fileName, String path) throws FileNotFoundException {
		
		// 判断目录是否存在,不存在则创建
		File file = new File(path);
		if(!file.exists()) {
			file.mkdir();
		}
		file = new File(path + "/" + fileName);
		PrintStream ps = new PrintStream(new FileOutputStream(file));
		ps.println(str);
	}

    /**
     *返回文件名
     * @param grade
     * @return
     * @author sawei
     */
    public static String getFileName(String grade) {
        return "计算机与信息工程学院" + grade + "学生毕业实习成绩.xls";
    }

	/**
	 * 保存文件
	 * @param srcPath
	 */
	public static String savePhoto(String srcPath) {
		File srcFile = new File(srcPath);
		if(!srcFile.exists()) {
			throw new RuntimeException("源文件不存在");
		}
		else {
			String fileName = getRandFileName(getExtension(srcPath));
			String destPath = Constant.PHOTO_DIR;
			System.out.println(destPath + fileName);
			File destFile = new File(destPath + fileName);
			File savePath = new File(destPath);
			if(!savePath.exists()) {
				savePath.mkdir();
			}
			try {
				FileUtils.copyFile(srcFile, destFile);
				return destPath + fileName;
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("文件保存失败");
			}
			
		}
	}

	/**
	 * 根据txt文件url，读取txt文件
	 * @param url 文件url
	 * @return 文件内容
	 */
	public static String readTxt(String url) {

		System.out.println("========================="+Constant.CONTENT_DIR+url);
		File file = new File(Constant.CONTENT_DIR+url);
		String result = "";
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));// 构造一个BufferedReader类来读取文件
			String s = null;
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				result = result + s;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
	
	/**
	 * 删除txt文件
	 * @param url
	 */
	public static void deleteTxt(String url) {
		File file = new File(Constant.CONTENT_DIR+url);
		file.delete();
	}
	
	/**
	 * 保存文件
	 * @param file 文件
	 * @param path 路径
	 * @throws IOException IO异常
	 */
	public static void saveFile(MultipartFile multipartFile, String path) throws IOException {
		InputStream inputStream = multipartFile.getInputStream();
		File file = new File(path);
		FileUtils.copyInputStreamToFile(inputStream, file);
	}
	
	/**
	 * 获取文件后缀名
	 * @param fileName 文件名
	 * @return 后缀名
	 */
	public static String getExtension(String fileName) {
		String extension = fileName.substring(fileName.lastIndexOf("."), fileName.length());
		return extension;
	}
	
	/**
	 * 根据存储文件夹路径和原文件名称，获取存储路径
	 * @param dir 存储文件夹路径
	 * @param originFileName 原文件名 
	 * @return 存储路径
	 */
	public static String getPath(String dir, String originFileName) {
		StringBuffer path = new StringBuffer(dir);
		
		// 文件名称前半部分是时间戳
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		String timeStr = simpleDateFormat.format(System.currentTimeMillis());
		path.append(timeStr);
		
		// 文件名称后半部分是随机数
		path.append(RandomStringUtils.randomNumeric(Constant.FILE_RAND_LENGTH));
		
		// 文件后缀名
		String extension = getExtension(originFileName);
		path.append(extension);
		
		return path.toString();
	}
	
	/**
	 * 利用时间戳和随机数生成随机文件名
	 * @param extension 文件后缀名
	 * @return 随机文件名
	 */
	public static String getRandFileName(String extension) {
		
		// 文件名称前半部分是时间戳
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		String timeStr = simpleDateFormat.format(System.currentTimeMillis());
		StringBuffer fileName = new StringBuffer(timeStr);
		
		// 文件名称后半部分是随机数
		fileName.append(RandomStringUtils.randomNumeric(Constant.FILE_RAND_LENGTH));
		
		// 文件后缀名
		fileName.append(extension);
		return fileName.toString();
	}
	
}
