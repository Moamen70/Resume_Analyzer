📄 Resume Analyzer – AI-Powered Resume Matching System

A smart Resume Analyzer built with JavaFX (UI), Java (processing & scoring), and a Python Embeddings API powered by Sentence Transformers.
This application compares a resume to a job description and generates:

Keyword Match Score

Semantic Similarity Score

Final Combined Score

Perfect for job seekers and developers learning applied NLP + desktop app development.

🚀 Features
🔍 Resume Parsing

Supports PDF and TXT files

Uses Apache Tika for clean text extraction

Removes stopwords, special characters, and noise

📝 Job Description Analysis

Extracts required skills, technologies, and keywords

Reads a job description template from the resources folder

🧠 Keyword Scoring Engine

Detects technical skills

Tools & technologies

Backend & testing keywords

Soft skills

Assigns weighted scores (customizable)

🤖 Semantic Similarity (AI Scoring)

Powered by a Python microservice using:

SentenceTransformers

all-MiniLM-L6-v2 model

Flask API

It computes embeddings and measures how semantically close a resume is to a job description.

🧮 Final Score Calculation

Combined score:

(Keyword Score + Semantic Score) / 2

🎨 Modern JavaFX User Interface

Simple and responsive design

Browse button to upload resume files

Displays:
✔ File name
✔ Keyword Score
✔ Semantic Score
✔ Final Score

Gradient background and clean label layout

🧩 Architecture Overview
+-------------------------+
|        JavaFX UI        |
+-------------------------+
            |
            v
+-------------------------+
|  Resume / Job Analyzer  |
| (Java + Apache Tika)    |
+-------------------------+
            |
            | sends text
            v
+-------------------------+
| Python Embedding API    |
| Flask + MiniLM Embeds   |
+-------------------------+
            |
            | similarity vector
            v
+-------------------------+
|     Resume Matcher      |
| (Keyword + Embedding)   |
+-------------------------+

🛠️ Technologies Used
Java

JavaFX

Apache Tika

Gson

HTTPClient

Java Streams & Collections

Python

Flask

SentenceTransformers

PyTorch

NumPy

Tools

Maven

JavaFX Maven Plugin

Venv (Python virtual environment)

📁 Project Structure
src/
 ├── main/java/org/example/resume/
 │      ├── controllers/        # JavaFX Controller
 │      ├── analysis/           # Resume/Job analyzers
 │      ├── scoring/            # Keyword & embedding scoring
 │      ├── textData/           # Data models
 │      └── Main.java           # JavaFX entry point
 │
 ├── main/resources/
 │      ├── main-view.fxml      # UI
 │      └── sampleData/
 │           └── sample_job_description.txt
 │
pythonServer/
 └── embedding_service.py        # Python AI API
