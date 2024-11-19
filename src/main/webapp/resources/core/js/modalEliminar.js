  const deleteButtons = document.querySelectorAll(".button-link.eliminar");
    const modal = document.getElementById("deleteModal");
    const confirmButton = document.getElementById("confirmDelete");
    const cancelButton = document.getElementById("cancelDelete");
    let formToSubmit = null;

    deleteButtons.forEach(button => {
        button.addEventListener("click", (event) => {
            event.preventDefault();
            formToSubmit = button.closest("form");
            modal.style.display = "flex";
        });
    });

    confirmButton.addEventListener("click", () => {
        if (formToSubmit) {
            formToSubmit.submit();
        }
    });

    cancelButton.addEventListener("click", () => {
        modal.style.display = "none";
    });

    window.addEventListener("click", (event) => {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    });