package com.baizhi.service;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class KindeditorServiceImpl implements KindeditorService {

    @Override
    public Map<String, Object> uploadImage(HttpServletRequest request, MultipartFile img) {
        HashMap<String, Object> map = new HashMap<String, Object>();

        /**
         * url : http://localhost:8989/cmfz/upload/img/图片名
         */


        try {
            //获取文件路径
            String realPath = request.getServletContext().getRealPath("/upload/img");
            //获取文件
            File file = new File(realPath);
            if (!file.exists()) {
                file.mkdir();
            }
            String imgName = new Date().getTime() + "_" + img.getOriginalFilename();
            img.transferTo(new File(realPath, imgName));

            String scheme = request.getScheme();    //http
            InetAddress address = InetAddress.getLocalHost(); //localhost
            String s = address.toString();
            String localhost = s.split("/")[1];

            int port = request.getServerPort();//8989
            String path = request.getContextPath();//cmfz

            String url = scheme+"://"+localhost+":"+port+"/"+path+"/upload/img/"+imgName;

            map.put("error", 0);
            map.put("url", url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Map<String, Object> getAllImager(HttpServletRequest request) throws UnknownHostException {
        HashMap<String,Object> map = new HashMap<>();
        ArrayList<Object> arrayList = new ArrayList<>();

        String realPath = request.getSession().getServletContext().getRealPath("/upload/img");
        File file = new File(realPath);
        String[] list = file.list();
        for(String img : list){
            HashMap<String,Object> maps = new HashMap<>();
            File fileImg = new File(realPath,img);
            long length = fileImg.length();
            maps.put("is_dir",false);
            maps.put("has_file",false);
            maps.put("filesize",length);
            maps.put("dir_path","");
            maps.put("is_photo",true);
            String extension = FilenameUtils.getExtension(img);
            maps.put("filetype",extension);
            maps.put("filename",img);
            String time = img.split("_")[0];
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Long lt = new Long(time);
            Date date = new Date(lt);
            String format = simpleDateFormat.format(date);
            maps.put("datetime",format);
            arrayList.add(maps);
        }
        map.put("file_list",arrayList);
        map.put("current_dir_path","");

        String scheme = request.getScheme();    //http
        InetAddress address = InetAddress.getLocalHost(); //localhost
        String s = address.toString();
        String localhost = s.split("/")[1];

        int port = request.getServerPort();//8989
        String path = request.getContextPath();//cmfz

        String url = scheme+"://"+localhost+":"+port+"/"+path+"/upload/img/";
        map.put("current_url",url);
        map.put("total_count",list.length);
        return map;
    }
}