<template>
  <header
    class="masthead"
    v-bind:style="{ 'background-image': 'url(' + require('@/assets/mainbg1.jpg') + ')' }"
  >
    <div class="overlay"></div>
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <div class="site-heading" style="padding: 100px 0">
            <h1>SHARE HOUSE</h1>
            <span class="subheading">함께 살아요</span>
          </div>
        </div>
      </div>
    </div>
  </header>
</template>

<script>
export default {
  name: "Header",
};
</script>

<style>
</style>
