package com.deni.crudrest2.dto;

public class TestCustomDto {
	private long id;
	private String judul;
	private String deskripsi;
	private boolean terbit;

	public TestCustomDto() {
	}

	public TestCustomDto(long id, String judul, String deskripsi, boolean terbit) {
		this.id = id;
		this.judul = judul;
		this.deskripsi = deskripsi;
		this.terbit = terbit;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getJudul() {
		return judul;
	}

	public void setJudul(String judul) {
		this.judul = judul;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public boolean isTerbit() {
		return terbit;
	}

	public void setTerbit(boolean terbit) {
		this.terbit = terbit;
	}

}
