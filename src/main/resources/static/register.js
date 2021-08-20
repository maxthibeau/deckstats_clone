$(document).ready(function(){
    $("#registration_form").submit(function(e){
        if ($("#psw").val() != $("#psw-repeat").val()){
            alert("passwords do not match")
            e.preventDefault();
        }
    });
});