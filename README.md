# 制作物紹介ポートフォリオ

これまでに制作したアプリと、職業訓練や個人学習で学んだ内容を紹介するポートフォリオサイトです。

Spring Bootの学習課題として、画面遷移、フォーム処理、入力チェック、Thymeleafによる共通部品化などを実装しています。

## 制作目的

前職ではイベント現場に関わり、照明機材の設営や本番対応を経験しました。

その経験から、機材管理やステージレイアウトの共有を分かりやすくするアプリを制作しています。

このサイトでは、制作物を並べるだけでなく、制作背景、使用技術、工夫した点、今後の改善点が伝わることを目指しています。

## 紹介している制作物

### Stage Layout Designer

学生団体やイベント現場向けの、ステージ仕込み図作成アプリです。

- Java / Swing
- 機材の配置とドラッグ移動
- グリッド表示と吸着
- 保存・読み込み
- アプリとしてリリース済み

[GitHubリポジトリ](https://github.com/Fkymyh/StageLayout-Designer)

### 照明機材管理アプリ

照明機材の管理を想定して制作したデスクトップアプリです。

- Java / Swing
- 機材情報の管理
- 検索
- 現在はソースコードを公開中

[GitHubリポジトリ](https://github.com/Fkymyh/LightingManagementGUI)

### 映画検索Webアプリ

映画検索APIを利用したWebアプリです。

- Python / Flask
- 映画情報の検索
- お気に入り管理
- ローカル環境で起動して利用可能

[GitHubリポジトリ](https://github.com/Fkymyh/movie-manager)

## 使用技術

- Java
- Spring Boot
- Spring MVC
- Thymeleaf
- Bean Validation
- HTML
- CSS
- Maven
- Git / GitHub

## 実装した機能

- 制作物の一覧表示
- 制作物ごとの詳細ページ
- パンくずリスト
- 現在ページの表示
- 共通ヘッダーとフッター
- お問い合わせフォーム
- フォームの入力チェック
- 送信内容の確認画面
- スマートフォンを考慮した表示

## 画面構成

- ホーム
- このサイトについて
- アプリ紹介
- アプリ詳細
- 学習記録
- お問い合わせ
- 送信完了

## 起動方法

### 必要な環境

- Java 17以上
- Mavenまたは付属のMaven Wrapper

### Eclipseから起動する場合

1. プロジェクトをEclipseにインポートします。
2. `PortfolioSiteApplication.java`を開きます。
3. `Run As`から`Spring Boot App`を選択します。
4. ブラウザで以下を開きます。

```text
http://localhost:8080/
