package com.fedormamaevv.SpringProjectTemp;

import javax.persistence.*;

@Entity
@Table(name = "participants")
public class Participant {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "firstName", nullable = false)
    private String firstName;
    @Column(name = "lastName", nullable = false)
    private String lastName;
    @Column(name = "age")
    private Integer age;
    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;
    @Column(name = "emailAddress", nullable = false)
    private String emailAddress;
    @Column(name = "country")
    private String country;
    @Column(name = "region")
    private String region;
    @Column(name = "city")
    private String city;
    @Column(name = "school")
    private String school;
    @Column(name = "grade", nullable = false)
    private Integer grade;

    public Participant() { }
    public Participant(String firstName, String lastName, String phoneNumber, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }
    public Participant(String firstName,
                       String lastName,
                       Integer age,
                       String phoneNumber,
                       String emailAddress,
                       String country,
                       String region,
                       String city,
                       String school,
                       Integer grade) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.country = country;
        this.region = region;
        this.city = city;
        this.school = school;
        this.grade = grade;
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstname) { this.firstName = firstname; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastname) { this.lastName = lastname; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phonenumber) { this.phoneNumber = phonenumber; }

    public String getEmailAddress() { return emailAddress; }
    public void setEmailAddress(String email) { this.emailAddress = email; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getRegion() { return region; }
    public void setRegion(String region) { this.region = region; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getSchool() { return school; }
    public void setSchool(String school) { this.school = school; }

    public Integer getGrade() { return grade; }
    public void setGrade(Integer _class) { this.grade = _class; }

    //public List<String> getSubjects() { return subjects; }
    //public void setSubjects(List<String> subjects) { this.subjects = subjects; }
    //public String getSubject(int index) { return subjects.get(index); }
    //public String setSubject(int index, String subject) { return subject; }


    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", school='" + school + '\'' +
                ", grade=" + grade +
                '}';
    }
}
