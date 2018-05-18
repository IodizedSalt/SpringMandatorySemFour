package com.mandatory.semfour.controller;

import com.mandatory.semfour.entity.Post;
import com.mandatory.semfour.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.mandatory.semfour.AmazonClient;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@RestController
public class PostController {

    @Autowired
    private AmazonClient amazonClient;

    @Autowired
    PostRepository pr;


    @PostMapping("/upload")
    public ModelAndView uploadFile(@RequestParam(name = "file") MultipartFile file,
                                   @RequestParam(name = "id", defaultValue = "-1") int id,
                                   @RequestParam(name = "title", defaultValue = "no_name") String title,
                                   @RequestParam(name = "username", defaultValue = "anon") String user) {

        System.out.println("Saved to s3 " + title +": " + file.getOriginalFilename());


        String content = this.amazonClient.uploadFile(file, title);
        String thumbnail = generateThumbnailname(content);
        String timestamp = new SimpleDateFormat("dd-MM-YYYY_HH:mm:ss").format(Calendar.getInstance().getTime());

        Post post = new Post(id, title, content, 0, user, thumbnail, timestamp);

        if (content.startsWith("ERROR")) {
            ModelAndView mv = new ModelAndView("HomePageLoggedIn");
            return mv;
//            return content;
        } else {
            post.setId(id);
            post.setTitle(title);
            post.setContent(content);
            post.setThumbnail(generateThumbnailname(content));
            post.setUser(user);
            post.setTimestamp(timestamp);
            pr.save(post);

            ModelAndView mv = new ModelAndView("HomePageLoggedIn");
            return mv;
        }
    }
        private String generateThumbnailname(String orignalname){

            String mainStr = orignalname;
            String subStr = "thumbnail_";
            String str = "bucket/";

            int pos_str = mainStr.indexOf(str)+str.length();

            StringBuilder thumbUrl = new StringBuilder(orignalname);
            thumbUrl.insert(pos_str, subStr);

            return thumbUrl.toString();
        }
}
