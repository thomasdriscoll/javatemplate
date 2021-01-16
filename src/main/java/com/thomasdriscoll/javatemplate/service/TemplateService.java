package com.thomasdriscoll.javatemplate.service;

import org.springframework.stereotype.Service;

@Service
public class TemplateService {
    public TemplateService(){}

    public String dummyFunction(String name){
        return "My name is " + name;
    }
}
