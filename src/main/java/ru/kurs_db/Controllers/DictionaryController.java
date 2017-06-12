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
import java.util.Iterator;
import java.util.List;

/**
 * Created by pacman29 on 04.06.17.
 */
@CrossOrigin(origins = "https://kursbd.herokuapp.com")
@RestController
@RequestMapping("/dictionary")
public class DictionaryController extends InferiorController {
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WordWithURL>> search(@RequestBody final SearchView view, HttpSession httpSession) throws IOException, DbxException {
        List<Word> results = this.jdbcWordsDAO.search(view.getWord(), view.getDialect(), view.getSlang());
        List<WordWithURL> res = new ArrayList<>();
        for (Iterator iter = results.iterator(); iter.hasNext();){
            Word tmp = (Word) iter.next();
            String url = this.filestorage.getfilelink(tmp.getFile_name());
            res.add(new WordWithURL(tmp,url));
        }
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @RequestMapping(value = "/wordinsymbol", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SymbolWithURL>> converter(@RequestBody final ConverterView view, HttpSession httpSession) throws IOException, DbxException {
        List<SymbolWithURL> results = this.jdbcSymbolsDAO.convertToSymbol(view.getWord(), view.getDialect());
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

    @RequestMapping(value = "/languages", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Language>> languages(HttpSession httpSession) throws IOException, DbxException {
        List<Language> results = this.jdbcLanguageDAO.get_all();
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

    @RequestMapping(value = "/dialects", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Dialect>> dialects(HttpSession httpSession) throws IOException, DbxException {
        List<Dialect> results = this.jdbcDialectsDAO.getAllDialects();
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

    @RequestMapping(value = "/slangs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Slang>> slangs(HttpSession httpSession) throws IOException, DbxException {
        List<Slang> results = this.jdbcSlangsDAO.getAllSlangs();
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }

    @RequestMapping(value = "/words", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WordWithURL>> words(HttpSession httpSession) throws IOException, DbxException {
        List<Word> results = this.jdbcWordsDAO.getAllWords();
        List<WordWithURL> res = new ArrayList<>();
        for (Iterator iter = results.iterator(); iter.hasNext();){
            Word tmp = (Word) iter.next();
            String url = this.filestorage.getfilelink(tmp.getFile_name());
            res.add(new WordWithURL(tmp,url));
        }
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @RequestMapping(value = "/symbols", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SymbolWithURL>> symbols(HttpSession httpSession) throws IOException, DbxException {
        List<SymbolWithURL> results = this.jdbcSymbolsDAO.getAllSymbols();
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }
}
