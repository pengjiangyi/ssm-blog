package com.ssm.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ssm.pojo.UeditorImage;
import com.ssm.util.FileUtils;
import com.ssm.util.OSSClientConstants;
import com.ssm.util.OSSClientUtil;

/**
 * 
	*@class FileUpLoadController
	*@description  ueditor整合阿里云oss,上传,预览,删除
 	*@author 彭江毅   
 	*@date  2018年2月10日下午7:03:06
	*@contact 741506070@qq.com
 */
@Controller
public class FileUpLoadController {


	//阿里云oss路径
	private String httpPath = "http://pjy-blog.oss-cn-shenzhen.aliyuncs.com/";

	/**
	 * 文件上传Action
	 * 
	 * @param req
	 * @return UEDITOR 需要的json格式数据
	 */
	@RequestMapping("/ueditor/fileupload/upload")
	@ResponseBody
	public Map<String, Object> upload(HttpServletRequest req) {
		//封装返回json格式
		Map<String, Object> result = new HashMap<String, Object>();
		MultipartHttpServletRequest mReq = null;
		MultipartFile file = null;
		InputStream is = null;
		String fileName = "";
		// 原始文件名 UEDITOR创建页面元素时的alt和title属性
		String originalFileName = "";
		String filePath = "";
		try {
			mReq = (MultipartHttpServletRequest) req;
			// 从config.json中取得上传文件的ID
			file = mReq.getFile("upfile");
			// 取得文件的原始文件名称
			fileName = file.getOriginalFilename();
			originalFileName = fileName;
			OSSClientUtil ossClientUtil = new OSSClientUtil();
			fileName = ossClientUtil.uploadImg2Oss(file);
			result.put("state", "SUCCESS");// UEDITOR的规则:不为SUCCESS则显示state的内容
			// 设置回调的url
			result.put("url", httpPath + OSSClientConstants.FOLDER + fileName);
			result.put("title", originalFileName);
			result.put("original", originalFileName);
			System.out.println("上传成功");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			result.put("state", "文件上传失败!");
			result.put("url", "");
			result.put("title", "");
			result.put("original", "");
			System.out.println("文件 " + fileName + " 上传失败!");
		}

		return result;
	}

	/**
	 * 获取阿里云的图片，在线管理
	 * 
	 * @throws IOException
	 */
	@RequestMapping("/ueditor/fileupload/download")
	@ResponseBody
	public String download(HttpServletRequest request, HttpServletResponse response, String callback, Integer start)
			throws IOException {

		UeditorImage result = new UeditorImage();
		response.setContentType("text/plain");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Access-Control-Allow-Origin", "*");// 添加跨域访问

		// 通过阿里云返回的图片地址
		try {

			OSSClientUtil clientUtil = new OSSClientUtil();
			List<Map<String, Object>> list = clientUtil.ListImage();
			result.setState("SUCCESS");
			result.setTotal(list.size());
			result.setList(list);
			result.setStart(start);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (callback != null) {
				return callback + "(" + JSON.toJSONString(result) + ")";
			}
			return JSON.toJSONString(result);

		}

	}
	/**
	 * 删除阿里云的指定图片,单图删除
	 * @param path
	 * @return
	 */
	@RequestMapping("/ueditor/deletePic")
	@ResponseBody
	public String deletePic(String path) {
		String keys[]=path.split("http://pjy-blog.oss-cn-shenzhen.aliyuncs.com/");
		String key=keys[1];
		OSSClientUtil clientUtil=new OSSClientUtil();
		clientUtil.deletePic(key);
		return "success";
	}
}
