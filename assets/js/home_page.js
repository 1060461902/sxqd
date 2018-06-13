$(document).ready(function () {
	/**
	 * 下方导航栏鼠标浮动效果
	 * */
	$('.post-item').hover(function () {
		$('.news-container').css("display", "none");
		$('.post-container').css("display", "block");
	});
	$('.news-item').hover(function () {
		$('.post-container').css("display", "none");
		$('.news-container').css("display", "block");
	});

	/**
	 * 请求后端获取页面数据
	 */
	var option = getBASEGETAJAX();
	option.url = './json/home_page.json';
	option.success = (data) => {
		/**
		 * 轮播图
		 * */
		HandelBarsHelper({
			origin: $('#carousel-model'),
			goal: $('#myCarousel'),
			data: data.show,
			helper: {
				name: 'active',
				callback: (index) => {
					if (index == 0) {
						return 'active';
					} else {
						return '';
					}
				}
			}
		});
	}
	option.error = (res) => {
		console.log(res)
	}
	$.ajax(option);

	/**
	 * 分页
	 */
	pageLimit(1, 20, 5);

	/**
	 * 
	 */
	$('div.carousel img , div.carousel span').click(function () {
		//alert($(this).attr('id'));
		window.location.href = "./news.html";
	});
});