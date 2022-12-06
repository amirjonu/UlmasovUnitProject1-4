package pingpong.com.ulmasovunitproject14;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class ConfirmBox {
    static boolean answer;
    //takes in a title and message and creates a new stage with a new scene that portrays a question/statements with a yes(to exit) or no(to stay) button.

    /**
     * display method that displays a new stage that acts as a confirm box or alert box for the user.
     * @param title represents the title of the stage
     * @param message represents the label that needs to be displayed
     * @return returns a boolean answer that depends on if the user pressed the yes or no button
     */

    public static boolean display(String title, String message){
        Stage stage= new Stage();
        CSS c= new CSS();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);
        Label label= new Label(message);
        label.setStyle(c.label());

        Button yes= new Button("Yes");
        yes.setStyle(c.button());
        Button no= new Button("No");
        no.setStyle(c.button());

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
