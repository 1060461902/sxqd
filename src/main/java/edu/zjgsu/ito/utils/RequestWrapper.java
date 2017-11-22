package edu.zjgsu.ito.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.nio.charset.Charset;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.io.IOUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * httpRequest的装饰器
 * 
 * @author Jenson_Zhou
 * 
 */
public class RequestWrapper extends HttpServletRequestWrapper {

	/**
	 * requestBody
	 */
	private byte[] body;

	public RequestWrapper(HttpServletRequest request) throws Exception {
		super(request);
		if(request.getMethod().equals("GET")) { // 如果是GET请求
			String queryString = request.getQueryString();
			if(queryString == null) {
				queryString = "{}";
			}
			
			queryString = URLDecoder.decode(queryString, "UTF-8");
			body = queryString.getBytes(Charset.forName("UTF-8"));
		
		} else { // 如果不是GET请求
			body = IOUtils.toByteArray(request.getInputStream());
		}
		body = removeToken(body);
	}
	
	/**
	 * 删除参数中的token
	 * @param body 请求体
	 * @throws IOException IO异常
	 */
	public byte[] removeToken(byte[] body) throws IOException {
		String content = new String(body, Charset.forName("UTF-8"));

		/* 删除adminToken和userToken，因为controller层无法接受这两个参数 */
		try {
			JSONObject jsonObject = JSON.parseObject(content);
			jsonObject.remove("adminToken");
			jsonObject.remove("userToken");
	
			/* 把处理过后的json数据转换成新的输入流 */
			String jsonString = jsonObject.toJSONString();
			body = jsonString.getBytes("UTF-8");
		} catch (Exception e) {
			
		}
		
		return body;
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(getInputStream()));
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		final ByteArrayInputStream bais = new ByteArrayInputStream(body);
		return new ServletInputStream() {
			@Override
			public int read() throws IOException {
				return bais.read();
			}

			@Override
			public boolean isFinished() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public boolean isReady() {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void setReadListener(ReadListener readListener) {
				// TODO Auto-generated method stub
				
			}
		};
	}


}
