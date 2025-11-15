package org.example.resume.analysis;

import org.apache.tika.exception.TikaException;
import org.example.resume.embeddings.EmbeddingClient;
import org.example.resume.nlp.TextPreprocessor;
import org.example.resume.parser.TikaParser;
import org.example.resume.textData.JobData;
import org.example.resume.textData.ResumeData;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JobAnalyzer {
    private final TikaParser tikaParser;
    private final TextPreprocessor textPreprocessor;
    private final EmbeddingClient embeddingClient;

    public JobAnalyzer() throws Exception {
        this.tikaParser = new TikaParser();
        this.textPreprocessor = new TextPreprocessor();
        this.embeddingClient = new EmbeddingClient();
    }


    public JobData analyzeJobDescription(File file) throws IOException, TikaException, Exception {
        String rawText = tikaParser.parse(file);
        List<String> tokens = textPreprocessor.getProccessedText(rawText);
        List<Double> embedding = embeddingClient.embed(rawText);
        return new JobData(rawText, tokens, embedding);
    }
}
