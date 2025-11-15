package org.example.resume;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.resume.analysis.JobAnalyzer;
import org.example.resume.analysis.ResumeAnalyzer;
import org.example.resume.controllers.MainController;
import org.example.resume.nlp.TextPreprocessor;
import org.example.resume.parser.TikaParser;
import org.example.resume.scoring.KeywordScorer;
import org.example.resume.scoring.ResumeMatcher;
import org.example.resume.textData.JobData;
import org.example.resume.textData.ResumeData;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/MainWindow.fxml"));
        Scene scene = new Scene(loader.load(), 650, 450);

        stage.setTitle("Resume Analyzer");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}