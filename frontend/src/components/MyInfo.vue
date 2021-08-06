<template>
	<b-container class="col-lg-8" align="center">
        <h2 class="title">회원 정보 확인</h2>
        <b-table stacked id="userinfo_table" :items="user" :fields="fields" >
	  </b-table>
	  <div class="form-group" align="center">
	        	<button type="button" class="btn btn-warning" @click="$router.push({name: 'MyInfoUpdate', params: {id: member.data.id}})">수정</button>
				<button type="button" class="	btn btn-primary" @click="del(member.data.id)">탈퇴</button>
		</div>
        </b-container>
    <!-- <div class="container" align="center">
	  <div class="col-lg-8" align="center">
	  <h2>회원 정보 확인</h2>
	  <table class="table table-active">
	    <tbody>
	      <tr class="table-info">
	        <td>아이디 : {{member.data.id}}</td>
	      </tr>
	      <tr class="table-info">
	        <td>이름 : {{member.data.name}}</td>
	        <td align="right">이메일 : {{member.data.email}}</td>
	      </tr>
	      <tr class="table-info">
	        <td colspan="2">주소: {{member.data.address}}</td>
	      </tr>
	      <tr>
	        <td colspan="2">
	        <div class="form-group" align="center">
	        	<button type="button" class="btn btn-warning" @click="$router.push({name: 'MyInfoUpdate', params: {id: member.data.id}})">수정</button>
				<button type="button" class="	btn btn-primary" @click="del(member.data.id)">삭제</button>
				</div>
			</td>
	      </tr>
	    </tbody>
	  </table>
	  </div>
	</div> -->
</template>

<script>

import { mapActions, mapState } from 'vuex';

export default {
    name: "MyInfo",
	data() {
		return {
			 fields: [
                { label: "아이디", key: "id" },
                { label: "이름", key: "name" },
                { label: "생년월일", key: "birth" },
                { label: "이메일", key: "email" },
                { label: "휴대폰 번호", key: "phone" },
                { label: "주소", key: "address" },
            ],
			user: [],
		}
	},
	computed: {
    	...mapState(["member"]),
  	},
	  created() {
		  this.user.push(this.member.data);
		  console.log(this.user);
	  },
	  methods: {
		  ...mapActions(["deleteUser"]),
		  del(userid){
			this.deleteUser(userid);
    		this.$router.push({name: 'Main'});
		  }
	  },
}
</script>

<style>

</style>