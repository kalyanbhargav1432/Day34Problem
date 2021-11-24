package javapractice;

import java.time.LocalDate;
import java.util.List;

public class EmployeePayroll {
	private List<EmployeePayrollData> employeePayrollList;
	private EmployeePayrollService employeePayroll;

	public EmployeePayroll() {
		employeePayroll = EmployeePayrollService.getInstance();
	}

	public EmployeePayroll(List<EmployeePayrollData> employeePayRollList) {
		this();
		this.employeePayrollList = employeePayRollList;
	}

	public List<EmployeePayrollData> readEmployeePayRoll() {
		this.employeePayrollList = EmployeePayrollService.getInstance().readData();
		return employeePayrollList;
	}

	public void updateEmployeeSalary(String name, double salary) {
		int result = employeePayroll.updateEmployeeData(name, salary);
		if (result == 0)
			return;
		EmployeePayrollData employeePayrollData = this.getEmployeePayrollData(name);
		if (employeePayrollData != null)
			employeePayrollData.salary = salary;
	}

	private EmployeePayrollData getEmployeePayrollData(String name) {
		EmployeePayrollData employeePayrollData;
		employeePayrollData = this.employeePayrollList.stream()
				.filter(employeePayrollDataItem -> employeePayrollDataItem.name.equals(name)).findFirst().orElse(null);
		return employeePayrollData;
	}

	public boolean checkEmployeePayrollInSyncWithDB(String name) {
		List<EmployeePayrollData> employeePayrollDataList = employeePayroll.getEmployeePayrollData(name);
		return employeePayrollDataList.get(0).equals(getEmployeePayrollData(name));
	}

	public List<EmployeePayrollData> readEmployeePayRollForDateRange(LocalDate startDate, LocalDate endDate) {
		return employeePayroll.getEmployeeForDateRange(startDate, endDate);
	}
}