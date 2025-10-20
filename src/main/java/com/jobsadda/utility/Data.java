package com.jobsadda.utility;

public class Data {

    public static String getMessageBody(String otp, String name) {
        return """
            <!DOCTYPE html>
            <html lang="en" style="font-family: Arial, sans-serif;">
              <head>
                <meta charset="UTF-8" />
                <title>Your OTP Code</title>
              </head>
              <body style="background-color: #f4f4f4; margin: 0; padding: 0;">
                <table width="100%" cellpadding="0" cellspacing="0">
                  <tr>
                    <td align="center" style="padding: 40px 0;">
                      <table width="600" cellpadding="0" cellspacing="0" style="background-color: #ffffff; border-radius: 10px; overflow: hidden; box-shadow: 0 2px 6px rgba(0,0,0,0.1);">
                        <tr>
                          <td style="background-color: #007bff; color: #ffffff; text-align: center; padding: 20px 0; font-size: 22px; font-weight: bold;">
                            🔐 Secure OTP Verification
                          </td>
                        </tr>
                        <tr>
                          <td style="padding: 30px; color: #333333; line-height: 1.6;">
            """ +
            "<h2 style='margin-top: 0;'>Hello, " + name + " 👋</h2>" +
            """
                            <p>We received a request to verify your account. Please use the following OTP (One-Time Password) to complete your verification:</p>
                            <p style="font-size: 30px; font-weight: bold; color: #007bff; text-align: center; letter-spacing: 5px; margin: 30px 0;">
            """ +
            otp +
            """
                            </p>
                            <p>This OTP is valid for <strong>5 minutes</strong>. Do not share it with anyone for security reasons.</p>
                            <p>If you did not request this, you can safely ignore this email.</p>
                            <p style="margin-top: 40px;">Thanks & Regards,<br><strong>JobsAdda Team</strong></p>
                          </td>
                        </tr>
                        <tr>
                          <td style="background-color: #f4f4f4; text-align: center; padding: 15px; font-size: 12px; color: #777;">
                            © 2025 JobsAdda. All rights reserved.
                          </td>
                        </tr>
                      </table>
                    </td>
                  </tr>
                </table>
              </body>
            </html>
            """;
    }
}
