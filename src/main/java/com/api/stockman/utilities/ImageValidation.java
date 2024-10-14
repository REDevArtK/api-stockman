package com.api.stockman.utilities;

import org.springframework.stereotype.Component;

@Component
public class ImageValidation {
    public static boolean isValidImagePath(String imagePath) {
        // Check if the imagePath is not null and ends with .jpg or .png (case insensitive)
        return imagePath != null && (imagePath.toLowerCase().endsWith(".jpg") || imagePath.toLowerCase().endsWith(".png"));
    }
}
