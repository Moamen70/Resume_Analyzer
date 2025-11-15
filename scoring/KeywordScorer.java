package org.example.resume.scoring;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class KeywordScorer {
    private HashMap<String, Double> keywords = new HashMap<>();
    public static int maxScore;

    public KeywordScorer() {

        // Core Backend & Java Skills
        keywords.put("java", 2.5);
        keywords.put("spring", 2.5);
        keywords.put("spring boot", 2.5);
        keywords.put("rest", 2.2);
        keywords.put("rest api", 2.5);
        keywords.put("microservices", 2.5);
        keywords.put("backend development", 2.0);
        keywords.put("oop", 1.8);
        keywords.put("jvm", 1.5);

        // Databases
        keywords.put("sql", 2.0);
        keywords.put("mysql", 2.0);
        keywords.put("postgresql", 2.0);
        keywords.put("postgres", 2.0);
        keywords.put("database design", 1.5);

        // ORM Frameworks
        keywords.put("hibernate", 2.2);
        keywords.put("jpa", 2.2);

        // Build Tools
        keywords.put("maven", 2.0);
        keywords.put("gradle", 1.5);

        // DevOps & Deployment
        keywords.put("docker", 2.0);
        keywords.put("ci/cd", 2.0);
        keywords.put("jenkins", 1.8);
        keywords.put("git", 1.8);

        // Testing & Quality
        keywords.put("unit testing", 2.0);
        keywords.put("integration testing", 1.8);
        keywords.put("junit", 2.0);
        keywords.put("mockito", 1.8);
        keywords.put("test driven development", 1.5);

        // Architecture & Concepts
        keywords.put("microservice architecture", 2.0);
        keywords.put("distributed systems", 1.8);
        keywords.put("api design", 1.7);
        keywords.put("scalability", 1.5);

        // Soft Skills (important for job description)
        keywords.put("teamwork", 1.0);
        keywords.put("communication", 1.0);
        keywords.put("problem solving", 1.3);
        keywords.put("clean code", 1.5);
        keywords.put("maintainable code", 1.0);

        // Experience Keywords
        keywords.put("backend services", 1.8);
        keywords.put("cross functional teams", 1.0);
        keywords.put("development", 1.0);

        // Compute maximum score
        for (var m : keywords.values()) {
            KeywordScorer.maxScore += m;
        }
    }

    public double scoreKeywords(List<String> tokens) {
        double score = 0.0;
        for (String token : tokens) {
            if (keywords.containsKey(token)) {
                score += keywords.get(token);
            }
        }
        return (score / this.maxScore);
    }
}
