package com.roplant.sep.model;

import com.roplant.sep.enums.Role;
import jakarta.persistence.*;
import java.time.LocalDate;

@MappedSuperclass
public abstract class BaseUser {

    @Column(nullable = false)
    private String name;

    private String address;

    @Column(name = "phone_no")
    private String phoneNo;

    @Column(unique = true)
    private String cnic;

    private LocalDate startDate;
    private LocalDate endDate;

    @Column(columnDefinition = "TEXT")
    private String aboutMe;

    // Simple Login
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPhoneNo() { return phoneNo; }
    public void setPhoneNo(String phoneNo) { this.phoneNo = phoneNo; }

    public String getCnic() { return cnic; }
    public void setCnic(String cnic) { this.cnic = cnic; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public String getAboutMe() { return aboutMe; }
    public void setAboutMe(String aboutMe) { this.aboutMe = aboutMe; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
}