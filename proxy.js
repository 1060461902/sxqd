/**
 * 解决跨域问题
 */
var http = require('http');
var url=require('url');

var port = 5500;

http.createServer(function (request, response) {
    var pathname = url.parse(request.url).pathname;
    console.log(pathname);
    var content = '';
    var opt = {
         host:'10.21.30.203',
         port:'8080',
         method: request.method,
         path:pathname
    };
    var req = http.request(opt, function(res) {
        res.on('data',function(body){
            console.log('return');
            content+=body;
        }).on("end", function () {
            response.writeHead(200, {'Content-Type': 'text/html'});
            response.write(content);
            response.end();
        });
    }).on('error', function(e) {
        console.log("Got error: " + e.message);
    });
    req.end();
}).listen(port);
console.log("Server runing at port: " + port + ".");