
function PegNotifFunction()
{
	
  var notifDiv= document.createElement("DIV");
  notifDiv.className="peg_notif_box";
  
  var notifText= document.createTextNode("این یک پیغام نمونه است.");
  notifText.className = "notifText";
  var closeBtn= document.createElement("BUTTON");
  closeBtn.className="peg_close_button";
  
  notifDiv.appendChild(closeBtn);
  notifDiv.appendChild(notifText);
  document.body.appendChild(notifDiv);
  
	$(".peg_notif_box").mouseenter(function(){
		$(this).animate({ width: "15em" }, 'slow');
        //$(this).addClass("peg_mouse_enter_notif");
    });
	
	$(".peg_notif_box").mouseleave(function(){
		//$(this).animate({ width: "5em" }, 'medium');
		//$(this).removeClass("peg_mouse_enter_notif");

	});
	
	
	closeBtn.onclick = function(){
		$(this.parentNode).fadeOut("500ms");
	 // this.parentNode.parentNode.removeChild(this.parentNode);
	  }; 
	
}

function loadAccSetting(){
	
	window.location.href="AccountSetting/accountsetting_Employee.html";
	
}
 