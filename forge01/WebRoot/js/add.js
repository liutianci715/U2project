/*
 * 自己增加的js
 * */

  /*单击登录，滑块弹出*/
  $(document).ready(function () {
	  $("#aa").click(function () {
		  if($("#login").valid()){
			  $("#demo").css({"display":"block"});
		  }      
	 });
	  
	  $(function () {
	        var slider = new SliderUnlock("#slider",{
					successLabelTip : "验证成功"	
				},function(){
					/*alert("Verify that the success is about to jump to the home page!");*/
					$("#login").submit();
	        	});
	        slider.init();
	    })
});