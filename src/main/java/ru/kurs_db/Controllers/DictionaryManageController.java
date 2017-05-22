package ru.kurs_db.Controllers;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import ru.kurs_db.Controllers.Views.CreateWordView;
import ru.kurs_db.Controllers.Responses.Response;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpSession;

/**
 * Created by pacman29 on 20.05.17.
 */
@RestController
@RequestMapping("/managedictionary")
public class DictionaryManageController extends InferiorController{
    @Bean(name = "commonsMultipartResolver")
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver();
    }


    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();

        factory.setMaxFileSize("10MB");
        factory.setMaxRequestSize("10MB");

        return factory.createMultipartConfig();
    }

    @RequestMapping(value = "/createword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public ResponseEntity<Response> createword (@RequestBody final CreateWordView view, HttpSession httpSession){

    }
}
