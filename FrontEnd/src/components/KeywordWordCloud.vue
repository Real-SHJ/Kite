<!-- HTML -->
<template>
  <div id="chartdiv" />
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
    name: 'keywordwordcloud',
    props:{
        info: Array
    },
    data () {
        return {
            mykeyword: null
        }
    },
    mounted () {
        var chart = am4core.create("chartdiv", am4plugins_wordCloud.WordCloud);
        setTimeout(()=>{
            chart.fontFamily = "Courier New";
            var series = chart.series.push(new am4plugins_wordCloud.WordCloudSeries());
            series.randomness = 0.4;
            series.rotationThreshold = 0.5;
            series.data = null;
            for(let index in this.info){
                var tmp = { "tag" : this.info[Number(index)]["keyword"], "count" : parseInt(this.info[Number(index)]["count"]) }
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
                this.mykeyword = ev.target.readerTitle;
                this.$emit('myKeyword', this.mykeyword)
            }, this);
            series.labels.template.urlTarget = "_blank";
            series.labels.template.tooltipText = "{word}: {value}";
            var hoverState = series.labels.template.states.create("hover");
            hoverState.properties.fill = am4core.color("#FF0000");

            var subtitle = chart.titles.create();
            subtitle.text = "(click to open)";

            var title = chart.titles.create();
            title.text = "내 키워드";
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
    },
    beforeDestroy() {
        if (this.chart) {
            this.chart.dispose();
        }
    }
}
</script>

<!-- Styles -->
<style scoped>
#chartdiv {
  width: 100%;
  height: 600px;
  background-color: #FFFBE6;
}
</style>
