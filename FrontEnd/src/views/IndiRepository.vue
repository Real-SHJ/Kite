<template>
<v-content>
    <v-layout justify-center row wrap>
        <v-flex xs11 style="text-align: right;">
            <h1 class="display-2 font-weight-thin mb=3"> Friends Search </h1>
            <!-- <h4 class="subheading">Find what your missing</h4> -->
        </v-flex>

        <v-flex xs11>
            <v-layout align-center justify-end="" row wrap>
                <v-flex xs2>
                    <v-form >
                        <v-text-field outline label="Search for firends" append-icon="search">
                        </v-text-field>
                    </v-form>
                </v-flex>
            </v-layout>
        </v-flex>
    </v-layout>
    <!-- ------------- 검색 기간 부분 --------------- -->
      <v-container fluid>
          <v-row>
            <br>검색기간
            <v-col cols="11" sm="2">
              <v-menu
                v-model="menu2"
                :close-on-content-click="false"
                :nudge-right="40"
                trasition="scale-transition"
                offset-y
                min-width="290px"
              >
                <template v-slot:activator="{ on }">
                  <v-text-field
                    v-model="date_search_start"
                    label="검색 시작일 - Calendar Click"
                    prepend-icon="event"
                    readonly
                    v-on="on"
                  > </v-text-field>
                </template>
                <v-date-picker v-model="date_search_start"
                  year-icon="mdi-calendar-blank"
                  prev-icon="mdi-skip-previous"
                  next-icon="mdi-skip-next"
                  @input="menu2 = false"> </v-date-picker>
              </v-menu>
            <v-spacer></v-spacer>
            </v-col>
            <br>~
            <v-col cols="11" sm="2">
              <v-menu
                v-model="menu2"
                :close-on-content-click="false"
                :nudge-right="40"
                trasition="scale-transition"
                offset-y
                min-width="290px"
              >
                <template v-slot:activator="{ on }">
                  <v-text-field
                    v-model="date_search_end"
                    label="검색 마지막날 - Calendar Click"
                    prepend-icon="event"
                    readonly
                    v-on="on"
                  > </v-text-field>
                </template>
                <v-date-picker v-model="date_search_end"
                  year-icon="mdi-calendar-blank"
                  prev-icon="mdi-skip-previous"
                  next-icon="mdi-skip-next"
                  @input="menu2 = false"> </v-date-picker>
              </v-menu>
            <v-spacer></v-spacer>
            </v-col>
            <v-col
              cols="12"
              sm="6"
              class="py-2"
            >
              <v-btn-toggle
                v-model="text"
                tile
                color="deep-purple accent-3"
                group
              >
                <v-btn value="all">
                  전체
                </v-btn>

                <v-btn value="oneday">
                  1일
                </v-btn>

                <v-btn value="oneweek">
                  1주
                </v-btn>

                <v-btn value="onemonth">
                  1개월
                </v-btn>

                <v-btn value="threemonth">
                  3개월
                </v-btn>

                <v-btn value="sixmonth">
                  6개월
                </v-btn>

                <v-btn value="oneyears">

                </v-btn>
              </v-btn-toggle>
              <v-btn depressed large color="pink white--text">검색</v-btn>
            </v-col>
          </v-row>
          </v-container>
    <!-- ------------- 검색 기간 부분 --------------- -->

    <!-- ------------- 1. 전체 (워드 클라우드, 관련뉴스 부분) start--------------- -->
      <v-container fluid>
      <v-layout >
        <v-row>
        <!-- ------------- 2. 워드 클라우드 부분 start --------------- -->
        <v-flex xs12 sm8 md8>
          <v-container fluid>
            <router-link to="/wordcloud" replace>키워드</router-link>
            <div id="chartdiv_keyword"></div>
          </v-container>
        </v-flex>
        <!-- ------------- 2. 워드 클라우드 부분 end --------------- -->

        <!-- ------------- 3. 연관 관계 그래프 부분 start ----------------- -->
          <v-flex xs12 sm8 md8>
          <v-container fluid>
            <router-link to="/forcedirected" replace>연관 관계 그래프</router-link>
            <div id="chartdiv_searching"></div>
          </v-container>
        </v-flex>
        <!-- ------------- 3. 연관 관계 그래프 부분 end ----------------- -->
        </v-row>
        <!-- ------------- 4. 최신뉴스, 관련뉴스 부분 start--------------- -->
        <v-flex xs12 sm4 md4>
          <v-container fluid>
            <v-card color="basil">
              <v-tabs
                v-model="tab"
                background-color="transparent"
                color="basil"
                grow
              >
                <v-tab
                  v-for="item in items"
                  :key="item"
                >
                  {{ item }}
                </v-tab>
              </v-tabs>

              <v-tabs-items v-model="tab">
                <v-tab-item
                  v-for="item in items"
                  :key="item"
                >
                  <v-card
                    color="basil"
                    flat
                  >
                    <v-card-text>{{ text }}</v-card-text>
                  </v-card>
                </v-tab-item>
              </v-tabs-items>
                <!-- ---------- 5. 카드 내부 기사 ----------- -->
              <v-container>
                <v-row dense>
                  <v-col cols="12">
                    <v-card
                      color="#385F73"
                      dark
                    >
                      <v-card-title class="headline">Unlimited music now</v-card-title>

                      <v-card-subtitle>Listen to your favorite artists and albums whenever and wherever, online and offline.</v-card-subtitle>

                      <v-card-actions>
                        <v-btn text>Listen Now</v-btn>
                      </v-card-actions>
                    </v-card>
                  </v-col>

                  <v-col
                    v-for="(item, i) in items2"
                    :key="i"
                    cols="12"
                  >
                    <v-card
                      :color="item.color"
                      dark
                    >
                      <div class="d-flex flex-no-wrap justify-space-between">

                        <v-avatar
                          class="ma-2"
                          size="125"
                          tile
                        >
                          <v-img :src="item.src"></v-img>
                        </v-avatar>
                        <div>
                          <v-card-title
                            {{article}}
                          ></v-card-title>

                          <v-card-subtitle v-text="item.artist"></v-card-subtitle>
                        </div>

                      </div>
                    </v-card>
                  </v-col>

                  <v-col
                    v-for="(item, i) in items2"
                    :key="i"
                    cols="12"
                  >
                    <v-card
                      :color="item.color"
                      dark
                    >
                      <div class="d-flex flex-no-wrap justify-space-between">

                        <v-avatar
                          class="ma-2"
                          size="125"
                          tile
                        >
                          <v-img :src="item.src"></v-img>
                        </v-avatar>
                        <div>
                          <v-card-title
                            class="headline"
                            v-text="item.title"
                          ></v-card-title>

                          <v-card-subtitle v-text="item.artist"></v-card-subtitle>
                        </div>

                      </div>
                    </v-card>
                  </v-col>

                </v-row>
              </v-container>
              <!-- ---------- 5. 카드 내부 기사 ----------- -->
            </v-card>
          </v-container>
        </v-flex>
        <!-- ------------- 4. 최신뉴스, 관련뉴스 부분 end--------------- -->
    <!-- ------------- 1. 전체 (워드 클라우드, 관련뉴스 부분) end --------------- -->
      </v-layout>
      </v-container>
