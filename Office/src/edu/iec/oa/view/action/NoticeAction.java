package edu.iec.oa.view.action;

import java.util.Date;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import edu.iec.oa.base.ModelDrivenBaseAction;
import edu.iec.oa.domain.Notice;
import edu.iec.oa.util.HqlHelper;
/**
 * @author TaoJG
 * IOC注入，多例
 */
@Controller
@Scope("prototype")
public class NoticeAction extends ModelDrivenBaseAction<Notice>{
	private static final long serialVersionUID = 1L;
	private String Vtitle;
	
	/**公告栏*/
	public String noticeUI() throws Exception {
//		List<Notice> noticeListByIdByDESC = noticeService.findAllByIdByDESC();//从数据库中查取所有公告，倒序
//		ActionContext.getContext().put("noticeListByIdByDESC", noticeListByIdByDESC);//将公告放到值栈中
		//构建查询条件
		new HqlHelper(Notice.class, "n")
			//过滤条件
			.addWhereCondition(Vtitle != null && Vtitle.length() > 0, "n.title LIKE ?", "%"+Vtitle+"%")//公告标题
			.addOrderByProperty("n.publishDate", false)//排序：降序
			.preparePageBean(noticeService, pageNum);
		return "noticeUI";
	}
	
	/**查看公告详细信息*/
	public String detailInfoUI() throws Exception {
		Notice notice = noticeService.getById(model.getId());//从数据库中查取对应的那条公告
		ActionContext.getContext().getValueStack().push(notice);//将公告放到栈顶
		return "detailInfoUI";
	}
	
	/**列表*/
	public String list() throws Exception {
//		List<Notice> noticeList = noticeService.findAll();//从数据库中查取所有公告
//		ActionContext.getContext().put("noticeList", noticeList);//将公告放到值栈中
		//构建查询条件
		new HqlHelper(Notice.class, "n")
			//过滤条件
			.addWhereCondition(Vtitle != null && Vtitle.length() > 0, "n.title LIKE ?", "%"+Vtitle+"%")//公告标题
			.addOrderByProperty("n.id", true)//排序：升序
			.preparePageBean(noticeService, pageNum);
		return "list";
	}

	/**添加*/
	public String add() throws Exception {
		//这两项没有从前台传来，所以要封装到model中，从前台转来的已经封装到model中了
		model.setPublishUser(getCurrentUser());//发布者是从登录的session中获取
		Date currentTime = new Date();//获取当前时间
		model.setPublishDate(currentTime);
		
		noticeService.save(model);
		return "toList";
	}

	/**添加页面*/
	public String addUI() throws Exception {
		return "saveUI";
	}

	/**修改*/
	public String update() throws Exception {
		//从数据库中获得原对象
		Notice notice = noticeService.getById(model.getId());
		//设置要修改的字段,发布时间和发布人不能修改
		notice.setTitle(model.getTitle());
		notice.setContent(model.getContent());
		//保存到数据库
		noticeService.update(notice);
		return "toList";
	}

	/**修改页面*/
	public String updateUI() throws Exception {
		//准备修改公告页面回显数据
		Notice notice = noticeService.getById(model.getId());
		//将数据放到栈顶，用于回显数据
		ActionContext.getContext().getValueStack().push(notice);
		return "saveUI";
	}

	/**删除*/
	public String delete() throws Exception {
		noticeService.delete(model.getId());
		return "toList";
	}

	public String getVtitle() {
		return Vtitle;
	}

	public void setVtitle(String vtitle) {
		Vtitle = vtitle;
	}

}
