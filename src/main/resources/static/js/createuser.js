    function createuser() {
        const userid = document.getElementById('userid').value;
        const pass = document.getElementById('pass').value;
        const name = document.getElementById('name').value;
        const selectedValue = document.querySelector('input[name="permission"]:checked').value;
        const company = document.getElementById('company').value;
        const notice = document.getElementById('notice').value;

        const csrfToken = document.querySelector('meta[name="csrf-token"]').getAttribute('content');

        if (userid === "" || pass === "" || name === "" || selectedValue === "" || company === "") {
            alert("계정 정보가 입력되지 않았습니다.")
            return;
        }

        // JSON으로 묶기
        const userData = {
            userid: userid,
            pass: pass,
            name: name,
            permission: selectedValue, // "permission" 필드 이름 수정 가능
            company: company,
            notice: notice
        };

        // fetch로 REST API 요청
        fetch('/api/createuser', {
            method: 'POST',  // HTTP 메서드 설정 (POST 요청)
            headers: {
                'Content-Type': 'application/json',  // JSON 형식으로 보낼 것을 명시
                'X-CSRF-TOKEN': csrfToken // CSRF 토큰 설정
            },
            body: JSON.stringify(userData)  // JSON 데이터로 변환하여 전송
        })
            .then(response => {
                if (!response.ok) {
                    return response.json().then(errData => {
                        throw new Error(errData.error2 || errData.error || '서버 응답 오류!');
                    });
                }
                return response.json(); // JSON 응답 파싱
            })
            .then(data => {
                console.log('서버 응답:', data);
                alert('사용자 생성 성공!');
            })
            .catch(error => {
                console.error('에러 발생:', error);
                alert(error.message);
            });
    }

    function reset() {
        const userid = document.getElementById('userid')
        const pass = document.getElementById('pass')
        const name = document.getElementById('name')
        const selectedValue = document.getElementById('ra1');
        const selectedValue2 = document.getElementById('ra2');
        const company = document.getElementById('company')
        const notice = document.getElementById('notice')

        userid.value = "";
        pass.value = "";
        name.value = "";
        selectedValue.checked = true;
        selectedValue2.checked = false;
        company.value = "";
        notice.value = "";
    }