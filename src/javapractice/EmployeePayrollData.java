package javapractice;

import java.time.LocalDate;
import java.util.Date;

public class EmployeePayrollData {
	public int id;
	public String name;
	public Double salary;
	public LocalDate start;

	public EmployeePayrollData(int id, String name, Double salary) {
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	public EmployeePayrollData(int id, String name, Double salary, LocalDate start) {
		this(id, name, salary);
		this.start = start;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		EmployeePayrollData that = (EmployeePayrollData) o;
		return id == that.id && name.equals(that.name) && salary.equals(that.salary) && start.equals(that.start);
	}

	@Override
	public String toString() {
		return "PayRollData{" + "id=" + id + ", name='" + name + '\'' + ", salary=" + salary + ", start=" + start + '}';
	}
}