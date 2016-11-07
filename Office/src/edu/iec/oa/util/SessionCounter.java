package edu.iec.oa.util;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author TaoJG
 * 统计session，也就是统计在线人数的监听器
 */
public class SessionCounter implements HttpSessionListener {

	//定义一个静态的Internet常量统计在线人数
	public static int activeSession = 0;

	//当创建一个session的时候，在线人数就加
	public void sessionCreated(HttpSessionEvent session) {
		activeSession++;
	}

	//当销毁一个session的时候，在线人数就减
	public void sessionDestroyed(HttpSessionEvent session) {
		if(activeSession > 0){
			activeSession--;
		}
	}
	
	//得到在线人数
	public static int getActiveSession(){
		System.out.println("在线人数："+activeSession);
		return activeSession;
	}

}
