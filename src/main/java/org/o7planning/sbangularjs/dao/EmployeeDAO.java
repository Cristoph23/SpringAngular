package org.o7planning.sbangularjs.dao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
  
import org.o7planning.sbangularjs.model.Employee;
import org.o7planning.sbangularjs.model.EmployeeForm;
import org.springframework.stereotype.Repository;
  
@Repository
public class EmployeeDAO {
  
    private static final Map<Long, Employee> empMap = new HashMap<Long, Employee>();
  
    
  
    public Long getMaxEmpId() {
        Set<Long> keys = empMap.keySet();
        Long max = 0L;
        for (Long key : keys) {
            if (key > max) {
                max = key;
            }
        }
        return max;
    }
  
    public Employee getEmployee(Long empId) {
        return empMap.get(empId);
    }
  
    public Employee addEmployee(EmployeeForm empForm) {
        Long empId= this.getMaxEmpId()+ 1;
        empForm.setEmpId(empId);
        Employee newEmp = new Employee(empForm);  
         
        empMap.put(newEmp.getEmpId(), newEmp);
        return newEmp;
    }
  
    public Employee updateEmployee(EmployeeForm empForm) {
        Employee emp = this.getEmployee(empForm.getEmpId());
        if(emp!= null)  {
            emp.setEmpNo(empForm.getEmpNo());
            emp.setEmpName(empForm.getEmpName());
            emp.setPosition(empForm.getPosition());
        }  
        return emp;
    }
  
    public void deleteEmployee(Long empId) {
        empMap.remove(empId);
    }
  
    public List<Employee> getAllEmployees() {
        Collection<Employee> c = empMap.values();
        List<Employee> list = new ArrayList<Employee>();
        list.addAll(c);
        return list;
    }
  
}