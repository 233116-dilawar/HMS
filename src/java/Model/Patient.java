package Model;

public class Patient {
    private String fname;
    private String lname;
    private String gender;
    private String city;
    private String email;
    private String age;
    private String address;
    private String date;
    private String mobile;

    public Patient() {
    }

    public Patient(String fname, String lname, String gender, String city, String email, String age, String address,
            String date, String mobile) {
        this.fname = fname;
        this.lname = lname;
        this.gender = gender;
        this.city = city;
        this.email = email;
        this.age = age;
        this.address = address;
        this.date = date;
        this.mobile = mobile;
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
