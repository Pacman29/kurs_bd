package ru.kurs_db.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kurs_db.Controllers.Errors.ErrorAccessException;
import ru.kurs_db.Controllers.Views.ChangeRoleView;
import ru.kurs_db.Controllers.Errors.ErrorAccessException;
import ru.kurs_db.Responses.Response;

import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Created by pacman29 on 14.05.17.
 */
@RestController
@RequestMapping("/manageusers")
public class UsersManagerController extends InferiorController{

    @RequestMapping(value = "/changerole", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public ResponseEntity<Response> changerole (@RequestBody final ChangeRoleView view, HttpSession httpSession) throws ErrorAccessException{

        if(!(Boolean) httpSession.getAttribute("isAdmin")){
            throw new ErrorAccessException();
        }

        final String username = view.getUsername();
        final String newrole = view.getNewrole();



        return ResponseEntity.status(HttpStatus.OK).body();
    }
}
