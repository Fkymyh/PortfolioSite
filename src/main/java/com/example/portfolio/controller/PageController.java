package com.example.portfolio.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * ポートフォリオの公開ページへの画面遷移を担当します。
 */
@Controller
public class PageController {

	/** ホーム画面を表示します。 */
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("pageTitle", "制作物紹介ポートフォリオ");
		model.addAttribute("currentPage", "home");
		model.addAttribute("breadcrumbs", List.of("ホーム"));
		return "index";
	}

	/** サイトの目的や制作背景を紹介する画面を表示します。 */
	@GetMapping("/about")
	public String about(Model model) {
		model.addAttribute("pageTitle", "このサイトについて");
		model.addAttribute("currentPage", "about");
		model.addAttribute("breadcrumbs", List.of("ホーム", "このサイトについて"));
		return "about";
	}

	/** 制作アプリの一覧画面を表示します。 */
	@GetMapping("/apps")
	public String apps(Model model) {
		model.addAttribute("pageTitle", "アプリ紹介");
		model.addAttribute("currentPage", "apps");
		model.addAttribute("breadcrumbs", List.of("ホーム", "アプリ紹介"));
		return "apps";
	}

	/** Stage Layout Designerの詳細画面を表示します。 */
	@GetMapping("/apps/stage-layout")
	public String stageLayout(Model model) {
		model.addAttribute("pageTitle", "Stage Layout Designer");
		model.addAttribute("currentPage", "apps");
		model.addAttribute("breadcrumbs", List.of("ホーム", "アプリ紹介", "Stage Layout Designer"));
		return "app-stage-layout";
	}
	/** 照明機材管理アプリの詳細画面を表示します。 */
	@GetMapping("/apps/lighting-management")
	public String lightingManagement(Model model) {
		model.addAttribute("pageTitle", "照明機材管理アプリ");
		model.addAttribute("currentPage", "apps");
		model.addAttribute(
				"breadcrumbs",
				List.of(
						"ホーム",
						"アプリ紹介",
						"照明機材管理アプリ"));
		return "app-lighting-management";
	}
	/** 映画検索Webアプリの詳細画面を表示します。 */
	@GetMapping("/apps/movie-manager")
	public String movieManager(Model model) {
		model.addAttribute("pageTitle", "映画検索Webアプリ");
		model.addAttribute("currentPage", "apps");
		model.addAttribute(
				"breadcrumbs",
				List.of(
						"ホーム",
						"アプリ紹介",
						"映画検索Webアプリ"));
		return "app-movie-manager";
	}

	/** 学習内容と制作物での活用例を表示します。 */
	@GetMapping("/learning")
	public String learning(Model model) {
		model.addAttribute("pageTitle", "学習記録");
		model.addAttribute("currentPage", "learning");
		model.addAttribute("breadcrumbs", List.of("ホーム", "学習記録"));
		return "learning";
	}
}
