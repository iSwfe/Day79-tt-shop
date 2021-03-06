package com.iswfe.ttshop.web;

import com.iswfe.ttshop.service.FileService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;

@Controller
public class FileAction {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private FileService fileService;

	@ResponseBody
	@RequestMapping(value = "/file/upload", method = RequestMethod.GET)
	public void config(HttpServletRequest request, HttpServletResponse response) {
		try {
			//设置请求和响应的参数
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");

			if ( "config".equals(request.getParameter("action")) ) {
				PrintWriter out = response.getWriter();
				InputStream is = this.getClass().getClassLoader().getResourceAsStream("config.json");
				IOUtils.copy(is, out, "UTF-8");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}

	@ResponseBody
	@RequestMapping(value = "/file/upload", method = RequestMethod.POST)
	public Map<String, Object> upload(MultipartFile upfile) {
		return fileService.uploadImages(upfile);
	}
}
