<template>
  <v-content>
    <!-- <div style="background-color: black; height: 200px;"></div> -->
    <div style="position: relative; width:; height: 368px;">
      <div style="background-color: black; opacity: 0; position: absolute; width: 100%; height: 400px; z-index: 2;" />
      <img
        src="../assets/Main_Image.png"
        alt=""
        style="width: 100%; height: 100%; position: absolute; z-index: 1;"
      >
      <!-- <img src="https://www.designmap.or.kr:10443/images/renew/main_background_img.jpg" alt="" style="width: 100%; height: 100%; position: absolute; z-index: 1;"> -->
    </div>
    <!-- <p class="header-title" style="position: absolute; z-index: 2; top: 8%; left: 25%;"><strong>최신 기업 정보, KITE </strong></p>
        <span class="header-title2" style="position: absolute; z-index: 2; top: 47%; right: 29%; ">KITE에서 나의 관심 기업 기사를 확인하세요.<br></span>
        <span class="header-title2" style="position: absolute; z-index: 2; top: 60%; right: 38% ">그리고 다시 꺼내 보세요.<br></span>
        <span class="header-title2" style="position: absolute; z-index: 2; top: 73%; right: 40% ">손쉬운 스크랩, 공유...<br></span> -->

    <!-- <div class="header-text my-4">
        <p class="header-title">최신 기업 정보, KITE </p>
        <p class="header-body">
          <span class="part">KITE에서 나의 관심 기업 기사를 확인하세요.<br></span>
          <span class="part">그리고 다시 꺼내 보세요.<br></span>
          <span class="part">
          <span class="txt_brunch">손쉬운 스크랩, 공유...</span>
          </span>
        </p>
      </div> -->
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
    text-align: center;
    z-index: 3;
}
/* .header-title {
  margin-top: 0;
  font-size: 60px;
  font-weight: 400;
  color: #ffffff;
  text-align: left;
  letter-spacing: -.05em;
} */
.header-title2 {
  margin-top: 0;
  font-size: 40px;
  font-weight: 400;
  color: #ffffff;
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
    letter-spacing: -.05em;
}
</style>
