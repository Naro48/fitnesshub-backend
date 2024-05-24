
package com.fitnesshub.ai.controller;
import com.fitnesshub.ai.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
public class ContactUsController {
    @Autowired
    private PredictionService predictionService;

    @PostMapping("/submit")
    public String submitContact(@RequestParam String message) {
        String isImportant = predictionService.isImportant(message);
        return isImportant ;
    }
}
