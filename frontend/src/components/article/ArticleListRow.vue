<template>
  <tr>
    <td>{{ articleno }}</td>
    <td>{{ userid }}</td>
    <td>
      <a
        href=""
        @click="$router.push({ name: 'ArticleUpdate', params: { no: articleno.item.articleno } })"
        >{{ subject }}</a
      >
    </td>
    <td>{{ regtime }}</td>
  </tr>
</template>

<script>
export default {
  name: "row",
  props: {
    articleno: { type: Number },
    userid: { type: String },
    subject: { type: String },
    regtime: { type: String },
  },
};
</script>

<style></style>
