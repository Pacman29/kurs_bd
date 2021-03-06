package ru.kurs_db.Controllers;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.FileMetadata;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import ru.kurs_db.Controllers.Errors.ErrorAccessException;
import ru.kurs_db.Controllers.Responses.SuccessDictionaryManageResponse;
import ru.kurs_db.Controllers.Views.*;
import ru.kurs_db.Controllers.Responses.Response;
import ru.kurs_db.JdbcDAO.Models.*;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by pacman29 on 20.05.17.
 */
@CrossOrigin(origins = "https://kursbd.herokuapp.com")
@RestController
@RequestMapping("/managedictionary")
//TODO:: сделать проверку на пользователя везде
//TODO:: проверка на вставку с файлами
public class DictionaryManageController extends InferiorController {
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

    @RequestMapping(value = "/createword", method = RequestMethod.POST)
    public ResponseEntity<Response> createword(@RequestParam("word") final String word,
                                               @RequestParam("dialect") final String dialect,
                                               @RequestParam("slang") final String slang,
                                               @RequestParam("description") final String description,
                                               @RequestParam("file") MultipartFile file, HttpSession httpSession) throws IOException, DbxException {
        if (!(Boolean) httpSession.getAttribute("isModerator")) {
            throw new ErrorAccessException();
        }
        FileMetadata savefile = filestorage.savefile(file);
        Objfile created_file = this.jdbcJdbcObjfilesDAO.create("/"+savefile.getName());
        Word created_word = this.jdbcWordsDAO.create(word, slang, dialect,
                created_file.getId(), description);

        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDictionaryManageResponse(
                (String) httpSession.getAttribute("username")));
    }

    @RequestMapping(value = "/createdialect", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> createdialect(@RequestBody final CreateDialectView view, HttpSession httpSession) throws IOException, DbxException {
        if (!(Boolean) httpSession.getAttribute("isModerator")) {
            throw new ErrorAccessException();
        }
        Dialect created_dialect = this.jdbcDialectsDAO.create(view.getDialect(), view.getLanguage(), view.getdescription());
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDictionaryManageResponse(
                (String) httpSession.getAttribute("username")));
    }

    @RequestMapping(value = "/createslang", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> createslang(@RequestBody final CreateSlangView view, HttpSession httpSession) throws IOException, DbxException {
        if (!(Boolean) httpSession.getAttribute("isModerator")) {
            throw new ErrorAccessException();
        }
        Slang created_slang = this.jdbcSlangsDAO.create(view.getSlang(), view.getdescription());
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDictionaryManageResponse(
                (String) httpSession.getAttribute("username")));
    }

    @RequestMapping(value = "/createsymbol", method = RequestMethod.POST)
    public ResponseEntity<Response> createsymbol(@RequestParam("symbol") final String symbol,
                                                 @RequestParam("dialect") final String dialect,
                                                 @RequestParam("description") final String description,
                                                 @RequestParam("file") MultipartFile file, HttpSession httpSession) throws IOException, DbxException {
        if (!(Boolean) httpSession.getAttribute("isModerator")) {
            throw new ErrorAccessException();
        }
        FileMetadata savefile = filestorage.savefile(file);
        Objfile created_file = this.jdbcJdbcObjfilesDAO.create("/"+savefile.getName());
        Symbol created_symbol = this.jdbcSymbolsDAO.create(symbol,
                dialect,
                created_file.getId(),
                description);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDictionaryManageResponse(
                (String) httpSession.getAttribute("username")));
    }

    @RequestMapping(value = "/createlanguage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> createlanguage(@RequestBody final CreateLanguageView view, HttpSession httpSession) throws IOException, DbxException {
        if (!(Boolean) httpSession.getAttribute("isModerator")) {
            throw new ErrorAccessException();
        }
        Language created_language = this.jdbcLanguageDAO.create(view.getName(), view.getdescription());
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDictionaryManageResponse(
                (String) httpSession.getAttribute("username")));
    }

    @RequestMapping(value = "/changeword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> changeword(@RequestBody final ChangeWordView view, HttpSession httpSession) throws IOException, DbxException {
        if (!(Boolean) httpSession.getAttribute("isModerator")) {
            throw new ErrorAccessException();
        }
        Word changed_word = this.jdbcWordsDAO.change(view.getWord_id(), view.getNew_slang(), view.getNew_dialect(), view.getNew_description());
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDictionaryManageResponse(
                (String) httpSession.getAttribute("username")));
    }

    @RequestMapping(value = "/changedialect", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> changedialect(@RequestBody final ChangeDialectView view, HttpSession httpSession) throws IOException, DbxException {
        if (!(Boolean) httpSession.getAttribute("isModerator")) {
            throw new ErrorAccessException();
        }
        Dialect changed_dialect = this.jdbcDialectsDAO.change(view.getDialect(), view.getNew_dialect(), view.getNew_language(), view.getNew_description());
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDictionaryManageResponse(
                (String) httpSession.getAttribute("username")));
    }

    @RequestMapping(value = "/changeslang", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> changeslang(@RequestBody final ChangeSlangView view, HttpSession httpSession) throws IOException, DbxException {
        if (!(Boolean) httpSession.getAttribute("isModerator")) {
            throw new ErrorAccessException();
        }
        Slang changed_slang = this.jdbcSlangsDAO.change(view.getSlang(), view.getNew_Slang(), view.getdescription());
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDictionaryManageResponse(
                (String) httpSession.getAttribute("username")));
    }

    @RequestMapping(value = "/changesymbol", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> changesymbol(@RequestBody final ChangeSymbolView view, HttpSession httpSession) throws IOException, DbxException {
        if (!(Boolean) httpSession.getAttribute("isModerator")) {
            throw new ErrorAccessException();
        }
        Symbol changed_symbol = this.jdbcSymbolsDAO.change(view.getSymbol(), view.getDialect(), view.getSymbol_new(), view.getDialect_new(), view.getdescription());
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDictionaryManageResponse(
                (String) httpSession.getAttribute("username")));
    }

    @RequestMapping(value = "/changelanguage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> changelanguage(@RequestBody final ChangeLanguageView view, HttpSession httpSession) throws IOException, DbxException {
        if (!(Boolean) httpSession.getAttribute("isModerator")) {
            throw new ErrorAccessException();
        }
        Language changed_language = this.jdbcLanguageDAO.change(view.getName(), view.getdescription());
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDictionaryManageResponse(
                (String) httpSession.getAttribute("username")));
    }

    @RequestMapping(value = "/deleteword", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> deleteword(@RequestBody final DeleteWordView view, HttpSession httpSession) throws IOException, DbxException {
        if (!(Boolean) httpSession.getAttribute("isModerator")) {
            throw new ErrorAccessException();
        }
        Word deleted_word = this.jdbcWordsDAO.delete(view.getId());
        Objfile tmp = this.jdbcJdbcObjfilesDAO.delete(deleted_word.getFile_id());
        this.filestorage.deletefile(tmp.getFilename());
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDictionaryManageResponse(
                (String) httpSession.getAttribute("username")));
    }

    @RequestMapping(value = "/deletedialect", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> deletedialect(@RequestBody final DeleteDialectView view, HttpSession httpSession) throws IOException, DbxException {
        if (!(Boolean) httpSession.getAttribute("isModerator")) {
            throw new ErrorAccessException();
        }
        Dialect deleted_dialect = this.jdbcDialectsDAO.delete(view.getDialect());
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDictionaryManageResponse(
                (String) httpSession.getAttribute("username")));
    }

    @RequestMapping(value = "/deleteslang", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> deleteslang(@RequestBody final DeleteSlangView view, HttpSession httpSession) throws IOException, DbxException {
        if (!(Boolean) httpSession.getAttribute("isModerator")) {
            throw new ErrorAccessException();
        }
        Slang deleted_slang = this.jdbcSlangsDAO.delete(view.getSlang());
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDictionaryManageResponse(
                (String) httpSession.getAttribute("username")));
    }

    @RequestMapping(value = "/deletesymbol", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> deletesymbol(@RequestBody final DeleteSymbolView view, HttpSession httpSession) throws IOException, DbxException {
        if (!(Boolean) httpSession.getAttribute("isModerator")) {
            throw new ErrorAccessException();
        }
        Symbol deleted_symbol = this.jdbcSymbolsDAO.delete(view.getSymbol(), view.getDialect());
        Objfile tmp = this.jdbcJdbcObjfilesDAO.delete(deleted_symbol.getFile_id());
        this.filestorage.deletefile(tmp.getFilename());
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDictionaryManageResponse(
                (String) httpSession.getAttribute("username")));
    }

    @RequestMapping(value = "/deletelanguage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Response> deletelanguage(@RequestBody final DeleteLanguageView view, HttpSession httpSession) throws IOException, DbxException {
        if (!(Boolean) httpSession.getAttribute("isModerator")) {
            throw new ErrorAccessException();
        }
        Language deleted_language = this.jdbcLanguageDAO.delete(view.getName());
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessDictionaryManageResponse(
                (String) httpSession.getAttribute("username")));
    }
}
