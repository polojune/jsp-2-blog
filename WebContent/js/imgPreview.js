
$("#img__preview").on("change", function(e) {
	var f = e.target.files[0];
	if (!f.type.match("image*")) {
		alert("이미지만 첨부 할 수 있습니다.");
		
		return;
	}
	
	//f.size = 1024*1024*2
	var reader = new FileReader();

	reader.onload = function(e) {
		$("#img__wrap").attr("src", e.target.result);
		console.log(e.target.result);
	};

	reader.readAsDataURL(f); // 비동기 실행

});
