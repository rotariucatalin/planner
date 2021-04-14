package com.example.Planner.pdf;

import com.example.Planner.models.Inquiry;
import com.example.Planner.models.Product;
import com.example.Planner.services.InquiryService;
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
import java.util.List;

@Component
public class InquiryPDF {

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private InquiryService inquiryService;

    private final TemplateEngine templateEngine;
    private final HttpServletRequest request;
    private final HttpServletResponse response;

    public InquiryPDF(TemplateEngine templateEngine, HttpServletRequest request, HttpServletResponse response) {
        this.templateEngine = templateEngine;
        this.request = request;
        this.response = response;
    }

    public ResponseEntity<?> createPDF(int inquiryId) {

        Inquiry inquiry = inquiryService.findById(inquiryId);
        List<Product> productList = inquiry.getProductList();

        WebContext context = new WebContext(request, response, servletContext);
        context.setVariable("inquiry", inquiry);
        context.setVariable("products", productList);
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
