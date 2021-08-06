var userId = '';
var nowArticleId = '';
const filterOption = {type:'all', value:null}; 
const pageSize = 5;

$(document).ready(function(){
    // 로그인
    $('#login').on('click', function(){
        userId = window.prompt("아이디를 입력하세요", "ssafy");
        let userPwd = window.prompt("비밀번호를 입력하세요", "1234");
        if(userId === "ssafy" && userPwd === "1234"){
            console.log("hi");
            alert('로그인 성공!');
            $('#login').css('display', 'none');
            $('#nickname').empty().append(userId + '님 환영합니다.');
            $('#nickname').css('display', '');
            $('#logout').css('display', '');
            $('.btn-write').css('display','');
        }
        else{
            alert('로그인 실패!');
            return;
        }

        goList();
    });

    // 로그아웃
    $('#logout').on('click', function(){
        $('#login').css('display', '');
        $('#nickname').css('display', 'none');
        $('#logout').css('display', 'none');
        $('.btn-write').css('display', 'none');
        userId = '';

        goList();
    });

    // 글쓰기 화면으로 가기
    $('.btn-write').on('click', function(){
        nowArticleId = '';
        goWriteArticle();
    });

    // 해당 글 수정하기
    $('.btn-modify').on('click', goWriteArticle);
    
    // 해당 글 삭제하기
    $('.btn-delete').on('click', function(){
        let infoList = JSON.parse(localStorage.getItem('written'));
        $.each(infoList, function(idx, item){
            if(item.id === nowArticleId){
                infoList.splice(idx, 1);
                localStorage.setItem('written', JSON.stringify(infoList));
                return;
            }
        });
        alert('삭제되었습니다');
        goList();
    });

    // 쓴 글 저장하기
    $('#btn-send').on('click', function(){
        let name = $('#input-name').val();
        let desc = $('#input-desc').val();
        if(!name){
            alert('제목을 입력하세요');
            return;
        }
        if(!desc){
            alert('내용을 입력하세요');
            return;
        }

        let data = new Object();
        let lastId = parseInt(localStorage.getItem("articleId"));
        if(!nowArticleId){
            if(!lastId){
                lastId = 0;
            }
            data.id = lastId+1;
            data.name = name;
            data.desc = desc;
            data.user = userId;
            data.cnt = 0;
            data.date = new Date().toLocaleString();
            data.comment = [];    
        }

        let check = localStorage.getItem("written");

        if(!check){
            check = [];
            check.push(data);
        }
        else{
            check = JSON.parse(check);
            if(nowArticleId){
                let flag = true;
                $.each(check, function(idx, item){
                    if(!flag) return;
                    if(item.id === nowArticleId){
                        item.name = name;
                        item.desc = desc;
                        flag = false;
                    }
                });
            }
            else{
                check.push(data);
            }
        }
        if(!nowArticleId){
            localStorage.setItem("articleId", lastId+1);
        }
        localStorage.setItem("written", JSON.stringify(check));
        alert('글 작성 성공!!');
        goList();
    });

    // 쓴 글 초기화하기
    $('#btn-reset').on('click', function(){
        $('#input-name').val('');
        $('#input-desc').val('');
    });

    // 필터링 핸들러
    $('#btn-search').on('click', function(){
        goList();
    });

    // input 바뀌는 이벤트 핸들러
    $('#input-search').on("propertychange change keyup paste input", function() {
        filterOption.value = $(this).val();
    });
    
    // select 바뀌는 이벤트 핸들러
    $('#dropdown').change(function(){
        filterOption.type = $(this).val();
    });

    // 초기화면
    print(1);
});

// 글목록 화면으로 가기
function goList() {
    $('#doc-detail').css('display', 'none');
    $('#doc-write').css('display', 'none');
    $('#doc-show').css('display', '');
    nowArticleId = '';
    print(1);
};

