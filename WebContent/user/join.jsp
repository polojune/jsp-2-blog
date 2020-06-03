<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



<%@ include file="../include/nav.jsp"%>

<div class="container">

	<form action="/blog/user?cmd=joinProc" method="post" class="was-validated" onsubmit="return validate()">

		<div class="form-group">
			<label for="username">Username:</label>
			<button type="button" class="btn btn-warning float-right" onclick="usernameCheck()">중복확인</button>
			<input type="text" class="form-control" id="username" placeholder="Enter username" name="username" required>
			<div class="valid-feedback">Valid.</div>
			<div class="invalid-feedback">Please fill out this field.</div>
		</div>

		<div class="form-group">
			<label for="pwd">Password:</label> <input type="password" class="form-control" id="password" placeholder="Enter Password" name="password" required>
			<div class="valid-feedback">Valid.</div>
			<div class="invalid-feedback">Please fill out this field.</div>
		</div>

		<div class="form-group">
			<label for="email">Email:</label> <input type="email" class="form-control" id="email" placeholder="Enter Email" name="email" required>
			<div class="valid-feedback">Valid.</div>
			<div class="invalid-feedback">Please fill out this field.</div>
		</div>

		<div class="form-group">
			<label for="address">Address:</label>
			<button type="button" class="btn btn-warning float-right" onclick="goPopup();">주소검색</button>

			<input type="text" class="form-control" id="address" placeholder="Enter Address" name="address" required readonly>
			<div class="valid-feedback">Valid.</div>
			<div class="invalid-feedback">Please fill out this field.</div>
		</div>

		<button type="submit" class="btn btn-primary">Submit</button>

	</form>




</div>

<script>
	function goPopup() {

		var pop = window.open("/blog/juso/jusoPopup.jsp", "pop",
				"width=570,height=420, scrollbars=yes, resizable=yes");

	}

	function jusoCallBack(roadFullAddr) {
		var tfAddress = document.querySelector("#address");
		tfAddress.value = roadFullAddr;
		//document.form.roadFullAddr.value = roadFullAddr;	
	}
</script>

<script>
	var isCheckedUsername = false;

	function validate() {
		//alert('나타?');
		if (!isCheckedUsername) {
			alert("아이디 중복체크를  해주세요");
		}

		return isCheckedUsername;
	}

	function usernameCheck() {
		//성공 (ajax)
		var tfUsername = $('#username').val();
		//alert(tfUsername);
		console.log(tfUsername);
		$.ajax({
			type:'get', 
			url:'/blog/user?cmd=usernameCheck&username='+tfUsername
		}).done(function(result){
            console.log(result);
            if(result == 1){
                  alert("아이디가 중복되었습니다.");
              }else if(result == 0){
                  alert("사용하실 수 있는 아이디 입니다.");
                  isCheckedUsername = true;
              }else{ 
                  console.log('develop: 서버오류');
                  }
		}).fail(function(error){
            console.log(error); 
		});
		//isCheckedUsername = true;
	}
</script>

<%@ include file="../include/footer.jsp"%>
