/**
 * Created by Administrator on 2018/3/23.
 */
$(function(){
    $("#register").validate({
        rules:{
        	/*LoginName:{
                required:true,
                minlength:1,
                maxlength:2,
                remote: {        //楠岃瘉鐢ㄦ埛鏄惁瀛樺湪validate鑷甫鐨�
           		  type: "post",  //鎻愪氦鏂瑰紡
          		  url: "/forge01/UsersServlet?method=name",//@WebServlet("/UserServlet")
          		  data: {//浼犺緭鏁版嵁鍒板悗鍙� @WebServlet("/UserServlet")
            		  username: function() {//
            			  
              		  return $("#LoginName").val();//鑾峰彇鍒拌〃鍗曠殑鍊煎彂閫佽繃鍘诲彇瀵规瘮鏁版嵁搴搄dbc
              		  
             		  }
            		  },    
         	       } 
        		  },*/
         

    
            lastName:{
                required:true
            },
            country:{
                required:true
            },
            state:{
                required:true
            },
            city:{
                required:true
            },
            address:{
                required:true
            },
            phone:{
                required:true,
                checkPhone:true//checkPhone闇�鑷繁涔﹀啓
            },
            email:{
                required:true,
                email:true
            },
            pwd:{
                required:true,
                minlength:6,
                maxlength:10
            },
            repwd:{
                required:true,
                minlength:6,
                maxlength:10,
                equelT0:"myPwd"
            }
        },
        messages:{
        	/*LoginName:{
                required: "Please enter your first name",
                minlength: "The length of the first name cannot be less than 1",
                maxlength: "The length of the first name cannot be greater than 2",
                remote:"LoginName is already exits"
            },*/
            lastName:{
                required: "Please enter your last name"
            },
            country:{
                required: "Please select your country"
            },
            state:{
                required: "Please select your province"
            },
            city:{
                required: "Please select your city"
            },
            address:{
                required: "Please enter your detailed address"
            },
            phone:{
                required: "Please enter the phone number",
                checkPhone: "Incorrect mobile number format"//checkPhone闇�鑷繁涔﹀啓
            },
            email:{
                required: "please input your email",
                email: "E-mail format is incorrect"
            },
            pwd:{
                required: "Please enter your password",
                minlength: "Password length cannot be less than 6",
                maxlength: "Password length cannot be greater than 10"
            },
            repwd:{
                required: "Please enter your password again",
                minlength: "Password length cannot be less than 6",
                maxlength: "Password length cannot be greater than 10",
                equelT0: "Two passwords must be the same"
            }
        },
        onfocusout:function(e){
            $(e).valid();
        }
    });
    /*澧炲姞鎵嬫満楠岃瘉瑙勫垯*/
    jQuery.validator.addMethod("checkPhone",function(value,element){
        var  phone=/^1[3|4|5|8][0-9]\d{4,8}$/;
        return this.optional(element)||(phone.test(value));
    },
    $("#login").validate({
        rules:{
            loginName:{
                required:true,
            },
            password:{
                required:true,
                minlength:6,
                maxlength:10
            }
        },
        messages:{
        	loginName:{
                required: "please input your loginName",
               
            },
            password: {
                required: "Please enter your password",
                minlength: "Password length cannot be less than 6",
                maxlength: "Password length cannot be greater than 10"
            }
        },
        onfocusout:function(e){
            $(e).valid();
        }
    });

});