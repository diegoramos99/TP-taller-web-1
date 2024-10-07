// script.js

document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('multiStepForm');
    const formSteps = Array.from(document.querySelectorAll('.form-step'));
    const nextButtons = document.querySelectorAll('.next-button');
    const prevButtons = document.querySelectorAll('.prev-button');
    const breadcrumbs = Array.from(document.querySelectorAll('.breadcrumb'));
    let currentStep = 0;

    // Mostrar el paso actual
    function showStep(step) {
        formSteps.forEach((formStep, index) => {
            formStep.classList.toggle('active', index === step);
        });
        breadcrumbs.forEach((breadcrumb, index) => {
            breadcrumb.classList.toggle('active', index <= step);
        });
    }

    // Validar los campos del paso actual
    function validateStep(step) {
        const currentFormStep = formSteps[step];
        const inputs = currentFormStep.querySelectorAll('input, select');
        let valid = true;

        inputs.forEach(input => {
            if (!input.checkValidity()) {
                input.reportValidity();
                valid = false;
            }
        });

        return valid;
    }

    // Manejar clic en botón "Siguiente"
    nextButtons.forEach(button => {
        button.addEventListener('click', () => {
            if (validateStep(currentStep)) {
                currentStep++;
                if (currentStep >= formSteps.length) {
                    currentStep = formSteps.length - 1;
                }
                showStep(currentStep);
            }
        });
    });

    // Manejar clic en botón "Anterior"
    prevButtons.forEach(button => {
        button.addEventListener('click', () => {
            currentStep--;
            if (currentStep < 0) {
                currentStep = 0;
            }
            showStep(currentStep);
        });
    });

    // Manejar envío del formulario
    form.addEventListener('submit', (e) => {
        e.preventDefault();
        if (validateStep(currentStep)) {
            // Aquí puedes procesar los datos del formulario
            // Por ejemplo, enviar a un servidor o mostrar un resumen
            alert('Formulario enviado correctamente.');
            form.reset();
            currentStep = 0;
            showStep(currentStep);
        }
    });

    // Inicializar el formulario mostrando el primer paso
    showStep(currentStep);
});
