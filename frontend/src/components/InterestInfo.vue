<template>
  <div class="row mt-5">
    <div class="col-5">
      <div
        id="map"
        class="map"
        style="width: 70%; height: 600px; margin: auto; position: relative; overflow: hidden"
      ></div>
    </div>
    <div class="col-6">
      <div class="sidebar">
        <div class="block clearfix" id="interestInfo">
          <h2 class="title">주택 정보</h2>
          <hr />
          <div class="separator-3"></div>
          <div class="media margin-clear">
            <div class="media-body">
              <table class="table table-hover" border="1" v-if="typeof apts != 'undefined'">
                <thead>
                  <th scope="col">No.</th>
                  <th scope="col">주택 명</th>
                  <th scope="col">지역</th>
                  <th scope="col">추천 거주 인원</th>
                  <th scope="col">예상 전/월세</th>
                  <th scope="col">찜하기</th>
                </thead>
                <tbody>
                  <tr v-for="(item, i) in apts" :key="i">
                    <td>{{ item.no }}</td>
                    <td>{{ item.houseName }}</td>
                    <td>{{ item.dong }}</td>
                    <td>{{ item.residentsNum }}</td>
                    <td v-if="item.monthlyRent == 0">{{ item.deposit }}(전)</td>
                    <td v-else>{{ item.monthlyRent }}(월)</td>
                    <td>
                      <img
                        id="logo-img-mobile"
                        src="@/assets/heart.png"
                        width="30"
                        alt="The SSAFY"
                        style="cursor: pointer"
                        @click="registinterestapt(item.no, member.data.id)"
                      />
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
          <div class="media margin-clear">
            <div class="media-body">
              <h3 v-if="typeof apts == 'undefined'">주택 정보가 없습니다</h3>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";

export default {
  name: "InterestInfo",
  data() {
    return {};
  },
  computed: {
    ...mapState(["apts", "member"]),
  },
  watch: {
    apts: function () {
      window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
    },
  },
  mounted() {
    window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
  },
  created() {
    this.getdata();
  },
  methods: {
    ...mapActions(["registInterest"]),
    ...mapActions(["getAptListByGu"]),
    getdata() {
      console.log(this.member);
      this.getAptListByGu(this.member.data.area);
    },
    addScript() {
      const script = document.createElement("script");
      script.onload = () => window.kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=899e04a2203a1978625b8dbbeef42184&libraries=services";
      document.head.appendChild(script);
    },
    initMap() {
      var container = document.getElementById("map");
      var options = {
        center: new window.kakao.maps.LatLng(33.450701, 126.570667),
        level: 5,
      };

      var map = new window.kakao.maps.Map(container, options);
      var geocoder = new window.kakao.maps.services.Geocoder();
      var imageSrc =
          "https://littledeep.com/wp-content/uploads/2019/04/littledeep_house_style2.png",
        imageSize = new window.kakao.maps.Size(64, 69),
        imageOption = { offset: new window.kakao.maps.Point(27, 69) };

      var markerImage = new window.kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

      for (var i = 0; i < this.apts.length; i++) {
        let title = this.apts[i].houseName;
        geocoder.addressSearch(this.apts[i].address, function (result, status) {
          if (status === window.kakao.maps.services.Status.OK) {
            var coords = new window.kakao.maps.LatLng(result[0].y, result[0].x);
            var marker = new window.kakao.maps.Marker({
              map: map,
              position: coords,
              image: markerImage,
            });
            var infowindow = new window.kakao.maps.InfoWindow({
              content: `<div style="width:150px;text-align:center;padding:6px 0;">${title}</div>`,
              removable: true,
            });
            window.kakao.maps.event.addListener(marker, "click", function () {
              // 마커 위에 인포윈도우를 표시합니다
              infowindow.open(map, marker);
            });
            map.setCenter(coords);
          }
        });
      }
    },
    registinterestapt(no, userid) {
      let param = userid + "/" + no;
      this.registInterest(param);
    },
  },
};
</script>

<style></style>
