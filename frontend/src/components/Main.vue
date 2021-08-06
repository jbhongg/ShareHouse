<template>
<div class="container">
		<div class="dark-bg section">
	<div class="container-fluid">
		<div
			class="sorting-filters text-center mb-20 d-flex justify-content-center">
			<form class="form-inline" id="frm" method="get" action="">
				<input type="hidden" id="act" name="act" value="searchAreas" />
				<div class="form-group md">
					<select class="form-control mr-2" name="sido" id="sido" v-model="si">
						<option value="">도/광역시</option>
						<option v-for="(item, i) in sido.data" :key="i" :value="item.sidoCode">{{item.sidoName}}</option>
					</select>
				</div>
				<div class="form-group mr-2 md-1">
					<select class="form-control" name="gugun" id="gugun" v-model="gu">
						<option value="">시/구/군</option>
						<option v-for="(item, i) in gugun.data" :key="i" :value="item.gugunCode">{{item.gugunName}}</option>
					</select>
				</div>
				<div class="form-group md-1">
					<select class="form-control" name="dong" id="dong" v-model="dong_code">
						<option value="">동</option>
						<option v-for="(item, i) in dong.data" :key="i" :value="item.dongCode">{{item.dongName}}</option>
					</select>
				</div>

			</form>
			<!--
			<form class="form-inline" method="get"
				action="${root}/house/apartment" id="aptSearchForm">
				<input type="hidden" id="searchDong" name="searchDong" value="">
				<input type="text" class="form-control" id="aptName" name="aptName"
					placeholder="아파트 이름"> 
					<button type="button" class="btn btn-outline-primary btn-sm" id="aptSearch" name="aptSearch">search</button>
			</form>
			-->
		</div>
	</div>
		</div>
	<div class="row mt-5" style="justify-content: center;">
		<div class="col-8">
				<div id="map" class="map"
					style="width: 100%; height: 600px; margin: auto; position: relative; overflow: hidden;">
				</div>
			</div>
	</div>
</div>
</template>

<script>
import { mapActions, mapState } from "vuex";
export default {
    name: "Main",
	data() {
		return {
			si: "",
			gu: "",
			dong_code: "",
		}
	},
	computed:{
		...mapState(["sido", "gugun", "dong", "popular"]),
	},
	mounted() {
		window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
	},
	created() {
		this.getdata();
	},
	watch:{
		si: function(newval){
			this.getgugundata(newval);
		},
		gu: function(newval){
			this.getdongdate(newval);
		},
		dong_code: function(newval){
			this.getapt(newval);
		}
	},
	methods: {
		...mapActions(["getSidoList"]),
		...mapActions(["getGugunList"]),
		...mapActions(["getDongList"]),
		...mapActions(["getAptList"]),
		...mapActions(["getPopular"]),
		addScript() {
      		const script = document.createElement('script');
      		script.onload = () => window.kakao.maps.load(this.initMap);
      		script.src = "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=899e04a2203a1978625b8dbbeef42184&libraries=services";
      		document.head.appendChild(script);
		},
		initMap() {
      		var container = document.getElementById('map'); 
      		var options = {
        		center: new window.kakao.maps.LatLng(33.450701, 126.570667),
        		level: 9
      		};

      		var map = new window.kakao.maps.Map(container, options);
			var geocoder = new window.kakao.maps.services.Geocoder();
			var imageSrc = 'https://littledeep.com/wp-content/uploads/2019/04/littledeep_house_style2.png',
				imageSize = new window.kakao.maps.Size(64, 69),
				imageOption = {offset: new window.kakao.maps.Point(27, 69)};

			var markerImage = new window.kakao.maps.MarkerImage(imageSrc, imageSize, imageOption);

			for (var i = 0; i < this.popular.length; i++) {
				let title = this.popular[i].houseName;
				geocoder.addressSearch(this.popular[i].address, function(result, status) {
     			if (status === window.kakao.maps.services.Status.OK) {

        			var coords = new window.kakao.maps.LatLng(result[0].y, result[0].x);
        			var marker = new window.kakao.maps.Marker({
						map: map,
            			position: coords,
						image: markerImage
        			});
        			var infowindow = new window.kakao.maps.InfoWindow({
            			content: `<div style="width:150px;text-align:center;padding:6px 0;">${title}</div>`,
						removable : true
        			});
					window.kakao.maps.event.addListener(marker, 'click', function() {
      					// 마커 위에 인포윈도우를 표시합니다
      					infowindow.open(map, marker);  
					});
        			map.setCenter(coords);
    			}
				}); 
			}
			
    	},
		getdata(){
			this.getSidoList();
			this.hotapt();
		},
		getgugundata(code){
			this.getGugunList(code);
		},
		getdongdate(code){
			this.getDongList(code);
		},
		getapt(code){
			this.getAptList(code);
			this.$router.push({name: 'Apt'});
		},
		hotapt(){
			this.getPopular();
		}
	}
}
</script>

<style>
.map {
  width: 100%;
  height: 400px;
}
</style>