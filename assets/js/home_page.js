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
	option.url = '../student/home';
	option.data = {
		pageNum:1
	}
	option.success = function (data) {
		if (data.code !== 200) {
			alert(data.msg);
			return;
		} else {
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
			})
			/**
			 * 新闻列表
			 */
			$('.news-list').handlebars($('#news-model'), data.data.events);
			/**
			 * 职位分页导航
			 */
			var post_data = data.data.recruitments;
			pageLimit(post_data.currentPage, post_data.totalPage, 5, function (page) {
				var option = getBASEGETAJAX();
				option.url = '../student/home/recruitment';
				option.data = {
					pageNum:page
				}
				option.success = function (data) {
					$('.post-item-list').handlebars($('#post-model'),data.data.data)
                }
                option.error = function (res) {
                    console.log(res);
                    setAlert('系统繁忙,请稍候再试');
                }
                $.ajax(option);
            });
			/**
			 * 职位列表
			 */
			$('.post-item-list').handlebars($('#post-model'), post_data.data);
		}
	}
	option.error = function (res) {
		console.log(res);
		setAlert('系统繁忙,请稍候再试');
	}
	$.ajax(option);

	/**
	 * 
	 */
	$('body').on('click','div.carousel img , div.carousel span',function () {
		//alert($(this).attr('id'));
		window.location.href = "./news.html";
	});
});