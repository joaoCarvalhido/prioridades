document.addEventListener('DOMContentLoaded', function() {
        var modals = document.querySelectorAll('.modal');
        var selects = document.querySelectorAll("select");
        M.Modal.init(modals);
        M.FormSelect.init(selects);
    });
