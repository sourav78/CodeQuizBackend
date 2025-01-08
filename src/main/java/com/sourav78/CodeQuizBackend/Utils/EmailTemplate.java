package com.sourav78.CodeQuizBackend.Utils;

public class EmailTemplate {
    public static String getVerificationEmailTemplate(String verificationCode) {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>Verification Code</title>\n" +
                "    <style>\n" +
                "        body {\n" +
                "            font-family: Arial, sans-serif;\n" +
                "            background-color: #f4f4f4;\n" +
                "            margin: 0;\n" +
                "            padding: 0;\n" +
                "        }\n" +
                "        .container {\n" +
                "            width: 100%;\n" +
                "            max-width: 600px;\n" +
                "            margin: 0 auto;\n" +
                "            background-color: #ffffff;\n" +
                "            padding: 20px;\n" +
                "            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
                "        }\n" +
                "        .header {\n" +
                "            text-align: center;\n" +
                "            padding: 10px 0;\n" +
                "        }\n" +
                "        .header h1 {\n" +
                "            margin: 0;\n" +
                "            color: #333333;\n" +
                "        }\n" +
                "        .content {\n" +
                "            padding: 20px;\n" +
                "            text-align: center;\n" +
                "        }\n" +
                "        .content p {\n" +
                "            font-size: 16px;\n" +
                "            color: #555555;\n" +
                "        }\n" +
                "        .verification-code {\n" +
                "            font-size: 24px;\n" +
                "            font-weight: bold;\n" +
                "            color: #333333;\n" +
                "            margin: 20px 0;\n" +
                "        }\n" +
                "        .caution {\n" +
                "            font-size: 14px;\n" +
                "            color: #ff0000;\n" +
                "            margin-top: 20px;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"header\">\n" +
                "            <img src=\"https://res.cloudinary.com/sourav78/image/upload/v1736327376/CodeQuiz/public/ef7nbwl1byrznnc2th7u.png\" alt=\"Company Logo\" width=\"100\" height=\"100\">\n" +
                "            <h1>CodeQuiz</h1>\n" +
                "        </div>\n" +
                "        <div class=\"content\">\n" +
                "            <p>Dear User,</p>\n" +
                "            <p>Thank you for registering with CodeQuiz. Please use the following verification code to complete your registration:</p>\n" +
                "            <div class=\"verification-code\">" + verificationCode + "</div>\n" +
                "            <p class=\"caution\">Caution: Do not share this code with anyone. If you did not request this code, please ignore this email.</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
    }
}
