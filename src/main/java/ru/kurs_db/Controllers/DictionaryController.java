package ru.kurs_db.Controllers;

import com.dropbox.core.DbxException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kurs_db.Controllers.Responses.Response;
import ru.kurs_db.Controllers.Responses.SuccessDictionaryManageResponse;
import ru.kurs_db.Controllers.Views.CreateDialectView;
import ru.kurs_db.Controllers.Views.SearchView;
import ru.kurs_db.JdbcDAO.Models.Dialect;
import ru.kurs_db.JdbcDAO.Models.Word;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by pacman29 on 04.06.17.
 */
@RestController
@RequestMapping("/dictionary")
public class DictionaryController extends InferiorController{
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public ResponseEntity<ArrayList<Word>> search (@RequestBody final SearchView view, HttpSession httpSession) throws IOException, DbxException {
        ArrayList<Word> results = this.jdbcWordsDAO.search(view.getWord(),view.getDialect(),view.getSlang());
        return ResponseEntity.status(HttpStatus.OK).body(results);
    }
}