// 저장된 글 정보 뿌려주기
// filterOption: 아이디/작성자/글번호 (객체 / key:value)
// curPage: 현재 보여주고 싶은 페이지
// pageSize: 현재 몇번째 페이지인지
function print(curPage) {
    let infoList = JSON.parse(localStorage.getItem('written'));

    if(curPage<=0){
        alert('처음 페이지입니다.');
        return;
    }
    if(curPage>=infoList.length/pageSize+1){
        alert('마지막 페이지입니다.');
        return;
    }

    infoList = filtering(infoList);  // 실제로 필터링 하는 부분

    let infos=`
    <tr>
    <th>글번호</th>
    <th style="width: 50%;">제목</th>
    <th>작성자</th>
    <th>조회수</th>
    <th>작성일</th>
    </tr>
    `;
    if(infoList){ 
        let startIdx = pageSize*(curPage-1);
        let endIdx = pageSize*curPage;
        if(endIdx >= infoList.length){
            endIdx = infoList.length;
        }
        for(let idx=startIdx; idx<endIdx; idx++){
            infos += `
            <tr id=${infoList[idx].id} onclick="javascript:showDetail(${infoList[idx].id});">
            <td>${infoList[idx].id}</td>
            <td>${infoList[idx].name}</td>
            <td>${infoList[idx].user}</td>
            <td>${infoList[idx].cnt}</td>
            <td>${infoList[idx].date}</td>
            </tr>
            `;
        }
    }

    $('#table-show').empty().append(infos);

    // footer 만들기
    makeFooter(infoList, curPage);
}

function filtering(infoList){
    if(!filterOption.value){
        return infoList;
    }

    let result = infoList.filter((info) => {
        let standardValue = filterOption.value;
        switch(filterOption.type){
            case 'user':
                return (info.user.includes(standardValue));
            case 'id':
                return (String(info.id) === standardValue);
            default:
                return (String(info.id) === standardValue) || (info.name.includes(standardValue)) || (info.user.includes(standardValue));
        }
    });

    return result;
}

function makeFooter(infoList, curPage) {
    let footer = `
    <ul class="pagination justify-content-center m-5">
    <li class="page-item"><a class="page-link" href="#" onclick="javascript:print(${curPage-1});">이전</a></li>
    `;
    let endIdx = infoList.length/pageSize;
    if(infoList.length % pageSize !== 0){
        endIdx++;
    }

    for(let idx=1; idx<=endIdx; idx++){
        if(idx === curPage){
            footer += `
            <li class="page-item active"><a class="page-link" href="#" onclick="javascript:print(${idx});">${idx}</a></li>
            `;
        }
        else{
            footer += `
            <li class="page-item"><a class="page-link" href="#" onclick="javascript:print(${idx});">${idx}</a></li>
            `;
        }
    }
    footer += `
    <li class="page-item"><a class="page-link" href="#" onclick="javascript:print(${curPage+1});">다음</a></li>
    </ul>
    `;
    
    $('#footer-show').empty().append(footer);
}

// 글보기 화면으로 가기
function showDetail(id){
    nowArticleId = id;
    if(!userId) {
        $('.btn-afterLogin').css('display', 'none');
    }
    else {
        $('.btn-afterLogin').css('display', '');
    }
    let infoList = JSON.parse(localStorage.getItem('written'));
    $.each(infoList, function(idx, item){
        if(item.id === id){
            item.cnt += 1;

            $('#doc-write').css('display', 'none');
            $('#doc-show').css('display', 'none');
            $('#doc-detail').css('display', '');
            $('#comment').val('');
            $('#th-name').text(id+'. '+item.name);
            $('#td-user').text(item.user);
            $('#td-cnt').text(item.cnt);
            $('#td-date').text(item.date);
            $('#td-desc').text(item.desc);
            let comments = `<h2 class="text-center mt-3"><댓글창></h2>`;
            $.each(item.comment, function(idx, value){
                comments += `
                <div class="ml-5 mb-2" style="width: 80%; height: 30px;">${idx+1}. ${value}</div>
                `;
            });
            $('.div-comment').empty().append(comments);

            localStorage.setItem('written', JSON.stringify(infoList));
            return;
        }
    });
}

function saveComment(){
    if(!userId){
        alert('로그인 후, 댓글 작성이 가능합니다.');
        return;
    }
    if(!$('#comment').val()){
        alert('댓글 내용을 입력하세요');
        return;
    }

    let flag = true;
    let infoList = JSON.parse(localStorage.getItem('written'));
    $.each(infoList, function(idx, item){
        if(!flag) return;
        if(item.id === nowArticleId){
            item.comment.push($('#comment').val());
            item.cnt -= 1;
            localStorage.setItem('written', JSON.stringify(infoList));
            flag = false;
            showDetail(item.id);
        }
    });
}

function goWriteArticle(){
    // 이전에 쓴 글 초기화하기
    if(!nowArticleId){
        $('#input-name').val('');
        $('#input-desc').val('');
    }
    // 이전에 쓴 글 수정하기
    else{
        $('#input-name').val($('#th-name').text().split('.')[1]);
        $('#input-desc').val($('#td-desc').text());
    }

    $('#doc-detail').css('display', 'none');
    $('#doc-write').css('display', '');
    $('#doc-show').css('display', 'none');
}