<template>
  <div class="container" align="center">
	<div class="col-lg-6" align="center">
		<form id="loginform" method="post" action="">
		<input type="hidden" name="act" id="act" value="login">
			<div class="form-group" align="left">
				<label for="">아이디</label>
				<input type="text" class="form-control" id="id" name="id" v-model="member.id" ref="member.id">
			</div>
			<div class="form-group" align="left">
				<label for="">비밀번호</label>
				<input type="password" class="form-control" id="password" name="password"  v-model="member.password" ref="member.password" @keyup.enter="checkValue()">
			</div>
			<div class="form-group" align="center">
				<button type="button" class="btn btn-warning" @click="checkValue();">로그인</button>
				<button type="button" class="btn btn-primary" @click="$router.push({name: 'Join'})">회원가입</button>
			</div>
		</form>
	</div>
</div>
</template>

<script>

import { mapActions } from "vuex";

export default {
    name: "LoginForm",
	data() {
		return {
      member: {
        name: "",
        id: "",
        joindate: "",
        password: "",
        email: "",
        phone: "",
        address: "",
      },
		};
	},
	methods: {
		...mapActions(["loginMember"]),
		checkValue() {
			let err = true;
      		let msg = "";
			!this.member.id && ((msg = "아이디 입력해주세요"), (err = false), this.$refs.member.id.focus());
      			err &&
        	!this.member.password && ((msg = "비빌번호 입력해주세요"), (err = false), this.$refs.member.password.focus());
		    if (!err) alert(msg);
      		else this.login();
		},
		login(){
			this.loginMember(this.member);
	  		this.$router.push({name: 'Main'});
		},
	},
}
</script>

<style>

</style>