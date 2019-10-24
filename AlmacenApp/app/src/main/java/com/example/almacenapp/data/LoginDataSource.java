package com.example.almacenapp.data;

import com.example.almacenapp.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {


        try {
            // TODO: handle loggedInUser authentication
            // TODO: obtener displayName del usuario y asignarselo (.nombre)
            LoggedInUser fakeUser =
                    new LoggedInUser(
                            username, username );
            if ((fakeUser.getDisplayName().equals( "daikoponce@gmail.com"))&&(password.equals("123467"))){
                return new Result.Success<>(fakeUser);

            }else{
                return new Result.Error(new IOException("Error, user no identificado"));
            }
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
