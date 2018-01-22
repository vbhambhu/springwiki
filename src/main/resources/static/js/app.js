$( "#pageForm" ).on('submit', function(e) {
    e.preventDefault();

    var formdata = $('#pageForm').serialize();

        console.log(formdata);


        $.post( "/api/page/create", formdata)
        .done(function( response ) {

            if(response.status){
               // window.location.replace("/page/"+response.message+"&edit=true");
            }

        });
});
