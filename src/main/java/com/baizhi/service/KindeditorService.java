package com.baizhi.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.net.UnknownHostException;
import java.util.Map;

public interface KindeditorService {
    Map<String,Object> uploadImage(HttpServletRequest request,MultipartFile img);
    Map<String,Object> getAllImager(HttpServletRequest request) throws UnknownHostException;
}
