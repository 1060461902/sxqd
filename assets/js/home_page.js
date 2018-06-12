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
});