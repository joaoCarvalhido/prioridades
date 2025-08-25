function aplicarMascaraDinheiro() {
  document.querySelectorAll('.money-mask').forEach(function (input) {
    const hiddenInputId = input.getAttribute('data-hidden-target');
    const hiddenInput = hiddenInputId ? document.getElementById(hiddenInputId) : input.nextElementSibling;

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

    if (hiddenInput && hiddenInput.value) {
      let numero = parseFloat(hiddenInput.value.replace(',', '.'));
      if (!isNaN(numero)) {
        input.value = cleave.properties.prefix + numero.toFixed(2).replace('.', ',');
        cleave.setRawValue(numero.toFixed(2));
      }
    }

    input.addEventListener('input', function () {
      if (hiddenInput) {
        let raw = cleave.getRawValue();
        hiddenInput.value = (parseFloat(raw.replace(',', '.')) || 0).toFixed(2);
      }
    });
  });
}
