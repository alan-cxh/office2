package edu.iec.oa.view.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.iec.oa.domain.AwardAndPunishment;
import edu.iec.oa.domain.Department;
import edu.iec.oa.domain.ZD_jobTitle;
import edu.iec.oa.domain.ZD_nation;
import edu.iec.oa.domain.ZD_sex;
import edu.iec.oa.service.AwardAndPunishmentService;
import edu.iec.oa.service.DepartmentService;
import edu.iec.oa.service.UserService;
import edu.iec.oa.service.ZD_jobTitleService;
import edu.iec.oa.service.ZD_nationService;
import edu.iec.oa.service.ZD_sexService;

/**
 * 数据统计：师资力量
 * @author admin
 *
 */
@Controller
@Scope("prototype")
public class DC_HumanResourceAction extends ActionSupport{
	
	private static final long serialVersionUID = 1L;

	@Resource
	private UserService userService;
	
	@Resource
	private DepartmentService departmentService;
	
	@Resource
	private ZD_sexService zD_sexService;
	
	@Resource
	private ZD_nationService zD_nationService;
	
	@Resource
	private ZD_jobTitleService zD_jobTitleService;
	
	@Resource
	private AwardAndPunishmentService awardAndPunishmentService;
	
	
	
	//默认显示首页
	@SuppressWarnings("static-access")
	public String show() throws Exception {
		//构造xml的开始
		String xml = "<?xml version='1.0' encoding='GBK'?>\n";
		//chart开始
		String chart = "<chart caption='奖惩人数统计图' xAxisName='奖惩类型' yAxisName='人数' pieSliceDepth='30' " +
				"showBorder='0' formatNumberScale='0' numberSuffix='人' baseFontSize='12'>\n";
		//准备奖励类型的数量:0代表奖励
		AwardAndPunishment awardAndPunishment = new AwardAndPunishment();
		Long awardValue = awardAndPunishmentService.countByType(awardAndPunishment.TYPE_AWARD);
		//准备惩处类型的数量：1代表惩处
		Long punishmentValue = awardAndPunishmentService.countByType(awardAndPunishment.TYPE_PUNISHMENT);
		//set开始
		String set = "";
		set += "<set name='奖励' value='"+awardValue+"'/>\n";
		set += "<set name='惩处' value='"+punishmentValue+"'/>\n";
		
		//最后拼接成完整的xml
		xml += chart + set + "</chart>";//xml结束
		ActionContext.getContext().put("xml", xml);
		return "show";
	}
	
