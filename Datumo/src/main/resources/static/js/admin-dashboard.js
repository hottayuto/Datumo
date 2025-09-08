document.addEventListener("DOMContentLoaded", () => {
    // --- 既存のスタッフ編集処理 ---
    window.enableEdit = (rowId) => {
        const row = document.getElementById("row-" + rowId);
        if (!row) return;

        // 編集前の値を保持（キャンセル用）
        const originalValues = {};
        const editableCells = row.querySelectorAll(".editable");

        editableCells.forEach(td => {
            originalValues[td.getAttribute("data-field")] = td.innerText.trim();

            const text = td.innerText.trim();
            const field = td.getAttribute("data-field");

            if (field === "role") {
                td.innerHTML = `
                    <select name="role" class="edit-input" required>
                        <option value="ADMIN" ${text === "ADMIN" ? "selected" : ""}>管理者</option>
                        <option value="STAFF" ${text === "STAFF" ? "selected" : ""}>スタッフ</option>
                    </select>
                `;
            } else if (field === "userId") {
                td.innerHTML = `
                    ${text}
                    <input type="hidden" name="userId" value="${text}">
                `;
            } else {
                td.innerHTML = `<input type="text" name="${field}" value="${text}" class="edit-input" required>`;
            }
        });

        // 操作列に更新・キャンセルボタンを追加
        const actionCell = row.querySelector(".actions");
        const originalActionHTML = actionCell.innerHTML;

        actionCell.innerHTML = `
            <button type="button" class="btn update-btn">更新</button>
            <button type="button" class="btn cancel-btn">キャンセル</button>
        `;

        // 更新ボタン
        actionCell.querySelector(".update-btn").addEventListener("click", () => {
            const form = document.createElement("form");
            form.method = "post";
            form.action = `/admin/staff/update/${rowId}`;

            editableCells.forEach(td => {
                const input = td.querySelector("input, select");
                if (input) {
                    const cloned = input.cloneNode(true);
                    if (cloned.tagName === "SELECT") cloned.value = input.value;
                    form.appendChild(cloned);
                }
            });

            document.body.appendChild(form);
            form.submit();
        });

        // キャンセルボタン
        actionCell.querySelector(".cancel-btn").addEventListener("click", () => {
            editableCells.forEach(td => {
                const field = td.getAttribute("data-field");
                td.innerText = originalValues[field];
            });
            actionCell.innerHTML = originalActionHTML;
        });
    };

    // --- アカウントプルダウン用追加 ---
    const accountBtn = document.querySelector(".account-btn");
    const dropdown = document.querySelector(".account-menu .dropdown");

    if (accountBtn && dropdown) {
        accountBtn.addEventListener("click", (e) => {
            e.stopPropagation(); // イベントバブリング防止
            dropdown.style.display = dropdown.style.display === "block" ? "none" : "block";
        });

        // 他の場所クリックで閉じる
        document.addEventListener("click", () => {
            dropdown.style.display = "none";
        });
    }
});
