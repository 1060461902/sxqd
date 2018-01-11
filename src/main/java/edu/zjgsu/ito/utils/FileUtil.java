package edu.zjgsu.ito.utils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

	/**
	 * 根据fileName和fileDir得到file对象
	 * @param fileName
	 * @return
	 * @author sawei
	 */
	public static File getFile(String fileName, String SFileDir) {
		File fileDir = new File(SFileDir);
//		目录不存在，则新建目录
		if (!fileDir.exists()) {
			fileDir.mkdir();
		}

		return new File(SFileDir + fileName);
	}

	/**
	 * 递归删除目录下的所有文件及子目录下所有文件
	 * @param dir 将要删除的文件目录
	 * @return
	 * @author sawei
	 */
	public static boolean deleteDir(File dir) {
		if (dir.isDirectory()) {
			String[] children = dir.list();
//　　　　　　　递归删除目录中的子目录下
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(dir, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		return dir.delete();
	}


	/**
	 * 将存放在sourceFilePath目录下的源文件，打包成fileName名称的zip文件，并存放到zipFilePath路径下
	 *
	 * @param sourceFilePath :待压缩的文件路径
	 * @param zipFilePath    :压缩后存放路径
	 * @param fileName       :压缩后文件的名称
	 * @return
     * @author sawei
	 */

	public static boolean fileToZip(String sourceFilePath,String zipFilePath,String fileName){
		boolean flag = false;
		File sourceFile = new File(sourceFilePath);
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		ZipOutputStream zos = null;

        File fileDir = new File(zipFilePath);
		if (!fileDir.exists()) {
			fileDir.mkdir();
		}

		if(sourceFile.exists() == false){
			System.out.println("待压缩的文件目录："+sourceFilePath+"不存在.");
		}else{
			try {
				File zipFile = new File(zipFilePath + "/" + fileName +".zip");
				if(zipFile.exists()){
					System.out.println(zipFilePath + "目录下存在名字为:" + fileName +".zip" +"打包文件.");
				}else{
					File[] sourceFiles = sourceFile.listFiles();
					if(null == sourceFiles || sourceFiles.length<1){
						System.out.println("待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩.");
					}else{
						fos = new FileOutputStream(zipFile);
						zos = new ZipOutputStream(new BufferedOutputStream(fos));
						byte[] bufs = new byte[1024*10];
						for(int i=0;i<sourceFiles.length;i++){
							//创建ZIP实体，并添加进压缩包
							ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
							zos.putNextEntry(zipEntry);
							//读取待压缩的文件并写进压缩包里
							fis = new FileInputStream(sourceFiles[i]);
							bis = new BufferedInputStream(fis, 1024*10);
							int read = 0;
							while((read=bis.read(bufs, 0, 1024*10)) != -1){
								zos.write(bufs,0,read);
							}
							bis.close();

						}
						flag = true;
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally{
				//关闭流
				try {
//                    if(null != bis) bis.close();
					if(null != zos) zos.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}
		return flag;
	}

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
	 * @param multipartFile 文件
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
