package util.authenticate;

import model.User;
import services.UserService;
import util.utility.Encryption;

public class Authentication {
    private static User authenticatedUser;

    public static User getAuthenticatedUser(){
        return authenticatedUser;
    }

    public static void setAuthenticatedUser(User user){
        authenticatedUser = user;
    }

    public static String authenticateUser(User user){

        UserService userService = new UserService();
        User resultUser = userService.loadSpecificUser(user.getuEmail());
        if(resultUser == null)
            return "Invalid Email";
        else if(!Encryption.passwordEncryption(user.getuPassword()).equals(resultUser.getuPassword()))
            return "Invalid Password";
        else{
            setAuthenticatedUser(resultUser);
            return "success";
        }


    }
}
