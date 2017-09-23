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
		// �жϱ��Ƿ����ļ� ���ݱ�ͷentype�����ж�
		boolean isContent = ServletFileUpload.isMultipartContent(request);
		if (!isContent) {
			// ��֤�����������enctype,����
			return null;
		}
		File file = new File(path);
		if (!file.exists() && !file.isDirectory()) {
			// �ж��ļ�Ŀ¼�Ƿ����
			file.mkdir();
		}
		// ����һ���ϴ�������
		ServletFileUpload upload = new ServletFileUpload(
				new DiskFileItemFactory());
		upload.setHeaderEncoding("utf-8");
		try {
			// ͨ��upload����������
			List<FileItem> list = upload.parseRequest(request);
			// ��ǿforѭ������������
			for (FileItem fileItem : list) {
				// �жϵ�ǰ�ļ��Ƿ�Ϊ��ͨ�ı���
				if (fileItem.isFormField()) {
					// getFiledName():��ȡ�ľ���input������
					String name = fileItem.getFieldName();
					// ��ȡ�ı������ݣ�utf8�������
					String value = fileItem.getString("utf-8");
					map.put(name, value);

				} else {
					String fileName = fileItem.getName().replace(" ", "");
					if (fileName == null && fileName.equals("")) {
						// ������ǰѭ����������һ��
						continue;
					}
					// ��ȡ�ļ��� +1��������"/"
					fileName = fileName
							.substring(fileName.lastIndexOf("/") + 1);
					// ͼƬ��׺����
					Pattern patter = Pattern
							.compile(".+(jpg|JPG|png|PNG|gif|GIF|jpeg|JPEG)");
					Matcher m1 = patter.matcher(fileName);
					if (m1.matches()) {
						map.put("photoName", fileName);
						map.put("photoUrl", Config.PATHURL + fileName);

					}
					// ��������
					Pattern pattern2 = Pattern
							.compile(".+(MP3|mp3|WAV|wav|MPEG-4)");
					Matcher m2 = pattern2.matcher(fileName);
					if (m2.matches()) {
						map.put("musicName", fileName);
						map.put("musicUrl", Config.PATHURL + fileName);
					}
					InputStream is = fileItem.getInputStream();
					// ����һ���ļ��������д���ļ�
					FileOutputStream os = new FileOutputStream(path + "/"
							+ fileName);
					byte[] buffer = new byte[1024];
					int len = 0;
					while ((len = is.read(buffer)) > 0) {
						// д���ļ�
						os.write(buffer, 0, len);
					}
					// �ر���
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
	//��ȡ����·����ƴ�ӵ�ַ
	String path=Config.PATHURL+musicName;
	URL url;
	try {
		url = new URL(path);
		URLConnection connection=url.openConnection();
		connection.setConnectTimeout(5*1000);
		//ִ�����Ӳ���
		connection.connect();
		File file=new File(Config.DOWNLOADPATH);
		if(!file.exists() && !file.isDirectory()){
			file.mkdir();
		}
		InputStream is= connection.getInputStream();
		FileOutputStream os=new FileOutputStream(Config.DOWNLOADPATH+"/"+musicName);
		//������
		byte[] buffer=new byte[1024];
		int len=0;
		//ѭ����ȡ����
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
