package hello;

 
import com.alibaba.fastjson.JSONObject;
import com.baidu.ai.aip.utils.Base64Util;
import com.baidu.ai.aip.utils.FileUtil;
import com.baidu.ai.aip.utils.HttpUtil;
import com.baidu.ai.aip.utils.UserDao;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

 
/**
* 通用文字识别（高精度含位置版）
*/
public class Helloworld {
	static UserDao  aa = new UserDao();
	String result=null;
	static DateFormat dfImageName;
	static File imageFile;
    /**
    * 重要提示代码中所需工具类
    * FileUtil,Base64Util,HttpUtil,GsonUtils请从
    * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
    * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
    * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
    * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
    * 下载
    */
    public static String accurate() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/accurate";
        try {
            // 本地文件路径
            String filePath = aaa()+"";
             System.out.print(filePath);
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String imgParam = URLEncoder.encode(imgStr, "UTF-8");
            String param = "image=" + imgParam;         
            AuthService a = new AuthService();    
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = a.getAuth();
            String result = HttpUtil.post(url, accessToken, param);
            
            JSONObject json = JSONObject.parseObject(result);
            Object object = json.getJSONArray("words_result").get(0);
            JSONObject json1 = JSONObject.parseObject(object.toString());   
            String cc =json1.getString("words");
            String regEx="[\n`~!@#$%^&*()+=|{}':;',.\\[\\].[0-9]<>/?~《》 ！@#￥%……&*（）——+|{}【】‘；：”“’。， 、？]";
            String aaa = "";//这里是将特殊字符换为aa字符串," "代表直接去掉
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(cc);//这里把想要替换的字符串传进来
            String newString = m.replaceAll(aaa).trim();
            
            
          System.out.print(newString+"\n");
            int length = newString.length();
            if(length >= 3){
                String str = newString.substring(0, length-3);
                System.out.print(str+"\n");
                aa.register(str);
            }else{
               
            }
                 
             return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    //区域截图
    public static File aaa (){
    	try {
    		
    		//Rectangle(60,195,400,40)
			BufferedImage image = new Robot().createScreenCapture(new Rectangle(60,195,400,40));
			DateFormat dfDirectory = new SimpleDateFormat("yyyyMMddhhmmss");
			File screenCaptureDirectory =new File("D:\\JIETU");
			 dfImageName = new SimpleDateFormat("yyyyMMddhhmmss");
			  imageFile = new File(screenCaptureDirectory, (dfImageName.format(new Date()) + ".png"));
			 ImageIO.write(image, "png", imageFile);
			 
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return imageFile;
    }

    public static void main(String[] args) {
    	
    	long start  = System.currentTimeMillis();
    	
    	Helloworld.accurate();
     	long end =  System.currentTimeMillis();
     	
    	
    	
    	
    }
}