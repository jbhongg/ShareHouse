<template>
  <div>
    <div>
      <h2>{{ $route.params.name }} 채팅방</h2>
    </div>
    <hr />
    <div class="row mt-5" style="justify-content: center">
      <div class="col-7" style="background: skyblue" v-for="(m, idx) in message" :key="idx">
        <div v-bind:class="m.style">
          <div v-if="m.style === 'otherMsg'">
            <h5  style="margin: 3px">
            {{ m.senderName }}
            </h5>
            {{ m.content }}
          </div>
          <div v-else-if="m.style === 'myMsg'">
            {{ m.content }}
          </div>
        <div v-else>
            채팅방이 개설되었습니다.
          </div>

        </div>
      </div>
    </div>
    <hr />
    <div class="row mt-5" style="justify-content: center">
      <div class="col-6">
        <b-form-input
          type="text"
          v-on:keyup.enter="sendMessage()"
          v-model="content"
          placeholder="보낼 메세지"
          size="100"
        />
      </div>
      <b-button variant="primary" class="col-1" @click="sendMessage()">SEND</b-button>
    </div>
    <div>
      <b-button variant="primary" class="btn btn-primary" @click="goback()">목록</b-button>
      <b-button variant="primary" class="btn btn-warning" @click="exit(id, roomid)">퇴장</b-button>
    </div>
  </div>
</template>

<script>
import Stomp from "webstomp-client";
import SockJS from "sockjs-client";
import http from "@/util/http-common";

import { mapActions, mapState } from "vuex";
export default {
  name: "MeseegeDetail",
  data() {
    return {
      id: -1,
      nickname: "",
      title: "",
      roomid: -1,
      idx: 0,
      message: [],
      content: "",
      stompClient: null,
    };
  },
  computed: {
    ...mapState(["roomInfo", "member", "msg"]),
  },
  created() {
    this.id = this.member.data.id;
    this.roomid = this.$route.params.id;
    this.nickname = this.member.data.id;
    console.log(this.roomid);
    if(this.msg == '1'){
      let m = {
            senderName: "admin",
            content: this.msg,
            style: "none",
      };
      this.message.push(m);
      console.log("ww");
    }
    else{
          http
      .get("/chat/room/message/" + this.roomid)
      .then((response) => {
        console.log("get");
        console.log(response);
        this.message = response.data;
        this.getChat();
      })
      .catch((error) => {
        console.log(error);
      });
    }

    // this.getdata(this.roomid);
    // this.getChat();
    // this.getdata(this.roomid);
    //this.getChat();
    let socket = new SockJS("http://59.27.106.177:8092/ws");
    this.stompClient = Stomp.over(socket);
    this.stompClient.connect(
      {},
      (frame) => {
        console.log("success", frame);
        this.stompClient.subscribe("/sub/" + this.roomid, (res) => {
          let jsonBody = JSON.parse(res.body);
          let m = {
            senderName: jsonBody.senderName,
            content: jsonBody.content,
            style: jsonBody.senderId == this.id ? "myMsg" : "otherMsg",
          };
          this.message.push(m);
        });
      },
      (err) => {
        console.log("fail", err);
      }
    );
  },
  methods: {
    ...mapActions(["getMsg"]),
    ...mapActions(["deleteRoom"]),
    sendMessage() {
      if (this.content.trim() != "" && this.stompClient != null) {
        let chatMessage = {
          content: this.content,
          chatRoomId: this.roomid,
          senderName: this.nickname,
          senderId: this.id,
          id: "0",
        };
        this.stompClient.send("/pub/message", JSON.stringify(chatMessage), {});

        this.content = "";
      }
    },
    getdata(no) {
      this.getMsg(no);
    },
    getChat() {
      //this.getMsg(no);
      this.message = [];
      if (this.msg.length == 0) {
        this.message = [];
      } else {
        console.log(this.msg);
        for (let i = this.msg.length - 1; i > -1; i--) {
          let m = {
            senderName: this.msg[i].senderId,
            content: this.msg[i].content,
            style: this.msg[i].senderId == this.id ? "myMsg" : "otherMsg",
          };
          this.message.push(m);
        }
      }
    },
    goback(){
      this.$router.push({name: 'Chat'});
    },
    exit(id, no){
      let param = no + "/" + id;
      console.log(param);
      this.deleteRoom(param);
      this.$router.push({name: 'Chat'});
      this.$router.go();
    }
  },
};
</script>

<style>
.myMsg {
  overflow: hidden;
  height: auto;
  margin-top: 10px;
  text-align: right;
  color: black;
  position: relative;
  background: #d5c700;
  border-radius: 5px;
  border: 1px solid #000000;
  width: 30%;
  margin-left: 70%;
}
.otherMsg {
  overflow: hidden;
  height: auto;
  text-align: left;
  margin-top: 10px;
  color: black;
  position: relative;
  background: #ffffff;
  border-radius: 5px;
  border: 1px solid #000000;
  width: 30%;
}

.input-form {
  align-items: flex-start;
}
</style>
