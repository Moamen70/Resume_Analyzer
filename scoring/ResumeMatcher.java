package org.example.resume.scoring;

import org.example.resume.embeddings.EmbeddingClient;
import org.example.resume.textData.JobData;
import org.example.resume.textData.ResumeData;

public class ResumeMatcher {
    private final KeywordScorer keywordScorer;
    private final EmbeddingClient embeddingClient;
    public ResumeMatcher() throws Exception {
        this.keywordScorer = new KeywordScorer();
        this.embeddingClient = new EmbeddingClient();
    }

    public MatchResult matchScore(ResumeData resume, JobData job)throws Exception{
        double keywordScore = keywordScorer.scoreKeywords(resume.tokens);
        double semanticScore = embeddingClient.similarity(resume.embedding, job.embedding);
        return new MatchResult(keywordScore, semanticScore,
                0.3 * keywordScore + 0.7 * semanticScore);
    }
}
