document.addEventListener("DOMContentLoaded", function () {
    if (document.getElementById('myChart')) {
        chart_setup();
    } else {
        console.error("myChart 캔버스를 찾을 수 없음!");
    }
});

function chart_setup() {
    const ctx = document.getElementById('myChart').getContext('2d');

    const myChart = new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: ['이상발생', '작동중', '빙결예상', '기타'],
            datasets: [{
                label: '결과값',
                data: [1, 20, 3, 3],
                backgroundColor: [
                    'rgba(255,61,101,0.7)',
                    'rgba(34,183,0,0.7)',
                    'rgba(255, 206, 86, 0.7)',
                    'rgba(19,19,19,0.7)',
                ],
                borderWidth: 2
            }]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                legend: {
                    position: 'top', //
                    labels: { //
                        font: {
                            size: 20, //
                            weight: 'bold'
                        },
                        color: 'black' //
                    }
                },
                datalabels: {
                    color: 'black',  // 글자 색상
                    anchor: 'center', // 도넛 중앙에 배치
                    align: 'center',
                    font: {
                        weight: 'bold',
                        size: 15
                    },
                    formatter: (value) => `${value}개` // 데이터 값 표시 (예: "12개")
                }
            },
            cutout: '60%' // 도넛 내부 비율 설정
        },
        plugins: [ChartDataLabels] //ChartDataLabels 플러그인 활성화
    });

    return myChart;
}
