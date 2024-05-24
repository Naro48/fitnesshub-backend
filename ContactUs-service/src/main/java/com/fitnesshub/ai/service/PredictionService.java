package com.fitnesshub.ai.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;

@Service
public class PredictionService {

    public String isImportant(String message) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python", "src/main/python/predict.py", message);
            processBuilder.redirectErrorStream(true); // Combine standard error and standard output
            Process process = processBuilder.start();

            // Capture standard output and standard error
            String result = captureOutput(process.getInputStream());
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                System.out.println("Python script exited with error code: " + exitCode);
            }
            System.out.println("Python script output: " + result);
            return result.trim();
        } catch (Exception e) {
            e.printStackTrace(); // Print the exception for debugging purposes
            return "I failed in this :(";
        }
    }

    private String captureOutput(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append(System.lineSeparator());
        }
        return output.toString();
    }
}
