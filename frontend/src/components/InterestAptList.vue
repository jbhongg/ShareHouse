<template>
    <div>
        <b-container class="container">
        <b-table id="interestapt_table" :items="wishs.data" :fields="fields" >
	  	<template #cell(delete)="no">
        <b-button size="sm" @click="deleteWish(no)" class="mr-2">삭제
        </b-button>
      </template>
	  </b-table>
        </b-container>
    </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
export default {
    name: "InterestAptList",
    data() {
        return {
             fields: [
                { label: "No.", key: "no" },
                { label: "이름.", key: "houseName" },
                { label: "주소", key: "address" },
                { label: "면적", key: "area" },
                { label: "층", key: "floor" },
                { label: "설립년도", key: "buildYear" },
                { label: "전세", key: "deposit" },
                { label: "월세", key: "monthlyRent" },
                { label: "삭제", key: "delete" },
             ],
        };
    },
    computed: {
        ...mapState(["member", "wishs"]),
    },
    created() {
        this.getdata();
    },
    methods: {
        ...mapActions(["getWishList"]),
        ...mapActions(["deleteWishList"]),
        getdata(){
		    this.getWishList(this.member.data.id);
	    },
        deleteWish(rentno){
            let param =  this.member.data.id + "/" + rentno.item.no
            
            console.log(param);
            this.deleteWishList(param);
            this.$router.go();
        }
    },
}
</script>

<style>

</style>