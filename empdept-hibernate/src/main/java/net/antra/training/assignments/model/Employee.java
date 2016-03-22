package net.antra.training.assignments.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
public class Employee implements Serializable {

    private static final long serialVersionUID = -1517498885542531938L;

    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private Department department;

    public Employee() {

    }

    public Employee(String firstName, String lastName, int age) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.age = age;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID", unique = true, nullable = false)
    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    @Column(name = "FIRST_NAME")
    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    @Column(name = "LAST_NAME")
    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    @Column(name = "AGE")
    public int getAge() {
	return age;
    }

    public void setAge(int age) {
	this.age = age;
    }

    @ManyToOne()
    @JoinColumn(name = "DEPARTMENT_ID")
    public Department getDepartment() {
	return department;
    }

    public void setDepartment(Department department) {
	this.department = department;
    }

}
