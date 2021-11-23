package javapractice;

import org.junit.Assert;
import org.junit.Test;
import java.util.List;

public class EmployeePayrollTest {
	@Test
	public void givenDataBaseConnection_ReturnDataFromDataBase() {
		EmployeePayroll employeePayroll = new EmployeePayroll();
		List<EmployeePayrollData> list = employeePayroll.readEmployeePayroll();
		System.out.println(list);
		Assert.assertEquals(3, list.size());
	}
}
