document.getElementById("cep").addEventListener("blur", function () {
  const cep = this.value.replace(/\D/g, "");

  if (cep.length !== 8) {
    alert("CEP inválido. Deve conter 8 números.");
    return;
  }

  fetch(`https://viacep.com.br/ws/${cep}/json/`)
    .then((response) => {
      if (!response.ok) throw new Error("Erro na requisição");
      return response.json();
    })
    .then((data) => {
      if (data.erro) {
        alert("CEP não encontrado.");
        return;
      }

      // Preenche os campos
      document.getElementById("logradouro").value = data.logradouro;
      document.getElementById("bairro").value = data.bairro;
      document.getElementById("cidade").value = data.localidade;
      document.getElementById("uf").value = data.uf;
      document.getElementById("complemento").value = data.complemento;
    })
    .catch((error) => {
      console.error("Erro ao buscar CEP:", error);
      alert("Erro ao buscar o CEP.");
    });
});

document.addEventListener("DOMContentLoaded", () => {
  const inputElement = document.getElementById("valorHonorario");

  if (inputElement) {
    // Usa o construtor da AutoNumeric com as configurações diretas
    new AutoNumeric(inputElement, {
      digitGroupSeparator: ".", // Ponto como separador de milhares (BRL - Visual)
      decimalCharacter: ",", // Vírgula como separador decimal (BRL - Visual)
      //currencySymbol: "R$", // Símbolo da moeda
      currencySymbolPlacement: "p", // Prefix (antes do valor)
      minimumValue: "0.00", // Valor mínimo permitido
      maximumValue: "1000000.00", // Valor máximo permitido
      decimalPlaces: 2, // 2 casas decimais
      // SOLUÇÃO 1: Garante que apenas o número seja enviado
      rawValueTrimPrefix: true,
    });
  }
});
