package com.example.jsf;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class HelloWorld implements Serializable {
    private String name = "";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /** Action method to navigate to page2.xhtml */
    public String goToPage2() {
        // return logical view ID with redirect; Spring+JSF makes view by name
        return "page2?faces-redirect=true";
    }

    /** Action method to navigate back to index.xhtml */
    public String goBack() {
        return "index?faces-redirect=true";
    }
}
