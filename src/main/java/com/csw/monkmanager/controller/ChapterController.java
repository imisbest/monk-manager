package com.csw.monkmanager.controller;

import com.csw.monkmanager.annotation.LogAnnotation;
import com.csw.monkmanager.entity.Chapter;
import com.csw.monkmanager.service.ChapterService;
import com.csw.monkmanager.util.HttpUtil;
import org.apache.commons.io.IOUtils;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("chapter")
@RestController
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @RequestMapping("queryAllChapters")
    public Map<String,Object> queryAllChapters(Integer page, Integer rows, String id) {
        return chapterService.showAllBanners(page, rows, id);
    }

    @LogAnnotation(value = "编辑章节信息")
    @RequestMapping("edit")
    public Map<String,Object> edit(String oper, Chapter chapter, String[] id, String albumId) {
        Map<String,Object> hashMap = new HashMap<>();
        switch (oper) {
            case "add":
                hashMap = chapterService.add(chapter, albumId);
                break;
            case "edit":
                hashMap = chapterService.update(chapter);
                break;
            case "del":

                hashMap = chapterService.delete(id);
                break;
        }
        return hashMap;
    }

    @LogAnnotation(value = "下载音频信息")
    @RequestMapping("download")
    public void download(String url, HttpSession session, HttpServletResponse response) throws IOException {

        String realPath = session.getServletContext().getRealPath("/upload/chapter");
       //realpath;;" + realPath);
        System.out.println(url.split("/").length);
        String relpath5 = realPath + "\\" + url.split("/")[url.split("/").length - 1];
       //本机路径" + relpath5);

        String fileName = url.split("/")[url.split("/").length - 1];
        File file = new File(relpath5);
        FileInputStream is = new FileInputStream(file);
        response.setHeader("content-disposition", "attachment" + ";fileName=" + URLEncoder.encode(fileName, StandardCharsets.UTF_8));
        ServletOutputStream os = response.getOutputStream();
        IOUtils.copy(is, os);
        IOUtils.closeQuietly(is);
        IOUtils.closeQuietly(os);
       //this is down");


    }

    @LogAnnotation(value = "上传音频信息")
    @RequestMapping("upload")
    public void upload(MultipartFile url, String chapterId, HttpSession session, HttpServletRequest request) throws IOException, TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException {
        //网络路径
        String httpUrl = HttpUtil.getHttpUrl(url, request, session, "/upload/chapter/");

        String realPath = session.getServletContext().getRealPath("/upload/chapter");
       //realpath;;" + realPath);
        System.out.println(httpUrl.split("/").length);
        String relpath5 = realPath + "\\" + httpUrl.split("/")[httpUrl.split("/").length - 1];
       //本机路径" + relpath5);

        // String s = split[split.length - 1];
        File file = new File(relpath5);
        long length = file.length();
        String size = length / 1024 / 1024 + "MB";

        MP3File read = (MP3File) AudioFileIO.read(file);
        MP3AudioHeader mp3AudioHeader = read.getMP3AudioHeader();
        int trackLength = mp3AudioHeader.getTrackLength();
        String min = trackLength / 60 + "分";
        String sed = trackLength % 60 + "秒";
        Chapter chapter = new Chapter();


        chapter.setId(chapterId);
        chapter.setSize(size);
        chapter.setTime(min + sed);
       //httpUrl;;" + httpUrl);
        chapter.setUrl(httpUrl);
       //chapter;;" + chapter);
        chapterService.updateByPrimaryKeySelective(chapter);
        //chapterDao.updateByPrimaryKeySelective(chapter);
        //       //url;" + url);
//       //chapterId;" + chapterId);
//        // 获取路径
//        String realPath = session.getServletContext().getRealPath("/upload/img");
//        // 判断路径文件夹是否存在
//        File file = new File(realPath);
//        if (!file.exists()) {
//            file.mkdirs();
//        }
//        // 防止重名操作
//        String originalFilename = url.getOriginalFilename();
//        originalFilename = new Date().getTime() + "_" + originalFilename;
//        try {
//            url.transferTo(new File(realPath, originalFilename));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        // 相对路径 : ../XX/XX/XX.jpg
//        // 网络路径 : http://IP:端口/项目名/文件存放位置
//        String http = request.getScheme();
//        String localHost = InetAddress.getLocalHost().toString();
//        int serverPort = request.getServerPort();
//        String contextPath = request.getContextPath();
//        String uri = http + "://" + localHost.split("/")[1] + ":" + serverPort + contextPath + "/upload/img/" + originalFilename;
//        Chapter chapter = new Chapter();
//        chapter.setId(chapterId);
//       //uri;;" + uri);
//        chapter.setUrl(uri);
//       //chapter;;" + chapter);
//        chapterDao.updateByPrimaryKeySelective(chapter);
//        System.out.println(url);
//        System.out.println(chapterId);
    }
}
