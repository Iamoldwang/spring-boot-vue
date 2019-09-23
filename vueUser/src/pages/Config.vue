<body>
  <div id="app">
    <h1>{{msg}},这里是outer html生成的html</h1>
  </div>
</body>
<style></style>
<script>
  var app = new Vue({
    el:"#app",
    template:"<h1>{{msg}},这里是template生成的html</h1>",
    render:function (createElement) {
      return createElement("h1","hello,这里是render生成的html")
    },
    data:{
      msg:'hello'
    }
  })
</script>
