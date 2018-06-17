(function($){
    var compiled = {};
    $.fn.handlebars = function (template, data , helper) {
        if(template instanceof jQuery){
            template = $(template).html();
        }
        if(typeof helper != 'undefined'){
            Handlebars.registerHelper(helper.name, helper.callback);
        }
        compiled[template] = Handlebars.compile(template);
        this.html(compiled[template](data));
    }
})(jQuery)