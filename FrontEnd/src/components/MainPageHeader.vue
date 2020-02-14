<template>
  <v-content>
    <!-- <div style="background-color: black; height: 200px;"></div> -->
    <div style="position: relative; height: 400px;">
      <div style="background-color: black; opacity: 0.4; position: absolute; width: 100%; height: 400px; z-index: 2;"></div>
      <img src="https://static3.depositphotos.com/1005460/260/i/450/depositphotos_2605601-stock-photo-latest-news.jpg" alt="" style="width: 100%; height: 100%; position: absolute; z-index: 1;">
    </div>
      <div class="header-text my-4">
        <p class="header-title">최신 기업 정보, KITE </p>
        <p class="header-body">
          <span class="part">KITE에서 나의 관심 기업 기사를 확인하세요.<br></span>
          <span class="part">그리고 다시 꺼내 보세요.<br></span>
          <span class="part">
          <span class="txt_brunch">손쉬운 스크랩, 공유...</span>
          </span>
        </p>
      </div>
  </v-content>
</template>

<script>
export default {
}
</script>

<style>
#pop-up-toolbar {
  position: fixed;
  width: 100%;
  top: 0px;
  opacity: 0.8;
  z-index: 2;
}
.header-text {
    font-family: "Nanum Myeongjo",sans-serif;
    z-index: 3;
}
.header-title {
  margin-top: 0;
  font-size: 40px;
  font-weight: 400;
  color: #1a1a1a;
  /* color: grey; */
  text-align: left;
  letter-spacing: -.05em;
}
.header-body {
    padding-bottom: 16px;
    margin-top: -3px;
    font-size: 32px;
    line-height: 46px;
    font-family: "Nanum Myeongjo",sans-serif;
    color: #cacaca;
    /* color: white; */
    letter-spacing: -.05em;
}
</style>
