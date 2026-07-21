(() => {
    // 選択中のテーマをブラウザに保存するためのキーです。
    const storageKey = "portfolio-theme";
    const root = document.documentElement;

    const button = document.querySelector(
        "[data-theme-toggle]"
    );

    if (!button) {
        return;
    }

    // 保存済みのテーマを取得します。利用できない環境ではnullを返します。
    const getSavedTheme = () => {
        try {
            return localStorage.getItem(storageKey);
        } catch (error) {
            return null;
        }
    };

    // 次回の表示でも同じテーマを使えるように保存します。
    const saveTheme = (theme) => {
        try {
            localStorage.setItem(storageKey, theme);
        } catch (error) {
            // 保存できなくてもテーマ切り替えは続ける
        }
    };

    // data-themeとボタン表示をまとめて更新します。
    const applyTheme = (theme) => {
        const isClassic = theme === "classic";

        root.dataset.theme = isClassic
            ? "classic"
            : "modern";

        button.textContent = isClassic
            ? "通常モード"
            : "クラシックモード";

        button.setAttribute(
            "aria-pressed",
            String(isClassic)
        );
    };

    // 初回表示時は保存値を使い、未保存なら通常モードにします。
    const savedTheme = getSavedTheme();

    applyTheme(
        savedTheme === "classic"
            ? "classic"
            : "modern"
    );

    // ボタンを押すたびに通常・クラシックを切り替えます。
    button.addEventListener("click", () => {
        const nextTheme =
            root.dataset.theme === "classic"
                ? "modern"
                : "classic";

        applyTheme(nextTheme);
        saveTheme(nextTheme);
    });
})();
