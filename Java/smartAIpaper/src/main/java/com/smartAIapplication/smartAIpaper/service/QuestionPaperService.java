package com.smartAIapplication.smartAIpaper.service;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.*;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
@Service
public class QuestionPaperService {
    public byte[] generateQuestionPaper(String institution, String school, String module, int totalMarks, List<Map<String, Object>> questions) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Add institution and school name
            document.add(new Paragraph(institution).setBold().setFontSize(18).setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph(school).setBold().setFontSize(14).setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Module: " + module + " | Total Marks: " + totalMarks).setItalic().setFontSize(12).setMarginBottom(10));

            // Add instructions
            document.add(new Paragraph("Instructions:").setBold());
            document.add(new Paragraph("1. Answer all questions.\n2. Read each question carefully before answering.\n3. Use separate sheets if required.\n").setFontSize(10));

            // Add questions with numbering
            int questionNumber = 1;
            for (Map<String, Object> questionData : questions) {
                String questionText = (String) questionData.get("question");
                document.add(new Paragraph(questionNumber + ". " + questionText).setFontSize(12));
                questionNumber++;
            }

            document.close();
            return outputStream.toByteArray(); // Return the generated PDF as a byte array
        } catch (Exception e) {
            throw new RuntimeException("Error generating PDF", e);
        }
    }
}
