//Programmer: Csaba Palosi
//Student ID: B00148978
//Date Written: 13/05/2023
//Function: Employee data

import java.util.*;
import java.io.*;

class TestEmployee {
	public static void displayDetails(ArrayList<Employee> list) {
			for(int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).toString());
				}
				System.out.println();
			}
	public static void main (String [] args) {
		FullTimeEmployee f1=new FullTimeEmployee("5804973M","Mary","Mars",38000);
		FullTimeEmployee f2=new FullTimeEmployee("1276454P","John","Doe",29000);
		FullTimeEmployee f3=new FullTimeEmployee("6743567Z","Kate","Balsh",42000);

		PartTimeEmployee p1=new PartTimeEmployee("4536789F","Rose","Smith",17.50);
		PartTimeEmployee p2=new PartTimeEmployee("1231566J","Hanna","Montana",23.50);
		PartTimeEmployee p3=new PartTimeEmployee("4553355H","Harrison","Ford",28.50);

		ArrayList<Employee>emp=new ArrayList<Employee>();
		emp.add(f1);
		emp.add(f2);
		emp.add(f3);

		emp.add(p1);
		emp.add(p2);
		emp.add(p3);

		displayDetails(emp);
		}
	}
abstract class Employee {
	private String pps;
	private String firstName;
    private String lastName;

    public Employee(String pps, String firstName, String lastName) {
		this.pps=pps;
		this.firstName=firstName;
        this.lastName=lastName;
		}
	public String getPPS() {return pps;}
	public String getFirstName() {return firstName;}
	public String getLastName() {return lastName;}

	public String toString() {
	        return String.format("%s %s %s %n",getPPS(),getFirstName(),getLastName());
	        }
	}
class FullTimeEmployee extends Employee {
	private double salary;

	public FullTimeEmployee(String pps,String firstName,String lastName,double salary) {
		super(pps,firstName,lastName);
		this.salary=salary;
		}
	public double getSalary() {return salary;}

	public String toString() {
		return String.format("--------------------%nPPS: %s%nFirst Name: %s%nLast Name: %s%nYearly Salary: %s EURO",getPPS(),getFirstName(),getLastName(),getSalary());
		}
	}
class PartTimeEmployee extends Employee {
	private double hourlyPay;

	public PartTimeEmployee(String pps,String firstName,String lastName,double hourlyPay) {
		super(pps,firstName,lastName);
		this.hourlyPay=hourlyPay;
		}
	public double getHourlyPay() {return hourlyPay;}

	public String toString() {
		return String.format("--------------------%nPPS: %s%nFirst Name: %s%nLast Name: %s%nHourly Pay: %s EURO",getPPS(),getFirstName(),getLastName(),getHourlyPay());
		}
	}