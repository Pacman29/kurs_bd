package ru.kurs_db.Controllers;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.FileMetadata;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import ru.kurs_db.Controllers.Responses.SuccessDictionaryManageResponse;
import ru.kurs_db.Controllers.Views.CreateDialectView;
import ru.kurs_db.Controllers.Views.CreateSlangView;
import ru.kurs_db.Controllers.Views.CreateSymbolView;
import ru.kurs_db.Controllers.Views.CreateWordView;
import ru.kurs_db.Controllers.Responses.Response;
import ru.kurs_db.JdbcDAO.Models.*;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpSession;
import java.io.IOException;

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
    public ResponseEntity<Response> createword (@RequestBody final CreateWordView view, HttpSession httpSession) throws IOException, DbxException {
        FileMetadata savefile = filestorage.savefile(view.getFile());
        Objfile created_file = this.jdbcObjfilesDAO.create(savefile.getName());
        Word created_word = this.jdbcWordsDAO.create(view.getWord(),view.getSlang(),view.getDialect(),
                created_file.getId(),view.getDiscription());
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDictionaryManageResponse(
                (String) httpSession.getAttribute("username")));
    }

    @RequestMapping(value = "/createdialect", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public ResponseEntity<Response> createdialect (@RequestBody final CreateDialectView view, HttpSession httpSession) throws IOException, DbxException {
        Dialect created_dialect = this.jdbcDialectsDAO.create(view.getDialect(),view.getLanguage(),view.getDiscription());
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDictionaryManageResponse(
                (String) httpSession.getAttribute("username")));
    }

    @RequestMapping(value = "/createslang", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public ResponseEntity<Response> createslang (@RequestBody final CreateSlangView view, HttpSession httpSession) throws IOException, DbxException {
        Slang created_slang = this.jdbcSlangsDAO.create(view.getSlang(),view.getDiscription());
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDictionaryManageResponse(
                (String) httpSession.getAttribute("username")));
    }

    @RequestMapping(value = "/createsymbol", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public ResponseEntity<Response> createsymbol (@RequestBody final CreateSymbolView view, HttpSession httpSession) throws IOException, DbxException {
        FileMetadata savefile = filestorage.savefile(view.getFile());
        Objfile created_file = this.jdbcObjfilesDAO.create(savefile.getName());
        Symbol created_symbol = this.jdbcSymbolsDAO.create(view.getSymbol(),
                view.getDialect(),
                created_file.getId(),
                view.getDiscription());
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDictionaryManageResponse(
                (String) httpSession.getAttribute("username")));
    }


}
