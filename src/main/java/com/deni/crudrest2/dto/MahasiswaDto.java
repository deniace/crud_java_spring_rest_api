package com.deni.crudrest2.dto;

public class MahasiswaDto {

	private int id;
	private String name;
	private String email;
	private String majors;

	public MahasiswaDto() {
	}

	public MahasiswaDto(int id, String name, String email, String majors) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.majors = majors;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMajors() {
		return majors;
	}

	public void setMajors(String majors) {
		this.majors = majors;
	}

}
