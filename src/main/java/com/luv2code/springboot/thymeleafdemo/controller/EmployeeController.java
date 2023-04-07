package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	// load employee data
@Autowired
	EmployeeService employeeService;



	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		 List<Employee> theEmployees=employeeService.findAll();
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "employees/list-employees";
	}
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Employee employee=new Employee();
		theModel.addAttribute("employee", employee);
		return "employees/employee-form";
	}
	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee employee){
		employeeService.save(employee);
		return "redirect:/employees/list";
	}
      @GetMapping("/showFormForUpdate")
	public String update(@RequestParam(value = "employeeId") int id,Model model){
		Employee employee=employeeService.findById(id);
		model.addAttribute("employee",employee);
		return "/employees/employee-form";
	  }
	  @GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int id){
		employeeService.deleteById(id);
		return "redirect:/employees/list";
	  }
}









