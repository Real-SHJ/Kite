<!-- HTML -->
<template>
  <div id="keywordchartdiv" />
</template>

<!-- Resources -->
<script src="https://www.amcharts.com/lib/4/core.js"></script>
<script src="https://www.amcharts.com/lib/4/charts.js"></script>
<script src="https://www.amcharts.com/lib/4/plugins/wordCloud.js"></script>
<script src="https://www.amcharts.com/lib/4/themes/kelly.js"></script>
<script src="https://www.amcharts.com/lib/4/themes/animated.js"></script>

<!-- Chart code -->
<script>
import * as am4core from "@amcharts/amcharts4/core";
import * as am4charts from "@amcharts/amcharts4/charts";
import * as am4plugins_wordCloud from "@amcharts/amcharts4/plugins/wordCloud";
import am4themes_kelly from "@amcharts/amcharts4/themes/kelly";
import am4themes_animated from "@amcharts/amcharts4/themes/animated";
import http from '../http-common'

am4core.useTheme(am4themes_kelly);
am4core.useTheme(am4themes_animated);

export default {
    name: 'companykeyword',
    props:{
        keywordinfo: Array,
        choice_company: String
    },
    data () {
        return {
            mykeyword: null
        }
    },
    watch: {
        keywordinfo : function(){
            var keywordchartdiv = am4core.create("keywordchartdiv", am4plugins_wordCloud.WordCloud);
            setTimeout(()=>{
                keywordchartdiv.fontFamily = "Courier New";
                var series = keywordchartdiv.series.push(new am4plugins_wordCloud.WordCloudSeries());
                series.randomness = 0.4;
                series.rotationThreshold = 0.5;
                series.data = null;
                for(let index in this.keywordinfo){
                    var tmp = { "tag" : this.keywordinfo[Number(index)]["keyword"], "count" : parseInt(this.keywordinfo[Number(index)]["count"]) }
                    series.data.push(tmp)
                }

                series.dataFields.word = "tag";
                series.dataFields.value = "count";

                series.heatRules.push({
                    "target": series.labels.template,
                    "property": "fill",
                    "min": am4core.color("#0000CC"),
                    "max": am4core.color("#CC00CC"),
                    "dataField": "value"
                });
                // series.labels.template.url = "https://ko.wikipedia.org/wiki/{word}";
                series.labels.template.events.on("hit", function(ev) {
                    console.log("clicked on ", ev.target.readerTitle);
                    this.mykeyword = ev.target.readerTitle;
                    console.log("내가 선택한 키워드는:" + this.mykeyword)
                    this.$emit('myKeyword', this.mykeyword)
                }, this);
                series.labels.template.urlTarget = "_blank";
                series.labels.template.tooltipText = "{word}: {value}";
                var hoverState = series.labels.template.states.create("hover");
                hoverState.properties.fill = am4core.color("#FF0000");

                var subtitle = keywordchartdiv.titles.create();
                subtitle.text = "(click to open)";

                var title = keywordchartdiv.titles.create();
                title.text = this.choice_company + '\n기사 연관 키워드';
                title.fontSize = 30;
                title.fontWeight = "800";
                title.paddingTop = "20";

                series.events.on("arrangestarted", function(ev) {
                ev.target.baseSprite.preloader.show(0);
                });

                series.events.on("arrangeprogress", function(ev) {
                ev.target.baseSprite.preloader.progress = ev.progress;
                });
            }, 1000)
        }
    },
    beforeDestroy() {
        if (this.keywordchartdiv) {
            this.keywordchartdiv.dispose();
        }
    }
}
</script>

<!-- Styles -->
<style scoped>
#keywordchartdiv {
  width: 100%;
  height: 600px;
  background-color : #F5F5F5;
}
</style>
