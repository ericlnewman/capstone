package com.ericnewman.sfaapp.dto;

public class UserDTO {

    private Long id;
    private String password;
    private String email;
    private int concernId;
    private String concerns;

    private String firstName;
    private String lastName;
    private int age;
    private String parentOrGuardian;
    private String gender;
    private int numberOfChildren;
    private String personOfConcernName;
    private int ageOfPersonOfConcern;
    private String userState;
    private String userTown;
    private String userZip;
    private String userStreet;
    private String userCountry;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String phone;

    public UserDTO() {
    }

    public UserDTO(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getConcernId() {
        return concernId;
    }

    public void setConcernId(int concernId) {
        this.concernId = concernId;
    }

    public String getConcerns() {
        return concerns;
    }

    public void setConcerns(String concerns) {
        this.concerns = concerns;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getUserTown() {
        return userTown;
    }

    public void setUserTown(String userTown) {
        this.userTown = userTown;
    }

    public String getUserZip() {
        return userZip;
    }

    public void setUserZip(String userZip) {
        this.userZip = userZip;
    }

    public String getUserStreet() {
        return userStreet;
    }

    public void setUserStreet(String userStreet) {
        this.userStreet = userStreet;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public void setUserCountry(String userCountry) {
        this.userCountry = userCountry;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getParentOrGuardian() {
        return parentOrGuardian;
    }

    public void setParentOrGuardian(String parentOrGuardian) {
        this.parentOrGuardian = parentOrGuardian;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public String getPersonOfConcernName() {
        return personOfConcernName;
    }

    public void setPersonOfConcernName(String personOfConcernName) {
        this.personOfConcernName = personOfConcernName;
    }

    public int getAgeOfPersonOfConcern() {
        return ageOfPersonOfConcern;
    }

    public void setAgeOfPersonOfConcern(int ageOfPersonOfConcern) {
        this.ageOfPersonOfConcern = ageOfPersonOfConcern;
    }
    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", concernId=" + concernId +
                ", concerns='" + concerns + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age + '\'' +
                ", phone=" + phone + '\'' +
                ", parentOrGuardian='" + parentOrGuardian + '\'' +
                ", gender='" + gender + '\'' +
                ", numberOfChildren=" + numberOfChildren +
                ", personOfConcernName='" + personOfConcernName + '\'' +
                ", ageOfPersonOfConcern=" + ageOfPersonOfConcern +
                ", userState='" + userState + '\'' +
                ", userTown='" + userTown + '\'' +
                ", userZip='" + userZip + '\'' +
                ", userStreet='" + userStreet + '\'' +
                ", userCountry='" + userCountry + '\'' +
                '}';
    }
    public UserDTO(Long id, String phone, String password, String email, int concernId, String concerns, String firstName, String lastName, int age, String parentOrGuardian, String gender, int numberOfChildren, String personOfConcernName, int ageOfPersonOfConcern, String userState, String userTown, String userZip, String userStreet, String userCountry) {
        this.id = id;
        this.password = password;
        this.email = email;
        this.concernId = concernId;
        this.concerns = concerns;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.parentOrGuardian = parentOrGuardian;
        this.gender = gender;
        this.numberOfChildren = numberOfChildren;
        this.personOfConcernName = personOfConcernName;
        this.ageOfPersonOfConcern = ageOfPersonOfConcern;
        this.userState = userState;
        this.userTown = userTown;
        this.userZip = userZip;
        this.userStreet = userStreet;
        this.userCountry = userCountry;
        this.password = phone;
    }
}
