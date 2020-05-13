<%@ page  pageEncoding="UTF-8"%>
<html>
<head>
<title>客户管理-创建客户</title>
</head>
<body>
<script>
    /*这里是提交表单前的非空校验*/
    function checkForm () {
   /*  	var name=document.getElementById('name').value;
    	var contact=document.getElementById('contact').value;
    	var telephone=document.getElementById('telephone').value;
    	var email=document.getElementById('email').value;
    	var remark=document.getElementById('remark').value;
         */
    	 document.getElementById("myForm").submit()
    	alert(name);
    }
</script>

	<h1>创建客户界面</h1>
	
	<form  id="myForm" action="customer_create" method="post">
		 客户名称：<input type="text"  name="name">
		 联系人：<input type="text"  name="contact">
		电话号码 ：<input type="text"  name="telephone">
		 邮箱地址：<input type="text"  name="email">
		 备注：<input type="text"  name="remark">
		 
		 <input id="tj" type="button" value="提交" onclick="checkForm();">
	</form>
</body>
</html>