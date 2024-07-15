package com.example.login.dto;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component("loginDTO")
public class LoginDTO {
	private String email;
	private String name;
	private String pwd;
	private String tel;
	private String address;
	private String birth;
	private String regNumber;
	private String regCheck;
	private Date joinDate;
	private String role;
	
	public LoginDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public LoginDTO(String email,String name,String pwd,String tel,String address,String birth,String regNumber,String regCheck,Date joinDate,String role) {
		this.email=email;
		this.name=name;
		this.pwd=pwd;
		this.tel=tel;
		this.address=address;
		this.birth=birth;
		this.regNumber=regNumber;
		this.regCheck=regCheck;
		this.joinDate=joinDate;
		this.role=role;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getRegNumber() {
		return regNumber;
	}
	public void setRegNumber(String regNumber) {
		this.regNumber = regNumber;
	}
	public String getRegCheck() {
		return regCheck;
	}
	public void setRegCheck(String regCheck) {
		this.regCheck = regCheck;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
