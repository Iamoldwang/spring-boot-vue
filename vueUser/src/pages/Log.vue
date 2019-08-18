<template>
    <div>{{odd}}</div>
</template>
<style></style>
<script>

  let getOddSum = function (num) {
    console.log('----------------' + num)
    if(num  > 0){
      if(num % 2 == 1){
        return num + this.getOddSum(num-1);
      }else{
        return this.getOddSum(num-1);
      }
    }
  }

  let he = function(n, m)
  {
    if (n == m && n % 2 == 0)
      return 0;
    else if (n == m)
      return n;
    else if (n % 2 == 0)
      return he(n-1,m);
    else
      return n + he(n - 1, m);

  }
  
    export default {
      data() {
          return {
            odd: 0
          }
      },
      methods:{
        getOddSum,
        he
      },
      mounted: function() {
        this.odd = this.he(1,50)
      }
    }
</script>
