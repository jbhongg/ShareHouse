<template>
    <div>
        <h2>회원 목록</h2>
	<div align="right"><button type="button" class="modiBtn btn btn-outline-info" data-toggle="modal" data-target="#userRegModal">등록</button></div>
  	<table class="table table-hover">
  		<colgroup>
            <col width="120">
            <col width="120">
            <col width="120">
            <col width="170">
            <col width="*">
            <col width="120">
            <col width="130">
        </colgroup>
    	<thead>
	      	<tr>
	        	<th class="text-center">아이디</th>
	        	<th class="text-center">비밀번호</th>
	        	<th class="text-center">이름</th>
	        	<th class="text-center">이메일</th>
	        	<th class="text-center">휴대폰 번호</th>
	        	<th class="text-center">주소</th>
	        	<th class="text-center">가입일</th>
	        	<th class="text-center">수정/삭제</th>
	      	</tr>
    	</thead>
    	<tbody id="userlist"></tbody>
	</table>
    </div>
</template>

<script>
export default {
    name: "UserInfo"
}
</script>

<style>

</style>