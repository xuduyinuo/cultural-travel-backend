package com.xudu.culturaltravelbackend.utils;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.Data;
import lombok.Value;
import org.springframework.aop.framework.autoproxy.AbstractAdvisorAutoProxyCreator;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @className: QiniuUtil
 * @description: TODO
 * @author: xudu
 * @create: 2024-06-20
 */
@Component
@Data
@ConfigurationProperties(prefix = "qiniu")
public class QiniuUtil {

    private String accessKey;   //公钥
    private String secretKey;   //私钥
    private String bucketName;  //存储空间
    private String path;    //域名

    /**
     * @param file 前端传来的图片
     * @return 图片的访问路径
     */
    public String upload(MultipartFile file) {


        // 通过UUID生成新的文件名 防止重复
        String originalFilename = file.getOriginalFilename();
        String filenameExtension = StringUtils.getFilenameExtension(originalFilename);

        String fileName = null;
        if ("mp3".equals(filenameExtension)){
            fileName = "audio/" + UUID.randomUUID() + "." + filenameExtension;
        }else {
            fileName = "image/" + UUID.randomUUID() + "." + filenameExtension;
        }


        Configuration cfg = new Configuration();
        cfg.resumableUploadAPIVersion = Configuration.ResumableUploadAPIVersion.V2;

        UploadManager uploadManager = new UploadManager(cfg);

        try {
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucketName);
            Response response = uploadManager.put(file.getInputStream(), fileName, upToken, null, null);
            if (!response.isOK()) {
                throw new RuntimeException("上传失败");
            }
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
        return path + fileName;
    }


    public void deleteFile(String fileAdress) {
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration();

        // 创建BucketManager对象
        BucketManager bucketManager = new BucketManager(Auth.create(accessKey, secretKey), cfg);

        try {
            bucketManager.delete(bucketName, fileAdress);
        } catch (QiniuException ex) {
            // 如果遇到异常，说明删除失败
            System.err.println(ex.code() + ": " + ex.response.toString());
        }
    }

}
