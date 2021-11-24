package javapractice;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class EmployeePayroll {
    private List<EmployeePayrollData> employeePayRollList;
    private EmployeePayrollService employeePayroll;

    public EmployeePayroll(){
        employeePayroll = EmployeePayrollService.getInstance();
    }

    public EmployeePayroll(List<EmployeePayrollData> employeePayRollList){
        this();
        this.employeePayRollList = employeePayRollList;
    }

    public List<EmployeePayrollData> readEmployeePayRoll(){
        this.employeePayRollList = EmployeePayrollService.getInstance().readData();
        return employeePayRollList;
    }

    public void updateEmployeeSalary(String name, double salary) {
        int result = employeePayroll.updateEmployeeData(name, salary);
        if(result == 0) return;
        EmployeePayrollData employeePayRollData = this.getEmployeePayRollData(name);
        if (employeePayRollData != null)
            employeePayRollData.salary = salary;
    }

    private EmployeePayrollData getEmployeePayRollData(String name) {
        EmployeePayrollData employeePayrollData;
        employeePayrollData = this.employeePayRollList.stream().filter(employeePayrollDataItem -> employeePayrollDataItem.name.equals(name)).findFirst().orElse(null);
        return employeePayrollData;
    }

    public boolean checkEmployeePayrollInSyncWithDB(String name) {
        List<EmployeePayrollData> employeePayrollDataList = employeePayroll.getEmployeePayrollData(name);
        return employeePayrollDataList.get(0).equals(getEmployeePayRollData(name));
    }

    public List<EmployeePayrollData> readEmployeePayRollForDateRange(LocalDate startDate, LocalDate endDate) {
        return employeePayroll.getEmployeeForDateRange(startDate, endDate);
    }

    public Map<String, Double> readAverageSalaryByGender() {
        return employeePayroll.getAverageSalaryByGender();
    }

    public Map<String, Integer> readCountSalaryByGender() {
        return employeePayroll.getCountByGender();
    }
}