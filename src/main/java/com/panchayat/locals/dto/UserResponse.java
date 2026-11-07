package com.panchayat.locals.dto;

import com.panchayat.locals.entity.Role;
import com.panchayat.locals.entity.User;

public class UserResponse {

    private Long id;
    private String name;
    private String mobile;
    private Role role;

    public UserResponse() {
    }

    public UserResponse(Long id, String name, String mobile, Role role) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.role = role;
    }

    public static UserResponse fromEntity(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getMobile(),
                user.getRole()
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

