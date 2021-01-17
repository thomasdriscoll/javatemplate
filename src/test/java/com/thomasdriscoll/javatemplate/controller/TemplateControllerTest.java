package com.thomasdriscoll.javatemplate.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thomasdriscoll.javatemplate.lib.exceptions.DriscollException;
import com.thomasdriscoll.javatemplate.lib.exceptions.TemplateExceptionEnums;
import com.thomasdriscoll.javatemplate.lib.responses.DriscollResponse;
import com.thomasdriscoll.javatemplate.service.TemplateService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TemplateController.class)
public class TemplateControllerTest {
    @MockBean
    private TemplateService templateService;

    @Autowired
    private MockMvc mockMvc;

    //Give each endpoint its own class of testing functions, so its easy to see what works and what doesn't
    @Nested
    @DisplayName("dummyFunction method tests")
    class dummyFunction_Tests{
        //Variables used for testing this function specifically
        private final String name = "Thomas";
        private String badName = "Thummus";
        private final String nameResponse = "My name is Thomas";

        @Test
        public void givenName_whenGetName_thenReturnResponseEntity() throws Exception {
            // Declare expected response and other variables used only in this test
            // Note: ObjectMapper here is mapping Java objects to JSON objects for you
            String expected = new ObjectMapper().writeValueAsString(new DriscollResponse<>(HttpStatus.OK.value(), nameResponse));

            //Mock what needs to be mocked
            when(templateService.dummyFunction(name)).thenReturn(nameResponse);

            //Do test
            MvcResult result = mockMvc.perform(get(String.format("/%s", name))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

            //Assert if test worked
            String actual = result.getResponse().getContentAsString();
            assertEquals(expected, actual);
        }
        @Test
        public void givenInvalidName_whenGetName_thenReturnException() throws Exception, DriscollException {
            //Variables local to test
            DriscollException exception = new DriscollException(TemplateExceptionEnums.TESTING_EXCEPTIONS.getStatus(), TemplateExceptionEnums.TESTING_EXCEPTIONS.getMessage());
            String expected = new ObjectMapper().writeValueAsString(new DriscollResponse<>(exception.getStatus().value(), exception.getMessage()));

            //Mock what needs to be mocked
            when(templateService.dummyFunction(badName)).thenThrow(exception);

            //Do test
            MvcResult result = mockMvc.perform(get(String.format("/%s", badName))
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            //Assert if test worked
            // assertEquals(expected, actual)
            assertEquals(expected, result.getResponse().getContentAsString());
        }
    }
}