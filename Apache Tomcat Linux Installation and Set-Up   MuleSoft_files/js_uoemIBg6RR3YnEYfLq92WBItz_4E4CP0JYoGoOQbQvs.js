/* Modernizr 2.5.3 (Custom Build) | MIT & BSD
 * Build: http://www.modernizr.com/download/#-cssanimations-csstransitions-shiv-cssclasses-testprop-testallprops-domprefixes-load
 */
;window.Modernizr=function(a,b,c){function x(a){j.cssText=a}function y(a,b){return x(prefixes.join(a+";")+(b||""))}function z(a,b){return typeof a===b}function A(a,b){return!!~(""+a).indexOf(b)}function B(a,b){for(var d in a)if(j[a[d]]!==c)return b=="pfx"?a[d]:!0;return!1}function C(a,b,d){for(var e in a){var f=b[a[e]];if(f!==c)return d===!1?a[e]:z(f,"function")?f.bind(d||b):f}return!1}function D(a,b,c){var d=a.charAt(0).toUpperCase()+a.substr(1),e=(a+" "+n.join(d+" ")+d).split(" ");return z(b,"string")||z(b,"undefined")?B(e,b):(e=(a+" "+o.join(d+" ")+d).split(" "),C(e,b,c))}var d="2.5.3",e={},f=!0,g=b.documentElement,h="modernizr",i=b.createElement(h),j=i.style,k,l={}.toString,m="Webkit Moz O ms",n=m.split(" "),o=m.toLowerCase().split(" "),p={},q={},r={},s=[],t=s.slice,u,v={}.hasOwnProperty,w;!z(v,"undefined")&&!z(v.call,"undefined")?w=function(a,b){return v.call(a,b)}:w=function(a,b){return b in a&&z(a.constructor.prototype[b],"undefined")},Function.prototype.bind||(Function.prototype.bind=function(b){var c=this;if(typeof c!="function")throw new TypeError;var d=t.call(arguments,1),e=function(){if(this instanceof e){var a=function(){};a.prototype=c.prototype;var f=new a,g=c.apply(f,d.concat(t.call(arguments)));return Object(g)===g?g:f}return c.apply(b,d.concat(t.call(arguments)))};return e}),p.cssanimations=function(){return D("animationName")},p.csstransitions=function(){return D("transition")};for(var E in p)w(p,E)&&(u=E.toLowerCase(),e[u]=p[E](),s.push((e[u]?"":"no-")+u));return x(""),i=k=null,function(a,b){function g(a,b){var c=a.createElement("p"),d=a.getElementsByTagName("head")[0]||a.documentElement;return c.innerHTML="x<style>"+b+"</style>",d.insertBefore(c.lastChild,d.firstChild)}function h(){var a=k.elements;return typeof a=="string"?a.split(" "):a}function i(a){var b={},c=a.createElement,e=a.createDocumentFragment,f=e();a.createElement=function(a){var e=(b[a]||(b[a]=c(a))).cloneNode();return k.shivMethods&&e.canHaveChildren&&!d.test(a)?f.appendChild(e):e},a.createDocumentFragment=Function("h,f","return function(){var n=f.cloneNode(),c=n.createElement;h.shivMethods&&("+h().join().replace(/\w+/g,function(a){return b[a]=c(a),f.createElement(a),'c("'+a+'")'})+");return n}")(k,f)}function j(a){var b;return a.documentShived?a:(k.shivCSS&&!e&&(b=!!g(a,"article,aside,details,figcaption,figure,footer,header,hgroup,nav,section{display:block}audio{display:none}canvas,video{display:inline-block;*display:inline;*zoom:1}[hidden]{display:none}audio[controls]{display:inline-block;*display:inline;*zoom:1}mark{background:#FF0;color:#000}")),f||(b=!i(a)),b&&(a.documentShived=b),a)}var c=a.html5||{},d=/^<|^(?:button|form|map|select|textarea)$/i,e,f;(function(){var a=b.createElement("a");a.innerHTML="<xyz></xyz>",e="hidden"in a,f=a.childNodes.length==1||function(){try{b.createElement("a")}catch(a){return!0}var c=b.createDocumentFragment();return typeof c.cloneNode=="undefined"||typeof c.createDocumentFragment=="undefined"||typeof c.createElement=="undefined"}()})();var k={elements:c.elements||"abbr article aside audio bdi canvas data datalist details figcaption figure footer header hgroup mark meter nav output progress section summary time video",shivCSS:c.shivCSS!==!1,shivMethods:c.shivMethods!==!1,type:"default",shivDocument:j};a.html5=k,j(b)}(this,b),e._version=d,e._domPrefixes=o,e._cssomPrefixes=n,e.testProp=function(a){return B([a])},e.testAllProps=D,g.className=g.className.replace(/(^|\s)no-js(\s|$)/,"$1$2")+(f?" js "+s.join(" "):""),e}(this,this.document),function(a,b,c){function d(a){return o.call(a)=="[object Function]"}function e(a){return typeof a=="string"}function f(){}function g(a){return!a||a=="loaded"||a=="complete"||a=="uninitialized"}function h(){var a=p.shift();q=1,a?a.t?m(function(){(a.t=="c"?B.injectCss:B.injectJs)(a.s,0,a.a,a.x,a.e,1)},0):(a(),h()):q=0}function i(a,c,d,e,f,i,j){function k(b){if(!o&&g(l.readyState)&&(u.r=o=1,!q&&h(),l.onload=l.onreadystatechange=null,b)){a!="img"&&m(function(){t.removeChild(l)},50);for(var d in y[c])y[c].hasOwnProperty(d)&&y[c][d].onload()}}var j=j||B.errorTimeout,l={},o=0,r=0,u={t:d,s:c,e:f,a:i,x:j};y[c]===1&&(r=1,y[c]=[],l=b.createElement(a)),a=="object"?l.data=c:(l.src=c,l.type=a),l.width=l.height="0",l.onerror=l.onload=l.onreadystatechange=function(){k.call(this,r)},p.splice(e,0,u),a!="img"&&(r||y[c]===2?(t.insertBefore(l,s?null:n),m(k,j)):y[c].push(l))}function j(a,b,c,d,f){return q=0,b=b||"j",e(a)?i(b=="c"?v:u,a,b,this.i++,c,d,f):(p.splice(this.i++,0,a),p.length==1&&h()),this}function k(){var a=B;return a.loader={load:j,i:0},a}var l=b.documentElement,m=a.setTimeout,n=b.getElementsByTagName("script")[0],o={}.toString,p=[],q=0,r="MozAppearance"in l.style,s=r&&!!b.createRange().compareNode,t=s?l:n.parentNode,l=a.opera&&o.call(a.opera)=="[object Opera]",l=!!b.attachEvent&&!l,u=r?"object":l?"script":"img",v=l?"script":u,w=Array.isArray||function(a){return o.call(a)=="[object Array]"},x=[],y={},z={timeout:function(a,b){return b.length&&(a.timeout=b[0]),a}},A,B;B=function(a){function b(a){var a=a.split("!"),b=x.length,c=a.pop(),d=a.length,c={url:c,origUrl:c,prefixes:a},e,f,g;for(f=0;f<d;f++)g=a[f].split("="),(e=z[g.shift()])&&(c=e(c,g));for(f=0;f<b;f++)c=x[f](c);return c}function g(a,e,f,g,i){var j=b(a),l=j.autoCallback;j.url.split(".").pop().split("?").shift(),j.bypass||(e&&(e=d(e)?e:e[a]||e[g]||e[a.split("/").pop().split("?")[0]]||h),j.instead?j.instead(a,e,f,g,i):(y[j.url]?j.noexec=!0:y[j.url]=1,f.load(j.url,j.forceCSS||!j.forceJS&&"css"==j.url.split(".").pop().split("?").shift()?"c":c,j.noexec,j.attrs,j.timeout),(d(e)||d(l))&&f.load(function(){k(),e&&e(j.origUrl,i,g),l&&l(j.origUrl,i,g),y[j.url]=2})))}function i(a,b){function c(a,c){if(a){if(e(a))c||(j=function(){var a=[].slice.call(arguments);k.apply(this,a),l()}),g(a,j,b,0,h);else if(Object(a)===a)for(n in m=function(){var b=0,c;for(c in a)a.hasOwnProperty(c)&&b++;return b}(),a)a.hasOwnProperty(n)&&(!c&&!--m&&(d(j)?j=function(){var a=[].slice.call(arguments);k.apply(this,a),l()}:j[n]=function(a){return function(){var b=[].slice.call(arguments);a&&a.apply(this,b),l()}}(k[n])),g(a[n],j,b,n,h))}else!c&&l()}var h=!!a.test,i=a.load||a.both,j=a.callback||f,k=j,l=a.complete||f,m,n;c(h?a.yep:a.nope,!!i),i&&c(i)}var j,l,m=this.yepnope.loader;if(e(a))g(a,0,m,0);else if(w(a))for(j=0;j<a.length;j++)l=a[j],e(l)?g(l,0,m,0):w(l)?B(l):Object(l)===l&&i(l,m);else Object(a)===a&&i(a,m)},B.addPrefix=function(a,b){z[a]=b},B.addFilter=function(a){x.push(a)},B.errorTimeout=1e4,b.readyState==null&&b.addEventListener&&(b.readyState="loading",b.addEventListener("DOMContentLoaded",A=function(){b.removeEventListener("DOMContentLoaded",A,0),b.readyState="complete"},0)),a.yepnope=k(),a.yepnope.executeStack=h,a.yepnope.injectJs=function(a,c,d,e,i,j){var k=b.createElement("script"),l,o,e=e||B.errorTimeout;k.src=a;for(o in d)k.setAttribute(o,d[o]);c=j?h:c||f,k.onreadystatechange=k.onload=function(){!l&&g(k.readyState)&&(l=1,c(),k.onload=k.onreadystatechange=null)},m(function(){l||(l=1,c(1))},e),i?k.onload():n.parentNode.insertBefore(k,n)},a.yepnope.injectCss=function(a,c,d,e,g,i){var e=b.createElement("link"),j,c=i?h:c||f;e.href=a,e.rel="stylesheet",e.type="text/css";for(j in d)e.setAttribute(j,d[j]);g||(n.parentNode.insertBefore(e,n),m(c,0))}}(this,document),Modernizr.load=function(){yepnope.apply(window,[].slice.call(arguments,0))};;
/*! fancyBox v2.0.6 fancyapps.com | fancyapps.com/fancybox/#license */
(function(s,l,d,t){var m=d(s),q=d(l),a=d.fancybox=function(){a.open.apply(this,arguments)},u=!1,k=l.createTouch!==t,o=function(a){return"string"===d.type(a)},n=function(b,c){c&&o(b)&&0<b.indexOf("%")&&(b=a.getViewport()[c]/100*parseInt(b,10));return Math.round(b)+"px"};d.extend(a,{version:"2.0.5",defaults:{padding:15,margin:20,width:800,height:600,minWidth:100,minHeight:100,maxWidth:9999,maxHeight:9999,autoSize:!0,autoResize:!k,autoCenter:!k,fitToView:!0,aspectRatio:!1,topRatio:0.5,fixed:!1,scrolling:"auto",
wrapCSS:"",arrows:!0,closeBtn:!0,closeClick:!1,nextClick:!1,mouseWheel:!0,autoPlay:!1,playSpeed:3E3,preload:3,modal:!1,loop:!0,ajax:{dataType:"html",headers:{"X-fancyBox":!0}},keys:{next:[13,32,34,39,40],prev:[8,33,37,38],close:[27]},tpl:{wrap:'<div class="fancybox-wrap"><div class="fancybox-skin"><div class="fancybox-outer"><div class="fancybox-inner"></div></div></div></div>',image:'<img class="fancybox-image" src="{href}" alt="" />',iframe:'<iframe class="fancybox-iframe" name="fancybox-frame{rnd}" frameborder="0" hspace="0"'+
(d.browser.msie?' allowtransparency="true"':"")+"></iframe>",swf:'<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="100%" height="100%"><param name="wmode" value="transparent" /><param name="allowfullscreen" value="true" /><param name="allowscriptaccess" value="always" /><param name="movie" value="{href}" /><embed src="{href}" type="application/x-shockwave-flash" allowfullscreen="true" allowscriptaccess="always" width="100%" height="100%" wmode="transparent"></embed></object>',error:'<p class="fancybox-error">The requested content cannot be loaded.<br/>Please try again later.</p>',
closeBtn:'<div title="Close" class="fancybox-item fancybox-close"></div>',next:'<a title="Next" class="fancybox-nav fancybox-next"><span></span></a>',prev:'<a title="Previous" class="fancybox-nav fancybox-prev"><span></span></a>'},openEffect:"fade",openSpeed:300,openEasing:"swing",openOpacity:!0,openMethod:"zoomIn",closeEffect:"fade",closeSpeed:300,closeEasing:"swing",closeOpacity:!0,closeMethod:"zoomOut",nextEffect:"elastic",nextSpeed:300,nextEasing:"swing",nextMethod:"changeIn",prevEffect:"elastic",
prevSpeed:300,prevEasing:"swing",prevMethod:"changeOut",helpers:{overlay:{speedIn:0,speedOut:300,opacity:0.8,css:{cursor:"pointer"},closeClick:!0},title:{type:"float"}}},group:{},opts:{},coming:null,current:null,isOpen:!1,isOpened:!1,player:{timer:null,isActive:!1},ajaxLoad:null,imgPreload:null,transitions:{},helpers:{},open:function(b,c){a.close(!0);b&&!d.isArray(b)&&(b=b instanceof d?d(b).get():[b]);a.isActive=!0;a.opts=d.extend(!0,{},a.defaults,c);d.isPlainObject(c)&&c.keys!==t&&(a.opts.keys=c.keys?
d.extend({},a.defaults.keys,c.keys):!1);a.group=b;a._start(a.opts.index||0)},cancel:function(){a.coming&&!1===a.trigger("onCancel")||(a.coming=null,a.hideLoading(),a.ajaxLoad&&a.ajaxLoad.abort(),a.ajaxLoad=null,a.imgPreload&&(a.imgPreload.onload=a.imgPreload.onabort=a.imgPreload.onerror=null))},close:function(b){a.cancel();a.current&&!1!==a.trigger("beforeClose")&&(a.unbindEvents(),!a.isOpen||b&&!0===b[0]?(d(".fancybox-wrap").stop().trigger("onReset").remove(),a._afterZoomOut()):(a.isOpen=a.isOpened=
!1,d(".fancybox-item, .fancybox-nav").remove(),a.wrap.stop(!0).removeClass("fancybox-opened"),a.inner.css("overflow","hidden"),a.transitions[a.current.closeMethod]()))},play:function(b){var c=function(){clearTimeout(a.player.timer)},e=function(){c();a.current&&a.player.isActive&&(a.player.timer=setTimeout(a.next,a.current.playSpeed))},f=function(){c();d("body").unbind(".player");a.player.isActive=!1;a.trigger("onPlayEnd")};if(a.player.isActive||b&&!1===b[0])f();else if(a.current&&(a.current.loop||
a.current.index<a.group.length-1))a.player.isActive=!0,d("body").bind({"afterShow.player onUpdate.player":e,"onCancel.player beforeClose.player":f,"beforeLoad.player":c}),e(),a.trigger("onPlayStart")},next:function(){a.current&&a.jumpto(a.current.index+1)},prev:function(){a.current&&a.jumpto(a.current.index-1)},jumpto:function(b){a.current&&(b=parseInt(b,10),1<a.group.length&&a.current.loop&&(b>=a.group.length?b=0:0>b&&(b=a.group.length-1)),a.group[b]!==t&&(a.cancel(),a._start(b)))},reposition:function(b,
c){var e;a.isOpen&&(e=a._getPosition(c),b&&"scroll"===b.type?(delete e.position,a.wrap.stop(!0,!0).animate(e,200)):a.wrap.css(e))},update:function(b){a.isOpen&&(u||setTimeout(function(){var c=a.current,e=!b||b&&"orientationchange"===b.type;if(u&&(u=!1,c)){if(!b||"scroll"!==b.type||e)c.autoSize&&"iframe"!==c.type&&(a.inner.height("auto"),c.height=a.inner.height()),(c.autoResize||e)&&a._setDimension(),c.canGrow&&"iframe"!==c.type&&a.inner.height("auto");(c.autoCenter||e)&&a.reposition(b);a.trigger("onUpdate")}},
200),u=!0)},toggle:function(){a.isOpen&&(a.current.fitToView=!a.current.fitToView,a.update())},hideLoading:function(){q.unbind("keypress.fb");d("#fancybox-loading").remove()},showLoading:function(){a.hideLoading();q.bind("keypress.fb",function(b){27===b.keyCode&&(b.preventDefault(),a.cancel())});d('<div id="fancybox-loading"><div></div></div>').click(a.cancel).appendTo("body")},getViewport:function(){return{x:m.scrollLeft(),y:m.scrollTop(),w:k&&s.innerWidth?s.innerWidth:m.width(),h:k&&s.innerHeight?
s.innerHeight:m.height()}},unbindEvents:function(){a.wrap&&a.wrap.unbind(".fb");q.unbind(".fb");m.unbind(".fb")},bindEvents:function(){var b=a.current,c=b.keys;b&&(m.bind("resize.fb orientationchange.fb"+(b.autoCenter&&!b.fixed?" scroll.fb":""),a.update),c&&q.bind("keydown.fb",function(b){var f;f=b.target||b.srcElement;if(!b.ctrlKey&&!b.altKey&&!b.shiftKey&&!b.metaKey&&(!f||!f.type&&!d(f).is("[contenteditable]")))f=b.keyCode,-1<d.inArray(f,c.close)?(a.close(),b.preventDefault()):-1<d.inArray(f,c.next)?
(a.next(),b.preventDefault()):-1<d.inArray(f,c.prev)&&(a.prev(),b.preventDefault())}),d.fn.mousewheel&&b.mouseWheel&&1<a.group.length&&a.wrap.bind("mousewheel.fb",function(b,c){var d=b.target||null;if(0!==c&&(!d||0===d.clientHeight||d.scrollHeight===d.clientHeight&&d.scrollWidth===d.clientWidth))b.preventDefault(),a[0<c?"prev":"next"]()}))},trigger:function(b,c){var e,f=c||a[-1<d.inArray(b,["onCancel","beforeLoad","afterLoad"])?"coming":"current"];if(f){d.isFunction(f[b])&&(e=f[b].apply(f,Array.prototype.slice.call(arguments,
1)));if(!1===e)return!1;f.helpers&&d.each(f.helpers,function(c,e){if(e&&d.isPlainObject(a.helpers[c])&&d.isFunction(a.helpers[c][b]))a.helpers[c][b](e,f)});d.event.trigger(b+".fb")}},isImage:function(a){return o(a)&&a.match(/\.(jpe?g|gif|png|bmp)((\?|#).*)?$/i)},isSWF:function(a){return o(a)&&a.match(/\.(swf)((\?|#).*)?$/i)},_start:function(b){var c={},e=a.group[b]||null,f,g,i;if(e&&(e.nodeType||e instanceof d))f=!0,d.metadata&&(c=d(e).metadata());c=d.extend(!0,{},a.opts,{index:b,element:e},d.isPlainObject(e)?
e:c);d.each(["href","title","content","type"],function(b,g){c[g]=a.opts[g]||f&&d(e).attr(g)||c[g]||null});"number"===typeof c.margin&&(c.margin=[c.margin,c.margin,c.margin,c.margin]);c.modal&&d.extend(!0,c,{closeBtn:!1,closeClick:!1,nextClick:!1,arrows:!1,mouseWheel:!1,keys:null,helpers:{overlay:{css:{cursor:"auto"},closeClick:!1}}});a.coming=c;if(!1===a.trigger("beforeLoad"))a.coming=null;else{g=c.type;b=c.href||e;g||(f&&(g=d(e).data("fancybox-type"),g||(g=(g=e.className.match(/fancybox\.(\w+)/))?
g[1]:null)),!g&&o(b)&&(a.isImage(b)?g="image":a.isSWF(b)?g="swf":b.match(/^#/)&&(g="inline")),g||(g=f?"inline":"html"),c.type=g);if("inline"===g||"html"===g){if(c.content||(c.content="inline"===g?d(o(b)?b.replace(/.*(?=#[^\s]+$)/,""):b):e),!c.content||!c.content.length)g=null}else b||(g=null);"ajax"===g&&o(b)&&(i=b.split(/\s+/,2),b=i.shift(),c.selector=i.shift());c.href=b;c.group=a.group;c.isDom=f;switch(g){case "image":a._loadImage();break;case "ajax":a._loadAjax();break;case "inline":case "iframe":case "swf":case "html":a._afterLoad();
break;default:a._error("type")}}},_error:function(b){a.hideLoading();d.extend(a.coming,{type:"html",autoSize:!0,minWidth:0,minHeight:0,padding:15,hasError:b,content:a.coming.tpl.error});a._afterLoad()},_loadImage:function(){var b=a.imgPreload=new Image;b.onload=function(){this.onload=this.onerror=null;a.coming.width=this.width;a.coming.height=this.height;a._afterLoad()};b.onerror=function(){this.onload=this.onerror=null;a._error("image")};b.src=a.coming.href;(b.complete===t||!b.complete)&&a.showLoading()},
_loadAjax:function(){a.showLoading();a.ajaxLoad=d.ajax(d.extend({},a.coming.ajax,{url:a.coming.href,error:function(b,c){a.coming&&"abort"!==c?a._error("ajax",b):a.hideLoading()},success:function(b,c){"success"===c&&(a.coming.content=b,a._afterLoad())}}))},_preloadImages:function(){var b=a.group,c=a.current,e=b.length,f,g,i,h=Math.min(c.preload,e-1);if(c.preload&&!(2>b.length))for(i=1;i<=h;i+=1)if(f=b[(c.index+i)%e],g=f.href||d(f).attr("href")||f,"image"===f.type||a.isImage(g))(new Image).src=g},_afterLoad:function(){a.hideLoading();
!a.coming||!1===a.trigger("afterLoad",a.current)?a.coming=!1:(a.isOpened?(d(".fancybox-item, .fancybox-nav").remove(),a.wrap.stop(!0).removeClass("fancybox-opened"),a.inner.css("overflow","hidden"),a.transitions[a.current.prevMethod]()):(d(".fancybox-wrap").stop().trigger("onReset").remove(),a.trigger("afterClose")),a.unbindEvents(),a.isOpen=!1,a.current=a.coming,a.wrap=d(a.current.tpl.wrap).addClass("fancybox-"+(k?"mobile":"desktop")+" fancybox-type-"+a.current.type+" fancybox-tmp "+a.current.wrapCSS).appendTo("body"),
a.skin=d(".fancybox-skin",a.wrap).css("padding",n(a.current.padding)),a.outer=d(".fancybox-outer",a.wrap),a.inner=d(".fancybox-inner",a.wrap),a._setContent())},_setContent:function(){var b=a.current,c=b.content,e=b.type,f=b.minWidth,g=b.minHeight,i=b.maxWidth,h=b.maxHeight;switch(e){case "inline":case "ajax":case "html":b.selector?c=d("<div>").html(c).find(b.selector):c instanceof d&&(c.parent().hasClass("fancybox-inner")&&c.parents(".fancybox-wrap").unbind("onReset"),c=c.show().detach(),d(a.wrap).bind("onReset",
function(){c.appendTo("body").hide()}));b.autoSize&&(f=d('<div class="fancybox-wrap '+a.current.wrapCSS+' fancybox-tmp"></div>').appendTo("body").css({minWidth:n(f,"w"),minHeight:n(g,"h"),maxWidth:n(i,"w"),maxHeight:n(h,"h")}).append(c),b.width=f.width(),b.height=f.height(),f.width(a.current.width),f.height()>b.height&&(f.width(b.width+1),b.width=f.width(),b.height=f.height()),c=f.contents().detach(),f.remove());break;case "image":c=b.tpl.image.replace("{href}",b.href);b.aspectRatio=!0;break;case "swf":c=
b.tpl.swf.replace(/\{width\}/g,b.width).replace(/\{height\}/g,b.height).replace(/\{href\}/g,b.href);break;case "iframe":c=d(b.tpl.iframe.replace("{rnd}",(new Date).getTime())).attr("scrolling",b.scrolling).attr("src",b.href),b.scrolling=k?"scroll":"auto"}if("image"===e||"swf"===e)b.autoSize=!1,b.scrolling="visible";"iframe"===e&&b.autoSize?(a.showLoading(),a._setDimension(),a.inner.css("overflow",b.scrolling),c.bind({onCancel:function(){d(this).unbind();a._afterZoomOut()},load:function(){a.hideLoading();
try{this.contentWindow.document.location&&(a.current.height=d(this).contents().find("body").height())}catch(b){a.current.autoSize=!1}a[a.isOpen?"_afterZoomIn":"_beforeShow"]()}}).appendTo(a.inner)):(a.inner.append(c),a._beforeShow())},_beforeShow:function(){a.coming=null;a.trigger("beforeShow");a._setDimension();a.wrap.hide().removeClass("fancybox-tmp");a.bindEvents();a._preloadImages();a.transitions[a.isOpened?a.current.nextMethod:a.current.openMethod]()},_setDimension:function(){var b=a.wrap,c=
a.inner,e=a.current,f=a.getViewport(),g=e.margin,i=2*e.padding,h=e.width,j=e.height,r=e.maxWidth+i,k=e.maxHeight+i,l=e.minWidth+i,m=e.minHeight+i,p;f.w-=g[1]+g[3];f.h-=g[0]+g[2];o(h)&&0<h.indexOf("%")&&(h=(f.w-i)*parseFloat(h)/100);o(j)&&0<j.indexOf("%")&&(j=(f.h-i)*parseFloat(j)/100);g=h/j;h+=i;j+=i;e.fitToView&&(r=Math.min(f.w,r),k=Math.min(f.h,k));if(e.aspectRatio){if(h>r&&(h=r,j=(h-i)/g+i),j>k&&(j=k,h=(j-i)*g+i),h<l&&(h=l,j=(h-i)/g+i),j<m)j=m,h=(j-i)*g+i}else h=Math.max(l,Math.min(h,r)),j=Math.max(m,
Math.min(j,k));h=Math.round(h);j=Math.round(j);d(b.add(c)).width("auto").height("auto");c.width(h-i).height(j-i);b.width(h);p=b.height();if(h>r||p>k)for(;(h>r||p>k)&&h>l&&p>m;)j-=10,e.aspectRatio?(h=Math.round((j-i)*g+i),h<l&&(h=l,j=(h-i)/g+i)):h-=10,c.width(h-i).height(j-i),b.width(h),p=b.height();e.dim={width:n(h),height:n(p)};e.canGrow=e.autoSize&&j>m&&j<k;e.canShrink=!1;e.canExpand=!1;if(h-i<e.width||j-i<e.height)e.canExpand=!0;else if((h>f.w||p>f.h)&&h>l&&j>m)e.canShrink=!0;a.innerSpace=p-i-
c.height()},_getPosition:function(b){var c=a.current,e=a.getViewport(),f=c.margin,d=a.wrap.width()+f[1]+f[3],i=a.wrap.height()+f[0]+f[2],h={position:"absolute",top:f[0]+e.y,left:f[3]+e.x};c.autoCenter&&c.fixed&&!b&&i<=e.h&&d<=e.w&&(h={position:"fixed",top:f[0],left:f[3]});h.top=n(Math.max(h.top,h.top+(e.h-i)*c.topRatio));h.left=n(Math.max(h.left,h.left+0.5*(e.w-d)));return h},_afterZoomIn:function(){var b=a.current,c=b?b.scrolling:"no";if(b&&(a.isOpen=a.isOpened=!0,a.wrap.addClass("fancybox-opened"),
a.inner.css("overflow","yes"===c?"scroll":"no"===c?"hidden":c),a.trigger("afterShow"),a.update(),(b.closeClick||b.nextClick)&&a.inner.css("cursor","pointer").bind("click.fb",function(c){if(!d(c.target).is("a")&&!d(c.target).parent().is("a"))a[b.closeClick?"close":"next"]()}),b.closeBtn&&d(b.tpl.closeBtn).appendTo(a.skin).bind("click.fb",a.close),b.arrows&&1<a.group.length&&((b.loop||0<b.index)&&d(b.tpl.prev).appendTo(a.outer).bind("click.fb",a.prev),(b.loop||b.index<a.group.length-1)&&d(b.tpl.next).appendTo(a.outer).bind("click.fb",
a.next)),a.opts.autoPlay&&!a.player.isActive))a.opts.autoPlay=!1,a.play()},_afterZoomOut:function(){var b=a.current;a.wrap.trigger("onReset").remove();d.extend(a,{group:{},opts:{},current:null,isActive:!1,isOpened:!1,isOpen:!1,wrap:null,skin:null,outer:null,inner:null});a.trigger("afterClose",b)}});a.transitions={getOrigPosition:function(){var b=a.current,c=b.element,e=b.padding,f=d(b.orig),g={},i=50,h=50;!f.length&&b.isDom&&d(c).is(":visible")&&(f=d(c).find("img:first"),f.length||(f=d(c)));f.length?
(g=f.offset(),f.is("img")&&(i=f.outerWidth(),h=f.outerHeight())):(b=a.getViewport(),g.top=b.y+0.5*(b.h-h),g.left=b.x+0.5*(b.w-i));return g={top:n(g.top-e),left:n(g.left-e),width:n(i+2*e),height:n(h+2*e)}},step:function(b,c){var e=c.prop,d,g;if("width"===e||"height"===e)d=Math.ceil(b-2*a.current.padding),"height"===e&&(g=(b-c.start)/(c.end-c.start),c.start>c.end&&(g=1-g),d-=a.innerSpace*g),a.inner[e](d)},zoomIn:function(){var b=a.wrap,c=a.current,e=c.openEffect,f="elastic"===e,g=d.extend({},c.dim,
a._getPosition(f)),i=d.extend({opacity:1},g);delete i.position;f?(g=this.getOrigPosition(),c.openOpacity&&(g.opacity=0),a.outer.add(a.inner).width("auto").height("auto")):"fade"===e&&(g.opacity=0);b.css(g).show().animate(i,{duration:"none"===e?0:c.openSpeed,easing:c.openEasing,step:f?this.step:null,complete:a._afterZoomIn})},zoomOut:function(){var b=a.wrap,c=a.current,d=c.openEffect,f="elastic"===d,g={opacity:0};f&&("fixed"===b.css("position")&&b.css(a._getPosition(!0)),g=this.getOrigPosition(),c.closeOpacity&&
(g.opacity=0));b.animate(g,{duration:"none"===d?0:c.closeSpeed,easing:c.closeEasing,step:f?this.step:null,complete:a._afterZoomOut})},changeIn:function(){var b=a.wrap,c=a.current,d=c.nextEffect,f="elastic"===d,g=a._getPosition(f),i={opacity:1};g.opacity=0;f&&(g.top=n(parseInt(g.top,10)-200),i.top="+=200px");b.css(g).show().animate(i,{duration:"none"===d?0:c.nextSpeed,easing:c.nextEasing,complete:a._afterZoomIn})},changeOut:function(){var b=a.wrap,c=a.current,e=c.prevEffect,f={opacity:0};b.removeClass("fancybox-opened");
"elastic"===e&&(f.top="+=200px");b.animate(f,{duration:"none"===e?0:c.prevSpeed,easing:c.prevEasing,complete:function(){d(this).trigger("onReset").remove()}})}};a.helpers.overlay={overlay:null,update:function(){var a,c;this.overlay.width("100%").height("100%");d.browser.msie||k?(a=Math.max(l.documentElement.scrollWidth,l.body.scrollWidth),c=Math.max(l.documentElement.offsetWidth,l.body.offsetWidth),a=a<c?m.width():a):a=q.width();this.overlay.width(a).height(q.height())},beforeShow:function(b){this.overlay||
(b=d.extend(!0,{},a.defaults.helpers.overlay,b),this.overlay=d('<div id="fancybox-overlay"></div>').css(b.css).appendTo("body"),b.closeClick&&this.overlay.bind("click.fb",a.close),a.current.fixed&&!k?this.overlay.addClass("overlay-fixed"):(this.update(),this.onUpdate=function(){this.update()}),this.overlay.fadeTo(b.speedIn,b.opacity))},afterClose:function(a){this.overlay&&this.overlay.fadeOut(a.speedOut||0,function(){d(this).remove()});this.overlay=null}};a.helpers.title={beforeShow:function(b){var c;
if(c=a.current.title)c=d('<div class="fancybox-title fancybox-title-'+b.type+'-wrap">'+c+"</div>").appendTo("body"),"float"===b.type&&(c.width(c.width()),c.wrapInner('<span class="child"></span>'),a.current.margin[2]+=Math.abs(parseInt(c.css("margin-bottom"),10))),c.appendTo("over"===b.type?a.inner:"outside"===b.type?a.wrap:a.skin)}};d.fn.fancybox=function(b){var c=d(this),e=this.selector||"",f,g=function(g){var h=this,j=f,k;!g.ctrlKey&&!g.altKey&&!g.shiftKey&&!g.metaKey&&!d(h).is(".fancybox-wrap")&&
(g.preventDefault(),g=b.groupAttr||"data-fancybox-group",k=d(h).attr(g),k||(g="rel",k=h[g]),k&&""!==k&&"nofollow"!==k&&(h=e.length?d(e):c,h=h.filter("["+g+'="'+k+'"]'),j=h.index(this)),b.index=j,a.open(h,b))},b=b||{};f=b.index||0;e?q.undelegate(e,"click.fb-start").delegate(e,"click.fb-start",g):c.unbind("click.fb-start").bind("click.fb-start",g);return this};d(l).ready(function(){a.defaults.fixed=d.support.fixedPosition||!(d.browser.msie&&6>=d.browser.version)&&!k})})(window,document,jQuery);;
/** 
 *  Slider Kit v1.9.2 (packed)
 *  http://www.kyrielles.net/sliderkit
 *  
 *  Copyright (c) 2010-2012 Alan Frog
 *  Licensed under the GNU General Public License
 */
eval(function(p,a,c,k,e,r){e=function(c){return(c<a?'':e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--)r[e(c)]=k[c]||e(c);k=[function(e){return r[e]}];e=function(){return'\\w+'};c=1};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p}('(7($){2p=7(){p n=3;3.2q=7(c,d){3.4=$.3r({},3.2r,d);3.8={1c:3.4.u+"-1c",1d:3.4.u+"-1d",w:3.4.u+"-w",B:3.4.u+"-1d-3s",T:3.4.u+"-1d-3t",U:3.4.u+"-w-3u",v:3.4.u+"-v",P:3.4.u+"-v-3v",2s:3.4.u+"-v-1e",16:3.4.u+"-v-2t",17:3.4.u+"-v-2u",C:3.4.u+"-1e-3w",1f:3.4.u+"-3x-1e",2v:3.4.u+"-1T-2t",2w:3.4.u+"-1T-2u",J:3.4.u+"-3y-1e",V:3.4.u+"-1T-1e"};3.q=$(c);3.w=$("."+3.8.1d,3.q);3.s=3.w.K();3.v=$("."+3.8.v,3.q);3.P=$("."+3.8.P,3.v);3.G=3.s>0?1:0;3.1C=3.P.K()>0?1:0;6(!3.G&&!3.1C){3.L("1D #3z",3.4.M,1)}3.1g=3.q.H();3.1h=3.q.I();6(!3.1g&&!3.4.1E){3.1g=3.4.H;3.q.x(\'H\',3.1g);3.L("1D #2x",3.4.M,0)}6(!3.1h){3.1h=3.4.I;3.q.x(\'I\',3.1h);3.L("1D #2x",3.4.M,0)}3.q.x(\'2y\',\'3A\');3.o=0;3.W=0;3.1i=0;3.Q=r;3.1U=0;3.2z=0;3.1j=1;3.1k=0;3.X=r;3.3B=9;3.3C=1;3.2A=9;3.2B=1l 1F;3.2C=1l 1F;3.2D=1l 1F;3.2E=1l 1F;3.1V=3.v;6(3.1C){3.2F()}3.2G();6(3.G){3.1W=$("."+3.8.w,3.q);6(3.4.1G=="1H"){3.2H()}}3.1I=!3.G?1:0;6(3.4.1X){3.q.1X(7(a,b){b>0?n.1J():n.1m();y 9})}6(3.4.2I){3.q.3D(7(a){6(a.2J==37){n.1J()}z 6(a.2J==39){n.1m()}})}6(3.4.2K&&3.G){3.1W.Y(7(){n.1m();y 9})}3.2L=3.4.1K>=3.s?3.s-1:3.4.1K<0?0:3.4.1K;6(3.4.2M){1L{3.3E()}1M(R){3.L(R,3.4.M,0)}}6(3.2N){1L{3.2N()}1M(R){3.L(R,3.4.M,0)}}6(3.4.1Y){1L{3.3F(3.4.1Y)}1M(R){3.L(R,3.4.M,0)}}3.1N(3.2L,r);6(3.4.1n){3.1o();3.2O()}6(3.4.1Z){1L{3.3G(3.4.1Z)}1M(R){3.L(R,3.4.M,0)}}6(3.G&&!3.4.2P){3.1V=3.q.3H(\'.\'+3.8.w,\'.\'+3.8.v)}y 3};3.2r={u:"20",I:3I,H:3J,1K:0,1n:t,2Q:3K,1O:9,1X:9,2I:9,1p:9,N:5,2R:9,2S:9,2T:9,21:9,2U:t,2V:"1H",22:7(){},23:7(){},O:r,2W:3L,2X:r,1G:"24",1P:3M,25:r,2Y:"1q",26:7(){},27:7(){},2Z:9,2K:9,D:9,30:9,31:9,1E:9,2P:t,2M:9,1Y:9,1Z:9,3N:9,M:9};3.L=7(a,b,c){6(b){3O("3P 3Q 3R!\\3S = "+a+" (3T 3U 3V 3W)\\32 33 = "+3.q.34("33")+"\\32 28 = "+3.q.34("28"))}6(c){y 9}};3.2O=7(){6(!3.1Q&&!3.4.1O){3.q.35(7(){6(n.X!=r){n.1r()}},7(){n.1o()})}6(3.4.1O){3.q.3X(7(){6(n.X==r){n.1o()}})}};3.2F=7(){3.S=$("3Y",3.P);3.E=$("29",3.S);3.1s=3.E.K();6(3.G&&(3.1s!=3.s)&&3.v.K()==1){3.L("1D #3Z",3.4.M,1)}6(3.4.31){3.4.N=3.s}z{7 1t(a){2a=n.E.x(a);6(2a!="1n"&&a!=""&&a!="40"){y 36(2a)}z y 0}p c=3.4.D?3.v.H():3.v.I();p d=3.E.41(t);p e=3.E.42(t);p f=1t("1u-Z")+1t("1u-43");p g=1t("1u-10")+1t("1u-44");3.s=3.1s;6(3.4.N>3.s){3.4.N=3.1s}3.11=3.4.D?e:d;3.2b=3.11*3.1s;3.1v=(3.4.N*3.11)-(3.4.D?g:f);3.1R=3.4.D?"10":"Z";p h=3.4.D?"H":"I";p i=3.4.D?"I":"H";3.E.x({I:3.E.I(),H:3.E.H()});3.S.x(h,3.2b+"2c");3.P.x({I:3.4.D?d:3.1v,H:3.4.D?3.1v:e});6(3.4.2S){3.P.x(3.1R,(c-3.1v)/2).x("1u","0")}6(3.s>3.4.N){3.1k=t;6(3.4.O==r||3.4.O<0||3.4.O>3.s){3.4.O=3.4.N}}3.18=$(\'.\'+3.8.2s,3.v);6(3.18.K()>0){3.38()}}6(3.4.2R&&3.G){3.E.2d(7(){n.1N(2e(3,"29"),$(3))})}z 6(3.G||3.4.21){3.E.Y(7(){n.1N(2e(3,"29"),$(3));y 9})}7 2e(a,b){y $(b,$(a).2f()).45(a)}};3.38=7(){6(3.1k){3.3a=t;3.19=$("."+3.8.16,3.v);3.1w=$("."+3.8.17,3.v);3.18.F(3.8.C);3.19.Y(7(){n.16();y 9});3.1w.Y(7(){n.17();y 9});6(3.4.2T){3.19.2d(7(){n.16(t)});3.1w.2d(7(){n.17(t)});3.18.46(7(){n.3b()})}6(!3.4.1p){3.19.A(3.8.C)}}z{3.18.A(3.8.C)}};3.2g=7(){3.1a=3.4.D?3.S.2h().10:3.S.2h().Z;3.12=13.47(13.1x(3.1a)/3.11);3.14=13.48((3.2b-13.1x(3.1a)-3.1v)/3.11);6(3.14<0){3.14=0}};3.2G=7(){3.J=$("."+3.8.J,3.q);3.1b=$("."+3.8.V,3.q);3.1Q=3.J.K()>0?1:0;3.V=3.1b.K()>0?1:0;6(3.1Q){6(3.4.1n){3.J.A(3.8.1f)}3.J.Y(7(){6(n.J.1S(n.8.1f)){n.2i()}z{n.3c()}y 9})}6(3.V){3.2j=$("."+3.8.2v,3.q);3.2k=$("."+3.8.2w,3.q);6(3.4.2Z){3.1b.1y();$("."+3.8.w,3.q).35(7(){n.1b.3d()},7(){n.1b.49()})}3.2j.Y(7(){n.1J($(3));y 9});3.2k.Y(7(){n.1m($(3));y 9})}};3.2H=7(){6($(\'.\'+3.8.U,3.q).K()==0){3.w.4a(\'<3e 28="\'+3.8.U+\'"></3e>\');3.U=$(\'.\'+3.8.U,3.1W);3.U.x(\'2h\',\'4b\')}};3.15=7(a,b,c,d,e){6(e&&3.X!=r){6(3.1Q){3.2i()}6(3.4.1O){n.1r()}}6(a){6(a.1S(3.8.C)){y 9}}p f=0;p g=$(\':4c\',3.1V).K()>0?1:0;6(!g&&!3.2A){3.W=3.o;6(c==r&&!d){3.o=b=="-="?3.o+1:3.o-1}z 6(c!=r){c=36(c);3.o=c<0?0:c>3.s-1?3.s-1:c;p h=a?a.2f().2f().1S(3.8.P)?9:t:t}6(3.V){3.1b.F(3.8.C)}6(!3.4.1p){6(3.o==-1){3.o=0;f=1}6(3.o==0&&3.V){3.2j.A(3.8.C)}6(3.o==3.s){3.o=3.s-1;f=1}6(3.o==3.s-1){6(3.4.1n){3.1r()}6(3.V){3.2k.A(3.8.C)}}}z 6(!3.1k){6(3.o==3.s){3.o=0}6(3.o==-1){3.o=3.s-1}}6(3.1k&&!f){3.3f(d,b,h)}6(3.1C){3.3g(3.o)}6(!(d&&!3.4.2U)){6(3.G){3.3h(3.o,b)}}6(3.1j){3.1j=0}}};3.3f=7(a,b,c){3.2g();p d=a?t:9;p e=0;6(!a){p f=13.1x(3.o+1-3.12);p g=3.4.N-f+1;p h=3.o==0||3.o==3.s-1?1:0;6((3.4.21&&(g==1||f==1))&&!3.1j&&!h){e=3.4.O-1;d=t}6(g==0||f==0){d=t}6(c){6(g<0){g=0}b=3.W<3.o?\'-=\':\'+=\';p i=13.1x(3.W-3.o);6((i-1>g&&b==\'-=\')||(i>f&&b==\'+=\')){e=i;d=t}}6(b==""){6(3.W==3.o&&!h){b=3.3i=="-="?"+=":"-="}z{b=3.W<3.o?"-=":"+="}}3.3i=b}6(d){p j=e>0?e:3.4.O;p k=b=="-="?3.14:3.12;p l=k<j?k:j;p m=l*3.11;3.1i=b=="-="?3.12+l:3.12-l+3.4.N-1;6((b=="-="&&3.1i>3.o)||(b=="+="&&3.1i<3.o)){3.o=3.1i}6(3.4.1p){6(3.12<=0&&b=="+="){b="-=";3.o=3.s-1;m=(3.14/3.4.O)*(3.11*3.4.O)}z 6(3.14==0&&b=="-="){b="+=";3.o=0;m=13.1x(3.1a)}}3.3j(b,m)}};3.3h=7(a,b){3.Q=3.w.2l(a);3.2z=3.w.2l(3.W);p c=7(){6($.1z(n.4.27)){n.4.27()}n.1A(n.2C)};6(!3.Q.1S(3.8.B)){6(3.1j){3.2m=3.4.2Y;p d=1}z{p e=3.4.1E&&3.4.1G=="24"?"3k":"1q";3.2m=3.4.1E?e:3.4.1G}6($.1z(n.4.26)){n.4.26()}3.1A(3.2B);3.3l[3.2m](b,d,c)}};3.3j=7(a,b){p c=7(){6(!n.4.1p&&n.3a){n.18.F(n.8.C);n.2g();6(n.12<=0){n.19.A(n.8.C)}z 6(n.14<=0){n.1w.A(n.8.C)}}6(n.1B){4d(7(){n.1B=="-="?n.16():n.17()},0)}z 6($.1z(n.4.23)){n.4.23()}n.1A(n.2E)};6($.1z(n.4.22)){n.4.22()}n.1A(n.2D);3.3m[3.4.2V](a,b,c)};3.1A=7(c){$.3n(c,7(a,b){6($.1z(b)){b()}})};3.4e=7(a){a.4f=0};3.3l={1q:7(a,b,c){n.w.F(n.8.B).1y();n.Q.A(n.8.B).2n();c()},1H:7(a,b,c){6(a==""){a=n.1U<n.o?"-=":"+="}n.1U=n.o;p d=a=="-="?"+":"-";p e=n.4.30?"10":"Z";p f=n.4.D?n.1g:n.1h;p g=e=="10"?{10:a+f}:{Z:a+f};n.3o=$("."+n.8.T,n.q);n.3p=$("."+n.8.B,n.q);n.w.x(e,"0");n.3o.F(n.8.T).1y();n.3p.F(n.8.B).A(n.8.T);n.Q.A(n.8.B).x(e,d+f+"2c").2n();n.U.3q(t,t).x(e,"0").2o(g,n.4.1P,n.4.25,7(){c()})},24:7(a,b,c){6(b){n.w.1y()}z{n.Q.x("2y","1q")}$("."+n.8.T,n.q).F(n.8.T);$("."+n.8.B,n.q).3q(t,t).F(n.8.B).A(n.8.T);n.Q.A(n.8.B).2o({"4g":"2n"},n.4.1P,n.4.25,7(){c()})},3k:7(a,b,c){n.w.F(n.8.B).1y();n.Q.3d(n.4.1P,7(){c()})}};3.3m={1q:7(a,b,c){p d=a=="-="?n.1a-b:n.1a+b;n.S.x(n.1R,d+"2c");c()},1H:7(a,b,c){n.S.2o(n.1R=="Z"?{Z:a+b}:{10:a+b},n.4.2W,n.4.2X,7(){c()})}};3.2i=7(){3.J.F(3.8.1f);3.1r()};3.3c=7(){3.J.A(n.8.1f);3.1o()};3.1o=7(){p a=3;3.X=4h(7(){a.15(r,"-=",r,a.1I,r)},a.4.2Q)};3.1r=7(){4i(3.X);3.X=r};3.1N=7(a,b){3.15(b,"",a,0,1)};3.1J=7(a){3.15(a,"+=",r,n.1I,1)};3.1m=7(a){3.15(a,"-=",r,n.1I,1)};3.16=7(c){6(c){n.1B="-="}3.15(3.19,"+=",r,1,1)};3.17=7(c){6(c){n.1B="+="}3.15(3.1w,"-=",r,1,1)};3.3b=7(){n.1B=""};3.3g=7(a){$("."+3.8.1c,3.S).F(3.8.1c);3.E.2l(a).A(3.8.1c)}};$.4j.20=7(a){y 3.3n(7(){$(3).4k("20",1l 2p().2q(3,a))})}})(4l);',62,270,'|||this|options||if|function|cssNames|false|||||||||||||||currId|var|domObj|null|allItems|true|cssprefix|nav|panels|css|return|else|addClass|panelActive|btnDisable|verticalnav|navLI|removeClass|arePanels|height|width|playBtn|size|_errorReport|debug|shownavitems|scroll|navClip|currPanel|err|navUL|panelOld|panelsWrapper|goBtns|prevId|isPlaying|click|left|top|navLIsize|LIbefore|Math|LIafter|_change|navPrev|navNext|navBtns|navBtnPrev|navPos|gBtns|selected|panel|btn|btnPause|domObjHeight|domObjWidth|newId|firstTime|scrollActive|new|stepForward|auto|autoScrollStart|circular|none|autoScrollStop|navLINum|getLImargin|margin|navClipSize|navBtnNext|abs|hide|isFunction|_runCallBacks|scrollcontinue|isNavClip|Error|freeheight|Array|panelfx|sliding|lineScrollDo|stepBackward|start|try|catch|changeWithId|autostill|panelfxspeed|isPlayBtn|cssPosAttr|hasClass|go|prevPanel|runningScope|panelsBag|mousewheel|delaycaptions|timer|sliderkit|navscrollatend|navfxbefore|navfxafter|fading|panelfxeasing|panelfxbefore|panelfxafter|class|li|attrVal|navULSize|px|mouseover|getIndex|parent|_getNavPos|position|playBtnPause|goBtnPrev|goBtnNext|eq|panelTransition|show|animate|SliderKit|_init|_settings|navBtn|prev|next|goPrev|goNext|02|display|prevPanelStill|animating|panelAnteFns|panelPostFns|navAnteFns|navPostFns|_buildNav|_buildControls|_wrapPanels|keyboard|keyCode|panelclick|startId|counter|imageFx|_autoScrollHoverStop|fastchange|autospeed|navitemshover|navclipcenter|navcontinuous|navpanelautoswitch|navfx|scrollspeed|scrolleasing|panelfxfirst|panelbtnshover|verticalslide|tabs|nElement|id|attr|hover|parseInt||_buildNavButtons||scrollBtns|navStopContinuous|playBtnStart|fadeIn|div|_setNavScroll|selectThumbnail|_animPanel|scrollWay|_animNav|tabsfading|_panelTransitions|_navTransitions|each|oldPanel|activePanel|stop|extend|active|old|wrapper|clip|disable|pause|play|01|block|changeOngoing|currLine|keyup|Counter|DelayCaptions|Timer|find|500|350|4000|600|700|imagefx|alert|Slider|Kit|error|nMessage|see|doc|for|details|mouseleave|ul|03|0px|outerWidth|outerHeight|right|bottom|index|mouseout|ceil|floor|fadeOut|wrapAll|relative|animated|setTimeout|_clearCallBacks|length|opacity|setInterval|clearTimeout|fn|data|jQuery'.split('|'),0,{}));
 /*!
 * Buttons helper for fancyBox
 * version: 1.0.2
 * @requires fancyBox v2.0 or later
 *
 * Usage: 
 *     $(".fancybox").fancybox({
 *         buttons: {
 *             position : 'top'
 *         }
 *     });
 * 
 * Options:
 *     tpl - HTML template
 *     position - 'top' or 'bottom'
 * 
 */
(function ($) {
	//Shortcut for fancyBox object
	var F = $.fancybox;

	//Add helper object
	F.helpers.buttons = {
		tpl: '<div id="fancybox-buttons"><ul><li><a class="btnPrev" title="Previous" href="javascript:;"></a></li><li><a class="btnPlay" title="Start slideshow" href="javascript:;"></a></li><li><a class="btnNext" title="Next" href="javascript:;"></a></li><li><a class="btnToggle" title="Toggle size" href="javascript:;"></a></li><li><a class="btnClose" title="Close" href="javascript:jQuery.fancybox.close();"></a></li></ul></div>',
		list: null,
		buttons: {},

		update: function () {
			var toggle = this.buttons.toggle.removeClass('btnDisabled btnToggleOn');

			//Size toggle button
			if (F.current.canShrink) {
				toggle.addClass('btnToggleOn');

			} else if (!F.current.canExpand) {
				toggle.addClass('btnDisabled');
			}
		},

		beforeLoad: function (opts) {
			//Remove self if gallery do not have at least two items
			if (F.group.length < 2) {
				F.coming.helpers.buttons = false;
				F.coming.closeBtn = true;

				return;
			}

			//Increase top margin to give space for buttons
			F.coming.margin[ opts.position === 'bottom' ? 2 : 0 ] += 30;
		},

		onPlayStart: function () {
			if (this.list) {
				this.buttons.play.attr('title', 'Pause slideshow').addClass('btnPlayOn');
			}
		},

		onPlayEnd: function () {
			if (this.list) {
				this.buttons.play.attr('title', 'Start slideshow').removeClass('btnPlayOn');
			}
		},

		afterShow: function (opts) {
			var buttons;

			if (!this.list) {
				this.list = $(opts.tpl || this.tpl).addClass(opts.position || 'top').appendTo('body');

				this.buttons = {
					prev : this.list.find('.btnPrev').click( F.prev ),
					next : this.list.find('.btnNext').click( F.next ),
					play : this.list.find('.btnPlay').click( F.play ),
					toggle : this.list.find('.btnToggle').click( F.toggle )
				}
			}

			buttons = this.buttons;

			//Prev
			if (F.current.index > 0 || F.current.loop) {
				buttons.prev.removeClass('btnDisabled');
			} else {
				buttons.prev.addClass('btnDisabled');
			}

			//Next / Play
			if (F.current.loop || F.current.index < F.group.length - 1) {
				buttons.next.removeClass('btnDisabled');
				buttons.play.removeClass('btnDisabled');

			} else {
				buttons.next.addClass('btnDisabled');
				buttons.play.addClass('btnDisabled');
			}

			this.update();
		},

		onUpdate: function () {
			this.update();
		},

		beforeClose: function () {
			if (this.list) {
				this.list.remove();
			}

			this.list = null;
			this.buttons = {};
		}
	};

}(jQuery));;
 /*!
 * Media helper for fancyBox
 * version: 1.0.0
 * @requires fancyBox v2.0 or later
 *
 * Usage:
 *     $(".fancybox").fancybox({
 *         media: {}
 *     });
 *
 *  Supports:
 *      Youtube
 *          http://www.youtube.com/watch?v=opj24KnzrWo
 *          http://youtu.be/opj24KnzrWo
 *      Vimeo
 *          http://vimeo.com/25634903
 *      Metacafe
 *          http://www.metacafe.com/watch/7635964/dr_seuss_the_lorax_movie_trailer/
 *          http://www.metacafe.com/watch/7635964/
 *      Dailymotion
 *          http://www.dailymotion.com/video/xoytqh_dr-seuss-the-lorax-premiere_people
 *      Twitvid
 *          http://twitvid.com/QY7MD
 *      Twitpic
 *          http://twitpic.com/7p93st
 *      Instagram
 *          http://instagr.am/p/IejkuUGxQn/
 *          http://instagram.com/p/IejkuUGxQn/
 *      Google maps
 *          http://maps.google.com/maps?q=Eiffel+Tower,+Avenue+Gustave+Eiffel,+Paris,+France&t=h&z=17
 *          http://maps.google.com/?ll=48.857995,2.294297&spn=0.007666,0.021136&t=m&z=16
 *          http://maps.google.com/?ll=48.859463,2.292626&spn=0.000965,0.002642&t=m&z=19&layer=c&cbll=48.859524,2.292532&panoid=YJ0lq28OOy3VT2IqIuVY0g&cbp=12,151.58,,0,-15.56
 */
(function ($) {
	//Shortcut for fancyBox object
	var F = $.fancybox;

	//Add helper object
	F.helpers.media = {
		beforeLoad : function(opts, obj) {
			var href = obj.href || '',
				type = false,
				rez;

			if ((rez = href.match(/(youtube\.com|youtu\.be)\/(v\/|u\/|embed\/|watch\?v=)?([^#\&\?]*).*/i))) {
				href = '//www.youtube.com/embed/' + rez[3] + '?autoplay=1&autohide=1&fs=1&rel=0&enablejsapi=1';
				type = 'iframe';

			} else if ((rez = href.match(/vimeo.com\/(\d+)\/?(.*)/))) {
				href = '//player.vimeo.com/video/' + rez[1] + '?hd=1&autoplay=1&show_title=1&show_byline=1&show_portrait=0&color=&fullscreen=1';
				type = 'iframe';

			} else if ((rez = href.match(/metacafe.com\/watch\/(\d+)\/?(.*)/))) {
				href = '//www.metacafe.com/fplayer/' + rez[1] + '/.swf?playerVars=autoPlay=yes';
				type = 'swf';

			} else if ((rez = href.match(/dailymotion.com\/video\/(.*)\/?(.*)/))) {
				href = '//www.dailymotion.com/swf/video/' + rez[1] + '?additionalInfos=0&autoStart=1';
				type = 'swf';

			} else if ((rez = href.match(/twitvid\.com\/([a-zA-Z0-9_\-\?\=]+)/i))) {
				href = '//www.twitvid.com/embed.php?autoplay=0&guid=' + rez[1];
				type = 'iframe';

			} else if ((rez = href.match(/twitpic\.com\/(?!(?:place|photos|events)\/)([a-zA-Z0-9\?\=\-]+)/i))) {
				href = '//twitpic.com/show/full/' + rez[1];
				type = 'image';

			} else if ((rez = href.match(/(instagr\.am|instagram\.com)\/p\/([a-zA-Z0-9_\-]+)\/?/i))) {
				href = '//' + rez[1] + '/p/' + rez[2] + '/media/?size=l';
				type = 'image';

			} else if ((rez = href.match(/maps\.google\.com\/(\?ll=|maps\/?\?q=)(.*)/i))) {
				href = '//maps.google.com/' + rez[1] + '' + rez[2] + '&output=' + (rez[2].indexOf('layer=c') ? 'svembed' : 'embed');
				type = 'iframe';
			}

			if (type) {
				obj.href = href;
				obj.type = type;
			}
		}
	}

}(jQuery));;
 /*!
 * Thumbnail helper for fancyBox
 * version: 1.0.4
 * @requires fancyBox v2.0 or later
 *
 * Usage:
 *     $(".fancybox").fancybox({
 *         thumbs: {
 *             width  : 50,
 *             height : 50
 *         }
 *     });
 *
 * Options:
 *     width - thumbnail width
 *     height - thumbnail height
 *     source - function to obtain the URL of the thumbnail image
 *     position - 'top' or 'bottom'
 *
 */
(function ($) {
	//Shortcut for fancyBox object
	var F = $.fancybox;

	//Add helper object
	F.helpers.thumbs = {
		wrap: null,
		list: null,
		width: 0,

		//Default function to obtain the URL of the thumbnail image
		source: function (el) {
			var img;

			if ($.type(el) === 'string') {
				return el;
			}

			img = $(el).find('img');

			return img.length ? img.attr('src') : el.href;
		},

		init: function (opts) {
			var that = this,
				list,
				thumbWidth = opts.width || 50,
				thumbHeight = opts.height || 50,
				thumbSource = opts.source || this.source;

			//Build list structure
			list = '';

			for (var n = 0; n < F.group.length; n++) {
				list += '<li><a style="width:' + thumbWidth + 'px;height:' + thumbHeight + 'px;" href="javascript:jQuery.fancybox.jumpto(' + n + ');"></a></li>';
			}

			this.wrap = $('<div id="fancybox-thumbs"></div>').addClass(opts.position || 'bottom').appendTo('body');
			this.list = $('<ul>' + list + '</ul>').appendTo(this.wrap);

			//Load each thumbnail
			$.each(F.group, function (i) {
				$("<img />").load(function () {
					var width = this.width,
						height = this.height,
						widthRatio, heightRatio, parent;

					if (!that.list || !width || !height) {
						return;
					}

					//Calculate thumbnail width/height and center it
					widthRatio = width / thumbWidth;
					heightRatio = height / thumbHeight;
					parent = that.list.children().eq(i).find('a');

					if (widthRatio >= 1 && heightRatio >= 1) {
						if (widthRatio > heightRatio) {
							width = Math.floor(width / heightRatio);
							height = thumbHeight;

						} else {
							width = thumbWidth;
							height = Math.floor(height / widthRatio);
						}
					}

					$(this).css({
						width: width,
						height: height,
						top: Math.floor(thumbHeight / 2 - height / 2),
						left: Math.floor(thumbWidth / 2 - width / 2)
					});

					parent.width(thumbWidth).height(thumbHeight);

					$(this).hide().appendTo(parent).fadeIn(300);

				}).attr('src', thumbSource( F.group[ i ] ));
			});

			//Set initial width
			this.width = this.list.children().eq(0).outerWidth(true);

			this.list.width(this.width * (F.group.length + 1)).css('left', Math.floor($(window).width() * 0.5 - (F.current.index * this.width + this.width * 0.5)));
		},

		//Center list
		update: function (opts) {
			if (this.list) {
				this.list.stop(true).animate({
					'left': Math.floor($(window).width() * 0.5 - (F.current.index * this.width + this.width * 0.5))
				}, 150);
			}
		},

		beforeLoad: function (opts) {
			//Remove self if gallery do not have at least two items
			if (F.group.length < 2) {
				F.coming.helpers.thumbs = false;

				return;
			}

			//Increase bottom margin to give space for thumbs
			F.coming.margin[ opts.position === 'top' ? 0 : 2 ] = opts.height + 30;
		},

		afterShow: function (opts) {
			//Check if exists and create or update list
			if (this.list) {
				this.update(opts);

			} else {
				this.init(opts);
			}

			//Set active element
			this.list.children().removeClass('active').eq(F.current.index).addClass('active');
		},

		onUpdate: function () {
			this.update();
		},

		beforeClose: function () {
			if (this.wrap) {
				this.wrap.remove();
			}

			this.wrap = null;
			this.list = null;
			this.width = 0;
		}
	}

}(jQuery));;
/**
 * Cookie plugin
 *
 * Copyright (c) 2006 Klaus Hartl (stilbuero.de)
 * Dual licensed under the MIT and GPL licenses:
 * http://www.opensource.org/licenses/mit-license.php
 * http://www.gnu.org/licenses/gpl.html
 *
 */

/**
 * Create a cookie with the given name and value and other optional parameters.
 *
 * @example $.cookie('the_cookie', 'the_value');
 * @desc Set the value of a cookie.
 * @example $.cookie('the_cookie', 'the_value', { expires: 7, path: '/', domain: 'jquery.com', secure: true });
 * @desc Create a cookie with all available options.
 * @example $.cookie('the_cookie', 'the_value');
 * @desc Create a session cookie.
 * @example $.cookie('the_cookie', null);
 * @desc Delete a cookie by passing null as value. Keep in mind that you have to use the same path and domain
 *       used when the cookie was set.
 *
 * @param String name The name of the cookie.
 * @param String value The value of the cookie.
 * @param Object options An object literal containing key/value pairs to provide optional cookie attributes.
 * @option Number|Date expires Either an integer specifying the expiration date from now on in days or a Date object.
 *                             If a negative value is specified (e.g. a date in the past), the cookie will be deleted.
 *                             If set to null or omitted, the cookie will be a session cookie and will not be retained
 *                             when the the browser exits.
 * @option String path The value of the path atribute of the cookie (default: path of page that created the cookie).
 * @option String domain The value of the domain attribute of the cookie (default: domain of page that created the cookie).
 * @option Boolean secure If true, the secure attribute of the cookie will be set and the cookie transmission will
 *                        require a secure protocol (like HTTPS).
 * @type undefined
 *
 * @name $.cookie
 * @cat Plugins/Cookie
 * @author Klaus Hartl/klaus.hartl@stilbuero.de
 */

/**
 * Get the value of a cookie with the given name.
 *
 * @example $.cookie('the_cookie');
 * @desc Get the value of a cookie.
 *
 * @param String name The name of the cookie.
 * @return The value of the cookie.
 * @type String
 *
 * @name $.cookie
 * @cat Plugins/Cookie
 * @author Klaus Hartl/klaus.hartl@stilbuero.de
 */
jQuery.cookie = function(name, value, options) {
    if (typeof value != 'undefined') { // name and value given, set cookie
        options = options || {};
        if (value === null) {
            value = '';
            options.expires = -1;
        }
        var expires = '';
        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
            var date;
            if (typeof options.expires == 'number') {
                date = new Date();
                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
            } else {
                date = options.expires;
            }
            expires = '; expires=' + date.toUTCString(); // use expires attribute, max-age is not supported by IE
        }
        // CAUTION: Needed to parenthesize options.path and options.domain
        // in the following expressions, otherwise they evaluate to undefined
        // in the packed version for some reason...
        var path = options.path ? '; path=' + (options.path) : '';
        var domain = options.domain ? '; domain=' + (options.domain) : '';
        var secure = options.secure ? '; secure' : '';
        document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
    } else { // only name given, get cookie
        var cookieValue = null;
        if (document.cookie && document.cookie != '') {
            var cookies = document.cookie.split(';');
            for (var i = 0; i < cookies.length; i++) {
                var cookie = jQuery.trim(cookies[i]);
                // Does this cookie string begin with the name we want?
                if (cookie.substring(0, name.length + 1) == (name + '=')) {
                    cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                    break;
                }
            }
        }
        return cookieValue;
    }
};
;
/*
 * jQuery Easing v1.3 - http://gsgd.co.uk/sandbox/jquery/easing/
 *
 * Uses the built in easing capabilities added In jQuery 1.1
 * to offer multiple easing options
 *
 * TERMS OF USE - jQuery Easing
 * 
 * Open source under the BSD License. 
 * 
 * Copyright © 2008 George McGinley Smith
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, 
 * are permitted provided that the following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice, this list of 
 * conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list 
 * of conditions and the following disclaimer in the documentation and/or other materials 
 * provided with the distribution.
 * 
 * Neither the name of the author nor the names of contributors may be used to endorse 
 * or promote products derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY 
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 *  COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 *  EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE
 *  GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED 
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED 
 * OF THE POSSIBILITY OF SUCH DAMAGE. 
 *
*/

// t: current time, b: begInnIng value, c: change In value, d: duration
jQuery.easing['jswing'] = jQuery.easing['swing'];

jQuery.extend( jQuery.easing,
{
	def: 'easeOutQuad',
	swing: function (x, t, b, c, d) {
		//alert(jQuery.easing.default);
		return jQuery.easing[jQuery.easing.def](x, t, b, c, d);
	},
	easeInQuad: function (x, t, b, c, d) {
		return c*(t/=d)*t + b;
	},
	easeOutQuad: function (x, t, b, c, d) {
		return -c *(t/=d)*(t-2) + b;
	},
	easeInOutQuad: function (x, t, b, c, d) {
		if ((t/=d/2) < 1) return c/2*t*t + b;
		return -c/2 * ((--t)*(t-2) - 1) + b;
	},
	easeInCubic: function (x, t, b, c, d) {
		return c*(t/=d)*t*t + b;
	},
	easeOutCubic: function (x, t, b, c, d) {
		return c*((t=t/d-1)*t*t + 1) + b;
	},
	easeInOutCubic: function (x, t, b, c, d) {
		if ((t/=d/2) < 1) return c/2*t*t*t + b;
		return c/2*((t-=2)*t*t + 2) + b;
	},
	easeInQuart: function (x, t, b, c, d) {
		return c*(t/=d)*t*t*t + b;
	},
	easeOutQuart: function (x, t, b, c, d) {
		return -c * ((t=t/d-1)*t*t*t - 1) + b;
	},
	easeInOutQuart: function (x, t, b, c, d) {
		if ((t/=d/2) < 1) return c/2*t*t*t*t + b;
		return -c/2 * ((t-=2)*t*t*t - 2) + b;
	},
	easeInQuint: function (x, t, b, c, d) {
		return c*(t/=d)*t*t*t*t + b;
	},
	easeOutQuint: function (x, t, b, c, d) {
		return c*((t=t/d-1)*t*t*t*t + 1) + b;
	},
	easeInOutQuint: function (x, t, b, c, d) {
		if ((t/=d/2) < 1) return c/2*t*t*t*t*t + b;
		return c/2*((t-=2)*t*t*t*t + 2) + b;
	},
	easeInSine: function (x, t, b, c, d) {
		return -c * Math.cos(t/d * (Math.PI/2)) + c + b;
	},
	easeOutSine: function (x, t, b, c, d) {
		return c * Math.sin(t/d * (Math.PI/2)) + b;
	},
	easeInOutSine: function (x, t, b, c, d) {
		return -c/2 * (Math.cos(Math.PI*t/d) - 1) + b;
	},
	easeInExpo: function (x, t, b, c, d) {
		return (t==0) ? b : c * Math.pow(2, 10 * (t/d - 1)) + b;
	},
	easeOutExpo: function (x, t, b, c, d) {
		return (t==d) ? b+c : c * (-Math.pow(2, -10 * t/d) + 1) + b;
	},
	easeInOutExpo: function (x, t, b, c, d) {
		if (t==0) return b;
		if (t==d) return b+c;
		if ((t/=d/2) < 1) return c/2 * Math.pow(2, 10 * (t - 1)) + b;
		return c/2 * (-Math.pow(2, -10 * --t) + 2) + b;
	},
	easeInCirc: function (x, t, b, c, d) {
		return -c * (Math.sqrt(1 - (t/=d)*t) - 1) + b;
	},
	easeOutCirc: function (x, t, b, c, d) {
		return c * Math.sqrt(1 - (t=t/d-1)*t) + b;
	},
	easeInOutCirc: function (x, t, b, c, d) {
		if ((t/=d/2) < 1) return -c/2 * (Math.sqrt(1 - t*t) - 1) + b;
		return c/2 * (Math.sqrt(1 - (t-=2)*t) + 1) + b;
	},
	easeInElastic: function (x, t, b, c, d) {
		var s=1.70158;var p=0;var a=c;
		if (t==0) return b;  if ((t/=d)==1) return b+c;  if (!p) p=d*.3;
		if (a < Math.abs(c)) { a=c; var s=p/4; }
		else var s = p/(2*Math.PI) * Math.asin (c/a);
		return -(a*Math.pow(2,10*(t-=1)) * Math.sin( (t*d-s)*(2*Math.PI)/p )) + b;
	},
	easeOutElastic: function (x, t, b, c, d) {
		var s=1.70158;var p=0;var a=c;
		if (t==0) return b;  if ((t/=d)==1) return b+c;  if (!p) p=d*.3;
		if (a < Math.abs(c)) { a=c; var s=p/4; }
		else var s = p/(2*Math.PI) * Math.asin (c/a);
		return a*Math.pow(2,-10*t) * Math.sin( (t*d-s)*(2*Math.PI)/p ) + c + b;
	},
	easeInOutElastic: function (x, t, b, c, d) {
		var s=1.70158;var p=0;var a=c;
		if (t==0) return b;  if ((t/=d/2)==2) return b+c;  if (!p) p=d*(.3*1.5);
		if (a < Math.abs(c)) { a=c; var s=p/4; }
		else var s = p/(2*Math.PI) * Math.asin (c/a);
		if (t < 1) return -.5*(a*Math.pow(2,10*(t-=1)) * Math.sin( (t*d-s)*(2*Math.PI)/p )) + b;
		return a*Math.pow(2,-10*(t-=1)) * Math.sin( (t*d-s)*(2*Math.PI)/p )*.5 + c + b;
	},
	easeInBack: function (x, t, b, c, d, s) {
		if (s == undefined) s = 1.70158;
		return c*(t/=d)*t*((s+1)*t - s) + b;
	},
	easeOutBack: function (x, t, b, c, d, s) {
		if (s == undefined) s = 1.70158;
		return c*((t=t/d-1)*t*((s+1)*t + s) + 1) + b;
	},
	easeInOutBack: function (x, t, b, c, d, s) {
		if (s == undefined) s = 1.70158; 
		if ((t/=d/2) < 1) return c/2*(t*t*(((s*=(1.525))+1)*t - s)) + b;
		return c/2*((t-=2)*t*(((s*=(1.525))+1)*t + s) + 2) + b;
	},
	easeInBounce: function (x, t, b, c, d) {
		return c - jQuery.easing.easeOutBounce (x, d-t, 0, c, d) + b;
	},
	easeOutBounce: function (x, t, b, c, d) {
		if ((t/=d) < (1/2.75)) {
			return c*(7.5625*t*t) + b;
		} else if (t < (2/2.75)) {
			return c*(7.5625*(t-=(1.5/2.75))*t + .75) + b;
		} else if (t < (2.5/2.75)) {
			return c*(7.5625*(t-=(2.25/2.75))*t + .9375) + b;
		} else {
			return c*(7.5625*(t-=(2.625/2.75))*t + .984375) + b;
		}
	},
	easeInOutBounce: function (x, t, b, c, d) {
		if (t < d/2) return jQuery.easing.easeInBounce (x, t*2, 0, c, d) * .5 + b;
		return jQuery.easing.easeOutBounce (x, t*2-d, 0, c, d) * .5 + c*.5 + b;
	}
});

/*
 *
 * TERMS OF USE - EASING EQUATIONS
 * 
 * Open source under the BSD License. 
 * 
 * Copyright © 2001 Robert Penner
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, 
 * are permitted provided that the following conditions are met:
 * 
 * Redistributions of source code must retain the above copyright notice, this list of 
 * conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list 
 * of conditions and the following disclaimer in the documentation and/or other materials 
 * provided with the distribution.
 * 
 * Neither the name of the author nor the names of contributors may be used to endorse 
 * or promote products derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY 
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE
 *  COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 *  EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE
 *  GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED 
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED 
 * OF THE POSSIBILITY OF SUCH DAMAGE. 
 *
 */;
/*
 * 	Easy Slider 1.7 - jQuery plugin
 *	written by Alen Grakalic	
 *	http://cssglobe.com/post/4004/easy-slider-15-the-easiest-jquery-plugin-for-sliding
 *
 *	Copyright (c) 2009 Alen Grakalic (http://cssglobe.com)
 *	Dual licensed under the MIT (MIT-LICENSE.txt)
 *	and GPL (GPL-LICENSE.txt) licenses.
 *
 *	Built for jQuery library
 *	http://jquery.com
 *
 */
 
/*
 *	markup example for $("#slider").easySlider();
 *	
 * 	<div id="slider">
 *		<ul>
 *			<li><img src="images/01.jpg" alt="" /></li>
 *			<li><img src="images/02.jpg" alt="" /></li>
 *			<li><img src="images/03.jpg" alt="" /></li>
 *			<li><img src="images/04.jpg" alt="" /></li>
 *			<li><img src="images/05.jpg" alt="" /></li>
 *		</ul>
 *	</div>
 *
 */

(function($) {

	$.fn.easySlider = function(options){
	  
		// default configuration properties
		var defaults = {			
			prevId: 		'prevBtn',
			prevText: 		'Previous',
			nextId: 		'nextBtn',	
			nextText: 		'Next',
			controlsShow:	true,
			controlsBefore:	'',
			controlsAfter:	'',	
			controlsFade:	true,
			firstId: 		'firstBtn',
			firstText: 		'First',
			firstShow:		false,
			lastId: 		'lastBtn',	
			lastText: 		'Last',
			lastShow:		false,				
			vertical:		false,
			speed: 			800,
			auto:			false,
			pause:			2000,
			continuous:		false, 
			numeric: 		false,
			numericId: 		'controls'
		}; 
		
		var options = $.extend(defaults, options);  
				
		this.each(function() {  
			var obj = $(this); 				
			var s = $("li", obj).length;
			var w = $("li", obj).width(); 
			var h = $("li", obj).height(); 
			var clickable = true;
			obj.width(w); 
			obj.height(h); 
			obj.css("overflow","hidden");
			var ts = s-1;
			var t = 0;
			$("ul", obj).css('width',s*w);			
			
			if(options.continuous){
				$("ul", obj).prepend($("ul li:last-child", obj).clone().css("margin-left","-"+ w +"px"));
				$("ul", obj).append($("ul li:nth-child(2)", obj).clone());
				$("ul", obj).css('width',(s+1)*w);
			};				
			
			if(!options.vertical) $("li", obj).css('float','left');
								
			if(options.controlsShow){
				var html = options.controlsBefore;				
				if(options.numeric){
					html += '<ol id="'+ options.numericId +'"></ol>';
				} else {
					if(options.firstShow) html += '<span id="'+ options.firstId +'"><a href=\"javascript:void(0);\">'+ options.firstText +'</a></span>';
					html += ' <span id="'+ options.prevId +'"><a href=\"javascript:void(0);\">'+ options.prevText +'</a></span>';
					html += ' <span id="'+ options.nextId +'"><a href=\"javascript:void(0);\">'+ options.nextText +'</a></span>';
					if(options.lastShow) html += ' <span id="'+ options.lastId +'"><a href=\"javascript:void(0);\">'+ options.lastText +'</a></span>';				
				};
				
				html += options.controlsAfter;						
				$(obj).after(html);										
			};
			
			if(options.numeric){									
				for(var i=0;i<s;i++){						
					$(document.createElement("li"))
						.attr('id',options.numericId + (i+1))
						.html('<a rel='+ i +' href=\"javascript:void(0);\">'+ (i+1) +'</a>')
						.appendTo($("#"+ options.numericId))
						.click(function(){							
							animate($("a",$(this)).attr('rel'),true);
						}); 												
				};							
			} else {
				$("a","#"+options.nextId).click(function(){		
					animate("next",true);
				});
				$("a","#"+options.prevId).click(function(){		
					animate("prev",true);				
				});	
				$("a","#"+options.firstId).click(function(){		
					animate("first",true);
				});				
				$("a","#"+options.lastId).click(function(){		
					animate("last",true);				
				});				
			};
			
			function setCurrent(i){
				i = parseInt(i)+1;
				$("li", "#" + options.numericId).removeClass("current");
				$("li#" + options.numericId + i).addClass("current");
			};
			
			function adjust(){
				if(t>ts) t=0;		
				if(t<0) t=ts;	
				if(!options.vertical) {
					$("ul",obj).css("margin-left",(t*w*-1));
				} else {
					$("ul",obj).css("margin-left",(t*h*-1));
				}
				clickable = true;
				if(options.numeric) setCurrent(t);
			};
			
			function animate(dir,clicked){
				if (clickable){
					clickable = false;
					var ot = t;				
					switch(dir){
						case "next":
							t = (ot>=ts) ? (options.continuous ? t+1 : ts) : t+1;						
							break; 
						case "prev":
							t = (t<=0) ? (options.continuous ? t-1 : 0) : t-1;
							break; 
						case "first":
							t = 0;
							break; 
						case "last":
							t = ts;
							break; 
						default:
							t = dir;
							break; 
					};	
					var diff = Math.abs(ot-t);
					var speed = diff*options.speed;						
					if(!options.vertical) {
						p = (t*w*-1);
						$("ul",obj).animate(
							{ marginLeft: p }, 
							{ queue:false, duration:speed, complete:adjust }
						);				
					} else {
						p = (t*h*-1);
						$("ul",obj).animate(
							{ marginTop: p }, 
							{ queue:false, duration:speed, complete:adjust }
						);					
					};
					
					if(!options.continuous && options.controlsFade){					
						if(t==ts){
							$("a","#"+options.nextId).hide();
							$("a","#"+options.lastId).hide();
						} else {
							$("a","#"+options.nextId).show();
							$("a","#"+options.lastId).show();					
						};
						if(t==0){
							$("a","#"+options.prevId).hide();
							$("a","#"+options.firstId).hide();
						} else {
							$("a","#"+options.prevId).show();
							$("a","#"+options.firstId).show();
						};					
					};				
					
					if(clicked) clearTimeout(timeout);
					if(options.auto && dir=="next" && !clicked){;
						timeout = setTimeout(function(){
							animate("next",false);
						},diff*options.speed+options.pause);
					};
			
				};
				
			};
			// init
			var timeout;
			if(options.auto){;
				timeout = setTimeout(function(){
					animate("next",false);
				},options.pause);
			};		
			
			if(options.numeric) setCurrent(0);
		
			if(!options.continuous && options.controlsFade){					
				$("a","#"+options.prevId).hide();
				$("a","#"+options.firstId).hide();				
			};				
			
		});
	  
	};

})(jQuery);
;
/*
	Mosaic - Sliding Boxes and Captions jQuery Plugin
	Version 1.0.1
	www.buildinternet.com/project/mosaic
	
	By Sam Dunn / One Mighty Roar (www.onemightyroar.com)
	Released under MIT License / GPL License
*/

(function($){

    if(!$.omr){
        $.omr = new Object();
    };
    
    $.omr.mosaic = function(el, options){
    
        var base = this;
        
        // Access to jQuery and DOM versions of element
        base.$el = $(el);
        base.el = el;
        
        // Add a reverse reference to the DOM object
        base.$el.data("omr.mosaic", base);
        
        base.init = function(){
            base.options = $.extend({},$.omr.mosaic.defaultOptions, options);
            
            base.load_box();
        };
        
        // Preload Images
        base.load_box = function(){
        	// Hide until window loaded, then fade in
			if (base.options.preload){
				$(base.options.backdrop, base.el).hide();
				$(base.options.overlay, base.el).hide();
			
				$(window).load(function(){
					// IE transparency fade fix
					if(base.options.options.animation == 'fade' && $(base.options.overlay, base.el).css('opacity') == 0 ) $(base.options.overlay, base.el).css('filter', 'alpha(opacity=0)');
					
					$(base.options.overlay, base.el).fadeIn(200, function(){
						$(base.options.backdrop, base.el).fadeIn(200);
					});
					
					base.allow_hover();
				});
			}else{
				$(base.options.backdrop, base.el).show();
				$(base.options.overlay , base.el).show();
				base.allow_hover();
			}
        };
        
        // Initialize hover animations
        base.allow_hover = function(){
        	// Select animation
			switch(base.options.animation){
			
				// Handle fade animations
				case 'fade':
					$(base.el).hover(function () {
			        	$(base.options.overlay, base.el).stop().fadeTo(base.options.speed, base.options.opacity);
			        },function () {
			        	$(base.options.overlay, base.el).stop().fadeTo(base.options.speed, 0);
			      	});
			      	
			    	break;
			    
			    // Handle slide animations
	      		case 'slide':
	      			// Grab default overlay x,y position
					startX = $(base.options.overlay, base.el).css(base.options.anchor_x) != 'auto' ? $(base.options.overlay, base.el).css(base.options.anchor_x) : '0px';
					startY = $(base.options.overlay, base.el).css(base.options.anchor_y) != 'auto' ? $(base.options.overlay, base.el).css(base.options.anchor_y) : '0px';;
	      			
			      	var hoverState = {};
			      	hoverState[base.options.anchor_x] = base.options.hover_x;
			      	hoverState[base.options.anchor_y] = base.options.hover_y;
			      	
			      	var endState = {};
			      	endState[base.options.anchor_x] = startX;
			      	endState[base.options.anchor_y] = startY;
			      	
					$(base.el).hover(function () {
			        	$(base.options.overlay, base.el).stop().animate(hoverState, base.options.speed);
			        },function () {
			        	$(base.options.overlay, base.el).stop().animate(endState, base.options.speed);
			      	});
			      	
			      	break;
			};
        };
        
        // Make it go!
        base.init();
    };
    
    $.omr.mosaic.defaultOptions = {
        animation	: 'fade',
        speed		: 150,
        opacity		: 1,
        preload		: 0,
        anchor_x	: 'left',
        anchor_y	: 'bottom',
        hover_x		: '0px',
        hover_y		: '0px',
        overlay  	: '.mosaic-overlay',	//Mosaic overlay
		backdrop 	: '.mosaic-backdrop'	//Mosaic backdrop
    };
    
    $.fn.mosaic = function(options){
        return this.each(function(){
            (new $.omr.mosaic(this, options));
        });
    };
    
})(jQuery);
;
/*
 * jQuery Menu v1.0 
 * Author: Augusto Leao
 * Mulesoft 2012 
 *
 *
 */

jQuery.fn.menu = function (loc) {

    var currentPage = jQuery(window.location).attr('href'); 
    var parser = document.createElement('a');
    var home = "http://www.mulesoft.org";
    
    parser.href = currentPage;
    parser.protocol; // => "http:"
    parser.hostname; // => "example.com"
    parser.port;     // => "3000"
    parser.pathname; // => "/pathname/"
    parser.search;   // => "?search=test"
    parser.hash;     // => "#hash"
    parser.host;     // => "example.com:3000"
    
    if(loc){
       jQuery("a",this).each(function(index){
           if(jQuery(this).parent().parent().parent().attr("id")=="nav-menu" && jQuery(this).text().toLowerCase()==loc.toLowerCase()){
              jQuery(this).parent().addClass("currentpage"); 
              return;
            }  
       });
    }else{
       jQuery("a",this).each(function(index){
           if(jQuery(this).parent().parent().parent().attr("id")=="nav-menu")   
              var element = jQuery(this).parent();   
              jQuery("a",jQuery(this).parent()).each(function(index2){
                var href = jQuery(this).attr("href"); 
                if(!href.match(/http\:/)){
                    href = home+href;        
                } 
                if(href==currentPage){
                   jQuery(element).addClass("currentpage"); 
                   return; 
                }  
              }); 
       });  
    }
       
}; 
 ;
/*
* vertical news ticker
* Tadas Juozapaitis ( kasp3rito [eta] gmail (dot) com )
* http://www.jugbit.com/jquery-vticker-vertical-news-ticker/
*/
(function($){
$.fn.vTicker = function(options) {
	var defaults = {
		speed: 700,
		pause: 4000,
		showItems: 3,
		animation: '',
		mousePause: true,
		isPaused: false,
		direction: 'up',
		height: 0
	};

	var options = $.extend(defaults, options);

	moveUp = function(obj2, height, options){
		if(options.isPaused)
			return;
		
		var obj = obj2.children('ul');
		
    	var clone = obj.children('li:first').clone(true);
		
		if(options.height > 0)
		{
			height = obj.children('li:first').height();
		}		
		
    	obj.animate({top: '-=' + height + 'px'}, options.speed, function() {
        	$(this).children('li:first').remove();
        	$(this).css('top', '0px');
        });
		
		if(options.animation == 'fade')
		{
			obj.children('li:first').fadeOut(options.speed);
			if(options.height == 0)
			{
			obj.children('li:eq(' + options.showItems + ')').hide().fadeIn(options.speed).show();
			}
		}

    	clone.appendTo(obj);
	};
	
	moveDown = function(obj2, height, options){
		if(options.isPaused)
			return;
		
		var obj = obj2.children('ul');
		
    	var clone = obj.children('li:last').clone(true);
		
		if(options.height > 0)
		{
			height = obj.children('li:first').height();
		}
		
		obj.css('top', '-' + height + 'px')
			.prepend(clone);
			
    	obj.animate({top: 0}, options.speed, function() {
        	$(this).children('li:last').remove();
        });
		
		if(options.animation == 'fade')
		{
			if(options.height == 0)
			{
				obj.children('li:eq(' + options.showItems + ')').fadeOut(options.speed);
			}
			obj.children('li:first').hide().fadeIn(options.speed).show();
		}
	};
	
	return this.each(function() {
		var obj = $(this);
		var maxHeight = 0;

		obj.css({overflow: 'hidden', position: 'relative'})
			.children('ul').css({position: 'absolute', margin: 0, padding: 0})
			.children('li').css({margin: 0, padding: 0});

		if(options.height == 0)
		{
			obj.children('ul').children('li').each(function(){
				if($(this).height() > maxHeight)
				{
					maxHeight = $(this).height();
				}
			});

			obj.children('ul').children('li').each(function(){
				$(this).height(maxHeight);
			});

			obj.height(maxHeight * options.showItems);
		}
		else
		{
			obj.height(options.height);
		}
		
    	var interval = setInterval(function(){ 
			if(options.direction == 'up')
			{ 
				moveUp(obj, maxHeight, options); 
			}
			else
			{ 
				moveDown(obj, maxHeight, options); 
			} 
		}, options.pause);
		
		if(options.mousePause)
		{
			obj.bind("mouseenter",function(){
				options.isPaused = true;
			}).bind("mouseleave",function(){
				options.isPaused = false;
			});
		}
	});
};
})(jQuery);;
/*!
 * jquery.qtip. The jQuery tooltip plugin
 *
 * Copyright (c) 2009 Craig Thompson
 * http://craigsworks.com
 *
 * Licensed under MIT
 * http://www.opensource.org/licenses/mit-license.php
 *
 * Launch  : February 2009
 * Version : 1.0.0-rc3
 * Released: Tuesday 12th May, 2009 - 00:00
 * Debug: jquery.qtip.debug.js
 */
(function($)
{
   // Implementation
   jQuery.fn.qtip = function(options, blanket)
   {
      var i, id, interfaces, opts, obj, command, config, api;

      // Return API / Interfaces if requested
      if(typeof options == 'string')
      {
         // Make sure API data exists if requested
         if(typeof jQuery(this).data('qtip') !== 'object')
            jQuery.fn.qtip.log.error.call(self, 1, jQuery.fn.qtip.constants.NO_TOOLTIP_PRESENT, false);

         // Return requested object
         if(options == 'api')
            return jQuery(this).data('qtip').interfaces[ jQuery(this).data('qtip').current ];
         else if(options == 'interfaces')
            return jQuery(this).data('qtip').interfaces;
      }

      // Validate provided options
      else
      {
         // Set null options object if no options are provided
         if(!options) options = {};

         // Sanitize option data
         if(typeof options.content !== 'object' || (options.content.jquery && options.content.length > 0)) options.content = { text: options.content };
         if(typeof options.content.title !== 'object') options.content.title = { text: options.content.title };
         if(typeof options.position !== 'object') options.position = { corner: options.position };
         if(typeof options.position.corner !== 'object') options.position.corner = { target: options.position.corner, tooltip: options.position.corner };
         if(typeof options.show !== 'object') options.show = { when: options.show };
         if(typeof options.show.when !== 'object') options.show.when = { event: options.show.when };
         if(typeof options.show.effect !== 'object') options.show.effect = { type: options.show.effect };
         if(typeof options.hide !== 'object') options.hide = { when: options.hide };
         if(typeof options.hide.when !== 'object') options.hide.when = { event: options.hide.when };
         if(typeof options.hide.effect !== 'object') options.hide.effect = { type: options.hide.effect };
         if(typeof options.style !== 'object') options.style = { name: options.style };
         options.style = sanitizeStyle(options.style);

         // Build main options object
         opts = jQuery.extend(true, {}, jQuery.fn.qtip.defaults, options);

         // Inherit all style properties into one syle object and include original options
         opts.style = buildStyle.call({ options: opts }, opts.style);
         opts.user = jQuery.extend(true, {}, options);
      };

      // Iterate each matched element
      return jQuery(this).each(function() // Return original elements as per jQuery guidelines
      {
         // Check for API commands
         if(typeof options == 'string')
         {
            command = options.toLowerCase();
            interfaces = jQuery(this).qtip('interfaces');

            // Make sure API data existsjQuery('.qtip').qtip('destroy')
            if(typeof interfaces == 'object')
            {
               // Check if API call is a BLANKET DESTROY command
               if(blanket === true && command == 'destroy')
                  while(interfaces.length > 0) interfaces[interfaces.length-1].destroy();

               // API call is not a BLANKET DESTROY command
               else
               {
                  // Check if supplied command effects this tooltip only (NOT BLANKET)
                  if(blanket !== true) interfaces = [ jQuery(this).qtip('api') ];

                  // Execute command on chosen qTips
                  for(i = 0; i < interfaces.length; i++)
                  {
                     // Destroy command doesn't require tooltip to be rendered
                     if(command == 'destroy') interfaces[i].destroy();

                     // Only call API if tooltip is rendered and it wasn't a destroy call
                     else if(interfaces[i].status.rendered === true)
                     {
                        if(command == 'show') interfaces[i].show();
                        else if(command == 'hide') interfaces[i].hide();
                        else if(command == 'focus') interfaces[i].focus();
                        else if(command == 'disable') interfaces[i].disable(true);
                        else if(command == 'enable') interfaces[i].disable(false);
                     };
                  };
               };
            };
         }

         // No API commands, continue with qTip creation
         else
         {
            // Create unique configuration object
            config = jQuery.extend(true, {}, opts);
            config.hide.effect.length = opts.hide.effect.length;
            config.show.effect.length = opts.show.effect.length;

            // Sanitize target options
            if(config.position.container === false) config.position.container = jQuery(document.body);
            if(config.position.target === false) config.position.target = jQuery(this);
            if(config.show.when.target === false) config.show.when.target = jQuery(this);
            if(config.hide.when.target === false) config.hide.when.target = jQuery(this);

            // Determine tooltip ID (Reuse array slots if possible)
            id = jQuery.fn.qtip.interfaces.length;
            for(i = 0; i < id; i++)
            {
               if(typeof jQuery.fn.qtip.interfaces[i] == 'undefined'){ id = i; break; };
            };

            // Instantiate the tooltip
            obj = new qTip(jQuery(this), config, id);

            // Add API references
            jQuery.fn.qtip.interfaces[id] = obj;

            // Check if element already has qTip data assigned
            if(typeof jQuery(this).data('qtip') == 'object')
            {
               // Set new current interface id
               if(typeof jQuery(this).attr('qtip') === 'undefined')
                  jQuery(this).data('qtip').current = jQuery(this).data('qtip').interfaces.length;

               // Push new API interface onto interfaces array
               jQuery(this).data('qtip').interfaces.push(obj);
            }

            // No qTip data is present, create now
            else jQuery(this).data('qtip', { current: 0, interfaces: [obj] });

            // If prerendering is disabled, create tooltip on showEvent
            if(config.content.prerender === false && config.show.when.event !== false && config.show.ready !== true)
            {
               config.show.when.target.bind(config.show.when.event+'.qtip-'+id+'-create', { qtip: id }, function(event)
               {
                  // Retrieve API interface via passed qTip Id
                  api = jQuery.fn.qtip.interfaces[ event.data.qtip ];

                  // Unbind show event and cache mouse coords
                  api.options.show.when.target.unbind(api.options.show.when.event+'.qtip-'+event.data.qtip+'-create');
                  api.cache.mouse = { x: event.pageX, y: event.pageY };

                  // Render tooltip and start the event sequence
                  construct.call( api );
                  api.options.show.when.target.trigger(api.options.show.when.event);
               });
            }

            // Prerendering is enabled, create tooltip now
            else
            {
               // Set mouse position cache to top left of the element
               obj.cache.mouse = {
                  x: config.show.when.target.offset().left,
                  y: config.show.when.target.offset().top
               };

               // Construct the tooltip
               construct.call(obj);
            }
         };
      });
   };

   // Instantiator
   function qTip(target, options, id)
   {
      // Declare this reference
      var self = this;

      // Setup class attributes
      self.id = id;
      self.options = options;
      self.status = {
         animated: false,
         rendered: false,
         disabled: false,
         focused: false
      };
      self.elements = {
         target: target.addClass(self.options.style.classes.target),
         tooltip: null,
         wrapper: null,
         content: null,
         contentWrapper: null,
         title: null,
         button: null,
         tip: null,
         bgiframe: null
      };
      self.cache = {
         mouse: {},
         position: {},
         toggle: 0
      };
      self.timers = {};

      // Define exposed API methods
      jQuery.extend(self, self.options.api,
      {
         show: function(event)
         {
            var returned, solo;

            // Make sure tooltip is rendered and if not, return
            if(!self.status.rendered)
               return jQuery.fn.qtip.log.error.call(self, 2, jQuery.fn.qtip.constants.TOOLTIP_NOT_RENDERED, 'show');

            // Only continue if element is visible
            if(self.elements.tooltip.css('display') !== 'none') return self;

            // Clear animation queue
            self.elements.tooltip.stop(true, false);

            // Call API method and if return value is false, halt
            returned = self.beforeShow.call(self, event);
            if(returned === false) return self;

            // Define afterShow callback method
            function afterShow()
            {
               // Call API method and focus if it isn't static
               if(self.options.position.type !== 'static') self.focus();
               self.onShow.call(self, event);

               // Prevent antialias from disappearing in IE7 by removing filter attribute
               if(jQuery.browser.msie) self.elements.tooltip.get(0).style.removeAttribute('filter');
            };

            // Maintain toggle functionality if enabled
            self.cache.toggle = 1;

            // Update tooltip position if it isn't static
            if(self.options.position.type !== 'static')
               self.updatePosition(event, (self.options.show.effect.length > 0));

            // Hide other tooltips if tooltip is solo
            if(typeof self.options.show.solo == 'object') solo = jQuery(self.options.show.solo);
            else if(self.options.show.solo === true) solo = jQuery('div.qtip').not(self.elements.tooltip);
            if(solo) solo.each(function(){ if(jQuery(this).qtip('api').status.rendered === true) jQuery(this).qtip('api').hide(); });

            // Show tooltip
            if(typeof self.options.show.effect.type == 'function')
            {
               self.options.show.effect.type.call(self.elements.tooltip, self.options.show.effect.length);
               self.elements.tooltip.queue(function(){ afterShow(); jQuery(this).dequeue(); });
            }
            else
            {
               switch(self.options.show.effect.type.toLowerCase())
               {
                  case 'fade':
                     self.elements.tooltip.fadeIn(self.options.show.effect.length, afterShow);
                     break;
                  case 'slide':
                     self.elements.tooltip.slideDown(self.options.show.effect.length, function()
                     {
                        afterShow();
                        if(self.options.position.type !== 'static') self.updatePosition(event, true);
                     });
                     break;
                  case 'grow':
                     self.elements.tooltip.show(self.options.show.effect.length, afterShow);
                     break;
                  default:
                     self.elements.tooltip.show(null, afterShow);
                     break;
               };

               // Add active class to tooltip
               self.elements.tooltip.addClass(self.options.style.classes.active);
            };

            // Log event and return
            return jQuery.fn.qtip.log.error.call(self, 1, jQuery.fn.qtip.constants.EVENT_SHOWN, 'show');
         },

         hide: function(event)
         {
            var returned;

            // Make sure tooltip is rendered and if not, return
            if(!self.status.rendered)
               return jQuery.fn.qtip.log.error.call(self, 2, jQuery.fn.qtip.constants.TOOLTIP_NOT_RENDERED, 'hide');

            // Only continue if element is visible
            else if(self.elements.tooltip.css('display') === 'none') return self;

            // Stop show timer and animation queue
            clearTimeout(self.timers.show);
            self.elements.tooltip.stop(true, false);

            // Call API method and if return value is false, halt
            returned = self.beforeHide.call(self, event);
            if(returned === false) return self;

            // Define afterHide callback method
            function afterHide(){ self.onHide.call(self, event); };

            // Maintain toggle functionality if enabled
            self.cache.toggle = 0;

            // Hide tooltip
            if(typeof self.options.hide.effect.type == 'function')
            {
               self.options.hide.effect.type.call(self.elements.tooltip, self.options.hide.effect.length);
               self.elements.tooltip.queue(function(){ afterHide(); jQuery(this).dequeue(); });
            }
            else
            {
               switch(self.options.hide.effect.type.toLowerCase())
               {
                  case 'fade':
                     self.elements.tooltip.fadeOut(self.options.hide.effect.length, afterHide);
                     break;
                  case 'slide':
                     self.elements.tooltip.slideUp(self.options.hide.effect.length, afterHide);
                     break;
                  case 'grow':
                     self.elements.tooltip.hide(self.options.hide.effect.length, afterHide);
                     break;
                  default:
                     self.elements.tooltip.hide(null, afterHide);
                     break;
               };

               // Remove active class to tooltip
               self.elements.tooltip.removeClass(self.options.style.classes.active);
            };

            // Log event and return
            return jQuery.fn.qtip.log.error.call(self, 1, jQuery.fn.qtip.constants.EVENT_HIDDEN, 'hide');
         },

         updatePosition: function(event, animate)
         {
            var i, target, tooltip, coords, mapName, imagePos, newPosition, ieAdjust, ie6Adjust, borderAdjust, mouseAdjust, offset, curPosition, returned

            // Make sure tooltip is rendered and if not, return
            if(!self.status.rendered)
               return jQuery.fn.qtip.log.error.call(self, 2, jQuery.fn.qtip.constants.TOOLTIP_NOT_RENDERED, 'updatePosition');

            // If tooltip is static, return
            else if(self.options.position.type == 'static')
               return jQuery.fn.qtip.log.error.call(self, 1, jQuery.fn.qtip.constants.CANNOT_POSITION_STATIC, 'updatePosition');

            // Define property objects
            target = {
               position: { left: 0, top: 0 },
               dimensions: { height: 0, width: 0 },
               corner: self.options.position.corner.target
            };
            tooltip = {
               position: self.getPosition(),
               dimensions: self.getDimensions(),
               corner: self.options.position.corner.tooltip
            };

            // Target is an HTML element
            if(self.options.position.target !== 'mouse')
            {
               // If the HTML element is AREA, calculate position manually
               if(self.options.position.target.get(0).nodeName.toLowerCase() == 'area')
               {
                  // Retrieve coordinates from coords attribute and parse into integers
                  coords = self.options.position.target.attr('coords').split(',');
                  for(i = 0; i < coords.length; i++) coords[i] = parseInt(coords[i]);

                  // Setup target position object
                  mapName = self.options.position.target.parent('map').attr('name');
                  imagePos = jQuery('img[usemap="#'+mapName+'"]:first').offset();
                  target.position = {
                     left: Math.floor(imagePos.left + coords[0]),
                     top: Math.floor(imagePos.top + coords[1])
                  };

                  // Determine width and height of the area
                  switch(self.options.position.target.attr('shape').toLowerCase())
                  {
                     case 'rect':
                        target.dimensions = {
                           width: Math.ceil(Math.abs(coords[2] - coords[0])),
                           height: Math.ceil(Math.abs(coords[3] - coords[1]))
                        };
                        break;

                     case 'circle':
                        target.dimensions = {
                           width: coords[2] + 1,
                           height: coords[2] + 1
                        };
                        break;

                     case 'poly':
                        target.dimensions = {
                           width: coords[0],
                           height: coords[1]
                        };

                        for(i = 0; i < coords.length; i++)
                        {
                           if(i % 2 == 0)
                           {
                              if(coords[i] > target.dimensions.width)
                                 target.dimensions.width = coords[i];
                              if(coords[i] < coords[0])
                                 target.position.left = Math.floor(imagePos.left + coords[i]);
                           }
                           else
                           {
                              if(coords[i] > target.dimensions.height)
                                 target.dimensions.height = coords[i];
                              if(coords[i] < coords[1])
                                 target.position.top = Math.floor(imagePos.top + coords[i]);
                           };
                        };

                        target.dimensions.width = target.dimensions.width - (target.position.left - imagePos.left);
                        target.dimensions.height = target.dimensions.height - (target.position.top - imagePos.top);
                        break;

                     default:
                        return jQuery.fn.qtip.log.error.call(self, 4, jQuery.fn.qtip.constants.INVALID_AREA_SHAPE, 'updatePosition');
                        break;
                  };

                  // Adjust position by 2 pixels (Positioning bug?)
                  target.dimensions.width -= 2; target.dimensions.height -= 2;
               }

               // Target is the document
               else if(self.options.position.target.add(document.body).length === 1)
               {
                  target.position = { left: jQuery(document).scrollLeft(), top: jQuery(document).scrollTop() };
                  target.dimensions = { height: jQuery(window).height(), width: jQuery(window).width() };
               }

               // Target is a regular HTML element, find position normally
               else
               {
                  // Check if the target is another tooltip. If its animated, retrieve position from newPosition data
                  if(typeof self.options.position.target.attr('qtip') !== 'undefined')
                     target.position = self.options.position.target.qtip('api').cache.position;
                  else
                     target.position = self.options.position.target.offset();

                  // Setup dimensions objects
                  target.dimensions = {
                     height: self.options.position.target.outerHeight(),
                     width: self.options.position.target.outerWidth()
                  };
               };

               // Calculate correct target corner position
               newPosition = jQuery.extend({}, target.position);
               if(target.corner.search(/right/i) !== -1)
                  newPosition.left += target.dimensions.width;

               if(target.corner.search(/bottom/i) !== -1)
                  newPosition.top += target.dimensions.height;

               if(target.corner.search(/((top|bottom)Middle)|center/) !== -1)
                  newPosition.left += (target.dimensions.width / 2);

               if(target.corner.search(/((left|right)Middle)|center/) !== -1)
                  newPosition.top += (target.dimensions.height / 2);
            }

            // Mouse is the target, set position to current mouse coordinates
            else
            {
               // Setup target position and dimensions objects
               target.position = newPosition = { left: self.cache.mouse.x, top: self.cache.mouse.y };
               target.dimensions = { height: 1, width: 1 };
            };

            // Calculate correct target corner position
            if(tooltip.corner.search(/right/i) !== -1)
               newPosition.left -= tooltip.dimensions.width;

            if(tooltip.corner.search(/bottom/i) !== -1)
               newPosition.top -= tooltip.dimensions.height;

            if(tooltip.corner.search(/((top|bottom)Middle)|center/) !== -1)
               newPosition.left -= (tooltip.dimensions.width / 2);

            if(tooltip.corner.search(/((left|right)Middle)|center/) !== -1)
               newPosition.top -= (tooltip.dimensions.height / 2);

            // Setup IE adjustment variables (Pixel gap bugs)
            ieAdjust = (jQuery.browser.msie) ? 1 : 0; // And this is why I hate IE...
            ie6Adjust = (jQuery.browser.msie && parseInt(jQuery.browser.version.charAt(0)) === 6) ? 1 : 0; // ...and even more so IE6!

            // Adjust for border radius
            if(self.options.style.border.radius > 0)
            {
               if(tooltip.corner.search(/Left/) !== -1)
                  newPosition.left -= self.options.style.border.radius;
               else if(tooltip.corner.search(/Right/) !== -1)
                  newPosition.left += self.options.style.border.radius;

               if(tooltip.corner.search(/Top/) !== -1)
                  newPosition.top -= self.options.style.border.radius;
               else if(tooltip.corner.search(/Bottom/) !== -1)
                  newPosition.top += self.options.style.border.radius;
            };

            // IE only adjustments (Pixel perfect!)
            if(ieAdjust)
            {
               if(tooltip.corner.search(/top/) !== -1)
                  newPosition.top -= ieAdjust
               else if(tooltip.corner.search(/bottom/) !== -1)
                  newPosition.top += ieAdjust

               if(tooltip.corner.search(/left/) !== -1)
                  newPosition.left -= ieAdjust
               else if(tooltip.corner.search(/right/) !== -1)
                  newPosition.left += ieAdjust

               if(tooltip.corner.search(/leftMiddle|rightMiddle/) !== -1)
                  newPosition.top -= 1
            };

            // If screen adjustment is enabled, apply adjustments
            if(self.options.position.adjust.screen === true)
               newPosition = screenAdjust.call(self, newPosition, target, tooltip);

            // If mouse is the target, prevent tooltip appearing directly under the mouse
            if(self.options.position.target === 'mouse' && self.options.position.adjust.mouse === true)
            {
               if(self.options.position.adjust.screen === true && self.elements.tip)
                  mouseAdjust = self.elements.tip.attr('rel');
               else
                  mouseAdjust = self.options.position.corner.tooltip;

               newPosition.left += (mouseAdjust.search(/right/i) !== -1) ? -6 : 6;
               newPosition.top += (mouseAdjust.search(/bottom/i) !== -1) ? -6 : 6;
            }

            // Initiate bgiframe plugin in IE6 if tooltip overlaps a select box or object element
            if(!self.elements.bgiframe && jQuery.browser.msie && parseInt(jQuery.browser.version.charAt(0)) == 6)
            {
               jQuery('select, object').each(function()
               {
                  offset = jQuery(this).offset();
                  offset.bottom = offset.top + jQuery(this).height();
                  offset.right = offset.left + jQuery(this).width();

                  if(newPosition.top + tooltip.dimensions.height >= offset.top
                  && newPosition.left + tooltip.dimensions.width >= offset.left)
                     bgiframe.call(self);
               });
            };

            // Add user xy adjustments
            newPosition.left += self.options.position.adjust.x;
            newPosition.top += self.options.position.adjust.y;

            // Set new tooltip position if its moved, animate if enabled
            curPosition = self.getPosition();
            if(newPosition.left != curPosition.left || newPosition.top != curPosition.top)
            {
               // Call API method and if return value is false, halt
               returned = self.beforePositionUpdate.call(self, event);
               if(returned === false) return self;

               // Cache new position
               self.cache.position = newPosition;

               // Check if animation is enabled
               if(animate === true)
               {
                  // Set animated status
                  self.status.animated = true;

                  // Animate and reset animated status on animation end
                  self.elements.tooltip.animate(newPosition, 200, 'swing', function(){ self.status.animated = false });
               }

               // Set new position via CSS
               else self.elements.tooltip.css(newPosition);

               // Call API method and log event if its not a mouse move
               self.onPositionUpdate.call(self, event);
               if(typeof event !== 'undefined' && event.type && event.type !== 'mousemove')
                  jQuery.fn.qtip.log.error.call(self, 1, jQuery.fn.qtip.constants.EVENT_POSITION_UPDATED, 'updatePosition');
            };

            return self;
         },

         updateWidth: function(newWidth)
         {
            var hidden;

            // Make sure tooltip is rendered and if not, return
            if(!self.status.rendered)
               return jQuery.fn.qtip.log.error.call(self, 2, jQuery.fn.qtip.constants.TOOLTIP_NOT_RENDERED, 'updateWidth');

            // Make sure supplied width is a number and if not, return
            else if(newWidth && typeof newWidth !== 'number')
               return jQuery.fn.qtip.log.error.call(self, 2, 'newWidth must be of type number', 'updateWidth');

            // Setup elements which must be hidden during width update
            hidden = self.elements.contentWrapper.siblings().add(self.elements.tip).add(self.elements.button);

            // Calculate the new width if one is not supplied
            if(!newWidth)
            {
               // Explicit width is set
               if(typeof self.options.style.width.value == 'number')
                  newWidth = self.options.style.width.value;

               // No width is set, proceed with auto detection
               else
               {
                  // Set width to auto initally to determine new width and hide other elements
                  self.elements.tooltip.css({ width: 'auto' });
                  hidden.hide();

                  // Set position and zoom to defaults to prevent IE hasLayout bug
                  if(jQuery.browser.msie)
                     self.elements.wrapper.add(self.elements.contentWrapper.children()).css({ zoom: 'normal' });

                  // Set the new width
                  newWidth = self.getDimensions().width + 1;

                  // Make sure its within the maximum and minimum width boundries
                  if(!self.options.style.width.value)
                  {
                     if(newWidth > self.options.style.width.max) newWidth = self.options.style.width.max
                     if(newWidth < self.options.style.width.min) newWidth = self.options.style.width.min
                  };
               };
            };

            // Adjust newWidth by 1px if width is odd (IE6 rounding bug fix)
            if(newWidth % 2 !== 0) newWidth -= 1;

            // Set the new calculated width and unhide other elements
            self.elements.tooltip.width(newWidth);
            hidden.show();

            // Set the border width, if enabled
            if(self.options.style.border.radius)
            {
               self.elements.tooltip.find('.qtip-betweenCorners').each(function(i)
               {
                  jQuery(this).width(newWidth - (self.options.style.border.radius * 2));
               })
            };

            // IE only adjustments
            if(jQuery.browser.msie)
            {
               // Reset position and zoom to give the wrapper layout (IE hasLayout bug)
               self.elements.wrapper.add(self.elements.contentWrapper.children()).css({ zoom: '1' });

               // Set the new width
               self.elements.wrapper.width(newWidth);

               // Adjust BGIframe height and width if enabled
               if(self.elements.bgiframe) self.elements.bgiframe.width(newWidth).height(self.getDimensions.height);
            };

            // Log event and return
            return jQuery.fn.qtip.log.error.call(self, 1, jQuery.fn.qtip.constants.EVENT_WIDTH_UPDATED, 'updateWidth');
         },

         updateStyle: function(name)
         {
            var tip, borders, context, corner, coordinates;

            // Make sure tooltip is rendered and if not, return
            if(!self.status.rendered)
               return jQuery.fn.qtip.log.error.call(self, 2, jQuery.fn.qtip.constants.TOOLTIP_NOT_RENDERED, 'updateStyle');

            // Return if style is not defined or name is not a string
            else if(typeof name !== 'string' || !jQuery.fn.qtip.styles[name])
               return jQuery.fn.qtip.log.error.call(self, 2, jQuery.fn.qtip.constants.STYLE_NOT_DEFINED, 'updateStyle');

            // Set the new style object
            self.options.style = buildStyle.call(self, jQuery.fn.qtip.styles[name], self.options.user.style);

            // Update initial styles of content and title elements
            self.elements.content.css( jQueryStyle(self.options.style) );
            if(self.options.content.title.text !== false)
               self.elements.title.css( jQueryStyle(self.options.style.title, true) );

            // Update CSS border colour
            self.elements.contentWrapper.css({ borderColor: self.options.style.border.color });

            // Update tip color if enabled
            if(self.options.style.tip.corner !== false)
            {
               if(jQuery('<canvas>').get(0).getContext)
               {
                  // Retrieve canvas context and clear
                  tip = self.elements.tooltip.find('.qtip-tip canvas:first');
                  context = tip.get(0).getContext('2d');
                  context.clearRect(0,0,300,300);

                  // Draw new tip
                  corner = tip.parent('div[rel]:first').attr('rel');
                  coordinates = calculateTip(corner, self.options.style.tip.size.width, self.options.style.tip.size.height);
                  drawTip.call(self, tip, coordinates, self.options.style.tip.color || self.options.style.border.color);
               }
               else if(jQuery.browser.msie)
               {
                  // Set new fillcolor attribute
                  tip = self.elements.tooltip.find('.qtip-tip [nodeName="shape"]');
                  tip.attr('fillcolor', self.options.style.tip.color || self.options.style.border.color);
               };
            };

            // Update border colors if enabled
            if(self.options.style.border.radius > 0)
            {
               self.elements.tooltip.find('.qtip-betweenCorners').css({ backgroundColor: self.options.style.border.color });

               if(jQuery('<canvas>').get(0).getContext)
               {
                  borders = calculateBorders(self.options.style.border.radius)
                  self.elements.tooltip.find('.qtip-wrapper canvas').each(function()
                  {
                     // Retrieve canvas context and clear
                     context = jQuery(this).get(0).getContext('2d');
                     context.clearRect(0,0,300,300);

                     // Draw new border
                     corner = jQuery(this).parent('div[rel]:first').attr('rel')
                     drawBorder.call(self, jQuery(this), borders[corner],
                        self.options.style.border.radius, self.options.style.border.color);
                  });
               }
               else if(jQuery.browser.msie)
               {
                  // Set new fillcolor attribute on each border corner
                  self.elements.tooltip.find('.qtip-wrapper [nodeName="arc"]').each(function()
                  {
                     jQuery(this).attr('fillcolor', self.options.style.border.color)
                  });
               };
            };

            // Log event and return
            return jQuery.fn.qtip.log.error.call(self, 1, jQuery.fn.qtip.constants.EVENT_STYLE_UPDATED, 'updateStyle');
         },

         updateContent: function(content, reposition)
         {
            var parsedContent, images, loadedImages;

            // Make sure tooltip is rendered and if not, return
            if(!self.status.rendered)
               return jQuery.fn.qtip.log.error.call(self, 2, jQuery.fn.qtip.constants.TOOLTIP_NOT_RENDERED, 'updateContent');

            // Make sure content is defined before update
            else if(!content)
               return jQuery.fn.qtip.log.error.call(self, 2, jQuery.fn.qtip.constants.NO_CONTENT_PROVIDED, 'updateContent');

            // Call API method and set new content if a string is returned
            parsedContent = self.beforeContentUpdate.call(self, content);
            if(typeof parsedContent == 'string') content = parsedContent;
            else if(parsedContent === false) return;

            // Set position and zoom to defaults to prevent IE hasLayout bug
            if(jQuery.browser.msie) self.elements.contentWrapper.children().css({ zoom: 'normal' });

            // Append new content if its a DOM array and show it if hidden
            if(content.jquery && content.length > 0)
               content.clone(true).appendTo(self.elements.content).show();

            // Content is a regular string, insert the new content
            else self.elements.content.html(content);

            // Check if images need to be loaded before position is updated to prevent mis-positioning
            images = self.elements.content.find('img[complete=false]');
            if(images.length > 0)
            {
               loadedImages = 0;
               images.each(function(i)
               {
                  jQuery('<img src="'+ jQuery(this).attr('src') +'" />')
                     .load(function(){ if(++loadedImages == images.length) afterLoad(); });
               });
            }
            else afterLoad();

            function afterLoad()
            {
               // Update the tooltip width
               self.updateWidth();

               // If repositioning is enabled, update positions
               if(reposition !== false)
               {
                  // Update position if tooltip isn't static
                  if(self.options.position.type !== 'static')
                     self.updatePosition(self.elements.tooltip.is(':visible'), true);

                  // Reposition the tip if enabled
                  if(self.options.style.tip.corner !== false)
                     positionTip.call(self);
               };
            };

            // Call API method and log event
            self.onContentUpdate.call(self);
            return jQuery.fn.qtip.log.error.call(self, 1, jQuery.fn.qtip.constants.EVENT_CONTENT_UPDATED, 'loadContent');
         },

         loadContent: function(url, data, method)
         {
            var returned;

            // Make sure tooltip is rendered and if not, return
            if(!self.status.rendered)
               return jQuery.fn.qtip.log.error.call(self, 2, jQuery.fn.qtip.constants.TOOLTIP_NOT_RENDERED, 'loadContent');

            // Call API method and if return value is false, halt
            returned = self.beforeContentLoad.call(self);
            if(returned === false) return self;

            // Load content using specified request type
            if(method == 'post')
               jQuery.post(url, data, setupContent);
            else
               jQuery.get(url, data, setupContent);

            function setupContent(content)
            {
               // Call API method and log event
               self.onContentLoad.call(self);
               jQuery.fn.qtip.log.error.call(self, 1, jQuery.fn.qtip.constants.EVENT_CONTENT_LOADED, 'loadContent');

               // Update the content
               self.updateContent(content);
            };

            return self;
         },

         updateTitle: function(content)
         {
            // Make sure tooltip is rendered and if not, return
            if(!self.status.rendered)
               return jQuery.fn.qtip.log.error.call(self, 2, jQuery.fn.qtip.constants.TOOLTIP_NOT_RENDERED, 'updateTitle');

            // Make sure content is defined before update
            else if(!content)
               return jQuery.fn.qtip.log.error.call(self, 2, jQuery.fn.qtip.constants.NO_CONTENT_PROVIDED, 'updateTitle');

            // Call API method and if return value is false, halt
            returned = self.beforeTitleUpdate.call(self);
            if(returned === false) return self;

            // Set the new content and reappend the button if enabled
            if(self.elements.button) self.elements.button = self.elements.button.clone(true);
            self.elements.title.html(content)
            if(self.elements.button) self.elements.title.prepend(self.elements.button);

            // Call API method and log event
            self.onTitleUpdate.call(self);
            return jQuery.fn.qtip.log.error.call(self, 1, jQuery.fn.qtip.constants.EVENT_TITLE_UPDATED, 'updateTitle');
         },

         focus: function(event)
         {
            var curIndex, newIndex, elemIndex, returned;

            // Make sure tooltip is rendered and if not, return
            if(!self.status.rendered)
               return jQuery.fn.qtip.log.error.call(self, 2, jQuery.fn.qtip.constants.TOOLTIP_NOT_RENDERED, 'focus');

            else if(self.options.position.type == 'static')
               return jQuery.fn.qtip.log.error.call(self, 1, jQuery.fn.qtip.constants.CANNOT_FOCUS_STATIC, 'focus');

            // Set z-index variables
            curIndex = parseInt( self.elements.tooltip.css('z-index') );
            newIndex = 6000 + jQuery('div.qtip[qtip]').length - 1;

            // Only update the z-index if it has changed and tooltip is not already focused
            if(!self.status.focused && curIndex !== newIndex)
            {
               // Call API method and if return value is false, halt
               returned = self.beforeFocus.call(self, event);
               if(returned === false) return self;

               // Loop through all other tooltips
               jQuery('div.qtip[qtip]').not(self.elements.tooltip).each(function()
               {
                  if(jQuery(this).qtip('api').status.rendered === true)
                  {
                     elemIndex = parseInt(jQuery(this).css('z-index'));

                     // Reduce all other tooltip z-index by 1
                     if(typeof elemIndex == 'number' && elemIndex > -1)
                        jQuery(this).css({ zIndex: parseInt( jQuery(this).css('z-index') ) - 1 });

                     // Set focused status to false
                     jQuery(this).qtip('api').status.focused = false;
                  }
               })

               // Set the new z-index and set focus status to true
               self.elements.tooltip.css({ zIndex: newIndex });
               self.status.focused = true;

               // Call API method and log event
               self.onFocus.call(self, event);
               jQuery.fn.qtip.log.error.call(self, 1, jQuery.fn.qtip.constants.EVENT_FOCUSED, 'focus');
            };

            return self;
         },

         disable: function(state)
         {
            // Make sure tooltip is rendered and if not, return
            if(!self.status.rendered)
               return jQuery.fn.qtip.log.error.call(self, 2, jQuery.fn.qtip.constants.TOOLTIP_NOT_RENDERED, 'disable');

            if(state)
            {
               // Tooltip is not already disabled, proceed
               if(!self.status.disabled)
               {
                  // Set the disabled flag and log event
                  self.status.disabled = true;
                  jQuery.fn.qtip.log.error.call(self, 1, jQuery.fn.qtip.constants.EVENT_DISABLED, 'disable');
               }

               // Tooltip is already disabled, inform user via log
               else  jQuery.fn.qtip.log.error.call(self, 1, jQuery.fn.qtip.constants.TOOLTIP_ALREADY_DISABLED, 'disable');
            }
            else
            {
               // Tooltip is not already enabled, proceed
               if(self.status.disabled)
               {
                  // Reassign events, set disable status and log
                  self.status.disabled = false;
                  jQuery.fn.qtip.log.error.call(self, 1, jQuery.fn.qtip.constants.EVENT_ENABLED, 'disable');
               }

               // Tooltip is already enabled, inform the user via log
               else jQuery.fn.qtip.log.error.call(self, 1, jQuery.fn.qtip.constants.TOOLTIP_ALREADY_ENABLED, 'disable');
            };

            return self;
         },

         destroy: function()
         {
            var i, returned, interfaces;

            // Call API method and if return value is false, halt
            returned = self.beforeDestroy.call(self);
            if(returned === false) return self;

            // Check if tooltip is rendered
            if(self.status.rendered)
            {
               // Remove event handlers and remove element
               self.options.show.when.target.unbind('mousemove.qtip', self.updatePosition);
               self.options.show.when.target.unbind('mouseout.qtip', self.hide);
               self.options.show.when.target.unbind(self.options.show.when.event + '.qtip');
               self.options.hide.when.target.unbind(self.options.hide.when.event + '.qtip');
               self.elements.tooltip.unbind(self.options.hide.when.event + '.qtip');
               self.elements.tooltip.unbind('mouseover.qtip', self.focus);
               self.elements.tooltip.remove();
            }

            // Tooltip isn't yet rendered, remove render event
            else self.options.show.when.target.unbind(self.options.show.when.event+'.qtip-create');

            // Check to make sure qTip data is present on target element
            if(typeof self.elements.target.data('qtip') == 'object')
            {
               // Remove API references from interfaces object
               interfaces = self.elements.target.data('qtip').interfaces;
               if(typeof interfaces == 'object' && interfaces.length > 0)
               {
                  // Remove API from interfaces array
                  for(i = 0; i < interfaces.length - 1; i++)
                     if(interfaces[i].id == self.id) interfaces.splice(i, 1)
               }
            }
            delete jQuery.fn.qtip.interfaces[self.id];

            // Set qTip current id to previous tooltips API if available
            if(typeof interfaces == 'object' && interfaces.length > 0)
               self.elements.target.data('qtip').current = interfaces.length -1;
            else
               self.elements.target.removeData('qtip');

            // Call API method and log destroy
            self.onDestroy.call(self);
            jQuery.fn.qtip.log.error.call(self, 1, jQuery.fn.qtip.constants.EVENT_DESTROYED, 'destroy');

            return self.elements.target
         },

         getPosition: function()
         {
            var show, offset;

            // Make sure tooltip is rendered and if not, return
            if(!self.status.rendered)
               return jQuery.fn.qtip.log.error.call(self, 2, jQuery.fn.qtip.constants.TOOLTIP_NOT_RENDERED, 'getPosition');

            show = (self.elements.tooltip.css('display') !== 'none') ? false : true;

            // Show and hide tooltip to make sure coordinates are returned
            if(show) self.elements.tooltip.css({ visiblity: 'hidden' }).show();
            offset = self.elements.tooltip.offset();
            if(show) self.elements.tooltip.css({ visiblity: 'visible' }).hide();

            return offset;
         },

         getDimensions: function()
         {
            var show, dimensions;

            // Make sure tooltip is rendered and if not, return
            if(!self.status.rendered)
               return jQuery.fn.qtip.log.error.call(self, 2, jQuery.fn.qtip.constants.TOOLTIP_NOT_RENDERED, 'getDimensions');

            show = (!self.elements.tooltip.is(':visible')) ? true : false;

            // Show and hide tooltip to make sure dimensions are returned
            if(show) self.elements.tooltip.css({ visiblity: 'hidden' }).show();
            dimensions = {
               height: self.elements.tooltip.outerHeight(),
               width: self.elements.tooltip.outerWidth()
            };
            if(show) self.elements.tooltip.css({ visiblity: 'visible' }).hide();

            return dimensions;
         }
      });
   };

   // Define priamry construct function
   function construct()
   {
      var self, adjust, content, url, data, method, tempLength;
      self = this;

      // Call API method
      self.beforeRender.call(self);

      // Set rendered status to true
      self.status.rendered = true;

      // Create initial tooltip elements
      self.elements.tooltip =  '<div qtip="'+self.id+'" ' +
         'class="qtip '+(self.options.style.classes.tooltip || self.options.style)+'"' +
         'style="display:none; -moz-border-radius:0; -webkit-border-radius:0; border-radius:0;' +
         'position:'+self.options.position.type+';">' +
         '  <div class="qtip-wrapper" style="position:relative; overflow:hidden; text-align:left;">' +
         '    <div class="qtip-contentWrapper" style="overflow:hidden;">' +
         '       <div class="qtip-content '+self.options.style.classes.content+'"></div>' +
         '</div></div></div>';

      // Append to container element
      self.elements.tooltip = jQuery(self.elements.tooltip);
      self.elements.tooltip.appendTo(self.options.position.container)

      // Setup tooltip qTip data
      self.elements.tooltip.data('qtip', { current: 0, interfaces: [self] });

      // Setup element references
      self.elements.wrapper = self.elements.tooltip.children('div:first');
      self.elements.contentWrapper = self.elements.wrapper.children('div:first').css({ background: self.options.style.background });
      self.elements.content = self.elements.contentWrapper.children('div:first').css( jQueryStyle(self.options.style) );

      // Apply IE hasLayout fix to wrapper and content elements
      if(jQuery.browser.msie) self.elements.wrapper.add(self.elements.content).css({ zoom: 1 });

      // Setup tooltip attributes
      if(self.options.hide.when.event == 'unfocus') self.elements.tooltip.attr('unfocus', true);

      // If an explicit width is set, updateWidth prior to setting content to prevent dirty rendering
      if(typeof self.options.style.width.value == 'number') self.updateWidth();

      // Create borders and tips if supported by the browser
      if(jQuery('<canvas>').get(0).getContext || jQuery.browser.msie)
      {
         // Create border
         if(self.options.style.border.radius > 0)
            createBorder.call(self);
         else
            self.elements.contentWrapper.css({ border: self.options.style.border.width+'px solid '+self.options.style.border.color  });

         // Create tip if enabled
         if(self.options.style.tip.corner !== false)
            createTip.call(self);
      }

      // Neither canvas or VML is supported, tips and borders cannot be drawn!
      else
      {
         // Set defined border width
         self.elements.contentWrapper.css({ border: self.options.style.border.width+'px solid '+self.options.style.border.color  });

         // Reset border radius and tip
         self.options.style.border.radius = 0;
         self.options.style.tip.corner = false;

         // Inform via log
         jQuery.fn.qtip.log.error.call(self, 2, jQuery.fn.qtip.constants.CANVAS_VML_NOT_SUPPORTED, 'render');
      };

      // Use the provided content string or DOM array
      if((typeof self.options.content.text == 'string' && self.options.content.text.length > 0)
      || (self.options.content.text.jquery && self.options.content.text.length > 0))
         content = self.options.content.text;

      // Use title string for content if present
      else if(typeof self.elements.target.attr('title') == 'string' && self.elements.target.attr('title').length > 0)
      {
         content = self.elements.target.attr('title').replace("\\n", '<br />');
         self.elements.target.attr('title', ''); // Remove title attribute to prevent default tooltip showing
      }

      // No title is present, use alt attribute instead
      else if(typeof self.elements.target.attr('alt') == 'string' && self.elements.target.attr('alt').length > 0)
      {
         content = self.elements.target.attr('alt').replace("\\n", '<br />');
         self.elements.target.attr('alt', ''); // Remove alt attribute to prevent default tooltip showing
      }

      // No valid content was provided, inform via log
      else
      {
         content = ' ';
         jQuery.fn.qtip.log.error.call(self, 1, jQuery.fn.qtip.constants.NO_VALID_CONTENT, 'render');
      };

      // Set the tooltips content and create title if enabled
      if(self.options.content.title.text !== false) createTitle.call(self);
      self.updateContent(content);

      // Assign events and toggle tooltip with focus
      assignEvents.call(self);
      if(self.options.show.ready === true) self.show();

      // Retrieve ajax content if provided
      if(self.options.content.url !== false)
      {
         url = self.options.content.url;
         data = self.options.content.data;
         method = self.options.content.method || 'get';
         self.loadContent(url, data, method);
      };

      // Call API method and log event
      self.onRender.call(self);
      jQuery.fn.qtip.log.error.call(self, 1, jQuery.fn.qtip.constants.EVENT_RENDERED, 'render');
   };

   // Create borders using canvas and VML
   function createBorder()
   {
      var self, i, width, radius, color, coordinates, containers, size, betweenWidth, betweenCorners, borderTop, borderBottom, borderCoord, sideWidth, vertWidth;
      self = this;

      // Destroy previous border elements, if present
      self.elements.wrapper.find('.qtip-borderBottom, .qtip-borderTop').remove();

      // Setup local variables
      width = self.options.style.border.width;
      radius = self.options.style.border.radius;
      color = self.options.style.border.color || self.options.style.tip.color;

      // Calculate border coordinates
      coordinates = calculateBorders(radius);

      // Create containers for the border shapes
      containers = {};
      for(i in coordinates)
      {
         // Create shape container
         containers[i] = '<div rel="'+i+'" style="'+((i.search(/Left/) !== -1) ? 'left' : 'right') + ':0; ' +
            'position:absolute; height:'+radius+'px; width:'+radius+'px; overflow:hidden; line-height:0.1px; font-size:1px">';

         // Canvas is supported
         if(jQuery('<canvas>').get(0).getContext)
            containers[i] += '<canvas height="'+radius+'" width="'+radius+'" style="vertical-align: top"></canvas>';

         // No canvas, but if it's IE use VML
         else if(jQuery.browser.msie)
         {
            size = radius * 2 + 3;
            containers[i] += '<v:arc stroked="false" fillcolor="'+color+'" startangle="'+coordinates[i][0]+'" endangle="'+coordinates[i][1]+'" ' +
               'style="width:'+size+'px; height:'+size+'px; margin-top:'+((i.search(/bottom/) !== -1) ? -2 : -1)+'px; ' +
               'margin-left:'+((i.search(/Right/) !== -1) ? coordinates[i][2] - 3.5 : -1)+'px; ' +
               'vertical-align:top; display:inline-block; behavior:url(#default#VML)"></v:arc>';

         };

         containers[i] += '</div>';
      };

      // Create between corners elements
      betweenWidth = self.getDimensions().width - (Math.max(width, radius) * 2);
      betweenCorners = '<div class="qtip-betweenCorners" style="height:'+radius+'px; width:'+betweenWidth+'px; ' +
         'overflow:hidden; background-color:'+color+'; line-height:0.1px; font-size:1px;">';

      // Create top border container
      borderTop = '<div class="qtip-borderTop" dir="ltr" style="height:'+radius+'px; ' +
         'margin-left:'+radius+'px; line-height:0.1px; font-size:1px; padding:0;">' +
         containers['topLeft'] + containers['topRight'] + betweenCorners;
      self.elements.wrapper.prepend(borderTop);

      // Create bottom border container
      borderBottom = '<div class="qtip-borderBottom" dir="ltr" style="height:'+radius+'px; ' +
         'margin-left:'+radius+'px; line-height:0.1px; font-size:1px; padding:0;">' +
         containers['bottomLeft'] + containers['bottomRight'] + betweenCorners;
      self.elements.wrapper.append(borderBottom);

      // Draw the borders if canvas were used (Delayed til after DOM creation)
      if(jQuery('<canvas>').get(0).getContext)
      {
         self.elements.wrapper.find('canvas').each(function()
         {
            borderCoord = coordinates[ jQuery(this).parent('[rel]:first').attr('rel') ];
            drawBorder.call(self, jQuery(this), borderCoord, radius, color);
         })
      }

      // Create a phantom VML element (IE won't show the last created VML element otherwise)
      else if(jQuery.browser.msie) self.elements.tooltip.append('<v:image style="behavior:url(#default#VML);"></v:image>');

      // Setup contentWrapper border
      sideWidth = Math.max(radius, (radius + (width - radius)) )
      vertWidth = Math.max(width - radius, 0);
      self.elements.contentWrapper.css({
         border: '0px solid ' + color,
         borderWidth: vertWidth + 'px ' + sideWidth + 'px'
      })
   };

   // Border canvas draw method
   function drawBorder(canvas, coordinates, radius, color)
   {
      // Create corner
      var context = canvas.get(0).getContext('2d');
      context.fillStyle = color;
      context.beginPath();
      context.arc(coordinates[0], coordinates[1], radius, 0, Math.PI * 2, false);
      context.fill();
   };

   // Create tip using canvas and VML
   function createTip(corner)
   {
      var self, color, coordinates, coordsize, path;
      self = this;

      // Destroy previous tip, if there is one
      if(self.elements.tip !== null) self.elements.tip.remove();

      // Setup color and corner values
      color = self.options.style.tip.color || self.options.style.border.color;
      if(self.options.style.tip.corner === false) return;
      else if(!corner) corner = self.options.style.tip.corner;

      // Calculate tip coordinates
      coordinates = calculateTip(corner, self.options.style.tip.size.width, self.options.style.tip.size.height);

      // Create tip element
      self.elements.tip =  '<div class="'+self.options.style.classes.tip+'" dir="ltr" rel="'+corner+'" style="position:absolute; ' +
         'height:'+self.options.style.tip.size.height+'px; width:'+self.options.style.tip.size.width+'px; ' +
         'margin:0 auto; line-height:0.1px; font-size:1px;">';

      // Use canvas element if supported
      if(jQuery('<canvas>').get(0).getContext)
          self.elements.tip += '<canvas height="'+self.options.style.tip.size.height+'" width="'+self.options.style.tip.size.width+'"></canvas>';

      // Canvas not supported - Use VML (IE)
      else if(jQuery.browser.msie)
      {
         // Create coordize and tip path using tip coordinates
         coordsize = self.options.style.tip.size.width + ',' + self.options.style.tip.size.height;
         path = 'm' + coordinates[0][0] + ',' + coordinates[0][1];
         path += ' l' + coordinates[1][0] + ',' + coordinates[1][1];
         path += ' ' + coordinates[2][0] + ',' + coordinates[2][1];
         path += ' xe';

         // Create VML element
         self.elements.tip += '<v:shape fillcolor="'+color+'" stroked="false" filled="true" path="'+path+'" coordsize="'+coordsize+'" ' +
            'style="width:'+self.options.style.tip.size.width+'px; height:'+self.options.style.tip.size.height+'px; ' +
            'line-height:0.1px; display:inline-block; behavior:url(#default#VML); ' +
            'vertical-align:'+((corner.search(/top/) !== -1) ? 'bottom' : 'top')+'"></v:shape>';

         // Create a phantom VML element (IE won't show the last created VML element otherwise)
         self.elements.tip += '<v:image style="behavior:url(#default#VML);"></v:image>';

         // Prevent tooltip appearing above the content (IE z-index bug)
         self.elements.contentWrapper.css('position', 'relative');
      };

      // Attach new tip to tooltip element
      self.elements.tooltip.prepend(self.elements.tip + '</div>');

      // Create element reference and draw the canvas tip (Delayed til after DOM creation)
      self.elements.tip = self.elements.tooltip.find('.'+self.options.style.classes.tip).eq(0);
      if(jQuery('<canvas>').get(0).getContext)
         drawTip.call(self, self.elements.tip.find('canvas:first'), coordinates, color);

      // Fix IE small tip bug
      if(corner.search(/top/) !== -1 && jQuery.browser.msie && parseInt(jQuery.browser.version.charAt(0)) === 6)
         self.elements.tip.css({ marginTop: -4 });

      // Set the tip position
      positionTip.call(self, corner);
   };

   // Canvas tip drawing method
   function drawTip(canvas, coordinates, color)
   {
      // Setup properties
      var context = canvas.get(0).getContext('2d');
      context.fillStyle = color;

      // Create tip
      context.beginPath();
      context.moveTo(coordinates[0][0], coordinates[0][1]);
      context.lineTo(coordinates[1][0], coordinates[1][1]);
      context.lineTo(coordinates[2][0], coordinates[2][1]);
      context.fill();
   };

   function positionTip(corner)
   {
      var self, ieAdjust, paddingCorner, paddingSize, newMargin;
      self = this;

      // Return if tips are disabled or tip is not yet rendered
      if(self.options.style.tip.corner === false || !self.elements.tip) return;
      if(!corner) corner = self.elements.tip.attr('rel');

      // Setup adjustment variables
      ieAdjust = positionAdjust = (jQuery.browser.msie) ? 1 : 0;

      // Set initial position
      self.elements.tip.css(corner.match(/left|right|top|bottom/)[0], 0);

      // Set position of tip to correct side
      if(corner.search(/top|bottom/) !== -1)
      {
         // Adjustments for IE6 - 0.5px border gap bug
         if(jQuery.browser.msie)
         {
            if(parseInt(jQuery.browser.version.charAt(0)) === 6)
               positionAdjust = (corner.search(/top/) !== -1) ? -3 : 1;
            else
               positionAdjust = (corner.search(/top/) !== -1) ? 1 : 2;
         };

         if(corner.search(/Middle/) !== -1)
            self.elements.tip.css({ left: '50%', marginLeft: -(self.options.style.tip.size.width / 2) });

         else if(corner.search(/Left/) !== -1)
            self.elements.tip.css({ left: self.options.style.border.radius - ieAdjust });

         else if(corner.search(/Right/) !== -1)
            self.elements.tip.css({ right: self.options.style.border.radius + ieAdjust });

         if(corner.search(/top/) !== -1)
            self.elements.tip.css({ top: -positionAdjust });
         else
            self.elements.tip.css({ bottom: positionAdjust });

      }
      else if(corner.search(/left|right/) !== -1)
      {
         // Adjustments for IE6 - 0.5px border gap bug
         if(jQuery.browser.msie)
            positionAdjust = (parseInt(jQuery.browser.version.charAt(0)) === 6) ? 1 : ((corner.search(/left/) !== -1) ? 1 : 2);

         if(corner.search(/Middle/) !== -1)
            self.elements.tip.css({ top: '50%', marginTop: -(self.options.style.tip.size.height / 2) });

         else if(corner.search(/Top/) !== -1)
            self.elements.tip.css({ top: self.options.style.border.radius - ieAdjust });

         else if(corner.search(/Bottom/) !== -1)
            self.elements.tip.css({ bottom: self.options.style.border.radius + ieAdjust });

         if(corner.search(/left/) !== -1)
            self.elements.tip.css({ left: -positionAdjust });
         else
            self.elements.tip.css({ right: positionAdjust });
      };

      // Adjust tooltip padding to compensate for tip
      paddingCorner = 'padding-' + corner.match(/left|right|top|bottom/)[0];
      paddingSize = self.options.style.tip.size[ (paddingCorner.search(/left|right/) !== -1) ? 'width' : 'height' ];
      self.elements.tooltip.css('padding', 0);
      self.elements.tooltip.css(paddingCorner, paddingSize);

      // Match content margin to prevent gap bug in IE6 ONLY
      if(jQuery.browser.msie && parseInt(jQuery.browser.version.charAt(0)) == 6)
      {
         newMargin = parseInt(self.elements.tip.css('margin-top')) || 0;
         newMargin += parseInt(self.elements.content.css('margin-top')) || 0;

         self.elements.tip.css({ marginTop: newMargin });
      };
   };

   // Create title bar for content
   function createTitle()
   {
      var self = this;

      // Destroy previous title element, if present
      if(self.elements.title !== null) self.elements.title.remove();

      // Create title element
      self.elements.title = jQuery('<div class="'+self.options.style.classes.title+'">')
         .css( jQueryStyle(self.options.style.title, true) )
         .css({ zoom: (jQuery.browser.msie) ? 1 : 0 })
         .prependTo(self.elements.contentWrapper);

      // Update title with contents if enabled
      if(self.options.content.title.text) self.updateTitle.call(self, self.options.content.title.text);

      // Create title close buttons if enabled
      if(self.options.content.title.button !== false
      && typeof self.options.content.title.button == 'string')
      {
         self.elements.button = jQuery('<a class="'+self.options.style.classes.button+'" style="float:right; position: relative"></a>')
            .css( jQueryStyle(self.options.style.button, true) )
            .html(self.options.content.title.button)
            .prependTo(self.elements.title)
            .click(function(event){ if(!self.status.disabled) self.hide(event) });
      };
   };

   // Assign hide and show events
   function assignEvents()
   {
      var self, showTarget, hideTarget, inactiveEvents;
      self = this;

      // Setup event target variables
      showTarget = self.options.show.when.target;
      hideTarget = self.options.hide.when.target;

      // Add tooltip as a hideTarget is its fixed
      if(self.options.hide.fixed) hideTarget = hideTarget.add(self.elements.tooltip);

      // Check if the hide event is special 'inactive' type
      if(self.options.hide.when.event == 'inactive')
      {
         // Define events which reset the 'inactive' event handler
         inactiveEvents = ['click', 'dblclick', 'mousedown', 'mouseup', 'mousemove',
         'mouseout', 'mouseenter', 'mouseleave', 'mouseover' ];

         // Define 'inactive' event timer method
         function inactiveMethod(event)
         {
            if(self.status.disabled === true) return;

            //Clear and reset the timer
            clearTimeout(self.timers.inactive);
            self.timers.inactive = setTimeout(function()
            {
               // Unassign 'inactive' events
               jQuery(inactiveEvents).each(function()
               {
                  hideTarget.unbind(this+'.qtip-inactive');
                  self.elements.content.unbind(this+'.qtip-inactive');
               });

               // Hide the tooltip
               self.hide(event);
            }
            , self.options.hide.delay);
         };
      }

      // Check if the tooltip is 'fixed'
      else if(self.options.hide.fixed === true)
      {
         self.elements.tooltip.bind('mouseover.qtip', function()
         {
            if(self.status.disabled === true) return;

            // Reset the hide timer
            clearTimeout(self.timers.hide);
         });
      };

      // Define show event method
      function showMethod(event)
      {
         if(self.status.disabled === true) return;

         // If set, hide tooltip when inactive for delay period
         if(self.options.hide.when.event == 'inactive')
         {
            // Assign each reset event
            jQuery(inactiveEvents).each(function()
            {
               hideTarget.bind(this+'.qtip-inactive', inactiveMethod);
               self.elements.content.bind(this+'.qtip-inactive', inactiveMethod);
            });

            // Start the inactive timer
            inactiveMethod();
         };

         // Clear hide timers
         clearTimeout(self.timers.show);
         clearTimeout(self.timers.hide);

         // Start show timer
         self.timers.show = setTimeout(function(){ self.show(event); }, self.options.show.delay);
      };

      // Define hide event method
      function hideMethod(event)
      {
         if(self.status.disabled === true) return;

         // Prevent hiding if tooltip is fixed and event target is the tooltip
         if(self.options.hide.fixed === true
         && self.options.hide.when.event.search(/mouse(out|leave)/i) !== -1
         && jQuery(event.relatedTarget).parents('div.qtip[qtip]').length > 0)
         {
            // Prevent default and popagation
            event.stopPropagation();
            event.preventDefault();

            // Reset the hide timer
            clearTimeout(self.timers.hide);
            return false;
         };

         // Clear timers and stop animation queue
         clearTimeout(self.timers.show);
         clearTimeout(self.timers.hide);
         self.elements.tooltip.stop(true, true);

         // If tooltip has displayed, start hide timer
         self.timers.hide = setTimeout(function(){ self.hide(event); }, self.options.hide.delay);
      };

      // Both events and targets are identical, apply events using a toggle
      if((self.options.show.when.target.add(self.options.hide.when.target).length === 1
      && self.options.show.when.event == self.options.hide.when.event
      && self.options.hide.when.event !== 'inactive')
      || self.options.hide.when.event == 'unfocus')
      {
         self.cache.toggle = 0;
         // Use a toggle to prevent hide/show conflicts
         showTarget.bind(self.options.show.when.event + '.qtip', function(event)
         {
            if(self.cache.toggle == 0) showMethod(event);
            else hideMethod(event);
         });
      }

      // Events are not identical, bind normally
      else
      {
         showTarget.bind(self.options.show.when.event + '.qtip', showMethod);

         // If the hide event is not 'inactive', bind the hide method
         if(self.options.hide.when.event !== 'inactive')
            hideTarget.bind(self.options.hide.when.event + '.qtip', hideMethod);
      };

      // Focus the tooltip on mouseover
      if(self.options.position.type.search(/(fixed|absolute)/) !== -1)
         self.elements.tooltip.bind('mouseover.qtip', self.focus);

      // If mouse is the target, update tooltip position on mousemove
      if(self.options.position.target === 'mouse' && self.options.position.type !== 'static')
      {
         showTarget.bind('mousemove.qtip', function(event)
         {
            // Set the new mouse positions if adjustment is enabled
            self.cache.mouse = { x: event.pageX, y: event.pageY };

            // Update the tooltip position only if the tooltip is visible and adjustment is enabled
            if(self.status.disabled === false
            && self.options.position.adjust.mouse === true
            && self.options.position.type !== 'static'
            && self.elements.tooltip.css('display') !== 'none')
               self.updatePosition(event);
         });
      };
   };

   // Screen position adjustment
   function screenAdjust(position, target, tooltip)
   {
      var self, adjustedPosition, adjust, newCorner, overflow, corner;
      self = this;

      // Setup corner and adjustment variable
      if(tooltip.corner == 'center') return target.position // TODO: 'center' corner adjustment
      adjustedPosition = jQuery.extend({}, position);
      newCorner = { x: false, y: false };

      // Define overflow properties
      overflow = {
         left: (adjustedPosition.left < jQuery.fn.qtip.cache.screen.scroll.left),
         right: (adjustedPosition.left + tooltip.dimensions.width + 2 >= jQuery.fn.qtip.cache.screen.width + jQuery.fn.qtip.cache.screen.scroll.left),
         top: (adjustedPosition.top < jQuery.fn.qtip.cache.screen.scroll.top),
         bottom: (adjustedPosition.top + tooltip.dimensions.height + 2 >= jQuery.fn.qtip.cache.screen.height + jQuery.fn.qtip.cache.screen.scroll.top)
      };

      // Determine new positioning properties
      adjust = {
         left: (overflow.left && (tooltip.corner.search(/right/i) != -1 || (tooltip.corner.search(/right/i) == -1 && !overflow.right))),
         right: (overflow.right && (tooltip.corner.search(/left/i) != -1 || (tooltip.corner.search(/left/i) == -1 && !overflow.left))),
         top: (overflow.top && tooltip.corner.search(/top/i) == -1),
         bottom: (overflow.bottom && tooltip.corner.search(/bottom/i) == -1)
      };

      // Tooltip overflows off the left side of the screen
      if(adjust.left)
      {
         if(self.options.position.target !== 'mouse')
            adjustedPosition.left = target.position.left + target.dimensions.width;
         else
            adjustedPosition.left = self.cache.mouse.x

         newCorner.x = 'Left';
      }

      // Tooltip overflows off the right side of the screen
      else if(adjust.right)
      {
         if(self.options.position.target !== 'mouse')
            adjustedPosition.left = target.position.left - tooltip.dimensions.width;
         else
            adjustedPosition.left = self.cache.mouse.x - tooltip.dimensions.width;

         newCorner.x = 'Right';
      };

      // Tooltip overflows off the top of the screen
      if(adjust.top)
      {
         if(self.options.position.target !== 'mouse')
            adjustedPosition.top = target.position.top + target.dimensions.height;
         else
            adjustedPosition.top = self.cache.mouse.y

         newCorner.y = 'top';
      }

      // Tooltip overflows off the bottom of the screen
      else if(adjust.bottom)
      {
         if(self.options.position.target !== 'mouse')
            adjustedPosition.top = target.position.top - tooltip.dimensions.height;
         else
            adjustedPosition.top = self.cache.mouse.y - tooltip.dimensions.height;

         newCorner.y = 'bottom';
      };

      // Don't adjust if resulting position is negative
      if(adjustedPosition.left < 0)
      {
         adjustedPosition.left = position.left;
         newCorner.x = false;
      };
      if(adjustedPosition.top < 0)
      {
         adjustedPosition.top = position.top;
         newCorner.y = false;
      };

      // Change tip corner if positioning has changed and tips are enabled
      if(self.options.style.tip.corner !== false)
      {
         // Determine new corner properties
         adjustedPosition.corner = new String(tooltip.corner);
         if(newCorner.x !== false) adjustedPosition.corner = adjustedPosition.corner.replace(/Left|Right|Middle/, newCorner.x);
         if(newCorner.y !== false) adjustedPosition.corner = adjustedPosition.corner.replace(/top|bottom/, newCorner.y);

         // Adjust tip if position has changed and tips are enabled
         if(adjustedPosition.corner !== self.elements.tip.attr('rel'))
            createTip.call(self, adjustedPosition.corner);
      };

      return adjustedPosition;
   };

   // Build a jQuery style object from supplied style object
   function jQueryStyle(style, sub)
   {
      var styleObj, i;

      styleObj = jQuery.extend(true, {}, style);
      for(i in styleObj)
      {
         if(sub === true && i.search(/(tip|classes)/i) !== -1)
            delete styleObj[i];
         else if(!sub && i.search(/(width|border|tip|title|classes|user)/i) !== -1)
            delete styleObj[i];
      };

      return styleObj;
   };

   // Sanitize styles
   function sanitizeStyle(style)
   {
      if(typeof style.tip !== 'object') style.tip = { corner: style.tip };
      if(typeof style.tip.size !== 'object') style.tip.size = { width: style.tip.size, height: style.tip.size };
      if(typeof style.border !== 'object') style.border = { width: style.border };
      if(typeof style.width !== 'object') style.width = { value: style.width };
      if(typeof style.width.max == 'string') style.width.max = parseInt(style.width.max.replace(/([0-9]+)/i, "jQuery1"));
      if(typeof style.width.min == 'string') style.width.min = parseInt(style.width.min.replace(/([0-9]+)/i, "jQuery1"));

      // Convert deprecated x and y tip values to width/height
      if(typeof style.tip.size.x == 'number')
      {
         style.tip.size.width = style.tip.size.x;
         delete style.tip.size.x;
      };
      if(typeof style.tip.size.y == 'number')
      {
         style.tip.size.height = style.tip.size.y;
         delete style.tip.size.y;
      };

      return style;
   };

   // Build styles recursively with inheritance
   function buildStyle()
   {
      var self, i, styleArray, styleExtend, finalStyle, ieAdjust;
      self = this;

      // Build style options from supplied arguments
      styleArray = [true, {}];
      for(i = 0; i < arguments.length; i++)
         styleArray.push(arguments[i]);
      styleExtend = [ jQuery.extend.apply(jQuery, styleArray) ];

      // Loop through each named style inheritance
      while(typeof styleExtend[0].name == 'string')
      {
         // Sanitize style data and append to extend array
         styleExtend.unshift( sanitizeStyle(jQuery.fn.qtip.styles[ styleExtend[0].name ]) );
      };

      // Make sure resulting tooltip className represents final style
      styleExtend.unshift(true, {classes:{ tooltip: 'qtip-' + (arguments[0].name || 'defaults') }}, jQuery.fn.qtip.styles.defaults);

      // Extend into a single style object
      finalStyle = jQuery.extend.apply(jQuery, styleExtend);

      // Adjust tip size if needed (IE 1px adjustment bug fix)
      ieAdjust = (jQuery.browser.msie) ? 1 : 0;
      finalStyle.tip.size.width += ieAdjust;
      finalStyle.tip.size.height += ieAdjust;

      // Force even numbers for pixel precision
      if(finalStyle.tip.size.width % 2 > 0) finalStyle.tip.size.width += 1;
      if(finalStyle.tip.size.height % 2 > 0) finalStyle.tip.size.height += 1;

      // Sanitize final styles tip corner value
      if(finalStyle.tip.corner === true)
         finalStyle.tip.corner = (self.options.position.corner.tooltip === 'center') ? false : self.options.position.corner.tooltip;

      return finalStyle;
   };

   // Tip coordinates calculator
   function calculateTip(corner, width, height)
   {
      // Define tip coordinates in terms of height and width values
      var tips = {
         bottomRight:   [[0,0],              [width,height],      [width,0]],
         bottomLeft:    [[0,0],              [width,0],           [0,height]],
         topRight:      [[0,height],         [width,0],           [width,height]],
         topLeft:       [[0,0],              [0,height],          [width,height]],
         topMiddle:     [[0,height],         [width / 2,0],       [width,height]],
         bottomMiddle:  [[0,0],              [width,0],           [width / 2,height]],
         rightMiddle:   [[0,0],              [width,height / 2],  [0,height]],
         leftMiddle:    [[width,0],          [width,height],      [0,height / 2]]
      };
      tips.leftTop = tips.bottomRight;
      tips.rightTop = tips.bottomLeft;
      tips.leftBottom = tips.topRight;
      tips.rightBottom = tips.topLeft;

      return tips[corner];
   };

   // Border coordinates calculator
   function calculateBorders(radius)
   {
      var borders;

      // Use canvas element if supported
      if(jQuery('<canvas>').get(0).getContext)
      {
         borders = {
            topLeft: [radius,radius], topRight: [0,radius],
            bottomLeft: [radius,0], bottomRight: [0,0]
         };
      }

      // Canvas not supported - Use VML (IE)
      else if(jQuery.browser.msie)
      {
         borders = {
            topLeft: [-90,90,0], topRight: [-90,90,-radius],
            bottomLeft: [90,270,0], bottomRight: [90, 270,-radius]
         };
      };

      return borders;
   };

   // BGIFRAME JQUERY PLUGIN ADAPTION
   //   Special thanks to Brandon Aaron for this plugin
   //   http://plugins.jquery.com/project/bgiframe
   function bgiframe()
   {
      var self, html, dimensions;
      self = this;
      dimensions = self.getDimensions();

      // Setup iframe HTML string
      html = '<iframe class="qtip-bgiframe" frameborder="0" tabindex="-1" src="javascript:false" '+
         'style="display:block; position:absolute; z-index:-1; filter:alpha(opacity=\'0\'); border: 1px solid red; ' +
         'height:'+dimensions.height+'px; width:'+dimensions.width+'px" />';

      // Append the new HTML and setup element reference
      self.elements.bgiframe = self.elements.wrapper.prepend(html).children('.qtip-bgiframe:first');
   };

   // Assign cache and event initialisation on document load
   jQuery(document).ready(function()
   {
      // Setup library cache with window scroll and dimensions of document
      jQuery.fn.qtip.cache = {
         screen: {
            scroll: { left: jQuery(window).scrollLeft(), top: jQuery(window).scrollTop() },
            width: jQuery(window).width(),
            height: jQuery(window).height()
         }
      };

      // Adjust positions of the tooltips on window resize or scroll if enabled
      var adjustTimer;
      jQuery(window).bind('resize scroll', function(event)
      {
         clearTimeout(adjustTimer);
         adjustTimer = setTimeout(function()
         {
            // Readjust cached screen values
            if(event.type === 'scroll')
               jQuery.fn.qtip.cache.screen.scroll = { left: jQuery(window).scrollLeft(), top: jQuery(window).scrollTop() };
            else
            {
               jQuery.fn.qtip.cache.screen.width = jQuery(window).width();
               jQuery.fn.qtip.cache.screen.height = jQuery(window).height();
            };

            for(i = 0; i < jQuery.fn.qtip.interfaces.length; i++)
            {
               // Access current elements API
               var api = jQuery.fn.qtip.interfaces[i];

               // Update position if resize or scroll adjustments are enabled
               if(api.status.rendered === true
               && (api.options.position.type !== 'static'
               || api.options.position.adjust.scroll && event.type === 'scroll'
               || api.options.position.adjust.resize && event.type === 'resize'))
               {
                  // Queue the animation so positions are updated correctly
                  api.updatePosition(event, true);
               }
            };
         }
         , 100);
      })

      // Hide unfocus toolipts on document mousedown
      jQuery(document).bind('mousedown.qtip', function(event)
      {
         if(jQuery(event.target).parents('div.qtip').length === 0)
         {
            jQuery('.qtip[unfocus]').each(function()
            {
               var api = jQuery(this).qtip("api");

               // Only hide if its visible and not the tooltips target
               if(jQuery(this).is(':visible') && !api.status.disabled
               && jQuery(event.target).add(api.elements.target).length > 1)
                  api.hide(event);
            })
         };
      })
   });

   // Define qTip API interfaces array
   jQuery.fn.qtip.interfaces = []

   // Define log and constant place holders
   jQuery.fn.qtip.log = { error: function(){ return this; } };
   jQuery.fn.qtip.constants = {};

   // Define configuration defaults
   jQuery.fn.qtip.defaults = {
      // Content
      content: {
         prerender: false,
         text: false,
         url: false,
         data: null,
         title: {
            text: false,
            button: false
         }
      },
      // Position
      position: {
         target: false,
         corner: {
            target: 'bottomRight',
            tooltip: 'topLeft'
         },
         adjust: {
            x: 0, y: 0,
            mouse: true,
            screen: false,
            scroll: true,
            resize: true
         },
         type: 'absolute',
         container: false
      },
      // Effects
      show: {
         when: {
            target: false,
            event: 'mouseover'
         },
         effect: {
            type: 'fade',
            length: 100
         },
         delay: 140,
         solo: false,
         ready: false
      },
      hide: {
         when: {
            target: false,
            event: 'mouseout'
         },
         effect: {
            type: 'fade',
            length: 100
         },
         delay: 0,
         fixed: false
      },
      // Callbacks
      api: {
         beforeRender: function(){},
         onRender: function(){},
         beforePositionUpdate: function(){},
         onPositionUpdate: function(){},
         beforeShow: function(){},
         onShow: function(){},
         beforeHide: function(){},
         onHide: function(){},
         beforeContentUpdate: function(){},
         onContentUpdate: function(){},
         beforeContentLoad: function(){},
         onContentLoad: function(){},
         beforeTitleUpdate: function(){},
         onTitleUpdate: function(){},
         beforeDestroy: function(){},
         onDestroy: function(){},
         beforeFocus: function(){},
         onFocus: function(){}
      }
   };

   jQuery.fn.qtip.styles = {
      defaults: {
         background: 'white',
         color: '#111',
         overflow: 'hidden',
         textAlign: 'left',
         width: {
            min: 0,
            max: 250
         },
         padding: '5px 9px',
         border: {
            width: 1,
            radius: 0,
            color: '#d3d3d3'
         },
         tip: {
            corner: false,
            color: false,
            size: { width: 13, height: 13 },
            opacity: 1
         },
         title: {
            background: '#e1e1e1',
            fontWeight: 'bold',
            padding: '7px 12px'
         },
         button: {
            cursor: 'pointer'
         },
         classes: {
            target: '',
            tip: 'qtip-tip',
            title: 'qtip-title',
            button: 'qtip-button',
            content: 'qtip-content',
            active: 'qtip-active'
         }
      },
      cream: {
         border: {
            width: 3,
            radius: 0,
            color: '#F9E98E'
         },
         title: {
            background: '#F0DE7D',
            color: '#A27D35'
         },
         background: '#FBF7AA',
         color: '#A27D35',

         classes: { tooltip: 'qtip-cream' }
      },
      light: {
         border: {
            width: 3,
            radius: 0,
            color: '#E2E2E2'
         },
         title: {
            background: '#f1f1f1',
            color: '#454545'
         },
         background: 'white',
         color: '#454545',

         classes: { tooltip: 'qtip-light' }
      },
      dark: {
         border: {
            width: 3,
            radius: 0,
            color: '#303030'
         },
         title: {
            background: '#404040',
            color: '#f3f3f3'
         },
         background: '#505050',
         color: '#f3f3f3',

         classes: { tooltip: 'qtip-dark' }
      },
      red: {
         border: {
            width: 3,
            radius: 0,
            color: '#CE6F6F'
         },
         title: {
            background: '#f28279',
            color: '#9C2F2F'
         },
         background: '#F79992',
         color: '#9C2F2F',

         classes: { tooltip: 'qtip-red' }
      },
      green: {
         border: {
            width: 3,
            radius: 0,
            color: '#A9DB66'
         },
         title: {
            background: '#b9db8c',
            color: '#58792E'
         },
         background: '#CDE6AC',
         color: '#58792E',

         classes: { tooltip: 'qtip-green' }
      },
      blue: {
         border: {
            width: 3,
            radius: 0,
            color: '#ADD9ED'
         },
         title: {
            background: '#D0E9F5',
            color: '#5E99BD'
         },
         background: '#E5F6FE',
         color: '#4D9FBF',

         classes: { tooltip: 'qtip-blue' }
      }
   };
})(jQuery);
;
﻿/* 
	AddThisEvent v1.5 <http://addthisevent.com>
	Copyright (c) 2012-2012 Michael Nilsson
*/
eval(function(p,a,c,k,e,r){e=function(c){return(c<a?'':e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--)r[e(c)]=k[c]||e(c);k=[function(e){return r[e]}];e=function(){return'\\w+'};c=1};while(c--)if(k[c])p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c]);return p}('D $d(d){1l 7.5g(d)}5 1P=\'1b://6.14/34/3J-3o-3p.3v\';5 25=\'\';5 1U=L;5 1S=\'S\';5 1u=\'\';5 1W=\'4d 1T\';5 2g=\'4B 1T\';5 2e=\'3R 1T\';5 2R=\'3S 1T\';5 2b=\'5l 1T\';5 2I=\'43 48\';5 2A=S;5 2u=S;5 2t=S;5 2s=S;5 2r=S;5 2q=S;5 6=D(){5 C=L,30=4i,1V=1,1J=\'\',2j;1l{Y:D(){1s{1P=1P}1p(e){1P=\'1b://6.14/34/3J-3o-3p.3v\'}1s{25=4q}1p(e){}1s{1U=4w}1p(e){}1s{1S=4x}1p(e){}5 a=6.3q(25);5 b=7.1Y(\'*\');2J(5 d=0;d<b.1Z;d+=1){5 c=\'\';5 f=L;5 g=b[d].M;4(g==\'6\'){5 h=b[d].1Y(\'N\');2J(5 m=0;m<h.1Z;m+=1){4(h[m].M==\'2F\'){h[m].G.H=\'I\';5 i=h[m].R.2C(/ /2B,"");c+=\'&4H=\'+W(i)}4(h[m].M==\'2n\'){h[m].G.H=\'I\';c+=\'&4K=\'+W(h[m].R)}4(h[m].M==\'2m\'){h[m].G.H=\'I\';c+=\'&4O=\'+W(h[m].R)}4(h[m].M==\'2U\'){h[m].G.H=\'I\';c+=\'&4S=\'+W(h[m].R)}4(h[m].M==\'2H\'){h[m].G.H=\'I\';c+=\'&4W=\'+W(h[m].R)}4(h[m].M==\'2z\'){h[m].G.H=\'I\';c+=\'&4Y=\'+W(h[m].R)}4(h[m].M==\'2x\'){h[m].G.H=\'I\';c+=\'&52=\'+W(h[m].R)}4(h[m].M==\'2T\'){h[m].G.H=\'I\';c+=\'&3T=\'+W(h[m].R)}4(h[m].M==\'2k\'){h[m].G.H=\'I\';c+=\'&5j=\'+W(h[m].R)}4(h[m].M==\'2i\'){h[m].G.H=\'I\';c+=\'&5h=\'+W(h[m].R)}4(h[m].M==\'5f\'){h[m].G.H=\'I\';c+=\'&56=\'+W(h[m].R)}4(h[m].M==\'2p\'){4(h[m].R!=\'\'){h[m].G.H=\'I\';5 j=h[m].R.2C(/ /2B,"");c+=\'&51=\'+W(j);f=S}}}c=c.2C(/\'/2B,"´");5 k=\'\';4(2A){k+=\'<N 1m="4X" Z="6.1k(\\\'1f\\\',\\\'\'+c+\'\\\');">\'+1W+\'</N>\'}4(2u){k+=\'<N 1m="4T" Z="6.1k(\\\'1e\\\',\\\'\'+c+\'\\\');">\'+2g+\'</N>\'}4(2t){k+=\'<N 1m="4Q" Z="6.1k(\\\'1d\\\',\\\'\'+c+\'\\\');">\'+2e+\'</N>\'}4(2s){k+=\'<N 1m="4P" Z="6.1k(\\\'1h\\\',\\\'\'+c+\'\\\');">\'+2R+\'</N>\'}4(2r){k+=\'<N 1m="4N" Z="6.1k(\\\'1c\\\',\\\'\'+c+\'\\\');">\'+2b+\'</N>\'}4(f){4(2q){k+=\'<N Z="6.1k(\\\'1n\\\',\\\'\'+c+\'\\\');">\'+2I+\'</N>\'}}4(!a){k+=\'<1w 1m="3b"><1w 1m="3e"></1w><1w 1m="2o" Z="6.1k(\\\'3i\\\');">4I</1w></1w>\'}b[d].2w=\'3w\'+1V;b[d].M=\'6-8\';b[d].4F=\'\';4(1U){b[d].4E=D(){4D(2j);6.P(3N,\'1t\',\'1t\',S)};b[d].4C=D(){2j=2E("6.36();",37)};b[d].Z=D(){1l L}}F{b[d].Z=D(){6.P(3N,\'1t\',\'1t\');1l L}}5 l=b[d];5 n=7.2K(\'N\');n.2w=\'3w\'+1V+\'-8\';n.M=\'1a\';n.R=k;l.1R(n);1V++}}4(1S==\'L\'){6.2O()}F{6.3j(a)}},1k:D(f,a){5 b=\'\';5 c=2Q.2S;5 d=S;5 g=3P 4A();5 h=g.4z();4(f==\'1f\'){b=\'1b://Y.6.14/?1C=4y\'+a+\'&1A=\'+h+\'&1z=\'+c;d=L}4(f==\'1e\'){b=\'1b://Y.6.14/?1C=4v\'+a+\'&1A=\'+h+\'&1z=\'+c}4(f==\'1d\'){b=\'1b://Y.6.14/?1C=4u\'+a+\'&1A=\'+h+\'&1z=\'+c}4(f==\'1h\'){b=\'1b://Y.6.14/?1C=4t\'+a+\'&1A=\'+h+\'&1z=\'+c}4(f==\'1c\'){b=\'1b://Y.6.14/?1C=4s\'+a+\'&1A=\'+h+\'&1z=\'+c;d=L}4(f==\'1n\'){b=\'1b://Y.6.14/?1C=4r\'+a+\'&1A=\'+h+\'&1z=\'+c}4(f==\'3i\'){b=\'1b://6.14/\'}4(b!=\'\'){4(d){V.4p(b)}F{2Q.2S=b}}4(1u){2J(5 i=0;i<1u.1Z;i++){1s{4o(1u[i])}1p(e){4n(e.4m)}}}},3j:D(a){5 b;b=\'.6-8 {H:4l-1H;1y:3L;11-4k:4j;1i:#4h!1B;1j:#32 4f(\'+1P+\') 4e-4b 1Q 50%;J-1v:I!1B;O:K 13 #4a;1i:#3d;11-2v:3f;11-1I:3h;J-1v:I;1O:1Q 2y 3k 49;-1M-O-1F:1g;-1L-O-1F:1g;}\';b+=\'.6-8:2D {O:K 13 #47;1i:#3d;11-2v:3f;11-1I:3h;J-1v:I!1B;}\';b+=\'.6-8:46 {19:K;}\';b+=\'.6-3t {1j-1i:#3Z;}\';4(a){b+=\'.1a {1K:2G;1y:27;z-28:3z;1O:T T T T;1j:#2a;J-2M:U;H:I;2c-19:-1g;2c-U:-K;O-19:K 13 #3C;O-1r:K 13 #2d;O-3E:K 13 #3F;O-U:K 13 #2d;-1M-O-1F:1g;-1L-O-1F:1g;-1L-1G-1E:K 1D 1q 1x(0,0,0,0.15);-1M-1G-1E:K 1D 1q 1x(0,0,0,0.15);1G-1E:K 1D 1q 1x(0,0,0,0.15);}\'}F{b+=\'.1a {1K:2G;1y:27;z-28:3z;1O:1q T T T;1j:#2a;J-2M:U;H:I;2c-19:-1g;2c-U:-K;O-19:K 13 #3C;O-1r:K 13 #2d;O-3E:K 13 #3F;O-U:K 13 #2d;-1M-O-1F:1g;-1L-O-1F:1g;-1L-1G-1E:K 1D 1q 1x(0,0,0,0.15);-1M-1G-1E:K 1D 1q 1x(0,0,0,0.15);1G-1E:K 1D 1q 1x(0,0,0,0.15);}\'}b+=\'.1a N {1K:3Q;H:1H;2V:3O;3M-2f:3H%;1j:#2a;J-1v:I;11-1I:2y;1i:#2P;1O:3k 29 1Q 3U;}\';b+=\'.1a N:2D {1j:#32;1i:#2P;J-1v:I;11-1I:2y;}\';b+=\'.6 N {H:I!1B;}\';b+=\'.6-8 .2F,.6-8 .2n,.6-8 .2m,.6-8 .2U,.6-8 .2H,.6-8 .2z,.6-8 .2x,.6-8 .2T,.6-8 .2k,.6-8 .2p,.6-8 .2i {H:I!1B;}\';b+=\'.1a .3b {1K:2G;2f:3V;H:1H;1y:3L;2V:3W;}\';b+=\'.1a .3e {1K:3X;2f:K;3Y:3u;1j:#40;1y:27;z-28:41;U:29;19:1Q;}\';b+=\'.1a .2o {1y:27;19:42;2V:3O;1r:29;1O-U:29;11-G:3s;11-2v:3s;J-2M:1r;z-28:44;3M-2f:3H%;1j:#2a;J-1v:I;11-1I:1Q;1i:#45;}\';b+=\'.1a .2o:2D {1i:#2P;}\';5 c=7.2K("G");c.3r="J/2h";4(c.23){c.23.3l=b}F{c.1R(7.3c(b))}7.1Y("35")[0].1R(c)},2O:D(){1s{5 a=\'.6 {4c:3u;}\';a+=\'.6-8 .2F,.6-8 .2n,.6-8 .2m,.6-8 .2U,.6-8 .2H,.6-8 .2z,.6-8 .2x,.6-8 .2T,.6-8 .2k,.6-8 .2p,.6-8 .2i {H:I!1B;}\';5 b=7.2K("G");b.3r="J/2h";4(b.23){b.23.3l=a}F{b.1R(7.3c(a))}7.1Y("35")[0].1R(b)}1p(e){}},P:D(f,o,a,b){5 c=f.2w;5 d=$d(c);5 g=$d(c+\'-8\');4(d&&g){4(1J!=c){6.1X(1J)}5 h=6.33(g,\'H\');1s{f.4g()}1p(e){};4(h==\'1H\'){4(b){}F{6.1X(c)}}F{1J=c;d.M=\'6-8 6-3t\';d.G.2Z=30++;g.G.U=\'T\';g.G.19=\'T\';g.G.H=\'1H\';2E("6.2Y();",50);C=L;5 i=12(d.2W);5 j=12(d.3I);5 k=12(g.2W);5 l=12(g.3I);5 m=6.3G();5 n=m.3D(\'/\');5 p=12(n[0]);5 q=12(n[1]);5 r=12(n[2]);5 s=12(n[3]);5 t=6.3A(g);5 u=t.3D(\'/\');5 v=12(u[0]);5 w=12(u[1]);5 x=w+k;5 y=q+s;5 z=v+l;5 A=p+r;5 B=0,X=0;4(o==\'31\'&&a==\'U\'){B=\'T\';X=i+\'Q\'}F 4(o==\'3B\'&&a==\'U\'){B=\'T\';X=-k+\'Q\'}F 4(o==\'31\'&&a==\'1r\'){B=-(l-j)+\'Q\';X=i+\'Q\'}F 4(o==\'3B\'&&a==\'1r\'){B=-(l-j)+\'Q\';X=-k+\'Q\'}F 4(o==\'1t\'&&a==\'U\'){B=\'T\';4(x>y){X=-k+\'Q\'}F{X=i+\'Q\'}}F 4(o==\'1t\'&&a==\'1r\'){B=-(l-j)+\'Q\';4(x>y){X=-k+\'Q\'}F{X=i+\'Q\'}}F{4(x>y){X=-k+\'Q\'}F{X=i+\'Q\'}4(z>A){B=-(l-j)+\'Q\'}F{B=\'T\'}}g.G.U=B;g.G.19=X;4(7.21){7.21("4G",D(){6.1N(c)},L)}F 4(7.22){7.22("Z",D(){6.1N(c)})}F{7.Z=D(){6.1N(c)}}}}},1N:D(f){5 a=$d(f);5 b=$d(f+\'-8\');4(a&&b){4(C&&b.G.H==\'1H\'){2E("6.1X(\'"+f+"\');",37)}}},36:D(){6.1N(1J)},1X:D(f){5 a=$d(f);5 b=$d(f+\'-8\');4(a&&b){a.M=\'6-8\';b.G.H=\'I\';b.G.2Z=\'\'}},2Y:D(){C=S},3G:D(){5 w=0,h=0,y=0,x=0;4(4J(V.39)==\'4L\'){w=V.39;h=V.4M}F 4(7.16&&(7.16.24||7.16.26)){w=7.16.24;h=7.16.26}F 4(7.1o&&(7.1o.24||7.1o.26)){w=7.1o.24;h=7.1o.26}4(7.4R){x=(7.16.2N)?7.16.2N:7.1o.2N;y=(7.16.2L)?7.16.2L:7.1o.2L}F{x=V.4U;y=V.4V}1l w+\'/\'+h+\'/\'+x+\'/\'+y},3A:D(a){5 x=0,y=0;4(a.3a){x=a.2X;y=a.3K;4Z(a=a.3a){x+=a.2X;y+=a.3K}}1l x+\'/\'+y},33:D(a,b){5 x=a;5 y;4(x.3x){y=x.3x[b]}F 4(V.3n){y=7.53.3n(x,54).55(b)}1l y},3q:D(f){5 b=2Q.2S;5 c=S;5 d=f;5 e=d.1Z;4(e==20){5 a=d.2l(0,1);5 z=d.2l(9,10);5 m=d.2l(17,18);4(a!=\'a\'){c=L}4(z!=\'z\'){c=L}4(m!=\'m\'){c=L}}F{c=L}4(b.57(\'6.14\')==-1&&d==\'58\'){c=L}1l c},59:D(){6.Y()},5a:D(f){1u=f},5b:D(l,t){5 x=l.5c();4(x==\'1f\'){1W=t}4(x==\'1e\'){2g=t}4(x==\'1d\'){2e=t}4(x==\'1c\'){2b=t}4(x==\'5d\'){2I=t}},5e:D(c){4(c.38!=E){25=c.38}4(c.2h!=E){4(c.2h){1S=\'S\'}F{1S=\'L\'}}4(c.3y!=E){1U=c.3y}4(c.1f!=E){4(c.1f.P!=E){2A=c.1f.P}}4(c.1e!=E){4(c.1e.P!=E){2u=c.1e.P}}4(c.1d!=E){4(c.1d.P!=E){2t=c.1d.P}}4(c.1h!=E){4(c.1h.P!=E){2s=c.1h.P}}4(c.1c!=E){4(c.1c.P!=E){2r=c.1c.P}}4(c.1n!=E){4(c.1n.P!=E){2q=c.1n.P}}4(c.1f!=E){4(c.1f.J!=E){1W=c.1f.J}}4(c.1e!=E){4(c.1e.J!=E){2g=c.1e.J}}4(c.1d!=E){4(c.1d.J!=E){2e=c.1d.J}}4(c.1h!=E){4(c.1h.J!=E){2R=c.1h.J}}4(c.1c!=E){4(c.1c.J!=E){2b=c.1c.J}}4(c.1n!=E){4(c.1n.J!=E){5i=c.1n.J}}4(c.3g!=E){1u=c.3g}}}}();6.2O();4(V.21){V.21("5k",D(){6.Y()},L)}F 4(V.22){V.22("3m",D(){6.Y()})}F{V.3m=D(){6.Y()}}',62,332,'||||if|var|addthisevent|document|drop|||||||||||||||||||||||||||||||function|undefined|else|style|display|none|text|1px|false|className|span|border|show|px|innerHTML|true|0px|left|window|encodeURIComponent|dropy|generate|onclick||font|parseInt|solid|com||documentElement|||top|addthisevent_dropdown|http|ical|yahoo|google|outlook|2px|hotmail|color|background|cli|return|class|facebook|body|catch|6px|right|try|auto|_ate_callback|decoration|em|rgba|position|reference|offset|important|service|3px|shadow|radius|box|block|size|olddrop|width|webkit|moz|force|padding|_image_path|9px|appendChild|_ate_css|Calendar|_ate_mouse|dropzcx|_ate_lbl_outlook|hide|getElementsByTagName|length||addEventListener|attachEvent|styleSheet|clientWidth|_ate_license|clientHeight|absolute|index|10px|fff|_ate_lbl_ical|margin|bebebe|_ate_lbl_yahoo|height|_ate_lbl_google|css|_all_day_event|dropmousetim|_organizer_email|substring|_end|_start|frs|_facebook_event|_ate_show_facebook|_ate_show_ical|_ate_show_hotmail|_ate_show_yahoo|_ate_show_google|weight|id|_location|12px|_description|_ate_show_outlook|gi|replace|hover|setTimeout|_url|200px|_summary|_ate_lbl_fb_event|for|createElement|scrollTop|align|scrollLeft|trycss|6d84b4|location|_ate_lbl_hotmail|href|_organizer|_zonecode|cursor|offsetHeight|offsetLeft|tim|zIndex|dropzind|down|f4f4f4|getstyle|gfx|head|out|200|license|innerWidth|offsetParent|copyx|createTextNode|555|brx|bold|callback|14px|home|applycss|8px|cssText|onload|getComputedStyle|calendar|t1|glicense|type|normal|selected|hidden|png|atedrop|currentStyle|mouse|99999|elementposition|up|c8c8c8|split|bottom|a8a8a8|viewport|110|offsetWidth|icon|offsetTop|relative|line|this|pointer|new|175px|Yahoo|Hotmail|dorga|15px|21px|default|180px|overflow|f7f7f7|e0e0e0|100|5px|Facebook|101|cacaca|active|aab9d4|Event|35px|d9d9d9|repeat|visibility|Outlook|no|url|blur|333|999999|arial|family|inline|description|alert|eval|open|_license|FACEBOOK|ICAL|HOTMAIL|YAHOO|GOOGLE|_mouse|_css|OUTLOOK|getTimezoneOffset|Date|Google|onmouseout|clearTimeout|onmouseover|title|click|durl|AddThisEvent|typeof|dstart|number|innerHeight|ateical|dend|atehotmail|ateyahoo|all|dzone|ategoogle|pageXOffset|pageYOffset|dsum|ateoutlook|ddesc|while||fbevent|dloca|defaultView|null|getPropertyValue|dateformat|indexOf|aao8iuet5zp9iqw5sm9z|refresh|callcack|setlabel|toLowerCase|facebookevent|settings|_date_format|getElementById|dallday|_ate_lbl_facebook|dorgaem|load|iCal'.split('|'),0,{}));
/*
 * jQuery spritely 0.6
 * http://spritely.net/
 *
 * Documentation:
 * http://spritely.net/documentation/
 *
 * Copyright 2010-2011, Peter Chater, Artlogic Media Ltd, http://www.artlogic.net/
 * Dual licensed under the MIT or GPL Version 2 licenses.
 *
 * Change history:
 * Version 0.6
 *   - added events to the .sprite() method: on_first_frame, on_last_frame, on_frame:
 *     $('#sprite').sprite({
 *         fps: 9, 
 *         no_of_frames: 24, 
 *         on_first_frame: function(obj) {
 *             obj.spState(1); // change to state 1 (first row) on frame 1
 *         }, 
 *         on_last_frame: function(obj) {
 *             obj.spStop(); // stop the animation on the last frame
 *         },
 *         on_frame: {
 *             8: function(obj) {
 *                 obj.spState(2); // change to state 2 (row 2) on frame 8
 *             },
 *             16: function(obj) {
 *                 obj.spState(3); // change to state 3 (row 3) on frame 16
 *             }
 *         }
 *     });
 *   - added start_at_frame: $('#sprite').sprite({fps: 9, no_of_frames: 24, start_at_frame: 8});
 * Version 0.5
 *   - added a 'destroy()' method which will stop the element's sprite behaviour, without actually removing the element. Example: $('#my_sprite').destroy()
 * Version 0.4
 *   - add up/down for 'pan' method. <ricky.hewitt@artlogic.net>
 *   - added 'dir' option for Sprites. This means a Sprite can be played in reverse.
 *   - removed alert message regarding jQuery.draggable (now uses console.log, if available) <ricky.hewitt@artlogic.net>
 * Version 0.3b
 *   - added lockTo method for locking sprites to background images. $('#sprite').lockTo('#background, {'left': 380, 'top': -60, 'bg_img_width': 1110});
 * Version 0.2.1
 *   - animate function will stop cycling after play_frames has completed
 * Version 0.2 beta
 *   - added isDraggable method (requires jquery-ui) $('#sprite').sprite().isDraggable({start: null, stop: function() {alert('Ouch! You dropped me!')});
 *   - sprites may be set to play a limited number of frames when instantiated, e.g. $('#sprite').sprite({fps: 9, no_of_frames: 3, play_frames: 30})
 *   - sprite speed may be controlled at any point by setting the frames-per-second $('#sprite').fps(20);
 *   - sprites with multiple rows of frames may have there 'state' changed, e.g. to make the second row of frames
 *     active, use: $('#sprite').spState(2); - to return to the first row, use $('#sprite').spState(1);
 *   - background element speed may be controlled at any point with .spSpeed(), e.g. $('#bg1').spSpeed(10)
 *   - background elements may be set to a depth where 100 is the viewer (up close) and 0 is the horizon, e.g.:
 *     $('#bg1').pan({fps: 30, speed: 2, dir: 'left', depth: 30});
 *     $('#bg2').pan({fps: 30, speed: 3, dir: 'left', depth: 70});
 *     relative speed of backgrounds may now be set in a single action with $('#bg1, #bg2').spRelSpeed(20);
 *     which will make elements closer to the horizon (lower depths) move slower than closer elements (higher depths)
 */

(function($) {
    $._spritely = {
        // shared methods and variables used by spritely plugin
        animate: function(options) {
            var el = $(options.el);
            var el_id = el.attr('id');
            if (!$._spritely.instances[el_id]) {
                return this;
            }
            options = $.extend(options, $._spritely.instances[el_id] || {});
            if (options.play_frames && !$._spritely.instances[el_id]['remaining_frames']) {
                $._spritely.instances[el_id]['remaining_frames'] = options.play_frames + 1;
            }
            if (options.type == 'sprite' && options.fps) {
                var frames;
                var animate = function(el) {
                    var w = options.width, h = options.height;
                    if (!frames) {
                        frames = [];
                        total = 0
                        for (var i = 0; i < options.no_of_frames; i ++) {
                            frames[frames.length] = (0 - total);
                            total += w;
                        }
                    }
                    if ($._spritely.instances[el_id]['current_frame'] == 0) {
                        if (options.on_first_frame) {
                            options.on_first_frame(el);
                        }
                    } else if ($._spritely.instances[el_id]['current_frame'] == frames.length - 1) {
                        if (options.on_last_frame) {
                            options.on_last_frame(el);
                        }
                    }
                    if (options.on_frame && options.on_frame[$._spritely.instances[el_id]['current_frame']]) {
                        options.on_frame[$._spritely.instances[el_id]['current_frame']](el);
                    }
                    if (options.rewind == true) {
                        if ($._spritely.instances[el_id]['current_frame'] <= 0) {
                            $._spritely.instances[el_id]['current_frame'] = frames.length - 1;
                        } else {
                            $._spritely.instances[el_id]['current_frame'] = $._spritely.instances[el_id]['current_frame'] - 1;
                        };
                    } else {
                        if ($._spritely.instances[el_id]['current_frame'] >= frames.length - 1) {
                            $._spritely.instances[el_id]['current_frame'] = 0;
                        } else {
                            $._spritely.instances[el_id]['current_frame'] = $._spritely.instances[el_id]['current_frame'] + 1;
                        }
                    }

                    var yPos = $._spritely.getBgY(el);
                    el.css('background-position', frames[$._spritely.instances[el_id]['current_frame']] + 'px ' + yPos);
                    if (options.bounce && options.bounce[0] > 0 && options.bounce[1] > 0) {
                        var ud = options.bounce[0]; // up-down
                        var lr = options.bounce[1]; // left-right
                        var ms = options.bounce[2]; // milliseconds
                        el
                            .animate({top: '+=' + ud + 'px', left: '-=' + lr + 'px'}, ms)
                            .animate({top: '-=' + ud + 'px', left: '+=' + lr + 'px'}, ms);
                    }
                }
                if ($._spritely.instances[el_id]['remaining_frames'] && $._spritely.instances[el_id]['remaining_frames'] > 0) {
                    $._spritely.instances[el_id]['remaining_frames'] --;
                    if ($._spritely.instances[el_id]['remaining_frames'] == 0) {
                        $._spritely.instances[el_id]['remaining_frames'] = -1;
                        delete $._spritely.instances[el_id]['remaining_frames'];
                        return;
                    } else {
                        animate(el);
                    }
                } else if ($._spritely.instances[el_id]['remaining_frames'] != -1) {
                    animate(el);
                }
            } else if (options.type == 'pan') {
                if (!$._spritely.instances[el_id]['_stopped']) {
                                        if (options.dir == 'up') {
                                            $._spritely.instances[el_id]['l'] = $._spritely.getBgX(el).replace('px', '');
                                            $._spritely.instances[el_id]['t'] = ($._spritely.instances[el_id]['t'] - (options.speed || 1)) || 0;
                                        }
                                        else if (options.dir == 'down') {
                                            $._spritely.instances[el_id]['l'] = $._spritely.getBgX(el).replace('px', '');
                                            $._spritely.instances[el_id]['t'] = ($._spritely.instances[el_id]['t'] + (options.speed || 1)) || 0;
                                        }
                    else if (options.dir == 'left') {
                        $._spritely.instances[el_id]['l'] = ($._spritely.instances[el_id]['l'] - (options.speed || 1)) || 0;
                                                $._spritely.instances[el_id]['t'] = $._spritely.getBgY(el).replace('px', '');
                    } else {
                        $._spritely.instances[el_id]['l'] = ($._spritely.instances[el_id]['l'] + (options.speed || 1)) || 0;
                                                $._spritely.instances[el_id]['t'] = $._spritely.getBgY(el).replace('px', '');
                    }

                                        // When assembling the background-position string, care must be taken
                                        // to ensure correct formatting.. <ricky.hewitt@artlogic.net>
                                        var bg_left = $._spritely.instances[el_id]['l'].toString();
                                        if (bg_left.indexOf('%') == -1) {
                                            bg_left += 'px ';
                                        } else { bg_left += ' '; }

                                        var bg_top = $._spritely.instances[el_id]['t'].toString();
                                        if (bg_top.indexOf('%') == -1) {
                                            bg_top += 'px ';
                                        } else { bg_top += ' '; }

                    $(el).css('background-position', bg_left + bg_top);
                }
            }
            $._spritely.instances[el_id]['options'] = options;
            window.setTimeout(function() {
                $._spritely.animate(options);
            }, parseInt(1000 / options.fps));
        },
        randomIntBetween: function(lower, higher) {
            return parseInt(rand_no = Math.floor((higher - (lower - 1)) * Math.random()) + lower);
        },
        getBgY: function(el) {
            if ($.browser.msie) {
                // fixme - the background-position property does not work
                // correctly in IE so we have to hack it here... Not ideal
                // especially as $.browser is depricated
                var bgY = $(el).css('background-position-y') || '0';
            } else {
                var bgY = ($(el).css('background-position') || ' ').split(' ')[1];
            }
            return bgY;
        },
        getBgX: function(el) {
            if ($.browser.msie) {
                // see note, above
                var bgX = $(el).css('background-position-x') || '0';
            } else {
                var bgX = ($(el).css('background-position') || ' ').split(' ')[0];
            }
            return bgX;
        },
        get_rel_pos: function(pos, w) {
            // return the position of an item relative to a background
            // image of width given by w
            var r = pos;
            if (pos < 0) {
                while (r < 0) {
                    r += w;
                }
            } else {
                while (r > w) {
                    r -= w;
                }
            }
            return r;
        }
    };
    $.fn.extend({
        spritely: function(options) {
            var options = $.extend({
                type: 'sprite',
                do_once: false,
                width: null,
                height: null,
                fps: 12,
                no_of_frames: 2,
                stop_after: null
            }, options || {});
            var el_id = $(this).attr('id');
            if (!$._spritely.instances) {
                $._spritely.instances = {};
            }
            if (!$._spritely.instances[el_id]) {
                if (options.start_at_frame) {
                    $._spritely.instances[el_id] = {current_frame: options.start_at_frame - 1};
                } else {
                    $._spritely.instances[el_id] = {current_frame: -1};
                }
            }
            $._spritely.instances[el_id]['type'] = options.type;
            $._spritely.instances[el_id]['depth'] = options.depth;
            options.el = this;
            options.width = options.width || $(this).width() || 100;
            options.height = options.height || $(this).height() || 100;
            var get_rate = function() {
                return parseInt(1000 / options.fps);
            }
            if (!options.do_once) {
                window.setTimeout(function() {
                    $._spritely.animate(options);
                }, get_rate(options.fps));
            } else {
                $._spritely.animate(options);
            }
            return this; // so we can chain events
        },
        sprite: function(options) {
            var options = $.extend({
                type: 'sprite',
                bounce: [0, 0, 1000] // up-down, left-right, milliseconds
            }, options || {});
            return $(this).spritely(options);
        },
        pan: function(options) {
            var options = $.extend({
                type: 'pan',
                dir: 'left',
                continuous: true,
                speed: 1 // 1 pixel per frame
            }, options || {});
            return $(this).spritely(options);
        },
        flyToTap: function(options) {
            var options = $.extend({
                el_to_move: null,
                type: 'moveToTap',
                ms: 1000, // milliseconds
                do_once: true
            }, options || {});
            if (options.el_to_move) {
                $(options.el_to_move).active();
            }
            if ($._spritely.activeSprite) {
                if (window.Touch) { // iphone method see http://cubiq.org/remove-onclick-delay-on-webkit-for-iphone/9 or http://www.nimblekit.com/tutorials.html for clues...
                    $(this)[0].ontouchstart = function(e) {
                        var el_to_move = $._spritely.activeSprite;
                        var touch = e.touches[0];
                        var t = touch.pageY - (el_to_move.height() / 2);
                        var l = touch.pageX - (el_to_move.width() / 2);
                        el_to_move.animate({
                            top: t + 'px',
                            left: l + 'px'
                        }, 1000);
                    };
                } else {
                    $(this).click(function(e) {
                        var el_to_move = $._spritely.activeSprite;
                        $(el_to_move).stop(true);
                        var w = el_to_move.width();
                        var h = el_to_move.height();
                        var l = e.pageX - (w / 2);
                        var t = e.pageY - (h / 2);
                        el_to_move.animate({
                            top: t + 'px',
                            left: l + 'px'
                        }, 1000);
                    });
                }
            }
            return this;
        },
        // isDraggable requires jQuery ui
        isDraggable: function(options) {
            if ((!$(this).draggable)) {
                //console.log('To use the isDraggable method you need to load jquery-ui.js');
                return this;
            }
            var options = $.extend({
                type: 'isDraggable',
                start: null,
                stop: null,
                drag: null
            }, options || {});
            var el_id = $(this).attr('id');
            if (!$._spritely.instances[el_id]) {
                return this;
            }
            $._spritely.instances[el_id].isDraggableOptions = options;
            $(this).draggable({
                start: function() {
                    var el_id = $(this).attr('id');
                    $._spritely.instances[el_id].stop_random = true;
                    $(this).stop(true);
                    if ($._spritely.instances[el_id].isDraggableOptions.start) {
                        $._spritely.instances[el_id].isDraggableOptions.start(this);
                    }
                },
                drag: options.drag,
                stop: function() {
                    var el_id = $(this).attr('id');
                    $._spritely.instances[el_id].stop_random = false;
                    if ($._spritely.instances[el_id].isDraggableOptions.stop) {
                        $._spritely.instances[el_id].isDraggableOptions.stop(this);
                    }
                }
            });
            return this;
        },
        active: function() {
            // the active sprite
            $._spritely.activeSprite = this;
            return this;
        },
        activeOnClick: function() {
            // make this the active script if clicked...
            var el = $(this);
            if (window.Touch) { // iphone method see http://cubiq.org/remove-onclick-delay-on-webkit-for-iphone/9 or http://www.nimblekit.com/tutorials.html for clues...
                el[0].ontouchstart = function(e) {
                    $._spritely.activeSprite = el;
                };
            } else {
                el.click(function(e) {
                    $._spritely.activeSprite = el;
                });
            }
            return this;
        },
        spRandom: function(options) {
            var options = $.extend({
                top: 50,
                left: 50,
                right: 290,
                bottom: 320,
                speed: 4000,
                pause: 0
            }, options || {});
            var el_id = $(this).attr('id');
            if (!$._spritely.instances[el_id]) {
                return this;
            }
            if (!$._spritely.instances[el_id].stop_random) {
                var r = $._spritely.randomIntBetween;
                var t = r(options.top, options.bottom);
                var l = r(options.left, options.right);
                $('#' + el_id).animate({
                    top: t + 'px',
                    left: l + 'px'
                }, options.speed)
            }
            window.setTimeout(function() {
                $('#' + el_id).spRandom(options);
            }, options.speed + options.pause)
            return this;
        },
        makeAbsolute: function() {
            // remove an element from its current position in the DOM and
            // position it absolutely, appended to the body tag.
            return this.each(function() {
                var el = $(this);
                var pos = el.position();
                el.css({position: "absolute", marginLeft: 0, marginTop: 0, top: pos.top, left: pos.left })
                    .remove()
                    .appendTo("body");
            });

        },
        spSet: function(prop_name, prop_value) {
            var el_id = $(this).attr('id');
            $._spritely.instances[el_id][prop_name] = prop_value;
            return this;
        },
        spGet: function(prop_name, prop_value) {
            var el_id = $(this).attr('id');
            return $._spritely.instances[el_id][prop_name];
        },
        spStop: function(bool) {
            $(this).each(function() {
                var el_id = $(this).attr('id');
                $._spritely.instances[el_id]['_last_fps'] = $(this).spGet('fps');
                $._spritely.instances[el_id]['_stopped'] = true;
                $._spritely.instances[el_id]['_stopped_f1'] = bool;
                if ($._spritely.instances[el_id]['type'] == 'sprite') {
                    $(this).spSet('fps', 0);
                }
                if (bool) {
                    // set background image position to 0
                    var bp_top = $._spritely.getBgY($(this));
                    $(this).css('background-position', '0 ' + bp_top);
                }
            });
            return this;
        },
        spStart: function() {
            $(this).each(function() {
                var el_id = $(this).attr('id');
                var fps = $._spritely.instances[el_id]['_last_fps'] || 12;
                $._spritely.instances[el_id]['_stopped'] = false;
                if ($._spritely.instances[el_id]['type'] == 'sprite') {
                    $(this).spSet('fps', fps);
                }
            });
            return this;
        },
        spToggle: function() {
            var el_id = $(this).attr('id');
            var stopped = $._spritely.instances[el_id]['_stopped'] || false;
            var stopped_f1 = $._spritely.instances[el_id]['_stopped_f1'] || false;
            if (stopped) {
                $(this).spStart();
            } else {
                $(this).spStop(stopped_f1);
            }
            return this;
        },
        fps: function(fps) {
            $(this).each(function() {
                $(this).spSet('fps', fps);
            });
            return this;
        },
        spSpeed: function(speed) {
            $(this).each(function() {
                $(this).spSet('speed', speed);
            });
            return this;
        },
        spRelSpeed: function(speed) {
            $(this).each(function() {
                var rel_depth = $(this).spGet('depth') / 100;
                $(this).spSet('speed', speed * rel_depth);
            });
            return this;
        },
        spChangeDir: function(dir) {
            $(this).each(function() {
                $(this).spSet('dir', dir);
            });
            return this;
        },
        spState: function(n) {
            $(this).each(function() {
                // change state of a sprite, where state is the vertical
                // position of the background image (e.g. frames row)
                var yPos = ((n - 1) * $(this).height()) + 'px';
                var xPos = $._spritely.getBgX($(this));
                var bp = xPos + ' -' + yPos;
                $(this).css('background-position', bp);
            });
            return this;
        },
        lockTo: function(el, options) {
            $(this).each(function() {
                var el_id = $(this).attr('id');
                if (!$._spritely.instances[el_id]) {
                    return this;
                }
                $._spritely.instances[el_id]['locked_el'] = $(this);
                $._spritely.instances[el_id]['lock_to'] = $(el);
                $._spritely.instances[el_id]['lock_to_options'] = options;
                window.setInterval(function() {
                    if ($._spritely.instances[el_id]['lock_to']) {
                        var locked_el = $._spritely.instances[el_id]['locked_el'];
                        var locked_to_el = $._spritely.instances[el_id]['lock_to'];
                        var locked_to_options = $._spritely.instances[el_id]['lock_to_options'];
                        var locked_to_el_w = locked_to_options.bg_img_width;
                        var locked_to_el_h = locked_to_el.height();
                        var locked_to_el_y = $._spritely.getBgY(locked_to_el);
                        var locked_to_el_x = $._spritely.getBgX(locked_to_el);
                        var el_l = (parseInt(locked_to_el_x) + parseInt(locked_to_options['left']));
                        var el_t = (parseInt(locked_to_el_y) + parseInt(locked_to_options['top']));
                        el_l = $._spritely.get_rel_pos(el_l, locked_to_el_w);
                        $(locked_el).css({
                            'top': el_t + 'px',
                            'left': el_l + 'px'
                        });
                    }
                }, options.interval || 20);
            });
            return this;
        },
        destroy: function() {
            var el = $(this);
            var el_id = $(this).attr('id');
            delete $._spritely.instances[el_id]
            return this;
        }
    })
})(jQuery);
// Stop IE6 re-loading background images continuously
try {
  document.execCommand("BackgroundImageCache", false, true);
} catch(err) {} ;
(function($, undefined) {
		
  /**
   * Slider object.
   */
  $.Slider 				= function( options, element ) {
  
    this.$el	= $( element );
    
    this._init( options );
    
  };
  
  $.Slider.defaults 		= {
    current		: 0, 	// index of current slide
    bgincrement	: 50,	// increment the bg position (parallax effect) when sliding
    autoplay	: false,// slideshow on / off
    interval	: 4000  // time between transitions
    };
  
  $.Slider.prototype 	= {
    _init 				: function( options ) {
      
      this.options 		= $.extend( true, {}, $.Slider.defaults, options );
      
      this.$slides		= this.$el.children('div.da-slide');
      this.slidesCount	= this.$slides.length;
      
      this.current		= this.options.current;
      
      if( this.current < 0 || this.current >= this.slidesCount ) {
      
        this.current	= 0;
      
      }
      
      this.$slides.eq( this.current ).addClass( 'da-slide-current' );
      
      var $navigation		= $( '<nav class="da-dots"/>' );
      for( var i = 0; i < this.slidesCount; ++i ) {
      
        $navigation.append( '<span/>' );
      
      }
      $navigation.appendTo( this.$el );
      
      this.$pages			= this.$el.find('nav.da-dots > span');
      this.$navNext		= this.$el.find('span.da-arrows-next');
      this.$navPrev		= this.$el.find('span.da-arrows-prev');
      
      this.isAnimating	= false;
      
      this.bgpositer		= 0;
      
      this.cssAnimations	= Modernizr.cssanimations;
      this.cssTransitions	= Modernizr.csstransitions;
      
      if( !this.cssAnimations || !this.cssAnimations ) {
        
        this.$el.addClass( 'da-slider-fb' );
      
      }
      
      this._updatePage();
      
      // load the events
      this._loadEvents();
      
      // slideshow
      if( this.options.autoplay ) {
      
        this._startSlideshow();
      
      }
      
    },
    _navigate			: function( page, dir ) {
      
      var $current	= this.$slides.eq( this.current ), $next, _self = this;
      
      if( this.current === page || this.isAnimating ) return false;
      
      this.isAnimating	= true;
      
      // check dir
      var classTo, classFrom, d;
      
      if( !dir ) {
      
        ( page > this.current ) ? d = 'next' : d = 'prev';
      
      }
      else {
      
        d = dir;
      
      }
        
      if( this.cssAnimations && this.cssAnimations ) {
        
        if( d === 'next' ) {
        
          classTo		= 'da-slide-toleft';
          classFrom	= 'da-slide-fromright';
          ++this.bgpositer;
        
        }
        else {
        
          classTo		= 'da-slide-toright';
          classFrom	= 'da-slide-fromleft';
          --this.bgpositer;
        
        }
        
        this.$el.css( 'background-position' , this.bgpositer * this.options.bgincrement + '% 0%' );
      
      }
      
      this.current	= page;
      
      $next			= this.$slides.eq( this.current );
      
      if( this.cssAnimations && this.cssAnimations ) {
      
        var rmClasses	= 'da-slide-toleft da-slide-toright da-slide-fromleft da-slide-fromright';
        $current.removeClass( rmClasses );
        $next.removeClass( rmClasses );
        
        $current.addClass( classTo );
        $next.addClass( classFrom );
        
        $current.removeClass( 'da-slide-current' );
        $next.addClass( 'da-slide-current' );
        
      }
      
      // fallback
      if( !this.cssAnimations || !this.cssAnimations ) {
        
        $next.css( 'left', ( d === 'next' ) ? '100%' : '-100%' ).stop().animate( {
          left : '0%'
        }, 1000, function() { 
          _self.isAnimating = false; 
        });
        
        $current.stop().animate( {
          left : ( d === 'next' ) ? '-100%' : '100%'
        }, 1000, function() { 
          $current.removeClass( 'da-slide-current' ); 
        });
        
      }
      
      this._updatePage();
      
    },
    _updatePage			: function() {
    
      this.$pages.removeClass( 'da-dots-current' );
      this.$pages.eq( this.current ).addClass( 'da-dots-current' );
    
    },
    _startSlideshow		: function() {
    
      var _self	= this;
      
      this.slideshow	= setTimeout( function() {
        
        var page = ( _self.current < _self.slidesCount - 1 ) ? page = _self.current + 1 : page = 0;
        _self._navigate( page, 'next' );
        
        if( _self.options.autoplay ) {
        
          _self._startSlideshow();
        
        }
      
      }, this.options.interval );
    
    },
    page				: function( idx ) {
      
      if( idx >= this.slidesCount || idx < 0 ) {
      
        return false;
      
      }
      
      if( this.options.autoplay ) {
      
        clearTimeout( this.slideshow );
        this.options.autoplay	= false;
      
      }
      
      this._navigate( idx );
      
    },
    _loadEvents			: function() {
      
      var _self = this;
      
      this.$pages.on( 'click.cslider', function( event ) {
        
        _self.page( $(this).index() );
        return false;
        
      });
      
      this.$navNext.on( 'click.cslider', function( event ) {
        
        if( _self.options.autoplay ) {
        
          clearTimeout( _self.slideshow );
          _self.options.autoplay	= false;
        
        }
        
        var page = ( _self.current < _self.slidesCount - 1 ) ? page = _self.current + 1 : page = 0;
        _self._navigate( page, 'next' );
        return false;
        
      });
      
      this.$navPrev.on( 'click.cslider', function( event ) {
        
        if( _self.options.autoplay ) {
        
          clearTimeout( _self.slideshow );
          _self.options.autoplay	= false;
        
        }
        
        var page = ( _self.current > 0 ) ? page = _self.current - 1 : page = _self.slidesCount - 1;
        _self._navigate( page, 'prev' );
        return false;
        
      });
      
      if( this.cssTransitions ) {
      
        if( !this.options.bgincrement ) {
          
          this.$el.on( 'webkitAnimationEnd.cslider animationend.cslider OAnimationEnd.cslider', function( event ) {
            
            if( event.originalEvent.animationName === 'toRightAnim4' || event.originalEvent.animationName === 'toLeftAnim4' ) {
              
              _self.isAnimating	= false;
            
            }	
            
          });
          
        }
        else {
        
          this.$el.on( 'webkitTransitionEnd.cslider transitionend.cslider OTransitionEnd.cslider', function( event ) {
          
            if( event.target.id === _self.$el.attr( 'id' ) )
              _self.isAnimating	= false;
            
          });
        
        }
      
      }
      
    }
  };
  
  var logError 			= function( message ) {
    if ( this.console ) {
      console.error( message );
    }
  };
  
  $.fn.cslider			= function( options ) {
  
  if ( typeof options === 'string' ) {
    
    var args = Array.prototype.slice.call( arguments, 1 );
    
    this.each(function() {
    
      var instance = $.data( this, 'cslider' );
      
      if ( !instance ) {
        logError( "cannot call methods on cslider prior to initialization; " +
        "attempted to call method '" + options + "'" );
        return;
      }
      
      if ( !$.isFunction( instance[options] ) || options.charAt(0) === "_" ) {
        logError( "no such method '" + options + "' for cslider instance" );
        return;
      }
      
      instance[ options ].apply( instance, args );
    
    });
  
  } 
  else {
    this.each(function() {
      var instance = $.data( this, 'cslider' );
      if ( !instance ) {
        $.data( this, 'cslider', new $.Slider( options, this ) );
      }
    });
  }
    
  return this;

  };
})(jQuery);;
(function ($, window, document) {
  $(function (){ // starts all functions

    // Home Page
    
    $('#da-slider').cslider({
      autoplay  : true,
      bgincrement : 0,
      interval  : 8000
    });
    
    /*$('#da-slider-quotes').cslider({
      autoplay  : true,
      bgincrement : 0,
    });*/
    
    $(".logos_home").click(function(){
      window.location="/customers";
    }
    );
    
    $('.logos_home img').first().show();
                setInterval(function(){
                        $('.logos_home :first-child')
                            .next('img').fadeIn(400)
                            .end().appendTo('.logos_home')
                            .fadeOut(1200);},
                    4000);
    
    $('.inner_quotes .quote').first().show();
                setInterval(function(){
                        $('.inner_quotes .quote').first()
                            .fadeOut(300, function () {
                                $(this).next('.quote').fadeIn(300)
                                    .end().appendTo('.inner_quotes');
                            })
                            },
                    7000);
    
    // Team
    
                var $items = $('#members>div');
                $items.click(function() {
                    $items.removeClass('selected');
                    $(this).addClass('selected');
    
                    var index = $items.index($(this));
                    $('#sidebar_left>div').hide().eq(index).show();
                }).eq(0).click();
    
    // Tips
    
      $('#content table a[href][title]').qtip({
          position: {
             corner: {
                target: 'rightMiddle',
                tooltip: 'leftMiddle'
             }
          },
          style: {
             name: 'dark',
         background: '#000',
             paddingTop: '9px',
         paddingRight: '15px',
         paddingBottom: '9px',
         paddingLeft: '15px',
             fontWeight: 'bold',
             fontSize: '12px',
         color: 'white',
             lineHeight: '18px',
         border: {
           color: '#000'
           },
             width: {
                max: 200,
                min: 0
             },
             tip:{corner:'leftMiddle',size: { x:8,y:14 }}
          },
          show: {
             delay: 5
          }
       
       });
    
    // Gallery Cover
             
        $('.cover').mosaic({
            animation   :   'slide',    //fade or slide
            hover_x     :   '300px'     //Horizontal position on hover
        });
    
    // Gallery Fade
             
        $('.fade').mosaic({
            animation   :   'fade',    //fade or slide
            hover_x     :   '300px'     //Horizontal position on hover
        });
        
    
    // Awards Filter
      
        jQuery(".tab:not(:first)").hide();
    
        //to fix u know who
        jQuery(".tab:first").show();
      
        jQuery(".tabnav a").click(function(){
            stringref = jQuery(this).attr("href").split('#')[1];
    
            jQuery('.tab:not(#'+stringref+')').hide();
    
            if (jQuery.browser.msie && jQuery.browser.version.substr(0,3) == "6.0") {
                jQuery('.tab#' + stringref).show();
            }
            else 
                jQuery('.tab#' + stringref).fadeIn();
        
            return false;
        });	
    
    // Content slider > Minimal
        $(".contentslider-std").sliderkit({
            auto:1,
            panelfxspeed: 1200,
            autospeed: 5000,
            tabs:0,
            circular:1,
            panelfx:"sliding",
            //panelfxfirst:"fading",
            panelfxeasing:"easeInOutExpo",
            fastchange:0,
            keyboard:1
    });
    
    // ESB Logo Slider
    
        $("#slider_logos").easySlider({
            auto: true,
            controlsShow: false,
            speed: 2000,
            pause: 5000,
            lastShow: false,		
            vertical: false,
            continuous: true
        });
    
    // Accordion
    
    function accordionify (headerId, contentId) {
        $(headerId).click(function() {
            if($(this).next("div").is(":visible")) {
                $(this).next("div").slideUp("slow", function() {
                    $(headerId).css("border-bottom", "none");
                    $(headerId).css("padding-bottom", "30px");
                });
    
            } else {
                $(headerId).css("border-bottom", "1px solid #cacaca");
                $(this).css("padding-bottom", "0px");
                $(contentId).css("padding-bottom", "0px");
                $(this).next("div").slideToggle("slow", function() {
                });
            }
        });
    }
    
    
    accordionify('#certification-header', '#certification-content');
    accordionify('#region-header', '#region-content');
    accordionify('#country-header', '#country-content');
    
    //Hide featured partners in search results
    
    if(document.URL.indexOf('partner-finder?title') != -1)
        $('.view-partner-finder-featured').hide();
    
    
    
    // Summit Clouds
    
      $('#far-clouds').pan({fps: 30, speed: 0.5, dir: 'left', depth: 30});
                    $('#near-clouds').pan({fps: 30, speed: 0.5, dir: 'left', depth: 70});
                    
                    window.actions = {
                        speedyClouds: function(){
                            $('#far-clouds').spSpeed(12);
                            $('#near-clouds').spSpeed(20);
                        },
                        runningClouds: function(){
                            $('#far-clouds').spSpeed(8);
                            $('#near-clouds').spSpeed(12);
                        },
                        walkingClouds: function(){
                            $('#far-clouds').spSpeed(3);
                            $('#near-clouds').spSpeed(5);
                        },
                        lazyClouds: function(){
                            $('#far-clouds').spSpeed(0.7);
                            $('#near-clouds').spSpeed(1);
                        },
                        stop: function(){
                            $('#far-clouds, #near-clouds').spStop();
                        },
                        start: function(){
                            $('#far-clouds, #near-clouds').spStart();
                        },
                        toggle: function(){
                            $('#far-clouds, #near-clouds').spToggle();
                        },
                        left: function(){
                            $('#far-clouds, #near-clouds').spChangeDir('left');                    
                        },
                        right: function(){
                            $('#far-clouds, #near-clouds').spChangeDir('right');                    
                        }
                    };
        
    // Summit Calendar
    
        addthisevent.settings({
          license   : "a4e2mhrlrznxbcp70mjx",
          mouse     : false,
          css       : true,
          outlook   : {show:true, text:"Outlook Calendar"},
          google    : {show:true, text:"Google Calendar"},
          yahoo     : {show:true, text:"Yahoo Calendar"},
          hotmail   : {show:true, text:"Hotmail Calendar"},
          ical      : {show:true, text:"iCal Calendar"},
          facebook  : {show:true, text:"Facebook Event"},
          callback  : ""
    });
        
    // Careers vTicker
    
        $('#phrases').vTicker({ 
        speed: 500,
        pause: 4000,
        animation: 'fade',
        mousePause: false,
        showItems: 1
      });
    
    // Tabs
    
      //  Main function that shows and hides the requested tabs and their content
      var set_tab = function(tab_container_id, tab_id){
        //  Remove class "active" from currently active tab
        $('#' + tab_container_id + ' ul li').removeClass('active');
    
        //  Now add class "active" to the selected/clicked tab
        $('#' + tab_container_id + ' a[rel="'+tab_id+'"]').parent().addClass("active");
    
        //  Hide contents for all the tabs.
        //  The '_content' part is merged with tab_container_id and the result
        //  is the content container ID.
        //  For example for the original tabs: tab_container_id + '_content' = original_tabs_content
        $('#' + tab_container_id + '_content .tab_content').hide();
    
        //  Show the selected tab content
        $('#' + tab_container_id + '_content #' + tab_id).fadeIn();
      }
    
      //  Function that gets the hash from URL
      var get_hash = function(){
        if (window.location.hash) {
          //  Get the hash from URL
          var url = window.location.hash;
    
          //  Remove the #
              var current_hash = url.substring(1);
    
          //  Split the IDs with comma
          var current_hashes = current_hash.split(",");
    
          //  Loop over the array and activate the tabs if more then one in URL hash
          $.each(current_hashes, function(i, v){
            set_tab($('a[rel="'+v+'"]').parent().parent().parent().attr('id'), v);
          });
        }
      }
    
      //  Called when page is first loaded or refreshed
      get_hash();
    
      //  Looks for changes in the URL hash
      $(window).bind('hashchange', function() {
        get_hash();
      });
    
      //  Called when we click on the tab itself
      $('.tabs_wrapper ul li').click(function() {
        var tab_id = $(this).children('a').attr('rel');
    
        //  Update the hash in the url
        window.location.hash = tab_id;
    
        //  Do nothing when tab is clicked
        return false;
      });
    
    // Fancybox
    
      $(".fancybox").fancybox({
                  openEffect  : 'fade',
                  closeEffect : 'fade',
                  prevEffect : 'fade',
                  nextEffect : 'fade',
                  closeClick : false,
                  padding: 10,
                  helpers: {
                    title : {
                      type : 'outside'
                    },
                    overlay : {
                      speedIn : 500,
                      opacity : 0.80
                    }
                  }
            });
    
            $('.fancybox-media')
            .attr('rel', 'media-gallery')
            .fancybox({
              padding: 0,
              openEffect : 'fade',
              closeEffect : 'fade',
              prevEffect : 'fade',
              nextEffect : 'fade',
              width : '80%',
              height : '80%',
    
              arrows : false,
              helpers : {
                media : {},
                buttons : {}
              }
            });
    
            $("a.iframe").fancybox({
                              'autoDimensions'    : false,
                              //'width'             : 830,
                              //'height'            : 438,
                              'padding'           : 0,
                              'autoScale'   : false,
                              'centerOnScroll'  : true,
                              'scrolling'   : "no",
                              'transitionIn'      : 'elastic',
                              'transitionOut'     : 'elastic',
                              'type'              : 'iframe'
    
            });
            $("a.iframe_small").fancybox({
                              'autoDimensions'    : false,
                              'width'             : 600,
                              'height'            : 400,
                              'padding'           : 0,
                              'autoScale'   : false,
                              'centerOnScroll'  : true,
                              'scrolling'   : "no",
                              'transitionIn'      : 'elastic',
                              'transitionOut'     : 'elastic',
                              'type'              : 'iframe'
    
            });
            $("a.iframe_bt").fancybox({
              'autoDimensions'    : false,
              'width'             : 720,
              'height'            : 680,
              'padding'           : 0,
              'autoScale'   : false,
              'centerOnScroll'  : true,
              'scrolling'   : "no",
              'transitionIn'      : 'elastic',
              'transitionOut'     : 'elastic',
              'type'              : 'iframe'
    
            });
            $("a.iframe_terms").fancybox({
                              'autoDimensions'    : false,
                              //'width'             : 830,
                              //'height'            : 438,
                              'padding'           : 10,
                              'autoScale'   : false,
                              'centerOnScroll'  : true,
                              'scrolling'   : "yes",
                              'transitionIn'      : 'elastic',
                              'transitionOut'     : 'elastic',
                              'type'              : 'iframe'
    
            });
    
    // bPopup
    
              $('.cta-partners-dr').bind('click', function(e) {
                  e.preventDefault();
                  $('#form-partners-dr').bPopup({
                      zIndex: 999,
                      modalClose: true,
                      opacity: 0.8
                  });
              })
    
    
    
    // Custom drop down menus
    $('#modal_change select').each(function() {
    
        // Cache the number of options
        var $this = $(this),
            numberOfOptions = $(this).children('option').length;
    
        // Hides the select element
        $this.addClass('s-hidden');
    
        // Wrap the select element in a div
        $this.wrap('<div class="select"></div>');
    
        // Insert a styled div to sit over the top of the hidden select element
        $this.after('<div class="styledSelect"></div>');
    
        // Cache the styled div
        var $styledSelect = $this.next('div.styledSelect');
    
        // Show the first select option in the styled div
        $styledSelect.text($this.children('option').eq(0).text());
    
        // Insert an unordered list after the styled div and also cache the list
        var $list = $('<ul />', {
            'class': 'options'
        }).insertAfter($styledSelect);
    
        // Insert a list item into the unordered list for each select option
        for (var i = 0; i < numberOfOptions; i++) {
            $('<li />', {
                text: $this.children('option').eq(i).text(),
                rel: $this.children('option').eq(i).val()
            }).appendTo($list);
        }
    
        // Cache the list items
        var $listItems = $list.children('li');
    
        // Show the unordered list when the styled div is clicked (also hides it if the div is clicked again)
        $styledSelect.click(function(e) {
            e.stopPropagation();
            $('div.styledSelect.active').each(function() {
                $(this).removeClass('active').next('ul.options').hide();
            });
            $(this).toggleClass('active').next('ul.options').toggle();
        });
    
        // Hides the unordered list when a list item is clicked and updates the styled div to show the selected list item
        // Updates the select element to have the value of the equivalent option
        $listItems.click(function(e) {
            e.stopPropagation();
            $styledSelect.text($(this).text()).removeClass('active');
            $this.val($(this).attr('rel'));
            $list.hide();
            /* alert($this.val()); Uncomment this for demonstration! */
        });
    
        // Hides the unordered list when clicking outside of it
        $(document).click(function() {
            $styledSelect.removeClass('active');
            $list.hide();
        });
    
    });
    
    // Sub Nav
      
        /*var aboveHeight = $('#header').outerHeight();
        $('#nav-bar a.top-logo').addClass('nobg').next().css('margin-left','-56px');
      
        // when scroll
        $(window).scroll(function(){
        
            //if scrolled down more than the header's height
            if ($(window).scrollTop() > aboveHeight){
          
                // if yes, add "fixed" class to the <nav>
                // add padding top to the #content (value is same as the height of the nav)
                $('#nav-bar').addClass('fixed').css('top','0').next().css('padding-top','39px');
                $('#nav-bar a.top-logo').removeClass('nobg').next().css('margin-left','0');
                $('#nav-bar a.top-logo').css('margin-right','25px');
            } else {
          
                // when scroll up or less than aboveHeight, remove the "fixed" class, and the padding-top
                $('#nav-bar').removeClass('fixed').next().css('padding-top','0');
                $('#nav-bar a.top-logo').addClass('nobg').next().css('margin-left','-56px');
                $('#nav-bar a.top-logo').css('margin-right','0');
                $('#content').css('padding-top','40px');
            }
        });*/
    
    // ends all functions

    var base_path = window.location.pathname;
    var lang = base_path.substr(0,3);
    var is_home = $.inArray(base_path, ['/de', '/fr']) !== -1;
    var path = "/";
    var languages = ['/de', '/fr'];
    
    if($.inArray(base_path.substr(0,3), ['/de', '/fr']) !== -1) {
      $('.field a').not('.iframe').not('.green_button').not('.grey_button').each(function(key,value){
          if($.inArray($(this).attr('href').substr(0,4), ['/de', '/fr']) === -1)
              $(this).attr('href', lang + $(this).attr('href'))
    
      });
    }
    
    add_hreflang_link_element('', 'x-default');
    
    if($.inArray(base_path.substr(0,3), ['/de', '/fr']) !== -1) {
        path = base_path.substr(3);
        alter_links($('#original_tabs a'), languages);
        alter_links($('#footer .sitemap a'), languages);
        /*
        alter_links($('#block-node-block-builder-press-related-stories .box_b a'), languages);
        */
        $('#header .logo').each(function(key,value){
            if($.inArray($(this).attr('href').substr(0,4), ['/de', '/fr']) === -1)
                $(this).attr('href', lang + $(this).attr('href'))
                //$(this).attr('class', 'logo logo_' + lang.substr(1))
        });
        add_hreflang_link_element('/de', 'de');
        add_hreflang_link_element('/fr', 'fr');
    }
    else {
        path = base_path;
    }
    
    is_home ? $('#en').attr('href', "/") : $('#en').attr('href', path);
    $('#de').attr('href', '/de' + path);
    $('#fr').attr('href', '/fr' + path);
    
    function add_hreflang_link_element(langPref, lang) {
        link = document.createElement('link');
        link.href= window.location.host + langPref + base_path.substr(3);
        link.hreflang= lang;
        link.rel='alternate';
        document.getElementsByTagName('head')[0].appendChild(link);
    }
    
    function alter_links(elements, languages) {
        elements.each(function(key,value) {
            if(($.inArray($(this).attr('href').substr(0,4), languages) === -1) && ($(this).attr('href').indexOf("http") === -1))
                $(this).attr('href', lang + $(this).attr('href'))
                $(this).attr('hreflang', lang.substr(1))
        });
    }


  $('.cta1').bind('click', function(e) {
      e.preventDefault();
      $('#api-developer-portal').bPopup({
          zIndex: 999,
          modalClose: true,
          opacity: 0.8
      });
  })
  $('#api-developer-portal').hide();

  });
})(jQuery, window, document);  // ends all functions;
