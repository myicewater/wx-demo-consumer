
var myScroll,
	pullUpEl, pullUpOffset;
function pullUpAction () {
	setTimeout(function () {
		var div, dl, i;
		dl = document.getElementById('page');

		for (i=0; i<3; i++) {
			div = document.createElement('div');
			div.innerHTML ="<div class='home'><h1>中融信托证券-展博二期</h1><ul><li><a href='#'><b style='padding-top:6px;'>5%-9%</b><span>收益率</span></a></li><li><a href='#'><b style='padding-top:6px;'>12<dd style='display:inline-block; color:#999; font-family:'微软雅黑';font-size:0.85em; font-weight:normal;'>个月</dd></b><span>投资期限</span></a></li><li><a href='#'><b style='font-size:1.45em;'>8.5<dd style='display:inline-block; color:#333; font-size:0.6em;'>%</dd></b><span>服务费率</span></a></li></ul></div>";
                                   
			dl.insertBefore(div, dl.lastchild);
		}
		
		myScroll.refresh();
	}, 1000);
}

function loaded() {
	pullUpEl = document.getElementById('pullUp');	
	pullUpOffset = pullUpEl.offsetHeight;
	
	myScroll = new iScroll('wrapper', {
		useTransition: true,
		
		onRefresh: function () {
			 if (pullUpEl.className.match('loading')) {
				pullUpEl.className = '';
				
			}
		},
		onScrollMove: function () {
			if (this.y < (this.maxScrollY - 5) && !pullUpEl.className.match('flip')) {
				pullUpEl.className = 'flip';
				
				this.maxScrollY = this.maxScrollY;
			} else if (this.y > (this.maxScrollY + 5) && pullUpEl.className.match('flip')) {
				pullUpEl.className = '';
				
				this.maxScrollY = pullUpOffset;
			}
		},
		onScrollEnd: function () {
			 if (pullUpEl.className.match('flip')) {
				pullUpEl.className = 'loading';
								
				pullUpAction();	// Execute custom function (ajax call?)
			}
		}
	});
	
	setTimeout(function () { document.getElementById('wrapper').style.left = '0'; }, 800);
}

document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);

document.addEventListener('DOMContentLoaded', function () { setTimeout(loaded, 200); }, false);


$(function(){
	//$('header li')
	$('header li').eq(0).on('click',function(){
		//alert($(this).find('img').attr('src'));
		if($(this).find('img').attr('src')=='../images/xs_03.png'){
		$('#top_list1').show();
		$('#top_list2').hide();
		$('.me_ban').show();
		$(this).find('img').attr('src','../images/ss_03.png');
		$('header li').eq(1).find('img').attr('src','../images/xs_03.png');
		return $(this).find('img').attr('src');
		}else{
			$('#top_list1').hide();
			$('.me_ban').hide();
			$(this).find('img').attr('src','../images/xs_03.png');
			}
	})
	$('header li').eq(1).on('click',function(){
		//alert($(this).find('img').attr('src'));
		if($(this).find('img').attr('src')=='../images/xs_03.png'){
		$('#top_list2').show();
		$('#top_list1').hide();
		$('.me_ban').show();
		$(this).find('img').attr('src','../images/ss_03.png');
		$('header li').eq(0).find('img').attr('src','../images/xs_03.png');
		return $(this).find('img').attr('src');
		}else{
			$('#top_list2').hide();
			$('.me_ban').hide();
			$(this).find('img').attr('src','../images/xs_03.png');
			}
	})
	$('#top_list1 li').eq(0).css({'border':'none','background':'#eee'});
	$('#top_list2 li').eq(0).css({'border':'none','background':'#eee'});
	$('.top li').on('click',function(){
		$(this).css({'border':'none','background':'#eee'}).siblings().css({'border-bottom':'1px solid #ddd','background':'#fff'});
		$('#top_list2').hide();
		$('#top_list1').hide();
		$('.me_ban').hide();
		$('header li').find('img').attr('src','../images/xs_03.png');
		})
	
})





