package model;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<User> userList = new ArrayList<>();

        User user1 =new User("ucar@mail.com","123456");
        User user2 =new User("mehmet@mail.com","123456");

        userList.add(user1);
        userList.add(user2);



    }
}
