package com.baidu.ai.aip.utils;

import javax.imageio.ImageIO;

import java.awt.*;

import java.awt.image.BufferedImage;

import java.io.File;

import java.io.IOException;

import java.text.DateFormat;

import java.text.SimpleDateFormat;

import java.util.Date;

/**

* @author 小墨

* @date 2020/9/13 23:57

* 实现效果:

* 运行程序后可以直接截取当前屏幕内容并保存到指定路径，然后使用默认的图片查看程序自动打开截取的屏幕图片

* 实现思路:

* 首先获取屏幕的宽和高，然后使用工具类捕捉屏幕，使用当前时间创建目录和文件，然后打开文件

*/

public class JieTuUntil {
public static void main(String[] args) throws AWTException, IOException {
// 获取屏幕的尺寸(屏幕宽高)

 

 
// 使用工具类截取屏幕

//利用获取到的屏幕宽高捕捉当前的屏幕内容

BufferedImage image = new Robot().createScreenCapture(new Rectangle(60,195,400,40));

// 设置日期格式，作为目录名(例如：20200914)

DateFormat dfDirectory = new SimpleDateFormat("yyyyMMddhhmmss");

// 使用自定义路径创建一个用于保存图片的文件夹，我定义在了D盘

File screenCaptureDirectory =

new File(

"D:\\"

+ File.separator

+ "ScreenCapture"

+ File.separator

+ dfDirectory.format(new Date()));

//判断目录是否存在，不存在的话直接创建

if (!screenCaptureDirectory.exists()) {
//创建目录的操作

screenCaptureDirectory.mkdirs();

System.out.println("目录 " + screenCaptureDirectory.getName() + " 创建成功");

}

// 设置日期格式，作为图片名

DateFormat dfImageName = new SimpleDateFormat("yyyyMMddhhmmss");

// 指定路径，并以特定的日期格式作为图片的名称

File imageFile = new File(screenCaptureDirectory, (dfImageName.format(new Date()) + ".png"));

// 以指定的图片格式将截取的图片写到指定的文件

ImageIO.write(image, "png", imageFile);
 System.out.print(imageFile);
}

}
 