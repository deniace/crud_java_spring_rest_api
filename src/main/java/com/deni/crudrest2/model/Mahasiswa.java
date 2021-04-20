package com.deni.crudrest2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mahasiswa")
public class Mahasiswa {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "nama")
	private String nama;
	@Column(name = "email")
	private String email;
	@Column(name = "jurusan")
	private String jurusan;

	public Mahasiswa() {
	}

	public Mahasiswa(int id, String nama, String email, String jurusan) {
		this.id = id;
		this.nama = nama;
		this.email = email;
		this.jurusan = jurusan;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getJurusan() {
		return jurusan;
	}

	public void setJurusan(String jurusan) {
		this.jurusan = jurusan;
	}

}
