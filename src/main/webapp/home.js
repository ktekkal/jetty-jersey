function getServerData(url, success){
    $.ajax({
        dataType: "json",
        url: url
    }).done(success);
}

function callDone(result){
	$("#result").text(JSON.stringify(result));
}

$(function(){
	$("#button").click(function(){
		getServerData("ws/example/aircraft",callDone);
	});
});