</v-content>
</template>

<!-- Resources - tagcloud -->
<script src="https://www.amcharts.com/lib/4/core.js"></script>
<script src="https://www.amcharts.com/lib/4/charts.js"></script>
<script src="https://www.amcharts.com/lib/4/plugins/wordCloud.js"></script>
<script src="https://www.amcharts.com/lib/4/themes/kelly.js"></script>
<script src="https://www.amcharts.com/lib/4/themes/animated.js"></script>

<!-- Resources - 연관 관계 그래프 -->
<!-- <script src="https://www.amcharts.com/lib/4/core.js"></script> -->
<!-- <script src="https://www.amcharts.com/lib/4/charts.js"></script> -->
<script src="https://www.amcharts.com/lib/4/plugins/forceDirected.js"></script>
<!-- <script src="https://www.amcharts.com/lib/4/themes/kelly.js"></script> -->
<!-- <script src="https://www.amcharts.com/lib/4/themes/animated.js"></script> -->

<script>
// import wordcloud from 'vue-wordcloud'
import * as am4core from "@amcharts/amcharts4/core";
import * as am4charts from "@amcharts/amcharts4/charts";
import * as am4plugins_wordCloud from "@amcharts/amcharts4/plugins/wordCloud";
import * as am4plugins_forceDirected from "@amcharts/amcharts4/plugins/forceDirected";
import am4themes_kelly from "@amcharts/amcharts4/themes/kelly";
import am4themes_animated from "@amcharts/amcharts4/themes/animated";

am4core.useTheme(am4themes_kelly);
am4core.useTheme(am4themes_animated);

export default {
  name: 'wordcloud',
  mounted() {
        var chart = am4core.create("chartdiv_keyword", am4plugins_wordCloud.WordCloud);
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

        // ----------------------  연관 관계 그래프 부분 ---------------------------  //
        var chart = am4core.create("chartdiv_searching", am4plugins_forceDirected.ForceDirectedTree);

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
        // ----------------------  연관 관계 그래프 부분 ---------------------------  //
    },
    beforeDestroy() {
        if (this.chart) {
            this.chart.dispose();
        }
    },
  components: {
    // wordcloud
  },
  methods: {
    wordClickHandler (name, value, vm) {
      console.log('wordClickHandler', name, value, vm)
    },
    getArticle () {
      http.get('article/list/')
        .then(res => {
          //토큰 저장
            console.log(res)
            this.articles = res.data.resvalue
        })
        .catch(err => console.log(err))
    }
  },
  data () {
    return {
      items: [
        '최신 뉴스', '관련 뉴스'
      ],
      text: '뉴스뉴스뉴스뉴스뉴스뉴스',
      items2: [
        {
          color: '#1F7087',
          src: 'https://cdn.vuetifyjs.com/images/cards/foster.jpg',
          title: 'Supermodel',
          artist: 'Foster the People'
        },
        {
          color: '#952175',
          src: 'https://cdn.vuetifyjs.com/images/cards/halcyon.png',
          title: 'Halcyon Days',
          artist: 'Ellie Goulding'
        },
        {
          color: '#1F7087',
          src: 'https://cdn.vuetifyjs.com/images/cards/foster.jpg',
          title: 'Supermodel',
          artist: 'Foster the People'
        },
        {
          color: '#952175',
          src: 'https://cdn.vuetifyjs.com/images/cards/halcyon.png',
          title: 'Halcyon Days',
          artist: 'Ellie Goulding'
        },
      ],
      articles: []
    },
    this.getArticle()
  }
}
</script>

<style>
.basil {
  background-color: #FFFBE6 !important;
}
.basil--text {
  color: #356859 !important;
}
#chartdiv_keyword {
  width: 100%;
  height: 600px;
}
#chartdiv_searching {
  width: 100%;
  height: 600px;
}
</style>
