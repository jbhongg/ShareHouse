<template>
  <div>
    <h4>사람들과 계약에 관련된 거래 상의를 할 수 있어요</h4>
    <b-container>
      <b-table id="chat_table" :items="rooms.data" :fields="fields">
        <template #cell(enter)="roomName">
          <b-button
            size="sm"
            @click="getdata(roomName.item.roomName, roomName.item.roomId)"
            class="mr-2"
            >입장
          </b-button>
        </template>
      </b-table>
    </b-container>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";

export default {
  name: "ChatRoom",
  data() {
    return {
      fields: [
        { label: "방 번호", key: "roomId" },
        { label: "방 이름", key: "roomName" },
        { label: "입장", key: "enter" },
      ],
    };
  },
  computed: {
    ...mapState(["rooms", "member"]),
  },
  created() {
    this.findAllRoom();
  },
  methods: {
      ...mapActions(["getMsg"]),
    ...mapActions(["getRoomsById"]),
    findAllRoom() {
      //this.getRooms();
      this.getRoomsById(this.member.data.id);
    },
    getdata(Name, id){
        this.getMsg(id);
        this.$router.push({name: 'Messege',params: { name: Name, id: id },});
    }
  },
};
</script>

<style></style>
