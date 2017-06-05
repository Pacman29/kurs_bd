package ru.kurs_db.Controllers;

import com.dropbox.core.DbxException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kurs_db.Controllers.Responses.Response;
import ru.kurs_db.Controllers.Responses.SuccessDictionaryManageResponse;
import ru.kurs_db.Controllers.Views.ConverterView;
import ru.kurs_db.Controllers.Views.CreateDialectView;
import ru.kurs_db.Controllers.Views.SearchView;
import ru.kurs_db.JdbcDAO.Models.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pacman29 on 04.06.17.
 */
@RestController
@RequestMapping("/dictionary")
public class DictionaryController extends InferiorController{
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public ResponseEntity<List<Word>> search (@RequestBody final SearchView view, HttpSession httpSession) throws IOException, DbxException {
        List<Word> results = this.jdbcWordsDAO.search(view.getWord(),view.getDialect(),view.getSlang());
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

    @RequestMapping(value = "/wordinsymbol", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public ResponseEntity<List<Symbol>> converter (@RequestBody final ConverterView view, HttpSession httpSession) throws IOException, DbxException {
        List<Symbol> results = this.jdbcSymbolsDAO.convertToSymbol(view.getWord(),view.getDialect());
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

    @RequestMapping(value = "/languages", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public ResponseEntity<List<Language>> languages (HttpSession httpSession) throws IOException, DbxException {
        List<Language> results = this.jdbcLanguageDAO.get_all();
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

    @RequestMapping(value = "/dialects", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public ResponseEntity<List<Dialect>> dialects (HttpSession httpSession) throws IOException, DbxException {
        List<Dialect> results = this.jdbcDialectsDAO.getAllDialects();
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

    @RequestMapping(value = "/slangs", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public ResponseEntity<List<Slang>> slangs (HttpSession httpSession) throws IOException, DbxException {
        List<Slang> results = this.jdbcSlangsDAO.getAllSlangs();
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

    @RequestMapping(value = "/words", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public ResponseEntity<List<Word>> words (HttpSession httpSession) throws IOException, DbxException {
        List<Word> results = this.jdbcWordsDAO.getAllWords();
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

    @RequestMapping(value = "/symbols", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public ResponseEntity<List<Symbol>> symbols (HttpSession httpSession) throws IOException, DbxException {
        List<Symbol> results = this.jdbcSymbolsDAO.getAllSymbols();
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }
}
