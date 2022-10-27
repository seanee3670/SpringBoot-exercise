package com.springboot.api.dto;

public class MemberDto {
    private String name;
    private String email;
    private String organization;

    MemberDto() {
    }

    MemberDto(String name, String email, String organization) {
        this.name = name;
        this.email = email;
        this.organization = organization;
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

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s", this.name, this.email, this.organization);
    }
}


