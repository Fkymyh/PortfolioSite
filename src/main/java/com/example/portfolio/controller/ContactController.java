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

@Controller
public class ContactController {

	private final ContactMessageRepository contactMessageRepository;

	public ContactController(
			ContactMessageRepository contactMessageRepository) {
		this.contactMessageRepository = contactMessageRepository;
	}

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

	@PostMapping("/contact")
	public String sendContact(
			@Valid @ModelAttribute ContactForm contactForm,
			BindingResult bindingResult,
			Model model,
			RedirectAttributes redirectAttributes) {

		model.addAttribute("currentPage", "contact");

		if (bindingResult.hasErrors()) {
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
