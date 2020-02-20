<!-- HTML -->
<template>
  <div id="chartdiv" />
</template>

<!-- Resources -->
<script src="https://www.amcharts.com/lib/4/core.js"></script>
<script src="https://www.amcharts.com/lib/4/charts.js"></script>
<script src="https://www.amcharts.com/lib/4/plugins/forceDirected.js"></script>
<script src="https://www.amcharts.com/lib/4/themes/kelly.js"></script>
<script src="https://www.amcharts.com/lib/4/themes/animated.js"></script>
<!-- Chart code -->
<script>
import * as am4core from "@amcharts/amcharts4/core";
import * as am4charts from "@amcharts/amcharts4/charts";
import * as am4plugins_forceDirected from "@amcharts/amcharts4/plugins/forceDirected";
import am4themes_kelly from "@amcharts/amcharts4/themes/kelly";
import am4themes_animated from "@amcharts/amcharts4/themes/animated";

am4core.useTheme(am4themes_kelly);
am4core.useTheme(am4themes_animated);

export default {
    name: 'forcedirected',
    mounted() {
        var chart = am4core.create("chartdiv", am4plugins_forceDirected.ForceDirectedTree);

        var networkSeries = chart.series.push(new am4plugins_forceDirected.ForceDirectedSeries())
        networkSeries.dataFields.linkWith = "linkWith";
        networkSeries.dataFields.name = "name";
        networkSeries.dataFields.id = "name";
        networkSeries.dataFields.value = "value";
        networkSeries.dataFields.children = "children";

        networkSeries.nodes.template.label.text = "{name}"
        networkSeries.fontSize = 8;
        networkSeries.linkWithStrength = 0;

        var nodeTemplate = networkSeries.nodes.template;
        nodeTemplate.tooltipText = "{name}";
        nodeTemplate.fillOpacity = 1;
        nodeTemplate.label.hideOversized = true;
        nodeTemplate.label.truncate = true;

        var linkTemplate = networkSeries.links.template;
        linkTemplate.strokeWidth = 1;
        var linkHoverState = linkTemplate.states.create("hover");
        linkHoverState.properties.strokeOpacity = 1;
        linkHoverState.properties.strokeWidth = 2;

        nodeTemplate.events.on("over", function (event) {
            var dataItem = event.target.dataItem;
            dataItem.childLinks.each(function (link) {
                link.isHover = true;
            })
        })

        nodeTemplate.events.on("out", function (event) {
            var dataItem = event.target.dataItem;
            dataItem.childLinks.each(function (link) {
                link.isHover = false;
            })
        })

        networkSeries.data = [
        {
            "name":"삼성",
            "value":500,
            "linkWith":[
                "현대",
                "SK",
                "LG",
                "카카오",
                "네이버"

            ],
            "children":[
                {
                    "name":"삼성그룹",
                    "value":490
                },
                {
                    "name":"삼성전자",
                    "value":490
                },
                {
                    "name":"삼성SDS",
                    "value":480
                },
                {
                    "name":"이재용",
                    "value":400
                },
                {
                    "name":"반도체",
                    "value":300
                },
                {
                    "name":"갤럭시폴드",
                    "value":300
                },
                {
                    "name":"스마트폰",
                    "value":300
                },
                {
                    "name":"삼성생명",
                    "value":200
                },
                {
                    "name":"삼성물산",
                    "value":200
                },
                {
                    "name":"코스피",
                    "value":150
                },
                {
                    "name":"영업이익",
                    "value":150
                },
                {
                    "name":"에스원",
                    "value":150
                }
            ]
        },
        {
            "name":"현대",
            "value":400,
            "linkWith":[
                "삼성",
                "SK",
                "영업이익",
                "코스피"
            ],
            "children":[
                {
                    "name":"현대자동차",
                    "value":350
                },
                {
                    "name":"현대모비스",
                    "value":350
                },
                {
                    "name":"쏘나타",
                    "value":200
                },
                {
                    "name":"정의선",
                    "value":150
                },
                {
                    "name":"쏘나타 하이브리드",
                    "value":130
                }
            ]
        },
        {
            "name":"SK",
            "value":300,
            "linkWith":[
                "삼성",
                "현대",
                "LG"
            ],
            "children":[
                {
                    "name":"SK 하이닉스",
                    "value":250
                },
                {
                    "name":"SK 이노베이션",
                    "value":250
                },
                {
                    "name":"SK 브로드밴드",
                    "value":200
                },
                {
                    "name":"SK 텔레콤",
                    "value":250
                }
            ]
        },
        {
            "name":"LG",
            "value":200,
            "linkWith":[
                "삼성",
                "SK"
            ],
            "children":[
                {
                    "name":"LG전자",
                    "value":180
                },
                {
                    "name":"LG유플러스",
                    "value":180
                },
                {
                    "name":"LG화학",
                    "value":180
                },
                {
                    "name":"V40",
                    "value":170
                },
                {
                    "name":"TV",
                    "value":100
                },
                {
                    "name":"시그니처 가전",
                    "value":110
                }
            ]
        },
        {
            "name":"카카오",
            "value":158,
            "linkWith":[
                "Chandler",
                "Ross",
                "Joey",
                "Phoebe",
                "Mr Geller",
                "Mrs Geller"
            ],
            "children":[
                {
                    "name":"카카오톡",
                    "value":130
                },
                {
                    "name":"헤이카카오",
                    "value":120
                },
                {
                    "name":"네오",
                    "value":60
                },
                {
                    "name":"무지",
                    "value":60
                },
                {
                    "name":"어피치",
                    "value":60
                },
                {
                    "name":"콘",
                    "value":60
                },
                {
                    "name":"라이언",
                    "value":60
                },
                {
                    "name":"제주도",
                    "value":70
                },
                {
                    "name":"다음",
                    "value":80
                },
                {
                    "name":"카카오페이",
                    "value":3
                }
            ]
        },
        {
            "name":"네이버",
            "value":130,
            "linkWith":[
                "Phoebe",
                "Janice",
                "Mrs Green",
                "Kathy",
                "Emily",
                "Charlie"
            ],
            "children":[
                {
                    "name":"메일",
                    "value":80
                },
                {
                    "name":"그린닷",
                    "value":40
                },
                {
                    "name":"검색",
                    "value":90
                },
                {
                    "name":"실시간검색어",
                    "value":50
                },
                {
                    "name":"음악검색",
                    "value":40
                },
                {
                    "name":"QR코드",
                    "value":40
                }
            ]
        }
        ];
    },
    beforeDestroy() {
        if (this.chart) {
            this.chart.dispose();
        }
    }
}
</script>

<style scoped>
#chartdiv {
    width: 100%;
    height:550px;
}
</style>
