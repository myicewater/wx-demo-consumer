
  $(document).ready(function(){
    
	 ulTab();
	//$('.model li:first-child').css('border-bottom','1px solid #f60');
	//$('.model li:first-child').find('a').css('color','#f60');
	//$('.model li').on('click',function(){			
	//	$(this).css('border-bottom','1px solid #f60').siblings().css('border-bottom','none');	
	//	$(this).find('a').css('color','#f60').parent().siblings().find('a').css('color','#000');	
	//	})		 		
  });

 
		
		
		
	
	

 function ulTab(){
	 $('.page dd:first-child').show();
	 $('#mos li').on('click',function(){
		var index=$(this).index();
		//alert(index); 
		$('.page dd').eq(index).show().siblings().hide();
		//alert($('#mos li').find('span').length);
		var i=index+1;
		//alert(i)
		if(i==1){
			$('#mos li').find('span').eq(0).html('<img src="../images/blue'+i+'.png">');
			$('#mos li').find('b').eq(0).css('color','#0082b4');
			$('#mos li').find('span').eq(1).html('<img src="../images/black2.png">');
			$('#mos li').find('span').eq(2).html('<img src="../images/black3.png">');
			$('#mos li').find('b').not($('#mos li').find('b').eq(0)).css('color','#000');
		}else if(i==2){
			
			$('#mos li').find('span').eq(1).html('<img src="../images/blue'+i+'.png">');
			$('#mos li').find('b').eq(1).css('color','#0082b4');
			$('#mos li').find('span').eq(0).html('<img src="../images/black1.png">');
			$('#mos li').find('span').eq(2).html('<img src="../images/black3.png">');
			$('#mos li').find('b').not($('#mos li').find('b').eq(1)).css('color','#000');
		}else if(i==3){
			$('#mos li').find('span').eq(2).html('<img src="../images/blue'+i+'.png">');
			$('#mos li').find('b').eq(2).css('color','#0082b4');
			$('#mos li').find('span').eq(1).html('<img src="../images/black2.png">');
			$('#mos li').find('span').eq(0).html('<img src="../images/black1.png">');
			$('#mos li').find('b').not($('#mos li').find('b').eq(2)).css('color','#000');
			}
			
		
		
	  })
	 
 	}

 
 function worthImage(xreal, data1real, data2real, max, min, proDesc) {
		var xnum = Math.floor(xreal.length / 6);
		$('#container').highcharts({
			// 图表区选项
			chart : {
				type : 'spline',
				backgroundColor : 'rgba(255, 255, 255, 0)',
				plotBorderColor : null,
				plotBackgroundColor : null,
				plotBackgroundImage : null,
				plotBorderWidth : null,
				plotShadow : false
			},
			exporting : {
				enabled : false
			},
			credits : {
				enabled : false
			},
			// 标题选项
			title : {
				text : ''
			},
			// 数据点提示框
			tooltip : {
				formatter : function() {
					return '<b>' + this.series.name + '</b><br/>' + this.x + ': ' + this.y.toFixed(2) + '%';
				}
			},

			// 数据列选项
			yAxis : {
				title : {
					text : ''
				},
				tickmarkPlacement : 'on',
				gridLineColor : '#d9d9d9',
				labels: {
	                formatter: function() {
	                    return this.value +'%';
	                }
	            },
	            //maxPadding: 0.05,
	            showLastLabel: true,
				
				max: max,
				min: min,
				tickPixelInterval: 10,
				plotLines : [ {
					value : '',
					width : 0.1,
					color : '#808080'
				} ]
			},
			xAxis : {
				labels : {
					step : 1,
					formatter : function() {
						return this.value;
					}
				},
				tickInterval : xnum,
				gridLineWidth : '1',
				gridLineDashStyle : 'Dash',
				tickmarkPlacement : 'on',
				// y传值
				categories : xreal
			},
			legend : {
				layout : 'horizontal',
				align : 'center',
				verticalAlign : 'bottom',
				x : 0,
				y : 0,
				borderWidth : 0
			},
			plotOptions : {
				series : {
			    	marker : {
						enabled : false
					},
					connectNulls : true
				}
			},
			// x传值
			series : [{
				name : proDesc,
				data : data1real,
				color : '#86a5c1'
			}, 
			{
				name : '沪深300指数',
				data : data2real,
				color : '#d86759'
			}]
		});
	}