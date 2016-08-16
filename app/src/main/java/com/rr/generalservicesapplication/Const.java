package com.rr.generalservicesapplication;

import java.util.ArrayList;

public class Const {


	public static final String URL_JSON_OBJECT = "http://takeawaymobileapplication.uk/clients/apis/generalservices/api.php?category=category";
	public static final String URL_JSON_ARRAY = "http://api.androidhive.info/volley/person_array.json";
	public static final String URL_STRING_REQ = "http://api.androidhive.info/volley/string_response.html";
	public static final String URL_IMAGE = "http://api.androidhive.info/volley/volley-image.jpg";
	private String id;
	private String name;

	public Const(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public Const(String id, String result_name, String result_email, String result_phone, String result_address, String result_image, String result_user_type, String result_cat_id, String result_exp, String result_desceription) {


	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	private  String phone;
	private  String address;
	private  String image;
	private  String user_type;
	private  String category;
	private  String experience;
	private  String description;

	public Const(String id, String name, String phone, String address, String image, String user_type, String category, String experience, String description) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.image = image;
		this.user_type = user_type;
		this.category = category;
		this.experience = experience;
		this.description = description;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}


