$.fn.exists = function(callback) {
    var args = [].slice.call(arguments, 1);

    if (this.length) {
        callback.call(this, args);
    }
    return this;
};

$('.select2_tags').exists(function() {
    $('.select2_tags').select2();

    tinymce.init({
        selector: '#copy'
    });
});


$('.select2_article').exists(function() {


$(".select2_article").select2({
    ajax: {
        url: "/api/article/search",
        dataType: 'json',
        delay: 250,
        data: function (params) {
            return {
                q: params.term, // search term
            };
        },
        processResults: function (data, params) {
            return {
                results: data,
            };
        },
        cache: true
    },
    escapeMarkup: function (markup) { return markup; },
    minimumInputLength: 1,
    templateResult: formatResult,
    templateSelection: formatResultSelection
});

function formatResult (data) {
    return data.title;
}

function formatResultSelection (data) {
    return data.title;
}


});