package employeesList;

/**
 * Created by Awais on 9/4/2015.
 */
public class Constants {

    private String id;
    private String emp_id;
    private String emp_name;
    private String emp_phone;
    private  String user_id;
    private  String user_name;
    private  String user_address;
    private  String user_phone;
    private  String job_status;
    private  String job_description;
    private  String rating;
    private  String emp_image;


    public Constants(String id, String emp_id, String emp_name, String emp_phone, String user_id, String user_name, String user_address, String user_phone, String job_status, String job_description, String rating, String emp_image) {
        this.id = id;
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.emp_phone = emp_phone;
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_address = user_address;
        this.user_phone = user_phone;
        this.job_status = job_status;
        this.job_description = job_description;
        this.rating = rating;
        this.emp_image = emp_image;
    }

    public String getEmp_image() {
        return emp_image;
    }

    public void setEmp_image(String emp_image) {
        this.emp_image = emp_image;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_phone() {
        return emp_phone;
    }

    public void setEmp_phone(String emp_phone) {
        this.emp_phone = emp_phone;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_address() {
        return user_address;
    }

    public void setUser_address(String user_address) {
        this.user_address = user_address;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(String user_phone) {
        this.user_phone = user_phone;
    }

    public String getJob_status() {
        return job_status;
    }

    public void setJob_status(String job_status) {
        this.job_status = job_status;
    }

    public String getJob_description() {
        return job_description;
    }

    public void setJob_description(String job_description) {
        this.job_description = job_description;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }



    private String name;
    private String email;
    private  String phone;
    private  String address;
    private  String image;
    private  String user_type;
    private  String category;
    private  String experience;
    private  String description;
//    private  String rating;

    public Constants(String id, String name, String email, String phone, String address, String image, String user_type, String category, String experience, String description, String rating) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.image = image;
        this.user_type = user_type;
        this.category = category;
        this.experience = experience;
        this.description = description;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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


