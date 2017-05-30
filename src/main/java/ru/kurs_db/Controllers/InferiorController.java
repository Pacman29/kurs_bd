package ru.kurs_db.Controllers;

import com.dropbox.core.DbxException;
import com.dropbox.core.v2.files.CreateFolderErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.kurs_db.Controllers.Errors.ErrorAccessException;
import ru.kurs_db.Controllers.Errors.ErrorChangeException;
import ru.kurs_db.DropboxService.DropboxService;
import ru.kurs_db.FileStorage.FileStorage;
import ru.kurs_db.JdbcDAO.*;
import ru.kurs_db.Controllers.Responses.ErrorResponse;
import ru.kurs_db.Controllers.Responses.Response;

import javax.management.InvalidAttributeValueException;
import java.util.Locale;

/**
 * Created by lieroz on 7.05.17.
 */
@Component
public class InferiorController {
    @Autowired
    protected JdbcUserDAO jdbcUserDAO;

    @Autowired
    protected JdbcRolesDAO jdbcRolesDAO;

    @Autowired
    protected JdbcDialectsDAO jdbcDialectsDAO;

    @Autowired
    protected JdbcLanguageDAO jdbcLanguageDAO;

    @Autowired
    protected JdbcObjfilesDAO jdbcObjfilesDAO;

    @Autowired
    protected JdbcSlangsDAO jdbcSlangsDAO;

    @Autowired
    protected JdbcSymbolsDAO jdbcSymbolsDAO;

    @Autowired
    protected JdbcWordsDAO jdbcWordsDAO;

    @Autowired
    protected FileStorage filestorage = new DropboxService();

    @Autowired
    protected MessageSource messageSource;

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response handleDataAccessException(DataAccessException e) {
        return new ErrorResponse(messageSource.getMessage("messages.bad-request", null, Locale.ENGLISH));
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public Response handleEmptyResultDataAccessException(Exception e) {
        return new ErrorResponse(messageSource.getMessage("messages.forbidden", null, Locale.ENGLISH));
    }

    @ExceptionHandler(ErrorAccessException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public Response handleErrorAccessException(ErrorAccessException e){
        return new ErrorResponse(messageSource.getMessage("messages.forbidden",null,Locale.ENGLISH));
    }

    @ExceptionHandler(ErrorChangeException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public Response handleErrorChangeException(ErrorChangeException e){
        return new ErrorResponse(messageSource.getMessage("messages.forbidden",null,Locale.ENGLISH));
    }

    @ExceptionHandler(InvalidAttributeValueException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public Response handleInvalidAttributeValueException(InvalidAttributeValueException e) {
        return new ErrorResponse(messageSource.getMessage("messages.forbidden", null, Locale.ENGLISH));
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public Response handleDuplicateKeyException(DuplicateKeyException e) {
        return new ErrorResponse(messageSource.getMessage("messages.conflict", null, Locale.ENGLISH));
    }

    @ExceptionHandler(DbxException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Response handleDbxException(DbxException e) {
        return new ErrorResponse(messageSource.getMessage("messages.not-found", null, Locale.ENGLISH));
    }

    @ExceptionHandler(CreateFolderErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response handleCreateFolderErrorException(CreateFolderErrorException e) {
        if (e.errorValue.isPath() && e.errorValue.getPathValue().isConflict()) {
            return new ErrorResponse(messageSource.getMessage("messages.conflict", null, Locale.ENGLISH));
        } else {
            return new ErrorResponse(messageSource.getMessage("messages.not-found", null, Locale.ENGLISH));
        }
    }
}
