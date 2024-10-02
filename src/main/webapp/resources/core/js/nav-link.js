
     window.onload = function() {
        let storedDate = localStorage.getItem('selectedDate');

        if (storedDate) {
            document.getElementById('url-RegistrarAlimento').href = `/spring/ver-Registrar-alimentos?fecha=` + storedDate;
        }
    };