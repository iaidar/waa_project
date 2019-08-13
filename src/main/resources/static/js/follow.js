$(document).ready(function () {

    $("#followForm").submit(function (e) {
        e.preventDefault();
        const data = $( this ).serializeArray();
        const follow = data.isFollow?'unfollow':'follow';
        const url = '/buyer/'+follow+'/'+data[0].value;

        console.log(url);


        fetch(url, {
            method: "POST",

        }).then(function (data) {
            console.log(data)
        }).then(function(myJson) {
            console.log(JSON.stringify(myJson));
        }).catch(error => console.error('Error:', error));;
    });



});