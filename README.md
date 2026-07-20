# 制作物紹介ポートフォリオ

これまでに制作したアプリと、職業訓練や個人学習で学んだ内容を紹介するポートフォリオサイトです。

Spring Bootの学習を目的として、画面遷移、フォーム処理、入力チェック、データベース保存、管理者認証などを実装しています。

## 制作目的

前職ではイベント現場に関わり、照明機材の設営や本番対応を経験しました。

その経験から、機材管理やステージレイアウトの共有を分かりやすくするアプリを制作しています。

このサイトでは制作物を並べるだけでなく、以下の内容が伝わることを目指しています。

- なぜ制作したのか
- どのような技術を使用したのか
- どこを工夫したのか
- どのような課題が残っているのか

## 紹介している制作物

### Stage Layout Designer

学生団体やイベント現場向けの、ステージ仕込み図作成アプリです。

- Java / Swing
- 機材の配置とドラッグ移動
- グリッド表示と吸着
- 保存・読み込み
- アプリとしてリリース済み

- [GitHubリポジトリ](https://github.com/Fkymyh/StageLayout-Designer)
- [最新版をダウンロード](https://github.com/Fkymyh/StageLayout-Designer/releases/latest)

### 照明機材管理アプリ

照明機材の管理を想定して制作したデスクトップアプリです。

- Java / Swing
- 機材情報の管理
- 機材の検索
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

### バックエンド

- Java 21
- Spring Boot 4.1
- Spring MVC
- Spring Security
- Spring Data JPA
- Bean Validation
- H2 Database

### フロントエンド

- HTML
- CSS
- Thymeleaf

### 開発環境・管理

- Eclipse
- Maven
- Git / GitHub

## 実装した機能

### 公開ページ

- ホーム画面
- このサイトについて
- 制作アプリ一覧
- 制作アプリごとの詳細ページ
- 学習記録
- パンくずリスト
- 現在ページの表示
- 共通ヘッダーとフッター
- 独自の404・500エラーページ
- スマートフォン表示への対応

### お問い合わせ

- お問い合わせフォーム
- 必須入力チェック
- メールアドレス形式チェック
- 件名とメッセージの文字数制限
- H2 Databaseへの保存
- 送信完了画面
- 画面更新による二重送信の防止

### 管理機能

- Spring Securityによるログイン認証
- 問い合わせ一覧
- 問い合わせ詳細表示
- 削除前の確認画面
- 問い合わせ削除
- ログアウト

管理画面は認証済みの管理者だけが閲覧できます。

## 画面構成

```text
ホーム
├ このサイトについて
├ アプリ紹介
│ ├ Stage Layout Designer
│ ├ 照明機材管理アプリ
│ └ 映画検索Webアプリ
├ 学習記録
└ お問い合わせ
   └ 送信完了

管理画面
├ ログイン
├ 問い合わせ一覧
├ 問い合わせ詳細
└ 削除確認
```

## プロジェクト構成

```text
src/main/java/com/example/portfolio
├ config
├ controller
├ entity
├ form
├ repository
└ PortfolioSiteApplication.java

src/main/resources
├ static
│ ├ css
│ └ images
├ templates
│ ├ error
│ ├ fragments.html
│ └ 各画面のHTML
├ application.properties
├ application-dev.properties
└ application-prod.properties
```

## 起動方法

### 必要な環境

- Java 21
- Maven、またはプロジェクト付属のMaven Wrapper
- 環境変数を設定できる実行環境

### 管理者パスワードの設定

起動前に、以下の環境変数を設定します。

```text
PORTFOLIO_ADMIN_PASSWORD
```

実際のパスワードは、設定ファイルやソースコードには保存しません。

### Eclipseから起動する場合

1. プロジェクトをEclipseへインポートします。
2. `PortfolioSiteApplication.java`を開きます。
3. `Run Configurations`を開きます。
4. 環境変数`PORTFOLIO_ADMIN_PASSWORD`を設定します。
5. 開発時はProgram argumentsへ以下を設定します。

```text
--spring.profiles.active=dev
```

6. `Run As`から`Spring Boot App`を実行します。
7. ブラウザで以下を開きます。

```text
http://localhost:8080/
```

### Maven Wrapperから起動する場合

Windowsでは、環境変数を設定してから次を実行します。

```powershell
$env:PORTFOLIO_ADMIN_PASSWORD="任意のパスワード"
.\mvnw.cmd spring-boot:run "-Dspring-boot.run.profiles=dev"
```

実際に使用するパスワードをGitHubへ登録しないでください。

## 開発用と公開用の設定

### devプロファイル

```text
--spring.profiles.active=dev
```

開発時のみ、以下を有効にします。

- H2 Console
- SQLログ

H2 Console：

```text
http://localhost:8080/h2-console
```

### prodプロファイル

```text
--spring.profiles.active=prod
```

公開用設定では、以下を無効にします。

- H2 Console
- SQLログ

H2 ConsoleとそのSecurity設定は、開発環境だけで有効になります。

## セキュリティ上の対応

- 管理画面をSpring Securityで保護
- 管理者パスワードを環境変数で管理
- 問い合わせ一覧・詳細・削除を管理者だけに制限
- 削除処理をPOSTで実行
- CSRF対策を有効化
- H2 Consoleを開発環境だけで有効化
- 外部リンクへ`noopener noreferrer`を設定
- DBファイルと問い合わせデータをGit管理から除外

## Git管理から除外するファイル

問い合わせ内容やローカルDBをGitHubへ登録しないよう、以下を除外しています。

```gitignore
data/
*.mv.db
*.trace.db
```

## 今後追加したい機能

- Web上への公開
- 本番環境向けデータベースへの移行
- 管理画面の検索機能
- 問い合わせの既読・未読管理
- アクセシビリティの改善
- クラシックモード

クラシックモードでは、2000年代の個人サイトやデスクトップUIをモチーフにしながら、現在の使いやすさやレスポンシブ対応を維持したデザインを検討しています。

## 参考資料

- 職業訓練で使用したJava、Servlet/JSP、Spring Frameworkの教材
- とほほのWWW入門
- Spring公式ドキュメント
- GitHub Docs

## 注意事項

このプロジェクトは現在、学習およびポートフォリオ用途で開発しています。

ローカルでH2 Databaseを使用しているため、Web公開時には本番環境向けのデータベースと保存方法を改めて設定する予定です。
