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

    // 同じタブ内で選択したテーマを取得します。新しいタブでは未選択になります。
    const getSavedTheme = () => {
        try {
            return sessionStorage.getItem(storageKey);
        } catch (error) {
            return null;
        }
    };

    // ページ移動後も同じテーマを使えるよう、このタブの間だけ保存します。
    const saveTheme = (theme) => {
        try {
            sessionStorage.setItem(storageKey, theme);
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
            ? "モダンモード"
            : "クラシックモード";

        button.setAttribute(
            "aria-pressed",
            String(isClassic)
        );
    };

    // 新しくサイトを開いた場合は、モダンモードから開始します。
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

    // クラシックモードのタスクバーに、閲覧端末の現在時刻を表示します。
    const updateTaskbarClock = () => {
        const now = new Date();
        const currentTime = new Intl.DateTimeFormat("ja-JP", {
            hour: "2-digit",
            minute: "2-digit",
            hour12: false
        }).format(now);

        root.style.setProperty(
            "--taskbar-clock",
            `"${currentTime}"`
        );
    };

    updateTaskbarClock();
    window.setInterval(updateTaskbarClock, 1000);
})();
