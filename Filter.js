$(function(){
    $("#NM_submit").click(function() {     
        alert($("input[name=Rmembership]:checked").val());
        var value = $("input[name=Rmembership]:checked").val();
        var page = 'AccountSetting/accountsetting' + '_' + String(value) + '.html';
        window.location.href = page;        
                    
    });
});

$(function(){
    $("#NM_login").click(function() {     
        alert($("input[name=Lmembership]:checked").val());
        var value = $("input[name=Lmembership]:checked").val();
        var page = 'Personal/'+String(value) + '_Personal.html';
        window.location.href = page;        
                    
    });
});
