var page = 1;

$(document).ready(function () {
	/**
	 * 获取url中的参数并判断切换到哪一页面
	 */
	var res = $.parseURL(location.href);
	if (res) {
		switch (res['tab']) {
			case '2':
				$('#qzzl').click();
				break;
			case '3':
				$('#wd').click();
				break;
			default:
				$('#sxzp').click();
				break;
		}
	}

	$(".weui-loadmore").hide();
	/**
	 * 请求后端获取页面数据
	 */
	var option = getBASEGETAJAX();
	option.data = {
		pageNum: 1
	}
	option.url = './json/home_page.json';
	option.success = function (data) {
		if (data.code === 200) {
			/**
			 * 轮播图
			 * */
			$('#myCarousel').handlebars($('#carousel-model'), data.data.shows, {
				name: 'active',
				callback: function (index) {
					if (index == 0) {
						return 'active';
					} else {
						return '';
					}
				}
			});
		} else {
			console.log(data.msg);
			$.toptip("系统繁忙，请稍后再试", 'error');
		}
	}
	option.error = function (res) {
		$.toptip("系统繁忙，请稍后再试", 'error');
		console.log(res);
	}
	$.ajax(option);

	$('.post-cells').css({
		'height':$(window).height()-$('#searchBar').outerHeight(true)-$('#myCarousel').outerHeight(true)-$('.weui-tabbar').outerHeight(true),
		'overflow': 'scroll'
	});

	/**
	 * 请求职位列表
	 */
	$('.post-loading').dropload({
		scrollArea: $('.post-cells'),
		loadDownFn: function (me) {
			$.ajax({
				type: 'GET',
				url: './json/home_rec.json',
				// url:'../student/collections/collection',
				data: {
					pageNum: page
				},
				success: function (data) {
					if (data.code === 200) {
						if (data.data.data.length === 0) {
							// 锁定
							me.lock();
							// 无数据
							me.noData();
						} else {
							var template = Handlebars.compile($('#post-list-model').html());
							var html = template(data.data.data);
							$('.cells-container').append(html);
							page++;
							me.resetload();
						}
					} else {
						console.log(data.msg);
						$.toptip("系统繁忙，请稍后再试", 'error');
						me.resetload();
						// 锁定
						me.lock();
						// 无数据
						me.noData();
					}
				},
				error: function (res) {
					$.toptip("系统繁忙，请稍后再试", 'error');
					console.log(res);
					me.resetload();
					// 锁定
					me.lock();
					// 无数据
					me.noData();
				}
			});
		}
	});
});