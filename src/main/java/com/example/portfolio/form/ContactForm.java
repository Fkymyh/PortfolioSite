package com.example.portfolio.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ContactForm {
	// 画面入力用の値と、各項目の入力チェック条件をまとめています。
	
	@NotBlank(message = "お名前を入力してください")
	private String name;
	
	@NotBlank(message = "メールアドレスを入力してください")
	@Email(message = "メールアドレスの形式で入力してください")
	private String email;
	
	@NotBlank(message = "件名を入力してください")
	@Size(max = 50, message = "件名は50文字以内で入力してください")
	private String subject;
	
	@NotBlank(message = "メッセージを入力してください")
	@Size(max = 1000, message = "メッセージは1000文字以内で入力してください")
	private String message;
	
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
