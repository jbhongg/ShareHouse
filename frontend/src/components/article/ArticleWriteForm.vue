<template>
<div align="center">
    <div class="col-lg-6" align="right">
	    <strong>{{member.data.name}}</strong>님 환영합니다.
	</div>
	<div class="col-lg-6" align="center">
		<h2>게시판 글쓰기</h2>
		<div id="writeform">
			<div class="form-group" align="left">
				<label for="subject">제목:</label>
				<input type="text" class="form-control" id="subject" name="subject" v-model="article.subject">
			</div>
			<div class="form-group" align="left">
				<label for="content">내용:</label>
				<textarea class="form-control" rows="15" id="content" name="content" v-model="article.content"></textarea>
			</div>
			<div class="form-group" align="left">
				<label for="subject">파일:</label>
				<input type="file" class="form-control-file border" name="upfile" multiple="multiple">
			</div>
			<button type="button" id="writeBtn" class="btn btn-primary" @click="write">글작성</button>
			<button type="reset" class="btn btn-warning">초기화</button>
		</div>
	</div>
    </div>
</template>

<script>

import { mapActions } from "vuex";
import { mapState } from 'vuex';

export default {
    name: "ArticleWriteForm",
    computed: {
    	...mapState(['member']),
  	},
    data() {
        return {
            article: {
            articleno: 0,
            content: "",
            fileInfos: [{
                    originFile: "",
                    saveFile: "",
                    saveFolder: ""
            }],
            regtime: "",
            subject: "",
            userid: "",
            },
        };
    },
    methods: {
        ...mapActions(["writeArticle"]),
        write(){
            this.article.userid = this.member.data.id;
            console.log(this.article);
            this.writeArticle(this.article);
            this.$router.push({name: 'Article'});
            this.$router.go();
        }
    },
}
</script>

<style>

</style>