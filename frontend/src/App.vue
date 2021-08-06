<template>
  <div id="app">
    <Nav />
    <Header />
    <router-view />
    <hr />
    <Footer />
  </div>
</template>

<script>
import Nav from "@/layout/Nav.vue";
import Header from "@/layout/Header.vue";
import Footer from "@/layout/Footer.vue";

export default {
  name: "App",
  components: {
    Nav,
    Header,
    Footer,
  },
  data() {
    return {};
  },
  methods: {},
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}

@import "./assets/css/clean-blog.css";
@import "./assets/css/clean-blog.min.css";
@import "./assets/css/styles.css";
</style>
