package edu.iec.oa.view.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ValidatePictureServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//doGet方法
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	//doPost方法
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//实例化ValidatePicture类
		ValidatePicture picturePicture = new ValidatePicture();
		
		String code = picturePicture.getCode();//获得验证码
		
		request.getSession().setAttribute("sessionCode", code.toLowerCase());//将验证码放在session中
		
		picturePicture.CreateValidatetPic(0, 0, code, response.getOutputStream());
	}
}


//生存验证码类
class ValidatePicture {

	private char mapTable[] = 
		{
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
			'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p',
			'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 
			'z', 'x', 'c', 'v', 'b', 'n', 'm'
		};
			
	/*
	 * 功能：生成彩色验证码图片 参数wedth为生成图片的宽度，参数height为生成图片的高度，参数os为页面的输出流
	 */
	
	public String getCode() {
		Random random = new Random();
		// 随机产生的验证码
		String strEnsure = "";
		// 4代表4为验证码，如果要产生更多位的验证码，则加大数值
		for (int i = 0; i < 4; ++i) {
			strEnsure += mapTable[random.nextInt(mapTable.length)];
		}
		return strEnsure;
	}
	
	public void CreateValidatetPic(int width, int height,String strEnsure, OutputStream os) {
		if (width <= 0)
			width = 60;
		if (height <= 0)
			height = 20;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 设定背景颜色
		g.setColor(new Color(0xDCDCDC));
		g.fillRect(0, 0, width, height);
		// 画边框
		g.setColor(Color.black);
		g.drawRect(0, 0, width - 1, height - 1);
		// 将认证码显示到图像中，如果要生成更多位的验证码，增加drawString语句
		g.setColor(Color.black);
		g.setFont(new Font("Atlantic Inline", Font.PLAIN, 18));
		String str = strEnsure.substring(0, 1);
		g.drawString(str, 8, 17);
		str = strEnsure.substring(1, 2);
		g.drawString(str, 20, 15);
		str = strEnsure.substring(2, 3);
		g.drawString(str, 35, 18);
		str = strEnsure.substring(3, 4);
		g.drawString(str, 45, 15);
		
		// 随机产生200个干扰点
		Random rand = new Random();
		for (int i = 0; i < 20; i++) {
			int x = rand.nextInt(width);
			int y = rand.nextInt(height);
			g.drawOval(x, y, 1, 1);
		}
		// 释放图形上下文
		g.dispose();
		try {
			// 输出图形到页面
			ImageIO.write(image, "JPEG", os);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

