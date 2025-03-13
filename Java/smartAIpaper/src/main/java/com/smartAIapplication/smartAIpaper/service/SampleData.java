package com.smartAIapplication.smartAIpaper.service;

import java.util.*;

public class SampleData {
    public  List<Map<String, Object>> getSampleQuestions() {
        List<Map<String, Object>> questions = new ArrayList<>();

        // Adding multiple questions
        questions.add(createQuestion(1, "Who was the first President of the United States?",
                Arrays.asList("George Washington", "Abraham Lincoln", "Thomas Jefferson", "John Adams"), 1));

        questions.add(createQuestion(2, "What is the capital of France?",
                Arrays.asList("Berlin", "Madrid", "Paris", "Rome"), 3));

        questions.add(createQuestion(3, "Solve for x: 2x + 5 = 15",
                Arrays.asList("x = 3", "x = 5", "x = 7", "x = 10"), 2));

        questions.add(createQuestion(4, "What is the chemical symbol for water?",
                Arrays.asList("CO₂", "H₂O", "O₂", "HCl"), 2));

        return questions;
    }
    private  Map<String, Object> createQuestion(int id, String question, List<String> options, int correctOptionIndex) {
        Map<String, Object> questionData = new HashMap<>();
        questionData.put("id", id);
        questionData.put("question", question);
        questionData.put("options", options);
        questionData.put("answerIndex", correctOptionIndex); // Index of correct answer (starting from 1)
        return questionData;
    }
}
