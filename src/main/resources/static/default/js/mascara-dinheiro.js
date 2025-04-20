function aplicarMascaraDinheiro() {
  document.querySelectorAll('.money-mask').forEach(function (input) {
    const cleave = new Cleave(input, {
      numeral: true,
      numeralThousandsGroupStyle: 'thousand',
      delimiter: '.',
      numeralDecimalMark: ',',
      numeralDecimalScale: 2,
      prefix: 'R$ ',
      noImmediatePrefix: false,
      rawValueTrimPrefix: true
    });

    let hiddenInput = input.nextElementSibling;
    const inputName = input.getAttribute('name');

    // Se houver valor inicial no input (ex: vindo do backend), aplicar máscara e sincronizar
    if (input.value && hiddenInput) {
      const raw = input.value
        .replace(/\s/g, '')
        .replace('R$', '')
        .replace(/\./g, '')
        .replace(',', '.');

      cleave.setRawValue(raw);
      hiddenInput.value = raw;
    }

    // Atualizar o campo hidden sempre que o usuário digitar
    input.addEventListener('input', function () {
      if (hiddenInput) {
        hiddenInput.value = cleave.getRawValue();
      }
    });
  });
}
