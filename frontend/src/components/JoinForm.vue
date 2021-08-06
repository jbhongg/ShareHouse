<template>
  <div class="container" align="center">
    <div class="col-lg-6" align="center">
      <div id="userform">
        <div class="form-group" align="left">
          <label for="name">이름</label>
          <input
            type="text"
            class="form-control"
            id="name"
            name="name"
            v-model="member.name"
            ref="name"
          />
        </div>
        <div class="form-group" align="left">
          <label for="">아이디</label>
          <input
            type="text"
            class="form-control"
            id="id"
            name="id"
            v-model="member.id"
            ref="id"
          />
        </div>
        <div class="form-group" align="left">
          <label for="">비밀번호</label>
          <input
            type="password"
            class="form-control"
            id="password"
            name="password"
            v-model="member.password"
            ref="password"
          />
        </div>
        <div class="form-group" align="left">
          <label for="">비밀번호재입력</label>
          <input
            type="password"
            class="form-control"
            id="pwdcheck"
            name="pwdcheck"
            v-model="pwdcheck"
            ref="pwdcheck"
          />
        </div>
        <div class="form-group" align="left">
          <label for="">생년월일</label>
          <br>
          <div id="bir_yy" class="custom-control-inline">
              <span class="box">
                  <input type="text" id="yy" class="form-control" name="yy" maxlength="4" v-model="yy" ref="yy" placeholder="년(4자)">
              </span>
              <span class="box">
                  <select id="mm" class="form-control" name="mm" v-model="mm" ref="mm">
                      <option value="">월</option>
                      <option value="01">1</option>
                      <option value="02">2</option>
                      <option value="03">3</option>
                      <option value="04">4</option>
                      <option value="05">5</option>
                      <option value="06">6</option>
                      <option value="07">7</option>
                      <option value="08">8</option>
                      <option value="09">9</option>                                    
                      <option value="10">10</option>
                      <option value="11">11</option>
                      <option value="12">12</option>
                  </select>
              </span>
            <span class="box">
                <input type="text" id="dd" class="form-control" name="dd" v-model="dd" ref="dd" maxlength="2" placeholder="일">
            </span>
        </div>
        </div>
        <div class="form-group" align="left">
          <label for="">성별 선택</label>
          <select type="text" id="gender" name="gender" class="form-control" v-model="member.gender" ref="gender">
            <option value="">성별 선택</option>
            <option value="M">남</option>
            <option value="F">여</option>
          </select>
        </div>
        <div class="form-group" align="left">
          <label for="">관심지역 선택</label>
          <div class="form-group md">
					<select class="form-control mr-2" name="sido" id="sido" v-model="si" ref="si">
						<option value="">도/광역시</option>
						<option v-for="(item, i) in sido.data" :key="i" :value="item.sidoCode">{{item.sidoName}}</option>
					</select>
				</div>
				<div class="form-group mr-2 md-1">
					<select class="form-control" name="gugun" id="gugun" v-model="gu" ref="gu">
						<option value="">시/구/군</option>
						<option v-for="(item, i) in gugun.data" :key="i" :value="item.gugunCode">{{item.gugunName}}</option>
					</select>
				</div>
        </div>
        <div class="form-group" align="left">
          <label for="email">이메일</label><br />
          <div id="emailid" class="custom-control-inline">
            <input
              type="text"
              class="form-control"
              id="emailid"
              name="emailid"
              v-model="emailid"
              ref="emailid"
              size="25"
            />
            @
            <select class="form-control" id="emaildomain" name="emaildomain" v-model="emaildomain">
              <option value="naver.com">naver.com</option>
              <option value="google.com">google.com</option>
              <option value="daum.net">daum.net</option>
              <option value="nate.com">nate.com</option>
              <option value="hanmail.net">hanmail.net</option>
            </select>
          </div>
        </div>
        <div class="form-group" align="left">
          <label for="tel">전화번호</label>
          <br>
          <div id="tel" class="custom-control-inline">
            <select class="form-control" id="tel1" name="tel1" v-model="tel1" ref="tel1">
              <option value="010">010</option>
              <option value="02">02</option>
              <option value="031">031</option>
              <option value="032">032</option>
              <option value="041">041</option>
              <option value="051">051</option>
              <option value="061">061</option>
            </select>
            _
            <input
              type="text"
              class="form-control"
              id="tel2"
              name="tel2"
              v-model="tel2"
              ref="tel2"
            />
            _
            <input
              type="text"
              class="form-control"
              id="tel3"
              name="tel3"
              v-model="tel3"
              ref="tel3"
            />
          </div>
        </div>
        <div class="form-group" align="left">
          <label for="">주소</label><br />
          <div id="addressdiv" class="custom-control-inline">
            <button
              type="button"
              id="zipcode"
              name="zipcode"
              style="background-color: rgb(228, 222, 222)"
              class="btn"
              @click="searchaddr"
            >
              주소 찾기
            </button>
          </div>
          <input
            type="text"
            class="form-control"
            name="addr"
            id="addr"
            v-model="addr"
            ref="addr"
            readonly="readonly"
          />
          <input
            type="text"
            class="form-control"
            id="address_detail"
            name="address_detail"
            v-model="address_detail"
            ref="address_detail"
          />
        </div>
        <div class="form-group" align="center">
          <button type="button" class="btn btn-primary" id="registerBtn" @click="checkValue">
            회원가입
          </button>
          <button type="reset" class="btn btn-warning">초기화</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";

