package edu.iec.oa.util;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import edu.iec.oa.domain.User;

/**
 * @author TaoJG
 * 权限拦截器
 */
public class CheckPrivilegeIntercepter extends AbstractInterceptor{

	private static final long serialVersionUID = 1L;

	public String intercept(ActionInvocation invocation) throws Exception {

		//获取登录用户
		User user = (User) ActionContext.getContext().getSession().get("user");
		String nameSpace = invocation.getProxy().getNamespace();//获得namespace名字
		String actionName = invocation.getProxy().getActionName();//获得action名字
		
		String priUrl = nameSpace+actionName;//对应权限的URL
		
		if(user == null){/**如果为登录，跳到登录页面*/
			if(priUrl.endsWith("/user_login")){//如果正在登录，放行;/user_login ./user_loginUI都要放行
				return invocation.invoke();
			}else{
				return "loginUI";
			}
		}else{/**如果已登录，判断权限*/
			if(user.hasPrivilegeByUrl(priUrl)){//如果有访问权限，就放行
				return invocation.invoke();//放行
			}else{//如果没有访问权限，就跳到提示页面
				return "noPrivilegeError";
			}
		}
	}
}
