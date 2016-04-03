$(document).ready(function(){
    
    //give fadeIn to introduction section in EmployeeAcountPage
    $("#introduction").fadeIn(3500);
    
  // Add smooth scrolling to all links in navbar + footer link
  $(".navbar a.for-scroll, footer a[href='#myPage']").on('click', function(event) {

  // Prevent default anchor click behavior
  event.preventDefault();

  // Store hash
  var hash = this.hash;

  // Using jQuery's animate() method to add smooth page scroll
  // The optional number (900) specifies the number of milliseconds it takes to scroll to the specified area
  $('html, body').animate({
    scrollTop: $(hash).offset().top
  }, 900, function(){

    // Add hash (#) to URL when done scrolling (default click behavior)
    window.location.hash = hash;
    });
  });
});

$(window).scroll(function() {
  $(".slideanim").each(function(){
    var pos = $(this).offset().top;

    var winTop = $(window).scrollTop();
    if (pos < winTop + 600) {
      $(this).addClass("slide");
    }
      
  });
    
    
  /** Employee Acount page **/    
  $(".progress").each(function(){
    var pos = $(this).offset().top;
    var winTop = $(window).scrollTop(); 
    
    if(pos< winTop + 1200) {
        
   $("#peg-pb-one").addClass("progress-bar-color");
   $("#peg-pb-two").addClass("progress-bar-color");
   $("#peg-pb-three").addClass("progress-bar-color");
   $("#peg-pb-four").addClass("progress-bar-color");
   $("#peg-pb-five").addClass("progress-bar-color");
    
    
    $("#peg-pb-one").animate({
        width: "50%"
    }, 2500);

    $("#peg-pb-two").animate({
        width: "40%"
    }, 2500);

    $("#peg-pb-three").animate({
        width: "70%"
    }, 2500);
    
    $("#peg-pb-four").animate({
        width: "60%"
    }, 2500);
    
    $("#peg-pb-five").animate({
        width: "20%"
    }, 2500);
    
    
    $("#peg-resume-dl-btn").addClass("peg-resume-dl");
    }
      
      
  });
    
    /** Employee Acount page **/
    
    
    
});








$(function(){
    $("#NM_submit").click(function() {     
        //alert($("input[name=Rmembership]:checked").val());
        var value = $("input[name=Rmembership]:checked").val();
        var page = 'AccountSetting/accountsetting' + '_' + String(value) + '.html';
        window.location.href = page;        
                    
    });
});

$(function(){
    $("#NM_login").click(function() {     
        //alert($("input[name=Lmembership]:checked").val());
        var value = $("input[name=Lmembership]:checked").val();
        var page = 'Personal/'+String(value) + '_Personal.html';
        window.location.href = page;        
                    
    });
});