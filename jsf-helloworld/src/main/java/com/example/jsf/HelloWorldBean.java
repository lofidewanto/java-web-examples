package com.example.jsf;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

@Component
@SessionScope
public class HelloWorldBean implements Serializable {

    private String name = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String goToPage2() {
        return "page2?faces-redirect=true";
    }

    public String goBack() {
        return "index?faces-redirect=true";
    }
}
