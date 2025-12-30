package Model;

public class Receptionist {
    private String fname;
    private String lname;
    private String gender;
    private String mobile;
    private String city;
    private String email;
    private String age;
    private String address;
    private String registrationDate;

    public Receptionist() {
    }

    public Receptionist(String fname, String lname, String gender, String mobile,
            String city, String email, String age, String address,
            String registrationDate) {
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.mobile = mobile;
        this.city = city;
        this.email = email;
        this.age = age;
        this.address = address;
        this.registrationDate = registrationDate;
    }

    // Getters and Setters
    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
}
