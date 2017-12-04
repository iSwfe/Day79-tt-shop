package com.iswfe.ttshop.service.impl;

import com.iswfe.ttshop.common.util.FtpUtils;
import com.iswfe.ttshop.common.util.IDUtils;
import com.iswfe.ttshop.service.FileService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileServiceImpl implements FileService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Map<String, Object> uploadImages(MultipartFile upfile) {
		try {

			String address = "10.31.164.63";
			int port = 21;
//			String username = "ftpuser";
//			String password = "ftp";
			String username = "ftpuser1";
			String password = "dhc890dhc";
			String basePath = "/home/ftpuser/www/images";
			String dateString = new DateTime().toString("/yyyy/MM/dd");

			//获取原来的文件名,包括扩展名
			String original = upfile.getOriginalFilename();
			//截取扩展名
			String fileType = original.substring(original.lastIndexOf("."));
			//拼接出新的文件名+扩展名
			String newName = IDUtils.genImageName() + fileType;

			InputStream is = upfile.getInputStream();
			//上传成功返回true,否则返回false
			boolean bool = FtpUtils.uploadFile(address, port, username, password, basePath, dateString, newName, is);

			Map<String, Object> map = new HashMap<>();
			if (bool) {
				map.clear();
				map.put("state", "SUCCESS");
				map.put("original", original);
				map.put("size", upfile.getSize());
				map.put("title", newName);
				map.put("type", fileType);
				map.put("url", dateString +"/"+ newName);
				return map;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return null;
	}
}
