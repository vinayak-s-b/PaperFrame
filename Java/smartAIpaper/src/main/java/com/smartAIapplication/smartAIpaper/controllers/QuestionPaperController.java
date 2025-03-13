package com.smartAIapplication.smartAIpaper.controllers;

import com.smartAIapplication.smartAIpaper.service.QuestionPaperService;
import com.smartAIapplication.smartAIpaper.service.SampleData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class QuestionPaperController {

    @Autowired
    private QuestionPaperService questionPaperService;

    SampleData sampleData= new SampleData();
//    private final String PYTHON_API_URL = "http://localhost:5000/extract_questions"; // Python API URL

    @PostMapping("/generate-pdf")
    public ResponseEntity<byte[]> generateQuestionPaper(@RequestParam("files") List<MultipartFile> files) {
        try {
            // Send files to Python API
//            RestTemplate restTemplate = new RestTemplate();
//            ResponseEntity<Map> response = restTemplate.postForEntity(PYTHON_API_URL, files, Map.class);
//
//            if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//            }

//            List<Map<String, Object>> questions = (List<Map<String, Object>>) response.getBody().get("questions");
            List<Map<String, Object>> questions =sampleData.getSampleQuestions();

            // Generate formatted PDF
            byte[] pdfContent = questionPaperService.generateQuestionPaper(
                    "ABC University",  // Institution Name
                    "XYZ High School", // School Name
                    "Mathematics",     // Module
                    100,               // Total Marks
                    questions
            );

            // Send PDF as response
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Generated_Question_Paper.pdf")
                    .body(pdfContent);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
