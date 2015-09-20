package com.snapsofts.picture.object;

public class Picture {

	private int vitri,luotTai;
	private String Path;
	private String NamePictures , Information,time,day, month,years,chieucao,chieurong,categoryId;
	public Picture() {
		super();
	}
	public int getVitri() {
		return vitri;
	}
	public void setVitri(int vitri) {
		this.vitri = vitri;
	}
	public int getLuotTai() {
		return luotTai;
	}
	public void setLuotTai(int luotTai) {
		this.luotTai = luotTai;
	}
	public String getChieucao() {
		return chieucao;
	}
	public void setChieucao(String chieucao) {
		this.chieucao = chieucao;
	}
	public String getChieurong() {
		return chieurong;
	}
	public void setChieurong(String chieurong) {
		this.chieurong = chieurong;
	}
	public String getPath() {
		return Path;
	}
	public void setPath(String path) {
		Path = path;
	}
	public String getNamePictures() {
		return NamePictures;
	}
	public void setNamePictures(String namePictures) {
		NamePictures = namePictures;
	}
	public String getInformation() {
		return Information;
	}
	public void setInformation(String information) {
		Information = information;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYears() {
		return years;
	}
	public void setYears(String years) {
		this.years = years;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public Picture(int vitri, int luotTai, String path, String namePictures,
			String information, String time, String day, String month,
			String years, String chieucao, String chieurong, String categoryId) {
		super();
		this.vitri = vitri;
		this.luotTai = luotTai;
		Path = path;
		NamePictures = namePictures;
		Information = information;
		this.time = time;
		this.day = day;
		this.month = month;
		this.years = years;
		this.chieucao = chieucao;
		this.chieurong = chieurong;
		this.categoryId = categoryId;
	}
	
}