	//部门-奖惩统计图
	@SuppressWarnings("static-access")
	public String departAwardAndPunishment() throws Exception {
		List<Department> departmentList = departmentService.findAll();
		AwardAndPunishment awardAndPunishment = new AwardAndPunishment();
		//构造xml的开始
		String xml = "<?xml version='1.0' encoding='GBK'?>\n";
		
		//xml的chart
		String chart = "<chart caption='部门-奖惩人数统计图' numberSuffix='人' formatNumberScale='1' " +
				"rotateValues='1' placeValuesInside='1' decimals='0' xAxisName='部门' yAxisName='奖惩类型' baseFontSize='12'" +
				"imageSave='1' >\n";
		
		//categories :横坐标
		String categories= "<categories>\n";
		
		//dataset:纵坐标
		String dataset = "";
		for(Department department : departmentList){
			categories += "<category label='"+department.getName()+"'/>\n";
		}
		categories += "</categories>\n";//横坐标：部门列表结束
		
		dataset += "<dataset seriesName='奖励'>\n";
		for(Department department : departmentList){
			//更具部门和奖励统计
			Long value = awardAndPunishmentService.countByDepartAndAward(department.getName(),awardAndPunishment.TYPE_AWARD);
			dataset += "<set value='"+value+"'/>\n";
		}
		dataset += "</dataset>\n";
		dataset += "<dataset seriesName='惩处'>\n";
		for(Department department : departmentList){
			Long value = awardAndPunishmentService.countByDepartAndPunishment(department.getName(),awardAndPunishment.TYPE_PUNISHMENT);
			dataset += "<set value='"+value+"'/>\n";
		}
		dataset += "</dataset>\n";
		
		//最终的xml
		xml += chart + categories + dataset + "</chart>";
		ActionContext.getContext().put("xml", xml);
		return "departAwardAndPunishment";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//===============================================================
	//民族统计人数图
	public String nation() throws Exception {
		List<ZD_nation> nationList = zD_nationService.findAll();
		//构造xml的开始
		String xml = "<?xml version='1.0' encoding='GBK'?>\n";
		//xml的chart
		String chart = "<chart caption='民族人数统计图' xAxisName='民族' yAxisName='人数' pieSliceDepth='30' " +
				"showBorder='0' formatNumberScale='0' numberSuffix='人' baseFontSize='12'>\n";
		//xml的set
		String set = "";
		for(ZD_nation nation : nationList){
			String name = nation.getName();
			Long value = userService.countByNation(name);
			set += "<set name='"+name+"' value='"+value+"'/>\n";
		}
		
		//最后拼接成完整的xml
		xml += chart + set+ "</chart>";//xml结束
		ActionContext.getContext().put("xml", xml);
		return "nation";
	}
	
	//职称统计人数图
	public String jobTitle() throws Exception {
		List<ZD_jobTitle> jobTitleList = zD_jobTitleService.findAll();
		//构造xml的开始
		String xml = "<?xml version='1.0' encoding='GBK'?>\n";
		//xml的chart
		String chart = "<chart caption='职称人数统计图' xAxisName='职称' yAxisName='人数' pieSliceDepth='30' " +
				"showBorder='0' formatNumberScale='0' numberSuffix='人' baseFontSize='12'>\n";
		//xml的set
		String set = "";
		for(ZD_jobTitle jobTitle : jobTitleList){
			String name = jobTitle.getName();
			Long value = userService.countByJobTitle(name);
			set += "<set name='"+name+"' value='"+value+"'/>\n";
		}
		
		//最后拼接成完整的xml
		xml += chart + set+ "</chart>";//xml结束
		ActionContext.getContext().put("xml", xml);
		return "jobTitle";
	}
	
	//性别统计人数图
	public String gender() throws Exception {
		List<ZD_sex> sexList = zD_sexService.findAll();
		//构造xml的开始
		String xml = "<?xml version='1.0' encoding='GBK'?>\n";
		//xml的chart
		String chart = "<chart caption='性别人数统计图' xAxisName='性别' yAxisName='人数' pieSliceDepth='30' " +
				"showBorder='0' formatNumberScale='0' numberSuffix='人' baseFontSize='12'>\n";
		//xml的set
		String set = "";
		for(ZD_sex sex : sexList){
			String name = sex.getName();
			Long value = userService.countByGender(name);
			set += "<set name='"+name+"' value='"+value+"'/>\n";
		}
		
		//最后拼接成完整的xml
		xml += chart + set+ "</chart>";//xml结束
		ActionContext.getContext().put("xml", xml);
		return "gender";
	}
	
	//部门-性别统计图
	public String departGender() throws Exception {
		List<Department> departmentList = departmentService.findAll();
		List<ZD_sex> sexList = zD_sexService.findAll();
		
		//构造xml的开始
		String xml = "<?xml version='1.0' encoding='GBK'?>\n";
		
		//xml的chart
		String chart = "<chart caption='部门-性别统计图' numberSuffix='人' formatNumberScale='1' " +
				"rotateValues='1' placeValuesInside='1' decimals='0' xAxisName='部门' yAxisName='人数' baseFontSize='12'" +
				"imageSave='1' >\n";
		
		//categories :横坐标
		String categories= "<categories>\n";
		
		//dataset:纵坐标
		String dataset = "";
		for(Department depart : departmentList){
			categories += "<category label='"+depart.getName()+"'/>\n";
		}
		categories += "</categories>\n";//横坐标：部门列表结束
		
		
		for(ZD_sex sex : sexList){
			dataset += "<dataset seriesName='"+sex.getName()+"'>\n";
			for(Department depart : departmentList){
				Long value = userService.countByDepartAndGender(sex.getName(),depart.getName());
				dataset += "<set value='"+value+"'/>\n";
			}
			dataset += "</dataset>\n";
		}
		
		xml += chart + categories + dataset + "</chart>";
		ActionContext.getContext().put("xml", xml);
		return "departGender";
	}
	
	//职称-性别统计图
	public String jobTitleGender() throws Exception {
		List<ZD_jobTitle> jobTitleList = zD_jobTitleService.findAll();
		List<ZD_sex> sexList = zD_sexService.findAll();
		
		//构造xml的开始
		String xml = "<?xml version='1.0' encoding='GBK'?>\n";
		
		//xml的chart
		String chart = "<chart caption='职称-性别统计图' numberSuffix='人' formatNumberScale='1' " +
				"rotateValues='1' placeValuesInside='1' decimals='0' xAxisName='职称' yAxisName='人数' baseFontSize='12'" +
				"imageSave='1' >\n";
		
		//categories :横坐标
		String categories= "<categories>\n";
		
		//dataset:纵坐标
		String dataset = "";
		for(ZD_jobTitle jobTitle : jobTitleList){
			categories += "<category label='"+jobTitle.getName()+"'/>\n";
		}
		categories += "</categories>\n";//横坐标：部门列表结束
		
		
		for(ZD_sex sex : sexList){
			dataset += "<dataset seriesName='"+sex.getName()+"'>\n";
			for(ZD_jobTitle jobTitle : jobTitleList){
				Long value = userService.countByJobTitletAndGender(jobTitle.getName(),sex.getName());
				dataset += "<set value='"+value+"'/>\n";
			}
			dataset += "</dataset>\n";
		}
		
		xml += chart + categories + dataset + "</chart>";
		ActionContext.getContext().put("xml", xml);
		return "jobTitleGender";
	}
	
	
	
	//部门-职称统计图
	public String departJobTitle() throws Exception {
		List<Department> departmentList = departmentService.findAll();
		List<ZD_jobTitle> jobTitleList = zD_jobTitleService.findAll();
		
		//构造xml的开始
		String xml = "<?xml version='1.0' encoding='GBK'?>\n";
		
		//xml的chart
		String chart = "<chart caption='部门-职称统计图' numberSuffix='人' formatNumberScale='1' " +
				"rotateValues='1' placeValuesInside='1' decimals='0' xAxisName='部门' yAxisName='职称' baseFontSize='12'" +
				"imageSave='1' >\n";
		
		//categories :横坐标
		String categories= "<categories>\n";
		
		//dataset:纵坐标
		String dataset = "";
		for(Department department : departmentList){
			categories += "<category label='"+department.getName()+"'/>\n";
		}
		categories += "</categories>\n";//横坐标：部门列表结束
		
		
		for(ZD_jobTitle jobTitle : jobTitleList){
			dataset += "<dataset seriesName='"+jobTitle.getName()+"'>\n";
			for(Department department : departmentList){
				Long value = userService.countByDepartmentAndJobTitlet(department.getName(),jobTitle.getName());
				dataset += "<set value='"+value+"'/>\n";
			}
			dataset += "</dataset>\n";
		}
		
		xml += chart + categories + dataset + "</chart>";
		ActionContext.getContext().put("xml", xml);
		return "departJobTitle";
	}
}
