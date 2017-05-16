package ru.kurs_db.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kurs_db.Controllers.Errors.ErrorAccessException;
import ru.kurs_db.Controllers.Errors.ErrorChangeException;
import ru.kurs_db.Controllers.Responses.SuccessChangeRoleResponse;
import ru.kurs_db.Controllers.Views.ChangeRoleView;
import ru.kurs_db.Controllers.Errors.ErrorAccessException;
import ru.kurs_db.DAO.RolesDAO;
import ru.kurs_db.JdbcDAO.Models.UserRole;
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
    public ResponseEntity<Response> changerole (@RequestBody final ChangeRoleView view, HttpSession httpSession)
            throws ErrorAccessException, ErrorChangeException{

        if(!(Boolean) httpSession.getAttribute("isAdmin")){
            throw new ErrorAccessException();
        }

        final String username = view.getUsername();

        if(username == httpSession.getAttribute("username")){
            throw new ErrorAccessException();
        }

        final UserRole.role_type newrole = UserRole.role_type.valueOf(view.getNewrole());
        if(newrole == UserRole.role_type.ADMIN) throw new ErrorChangeException();

        final UserRole ur = this.jdbcRolesDAO.changeRole(username,newrole);

        return ResponseEntity.status(HttpStatus.OK).body( new SuccessChangeRoleResponse(ur.getUsername(),ur.getType().name()));
    }
}
