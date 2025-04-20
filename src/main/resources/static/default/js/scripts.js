document.addEventListener('DOMContentLoaded', function() {
        var modals = document.querySelectorAll('.modal');
        var selects = document.querySelectorAll("select");
        M.Modal.init(modals, {
            onOpenEnd: function () {
              aplicarMascaraDinheiro();
            }
          });
        M.FormSelect.init(selects);
    });
