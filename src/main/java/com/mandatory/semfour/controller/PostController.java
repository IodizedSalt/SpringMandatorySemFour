package com.mandatory.semfour.controller;

import com.mandatory.semfour.AmazonClient;
import com.mandatory.semfour.entity.Post;
import com.mandatory.semfour.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
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
                                   @RequestParam(name = "description", defaultValue = "") String description) {

        System.out.println("Saved to s3 " + title +": " + file.getOriginalFilename());


        String content = this.amazonClient.uploadFile(file, title);
        String thumbnail = generateThumbnailname(content);
        String timestamp = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss").format(Calendar.getInstance().getTime());

        Post post = new Post(id, title, content, 0, description, thumbnail, timestamp);

        if (content.startsWith("ERROR")) {
            ModelAndView mv = new ModelAndView("HomePageLoggedIn");
            return mv;
        } else {
            post.setId(id);
            post.setTitle(title);
            post.setContent(content);
            post.setThumbnail(generateThumbnailname(content));
            post.setDescription(description);
            post.setTimestamp(timestamp);
            pr.save(post);

            ModelAndView mv = new ModelAndView("HomePageLoggedIn");
            mv.getModel().put("postList", pr.findAll());

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
