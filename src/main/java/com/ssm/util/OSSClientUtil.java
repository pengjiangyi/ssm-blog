package com.ssm.util;

import java.io.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;



import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 阿里云 OSS工具类
 * 
 * @author Monkey
 * @date 2017年9月30日下午3:38:09
 * @version 1.0
 */
@Controller
public class OSSClientUtil {

	public static final Logger logger = LoggerFactory.getLogger(OSSClientUtil.class);
	// endpoint
	private String endpoint = OSSClientConstants.ENDPOINT;
	// accessKey
	private String accessKeyId = OSSClientConstants.ACCESS_KEY_ID;
	private String accessKeySecret = OSSClientConstants.ACCESS_KEY_SECRET;
	// 空间
	private String bucketName = OSSClientConstants.BACKET_NAME;
	// 文件存储目录
	private String filedir = OSSClientConstants.FOLDER;

	private OSSClient ossClient;

	public OSSClientUtil() {
		ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		
		
		
	}

	/**
	 * 初始化
	 */
	public void init() {
		ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
	}

	/**
	 * 销毁
	 */
	public void destory() {
		ossClient.shutdown();
	}

	/**
	 * 上传图片
	 *
	 * @param url
	 * @throws Exception 
	 * @throws ImgException
	 */
	public void uploadImg2Oss(String url) throws Exception  {
		File fileOnServer = new File(url);
		FileInputStream fin;
		try {
			fin = new FileInputStream(fileOnServer);
			String[] split = url.split("/");
			this.uploadFile2OSS(fin, split[split.length - 1]);
		} catch (FileNotFoundException e) {
			throw new Exception("图片上传失败");
		}
	}

	public String uploadImg2Oss(MultipartFile file) throws Exception {
		if (file.getSize() > 10 * 1024 * 1024) {
			throw new Exception("上传图片大小不能超过10M！");
		}
		String originalFilename = file.getOriginalFilename();
		String substring = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
		Random random = new Random();
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String time=sdf.format(date);
		
		String name = time+"/"+random.nextInt(10000) + System.currentTimeMillis() + substring;
		try {
			InputStream inputStream = file.getInputStream();
			this.uploadFile2OSS(inputStream, name);
			return name;
		} catch (Exception e) {
			throw new Exception("图片上传失败");
		}
	}

	/**
	 * 获得图片路径
	 *
	 * @param fileUrl
	 * @return
	 */
	public String getImgUrl(String fileUrl) {
		System.out.println(fileUrl);
		if (!StringUtils.isEmpty(fileUrl)) {
			String[] split = fileUrl.split("/");
			return this.getUrl(this.filedir + split[split.length - 1]);
		}
		return null;
	}

	/**
	 * 上传到OSS服务器 如果同名文件会覆盖服务器上的
	 *
	 * @param instream
	 *            文件流
	 * @param fileName
	 *            文件名称 包括后缀名
	 * @return 出错返回"" ,唯一MD5数字签名
	 */
	public String uploadFile2OSS(InputStream instream, String fileName) {
		String ret = "";
		try {
			// 创建上传Object的Metadata
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentLength(instream.available());
			objectMetadata.setCacheControl("no-cache");
			objectMetadata.setHeader("Pragma", "no-cache");
			objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
			objectMetadata.setContentDisposition("inline;filename=" + fileName);
			// 上传文件
			PutObjectResult putResult = ossClient.putObject(bucketName, filedir + fileName, instream, objectMetadata);
			ret = putResult.getETag();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} finally {
			try {
				if (instream != null) {
					instream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	/**
	 * Description: 判断OSS服务文件上传时文件的contentType
	 *
	 * @param FilenameExtension
	 *            文件后缀
	 * @return String
	 */
	public static String getcontentType(String filenameExtension) {
		if (filenameExtension.equalsIgnoreCase("bmp")) {
			return "image/bmp";
		}
		if (filenameExtension.equalsIgnoreCase("gif")) {
			return "image/gif";
		}
		if (filenameExtension.equalsIgnoreCase("jpeg") || filenameExtension.equalsIgnoreCase("jpg")
				|| filenameExtension.equalsIgnoreCase("png")) {
			return "image/jpeg";
		}
		if (filenameExtension.equalsIgnoreCase("html")) {
			return "text/html";
		}
		if (filenameExtension.equalsIgnoreCase("txt")) {
			return "text/plain";
		}
		if (filenameExtension.equalsIgnoreCase("vsd")) {
			return "application/vnd.visio";
		}
		if (filenameExtension.equalsIgnoreCase("pptx") || filenameExtension.equalsIgnoreCase("ppt")) {
			return "application/vnd.ms-powerpoint";
		}
		if (filenameExtension.equalsIgnoreCase("docx") || filenameExtension.equalsIgnoreCase("doc")) {
			return "application/msword";
		}
		if (filenameExtension.equalsIgnoreCase("xml")) {
			return "text/xml";
		}
		return "image/jpeg";
	}

	/**
	 * 获得url链接
	 *
	 * @param key
	 * @return
	 */
	public String getUrl(String key) {
		// 设置URL过期时间为10年 3600l* 1000*24*365*10

		Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
		// 生成URL
		URL url = ossClient.generatePresignedUrl(bucketName, key, expiration);
		if (url != null) {
			return url.toString();
		}
		return null;
	}
	
/**
 * 得到阿里云指定目录下的图片路径
 *  * @return
 */
	public List<Map<String, Object>> ListImage() {
		ObjectListing objectListing = ossClient.listObjects(bucketName);
		List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
		String prefix="http://pjy-blog.oss-cn-shenzhen.aliyuncs.com/";
		List<Map<String, Object>> list=new ArrayList<>();
		for (int i=1;i<sums.size();i++) {
			Map<String, Object> map=new HashMap<>();
			map.put("url", prefix+sums.get(i).getKey());
			list.add(map);
		}
		
		return list;	
	}
	/**
	 * 通过key删除指定下的文件
	 * @param key
	 */
	public void deletePic(String key)
	{
		ossClient.deleteObject(bucketName, key);
		ossClient.shutdown();
		
	}
	
	public static void main(String[] args) {
		OSSClientUtil clientUtil=new OSSClientUtil();
		List<Map<String, Object>> list=clientUtil.ListImage();
		System.out.println(JSON.toJSONString(list));
	}
	
}