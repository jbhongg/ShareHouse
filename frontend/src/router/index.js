import Vue from "vue";
import VueRouter from "vue-router";
import Home from "@/views/Home.vue";
import Login from "@/views/Login.vue";
import Join from "@/views/Join.vue";
import MyPage from "@/views/MyPage.vue";
import Interest from "@/views/Interest.vue";
import Article from "@/views/Article.vue";
import Admin from "@/views/Admin.vue";
import ArticleWrite from "@/views/ArticleWrite.vue";
import ArticleUpdate from "@/views/ArticleUpdate.vue";
import MyInfoUpdate from "@/views/MyInfoUpdate.vue";
import Apt from "@/views/Apt.vue";
import InterestApt from "@/views/InterestApt.vue";
import AptInfo from "@/views/AptInfo.vue";
import Chat from "@/views/Chat.vue";
import Messege from "@/views/Messege.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Main",
    redirect: "/home/",
  },
  {
    path: "/home/",
    name: "Home",
    component: Home,
  },
  {
    path: "/login/",
    name: "Login",
    component: Login,
  },
  {
    path: "/join/",
    name: "Join",
    component: Join,
  },
  {
    path: "/mypage/",
    name: "MyPage",
    component: MyPage,
  },
  {
    path: "/interest/",
    name: "Interest",
    component: Interest,
  },
  {
    path: "/interestapt/",
    name: "InterestApt",
    component: InterestApt,
  },
  {
    path: "/article/",
    name: "Article",
    component: Article,
  },
  {
    path: "/article/update/",
    name: "ArticleUpdate",
    component: ArticleUpdate,
    props: true,
  },
  {
    path: "/article/write",
    name: "ArticleWrite",
    component: ArticleWrite,
  },
  {
    path: "/admin/",
    name: "Admin",
    component: Admin,
  },
  {
    path: "/mypage/update:id",
    name: "MyInfoUpdate",
    component: MyInfoUpdate,
  },
  {
    path: "/apt",
    name: "Apt",
    component: Apt,
  },
  {
    path: "/aptinfo",
    name: "AptInfo",
    component: AptInfo,
  },
  {
    path: "/chat",
    name: "Chat",
    component: Chat,
  },
  {
    path: "/messege/enter:name:id",
    name: "Messege",
    component: Messege,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
