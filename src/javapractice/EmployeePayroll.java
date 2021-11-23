package javapractice;

import java.util.List;

public class EmployeePayroll {

    public List<EmployeePayrollData> readEmployeePayroll()  {
        List<EmployeePayrollData> employeePayRollList ;
        employeePayRollList = new EmployeePayrollService().readData();
        return employeePayRollList;
    }
}