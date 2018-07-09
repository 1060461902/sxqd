var pages = 1;
var size = 5;
var loading = false;

$(document).ready(function () {
	$(".weui-loadmore").hide();
	/**
	 * 请求后端获取页面数据
	 */
	var option = getBASEGETAJAX();
	option.url = './json/weixin_intership_index.json';
	option.success = function (data) {
		/**
		 * 轮播图
		 * */
		$('#myCarousel').handlebars($('#carousel-model'), data.show, {
			name: 'active',
			callback: function (index) {
				if (index == 0) {
					return 'active';
				} else {
					return '';
				}
			}
		})
	}
	option.error = function (res) {
		console.log(res)
	}
	$.ajax(option);

	/**
	 * 
	 */
	$(document.body).infinite().on("infinite", function () {
		if (loading) return;
		loading = true;
		pages++; //页数
		$('.weui-loadmore').show();
		setTimeout(function () {
			loadlist();
			loading = false;
		}, 1000); //模拟延迟
	});
});

/**
 * 
 */
function loadlist() {
	for (var i = 0; i < size; i++) {
		$('.cells-container').append('<a class="weui-cell" data-id="" href="">' +
			'<div class="cell-left">' +
			'<p class="post-name">后端工程师</p>' +
			'<p>' +
			'<span>即刻</span>' +
			'<span>&nbsp;&nbsp;&nbsp;</span>' +
			'<span>IT/互联网</span>' +
			'</p>' +
			'<p>' +
			'<img src="./assets/images/location_point.png" alt="">' +
			'<span class="location-show">上海杨浦区政学路51号</span>' +
			'</p>' +
			'</div>' +
			'<div class="cell-right">' +
			'<p class="post-date">05\31</p>' +
			'<img src="./assets/images/jk.png" alt="">' +
			'</div>' +
			'</a>');
	}
	$(".weui-loadmore").hide();
}