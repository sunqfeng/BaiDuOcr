package Org.BaiduOcr;

import java.io.File;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import Org.Tools.Base64Util;
import Org.Tools.FileUtil;
import Org.Tools.HttpUtil;

public class GeneralBasic {

	/**
	 * 重要提示代码中所需工具类 FileUtil,Base64Util,HttpUtil,GsonUtils请从
	 * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
	 * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
	 * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
	 * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3 下载
	 */
	public static String generalBasic(String filePath) {
		// 请求url
		String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";
		try {
			// 本地文件路径
//			String filePath = "D://java_picture//wocao.jpg";

			byte[] imgData = FileUtil.readFileByBytes(filePath);
			String imgStr = Base64Util.encode(imgData);
			String imgParam = URLEncoder.encode(imgStr, "UTF-8");

			String param = "image=" + imgParam;

			// 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
			AuthService authService = new AuthService();
			String token = authService.getAuth();
			System.out.println("token = " + token);
			String accessToken = token;

			String result = HttpUtil.post(url, accessToken, param);
//			System.out.println("sqf==== "+result);

			String obj = result.substring(result.indexOf("[") + 1, result.indexOf("]"));
			System.out.println("obj===" + obj);
			String[] arry = obj.split("\\},");
			for (int i = 0; i < arry.length; i++) {
				String[] arry1 = arry[i].split("\\:");
				System.out.println(arry1[1]);
//				System.out.println( arry1[1].replaceAll("\\s*","").replaceAll("[^(\\u4e00-\\u9fa5)]",""));
			}

			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {

		System.out.println("已开始...");
		String path = "D://java_picture//";

//		List<String> files = new ArrayList<String>();
		File file = new File(path);
		File[] tempList = file.listFiles();

		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
//                files.add(tempList[i].toString());
				GeneralBasic.generalBasic(tempList[i].toString());
			}
			if (tempList[i].isDirectory()) {
			}
		}

	}

}
