package com.example.portfolio.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.portfolio.entity.ContactMessage;
import com.example.portfolio.form.ContactForm;
import com.example.portfolio.repository.ContactMessageRepository;

/**
 * お問い合わせフォームの表示、検証、保存、完了画面への遷移を担当します。
 */
@Controller
public class ContactController {

	private final ContactMessageRepository contactMessageRepository;

	public ContactController(
			ContactMessageRepository contactMessageRepository) {
		this.contactMessageRepository = contactMessageRepository;
	}

	/** 入力前のお問い合わせフォームを表示します。 */
	@GetMapping("/contact")
	public String contact(Model model) {
		model.addAttribute("pageTitle", "お問い合わせ");
		model.addAttribute("currentPage", "contact");
		model.addAttribute(
			"breadcrumbs",
			List.of("ホーム", "お問い合わせ")
		);
		model.addAttribute(
			"contactForm",
			new ContactForm()
		);

		return "contact";
	}

	/** 入力値を検証し、問題がなければお問い合わせをDBへ保存します。 */
	@PostMapping("/contact")
	public String sendContact(
			@Valid @ModelAttribute ContactForm contactForm,
			BindingResult bindingResult,
			Model model,
			RedirectAttributes redirectAttributes) {

		model.addAttribute("currentPage", "contact");

		if (bindingResult.hasErrors()) {
			// 入力内容を保持したままフォームへ戻し、エラーを表示します。
			model.addAttribute(
				"pageTitle",
				"お問い合わせ"
			);
			model.addAttribute(
				"breadcrumbs",
				List.of("ホーム", "お問い合わせ")
			);

			return "contact";
		}

		ContactMessage contactMessage = new ContactMessage();
		// 画面入力用フォームの値を、永続化するEntityへ詰め替えます。
		contactMessage.setName(contactForm.getName());
		contactMessage.setEmail(contactForm.getEmail());
		contactMessage.setSubject(contactForm.getSubject());
		contactMessage.setMessage(contactForm.getMessage());

		contactMessageRepository.save(contactMessage);

		redirectAttributes.addFlashAttribute(
			"contactName",
			contactForm.getName()
		);

		return "redirect:/contact/thanks";
	}

	/** PRGパターンでリダイレクトされた送信完了画面を表示します。 */
	@GetMapping("/contact/thanks")
	public String contactThanks(Model model) {
		model.addAttribute("pageTitle", "送信完了");
		model.addAttribute("currentPage", "contact");
		model.addAttribute(
			"breadcrumbs",
			List.of(
				"ホーム",
				"お問い合わせ",
				"送信完了"
			)
		);

		return "thanks";
	}
}
