package com.example.portfolio.controller;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.portfolio.repository.ContactMessageRepository;

/**
 * 管理者向けのお問い合わせ一覧、詳細、削除処理を担当します。
 */
@Controller
public class AdminController {

	private final ContactMessageRepository contactMessageRepository;

	public AdminController(
			ContactMessageRepository contactMessageRepository) {
		this.contactMessageRepository = contactMessageRepository;
	}

	/** 新しいお問い合わせから順に一覧表示します。 */
	@GetMapping("/admin/messages")
	public String adminMessages(Model model) {
		model.addAttribute(
			"pageTitle",
			"お問い合わせ管理"
		);
		model.addAttribute("currentPage", "");

		model.addAttribute(
			"contactMessages",
			contactMessageRepository.findAll(
				Sort.by(
					Sort.Direction.DESC,
					"createdAt"
				)
			)
		);

		return "admin-messages";
	}

	/** 指定されたお問い合わせの詳細を表示します。 */
	@GetMapping("/admin/messages/{id}")
	public String adminMessageDetail(
			@PathVariable Long id,
			Model model,
			RedirectAttributes redirectAttributes) {

		return contactMessageRepository.findById(id)
			.map(contactMessage -> {
				model.addAttribute(
					"pageTitle",
					"お問い合わせ詳細"
				);
				model.addAttribute("currentPage", "");
				model.addAttribute(
					"contactMessage",
					contactMessage
				);

				return "admin-message-detail";
			})
			.orElseGet(() -> {
				redirectAttributes.addFlashAttribute(
					"errorMessage",
					"指定されたお問い合わせが見つかりません。"
				);

				return "redirect:/admin/messages";
			});
	}

	/** 削除前の確認画面を表示します。 */
	@GetMapping("/admin/messages/{id}/delete")
	public String confirmDeleteMessage(
			@PathVariable Long id,
			Model model,
			RedirectAttributes redirectAttributes) {

		return contactMessageRepository.findById(id)
			.map(contactMessage -> {
				model.addAttribute(
					"pageTitle",
					"お問い合わせ削除確認"
				);
				model.addAttribute("currentPage", "");
				model.addAttribute(
					"contactMessage",
					contactMessage
				);

				return "admin-message-delete";
			})
			.orElseGet(() -> {
				redirectAttributes.addFlashAttribute(
					"errorMessage",
					"指定されたお問い合わせが見つかりません。"
				);

				return "redirect:/admin/messages";
			});
	}

	/** 確認済みのお問い合わせを削除し、一覧へ戻します。 */
	@PostMapping("/admin/messages/{id}/delete")
	public String deleteMessage(
			@PathVariable Long id,
			RedirectAttributes redirectAttributes) {

		if (!contactMessageRepository.existsById(id)) {
			redirectAttributes.addFlashAttribute(
				"errorMessage",
				"指定されたお問い合わせが見つかりません。"
			);

			return "redirect:/admin/messages";
		}

		contactMessageRepository.deleteById(id);

		redirectAttributes.addFlashAttribute(
			"successMessage",
			"お問い合わせを削除しました。"
		);

		return "redirect:/admin/messages";
	}
}
