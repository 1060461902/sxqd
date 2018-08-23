var page = 1;
var mescroll_sxzp;
var mescroll_qzzl;

$(document).ready(function () {
    /**
	 * 询问是否已绑定，后端自动跳转到提示页面
     */
    var option = getBASEGETAJAX();
    option.url = '/internshipmgn/wx/auth/home';
    option.error = function (res) {
        $.toptip("系统繁忙，请稍后再试", 'error');
        console.log(res);
    }
    $.ajax(option);

	/**
	 * 获取url中的参数并判断切换到哪一页面
	 */
	var res = $.parseURL(location.href);
	if (res) {
		switch (res['tab']) {
			case '2':
				$('#qzzl').click();
				getResume()
				break;
			case '3':
				$('#wd').click();
				break;
			default:
				$('#sxzp').click();
				getPost();
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
	option.url = '/internshipmgn/student/home';
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
		'height': $(window).height() - $('#searchBar').outerHeight(true) - $('#myCarousel').outerHeight(true) - $('.weui-tabbar').outerHeight(true),
		'overflow': 'scroll'
	});

	$('.road-cells').css({
		'height': $(window).height(),
		'overflow': 'scroll'
	});


    var option = getBASEGETAJAX();
    option.url = '/internshipmgn/student/studentsets/set';
    option.success = function (d) {
        if (d.code === 200) {
            /**
             * 基础信息
             */
            var info = d.data.info;
            $('.person-bar').handlebars($('#person-info-model'),info);
        }else {
            console.log(d.msg);
            $.toptip("系统繁忙，请稍后再试", 'error');
		}
    }
    option.error = function (res) {
        $.toptip("系统繁忙，请稍后再试", 'error');
        console.log(res);
    }
    $.ajax(option);
	/**
	 * 滚动加载触发与销毁
	 */
	$('#sxzp').click(function () {
		getPost();
	});
	$('#qzzl,#wd').click(function () {
		mescroll_sxzp.destroy();
	});
	$('#qzzl').click(function () {
		getResume();
	});
	$('#sxzp,#wd').click(function () {
		mescroll_qzzl.destroy();
	});
});

function getPost() {
	mescroll_sxzp = new MeScroll("mescroll_sxzp", {
		up: {
			auto: true,
			isBounce: false,
			clearEmptyId: "dataList_sxzp",
			page: {
				num: 0,
				size: 1
			},
			loadFull: {
				use: true
			},
			callback: function (page) {
				$.ajax({
					type: 'GET',
					url: '/internshipmgn/student/home/recruitment',
					data: {
						pageNum: page.num
					},
					success: function (data) {
						if (data.code === 200) {
							mescroll_sxzp.endSuccess(data.data.data.length, page.num == data.data.totalPage ? false : true);
							var template = Handlebars.compile($('#post-list-model').html());
							var html = template(data.data.data);
							$('.cells-container').append(html);
						} else {
							mescroll_sxzp.endErr();
							console.log(data.msg);
							$.toptip("系统繁忙，请稍后再试", 'error');
						}
					},
					error: function (res) {
						//联网失败的回调
						mescroll_sxzp.endErr();
						$.toptip("系统繁忙，请稍后再试", 'error');
						console.log(res);
					}
				});

			},
			toTop: {
				src: "./assets/images/mescroll-totop.png",
				offset: 1000
			}
		}
	});
}

function getResume(){
	mescroll_qzzl = new MeScroll("mescroll_qzzl", {
		up: {
			auto: true,
			isBounce: false,
			clearEmptyId: "dataList_qzzl",
			page: {
				num: 0,
				size: 1
			},
			loadFull: {
				use: true
			},
			callback: function (page) {
				$.ajax({
					type: 'GET',
					url: '/internshipmgn/student/roads/road',
					data: {
						pageNum: page.num
					},
					success: function (data) {
						if (data.code === 200) {
							mescroll_qzzl.endSuccess(data.data.data.length, page.num == data.data.totalPage ? false : true);
							var template = Handlebars.compile($('#resume-list-model').html());
							Handlebars.registerHelper('which_img',function (actionName) {
								switch (actionName) {
                                    case '投递简历':
                                        return './assets/images/history_send.png';
                                    case '简历通过':
                                        return './assets/images/history_pass.png';
                                    case '成功录用':
                                        return './assets/images/history_success.png';
                                }
							});
							var html = template(data.data.data);
							$('.resume-list').append(html);
						} else {
							mescroll_qzzl.endErr();
							console.log(data.msg);
							$.toptip("系统繁忙，请稍后再试", 'error');
						}
					},
					error: function (res) {
						//联网失败的回调
						mescroll_qzzl.endErr();
						$.toptip("系统繁忙，请稍后再试", 'error');
						console.log(res);
					}
				});

			},
			toTop: {
				src: "./assets/images/mescroll-totop.png",
				offset: 1000
			}
		}
	});
}