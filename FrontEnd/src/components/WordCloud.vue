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

am4core.useTheme(am4themes_kelly);
am4core.useTheme(am4themes_animated);

export default {
    name: 'wordcloud',
    mounted() {
        var chart = am4core.create("chartdiv", am4plugins_wordCloud.WordCloud);
        chart.fontFamily = "Courier New";
        var series = chart.series.push(new am4plugins_wordCloud.WordCloudSeries());
        series.randomness = 0.1;
        series.rotationThreshold = 0.5;

        series.data = [ {
            "tag": "삼성전자",
            "count": "1765836"
        }, {
            "tag": "삼성SDS",
            "count": "1517355"
        }, {
            "tag": "현대자동차",
            "count": "1287629"
        }, {
            "tag": "현대모비스",
            "count": "1263946"
        }, {
            "tag": "SK하이닉스",
            "count": "1174721"
        }, {
            "tag": "SK이노베이션",
            "count": "1116769"
        }, {
            "tag": "SK텔레콤",
            "count": "944983"
        }, {
            "tag": "LG화학",
            "count": "805679"
        }, {
            "tag": "LG전자",
            "count": "606051"
        }, {
            "tag": "롯데",
            "count": "591410"
        }, {
            "tag": "포스코",
            "count": "574684"
        }, {
            "tag": "한화",
            "count": "550916"
        }, {
            "tag": "GS칼텍스",
            "count": "479892"
        }, {
            "tag": "농협",
            "count": "343092"
        }, {
            "tag": "현대중공업",
            "count": "303311"
        }, {
            "tag": "신세계",
            "count": "296963"
        }, {
            "tag": "KT",
            "count": "288445"
        }, {
            "tag": "한진",
            "count": "286823"
        }, {
            "tag": "CJ",
            "count": "280079"
        }, {
            "tag": "두산",
            "count": "277144"
        }, {
            "tag": "부영",
            "count": "263451"
        }, {
            "tag": "LS",
            "count": "257159"
        }, {
            "tag": "대림",
            "count": "255661"
        }, {
            "tag": "미래에셋",
            "count": "253824"
        }, {
            "tag": "S-oil",
            "count": "222387"
        }, {
            "tag": "현대백화점",
            "count": "219827"
        }, {
            "tag": "효성",
            "count": "203121"
        }, {
            "tag": "한국투자금융",
            "count": "202547"
        }, {
            "tag": "대우조선해양",
            "count": "196727"
        }, {
            "tag": "영풍",
            "count": "191174"
        }, {
            "tag": "하림",
            "count": "188787"
        }, {
            "tag": "교보생명보험",
            "count": "180742"
        }, {
            "tag": "금호아시아나",
            "count": "178291"
        }, {
            "tag": "KT&G",
            "count": "173278"
        }, {
            "tag": "코오롱",
            "count": "154447"
        }, {
            "tag": "OCI",
            "count": "153581"
        }, {
            "tag": "카카오",
            "count": "147538"
        }, {
            "tag": "HDC",
            "count": "147456"
        }, {
            "tag": "KCC",
            "count": "145801"
        }, {
            "tag": "SM",
            "count": "145685"
        }, {
            "tag": "대우건설",
            "count": "139940"
        }, {
            "tag": "중흥건설",
            "count": "136649"
        }, {
            "tag": "한국타이어",
            "count": "130591"
        }, {
            "tag": "세아",
            "count": "127680"
        }, {
            "tag": "태광",
            "count": "125021"
        }, {
            "tag": "이랜드",
            "count": "122559"
        }, {
            "tag": "셀트리온",
            "count": "118810"
        }, {
            "tag": "DB",
            "count": "115802"
        }, {
            "tag": "호반건설",
            "count": "113719"
        }, {
            "tag": "네이버",
            "count": "110348"
        }, {
            "tag": "태영",
            "count": "109340"
        }, {
            "tag": "넥슨",
            "count": "108797"
        }, {
            "tag": "동원",
            "count": "108075"
        }, {
            "tag": "한라",
            "count": "106936"
        }, {
            "tag": "아모레퍼시픽",
            "count": "96225"
        }, {
            "tag": "삼천리",
            "count": "96027"
        }, {
            "tag": "한국지엠",
            "count": "94348"
        }, {
            "tag": "동국제강",
            "count": "92995"
        }, {
            "tag": "유진",
            "count": "92131"
        }, {
            "tag": "금호석유화학",
            "count": "90327"
        }, {
            "tag": "하이트진로",
            "count": "89670"
        }, {
            "tag": "넷마블",
            "count": "88762"
        }, {
            "tag": "애경",
            "count": "86971"
        }, {
            "tag": "다우키움",
            "count": "85825"
        }, {
            "tag": "기업1",
            "count": "84392"
        }, {
            "tag": "기업2",
            "count": "83948"
        }, {
            "tag": "기업3",
            "count": "83600"
        }, {
            "tag": "기업4",
            "count": "83367"
        }, {
            "tag": "기업5",
            "count": "83212"
        }, {
            "tag": "기업6",
            "count": "82452"
        }, {
            "tag": "기업7",
            "count": "81443"
        }, {
            "tag": "기업8",
            "count": "78250"
        }, {
            "tag": "기업9",
            "count": "78243"
        }, {
            "tag": "기업10",
            "count": "76123"
        }, {
            "tag": "기업11",
            "count": "74867"
        }, {
            "tag": "기업12",
            "count": "73128"
        }, {
            "tag": "기업13",
            "count": "72333"
        }, {
            "tag": "기업14",
            "count": "72043"
        }, {
            "tag": "기업15",
            "count": "71155"
        }, {
            "tag": "기업16",
            "count": "69552"
        }, {
            "tag": "기업17",
            "count": "69138"
        }, {
            "tag": "기업18",
            "count": "68854"
        }, {
            "tag": "기업19",
            "count": "67431"
        }, {
            "tag": "기업20",
            "count": "66411"
        }, {
            "tag": "기업21",
            "count": "66158"
        }, {
            "tag": "기업22",
            "count": "66113"
        }, {
            "tag": "기업23",
            "count": "65467"
        }, {
            "tag": "기업24",
            "count": "65014"
        }, {
            "tag": "기업25",
            "count": "64888"
        }, {
            "tag": "기업26",
            "count": "62783"
        }, {
            "tag": "기업27",
            "count": "62393"
        }, {
            "tag": "기업28",
            "count": "61909"
        }, {
            "tag": "기업29",
            "count": "61752"
        }, {
            "tag": "기업30",
            "count": "61015"
        }, {
            "tag": "기업31",
            "count": "60820"
        }, {
            "tag": "기업32",
            "count": "59855"
        }, {
            "tag": "기업33",
            "count": "59616"
        }, {
            "tag": "기업34",
            "count": "59600"
        }, {
            "tag": "기업35",
            "count": "59011"
        }, {
            "tag": "기업36",
            "count": "58916"
        }, {
            "tag": "기업37",
            "count": "58195"
        }, {
            "tag": "기업38",
            "count": "58055"
        }, {
            "tag": "기업39",
            "count": "57132"
        }, {
            "tag": "기업40",
            "count": "56836"
        }];

        series.dataFields.word = "tag";
        series.dataFields.value = "count";

        series.heatRules.push({
        "target": series.labels.template,
        "property": "fill",
        "min": am4core.color("#0000CC"),
        "max": am4core.color("#CC00CC"),
        "dataField": "value"
        });

        series.labels.template.url = "https://ko.wikipedia.org/wiki/{word}";
        series.labels.template.urlTarget = "_blank";
        series.labels.template.tooltipText = "{word}: {value}";

        var hoverState = series.labels.template.states.create("hover");
        hoverState.properties.fill = am4core.color("#FF0000");

        var subtitle = chart.titles.create();
        subtitle.text = "(click to open)";

        var title = chart.titles.create();
        title.text = "Most Popular Tags @ StackOverflow";
        title.fontSize = 20;
        title.fontWeight = "800";
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
}
</style>
