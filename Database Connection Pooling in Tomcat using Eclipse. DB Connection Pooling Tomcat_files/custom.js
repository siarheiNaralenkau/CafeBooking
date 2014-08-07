var mainmenu;
var startMenu;
var mainMenuScroll = function() {
	var p = $(window).scrollTop();
	$(mainmenu).css('position',((p)>startMenu) ? 'fixed' : 'static');
	$(mainmenu).css('top',((p)>startMenu) ? '0px' : '');
}
var lazyLoading = function(){ 
	$('img.lazy').each(function(){ 
		var distanceToTop = $(this).offset().top; 
		var scroll = $(window).scrollTop(); 
		var windowHeight = $(window).height(); 
		var isVisible = distanceToTop - scroll < windowHeight; 
		if (isVisible) { 
			$(this).attr('src', $(this).attr('data-src')); 
		} 
	}); 
} 

// No conflict with JS libraries
$(document).ready(function($) {

	$('a[href$=".zip"]').click(function(){
		_gaq.push(['_trackEvent', 'Downloads', $(this).text(), window.location.href]);	
		if('_blank' == this.target) {
			return true;
		}
		setTimeout('document.location = "'+this.href+'"', 150);
		return false;
	});
	

	mainmenu = $('#head-menu');
	startMenu = $(mainmenu).offset().top;
	lazyLoading(); 
	$(window).scroll(function() { 
		mainMenuScroll();
		lazyLoading(); 
	}); 

	// Drop Down Menu (2 Levels)
	$('#menu ul').hide(0.000000000000000000000000000000000000001);
	$('#menu li a').addClass('nav_parent');
	$('#menu ul a').removeClass('nav_parent');
	$('#menu ul a').addClass('nav_child');

	$('.nav_parent').bind('mouseenter', function() {
		if (($(this).next().is('ul')) && ($(this).next().is(':visible'))) {
			return false;
		} else if (($(this).next().is('ul')) && ($(this).next().is(':hidden'))) 	{
			$(this).addClass('selected');
			$(this).append('<div class="arrow"></div>');
			$(this).next().show();
			return false;
		}
	});

	$('.nav_parent').parent().bind('mouseleave', function() {
		$('#menu li a').removeClass('selected');
		$('.arrow').remove();
		$('#menu ul:visible').hide();
	});
// End of Menu

	// CSS Work
	$('#pagenav li:last').css('border', 'none');
	$('#pagenav li:last').css('padding', '0 0 0 4px');
	$('#sidebar_l li:first').css('margin-top', '0');
	$('#sidebar_r li:first').css('margin-top', '0');
	$('#sidebar_m li:first').css('margin-top', '0');
	$('#sidebar_m li:first').css('margin-bottom', '15px');

	SyntaxHighlighter.all();

// Main Menu fixing while scrolling
//$.event.add(window, "scroll", main_menu_scroll);

});



