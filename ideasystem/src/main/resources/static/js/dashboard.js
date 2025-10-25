const ctx = document.getElementById('meuGrafico').getContext('2d');
 
new Chart(ctx, {
type: 'line',
data: {
    labels: ['1','2','3','4','5','6'],
    datasets: [{
        label: 'Entradas',
        data: [0.1,0.3,0.2,0.5,0.4,0.6],
        borderColor: 'green',
        backgroundColor: 'rgba(0,128,0,0.1)',
        fill: true,
        tension: 0.1
    }]
},
options: {
    responsive: true,
    maintainAspectRatio: true, // usa a altura fixa do canvas
    plugins: {
        legend: { display: false }
    },
    scales: {
        y: {
            beginAtZero: true,
            ticks: {
                // Formata o eixo Y para se parecer com R$
                callback: function(value, index, values) {
                    return 'R$ ' + value.toFixed(2);
                }
            }
        },
        x: {
            grid: {
                display: false // Esconde as linhas de grade do eixo X
                }
            }
        }
    }
});