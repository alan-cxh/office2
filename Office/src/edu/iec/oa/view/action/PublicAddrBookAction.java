package edu.iec.oa.view.action;


import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import edu.iec.oa.base.ModelDrivenBaseAction;
import edu.iec.oa.domain.PublicAddrBook;
import edu.iec.oa.util.HqlHelper;

/**
 * @author TaoJG IOC注入，多列获取 
 * 公共通讯录：每个用户都可以创建，单添加，删除，修改根据权限
 */
@Controller
@Scope("prototype")
public class PublicAddrBookAction extends ModelDrivenBaseAction<PublicAddrBook> {
	private static final long serialVersionUID = 1L;
	
	//查询条件
	private String Vname;
	private String Vgender;
	
	/**列表*/
	public String list() throws Exception {
//		List<PublicAddrBook> publicAddrBookList = publicAddrBookService.findAll();
//		ActionContext.getContext().put("publicAddrBookList", publicAddrBookList);
		
		//构造查询条件
		new HqlHelper(PublicAddrBook.class, "p")//
			.addWhereCondition(Vname != null && Vname.length() > 0, "p.name LIKE ?", "%"+Vname+"%")//
			.addWhereCondition(Vgender != null && Vgender.length() > 0, "p.gender=?", Vgender)//
			.addOrderByProperty("p.id", true)//排序：id升序
			.preparePageBean(publicAddrBookService, pageNum);
		
		return "list";
	}

	/**添加*/
	public String add() throws Exception {
		//由于是公共通讯录：为了便于管理和检查，需要记录添加人的信息
		model.setUser(getCurrentUser().getName());//获取添加的人
		model.setIpAddress(ServletActionContext.getRequest().getRemoteAddr());//获取添加的地址
		//前台传来的数据已经封装到model里面了
		publicAddrBookService.save(model);
		return "toList";
	}

	/**添加页面*/
	public String addUI() throws Exception {
		return "saveUI";
	}

	/**修改*/
	public String update() throws Exception {
		//从数据库中获取元对象
		PublicAddrBook publicAddrBook = publicAddrBookService.getById(model.getId());
		//修改相关字段
		publicAddrBook.setName(model.getName());
		publicAddrBook.setGender(model.getGender());
		publicAddrBook.setPhone(model.getPhone());
		publicAddrBook.setMobilePhone(model.getMobilePhone());
		publicAddrBook.setQQ(model.getQQ());
		publicAddrBook.setEmail(model.getEmail());
		publicAddrBook.setDeparment(model.getDeparment());
		publicAddrBook.setPostCode(model.getPostCode());
		publicAddrBook.setHobby(model.getHobby());
		publicAddrBook.setBirthday(model.getBirthday());
		publicAddrBook.setAddress(model.getAddress());
		publicAddrBook.setDescription(model.getDescription());
		//跟更新到数据库
		publicAddrBookService.update(publicAddrBook);
		return "toList";
	}

	/**修改页面*/
	public String updateUI() throws Exception {
		//从数据库中获取元对象
		PublicAddrBook publicAddrBook = publicAddrBookService.getById(model.getId());
		//放到栈顶：用于回显数据
		ActionContext.getContext().getValueStack().push(publicAddrBook);
		return "saveUI";
	}

	/**删除*/
	public String delete() throws Exception {
		publicAddrBookService.delete(model.getId());
		return "toList";
	}
	//查看通讯录的详细信息
	public String detailInfoUI() throws Exception {
		PublicAddrBook publicAddrBook = publicAddrBookService.getById(model.getId());
		//放到栈顶
		ActionContext.getContext().getValueStack().push(publicAddrBook);
		return "detailInfoUI";
	}
	
	public String getVname() {
		return Vname;
	}

	public void setVname(String vname) {
		Vname = vname;
	}

	public String getVgender() {
		return Vgender;
	}

	public void setVgender(String vgender) {
		Vgender = vgender;
	}

}
