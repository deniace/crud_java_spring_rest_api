package com.deni.crudrest2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pegawai")
public class Pegawai {
	@Id
	@Column(name = "nip")
	private String nip;
	@Column(name = "nama")
	private String nama;
	@Column(name = "no_hp")
	private String noHp;
	@Column(name = "jabatan")
	private String jabatan;
	@Column(name = "departemen")
	private String departemen;

	public Pegawai() {
	}

	public Pegawai(String nip, String nama, String noHp, String jabatan, String departemen) {
		this.nip = nip;
		this.nama = nama;
		this.noHp = noHp;
		this.jabatan = jabatan;
		this.departemen = departemen;
	}

	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getNoHp() {
		return noHp;
	}

	public void setNoHp(String noHp) {
		this.noHp = noHp;
	}

	public String getJabatan() {
		return jabatan;
	}

	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}

	public String getDepartemen() {
		return departemen;
	}

	public void setDepartemen(String departemen) {
		this.departemen = departemen;
	}

}
