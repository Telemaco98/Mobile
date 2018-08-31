package com.android.ohara.telemaco.buniness;

import android.content.Context;

import com.android.ohara.telemaco.Messages;
import com.android.ohara.telemaco.exceptions.UserAlreadyExistsException;
import com.android.ohara.telemaco.exceptions.UserNotExistsException;
import com.android.ohara.telemaco.data.DAO.UserDAO;
import com.android.ohara.telemaco.entity.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserValidate {
    public void validateRegisterUser(Context context, String name, String lastName, String email, String psw, String birth, String genre)
            throws ParseException, UserAlreadyExistsException {
        UserDAO userDAO = UserDAO.getInstance( context );

        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date birthday = format.parse(birth);

        User user = new User (name, lastName, email, psw, birthday, genre);
        validExistenceUser( user, userDAO );

        userDAO.insert(user);
    }

    private void validExistenceUser (User user, UserDAO userDAO) throws UserAlreadyExistsException {
        User answer = userDAO.select( user.getEmail() );
        if (answer != null) throw new UserAlreadyExistsException( Messages.USER_ALREADY_EXISTS );
    }

    public User validateLoginUser (Context context, String email, String psw) throws UserNotExistsException {
        UserDAO userDAO = UserDAO.getInstance( context );
        User answer = userDAO.select( email );
        if (answer == null) throw new UserNotExistsException( Messages.USER_INVALID );
        else {
            if (!psw.equals( answer.getPassword()))
                throw new UserNotExistsException( Messages.PSW_INVALID );
            else return answer;
        }
    }

}
