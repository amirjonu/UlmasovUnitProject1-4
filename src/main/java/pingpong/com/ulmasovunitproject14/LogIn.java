package pingpong.com.ulmasovunitproject14;

import java.util.ArrayList;


public class LogIn {
    static boolean answer;
    static boolean user;
    static boolean pa;

    public static boolean display(String username, String pass, ArrayList<String> list){
        //returns is username and pass are valid
        user=false;
       pa=false;
        for (int i=0; i<(list.size()); i++){
           if (list.get(i).equals(username)){
               user=true;
               if (list.get(i+1).equals(pass)){
                   pa=true;
                   break;
               }else{
                   break;
               }
           }
       }
        if (user && pa){
            answer=true;
        }else{
            answer=false;
        }

        return answer;
    }
}
