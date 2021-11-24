package javapractice;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class EmployeePayroll {
	private List<EmployeePayrollData> employeePayrollList;
	private EmployeePayrollService employeePayRoll;

	public EmployeePayroll() {
		employeePayRoll = EmployeePayrollService.getInstance();
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
		int result = employeePayRoll.updateEmployeeData(name, salary);
		if (result == 0)
			return;
		EmployeePayrollData employeePayrollData = this.getEmployeePayRollData(name);
		if (employeePayrollData != null)
			employeePayrollData.salary = salary;
	}

	private EmployeePayrollData getEmployeePayRollData(String name) {
		EmployeePayrollData employeePayrollData;
		employeePayrollData = this.employeePayrollList.stream()
				.filter(employeePayrollDataItem -> employeePayrollDataItem.name.equals(name)).findFirst().orElse(null);
		return employeePayrollData;
	}

	public boolean checkEmployeePayrollInSyncWithDB(String name) {
		List<EmployeePayrollData> employeePayrollDataList = employeePayRoll.getEmployeePayrollData(name);
		return employeePayrollDataList.get(0).equals(getEmployeePayRollData(name));
	}

	public List<EmployeePayrollData> readEmployeePayRollForDateRange(LocalDate startDate, LocalDate endDate) {
		return employeePayRoll.getEmployeeForDateRange(startDate, endDate);
	}

	public Map<String, Double> readAverageSalaryByGender() {
		return employeePayRoll.getAverageSalaryByGender();
	}

	public Map<String, Integer> readCountSalaryByGender() {
		return employeePayRoll.getCountByGender();
	}

	public void addEmployeePayroll(String name, double salary, LocalDate startDate, String gender) {
		employeePayrollList.add(employeePayRoll.addEmployeeToPayroll(name, salary,startDate, gender ));
	}
}