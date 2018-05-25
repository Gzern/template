package com.example.template;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ComponentScan("FileUploadController.class")
@SpringBootTest(classes = TemplateApplication.class)
public class TemplateApplicationTests {

    @Test
    public void contextLoads() throws Exception {
        System.in.read();
    }

}
