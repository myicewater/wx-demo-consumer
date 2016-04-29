function worthImage(x,y,data1,data2) {
    $('#container').highcharts({
        //图表区选项
        chart: {
			type:'spline',
             backgroundColor: 'rgba(255, 255, 255, 0)',  
                plotBorderColor : null,  
                plotBackgroundColor: null,  
                plotBackgroundImage:null,  
                plotBorderWidth: null,  
                plotShadow: false 
        },
		exporting:{
                    enabled:false
                },
                credits: {
                    enabled: false
                },
        //标题选项
        title: {
            text: ''
        },
        //数据点提示框
        tooltip: {
		formatter: function() {
				return '<b>'+ this.series.name +'</b><br/>'+
				this.x +': '+ this.y +'%';
		}
	},
     
        //数据列选项
		yAxis:{
			title: {
			text: ''
		},
		tickmarkPlacement:'on',	
		gridLineColor:'#d9d9d9',	
		tickPositions: y,
		plotLines: [{
			value: '',
			width: 1,
			color: '#808080'
		}]
			},
        xAxis: {
		 labels: {
			 		 step:1,
                    formatter:function(){ return this.value; }
			},
		 tickInterval: 5,	
		gridLineWidth:'1',
		gridLineDashStyle:'Dash',
		tickmarkPlacement:'on',	
		<!--y传值-->
		categories: x
	},
	legend: {
		layout: 'horizontal',
		align: 'center',
		verticalAlign: 'bottom',
		x: 0,
		y: 0,
		borderWidth: 0
	},
	plotOptions: {
		series: {
			marker: {
				enabled: false
			},
		connectNulls:true
		}
	},
	<!--x传值-->
	series: [{
		name: '复权净值',		
		data: data1,
		color:'#86a5c1'
		
		
	}, {
		name: '沪深300指数',
		data: data2,
		color:'#d86759'
	}]
	
    });
}
    
