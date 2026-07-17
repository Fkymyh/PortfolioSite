package com.example.portfolio.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.portfolio.form.ContactForm;

@Controller
public class PageController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("pageTitle", "制作物紹介ポートフォリオ");
        model.addAttribute("breadcrumbs", List.of("ホーム"));
        return "index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("pageTitle", "このサイトについて");
        model.addAttribute("breadcrumbs", List.of("ホーム", "このサイトについて"));
        return "about";
    }

    @GetMapping("/apps")
    public String apps(Model model) {
        model.addAttribute("pageTitle", "アプリ紹介");
        model.addAttribute("breadcrumbs", List.of("ホーム", "アプリ紹介"));
        return "apps";
    }

    @GetMapping("/apps/stage-layout")
    public String stageLayout(Model model) {
        model.addAttribute("pageTitle", "Stage Layout Designer");
        model.addAttribute("breadcrumbs", List.of("ホーム", "アプリ紹介", "Stage Layout Designer"));
        return "app-stage-layout";
    }

    @GetMapping("/learning")
    public String learning(Model model) {
        model.addAttribute("pageTitle", "学習記録");
        model.addAttribute("breadcrumbs", List.of("ホーム", "学習記録"));
        return "learning";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("pageTitle", "お問い合わせ");
        model.addAttribute("breadcrumbs", List.of("ホーム", "お問い合わせ"));
        model.addAttribute("contactForm", new ContactForm());
        return "contact";
    }
    @PostMapping("/contact")
    public String sendContact(
    			@ModelAttribute ContactForm contactForm,
    			Model model) {
    	
    			model.addAttribute("pageTitle", "送信完了");
    			model.addAttribute("breadcrumbs", List.of("ホーム", "お問い合わせ", "送信完了"));
    			model.addAttribute("contactForm", contactForm);
    			
    			return "thanks";
    }
}
