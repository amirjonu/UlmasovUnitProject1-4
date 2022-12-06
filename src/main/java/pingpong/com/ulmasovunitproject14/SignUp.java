package pingpong.com.ulmasovunitproject14;

import java.util.ArrayList;
//adds username and password into array list respectively
public class SignUp {
    /**
     * It adds the username and password inputted into the provided list and returns it
     * @param userName represents the username the user inputs
     * @param pass represents the password the user inputs
     * @param list represents the list of all the usernames and passwords
     * @return a list with the new username and passowrd added
     */
    public static ArrayList<String> addUser(String userName, String pass, ArrayList<String> list){
        list.add(userName);
        list.add(pass);
        return list;
    }
}
