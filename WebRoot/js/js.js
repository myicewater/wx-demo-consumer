// JavaScript Document

function d_time(){
 var t; 
	t=60; 
setInterval(function(){	
	if(t>1){
			t=t-1; 
			$('#w_btn').html(t+" 秒后重新获取"); 
		}else{
			$('#w_btn').html("获取验证码");
			}
	 },1000);
} 
/*验证方法*/

/*验证手机号*/
function phone(){
	//alert('zx');
	var reg=/^1[3|4|7|5|8][0-9]\d{8}$/;
	var phone=$('#phone').val();
	if(reg.test(phone) == false || phone ==''){  		    			  
	   $('._label1').html('请正确填写手机号码');
	   return  false;  
   }else{
	   $('._label1').html('');
	   return true;
	   }	
}
/*验证密码*/
function mima(){	
	var mima=$('#pwd').val();
	if(mima.length<6||mima.length>12 || mima ==''){  		    			  
	   $('._label2').html('密码应为6-12个字符');
	   return  false;  
   }else{
	   $('._label2').html('');
	   return true;
	 }
	 
}

function mima2(){
	var mima=$('#pwd').val();
	var mima2=$('#pwdagain').val();
	if(mima !== mima2){
		  $('._label3').html('两次密码输入不一样，请重新输入');
		  return  false;  
   }else{
	   $('._label3').html('');
	   return true;
	 }		
	
}
/*验证码*/
function yzm(){
	var yzm=$('#validCodes').val();
	var reg=/^\d{6}$/;
	if(reg.test(yzm)== false){
		  $('._label4').html('验证码输入错误');
		  return  false;  
   }else{
	   $('._label4').html('');
	   return true;
	 }		
}

/*验证邮箱*/
function email(){
	var e_email=$('#email').val();
	var reg= /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/;
	if(reg.test(e_email) == false){
		  $('._label5').html('邮箱格式输入不正确');
		  return  false;  
   }else{
	   $('._label5').html('');
	   return true;
	 }		
}

/*身份证后四位*/
function checkCard(){
		var reg = /^\d{3}(\d|X|x)$/;  
		//alert(card);
	 	var nums=$('#nums').val();
		
	 if(reg.test(nums) === false){  		    
			   //alert("身份证输入不合法"); 
			   $('#two').html('请正确书写身份证后四位');
			   return  false;  
		   }else{
			   $('#two').html('');
			   return true;
			   }
		  		
		}


function Nannum(){
	var Name=$('#Name').val();
	if(!isNaN(Name)){
		$('#one').html('亲，您的名字不可能是数字哦！');
		return false;
		}else{
			   $('#one').html('') ;
			   return true;
			   }  
	
	}
/*打款金额*/
	function isnum(){
	var money=$('#money').val();
	var num=/^\+?[1-9][0-9]*$/;
	if(!num.test(money)){
		$('#three').html('只能输入非零整数'); 
		return false;
		}else{
			   $('#three').html('单位：万元');
			   return true;
			   } 
	
	}
	var wait = 60;
	function time(o) {
		if (wait == 0) {
			o.attr("onclick","sendVcode()");
			o.val("重新获取验证码");
			wait = 60;
		} else {
			
			o.removeAttr("onclick");
			o.val(wait + "秒后重新发送");
			wait--;
			setTimeout(function() {
				time(o);
			}, 1000);
		}
	}
function sendVcode(){
		
		time($("#w_btn"));   
	}