package pingpong.com.ulmasovunitproject14;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.scene.Parent;
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;



public class HelloApplication extends Application{
    Button start, login, signup, howtoplay, back, easy, medium, hard, restart;
    Scene scene1, log, sign, scene2, Game, scene3, gameR;

    String mode;

    int score=0;

    ArrayList<String> listo= new ArrayList<>();

    Stage stage;

    Pane win;
    Rectangle player, ai;
    Circle ball;
    Line line;
    AnimationTimer time;
    final int w= 1000;
    final int l= 400;
    int speedx=3, speedy=3, dv=speedx, dy=speedy;

    private Parent content(){
        win=new Pane();
        win.setPrefSize(w, l);
        win.setStyle("-fx-background-color: black");

        line= new Line(w/2, 0, w/2, l);
        line.setStroke(Color.WHITE);

        ai= new Rectangle(10, 80, Color.WHITE);
        ai.setLayoutX(0);
        ai.setLayoutY(l/2-40);


        player= new Rectangle(10, 80, Color.WHITE);
        player.setLayoutX(w-10);
        player.setLayoutY(l/2-40);

        ball=new Circle(5);
        ball.setFill(Color.WHITE);
        ball.setLayoutX(w/2);
        ball.setLayoutY(l/2);


        time= new AnimationTimer() {
            @Override
            public void handle(long l) {
                gameUpdate();
            }
        };

        win.getChildren().addAll(line, ai, player, ball);

        time.start();
        return win;
    }

    private void gameUpdate(){
        double x=ball.getLayoutX(), y= ball.getLayoutY();

        if (x<=10 && y>ai.getLayoutY()&& y<ai.getLayoutY()+80) {
            dv=speedx;
        }


        if (x>= w-12.5 && y>player.getLayoutY()&& y<player.getLayoutY()+80 ){
            speedx++;
            dv=-speedx;
            score++;
        }
        if (y<=0) {
            dy=speedy;
        }

        if (y>=l-5) {
            dy= -speedy;
        }

        ball.setLayoutX(ball.getCenterX()+dv);
        ball.setLayoutY(ball.getLayoutY()+dy);

        if (x< w/2 && ai.getLayoutY()> y) {
            ai.setLayoutY(ai.getLayoutY()-5);
        }

        if (x< w/2 && ai.getLayoutY()+80< y) {
            ai.setLayoutY(ai.getLayoutY()+5);
        }
    }



    @Override
    public void start(Stage window) throws Exception{
        stage=window;
        stage.setTitle("Ping Pong");

        Label welcome= new Label("Welcome to Pong");
        start= new Button("Start");
        login= new Button("Log In");
        signup= new Button("Sign Up");
        start.setOnAction(e -> stage.setScene(scene2));

        howtoplay= new Button("How to Play");
        howtoplay.setOnAction(e-> stage.setScene(gameR));
        VBox layout= new VBox(20);
        layout.getChildren().addAll(welcome, start, howtoplay, login, signup);
        layout.setAlignment(Pos.CENTER);
        scene1= new Scene(layout, w, l);

        login.setOnAction(e-> stage.setScene(log));
        signup.setOnAction(e-> stage.setScene(sign));
        GridPane gridlog= new GridPane();
        gridlog.setPadding(new Insets(10, 10, 10, 10));
        gridlog.setVgap(8);
        gridlog.setHgap(10);
        Label username= new Label("Username: ");
        GridPane.setConstraints(username,0, 0);
        TextField name= new TextField();
        name.setPromptText("Password");
        GridPane.setConstraints(name, 1, 0);
        Label password= new Label("Password:");
        GridPane.setConstraints(password, 0, 1);
        TextField pas= new TextField();
        pas.setPromptText("Password");
        GridPane.setConstraints(pas, 1, 1);

        Button enter= new Button("Log In");
        GridPane.setConstraints(enter, 1, 2);
        gridlog.getChildren().addAll(username, name, password, pas, enter);
        log= new Scene(gridlog, w, l);


        enter.setOnAction(e->{

            if (LogIn.display(name.getText(), pas.getText(), listo)){
                stage.setScene(scene1);
            }else{
                if (ConfirmBox.display("Error", "Wrong UserName and Password. Continue?")){
                    stage.setScene(log);
                }else{
                    stage.setScene(scene1);
                }
            }
        });

        GridPane gridlog2= new GridPane();
        gridlog2.setPadding(new Insets(10, 10, 10, 10));
        gridlog2.setVgap(8);
        gridlog2.setHgap(10);
        Label username2= new Label("Username: ");
        GridPane.setConstraints(username2,0, 0);
        TextField name2= new TextField();
        name.setPromptText("Password");
        GridPane.setConstraints(name2, 1, 0);
        Label password2= new Label("Password:");
        GridPane.setConstraints(password2, 0, 1);
        TextField pas2= new TextField();
        pas.setPromptText("Password");
        GridPane.setConstraints(pas2, 1, 1);

        Button enter2= new Button("Sign Up");
        GridPane.setConstraints(enter2, 1, 2);
        gridlog2.getChildren().addAll(username2, name2, password2, pas2, enter2);
        sign= new Scene(gridlog2, w, l);
        enter2.setOnAction(e->{
            listo=SignUp.addUser(name2.getText(), pas2.getText(), listo);
            stage.setScene(scene1);
        });


        Label rules= new Label("Use the Left and Right arrow keys to move your paddle!");
        back= new Button("Back");
        back.setOnAction(e-> stage.setScene(scene1));
        VBox game= new VBox(20);
        game.getChildren().addAll(rules, back);
        game.setAlignment(Pos.CENTER);
        gameR= new Scene(game, w, l);

        easy= new Button("Easy");
        medium= new Button("Medium");
        hard= new Button("Hard");

        easy.setOnAction(e -> {
            stage.setScene(Game);
            mode="easy";
        });
        medium.setOnAction(e -> {
            stage.setScene(Game);
            mode="medium";
        });
        hard.setOnAction(e -> {
            stage.setScene(Game);
            mode="hard";
        });
        Label chooseMode= new Label("Choose your Mode");

        HBox layout2= new HBox(50);
        layout2.getChildren().addAll(easy, medium, hard);
        layout2.setAlignment(Pos.CENTER);

        HBox topMode= new HBox();
        topMode.getChildren().add(chooseMode);
        topMode.setAlignment(Pos.CENTER);

        BorderPane modeChoose= new BorderPane();
        modeChoose.setTop(topMode);
        modeChoose.setCenter(layout2);

        scene2= new Scene(modeChoose, w, l);

        Game= new Scene(content());
        Game.setOnKeyPressed(event -> {
            if (event.getCode()== KeyCode.UP){
                if (player.getLayoutY()<=40){
                    player.setLayoutY(0);
                }else{
                    player.setLayoutY(player.getLayoutY()-40);
                }
            }
            if (event.getCode()== KeyCode.DOWN){
                if (player.getLayoutY()+40>=l-40){
                    player.setLayoutY(l-80);
                }else{
                    player.setLayoutY(player.getLayoutY()+40);
                }
            }
        });



        restart= new Button("Restart");
        StackPane layout3= new StackPane();
        layout3.getChildren().add(restart);
        layout3.setAlignment(Pos.CENTER);
        scene3= new Scene(layout3, w, l);
        restart.setOnAction(e-> stage.setScene(scene1));

        stage.setOnCloseRequest(e-> {
            e.consume();
            closeProgram();
        });

        stage.setScene(scene1);
        stage.show();
    }

    private void closeProgram(){
        Boolean close= ConfirmBox.display("Close", "Are you sure you want to exit?");
        if (close==true){
            stage.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}