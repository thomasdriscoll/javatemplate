package com.thomasdriscoll.javatemplate.controller;

import com.thomasdriscoll.javatemplate.lib.responses.DriscollResponse;
import com.thomasdriscoll.javatemplate.service.TemplateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TemplateController {

    private TemplateService templateService;

    public TemplateController(TemplateService templateService){
        this.templateService = templateService;
    }

    @GetMapping("/{name}")
    private ResponseEntity<DriscollResponse<String>> dummyFunction(@PathVariable String name){
        return ResponseEntity.ok().body(new DriscollResponse<>(HttpStatus.OK.value(), templateService.dummyFunction(name)));
    }
}
