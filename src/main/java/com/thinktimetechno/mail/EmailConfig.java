/*
 * Copyright (c) 2022.
 * Automation Framework Selenium - Anh Tester
 */

package com.thinktimetechno.mail;

import com.thinktimetechno.constants.FrameworkConstants;

/**
 * Data for Sending email after execution
 */
public class EmailConfig {

    //Nhớ tạo mật khẩu app (App Password) cho Gmail mới gửi được nhen
    //Nếu dùng mail của Hosting thì bình thường
    //Enable Override Report and Send mail in config file => src/test/resources/config/config.properties
    //OVERRIDE_REPORTS=yes
    //send_email_to_users=yes

    public static final String SERVER = "smtp.hostinger.com";
    public static final String PORT = "587";

    public static final String FROM = "priya@thinktime.in";
    public static final String PASSWORD = "Priya@2023";

    public static final String[] TO = {"shankar@thinktime.in"};
    public static final String SUBJECT = FrameworkConstants.REPORT_TITLE;
}