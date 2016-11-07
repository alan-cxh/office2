package edu.iec.oa.view.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import edu.iec.oa.base.ModelDrivenBaseAction;
import edu.iec.oa.domain.PersonalAddrBook;
import edu.iec.oa.util.HqlHelper;

/**
 * @author TaoJG IOC注入，多列获取 
 * 由于公共通讯录的信息是来自OA里面用户的相关通讯信息，所以传User
 */
@Controller
@Scope("prototype")
public class PersonalAddrBookAction extends ModelDrivenBaseAction<PersonalAddrBook> {
	private static final long serialVersionUID = 1L;
	
	//查询条件
	private String Vname;
	private String Vgender;
	
	

	/**列表*/
	public String list() throws Exception {
//  	List<PersonalAddrBook> personalAddrBookList = personalAddrBookService.findAllByCurrentUser(getCurrentUser());
//		ActionContext.getContext().put("personalAddrBookList", personalAddrBookList);
		
		//构造查询条件
		new HqlHelper(PersonalAddrBook.class, "p")//
			.addWhereCondition("p.user=?", getCurrentUser())//过滤条件：当前用户
			.addWhereCondition(Vname != null && Vname.length() > 0, "p.name LIKE ?", "%"+Vname+"%")//
			.addWhereCondition(Vgender != null && Vgender.length() > 0, "p.gender=?", Vgender)//
			.addOrderByProperty("p.id", true)//排序：id升序
			.preparePageBean(personalAddrBookService, pageNum);
		
		return "list";
	}

	/**添加*/
	public String add() throws Exception {
		//user(拥有者)没有从前台传来，所以要封装到model中，从前台转来的属性已经封装到model中了
		model.setUser(getCurrentUser());
		personalAddrBookService.save(model);
		return "toList";
	}

	/**添加页面*/
	public String addUI() throws Exception {
		return "saveUI";
	}

	/**修改*/
	public String update() throws Exception {
		//获取原对象
		PersonalAddrBook personalAddrBook = personalAddrBookService.getById(model.getId());
		//从前台获取修改的相关字段：修改
		personalAddrBook.setName(model.getName());
		personalAddrBook.setGender(model.getGender());
		personalAddrBook.setPhone(model.getPhone());
		personalAddrBook.setMobilePhone(model.getMobilePhone());
		personalAddrBook.setQQ(model.getQQ());
		personalAddrBook.setEmail(model.getEmail());
		personalAddrBook.setDeparment(model.getDeparment());
		personalAddrBook.setPostCode(model.getPostCode());
		personalAddrBook.setHobby(model.getHobby());
		personalAddrBook.setBirthday(model.getBirthday());
		personalAddrBook.setAddress(model.getAddress());
		personalAddrBook.setDescription(model.getDescription());
		//更新到数据库
		personalAddrBookService.update(personalAddrBook);
		return "toList";
	}

	/**修改页面*/
	public String updateUI() throws Exception {
		//从数据库中读取原对象
		PersonalAddrBook personalAddrBook =  personalAddrBookService.getById(model.getId());
		//放到栈顶里面：回显
		ActionContext.getContext().getValueStack().push(personalAddrBook);
		return "saveUI";
	}

	/**删除*/
	public String delete() throws Exception {
		//直接调用service
		personalAddrBookService.delete(model.getId());
        
		return "toList";
	}
	
	//查看通讯录的详细信息
	public String detailInfoUI() throws Exception {
		PersonalAddrBook personalAddrBook = personalAddrBookService.getById(model.getId());
		//放到栈顶
		ActionContext.getContext().getValueStack().push(personalAddrBook);
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
