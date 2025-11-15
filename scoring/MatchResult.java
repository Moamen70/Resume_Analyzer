package org.example.resume.scoring;

public class MatchResult {
    private final double keywordScore;
    private final double embeddingScore;
    private final double finalScore;

    public MatchResult(double keywordScore, double embeddingScore, double finalScore) {
        this.keywordScore = keywordScore;
        this.embeddingScore = embeddingScore;
        this.finalScore = finalScore;
    }

    public double getKeywordScore() {
        return keywordScore;
    }

    public double getEmbeddingScore() {
        return embeddingScore;
    }

    public double getFinalScore() {
        return finalScore;
    }
}
