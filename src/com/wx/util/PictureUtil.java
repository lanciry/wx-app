package com.wx.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 图片处理
 * @author meiiy
 * @version 2016年4月9日
 */
public class PictureUtil
{
	/**
	 * 保存图片
	 * @param stream 输入流
	 * @param path 保存路径
	 * @param filename 文件名
	 * @throws IOException
	 * @author meiiy
	 * @version 2016年4月9日
	 */
	public static void saveFileFromInputStream(InputStream stream, String path,
			String filename) throws IOException 
	{
		FileOutputStream fs = new FileOutputStream(path + File.separator + filename);
		byte[] buffer = new byte[1048576];
		int byteread = 0;
		while ((byteread = stream.read(buffer)) != -1) {
			fs.write(buffer, 0, byteread);
			fs.flush();
		}
		fs.close();
		stream.close();
	}

	/**
	 * 图片等比例压缩
	 * @param filePath
	 * @param thumbPath
	 * @throws Exception
	 * @author meiiy
	 * @version 2016年4月9日
	 */
	public static void toSmaillImg(String filePath, String thumbPath) throws Exception 
	{
		String newurl = thumbPath;
		Image bigJpg = ImageIO.read(new File(filePath));
		float tagsize = 200.0F;
		int old_w = bigJpg.getWidth(null);
		int old_h = bigJpg.getHeight(null);
		int new_w = 0;
		int new_h = 0;

		float tempdouble = old_w > old_h ? old_w / tagsize : old_h / tagsize;
		new_w = Math.round(old_w / tempdouble);
		new_h = Math.round(old_h / tempdouble);
		BufferedImage tag = new BufferedImage(new_w, new_h, 1);
		tag.getGraphics().drawImage(bigJpg, 0, 0, new_w, new_h, null);
		FileOutputStream newimage = new FileOutputStream(newurl);
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
		encoder.encode(tag);
		newimage.close();
	}
}
