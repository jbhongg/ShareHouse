<template>
  <div>
    <b-container v-if="typeof boards != 'undefined'" class="container">
      <div id="write" align="right">
        <b-button
          pill
          id="mvWriteBtn"
          v-if="login !== '1'"
          @click="$router.push({ name: 'ArticleWrite' })"
        >
          글쓰기
        </b-button>
      </div>
      <b-table id="board_table" :items="boards" :fields="fields">
        <template #cell(update)="article">
          <b-button
            size="sm"
            class="mr-2"
            @click="$router.push({ name: 'ArticleUpdate', params: { no: article.item.articleno } })"
          >
            글 보기
          </b-button>
        </template>
      </b-table>

      <b-pagination
        v-if="typeof boardPageNav != 'undefined'"
        v-model="boardPageNav.currentPage"
        :total-rows="boardPageNav.totalCount"
        :per-page="boardPageNav.naviSize"
        aria-controls="board_table"
        align="center"
        @page-click="pageMove"
      ></b-pagination>
    </b-container>
    <b-container v-else class="container">
<div id="write" align="right">
            <b-button
              pill
              id="mvWriteBtn"
              v-if="login !== '1'"
              @click="$router.push({ name: 'ArticleWrite' })"
            >
              글쓰기
            </b-button>
</div>
      <h3>글 많이 써주세요</h3>
    </b-container>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";

export default {
  name: "ArticleList",
  data() {
    return {
      fields: [
        { label: "글 번호", key: "articleno" },
        { label: "ID", key: "userid" },
        { label: "제목", key: "subject", sortable: true },
        { label: "작성 일자", key: "regtime", sortable: true },
        { label: "", key: "update" },
      ],
    };
  },
  computed: {
    ...mapState(["boards", "boardPageNav", "key", "word", "login", "member"]),
  },
  created() {
    console.log(this.boards);
    this.getdata();
  },
  watch: {
    $route: "getdata",
  },
  methods: {
    ...mapActions(["getArticleList"]),
    ...mapActions(["deleteArticle"]),
    getdata() {
      this.getArticleList(this.$store.getters.requestParams);
    },
    pageMove: function (button, page) {
      const params = { key: this.key, word: this.word, pg: page };
      this.getArticleList(params);
    },
    deleteBoard(articleno) {
      this.deleteArticle(articleno);
      this.$router.go();
    },
  },
};
</script>

<style>
#write {
  margin: 10px;
}
</style>
