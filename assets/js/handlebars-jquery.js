(function($){
    var compiled = {};
    $.fn.handlebars = function (template, data , helper) {
        if(template instanceof jQuery){
            template = $(template).html();
        }
        if(helper instanceof Array){
            $.each(helper,function (index,value) {
                if(value.name && value.callback){
                    Handlebars.registerHelper(value.name, value.callback);
                }
            });
        }else if(typeof helper === 'object' && helper.name && helper.callback){
            Handlebars.registerHelper(helper.name, helper.callback);
        }
        compiled[template] = Handlebars.compile(template);
        this.html(compiled[template](data));
        if(helper instanceof Array){
            $.each(helper,function (index,value) {
                if(value.name && value.callback){
                    Handlebars.unregisterHelper(value.name);
                }
            });
        }else if(typeof helper === 'object' && helper.name && helper.callback){
            Handlebars.unregisterHelper(helper.name);
        }
    }
})(jQuery)