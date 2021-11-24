package javapractice;

import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDate;
import java.util.List;

public class EmployeePayrollTest {

	@Test
	public void givenDataBaseConnection_ReturnDataFromDataBase() {
		EmployeePayroll employeePayRollService = new EmployeePayroll();
		List<EmployeePayrollData> list = employeePayRollService.readEmployeePayRoll();
		System.out.println(list);
		Assert.assertEquals(3, list.size());
	}

	@Test
	public void givenNewSalaryForEmployee_WhenUpdated_ShouldSyncWithDB() {
		EmployeePayroll employeePayroll = new EmployeePayroll();
		List<EmployeePayrollData> payRollDataList = employeePayroll.readEmployeePayRoll();
		employeePayroll.updateEmployeeSalary("kalyan", 3000000.00);
		boolean result = employeePayroll.checkEmployeePayrollInSyncWithDB("kalyan");
		Assert.assertTrue(result);
	}

	@Test
	public void givenDateRange_WhenRetrieved_ShouldMatchEmployeeCount() {
		EmployeePayroll employeePayRollService = new EmployeePayroll();
		employeePayRollService.readEmployeePayRoll();
		LocalDate startDate = LocalDate.of(1997, 07, 19);
		LocalDate endDate = LocalDate.now();
		List<EmployeePayrollData> employeePayRollData = employeePayRollService
				.readEmployeePayRollForDateRange(startDate, endDate);
		Assert.assertEquals(2, employeePayRollData.size());
	}
}