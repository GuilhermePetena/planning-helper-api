package com.planning.taskplanning.mock;

import com.planning.taskplanning.model.User;
public class UserMock {

    public static User getUserMock(){
        return new User("1234-ABCD", "gui@email.com", "senha", "Qual a sua idade?", "22");
    }
}
