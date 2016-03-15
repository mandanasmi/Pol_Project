/* Script File */

var loadState = 0;

function loadFeed() {
	var xhttp = new XMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if(xhttp.readyState == 4 && xhttp.status == 200) {
			$(".list_feed").append(xhttp.responseText);
		}
	};
	xhttp.open("POST", "feed001.txt", true);
	xhttp.send();
}

$(window).scroll(function() {
	var head = $(".div_header").outerHeight(true);
	var navi = $(".div_navi").outerHeight(true);
	if($(this).scrollTop() > head) {
		$(".div_navi").css("position", "fixed");
		$(".div_page").css("padding-top", navi);
		$(".div_sidebarinner").css("padding-top", navi);
	}
	else {
		$(".div_navi").css("position", "relative");
		$(".div_page").css("padding-top", "0");
		$(".div_sidebarinner").css("padding-top", (head + navi - $(this).scrollTop()));
	}
	if($(this).scrollTop() + $(this).height() > $(document).height() && loadState == 0) {
		loadState = 1;
		$("#wait_icon").css("display", "block");
		setTimeout(function() {
			loadFeed();
			$("#wait_icon").css("display", "none");
			loadState = 0;
		}, 500);
	}
});
