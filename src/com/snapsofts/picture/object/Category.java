package com.snapsofts.picture.object;

public class Category {
	private int categoryId;
	private String NameCategory;
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getNameCategory() {
		return NameCategory;
	}
	public void setNameCategory(String nameCategory) {
		NameCategory = nameCategory;
	}
	public Category(int categoryId, String nameCategory) {
		super();
		this.categoryId = categoryId;
		NameCategory = nameCategory;
	}
	public Category() {
		super();
	}
}