export default {
  name: "JoinForm",
  data() {
    return {
      member: {
        name: "",
        id: "",
        password: "",
        email: "",
        phone: "",
        address: "",
        area: "",
        birth: "",
        gender: "",
      },
      pwdcheck: "",
      emailid: "",
      emaildomain: "",
      tel1: "",
      tel2: "",
      tel3: "",
      addr: "",
      address_detail: "",
      yy: "",
      mm: "",
      dd: "",
      si: "",
      gu: "",
    };
  },
  	computed:{
		...mapState(["sido", "gugun"]),
	},
  created() {
		this.getdata();
	},
  watch:{
		si: function(newval){
			this.getgugundata(newval);
		},
	},
  methods: {
    ...mapActions(["getSidoList"]),
		...mapActions(["getGugunList"]),
    ...mapActions(["joinMember"]),
    getdata() {
      this.getSidoList();
    },
    getgugundata(code){
			this.getGugunList(code);
		},
    checkValue() {
      let err = true;
      let msg = "";
      !this.member.name &&
        ((msg = "이름 입력해주세요"), (err = false), this.$refs.name.focus());
      err &&
        !this.member.id &&
        ((msg = "아이디 입력해주세요"), (err = false), this.$refs.id.focus());
      err &&
        !this.member.password &&
        ((msg = "비빌번호 입력해주세요"), (err = false), this.$refs.password.focus());
      err &&
        !this.pwdcheck &&
        ((msg = "비빌번호를 다시 한번 입력해주세요"), (err = false), this.$refs.pwdcheck.focus());
      err &&
        this.pwdcheck !== this.member.password &&
        ((msg = "비빌번호가 일치하지 않습니다"), (err = false), this.$refs.pwdcheck.focus());
      err &&
        (!this.yy || !this.mm || !this.dd) &&
        ((msg = "생년월일 입력해주세요"), (err = false), this.$refs.yy.focus());
      err &&
        !this.member.gender &&
        ((msg = "성별 입력해주세요"), (err = false), this.$refs.gender.focus());  
      err &&
        (!this.si || !this.gu) &&
        ((msg = "관심지역 입력해주세요"), (err = false), this.$refs.si.focus());
      err &&
        !this.emailid &&
        ((msg = "이메일 입력해주세요"), (err = false), this.$refs.emailid.focus());
      err &&
        (!this.tel1 || !this.tel2 || !this.tel3) &&
        ((msg = "전화번호 입력해주세요"), (err = false), this.$refs.tel1.focus());
      err &&
        !this.addr &&
        ((msg = "주소 입력해주세요"), (err = false), this.$refs.addr.focus());
      err &&
        !this.address_detail &&
        ((msg = "상세주소 입력해주세요"), (err = false), this.$refs.address_detail.focus());
      if(this.dd.length == 1){
        this.dd = 0 + this.dd;
      }
      this.member.phone = this.tel1 + "-" + this.tel2 + "-" + this.tel3;
      this.member.email = this.emailid + "@" + this.emaildomain;
      this.member.address = this.addr + " " + this.address_detail;
      this.member.birth = this.yy + this.mm + this.dd
      this.member.area = this.gu;
      if (!err) alert(msg);
      else this.regitstEmp();
    },
    regitstEmp() {
      this.joinMember(this.member);
	    this.$router.push({name: 'Login'});
    },
    searchaddr() {
      new window.daum.Postcode({ 
        oncomplete: (data) => { 
          let fullRoadAddr = data.roadAddress; 
          let extraRoadAddr = ''; 
          if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){ 
            extraRoadAddr += data.bname; 
          } 
          if(data.buildingName !== '' && data.apartment === 'Y'){ 
            extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName); 
          } 
          if(extraRoadAddr !== ''){ 
            extraRoadAddr = ' (' + extraRoadAddr + ')'; 
          } 
          if(fullRoadAddr !== ''){ 
            fullRoadAddr += extraRoadAddr; 
          } 
          this.addr = fullRoadAddr;
        } 
      }).open()
    },
  },
};
</script>

<style></style>
