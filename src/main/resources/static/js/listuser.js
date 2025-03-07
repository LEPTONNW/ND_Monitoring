document.addEventListener("DOMContentLoaded", function () {
    loadUserList(0, "", ""); // 페이지 로딩 시 기본 리스트 불러오기
});

searchButton.addEventListener("click", function () {
    const searchType = document.querySelector(".form-select").value; // 선택된 검색 타입
    const keyword = document.querySelector(".form-control").value.trim(); // 검색어
    const encodedKeyword = encodeURIComponent(keyword);
    loadUserList(0, searchType, encodedKeyword); // 검색 적용
});

function loadUserList(page = 0, searchType = "", keyword ="") {
    let url = `/api/userlist?page=${page}&size=10`;
    if(searchType && keyword){
        url+=`&searchType=${searchType}&keyword=${keyword}`;
    }
    fetch(url, { cache: "no-store" })
        .then(response => response.json())
        .then(data => {
            setTimeout(() => {
                const tableBody = document.getElementById('boardTableBody');
                if (tableBody) tableBody.innerHTML = ""; // 기존 내용 초기화
                // 리스트 데이터 받아와서
                data.content.forEach((item, index) => {
                    const row = document.createElement("tr");
                    row.innerHTML = `
                        <td>${index + 1 + page * 10}</td>
                        <td><a href="/users/userslist?uno=${item.uno}&userid=${item.userid}">${item.userid}</a></td>
                        <td>${item.name}</td>
                        <td>${item.company}</td>
                        <td>${item.permission}</td>
                        <td>${item.regdate}</td>
                        <td>${item.notice}</td>
                    `;
                    tableBody.appendChild(row);
                });

                // 페이지네이션 버튼 생성
                if (data.totalPages) {
                    renderPagination(data.totalPages, page, searchType, keyword);
                }

                // 페이지 상단으로 부드럽게 이동
                window.scrollTo({ top: 0, behavior: 'smooth' });

            }, 200);
        })
        .catch(error => {
            console.error("Error : 데이터 로딩 실패", error);
        });
}
<!--유저 검색-->
function searchUserList() {
    const searchType = document.querySelector(".form-select").value;
    const keyword = document.querySelector(".form-control").value.trim();

    loadUserList(0, searchType, keyword);
}
<!---->
function renderPagination(totalPages, currentPage) {
    const paginationContainer = document.getElementById('pagination');
    if (!paginationContainer) return; // paginationContainer가 없으면 종료

    paginationContainer.innerHTML = ""; // 기존 버튼 초기화
    const pagesPerGroup = 5; // 그룹당 페이지 수는 5
    const currentGroup = Math.floor(currentPage / pagesPerGroup); // 현재 페이지 그룹
    const startPage = currentGroup * pagesPerGroup; // 시작 페이지
    const endPage = Math.min(startPage + pagesPerGroup, totalPages); // 현재 그룹 마지막 페이지

    // '이전' 버튼 생성
    if (startPage > 0) {
        const prevButton = createPageButton('이전', startPage - 1);
        paginationContainer.appendChild(prevButton);
    }

    // 페이지 버튼 생성
    for (let i = startPage; i < endPage; i++) {
        const button = createPageButton(i + 1, i);
        button.disabled = i === currentPage; // 현재 페이지는 비활성화
        paginationContainer.appendChild(button);
    }

    // '다음' 버튼 생성
    if (endPage < totalPages) {
        const nextButton = createPageButton('다음', endPage);
        paginationContainer.appendChild(nextButton);
    }
}

function createPageButton(text, page) {
    const button = document.createElement('button');
    button.innerText = text;
    button.classList.add("btn", "btn-default", "page-btn");
    button.onmouseover = function () {
        this.style.backgroundColor = "grey";
    };

    button.onmouseout = function () {
        this.style.backgroundColor = "transparent";
    };
    button.style.color = "black";
    button.style.margin = "0 5px";
    button.onclick = () => loadUserList(page);

    return button;
}
