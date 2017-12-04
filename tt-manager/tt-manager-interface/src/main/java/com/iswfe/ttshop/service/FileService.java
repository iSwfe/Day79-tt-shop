package com.iswfe.ttshop.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface FileService {

	Map<String,Object> uploadImages(MultipartFile upfile);
}
