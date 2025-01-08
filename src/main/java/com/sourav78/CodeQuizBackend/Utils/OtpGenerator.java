package com.sourav78.CodeQuizBackend.Utils;

public class OtpGenerator {
    // Java code to generate a random OTP of 6 digits
    public static String generateOTP(int lengthOfOtp) {
        // Using numeric values
        String numbers = "0123456789";
        // Using random method
        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < lengthOfOtp; i++) {
            // Using Math.random() method
            int index = (int) (numbers.length() * Math.random());
            sb.append(numbers.charAt(index));
        }
        return sb.toString();
    }
}
