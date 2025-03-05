document.addEventListener("DOMContentLoaded", function() {
    const toggleButton = document.getElementById("toggleSidebar");
    const closeButton = document.getElementById("closeSidebar");
    const sidebar = document.getElementById("sidebar");

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
});
