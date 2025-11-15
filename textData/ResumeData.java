package org.example.resume.textData;

import java.util.List;

public class ResumeData {
    public String rawText;
    public List<String> tokens;
    public List<Double> embedding;


    public ResumeData(String rawText, List<String> tokens, List<Double> embedding) {
        this.rawText = rawText;
        this.tokens = tokens;
        this.embedding = embedding;
    }
}
