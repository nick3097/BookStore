package com.company.panels.userPanel;

import com.company.entities.User;
import com.company.panels.BookStoreImpl;
import com.company.constants.Constants;
import com.company.entities.UserDataBase;
import com.company.entities.UserDataManager;

import java.io.IOException;
import java.util.List;

public class UserImpl extends BookStoreImpl implements UserService {
    private UserDataManager userDataBase;
    private User currentUser;
    private final String [] userCredentials = new String[7];
    private String userId;

    public UserImpl(String userId){
        this.userId = userId;
        userDataBase = new UserDataBase();
        currentUser = new User();
    }

    @Override
    public String[] userProfile() {
        List<User> userList = userDataBase.getUsers();
        for (User user: userList) {
            if (user.getId().equals(userId)){
                currentUser = user;
                userCredentials[Constants.USER_ID] = user.getId();
                userCredentials[Constants.USER_NAME] = user.getName();
                userCredentials[Constants.USER_SURNAME] = user.getSurname();
                userCredentials[Constants.USER_AGE] = user.getAge();
                userCredentials[Constants.USER_LOGIN] = user.getLogin();
                userCredentials[Constants.USER_PASSWORD] = user.getPassword();
            }
        }
        return userCredentials;
    }

    public void changeCredentials(int oldValue, String newValue){
        switch (oldValue){
            case Constants.USER_NAME:
                 currentUser.setName(newValue);
                 break;
            case Constants.USER_SURNAME:
                 currentUser.setSurname(newValue);
                 break;
            case Constants.USER_AGE:
                 currentUser.setAge(newValue);
                 break;
            case Constants.USER_LOGIN:
                 currentUser.setLogin(newValue);
                 break;
            case Constants.USER_PASSWORD:
                 currentUser.setPassword(newValue);
                 break;
        }
        try {
            userDataBase.exportUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void buyBook() {

    }


}
