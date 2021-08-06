<template>
    <div class="row mt-5">
        <div class="col-6">
            <img id="logo-img-mobile" src="@/assets/house.jpg"  alt="The house" style="width: 60%; height: 400px; overflow: hidden;" />
        </div>
        <div class="col-4">
        <h2 class="title">주택 정보</h2>
        <b-table-simple stacked>
            <colgroup><col><col></colgroup>
    <colgroup><col><col><col></colgroup>
    <colgroup><col><col></colgroup>
    <b-thead head-variant="dark">
      <b-tr>
        <b-th colspan="2">Region</b-th>
        <b-th colspan="3">Clothes</b-th>
        <b-th colspan="2">Accessories</b-th>
      </b-tr>
      <b-tr>
        <b-th>Country</b-th>
        <b-th>City</b-th>
        <b-th>Trousers</b-th>
        <b-th>Skirts</b-th>
        <b-th>Dresses</b-th>
        <b-th>Bracelets</b-th>
        <b-th>Rings</b-th>
      </b-tr>
    </b-thead>
    <b-tbody>
              <b-tr>
        <b-td stacked-heading="No">{{apt[0].no}}</b-td>
        <b-td stacked-heading="이름">{{apt[0].houseName}}</b-td>
        <b-td stacked-heading="주소">{{apt[0].address}}</b-td>
        <b-td stacked-heading="면적">{{apt[0].area}}</b-td>
        <b-td stacked-heading="현재 찜한 인원">{{wishnum}}</b-td>
        <b-td stacked-heading="추천 거주 인원">{{apt[0].residentsNum}}</b-td>
        <b-td stacked-heading="층">{{apt[0].floor}}</b-td>
        <b-td stacked-heading="설립년도">{{apt[0].buildYear}}</b-td>
        <b-td v-if="apt[0].deposit != 0" stacked-heading="전세">{{apt[0].deposit}}</b-td>
        <b-td v-if="apt[0].monthlyRent != 0" stacked-heading="월세">{{apt[0].monthlyRent}}</b-td>
        <b-td v-if="roomnum < apt[0].residentsNum" stacked-heading="채팅방 입장">
                      <img
                        id="logo-img-mobile"
                        src="@/assets/enter.png"
                        width="30"
                        alt="The SSAFY"
                        style="cursor: pointer"
                        @click="enterchat(apt[0].no)"
                      />
                      ({{roomnum}} / {{apt[0].residentsNum}})
        </b-td>
        <b-td v-else stacked-heading="채팅방 입장">
            채팅방 인원이 정원입니다.({{roomnum}} / {{apt[0].residentsNum}})
        </b-td>
      </b-tr>
      </b-tbody>
      </b-table-simple>
        </div>
    </div>
</template>

<script>

import { mapActions, mapState } from "vuex";
import http from "@/util/http-common";

export default {
    name: "AptInfoDetail",
    data() {
        return {
        }
    },
    computed: {
        ...mapState(["apt", "wishnum", "member", "roomnum","roomInfo"]),
    },
    methods: {
        ...mapActions(["registRoom"]),
        enterchat(no) {
      http
        .post("/chat/room/" + no + "/" + this.member.data.id)
        .then((response) => {
          alert(response.data.roomName + "번 방에 입장합니다.");
          this.createRoom();
          this.$router.push({
            name: "Messege",
            params: { name: response.data.roomName, id: response.data.roomId },
          });
        })
        .catch((error) => {
          console.dir(error);
        });
    },
    createRoom(){
        this.registRoom();
    }
    },
}
</script>

<style>

</style>