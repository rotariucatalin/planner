package com.example.Planner.pdf;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;

@Component
public class InquiryPDF {

    @Autowired
    private ServletContext servletContext;

    private final TemplateEngine templateEngine;
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public InquiryPDF(TemplateEngine templateEngine, HttpServletRequest request, HttpServletResponse response) {
        this.templateEngine = templateEngine;
        this.request = request;
        this.response = response;
    }

    public ResponseEntity<?> createPDF() {

        WebContext context = new WebContext(request, response, servletContext);
        String inquiryHtml = templateEngine.process("inquiry", context);

        ByteArrayOutputStream target = new ByteArrayOutputStream();
        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri("http://localhost:8080");

        HtmlConverter.convertToPdf(inquiryHtml, target, converterProperties);

        byte[] bytes = target.toByteArray();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=inquiry.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);
    }

}
