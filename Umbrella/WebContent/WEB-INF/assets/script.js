 jQuery(document).ready(function($) {

    $(".scroll").click(function(event){
    event.preventDefault();
    $('html,body').animate({scrollTop:$(this.hash).offset().top}, 400,'swing');
    });


    function handleResize() {
    var h = jQuery(window).height();
    jQuery('.fullpage').css({'height': h + 'px'});
    }
    jQuery(function() {
    handleResize();
    jQuery(window).resize(function() {
        handleResize();
    });
    });

var wow = new WOW(
  {
    boxClass:     'wowload',      // animated element css class (default is wow)
    animateClass: 'animated', // animation css class (default is animated)
    offset:       0,          // distance to the element when triggering the animation (default is 0)
    mobile:       true,       // trigger animations on mobile devices (default is true)
    live:         true        // act on asynchronously loaded content (default is true)
  }
);
wow.init();

$('.carousel').swipe( {
     swipeLeft: function() {
         $(this).carousel('next');
     },
     swipeRight: function() {
         $(this).carousel('prev');
     },
     allowPageScroll: 'vertical'
 });

 function change(o) {
    window.location.href = 'www.baidu.com'
 }

$("#submitButton").click(function(){
    alert("提交成功!!");
});




  });
