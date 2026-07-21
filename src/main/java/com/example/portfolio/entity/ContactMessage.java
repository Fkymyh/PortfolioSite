package com.example.portfolio.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.PrePersist;

/**
 * データベースへ保存するお問い合わせ情報です。
 */
@Entity
public class ContactMessage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String email;
	
	private String subject;
	
	@Lob
	private String message;
	
	private LocalDateTime createdAt;
	
	/** 初回保存の直前に受付日時を自動設定します。 */
	@PrePersist
	public void setCreatedAt() {
		createdAt = LocalDateTime.now();
	}
	
	public Long getId() {
		return id;
	}
	
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
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

}
