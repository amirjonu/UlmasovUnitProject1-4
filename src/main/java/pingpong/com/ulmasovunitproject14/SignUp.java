package pingpong.com.ulmasovunitproject14;

import java.util.ArrayList;

public class SignUp {
    public static ArrayList<String> addUser(String userName, String pass, ArrayList<String> list){
        list.add(userName);
        list.add(pass);
        return list;
    }
}
