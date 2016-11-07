package edu.iec.oa.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import edu.iec.oa.domain.Department;

public class DepartmentTreeUtil {
	
	/**
	 * 遍历部门，把所有部门遍历出来放到同一个集合中，并且让所有部门的名称改变，以表示层次，用于显示给用户看
	 * @param topList:传来的顶级部门集合
	 * @return
	 */
	public static List<Department> getAllDepartment(List<Department> topList){
		/*for(Department d:topList){
			String name = d.getName();
			//System.out.println("传来的顶级部门:"+name);
		}*/
		List<Department> list = new ArrayList<Department>();
		walkDepartmentTreeList(topList,"┣",list);
		return list;
	}

	/**
	 * 遍历部门树，把遍历出来的信息放在集合中
	 * @param topList：传来的Department集合
	 * @param prefix：制表符，用于区分上下级
	 * @param list
	 */
	private static void walkDepartmentTreeList(Collection<Department> topList,String prefix, List<Department> list) {
		for(Department top : topList){
			//顶点
			Department copyDepartment = new Department();//使用副本，因为原对象在session中
			
			copyDepartment.setId(top.getId());
			copyDepartment.setName(prefix + top.getName());
			//将副本添加到集合中
			list.add(copyDepartment);
			//子树；递归调用
			walkDepartmentTreeList(top.getChildren(), "　"+ prefix, list);
		}
	}

}
