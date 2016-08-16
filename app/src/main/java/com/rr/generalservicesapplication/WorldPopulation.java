package com.rr.generalservicesapplication;

public class WorldPopulation {

    private String Email;
    private String population;
    private String flag;
    private String epm_name;
    private String emp_address;
    private String emp_phone;
    private String emp_category;
    private String emp_experience;
    private String emp_photo;
    private String empId;
    String rating;
    String comments;
    String singleEmpId;
    String job_status;
    String job_Discription;
    String emp_discription;
    String strUserName;
    String password;
    String email;
    String strReasonJobCancel;
    String ratingCounter;
    String strEmpObjid;
    String newRatingCounter;
    String id;
    String userPhone;

    String str_EmpObjid;

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }


	public WorldPopulation(String empId,String epm_name,String email,String emp_phone,String emp_category,String job_status
    ,String emp_discription,String emp_experience,String flag) {
     this.empId=empId;
        this.epm_name=epm_name;
        this.Email=email;
        this.emp_phone=emp_phone;
        this.emp_category=emp_category;
        this.job_status=job_status;
        this.emp_discription=emp_discription;
        this.emp_experience=emp_experience;
        this.flag=flag;
	}
    public WorldPopulation(String empId,String epm_name,String email,String emp_phone,String emp_category,String job_status
            ,String emp_discription,String emp_experience,String Rating,String flag) {
        this.empId=empId;
        this.epm_name=epm_name;
        this.Email=email;
        this.emp_phone=emp_phone;
        this.emp_category=emp_category;
        this.job_status=job_status;
        this.emp_discription=emp_discription;
        this.emp_experience=emp_experience;
        this.rating=Rating;
        this.flag=flag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStr_EmpObjid() {
        return str_EmpObjid;
    }

    public void setStr_EmpObjid(String str_EmpObjid) {
        this.str_EmpObjid = str_EmpObjid;
    }

    public String getNewRatingCounter() {
        return newRatingCounter;
    }

    public void setNewRatingCounter(String newRatingCounter) {
        this.newRatingCounter = newRatingCounter;
    }


    public String getStrEmpObjid() {
        return strEmpObjid;
    }

    public void setStrEmpObjid(String strEmpObjid) {
        this.strEmpObjid = strEmpObjid;
    }


    public String getRatingCounter() {
        return ratingCounter;
    }

    public void setRatingCounter(String ratingCounter) {
        this.ratingCounter = ratingCounter;
    }


    public String getStrReasonJobCancel() {
        return strReasonJobCancel;
    }

    public void setStrReasonJobCancel(String strReasonJobCancel) {
        this.strReasonJobCancel = strReasonJobCancel;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getStrUserName() {
        return strUserName;
    }

    public void setStrUserName(String strUserName) {
        this.strUserName = strUserName;
    }


    public String getEmp_discription() {
        return emp_discription;
    }

    public void setEmp_discription(String emp_discription) {
        this.emp_discription = emp_discription;
    }


    public String getJob_Discription() {
        return job_Discription;
    }

    public void setJob_Discription(String job_Discription) {
        this.job_Discription = job_Discription;
    }


    public String getJob_status() {
        return job_status;
    }

    public void setJob_status(String job_status) {
        this.job_status = job_status;
    }


    public String getSingleEmpId() {
        return singleEmpId;
    }

    public void setSingleEmpId(String singleEmpId) {
        this.singleEmpId = singleEmpId;
    }


    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;

    }


    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmp_category() {
        return emp_category;
    }

    public void setEmp_category(String emp_category) {
        this.emp_category = emp_category;
    }

    public String getEpm_name() {
        return epm_name;
    }

    public void setEpm_name(String epm_name) {
        this.epm_name = epm_name;
    }


    public String getEmp_address() {
        return emp_address;
    }

    public void setEmp_address(String emp_address) {
        this.emp_address = emp_address;
    }


    public String getEmp_phone() {
        return emp_phone;
    }

    public void setEmp_phone(String emp_phone) {
        this.emp_phone = emp_phone;
    }


    public String getPopulation() {
        return population;
    }

    public void setPopulation(String phone) {
        this.population = phone;
    }

    public String getEmp_experience() {
        return emp_experience;
    }

    public void setEmp_experience(String emp_experience) {
        this.emp_experience = emp_experience;
    }

    public String getEmp_photo() {
        return emp_photo;
    }

    public void setEmp_photo(String emp_photo) {
        this.emp_photo = emp_photo;
    }


    //public String getRank() {
    //	return rank;
    //}

//	public void setRank(String username) {
//		this.rank = username;
//	}

    public String getCountry() {
        return Email;
    }

    public void setCountry(String email) {
        this.Email = email;
    }


    public String getFlag() {
        return flag;
    }

    public void setFlag(String photo) {
        this.flag = photo;
    }

    private  String name;

    private  String phone;
    private  String address;
    private  String image;
    private  String user_type;
    private  String category;
    private  String experience;
    private  String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}