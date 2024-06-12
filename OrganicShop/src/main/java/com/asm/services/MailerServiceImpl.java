package com.asm.services;


import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.asm.model.MailInfo;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service // Đánh dấu lớp này là một Service để Spring Boot có thể quản lý
public class MailerServiceImpl implements MailService {

    @Autowired
    JavaMailSender sender; // Inject JavaMailSender để sử dụng dịch vụ gửi email

    @Override
    public void send(MailInfo mail) throws MessagingException {
        MimeMessage message = sender.createMimeMessage(); // Tạo một đối tượng MimeMessage
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8"); // Sử dụng MimeMessageHelper để hỗ trợ cấu hình email

        helper.setFrom(mail.getFrom()); // Thiết lập địa chỉ người gửi
        helper.setTo(mail.getTo()); // Thiết lập địa chỉ người nhận
        helper.setSubject(mail.getSubject()); // Thiết lập tiêu đề email
        helper.setText(mail.getBody(), true); // Thiết lập nội dung email, cho phép định dạng HTML
        helper.setReplyTo(mail.getFrom()); // Thiết lập địa chỉ phản hồi

        String[] cc = mail.getCc(); // Lấy danh sách cc từ MailInfo

        if (cc != null && cc.length > 0) {
            helper.setCc(cc); // Thiết lập danh sách cc nếu có
        }

        String[] bcc = mail.getBcc(); // Lấy danh sách bcc từ MailInfo

        if (bcc != null && bcc.length > 0) {
            helper.setBcc(bcc); // Thiết lập danh sách bcc nếu có
        }

        String[] attachments = mail.getAttachments(); // Lấy danh sách đính kèm từ MailInfo

        if (attachments != null && attachments.length > 0) {
            for (String path : attachments) {
                File file = new File(path); // Tạo đối tượng File từ đường dẫn
                helper.addAttachment(file.getName(), file); // Thêm file đính kèm vào email
            }
        }

        sender.send(message); // Gửi email qua SMTP server
    }

    @Override
    public void send(String to, String subject, String body) throws MessagingException {
        this.send(new MailInfo(to, subject, body)); // Tạo đối tượng MailInfo từ các tham số và gửi email
    }
}