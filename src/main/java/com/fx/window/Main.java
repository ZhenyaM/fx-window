package com.fx.window;

import com.fx.window.util.StageBuilder;
import com.fx.window.util.ViewUtils;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    private static final String PATH = "/fxml/window-frame.fxml";
    //    private static final String PATH = "/fxml/window-frame1.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage = load(primaryStage);

//        final Scene scene = new Scene(root, 800, 600);
//        scene.getStylesheets().add("css/fx-window.css");
//        primaryStage.setScene(scene);
        primaryStage.getScene().getStylesheets().add("css/fx-window.css");
        primaryStage.setMinWidth(200);
        primaryStage.setMinHeight(200);
        primaryStage.setMaxWidth(ViewUtils.getScreenDimension().getWidth() - 100);
        primaryStage.setMaxHeight(ViewUtils.getScreenDimension().getHeight() - 100);
        primaryStage.show();
    }

    private Stage load(final Stage primaryStage) throws java.io.IOException {
        final StageBuilder.BuildResult build = new StageBuilder()
                .setStage(primaryStage)
                .build();
        return build.getStage();
    }


    public static void main(String[] args) throws InterruptedException {
//        main.java.com.goxr3plus.fxborderlessscene.application.Main.main(args);
        launch(args);

    }
}
