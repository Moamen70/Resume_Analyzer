package org.example.resume.nlp;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import org.apache.tika.exception.TikaException;
import org.example.resume.parser.TikaParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class TextPreprocessor {
    private final SentenceDetectorME sentenceDetector;
    private final TokenizerME tokenizer;
    private final Set<String> stopWords;
    int minTokenLength = 2;
    private final Set<String> shortTokenWhitelist = Set.of("c", "r", "go", "js");

    public TextPreprocessor() throws IOException{
        try(InputStream sentenceModelStream = openModel("models/opennlp-en-ud-ewt-sentence-1.3-2.5.4.bin")){
            SentenceModel sentenceModel = new SentenceModel(sentenceModelStream);
            this.sentenceDetector = new SentenceDetectorME(sentenceModel);
        }
        try(InputStream tokenModelStream = openModel("models/opennlp-en-ud-ewt-tokens-1.3-2.5.4.bin")){
            TokenizerModel tokenModel = new TokenizerModel(tokenModelStream);
            this.tokenizer = new TokenizerME(tokenModel);
        }
        this.stopWords = new HashSet<>(Arrays.asList(
                "a", "an", "and", "the", "or", "in", "on", "of", "for",
                "to", "with", "is", "are", "was", "were", "at", "by", "from",
                "as", "that", "this", "be", "have", "has", "it", "i", "he", "she",
                "they", "we", "you", "your", "their", "our"
        ));
    }
    private InputStream openModel(String resourcePath) throws IOException {
        InputStream in = getClass().getClassLoader().getResourceAsStream(resourcePath);
        if (in == null) {
            throw new IOException("Model resource not found: " + resourcePath);
        }
        return in;
    }
    public List<String> getProccessedText(String rawText) throws IOException, TikaException{
        if (rawText == null || rawText.isBlank()) {
            return Collections.emptyList();
        }
        String[] sentences = sentenceDetector.sentDetect(rawText);
        List<String> processedTokens = new ArrayList<>();

        for(String sentence : sentences){
            String[] tokens = this.tokenizer.tokenize(sentence);
            for (String token : tokens) {
                // 3. Normalization
                String normalized = normalizeToken(token);

                // 4. Skip punctuation
                if (isPunctuation(normalized)) continue;

                // 5. Skip stopwords
                if (stopWords.contains(normalized)) continue;

                // 6. Skip short tokens (unless whitelisted)
                if (normalized.length() < minTokenLength && !shortTokenWhitelist.contains(normalized))
                    continue;

                processedTokens.add(normalized);
            }
        }
        return dedupePreserveOrder(processedTokens);
    }
    private String normalizeToken(String token) {
        if (token == null) return "";

        String t = token.trim();
        if (t.isEmpty()) return "";

        return t.toLowerCase();

    }
    private boolean isPunctuation(String token) {
        return token.matches("\\p{Punct}+");
    }

    private List<String> dedupePreserveOrder(List<String> tokens) {
        LinkedHashSet<String> set = new LinkedHashSet<>(tokens);
        return new ArrayList<>(set);
    }
}


