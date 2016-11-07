package edu.iec.oa.view.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 通讯录Action
 * 
 * @author TaoJG 注入到IOC容器中 多例获取
 */

@Controller
@Scope("prototype")
public class AddressBookAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	/**
	 * 点击通讯录的时候，分别列出三种通讯录的类型：个人通讯录，公共通讯录，单位通讯录（这里默认就给了三种，不能添加删除修改）
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		return "list";
	}
}
