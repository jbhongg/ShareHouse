<template>
  <div align="center">
        <div class="col-lg-6" align="right">
	    <strong>{{member.data.name}}</strong>님 환영합니다.
	</div>
    <div class="col-lg-6" align="center">
      <h2>게시판 글 확인하기{{ no }}</h2>
      <div id="writeform">
        <div class="form-group" align="left">
          <label for="subject">제목:</label>
          <input
            :disabled="member.data.id != article.userid"
            type="text"
            class="form-control"
            id="subject"
            name="subject"
            v-model="article.subject"
          />
        </div>
        <div class="form-group" align="left">
          <label for="content">내용:</label>
          <textarea
            :disabled="member.data.id != article.userid"
            class="form-control"
            rows="15"
            id="content"
            name="content"
            v-model="article.content"
          ></textarea>
        </div>
        <div class="form-group" align="left">
          <label for="subject">파일:</label>
          <input type="file" class="form-control-file border" name="upfile" multiple="multiple" />
        </div>
        <div class="button">
          <button
            :disabled="member.data.id != article.userid"
            type="button"
            id="writeBtn"
            class="btn btn-primary"
            @click="update"
          >
            글수정
          </button>
          <button
            :disabled="member.data.id != article.userid"
            type="button"
            id="writeBtn"
            class="btn btn-danger"
            @click="deleteBoard"
          >
            글삭제
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import http from "@/util/http-common";
import { mapActions } from "vuex";
import { mapState } from "vuex";

export default {
  name: "ArticleUpdateForm",
  computed: {
    ...mapState(["member"]),
  },
  props: {
    no: { type: Number },
  },
  data() {
    return {
      article: {
        articleno: 0,
        content: "",
        fileInfos: [
          {
            originFile: "",
            saveFile: "",
            saveFolder: "",
          },
        ],
        regtime: "",
        subject: "",
        userid: "",
      },
    };
  },
  created() {
    console.log(this.no);
    console.log(this.$route.params.no);
    http.get("/board/" + this.$route.params.no).then(({ data }) => {
      console.log(data);
      this.article = data;
    });
  },
  methods: {
    ...mapActions(["updateArticle"]),
    ...mapActions(["deleteArticle"]),
    update() {
      this.article.userid = this.member.data.id;
      this.article.articleno = this.$route.params.no;
      console.log(this.article);
      this.updateArticle(this.article);
      this.$router.push({ name: "Article" });
      this.$router.go();
    },
    deleteBoard() {
      this.deleteArticle(this.article);
      this.$router.push({ name: "Article" });
      this.$router.go();
    },
  },
};
</script>

<style>
.button > button {
  margin: 10px;
}
</style>
