package pingpong.com.ulmasovunitproject14;

import java.util.ArrayList;

public class HighScoreCalc {
    private String top1="0";
    private String top2="0";
    private String top3="0";
    public HighScoreCalc(){
    }

    public String getHighScores(ArrayList<String>list){
        for (int i=0; i<(list.size()); i++){
            if (Integer.parseInt(list.get(i))>=Integer.parseInt(top1)){
                if (Integer.parseInt(list.get(i))==Integer.parseInt(top1)){continue;}
                top2=top1;
                top1= list.get(i);
                break;
            }
            if (Integer.parseInt(list.get(i))>=Integer.parseInt(top2)){
                if (Integer.parseInt(list.get(i))==Integer.parseInt(top2)){continue;}
                top3=top2;
                top2= list.get(i);
                break;
            }
            if (Integer.parseInt(list.get(i))>=Integer.parseInt(top3)){
                if (Integer.parseInt(list.get(i))==Integer.parseInt(top3)){continue;}
                top3=list.get(i);
                break;
            }
        }


        String total= "Top1: "+top1+"\nTop2: "+top2+"\nTop3: "+top3;
        return total;
    }
}
