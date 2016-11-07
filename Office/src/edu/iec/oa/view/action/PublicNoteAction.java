package edu.iec.oa.view.action;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import edu.iec.oa.base.ModelDrivenBaseAction;
import edu.iec.oa.domain.PublicNote;
import edu.iec.oa.domain.User;
import edu.iec.oa.util.HqlHelper;

/**
 * @author TaoJG
 * 注入到IOC容器中
 * 多例获取
 * 职位更变Action
 */

@Controller
@Scope("prototype")
public class PublicNoteAction extends ModelDrivenBaseAction<PublicNote>{
	private static final long serialVersionUID = 1L;
	
	private String Vtitle;//
	private String Vtype;//
	private Long noteId;//赞，踩，转载的时候传来的原日志的Id：通过get和set方法就能获得
	
	/**列表 :应该先显示用户的信息*/
	public String list() throws Exception {
//		List<PublicNote> publicNoteList = publicNoteService.getAllByTimeDESC();//获取所有日志对象：降序
//		ActionContext.getContext().put("publicNoteList", publicNoteList);//将User对象 放到值栈中
		//构建查询条件
		new HqlHelper(PublicNote.class, "p")
			//过滤条件
			.addWhereCondition(Vtitle != null && Vtitle.length() > 0, "p.title LIKE ?", "%"+Vtitle+"%")//表题
			.addWhereCondition(Vtype != null && Vtype.length() > 0 && Vtype.equals(PublicNote.TYPE_ORIGINAL), "p.type=?", 0)//类型,原创
			.addWhereCondition(Vtype != null && Vtype.length() > 0 && Vtype.equals(PublicNote.TYPE_REPRINT), "p.type=?", 1)//类型，转载
			.addOrderByProperty("p.time", false)//排序：发表时间降序
			.preparePageBean(publicNoteService, pageNum);
		
		return "list";
	}

	/**添加页面*/
	public String addUI() throws Exception {
		//不需要准备数据，直接返回saveUI页面
		return "saveUI";
	}

	/**添加*/
	public String add() throws Exception {
		//前台封装的参数就不用封装了：但需要自己封装另外的一些属性
		//1,获得原对象
		//自己封装一些属性
		Long authorId = getCurrentUser().getId();
		User user = userService.getById(authorId);
		
		model.setAuthorId(authorId);
		model.setAuthor(user.getName());
		model.setTime(new Date());
		
		//刚添加的时候：赞，踩，转载数都为0
		model.setPraiseCount(0);
		model.setDemoteCount(0);
		model.setReprintCount(0);
		
		model.setType(0);//刚发表日志的类型为：原创
		
		publicNoteService.save(model);//保存到数据库
		return "toList";
	}
	
	/**阅读日志*/
	public String readNoteUI() throws Exception {
		//阅读日志：和查看相信信息很像
		//1.获得原对象,放到栈顶
		PublicNote publicNote = publicNoteService.getById(model.getId());
		System.out.println("id==="+model.getId());
		ActionContext.getContext().getValueStack().push(publicNote);
		return "readNoteUI";
	}
	
	/**赞日志*/
	public String praise() throws Exception {
		//阅读日志：和查看相信信息很像
		//1.根据传来的noteId参数获得对象
		PublicNote publicNote = publicNoteService.getById(noteId);
		System.out.println("前台传来的NoteId："+noteId);
		//修改赞的次数：+1
		publicNote.setPraiseCount(publicNote.getPraiseCount() + 1);
		//更新到数据库
		publicNoteService.update(publicNote);
		return "toReadNoteUI";
	}
	
	/**踩日志*/
	public String demote() throws Exception {
		//阅读日志：和查看相信信息很像
		//1.根据传来的noteId参数获得对象
		PublicNote publicNote = publicNoteService.getById(noteId);
		//修改踩的次数：+1
		publicNote.setDemoteCount(publicNote.getDemoteCount() + 1);
		//更新到数据库
		publicNoteService.update(publicNote);
		return "toReadNoteUI";
	}
	
	/**转载日志*/
	public String reprint() throws Exception {
		//阅读日志：和查看相信信息很像
		//1.根据传来的noteId参数获得对象
		PublicNote publicNote = publicNoteService.getById(noteId);
		
		
		//将得到的日志相关字段copy到自己的日志里面
		User user = userService.getById(getCurrentUser().getId());//通过session获得当前用户（转载人）
		model.setAuthorId(user.getId());
		model.setAuthor(user.getName());
		
		model.setTitle(publicNote.getTitle());
		model.setContent(publicNote.getContent());
		model.setOldAuthorId(publicNote.getAuthorId());
		model.setOldAuthor(publicNote.getAuthor());
		model.setTime(new Date());
		model.setPraiseCount(0);
		model.setDemoteCount(0);
		model.setReprintCount(0);
		model.setType(1);//类型，转载
		
		//更新到数据库
		publicNoteService.save(model);
		//修改转载的次数：+1
		publicNote.setReprintCount(publicNote.getReprintCount() + 1);
		//更新到数据库
		publicNoteService.update(publicNote);
		
		return "toList";
	}
	
	/**管理我的公开日志列表*/
	public String myNoteList() throws Exception {
		//获取所有当前用户日志对象，参数：作者姓名Id=当前登录用户名Id：降序
//		List<PublicNote> myNoteList = publicNoteService.getAllByMy(getCurrentUser().getId());
//		ActionContext.getContext().put("myNoteList", myNoteList);//将User对象 放到值栈中
		
		//构建查询条件
		new HqlHelper(PublicNote.class, "p")
			//过滤条件
			.addWhereCondition("author=?", getCurrentUser().getName())//过滤条件：当前用户
			.addWhereCondition(Vtitle != null && Vtitle.length() > 0, "p.title LIKE ?", "%"+Vtitle+"%")//表题
			.addOrderByProperty("p.time", false)//排序：时间降序
			.preparePageBean(publicNoteService, pageNum);
		
		return "myNoteList";
	}
	
	/**删除我的公开日志*/
	public String myNoteDelete() throws Exception {
		//获取所有当前用户日志对象，参数：作者姓名Id=当前登录用户名Id：降序
		publicNoteService.delete(model.getId());
		return "toMyNoteList";
	}
	/**查看日志*/
	public String readMyNoteUI() throws Exception {
		//阅读日志：和查看相信信息很像
		//1.获得原对象,放到栈顶
		PublicNote publicNote = publicNoteService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(publicNote);
		return "readMyNoteUI";
	}
	

	public String getVtitle() {
		return Vtitle;
	}

	public void setVtitle(String vtitle) {
		Vtitle = vtitle;
	}

	public Long getNoteId() {
		return noteId;
	}

	public void setNoteId(Long noteId) {
		this.noteId = noteId;
	}

	public String getVtype() {
		return Vtype;
	}

	public void setVtype(String vtype) {
		Vtype = vtype;
	}
}
