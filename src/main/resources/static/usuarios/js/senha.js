document.addEventListener('DOMContentLoaded', function() {
        // Inicializar MaterializeCSS
        M.updateTextFields();

        // Validação de senha
        const senha = document.getElementById('senha');
        const confirmarSenha = document.getElementById('confirmar-senha');

        function validarSenhas() {
            if (senha.value !== confirmarSenha.value) {
                confirmarSenha.setCustomValidity('As senhas não coincidem');
            } else {
                confirmarSenha.setCustomValidity('');
            }
        }

        senha.addEventListener('input', validarSenhas);
        confirmarSenha.addEventListener('input', validarSenhas);
}