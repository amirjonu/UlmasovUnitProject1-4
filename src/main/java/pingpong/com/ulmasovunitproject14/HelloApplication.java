package pingpong.com.ulmasovunitproject14;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.scene.Parent;
import javafx.scene.shape.Line;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;



public class HelloApplication extends Application{
    Button start, login, signup, howtoplay, back, easy, medium, hard, restart, sc,vs;
    Scene scene1, log, sign, scene2, Game, scene3, gameR;

    String mode;

    int score=0, lives=3, counter=0;

    ArrayList<String> listo= new ArrayList<>();
    ArrayList<String> highScore= new ArrayList<>();

    Stage stage;

    Pane win;
    Rectangle player, ai;
    Circle ball;
    Line line;
    AnimationTimer time;
    final int w= 1000;
    final int l= 400;
    int speedx=3, speedy=3, dv=speedx, dy=speedy;

    private Parent content() {
            win = new Pane();
            win.setPrefSize(w, l);
            win.setStyle("-fx-background-color: black");

            line = new Line(w / 2, 0, w / 2, l);
            line.setStroke(Color.WHITE);

            ai = new Rectangle(10, 80, Color.WHITE);
            ai.setLayoutX(0);
            ai.setLayoutY(l / 2 - 40);


            player = new Rectangle(10, 80, Color.WHITE);
            player.setLayoutX(w - 10);
            player.setLayoutY(l / 2 - 40);

            ball = new Circle(5);
            ball.setFill(Color.WHITE);
            ball.setLayoutX(w / 2);
            ball.setLayoutY(l / 2);
                time = new AnimationTimer() {
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
        if (counter==1) {
            double x = ball.getLayoutX(), y = ball.getLayoutY();

            if (x <= 10 && y > ai.getLayoutY() && y < ai.getLayoutY() + 80) {
                dv = speedx;
            }

            if (x >= w - 12.5 && y > player.getLayoutY() && y < player.getLayoutY() + 80) {
                speedx++;
                dv = -speedx;
                score++;
            }

            if (x <= 5) {
                dv = speedx;
            }
            if (x >= w - 5) {
                ball.setLayoutY(l/2);
                ball.setLayoutX(w/2);
                dv= -speedx;
                lives--;
            }

            if (y <= 0) {
                dy = speedy;
            }

            if (y >= l - 5) {
                dy = -speedy;
            }

            ball.setLayoutX(ball.getLayoutX() + dv);
            ball.setLayoutY(ball.getLayoutY() + dy);

            if (x < w / 2 && ai.getLayoutY() > y) {
                ai.setLayoutY(ai.getLayoutY() - 5);
            }

            if (x < w / 2 && ai.getLayoutY() + 80 < y) {
                ai.setLayoutY(ai.getLayoutY() + 5);
            }
            if (lives == 0) {
                stage.setScene(scene3);
            }
        }
    }



    @Override
    public void start(Stage window) throws Exception{
        CSS c= new CSS();
        stage=window;
        stage.setTitle("Ping Pong");

        Label welcome= new Label("Welcome to Pong");
        welcome.setStyle(c.label());
        start= new Button("Start");
        start.setStyle(c.button());
        login= new Button("Log In");
        login.setStyle(c.button());
        signup= new Button("Sign Up");
        signup.setStyle(c.button());
        start.setOnAction(e -> stage.setScene(scene2));
        howtoplay= new Button("How to Play");
        howtoplay.setStyle(c.button());
        howtoplay.setOnAction(e-> stage.setScene(gameR));
        VBox layout= new VBox(20);
        layout.getChildren().addAll(welcome, start, howtoplay, login, signup);
        layout.setAlignment(Pos.CENTER);
        login.setOnAction(e-> stage.setScene(log));
        signup.setOnAction(e-> stage.setScene(sign));

        scene1= new Scene(layout, w, l);
        GridPane gridlog= new GridPane();
        gridlog.setPadding(new Insets(10, 10, 10, 10));
        gridlog.setVgap(8);
        gridlog.setHgap(10);
        Label username= new Label("Username: ");
        username.setStyle(c.label());
        GridPane.setConstraints(username,0, 0);
        TextField name= new TextField();
        name.setPromptText("Username");
        GridPane.setConstraints(name, 1, 0);
        Label password= new Label("Password: ");
        password.setStyle(c.label());
        GridPane.setConstraints(password, 0, 1);
        PasswordField pas= new PasswordField();
        pas.setPromptText("Password");
        GridPane.setConstraints(pas, 1, 1);

        Button enter= new Button("Log In");
        enter.setStyle(c.button());
        GridPane.setConstraints(enter, 1, 2);
        gridlog.getChildren().addAll(username, name, password, pas, enter);
        log= new Scene(gridlog, w, l);


        enter.setOnAction(e->{

            if (LogIn.display(name.getText(), pas.getText(), listo)){
                stage.setScene(scene1);
            }else{
                if (ConfirmBox.display("Error", "Wrong UserName or Pass. Continue?")){
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
        username2.setStyle(c.label());
        GridPane.setConstraints(username2,0, 0);
        TextField name2= new TextField();
        name2.setPromptText("Username");
        GridPane.setConstraints(name2, 1, 0);
        Label password2= new Label("Password: ");
        password2.setStyle(c.label());
        GridPane.setConstraints(password2, 0, 1);
        PasswordField pas2= new PasswordField();
        pas2.setPromptText("Password");
        GridPane.setConstraints(pas2, 1, 1);

        Button enter2= new Button("Sign Up");
        enter2.setStyle(c.button());
        GridPane.setConstraints(enter2, 1, 2);
        gridlog2.getChildren().addAll(username2, name2, password2, pas2, enter2);
        sign= new Scene(gridlog2, w, l);
        enter2.setOnAction(e->{
            listo=SignUp.addUser(name2.getText(), pas2.getText(), listo);
            stage.setScene(scene1);
        });


        Label rules= new Label("Use the Up and Down arrow keys to move your paddle! You are on the right side! You have only 3 lives!");
        rules.setStyle(c.label());
        back= new Button("Back");
        back.setStyle(c.button());
        back.setOnAction(e-> stage.setScene(scene1));
        VBox game= new VBox(20);
        game.getChildren().addAll(rules, back);
        game.setAlignment(Pos.CENTER);
        gameR= new Scene(game, w, l);

        easy= new Button("Easy");
        easy.setStyle(c.button());
        medium= new Button("Medium");
        medium.setStyle(c.button());
        hard= new Button("Hard");
        hard.setStyle(c.button());

        easy.setOnAction(e -> {
            stage.setScene(Game);
            mode="easy";
            counter=1;
        });
        medium.setOnAction(e -> {
            stage.setScene(Game);
            mode="medium";
            counter=1;
        });
        hard.setOnAction(e -> {
            stage.setScene(Game);
            mode="hard";
            counter=1;
        });
        Label chooseMode= new Label("Choose your Mode");
        chooseMode.setStyle(c.label());

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

        Game = new Scene(content());
            Game.setOnKeyPressed(event -> {
                if (mode.equals("easy")){
                    if (event.getCode() == KeyCode.UP) {
                        if (player.getLayoutY() <= 40) {
                            player.setLayoutY(0);
                        } else {
                            player.setLayoutY(player.getLayoutY() - 60);
                        }
                    }
                    if (event.getCode() == KeyCode.DOWN) {
                        if (player.getLayoutY() + 40 >= l - 40) {
                            player.setLayoutY(l - 80);
                        } else {
                            player.setLayoutY(player.getLayoutY() + 60);
                        }
                    }
                }else if (mode.equals("medium")){
                    if (event.getCode() == KeyCode.UP) {
                        if (player.getLayoutY() <= 40) {
                            player.setLayoutY(0);
                        } else {
                            player.setLayoutY(player.getLayoutY() - 40);
                        }
                    }
                    if (event.getCode() == KeyCode.DOWN) {
                        if (player.getLayoutY() + 40 >= l - 40) {
                            player.setLayoutY(l - 80);
                        } else {
                            player.setLayoutY(player.getLayoutY() + 40);
                        }
                    }
                }else{
                    if (event.getCode() == KeyCode.UP) {
                        if (player.getLayoutY() <= 40) {
                            player.setLayoutY(0);
                        } else {
                            player.setLayoutY(player.getLayoutY() - 20);
                        }
                    }
                    if (event.getCode() == KeyCode.DOWN) {
                        if (player.getLayoutY() + 40 >= l - 40) {
                            player.setLayoutY(l - 80);
                        } else {
                            player.setLayoutY(player.getLayoutY() + 20);
                        }
                    }
                }
            });



            Label lose = new Label("You lose. Click score to view score");
            lose.setStyle(c.label());
            restart = new Button("Restart");
            restart.setStyle(c.button());
            sc= new Button("View Score");
            sc.setStyle(c.button());
            vs= new Button("View Past High Scores");
            vs.setStyle(c.button());
            VBox layout3 = new VBox(10);
            layout3.getChildren().addAll(lose, restart,sc, vs);
            layout3.setAlignment(Pos.CENTER);
            scene3 = new Scene(layout3, w, l);
            HighScoreCalc calc= new HighScoreCalc();
            vs.setOnAction(e->{
                highScore.add(getScore());
                ConfirmBox.display("High Score", calc.getHighScores(highScore));
                counter=0;
            });
            restart.setOnAction(e -> {
                stage.setScene(scene1);
                score=0;
                lives=3;
                counter=0;
                speedx=3;
                speedy=3;
                dv=speedx;
                dy=speedy;
                ball.setLayoutX(w / 2);
                ball.setLayoutY(l / 2);
            });
            sc.setOnAction(e->{
                ConfirmBox.display("Score", getScore());
                counter=0;
            });
            stage.setOnCloseRequest(e -> {
                e.consume();
                closeProgram();
            });


        stage.setScene(scene1);
        stage.show();
    }

    public String getScore(){
        return ""+score;
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