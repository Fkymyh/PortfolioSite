(() => {
    const storageKey = "portfolio-theme";
    const root = document.documentElement;

    const button = document.querySelector(
        "[data-theme-toggle]"
    );

    if (!button) {
        return;
    }

    const getSavedTheme = () => {
        try {
            return localStorage.getItem(storageKey);
        } catch (error) {
            return null;
        }
    };

    const saveTheme = (theme) => {
        try {
            localStorage.setItem(storageKey, theme);
        } catch (error) {
            // 保存できなくてもテーマ切り替えは続ける
        }
    };

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

    const savedTheme = getSavedTheme();

    applyTheme(
        savedTheme === "classic"
            ? "classic"
            : "modern"
    );

    button.addEventListener("click", () => {
        const nextTheme =
            root.dataset.theme === "classic"
                ? "modern"
                : "classic";

        applyTheme(nextTheme);
        saveTheme(nextTheme);
    });
})();