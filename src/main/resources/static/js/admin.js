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

