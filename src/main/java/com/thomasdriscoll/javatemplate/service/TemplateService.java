package com.thomasdriscoll.javatemplate.service;

import com.thomasdriscoll.javatemplate.lib.exceptions.DriscollException;
import com.thomasdriscoll.javatemplate.lib.exceptions.TemplateExceptionEnums;
import org.springframework.stereotype.Service;

@Service
public class TemplateService {
    public TemplateService(){}

    public String dummyFunction(String name) throws DriscollException {
        if(name.equals("Thummus")){
            throw new DriscollException(TemplateExceptionEnums.TESTING_EXCEPTIONS.getStatus(), TemplateExceptionEnums.TESTING_EXCEPTIONS.getMessage());
        }
        return "My name is " + name;
    }
}
