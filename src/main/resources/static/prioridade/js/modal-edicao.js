function abrirModalEdicao(idPrioridade, nome, valorObjetivo, valorInvestido, icone, topPrioridade) {
    topPrioridade = true;
    document.getElementById("prioridadeNome").textContent = nome;
    document.getElementById("prioridadeId").value = idPrioridade;

    document.getElementById("nome-edicao").value = nome;
    document.getElementById("valor-objetivo-edicao").value = valorObjetivo;
    document.getElementById("valor-investido-edicao").value = valorInvestido;
    document.getElementById("valorObjetivoReal-edicao").value = valorObjetivo;
    document.getElementById("valorInvestidoReal-edicao").value = valorInvestido;
    document.getElementById("icone-edicao").value = icone;

    document.getElementById("formEdicao").setAttribute("action", "/prioridades/edicao/" + idPrioridade);

    M.updateTextFields();

    var modal = M.Modal.getInstance(document.getElementById('modal-edicao'));
    modal.open();

    aplicarMascaraDinheiro();
}
