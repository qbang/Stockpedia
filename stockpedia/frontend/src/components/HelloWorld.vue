<template>
  <div class="hello">
    {{ member }}
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: 'HelloWorld',
  data() {
    return {
      member : [],
    };
  },
  props: {
    msg: String,
  },
  created() {
    this.getMember();
  },
  methods: {
    getMember() {
      axios.get("http://localhost:8080/Stockpedia_war_exploded/test/vue")
          .then((response) => {
            this.member = response.data;
          })
          .catch(function (error) {
            console.log(error);
          });
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
