package com.csw.monkmanager.controller;

import com.csw.monkmanager.annotation.LogAnnotation;
import com.csw.monkmanager.dao.AlbumDao;
import com.csw.monkmanager.entity.Album;
import com.csw.monkmanager.entity.Chapter;
import com.csw.monkmanager.service.AlbumService;
import com.csw.monkmanager.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private ChapterService chapterService;

    @RequestMapping("showAllalbums")
    public Map<String,Object> showAllAlbums(Integer page, Integer rows) {
        return albumService.showAllAlbums(page, rows);
    }


    @RequestMapping("edit")
    public Map<String,Object> edit(String oper, Album album, String[] id) {
        Map<String,Object> hashMap = new HashMap<>();
        switch (oper) {
            case "add":
                hashMap = albumService.add(album);
                break;
            case "edit":
                hashMap = albumService.update(album);
                break;
            case "del":

                hashMap = albumService.delete(id);
                break;
        }
        return hashMap;
    }

    @LogAnnotation(value = "展示专辑图片信息")
    @RequestMapping("upload")
    public void upload(MultipartFile cover, String albumId, HttpSession session, HttpServletRequest request) throws UnknownHostException {
        System.out.println(cover);
        System.out.println(albumId);
        // 获取路径
        String realPath = session.getServletContext().getRealPath("/upload/img");
        // 判断路径文件夹是否存在
        File file = new File(realPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        // 防止重名操作
        String originalFilename = cover.getOriginalFilename();
        originalFilename = new Date().getTime() + "_" + originalFilename;
        try {
            cover.transferTo(new File(realPath, originalFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 相对路径 : ../XX/XX/XX.jpg
        // 网络路径 : http://IP:端口/项目名/文件存放位置
        String http = request.getScheme();
        String localHost = InetAddress.getLocalHost().toString();
        int serverPort = request.getServerPort();
        String contextPath = request.getContextPath();
        String uri = http + "://" + localHost.split("/")[1] + ":" + serverPort + contextPath + "/upload/img/" + originalFilename;
        //Banner banner = new Banner();
        Album album = new Album();
        album.setId(albumId);
        album.setCover(uri);
        albumService.updateByPrimaryKeySelective(album);
        //albumDao.updateByPrimaryKeySelective(album);
        System.out.println(uri);
        System.out.println(albumId);
    }


     // 7. 专辑详情接口

    @RequestMapping("album")
    public Map<String,Object> album(Album album) {
        Map<String,Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("album", albumDao.selectByPrimaryKey(album));
        List<Chapter> chapters = chapterService.selectByAlbumId(album.getId());
        map.put("list", chapters);
        return map;
    }
}
