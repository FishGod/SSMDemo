package com.wjc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wjc.bean.Employee;
import com.wjc.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 处理员工CURD请求
 */
@Controller
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	/**
	 * 查询员工数据(分页查询)
	 * @return
	 */

	@RequestMapping("/emps")
	public String getEmps(@RequestParam(value="pn" ,defaultValue="1")Integer pn,Model model){ //传入当前页面，如果没有传则默认为1

		//引入PageHelper分页插件
		//在查询之前只需要调用,传入页码，以及分页每一页的大小
		PageHelper.startPage(pn, 5);
		//startPage后面紧跟的这个查询就是一个分页查询
		List<Employee> emps =employeeService.getAll();
		//使用pageInfo包装查询后的信息,只需要将pageinfo交给页面就行了。
		//封装了详细的分页信息,包括有我们查询出来的数据,传入:连续显示的页数
		PageInfo page=new PageInfo(emps,5); //5表示要连续显示的页数
		model.addAttribute("PageInfo",page);
		return "list";

	}
}
