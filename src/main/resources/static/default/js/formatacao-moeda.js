// formatacao-moeda.js - Funções para formatação de valores monetários

/**
 * Formata um valor numérico para o formato de moeda brasileira
 * @param {number|string} valor - O valor a ser formatado
 * @returns {string} - Valor formatado como "R$ 1.234,56"
 */
function formatarMoedaBrasil(valor) {
    if (valor === null || valor === undefined || valor === '') {
        return 'R$ 0,00';
    }
    
    // Converte para número se for string
    const numero = typeof valor === 'string' ? parseFloat(valor.replace(',', '.')) : valor;
    
    if (isNaN(numero)) {
        return 'R$ 0,00';
    }
    
    // Usa Intl.NumberFormat para formatação
    return new Intl.NumberFormat('pt-BR', {
        style: 'currency',
        currency: 'BRL'
    }).format(numero);
}

/**
 * Formata um valor usando o mesmo padrão do Cleave.js
 * @param {number|string} valor - O valor a ser formatado
 * @returns {string} - Valor formatado como "R$ 1.234,56"
 */
function formatarMoedaCleave(valor) {
    if (valor === null || valor === undefined || valor === '') {
        return 'R$ 0,00';
    }
    
    const numero = typeof valor === 'string' ? parseFloat(valor.replace(',', '.')) : valor;
    
    if (isNaN(numero)) {
        return 'R$ 0,00';
    }
    
    // Converte para string com 2 casas decimais
    const valorStr = numero.toFixed(2);
    
    // Separa parte inteira e decimal
    const [parteInteira, parteDecimal] = valorStr.split('.');
    
    // Adiciona separadores de milhares
    const parteInteiraFormatada = parteInteira.replace(/\B(?=(\d{3})+(?!\d))/g, '.');
    
    return `R$ ${parteInteiraFormatada},${parteDecimal}`;
}

/**
 * Aplica formatação de moeda a todos os elementos com a classe 'valor-moeda'
 * Os elementos devem ter o atributo 'data-valor' com o valor numérico
 */
function formatarValoresMonetarios() {
    const elementos = document.querySelectorAll('.valor-moeda');
    
    elementos.forEach(elemento => {
        const valor = elemento.getAttribute('data-valor');
        if (valor) {
            elemento.textContent = formatarMoedaBrasil(valor);
        }
    });
}

/**
 * Formata valores monetários usando uma abordagem mais específica
 * para compatibilidade com o Cleave.js existente
 */
function formatarValoresMonetariosCleave() {
    const elementos = document.querySelectorAll('.valor-moeda');
    
    elementos.forEach(elemento => {
        const valor = elemento.getAttribute('data-valor');
        if (valor) {
            elemento.textContent = formatarMoedaCleave(valor);
        }
    });
}

/**
 * Função para formatar um elemento específico
 * @param {string} elementId - ID do elemento a ser formatado
 * @param {number|string} valor - Valor a ser formatado
 */
function formatarElementoMoeda(elementId, valor) {
    const elemento = document.getElementById(elementId);
    if (elemento) {
        elemento.textContent = formatarMoedaBrasil(valor);
    }
}

/**
 * Versão alternativa que usa o mesmo padrão do seu mascara-dinheiro.js
 * para manter consistência visual
 */
function aplicarFormatacaoMoedaTexto() {
    // Busca elementos que têm valores monetários para formatar
    const elementosValor = document.querySelectorAll('[data-valor-moeda]');
    
    elementosValor.forEach(elemento => {
        const valor = elemento.getAttribute('data-valor-moeda');
        if (valor && valor.trim() !== '') {
            // Converte o valor para número
            const numero = parseFloat(valor.replace(',', '.'));
            
            if (!isNaN(numero)) {
                // Cria um input temporário para usar o Cleave
                const inputTemp = document.createElement('input');
                inputTemp.style.display = 'none';
                document.body.appendChild(inputTemp);
                
                // Aplica o Cleave no input temporário
                const cleave = new Cleave(inputTemp, {
                    numeral: true,
                    numeralThousandsGroupStyle: 'thousand',
                    delimiter: '.',
                    numeralDecimalMark: ',',
                    numeralDecimalScale: 2,
                    prefix: 'R$ ',
                    noImmediatePrefix: false,
                    rawValueTrimPrefix: true
                });
                
                // Define o valor e pega o resultado formatado
                cleave.setRawValue(numero.toFixed(2));
                elemento.textContent = inputTemp.value;
                
                // Remove o input temporário
                document.body.removeChild(inputTemp);
            }
        }
    });
}

