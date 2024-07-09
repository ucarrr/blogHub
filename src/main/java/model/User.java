package model;

import model.enums.StatusType;
import model.enums.UserType;

import java.util.List;
import java.util.Set;


public class User {
    private String name;
    private String email;
    private String password;
    private UserType userType;
    private StatusType statusType;
    private String bio;
    private Set<SocialMedia> socialMediaList;
    private List<Blog> blog;


    public User(){
         userType= UserType.STANDARD;
    }

    public User(String email,  String password) {
        this.email = email;
        this.password = password;
        this.statusType=StatusType.WAITING_APPROVAL;
        this.userType= UserType.STANDARD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Set<SocialMedia> getSocialMediaList() {
        return socialMediaList;
    }

    public void setSocialMediaList(Set<SocialMedia> socialMediaList) {
        this.socialMediaList = socialMediaList;
    }

    public List<Blog> getBlog() {
        return blog;
    }

    public void setBlog(List<Blog> blog) {
        this.blog = blog;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                ", statusType=" + statusType +
                '}';
    }
}
