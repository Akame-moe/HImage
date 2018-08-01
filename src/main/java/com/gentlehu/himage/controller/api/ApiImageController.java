package com.gentlehu.himage.controller.api;

import com.gentlehu.himage.base.BaseController;
import com.gentlehu.himage.entity.JsonResult;
import com.gentlehu.himage.entity.StatusCode;
import com.gentlehu.himage.pojo.Image;
import com.gentlehu.himage.service.ImageService;
import com.gentlehu.himage.utils.TextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by gentle-hu on 2018/7/29 0:06.
 * Email:me@gentlehu.com
 */
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ApiImageController extends BaseController{

    @Value("${upload.imageLocation}")
    private String imageLocation;

    @Autowired
    private ImageService imageService;

    private static final String PATTERN = "%04d/%02d/%02d";


    @RequestMapping(value = "/api/query",method = RequestMethod.GET)
    public String findById(String id){
        Image image = imageService.findById(id);
        return JsonResult.ok(image).json();
    }
    @RequestMapping(value = "/api/upload",method = RequestMethod.POST)
    public String insert(@RequestParam("src_file") MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return JsonResult.error("file could't be empty.").json();
        }
        LocalDateTime now = LocalDateTime.now();
        String p = String.format(PATTERN,now.getYear(),now.getMonthValue(),now.getDayOfMonth());
        File parent = new File(imageLocation,p);
        if(!parent.exists() && !parent.mkdirs()){
            return JsonResult.error("can't mkdirs.").json();
        }

        String originName = file.getOriginalFilename();
        String destName = System.currentTimeMillis() + TextUtil.suffix(originName);
        File destFile = new File(parent,destName);
        file.transferTo(destFile);
        Image image = new Image();
        int hash = UUID.randomUUID().hashCode();
        //暂时这样取hash值，日后改为计算文件md5
        image.setId(String.valueOf(hash));
        image.setRelpath(p + "/" + destName);
        image.setLocation(destFile.getAbsolutePath());
        //匿名的uid定义为anonymous
        image.setUid("anonymous");
        imageService.insert(image);
        Map<String,String> m = new HashMap<>();
        m.put("filename","test.jpg");
        m.put("storename","storename.jpg");
        m.put("path","/upload/images/test.jpg");
        m.put("hash","hash_abc");
        m.put("url","http://www.google.com/upload/images/test.jpg");
        m.put("delete","http://www.google.com/api/delete/test");
        return JsonResult.ok(m).json();
    }

    @RequestMapping(value = "/api/delete",method = RequestMethod.GET)
    public String delete(String id){
        Image image = imageService.findById(id);
        if(image == null){
            JsonResult.error("not found.").json();
        }else{
            Image img = new Image();
            img.setStatus(StatusCode.INVALID);
            imageService.update(id,img);
            File file = new File(image.getLocation());
            if(file.delete()){
                JsonResult.ok().json();
            }
        }
        return JsonResult.error("delete error.").json();
    }


}
