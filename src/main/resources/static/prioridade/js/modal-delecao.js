function abrirModalConfirmacaoDelecao(idPrioridade) {
    document.getElementById("prioridadeId").value = idPrioridade;

    document.getElementById("formDelecao").setAttribute("action", "/prioridades/delecao/" + idPrioridade);

    const modal = M.Modal.getInstance(document.getElementById('modal-confirma-delecao'));
    if (modal) {
        modal.open();
    }
}