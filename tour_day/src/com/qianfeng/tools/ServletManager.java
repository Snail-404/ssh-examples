package com.qianfeng.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ServletManager {
	public Map<String, String> upload(HttpServletRequest request, String path) {
		Map<String, String> map = new HashMap<String, String>();
		// 判断表单是否含有文件 根据表头entype进行判断
		boolean isContent = ServletFileUpload.isMultipartContent(request);
		if (!isContent) {
			// 验证，如果不包含enctype,结束
			return null;
		}
		File file = new File(path);
		if (!file.exists() && !file.isDirectory()) {
			// 判断文件目录是否存在
			file.mkdir();
		}
		// 创建一个上传工具类
		ServletFileUpload upload = new ServletFileUpload(
				new DiskFileItemFactory());
		upload.setHeaderEncoding("utf-8");
		try {
			// 通过upload解析请求码
			List<FileItem> list = upload.parseRequest(request);
			// 增强for循环，遍历集合
			for (FileItem fileItem : list) {
				// 判断当前文件是否为普通文本表单
				if (fileItem.isFormField()) {
					// getFiledName():获取的就是input的内容
					String name = fileItem.getFieldName();
					// 获取文本表单内容，utf8解决乱码
					String value = fileItem.getString("utf-8");
					map.put(name, value);

				} else {
					String fileName = fileItem.getName().replace(" ", "");
					if (fileName == null && fileName.equals("")) {
						// 结束当前循环，继续下一次
						continue;
					}
					// 截取文件名 +1：不包含"/"
					fileName = fileName
							.substring(fileName.lastIndexOf("/") + 1);
					// 图片后缀正则
					Pattern patter = Pattern
							.compile(".+(jpg|JPG|png|PNG|gif|GIF|jpeg|JPEG)");
					Matcher m1 = patter.matcher(fileName);
					if (m1.matches()) {
						map.put("photoName", fileName);
						map.put("photoUrl", Config.PATHURL + fileName);

					}
					// 音乐正则
					Pattern pattern2 = Pattern
							.compile(".+(MP3|mp3|WAV|wav|MPEG-4)");
					Matcher m2 = pattern2.matcher(fileName);
					if (m2.matches()) {
						map.put("musicName", fileName);
						map.put("musicUrl", Config.PATHURL + fileName);
					}
					InputStream is = fileItem.getInputStream();
					// 创建一个文件输出流，写入文件
					FileOutputStream os = new FileOutputStream(path + "/"
							+ fileName);
					byte[] buffer = new byte[1024];
					int len = 0;
					while ((len = is.read(buffer)) > 0) {
						// 写入文件
						os.write(buffer, 0, len);
					}
					// 关闭流
					os.close();
					is.close();
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
public static boolean download(String musicName){
	//获取音乐路径的拼接地址
	String path=Config.PATHURL+musicName;
	URL url;
	try {
		url = new URL(path);
		URLConnection connection=url.openConnection();
		connection.setConnectTimeout(5*1000);
		//执行链接操作
		connection.connect();
		File file=new File(Config.DOWNLOADPATH);
		if(!file.exists() && !file.isDirectory()){
			file.mkdir();
		}
		InputStream is= connection.getInputStream();
		FileOutputStream os=new FileOutputStream(Config.DOWNLOADPATH+"/"+musicName);
		//缓冲区
		byte[] buffer=new byte[1024];
		int len=0;
		//循环读取数据
		while((len=is.read(buffer))>0){
			os.write(buffer, 0, len);
		}
		os.close();
		is.close();
		return true;
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return false;
}
}
