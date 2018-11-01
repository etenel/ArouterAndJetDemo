package com.eternel.jet.demo.model.bean;

import javax.inject.Inject;

public class Student {
    private String name;
    private String sex;

    public Student() {
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex == null ? "" : sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
