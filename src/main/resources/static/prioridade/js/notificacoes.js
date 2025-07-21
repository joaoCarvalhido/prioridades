// notificacoes.js - Script para exibir notificações usando Materialize Toast

function verificarMensagemErro() {
    const mensagemErro = document.getElementById('mensagem-erro');
    const tipoErro = document.getElementById('tipo-erro');

    if (mensagemErro && mensagemErro.value && mensagemErro.value.trim() !== '') {
        const mensagem = mensagemErro.value;
        const tipo = tipoErro ? tipoErro.value : 'erro';

        exibirNotificacao(mensagem, tipo);
    }
}

function exibirNotificacao(mensagem, tipo = 'erro') {
    let classes = '';
    let icone = '';
    let duracao = 4000; // 4 segundos

    switch (tipo) {
        case 'limite_top_prioridade_2':
            classes = 'red darken-1 white-text rounded';
            icone = '<i class="material-icons left">warning</i>';
            duracao = 3000; // 3 segundos para este tipo específico
            break;
        case 'limite_top_prioridade':
            classes = 'orange darken-1 white-text rounded';
            icone = '<i class="material-icons left">info</i>';
            break;
        case 'erro':
        default:
            classes = 'red darken-1 white-text';
            icone = '<i class="material-icons left">error</i>';
            break;
    }

    // Cria o HTML do toast com ícone
    const htmlToast = icone + mensagem;

    // Exibe o toast do Materialize
    M.toast({
        html: htmlToast,
        classes: classes,
        displayLength: duracao,
        completeCallback: function() {
            console.log('Toast fechado');
        }
    });
}