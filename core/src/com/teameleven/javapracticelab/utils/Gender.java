package com.teameleven.javapracticelab.utils;

public enum Gender {
    MALE("남자"), FEMALE("여자");

    final private String gender;

    private Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
