package org.example.resume.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import org.example.resume.analysis.JobAnalyzer;
import org.example.resume.analysis.ResumeAnalyzer;
import org.example.resume.scoring.ResumeMatcher;
import org.example.resume.textData.JobData;
import org.example.resume.textData.ResumeData;

import java.io.File;

public class MainController {
    @FXML private Button browseButton;
    @FXML private Label fileLabel;
    @FXML private Label keywordScoreLabel;
    @FXML private Label semanticScoreLabel;
    @FXML private Label finalScoreLabel;

    @FXML
    public void initialize(){
        browseButton.setOnAction(event -> {
            try {
                onBrowse();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @FXML
    public void onBrowse() throws Exception {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Resume File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PDF Files", "*.pdf"),
                new FileChooser.ExtensionFilter("Text Files", "*.txt")
        );
        File file = fileChooser.showOpenDialog(null);
        if (file != null){

            fileLabel.setText(file.getName());
            ResumeAnalyzer resumeAnalyzer = new ResumeAnalyzer();
            JobAnalyzer jobAnalyzer = new JobAnalyzer();
            ResumeData resumeData = resumeAnalyzer.analyzeResume(file);
            JobData jobData = jobAnalyzer.analyzeJobDescription(new File("src/main/resources/sampleData/sample_job_description.txt"));
            ResumeMatcher matcher = new ResumeMatcher();
            var matchResult = matcher.matchScore(resumeData, jobData);
            keywordScoreLabel.setText(String.format("%.2f",matchResult.getKeywordScore()));
            semanticScoreLabel.setText(String.format("%.2f",matchResult.getEmbeddingScore()));
            finalScoreLabel.setText(String.format("%.2f",matchResult.getFinalScore()));
        }
    }
}
