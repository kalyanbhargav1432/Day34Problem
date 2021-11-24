package javapractice;

import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class EmployeePayrollTest {

    @Test
    public void givenDataBaseConnection_ReturnDataFromDataBase(){
        EmployeePayroll employeePayroll = new EmployeePayroll();
        List<EmployeePayrollData> list = employeePayroll.readEmployeePayRoll();
        System.out.println(list);
        Assert.assertEquals(3, list.size());
    }

    @Test
    public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDB(){
        EmployeePayroll employeePayRollService = new EmployeePayroll();
        List<EmployeePayrollData> payRollDataList = employeePayRollService.readEmployeePayRoll();
        employeePayRollService.updateEmployeeSalary("kalyan",3000000.00);
        boolean result = employeePayRollService.checkEmployeePayrollInSyncWithDB("kalyan");
        Assert.assertTrue(result);
    }

    @Test
    public void givenDateRange_WhenRetrieved_ShouldMatchEmployeeCount(){
        EmployeePayroll employeePayRollService = new EmployeePayroll();
        employeePayRollService.readEmployeePayRoll();
        LocalDate startDate = LocalDate.of(1997,07,19);
        LocalDate endDate = LocalDate.now();
        List<EmployeePayrollData> employeePayRollData = employeePayRollService.readEmployeePayRollForDateRange(startDate, endDate);
        Assert.assertEquals(3, employeePayRollData.size());
    }

    @Test
    public void givenPayrollData_WhenAverageSalaryRetrieveByGender_ShouldReturnProperValue(){
        EmployeePayroll employeePayRollService = new EmployeePayroll();
        employeePayRollService.readEmployeePayRoll();
        Map<String, Double> averageSalaryByGender = employeePayRollService.readAverageSalaryByGender();
        Assert.assertTrue(averageSalaryByGender.get("F").equals(40000.00) && averageSalaryByGender.get("M").equals(1532000.00));
    }

    @Test
    public void givenPayrollData_WhenCountByGender_ShouldReturnCount(){
        EmployeePayroll employeePayRollService = new EmployeePayroll();
        employeePayRollService.readEmployeePayRoll();
        Map<String, Integer> countByGender = employeePayRollService.readCountSalaryByGender();
        Assert.assertTrue(countByGender.get("F").equals(1) && countByGender.get("M").equals(2));
    }
}