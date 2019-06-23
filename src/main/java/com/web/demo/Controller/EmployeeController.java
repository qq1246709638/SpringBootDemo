package com.web.demo.Controller;

import com.web.demo.dao.DepartmentDao;
import com.web.demo.dao.EmployeeDao;
import com.web.demo.entities.Department;
import com.web.demo.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;
    @Autowired
    DepartmentDao departmentDao;
    @GetMapping("/emps")
    //查询所有员工返回list列表页面
    public String list(Model model){
        //将2放在请求域中,
        Collection<Employee> employees= employeeDao.getAll();
        model.addAttribute("emps",employees);
        //返回员工列表
        return "emp/list";
    }
    //转到添加页面
    @GetMapping("/emp")

    public String toaddpage (Model model){
        //查出所有部门，在选择条中显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return "emp/add";
    }
        //添加员工
    @PostMapping("/emp")
    //SpringMvc自动将请求参数和入参对象的属性进行一一绑定：要求请求参数名字和JavaBean入参的对象里面的属性一致
    public String addemp(Employee employee) {
        //redirect: 表示重定向到一个地址
        //forward: 表示转发到一个地址
        employeeDao.save(employee);
        System.out.println("保存的员工信息" + employee);
        //转发到emps
        return "redirect:/emps";
    }
    //来到修改页面，查出当前员工，在页面回显
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp",employee);
        //查出部门，在选择条中显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        //回到修改页面
        return "emp/add";
    }
    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        System.out.println("employee"+employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id ){
        System.out.println(id);
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}

