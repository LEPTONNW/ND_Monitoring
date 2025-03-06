document.addEventListener("DOMContentLoaded", function() {
    const toggleButton = document.getElementById("toggleSidebar");
    const closeButton = document.getElementById("closeSidebar");
    const sidebar = document.getElementById("sidebar");
    const closeMenu = document.getElementById("close_menu");

    if (toggleButton && sidebar) {
        // 버튼 클릭 시 사이드바 열기/닫기
        toggleButton.addEventListener("click", function(event) {
            sidebar.classList.toggle("active");
            event.stopPropagation(); // 이벤트 버블링 방지
        });
    }

    if (closeButton) {
        // 닫기 버튼 클릭 시 사이드바 숨기기
        closeButton.addEventListener("click", function(event) {
            sidebar.classList.remove("active");
            event.stopPropagation(); // 이벤트 버블링 방지
        });
    }

    if (closeMenu) { // ✅ 추가된 코드 (id="close_menu" 클릭 시 사이드바 닫기)
        closeMenu.addEventListener("click", function(event) {
            sidebar.classList.remove("active");
            event.stopPropagation();
        });
    }



    // 바깥 클릭 시 사이드바 닫기
    document.addEventListener("click", function(event) {
        if (
            sidebar.classList.contains("active") && // 사이드바가 열려 있을 때만 실행
            !sidebar.contains(event.target) && // 사이드바 내부를 클릭하지 않았을 때
            !toggleButton.contains(event.target) // 버튼을 클릭한 것이 아닐 때 (아이콘 포함)
        ) {
            sidebar.classList.remove("active");
        }
    });



    window.onload = function () {
        load_layout();
    }

    function load_layout() {
        const userid = document.getElementById('layout_userid').value;
        const lay_username = document.getElementById('layout_username');
        const lay_company = document.getElementById('layout_company');


        fetch(`/api/load_layout?userid=${userid}`)
            .then(response => response.json())
            .then(data => {
                lay_username.textContent = data.name;
                lay_company.textContent = "소 속 : " + data.company;
            })
            .catch(error => {
                console.error("Error : 데이터 로딩 실패");
            });
    }
});
