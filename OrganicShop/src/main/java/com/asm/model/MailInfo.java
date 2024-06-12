package com.asm.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailInfo {
	String from;
	String to;
	String[] cc;
	String[] bcc;
	String subject;
	String body;
	String[] attachments;
	public MailInfo(String to, String subject, String body) {  // tạo constructor chỉ với 3 trường nên tự khai báo
		this.from = "Oganic Shop <anhnvqps30771@fpt.edu.vn>";
		this.to = to;
		this.subject = subject;
		this.body = body;
		}
}