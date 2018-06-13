$(document).ready(function () {
	$('div.carousel img , div.carousel span').click(function () {
		//alert($(this).attr('id'));
		window.location.href = "./news.html";
	});
	$('.post-item').hover(function () {
		$('.news-container').css("display", "none");
		$('.post-container').css("display", "block");
	});
	$('.news-item').hover(function () {
		$('.post-container').css("display", "none");
		$('.news-container').css("display", "block");
	});

	pageLimit(1,20,5);

	//后端返回的数据
	var icontent = {
		img_info:[
			{title:"1",img_laod:"./assets/images/u3040.jpg"},
			{title:"2",img_laod:"./assets/images/u3041.jpg"},
			{title:"3",img_laod:"./assets/images/u3040.jpg"},
		]
	}

	//轮播图
	HandelBarsHelper({
		origin:$('#carousel-model'),
		goal:$('#myCarousel'),
		data:icontent.img_info,
		helper:{
			name:'active',
			callback:function (index) {
				if(index == 0){
					return 'active';
				}else{
					return '';
				}
			}
		}
	});
});