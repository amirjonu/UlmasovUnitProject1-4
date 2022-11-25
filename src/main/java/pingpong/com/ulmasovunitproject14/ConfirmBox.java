package pingpong.com.ulmasovunitproject14;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class ConfirmBox {
    static boolean answer;
    public static boolean display(String title, String message){
        Stage stage= new Stage();

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        Label label= new Label(message);

        Button yes= new Button("Yes");
        Button no= new Button("No");

        yes.setOnAction(e->{
            answer=true;
            stage.close();
        });

        no.setOnAction(e->{
            answer=false;
            stage.close();
        });

        VBox layout= new VBox(30);
        layout.getChildren().addAll(label,yes, no);
        layout.setAlignment(Pos.CENTER);
        Scene scene= new Scene(layout, 300, 300);
        stage.setScene(scene);
        stage.showAndWait();

        return answer;
    }
}
