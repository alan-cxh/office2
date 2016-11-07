package edu.iec.oa.view.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.iec.oa.domain.Department;
import edu.iec.oa.domain.ZD_jobTitle;
import edu.iec.oa.domain.ZD_nation;
import edu.iec.oa.domain.ZD_sex;
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
public class DC_TeacherResourceAction extends ActionSupport{
	
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
	
	
	//默认显示首页
	public String show() throws Exception {
		List<Department> departmentList = departmentService.findAll();
		//构造xml的开始
		String xml = "<?xml version='1.0' encoding='GBK'?>\n";
		//chart开始
		String chart = "<chart caption='各部门师资力量统计图' xAxisName='部门' yAxisName='人数' pieSliceDepth='30' " +
				"showBorder='0' formatNumberScale='0' numberSuffix='人' baseFontSize='12'>\n";
		//set开始
		String set = "";
		for(Department derpartment : departmentList){
			String name = derpartment.getName();
			Long value = userService.countByDepartmentName(name);
			set += "<set name='"+name+"' value='"+value+"'/>\n";
		}
		
		//最后拼接成完整的xml
		xml += chart + set + "</chart>";//xml结束
		ActionContext.getContext().put("xml", xml);
		return "show";
	}
	
	
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
