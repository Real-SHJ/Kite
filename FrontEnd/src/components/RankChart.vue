<template>
  <div>
    <div class="mb-6">
      <v-menu offset-y>
        <template v-slot:activator="{ on }">
          <v-btn
            color="green"
            dark
            v-on="on"
          >
            항목을 선택하세요.
          </v-btn>
        </template>
        <v-list>
          <v-list-item
            v-for="(item, index) in items"
            :key="index"
            @click="getChart(item.title)"
          >
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item>
        </v-list>
      </v-menu>
    </div>
    <p
      id="title2"
      style="margin-bottom: 10px;"
    >
      {{ title }}
    </p>
    <div
      id="chartdiv"
      style="width:100%;"
    />
  </div>
</template>

<!-- Resources -->
<script src="https://www.amcharts.com/lib/4/core.js"></script>
<script src="https://www.amcharts.com/lib/4/charts.js"></script>
<script src="https://www.amcharts.com/lib/4/themes/animated.js"></script>

<script>
import * as am4core from "@amcharts/amcharts4/core";
import * as am4charts from "@amcharts/amcharts4/charts";
import am4themes_animated from "@amcharts/amcharts4/themes/animated";

am4core.useTheme(am4themes_animated);
export default {
    name: 'rankchart',
    data() {
        return {
            items: [],
            chart: "",
            title: ""
        }
    },
    props:{
        info: Object
    },
    mounted() {
        this.chart = am4core.create("chartdiv", am4charts.RadarChart);
        //json 형식의 데이터를 배열 data에 append 해주면 끝날듯...
        setTimeout(()=>{
            this.makedropdownData()
        // this.chart.data = [{
        // "company": "USA",
        // "counts": 2025
        // }, {
        // "company": "China",
        // "counts": 1882
        // }, {
        // "company": "Japan",
        // "counts": 1809
        // }, {
        // "company": "Germany",
        // "counts": 1322
        // }];

        this.chart.innerRadius = am4core.percent(40)

        var categoryAxis = this.chart.xAxes.push(new am4charts.CategoryAxis());
        categoryAxis.renderer.grid.template.location = 0;
        categoryAxis.dataFields.category = "company";
        categoryAxis.renderer.minGridDistance = 60;
        categoryAxis.renderer.inversed = true;
        categoryAxis.renderer.labels.template.location = 0.5;
        categoryAxis.renderer.grid.template.strokeOpacity = 0.08;

        var valueAxis = this.chart.yAxes.push(new am4charts.ValueAxis());
        valueAxis.min = 0;
        valueAxis.extraMax = 0.1;
        valueAxis.renderer.grid.template.strokeOpacity = 0.08;

        this.chart.seriesContainer.zIndex = -10;

        var series = this.chart.series.push(new am4charts.RadarColumnSeries());
        series.dataFields.categoryX = "company";
        series.dataFields.valueY = "counts";
        series.tooltipText = "{valueY.value}"
        series.columns.template.strokeOpacity = 0;
        series.columns.template.radarColumn.cornerRadius = 5;
        series.columns.template.radarColumn.innerCornerRadius = 0;

        this.chart.zoomOutButton.disabled = true;

        // as by default columns of the same series are of the same color, we add adapter which takes colors from chart.colors color set
        series.columns.template.adapter.add("fill", (fill, target) => {
        return this.chart.colors.getIndex(target.dataItem.index);
        });
        categoryAxis.sortBySeries = series;

        this.chart.cursor = new am4charts.RadarCursor();
        this.chart.cursor.behavior = "none";
        this.chart.cursor.lineX.disabled = true;
        this.chart.cursor.lineY.disabled = true;
        }, 1000)
     },
    beforeDestroy() {
        if (this.chart) {
            this.chart.dispose();
        }
    },
    methods:{
        getChart(property){
        this.title=property
        this.chart = am4core.create("chartdiv", am4charts.RadarChart);
        //json 형식의 데이터를 배열 data에 append 해주면 끝날듯...
        this.makeData(property)

        this.chart.innerRadius = am4core.percent(40)

        var categoryAxis = this.chart.xAxes.push(new am4charts.CategoryAxis());
        categoryAxis.renderer.grid.template.location = 0;
        categoryAxis.dataFields.category = "company";
        categoryAxis.renderer.minGridDistance = 60;
        categoryAxis.renderer.inversed = true;
        categoryAxis.renderer.labels.template.location = 0.5;
        categoryAxis.renderer.grid.template.strokeOpacity = 0.08;

        var valueAxis = this.chart.yAxes.push(new am4charts.ValueAxis());
        valueAxis.min = 0;
        valueAxis.extraMax = 0.1;
        valueAxis.renderer.grid.template.strokeOpacity = 0.08;

        this.chart.seriesContainer.zIndex = -10;

        var series = this.chart.series.push(new am4charts.RadarColumnSeries());
        series.dataFields.categoryX = "company";
        series.dataFields.valueY = "counts";
        series.tooltipText = "{valueY.value}"
        series.columns.template.strokeOpacity = 0;
        series.columns.template.radarColumn.cornerRadius = 5;
        series.columns.template.radarColumn.innerCornerRadius = 0;

        this.chart.zoomOutButton.disabled = true;

        // as by default columns of the same series are of the same color, we add adapter which takes colors from chart.colors color set
        series.columns.template.adapter.add("fill", (fill, target) => {
        return this.chart.colors.getIndex(target.dataItem.index);
        });

        categoryAxis.sortBySeries = series;

        this.chart.cursor = new am4charts.RadarCursor();
        this.chart.cursor.behavior = "none";
        this.chart.cursor.lineX.disabled = true;
        this.chart.cursor.lineY.disabled = true;
        },
        makedropdownData(){
            for(const property in this.info){
                var tmp={"title" : property}
                this.items.push(tmp)
                // console.log(property)
            }

        },
        makeData(property){
            this.chart.data=[]
            // console.log("makedata진입")
        //    for (const property in this.info) {
        //        if(cnt==1)
        //         break;
        //         console.log("!!!!"+property)
                for(const data in this.info[property]){
                //  console.log("@@@@"+"company" +" "+ this.info[property][Number(data)]["company"]+" "+ "count" +" "+ this.info[property][Number(data)]["count"])
                 var tmp={"company" : this.info[property][Number(data)]["company"], "counts" : parseInt(this.info[property][Number(data)]["count"]) }
                 this.chart.data.push(tmp)
                }
                // console.log("!@#$"+this.chart.data)
        //    }
        }
    }
}
</script>

<style scoped>
body {
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
}
#title2 {
    font-size: 200%;
    font-family: 'Oswald', sans-serif;
    font-weight: bold;
    text-align: center
}
#chartdiv {
  width: 100%;
  height: 500px;
}
.chart{
  width: 100%;
  height: 500px;
}
.chart{
  width: 100%;
}
</style>
