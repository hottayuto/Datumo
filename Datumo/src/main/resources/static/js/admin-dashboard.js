document.addEventListener("DOMContentLoaded", () => {
    const btn = document.querySelector(".account-btn");
    const menu = document.querySelector(".dropdown");

    btn.addEventListener("click", () => {
        menu.style.display = menu.style.display === "block" ? "none" : "block";
    });

    document.addEventListener("click", (e) => {
        if (!btn.contains(e.target) && !menu.contains(e.target)) {
            menu.style.display = "none";
        }
    });
});
