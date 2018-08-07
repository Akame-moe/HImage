package com.gentlehu.himage.controller.api;

import com.gentlehu.himage.Config;
import com.gentlehu.himage.annotation.Authorized;
import com.gentlehu.himage.common.TokenPool;
import com.gentlehu.himage.entity.JsonResult;
import com.gentlehu.himage.entity.StatusCode;
import com.gentlehu.himage.pojo.Image;
import com.gentlehu.himage.service.ImageService;
import com.gentlehu.himage.service.UserService;
import com.gentlehu.himage.utils.HttpUtil;
import com.gentlehu.himage.utils.TextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Created by gentle-hu on 2018/7/29 0:06.
 * Email:me@gentlehu.com
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiImageController{

    @Value("${upload.imageLocation}")
    private String imageLocation;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    private static final String DATE_PATH_PATTERN = "%04d/%02d/%02d";


    @Authorized
    @RequestMapping(value = "/api/image/query",method = RequestMethod.GET)
    public JsonResult query(@RequestParam Integer id){
        Image image = imageService.findById(id);
        return JsonResult.ok(image);
    }
    @Authorized
    @RequestMapping(value = "/api/image/upload",method = RequestMethod.POST)
    public JsonResult upload(@RequestParam("src_file") MultipartFile file, HttpServletRequest request) throws IOException {
        if(file.isEmpty()){
            return JsonResult.error("file could't be empty.");
        }
        LocalDateTime now = LocalDateTime.now();
        String p = String.format(DATE_PATH_PATTERN,now.getYear(),now.getMonthValue(),now.getDayOfMonth());
        File parent = new File(imageLocation,p);
        if(!parent.exists() && !parent.mkdirs()){
            return JsonResult.error("can't mkdirs.");
        }

        String originName = file.getOriginalFilename();
        String destName = System.currentTimeMillis() + TextUtil.suffix(originName);
        File destFile = new File(parent,destName);
        file.transferTo(destFile);
        String path = String.format("upload/%s/%s",p,destName);
        Image image = new Image();
        image.setRelpath(path);
        image.setLocation(destFile.getAbsolutePath());
        //匿名的uid定义为anonymous
        String token = HttpUtil.fetchToken(request);
        if(TextUtil.isNotEmpty(token)){
            String uid = Optional.ofNullable(TokenPool.get(token)).orElse("anonymous");
            image.setUid(uid);
        }else{
            image.setUid("anonymous");
        }
        imageService.insert(image);
        Map<String,String> m = new HashMap<>();
        m.put("path",path);
        m.put("id","image_Id");
        m.put("url",String.format("%s/%s", Config.STATIC_IMAGE_SERVER,path));
        m.put("delete","http://www.google.com/api/image/delete/images_id");
        return JsonResult.ok(m);
    }

    @Authorized
    @RequestMapping(value = "/api/image/delete",method = RequestMethod.GET)
    public JsonResult delete(@RequestParam Integer id){
        Image image = imageService.findById(id);
        if(image == null){
            return JsonResult.error("not found.");
        }else{
            Image img = new Image();
            img.setStatus(StatusCode.INVALID);
            imageService.update(id,img);
            File file = new File(image.getLocation());
            if(file.delete()){
                JsonResult.ok();
            }
            return JsonResult.error("delete error.");
        }
    }


}
