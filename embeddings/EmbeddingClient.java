package org.example.resume.embeddings;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.net.http.HttpClient;
public class EmbeddingClient {
    private final HttpClient httpClient;
    private final Gson gson;
    private final String apiUrl = "http://localhost:5001/embed";
    public EmbeddingClient() {
        this.httpClient = HttpClient.newHttpClient();
        this.gson = new Gson();
    }
    public List<Double> embed(String text) throws Exception {
        List<String> wrapper = new ArrayList<>();
        wrapper.add(text);
        List<List<Double>> vectors = embedMany(wrapper);
        return vectors.get(0);
    }
    public List<List<Double>> embedMany(List<String> texts) throws Exception{
        JsonObject json = new JsonObject();
        JsonArray arr = new JsonArray();
        for(String txt : texts){
            arr.add(txt);
        }
        json.add("text", arr);
        String requestBody = gson.toJson(json);
        // Build HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(apiUrl))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();
        // Send request and get response
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() != 200) {
            throw new RuntimeException("Embedding API returned status " + response.statusCode());
        }
        // Parse response JSON
        JsonObject obj = gson.fromJson(response.body(), JsonObject.class);
        JsonArray embArray = obj.getAsJsonArray("embeddings");


        List<List<Double>> result = new ArrayList<>();


        for (int i = 0; i < embArray.size(); i++) {
            JsonArray vectorJson = embArray.get(i).getAsJsonArray();
            List<Double> vector = new ArrayList<>();
            for (int j = 0; j < vectorJson.size(); j++) {
                vector.add(vectorJson.get(j).getAsDouble());
            }
            result.add(vector);
        }
        return result;

    }

    public double similarity(List<Double> v1, List<Double> v2){
        if (v1.size() != v2.size()) {
            throw new IllegalArgumentException("Vectors must have same size");
        }

        double dot = 0.0;
        double norm1 = 0.0;
        double norm2 = 0.0;

        for (int i = 0; i < v1.size(); i++) {
            double a = v1.get(i);
            double b = v2.get(i);
            dot += a * b;
            norm1 += a * a;
            norm2 += b * b;
        }

        double denom = Math.sqrt(norm1) * Math.sqrt(norm2);
        if (denom == 0) return 0;


        return dot / denom;
    }
}
