package com.example.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.portfolio.entity.ContactMessage;

/**
 * お問い合わせ情報の検索・保存・削除を行うRepositoryです。
 * JpaRepositoryの基本的なCRUD操作を利用します。
 */
public interface ContactMessageRepository
		extends JpaRepository<ContactMessage, Long> {
}

