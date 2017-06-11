package ru.kurs_db.Controllers;

import org.springframework.web.bind.annotation.*;
import ru.kurs_db.Controllers.Errors.ErrorChangeException;
import ru.kurs_db.JdbcDAO.Models.UserRole;
import ru.kurs_db.JdbcDAO.Models.User;
import ru.kurs_db.Controllers.Responses.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import ru.kurs_db.Controllers.Responses.SuccessUserResponse;
import ru.kurs_db.Controllers.Views.UserLoginView;
import ru.kurs_db.Controllers.Views.UserRegisterView;
import ru.kurs_db.Controllers.Views.UserPublicView;
import ru.kurs_db.Controllers.Views.UserUpdateView;

import javax.management.InvalidAttributeValueException;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Created by lieroz on 6.05.17.
 */
@CrossOrigin(origins = "https://kursbd.herokuapp.com")
@RestController
@RequestMapping("/user")
public class UserController extends InferiorController {
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Response> createUser(@RequestBody final UserRegisterView view, HttpSession httpSession) {
        final String hashedPassword = passwordEncoder().encode(view.getPassword());
        final User user = jdbcUserDAO.insert(view.getUsername(), view.getEmail(), hashedPassword);
        httpSession.setAttribute("userId", user.getId());
        httpSession.setAttribute("username", user.getUsername());

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new SuccessUserResponse(user.getId(), messageSource.getMessage("messages.created", null, Locale.ENGLISH), null));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Response> loginUser(@RequestBody final UserLoginView view, HttpSession httpSession) throws InvalidAttributeValueException {
        final User user = jdbcUserDAO.findByUsername(view.getUsername());
        if (!passwordEncoder().matches(view.getPassword(), user.getHashedPassword())) {
            throw new InvalidAttributeValueException();
        }
        httpSession.setAttribute("userId", user.getId());
        httpSession.setAttribute("username", user.getUsername());
        httpSession.setAttribute("isAdmin",jdbcRolesDAO.getRole(user.getUsername()) == UserRole.role_type.ADMIN);
        return ResponseEntity.status(HttpStatus.OK).body(
                new SuccessUserResponse(user.getId(), messageSource.getMessage("messages.ok", null, Locale.ENGLISH), null));
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Response> logoutUser(HttpSession httpSession) throws InvalidAttributeValueException {
        final Integer userId = (Integer) httpSession.getAttribute("userId");
        if (userId == null) {
            throw new InvalidAttributeValueException();
        }
        httpSession.invalidate();
        return ResponseEntity.status(HttpStatus.OK).body(
                new SuccessUserResponse(userId, messageSource.getMessage("messages.ok", null, Locale.ENGLISH), null));
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Response> updateUser(@RequestBody final UserUpdateView view, HttpSession httpSession) throws InvalidAttributeValueException {
        final Integer userId = (Integer) httpSession.getAttribute("userId");
        if (userId == null) {
            throw new InvalidAttributeValueException();
        }
        final String hashedPassword = view.getPassword() == null ? null : passwordEncoder().encode(view.getPassword());
        final User user = jdbcUserDAO.update(userId, view.getUsername(), view.getEmail(), hashedPassword);
        httpSession.setAttribute("username", user.getUsername());
        return ResponseEntity.status(HttpStatus.OK).body(
                new SuccessUserResponse(userId, messageSource.getMessage("messages.ok", null, Locale.ENGLISH),
                        new UserPublicView(user)));
    }

    @RequestMapping(value = "/data", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Response> viewUserData(HttpSession httpSession) throws InvalidAttributeValueException {
        final Integer userId = (Integer) httpSession.getAttribute("userId");
        if (userId == null) {
            throw new InvalidAttributeValueException();
        }
        final User user = jdbcUserDAO.findById(userId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new SuccessUserResponse(userId, messageSource.getMessage("messages.ok", null, Locale.ENGLISH),
                        new UserPublicView(user)));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Response> deleteUser(HttpSession httpSession) throws InvalidAttributeValueException, ErrorChangeException {
        final Integer userId = (Integer) httpSession.getAttribute("userId");
        if((Boolean) httpSession.getAttribute("isAdmin")){
            throw new ErrorChangeException();
        }
        if (userId == null) {
            throw new InvalidAttributeValueException();
        }
        jdbcUserDAO.delete(userId);
        httpSession.invalidate();
        return ResponseEntity.status(HttpStatus.OK).body(
                new SuccessUserResponse(userId, messageSource.getMessage("messages.ok", null, Locale.ENGLISH), null));
    }
}
