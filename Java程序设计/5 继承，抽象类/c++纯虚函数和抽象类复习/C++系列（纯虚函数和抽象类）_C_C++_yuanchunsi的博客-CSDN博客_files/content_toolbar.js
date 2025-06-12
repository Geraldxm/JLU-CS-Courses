var _gaq = [];
var userAgent = navigator.userAgent.toLowerCase();

// 百度统计&google统计
var _hmt = _hmt || [];
(function () {
  var getCookie = function (objName) { // 获取指定名称的cookie的值
    var arrStr = document.cookie.split("; ");
    for (var i = 0; i < arrStr.length; i++) {
      var temp = arrStr[i].split("=");
      if (temp[0] == objName && objName == "UD") return decodeURIComponent(temp[1]);
      if (temp[0] == objName) return decodeURI(temp[1]);
    }
  }

  // 百度统计
  var hm = document.createElement("script");
  hm.src = "https://hm.baidu.com/hm.js?6bcd52f51e9b3dce32bec4a3997715ac";
  var s = document.getElementsByTagName("script")[0];
  s.parentNode.insertBefore(hm, s);

  // google统计
  if (document.referrer.indexOf('google.com') > -1) {
    var google_hm = document.createElement("script");
    google_hm.src = 'https://www.googletagmanager.com/gtag/js?id=' + siteId();
    s.parentNode.insertBefore(google_hm, s);
    window.dataLayer = window.dataLayer || [];
    var userName = getCookie("UserName") || ''
    function gtag() { dataLayer.push(arguments); }
    gtag('js', new Date());
    gtag('config', siteId());
    if (userName) {
      gtag('set', { 'user_id': userName });
    }
  }
})();

//Chrome通知，统计，导航字体库
(function() {
    var s = document.createElement('script');
    s.type = 'text/javascript';
    s.async = true;
    s.src = 'https://g.csdnimg.cn/??notification/1.3.7/notify.js,notification/1.3.7/main.js,iconfont/nav/iconfont-1.0.0.js';
    var x = document.getElementsByTagName('script')[0];
    x.parentNode.insertBefore(s, x);
})();
(function() {
    var s = document.createElement('script');
    s.type = 'text/javascript';
    s.async = true;
    s.src = 'https://g.csdnimg.cn/asdf/1.1.2/trackad.js';
    var x = document.getElementsByTagName('script')[0];
    x.parentNode.insertBefore(s, x);
})();


/**
 * 判断是否是博客网站
 * @return {Boolean} [description]
 */
function is_blog() {
    var loac_host = window.location.host;
    if (loac_host.indexOf("blog.csdn.net") > -1) return true;
    else return false;
}

function siteId() {
    var loac_host = window.location.host;
    // loac_host ='www.csdn.net'
    var siteId;
    if (loac_host.indexOf("blog") === 0) {
        siteId = 'UA-127895514-2'
    } else if (loac_host.indexOf("download") === 0) {
        siteId = 'UA-127895514-8'
    } else if (loac_host.indexOf("edu") === 0) {
        siteId = 'UA-127895514-9'
    } else if (loac_host.indexOf("bbs") === 0) {
        siteId = 'UA-127895514-4'
    } else if (loac_host.indexOf("ask") === 0) {
        siteId = 'UA-127895514-5'
    } else if (loac_host.indexOf("gitbook") === 0) {
        siteId = 'UA-127895514-10'
    } else if (loac_host.indexOf("iteye") === 0) {
        siteId = 'UA-127895514-6'
    } else if (loac_host.indexOf("passport") === 0) {
        siteId = 'UA-127895514-7'
    } else if (loac_host.indexOf("so") === 0) {
        siteId = 'UA-127895514-3'
    } else if (loac_host.indexOf("www") === 0) {
        if (loac_host.indexOf("iteye") > 0) {
            siteId = 'UA-127895514-6'
        } else {
            siteId = 'UA-127895514-1'
        }
    } else {
        siteId = ''
    }
    return siteId
}
/**
 * 确定是否博客并有用户名
 * @return {Boolean} [description]
 */
function is_bloger() {
    try {
        if (is_blog() && username) {
            return true;
        }
    } catch (e) {
        return false;
    }
}

!(function ($) {
    var currUser = {
        userName: "",
        userNick: '<a class="set-nick" href="https://passport.csdn.net/account/profile">设置昵称<span class="write-icon"></span></a>',
        desc: '<a class="fill-dec" href="//my.csdn.net" target="_blank">编辑自我介绍，让更多人了解你<span class="write-icon"></span></a>',
        avatar: "//csdnimg.cn/public/common/toolbar/images/100x100.jpg"
    };
    var prodLogo = "none";
    var $oScriptTag = $("#toolbar-tpl-scriptId");
    var skin = $oScriptTag.attr("skin") == "black" ? " csdn-toolbar-skin-black " : "";
    var fixed = $oScriptTag.attr("fixed") == "top" ? " navbar-fixed-top " : "";
    var prodIndex = $oScriptTag.attr("domain") ? $oScriptTag.attr("domain") : window.location.protocol + "//" + window.location.host;
    prodIndex += '_logo';
    var getCookie = function (objName) {//获取指定名称的cookie的值
        var arrStr = document.cookie.split("; ");
        for (var i = 0; i < arrStr.length; i++) {
            var temp = arrStr[i].split("=");
            if (temp[0] == objName && objName == "UD") return decodeURIComponent(temp[1]);
            if (temp[0] == objName) return decodeURI(temp[1]);
        }
    }
    var setCookie = function (name, value, t) {
        var exp = new Date();
        if (t == -1) {
          document.cookie = name + "=" + escape(value) + ";domain=.csdn.net;path=/";
          return
        }
        if (!t) {
            var Days = 30;
            exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);

        } else {
            exp.setTime(exp.getTime() +t);
        }
        document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString() + ";domain=.csdn.net;path=/";
    }
    var HTMLEncode = function (str) {
        var s = "";
        if (str.length == 0) return "";
        s = str.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/\'/g, "&#39;").replace(/\"/g, "&quot;");
        return s;
    }
    var AUtoAvatar = function (AU) {
        if (!AU || !currUser.userName) {
            return false;
        }
        var _AUPath = AU.split("").join("/");
        var userName = currUser.userName && currUser.userName.toLowerCase();
        return "//profile.csdnimg.cn/" + _AUPath + "/2_" + userName;
    }
    var hasLogin = false;
    var loginMark = "unlogin";
    function checkLogin(callback) {
        currUser.userNick = getCookie("UserNick") || currUser.userNick;
        currUser.userName = getCookie("UserName") || currUser.userName;
        currUser.avatar = AUtoAvatar(getCookie("AU")) || currUser.avatar;
        currUser.desc = getCookie("UD") || currUser.desc;
        if (getCookie("UD")) {
            currUser.desc = HTMLEncode(currUser.desc.replace(/\+/g, " "));
        }
        callback(currUser);
    }
    checkLogin(function (currUser) {
        if (currUser.userName) {
            hasLogin = true;
            _hmt.push(['_setUserTag', '5744', currUser.userName]);
        }
        loginMark = hasLogin ? "" : "unlogin";
    })
    try {
        _hmt.push(['_setUserTag', '6525', getCookie("uuid_tt_dd")])
    } catch (e) {
        console.warn(e)
    }

    /*
    * init pord logo
    */
    var prodJSON = {
        "blog": "blog-icon",
        "download": "down-icon",
        "bbs": "bbs-icon",
        "my": "space-icon",
        "code": "code-icon",
        "share": "share-icon",
        "tag": "tag-icon",
        "dashboard": "dashboard-icon",
        "news": "news-icon",
        "tag": "tag-icon",
        "ask": "ask-icon",
        "notify": "notify-icon"
    }
    if (prodJSON[$oScriptTag.attr("prod")]) {
        prodLogo = prodJSON[$oScriptTag.attr("prod")] || $oScriptTag.attr("prod");
    }

    /**
     * 二级域名 getSecondaryDomain
     * @return {string} 二级域名
     */
    var getSecondaryDomain = (function () {
        var host = window.location.host;
        return host.split('.')[0]
    })()
    //  festival customize
    window.csdn = window.csdn ? window.csdn : {}
    var start_time = 1571846400, // toolbar 特殊样式起始时间戳new Date(2018,11,29).getTime()/1000
        end_time = 1571932800,  // toolbar 特殊样式结束时间戳
        now_time = Math.floor(Date.now() / 1000), // 当前时间戳csdnlogo
        logo_tpl = window.csdn.toolbarIsBlack ? '<img src="//csdnimg.cn/cdn/content-toolbar/csdnlogo-black.png" class="csdn-logo">' : '<img src="//csdnimg.cn/cdn/content-toolbar/csdn-logo_.png?v=20190924.1" class="csdn-logo">',  // 正常样式
        logo_title = 'CSDN首页', // 正常提示
        logo_href = 'https://www.csdn.net';
    /**
     * 当前时间在起始与结束时间内时触发
     */
    if (now_time > start_time && now_time < end_time) {
        logo_tpl = window.csdn.toolbarIsBlack ? '<img src="//csdnimg.cn/cdn/content-toolbar/csdn1024-dark.gif">' :'<img src="//csdnimg.cn/cdn/content-toolbar/csdn1024-white.gif">';
        logo_title = '1024';
    }

    // 获取时间戳
    function getTimestamp (date) {
      return Math.round(new Date(date).getTime() / 1000);
    }

    // 判断当前日期是否处在节日范围(春节、博客之星）
    // 春节（【小年前一天】2020/01/16 14:00:00 - 【正月十五后一天】2020/02/09 10:30:00）
    // 博客之星（上线日期-2020/02/29 00:00:00）
    // π Day（2020/03/14 00:00:00-2020/03/14 23:59:59）
    function isFestivalRange (day) {
      var start_time,
          end_time,
          now_time = Math.floor(Date.now() / 1000), // 当前时间戳
          ChineseNewYear_start_time = getTimestamp('2020/01/16 14:00:00'),
          ChineseNewYear_end_time = getTimestamp('2020/02/09 10:30:00'),
          blogStar_start_time = getTimestamp('2020/01/08 00:00:00'),
          blogStar_end_time = getTimestamp('2020/02/29 00:00:00'),
          πDay_start_time = getTimestamp('2020/03/14 00:00:00'),
          πDay_end_time = getTimestamp('2020/03/14 23:59:59');
      if (day === 'ChineseNewYear') {
        start_time = ChineseNewYear_start_time;
        end_time = ChineseNewYear_end_time;
      } else if (day === 'blogStar') {
        start_time = blogStar_start_time;
        end_time = blogStar_end_time;
      } else if (day === 'πDay') {
        start_time = πDay_start_time;
        end_time = πDay_end_time;
      }
      return now_time > start_time && now_time < end_time;
    }

    // 设置节日logo
    function setFestiveLogo () {
      var logo_tpl,
          logo_title,
          prefix = 'https://csdnimg.cn/cdn/content-toolbar/',
          csdnlogo_ChineseNewYear = prefix + 'csdnlogo_ChineseNewYear.gif',
          csdnlogo_πDay = window.csdn.toolbarIsBlack ? prefix + '314-black.gif' : prefix + '314-white.gif';
      if (isFestivalRange('ChineseNewYear')) {
        logo_tpl = '<img src="' + csdnlogo_ChineseNewYear + '">';
        logo_title = '欢度新春';
        return { logo_tpl: logo_tpl, logo_title: logo_title };
      } else if (isFestivalRange('πDay')) {
        logo_tpl = '<img src="' + csdnlogo_πDay + '">';
        return { logo_tpl: logo_tpl };
      }
    }

    // toolbar是否有festival类
    function isFestivalClass () {
      return $('#csdn-toolbar').hasClass('festival');
    }

    // 设置春节class
    function setChineseNewYearClass () {
      if (isFestivalRange('ChineseNewYear')) {
        $('#csdn-toolbar').addClass('festival');
      }
    }

    var logo_obj = setFestiveLogo(); // 设置节日logo
    if (logo_obj) {
      logo_tpl = logo_obj.logo_tpl;
    }

    // 订单来源追踪
    function orderSourceTracking () {  
      var domain_white_list = ['blog', 'bbs', 'download', 'ask', 'edu', 'biwen', 'mall'];
      var ref = document.referrer;
      if (ref !== '') {
        if (ref.indexOf('.csdn.net') > -1) {
          for (var i = 0; i < domain_white_list.length; i++) {
            if (ref.indexOf(domain_white_list[i] + '.csdn.net') > -1) {
              setCookie('c_ref', ref, -1);
            }
          }
        } else {
          setCookie('c_ref', ref, -1);
        }
      }
    }
    
    orderSourceTracking();

    //输入框随机抽取关键词
    var searchWordArr = ['Python进阶之路', 'Python工程师'],
        searchWordN = Math.floor(Math.random() * 2),
        searchWord = searchWordArr[searchWordN]

    var input_placeholder = "搜CSDN";
    if (is_bloger()) {
        input_placeholder = "搜博主文章"
    }

    // 因为 logo 是SVG 的，所以必须把 iconfont.js 加入到代码里面
    var tpl = '\<div id="csdn-toolbar" class="csdn-toolbar tb_disnone ' + skin + fixed + '">\
        <div class="container row center-block " id="csdn_container_tool">\
          <ul class="pull-left left-menu clearfix" id="nav-left-menu">\
            <li>\
              <a href="'+ logo_href + '" title="' + logo_title + '">' + logo_tpl + '</a>\
            </li>\
            <li data-report-click=\'{"mod":"popu_336","dest":"https://www.csdn.net/","extend1":"首页"}\'><a href="//www.csdn.net/" class="toolbar_to_feed" title="首页" style="margin-left:0">首页</a></li>\
            <li data-report-click=\'{"mod":"popu_336","dest":"https://blog.csdn.net/","extend1":"博客"}\'><a href="//blog.csdn.net/" class="toolbar_to_feed" title="博客">博客</a></li>\
            <li data-report-click=\'{"mod":"popu_336","dest":"https://edu.csdn.net","extend1":"学院"}\' class="app-control app-edu"><a class="link-title" href="//edu.csdn.net" title="学院">学院</a>\
              <div class="appControl">\
              <span class="eduwx">\
                <img src="https://csdnimg.cn/public/common/toolbar/images/eduwxfix.png" />\
                <em>CSDN学院</em>\
              </span>\
              </div>\
            </li>\
            <li data-report-click=\'{"mod":"popu_336","dest":"https://download.csdn.net","extend1":"下载"}\' ><a href="//download.csdn.net" title="下载">下载</a></li>\
            <li data-report-click=\'{"mod":"popu_336","dest":"https://bbs.csdn.net","extend1":"论坛"}\'><a href="//bbs.csdn.net" title="论坛">论坛</a></li>\
            <li data-report-click=\'{"mod":"popu_336","dest":"https://ask.csdn.net","extend1":"问答"}\'><a href="//ask.csdn.net" title="问答">问答</a></li>\
            <li data-report-click=\'{"mod":"popu_336","dest":"https://huiyi.csdn.net/","extend1":"活动"}\'><a href="//huiyi.csdn.net/" title="活动">活动</a></li>\
            <li data-report-click=\'{"mod":"popu_336","dest":"https://spec.csdn.net/","extend1":"专题"}\'><a href="https://spec.csdn.net/" title="专题">专题</a></li>\
            <li data-report-click=\'{"mod":"popu_336","dest":"https://job.csdn.net","extend1":"招聘"}\'><a href="http://job.csdn.net" title="招聘">招聘</a></li>\
            <li data-report-click=\'{"mod":"popu_336","dest":"https://www.csdn.net/apps/download?code=pc_1555579859","extend1":"APP"}\' class="app-control app-app"><a class="link-title" href="https://www.csdn.net/apps/download" data-report-query="code=pc_1555579859" title="APP">APP</a>\
              <div class="appControl">\
              <span>\
                <img src="https://csdnimg.cn/public/common/toolbar/images/csdnqr@2x.png" />\
                <em>CSDN</em>\
              </span>\
              </div>\
            </li>\
            <li data-report-click=\'{"mod":"popu_336","dest":"https://mall.csdn.net/vip","extend1":"VIP会员"}\' class="vip-caise"><a href="https://mall.csdn.net/vip" title="VIP会员">VIP会员</a><span class="vip-totast">续费8折</span></li>'
            + blogStar() +
                '<div class="search_bar onlySearch searchUser" data-report-click=\'{"mod":"popu_369","dest":"https://so.csdn.net/so/"}\' data-report-view=\'{"mod":"popu_369","dest":"https://so.csdn.net/so/"}\'>\
                    <input type="text" class="input_search" name="" autocomplete="off" value='+ searchWord + ' id="toolber-keyword" placeholder=' + input_placeholder + '>\
                    <a href="//so.csdn.net/so/" target="_blank" class="btn-nobg-noborder btn-search">\
                    <img src="//csdnimg.cn/cdn/content-toolbar/csdn-sou.png?v=20190924.1">\
                    </a>'
                    + buildDropDownMenu('search_bar') +
                '</div>\
          </ul>\
          <div class="pull-right onlyUser searchUser login-wrap '+ loginMark + '">\
            <ul class="btns">\
              <li class="write-bolg-btn" data-report-click=\'{"mod":"popu_370","dest":"https://mp.csdn.net/console/editor/html"}\' data-report-view=\'{"mod":"popu_370","dest":"https://mp.csdn.net/console/editor/html"}\'><a class="" href="https://mp.csdn.net/console/editor/html" target="_blank" id="blogClick">\
              <i class="csdn-write"></i>\
              <span>写博客</span></a>\
              <li class="gitChat upload msgBox" id="msgBox">\
              <a class="" id="remind" href="#"><i class="message"></i><div class="toolbar-circle" id="msg-circle"></div></a>\
                <div class="msgList" id="msgList">\
                  <dd><a href="#" id="msgList-notice">公告<em class="notice"></em></a></dd>\
                  <div id="msgList-other">\
                  <dd><a href="//i.csdn.net/#/msg/index">评论<em class="comment"></em></a></dd>\
                  <dd><a href="//i.csdn.net/#/msg/attention">关注<em class="follow"></em></a></dd>\
                  <dd><a href="//i.csdn.net/#/msg/like">点赞<em class="thumb_up"></em></a></dd>\
                  <dd><a href="//im.csdn.net/im/main.html">私信<em class="im"></em></a></dd>\
                  <dd><a href="//i.csdn.net/#/msg/answer">回答<em class="invitation"></em></a></dd>\
                  <dd><a href="//i.csdn.net/#/msg/notice">系统通知<em class="system"></em></a></dd>\
                  <dd><a href="https://i.csdn.net/#/msg/setting">消息设置</a></dd>\
                  </div>\
                </div>\
                <a href="//i.csdn.net/#/msg/notice" class="msg-toast" id="msgToastText">你有一张VIP限时优惠券哦</a>\
              </li>\
              <li class="userinfo"><a href="https://passport.csdn.net/account/login">登录/注册</a><span></span>\
              </li>\
              <li class="userLogin">\
                <div class="loginCenter"><a href="//i.csdn.net" target="_blank"><img class="login_img" src="'+ currUser.avatar + '"></a></div>\
                <div class="userControl">\
                <div class="bord">\
                <div data-report-click=\'{"mod":"popu_789","dest":"https://www.csdn.net/nav/watchers","extend1":"我的关注"}\'><i class="pull_icon pull_icon1"></i><a href="https://www.csdn.net/nav/watchers" target="_blank">我的关注</a></div>\
                <div data-report-click=\'{"mod":"popu_789","dest":"https://i.csdn.net/#/uc/collection-list","extend1":"我的收藏"}\'><i class="pull_icon pull_icon2"></i><a href="https://i.csdn.net/#/uc/collection-list" target="_blank">我的收藏</a></div>\
                <div data-report-click=\'{"mod":"popu_789","dest":"https://i.csdn.net/#/uc/profile","extend1":"个人中心"}\'><i class="pull_icon pull_icon4"></i><a href="https://i.csdn.net/#/uc/profile" target="_blank">个人中心</a></div>\
                <div data-report-click=\'{"mod":"popu_789","dest":"https://i.csdn.net/#/account/index","extend1":"帐号设置"}\'><i class="pull_icon pull_icon7"></i><a href="https://i.csdn.net/#/account/index" target="_blank">帐号设置</a></div>\
                </div>\
                  <div class="bord">\
                  <div data-report-click=\'{"mod":"popu_789","dest":"https://blog.csdn.net/'+ currUser.userName + '","extend1":"我的博客"}\'><i class="pull_icon pull_icon5"></i><a href="https://blog.csdn.net/'+ currUser.userName + '" target="_blank">我的博客</a></div>\
                  <div data-report-click=\'{"mod":"popu_789","dest":"https://mp.csdn.net","extend1":"管理博客"}\'><i class="pull_icon pull_icon6"></i><a href="https://mp.csdn.net" target="_blank">管理博客</a></div>\
                  <div data-report-click=\'{"mod":"popu_789","dest":"https://edu.csdn.net/mycollege","extend1":"我的学院"}\'><i class="pull_icon pull_icon12"></i><a href="https://edu.csdn.net/mycollege" target="_blank">我的学院</a></div>\
                  <div data-report-click=\'{"mod":"popu_789","dest":"https://download.csdn.net/my/downloads","extend1":"我的下载"}\'><i class="pull_icon pull_icon13"></i><a href="https://download.csdn.net/my/downloads" target="_blank">我的下载</a></div><div><i class="pull_icon pull_icon14"></i><a href="https://huiyi.csdn.net/activity/myorder" target="_blank">我的活动</a></div>\
                  </div>\
                  <div class="bord">\
                  <div data-report-click=\'{"mod":"popu_789","dest":"https://my.csdn.net/my/score","extend1":"我的C币"}\'><i class="pull_icon pull_icon8"></i><a href="https://my.csdn.net/my/score" target="_blank">我的C币</a></div>\
                  <div data-report-click=\'{"mod":"popu_789","dest":"https://order.csdn.net/myorder","extend1":"订单中心"}\'><i class="pull_icon pull_icon9"></i><a href="https://order.csdn.net/myorder" target="_blank">订单中心</a></div>\
                  </div>\
                  <div class="bord">\
                  <div data-report-click=\'{"mod":"popu_789","dest":"https://blog.csdn.net/blogdevteam/article/details/103478461","extend1":"帮助"}\'><i class="pull_icon pull_icon10"></i><a href="https://blog.csdn.net/blogdevteam/article/details/103478461" target="_blank">帮助</a></div>\
                  <div><i class="pull_icon pull_icon11"></i><a href="javascript:void(0);" class="logout">退出</a></div>\
                  </div>\
                </div>\
                <div class="guo_tip_box">关注和收藏在这里</div>\
              </li>\
            </ul>\
          </div>\
        </div>\
    </div>';
    $(document.body).prepend($(tpl));
    $('#csdn-toolbar .logout').click(function () {
        var trackdata = {"mod":"popu_789"};
        var url = 'https://passport.csdn.net/account/logout?from=' + encodeURIComponent(window.location.href);
        trackdata['dest'] = url;
        trackdata['extend1'] = '退出';
        window.location.href = url;
        csdn&&csdn.report?csdn.report.reportClick(trackdata):"";
    })
    
    var timeOut = 1;
    if ((window.location.host.indexOf('bbs.csdn.net') > -1 && window.location.pathname.indexOf('home') > -1) || (window.location.host.indexOf('bbs.csdn.net') > -1 && window.location.pathname.indexOf('forums') > -1)) {
        $('.csdn-toolbar').addClass('csdn-toolbarbbshome')
    }

    setChineseNewYearClass(); // 设置春节class

    // 消息新接口
    function getNews(announcementCount) {
        var msgCircle = $('#msg-circle');
        var msgListNotice = $('#msgList-notice')
        if(parseInt(announcementCount) > 0){
            msgCircle.text(announcementCount).fadeIn()
            msgListNotice.find('em').text(announcementCount)
        }
        if (!hasLogin) return;
        var url = 'https://msg.csdn.net/v1/web/message/view/unread';
        var maxNum = 99;
        var elems = $('#msgList a em');
        var msgToastText = $('#msgToastText');
        var docTitle = document.head.getElementsByTagName('title');
        var d = { coupon: true };
        var docTitleText;
        docTitle = docTitle && docTitle[0];
        docTitle && (docTitleText = docTitle.innerHTML);
        d = JSON.stringify ? JSON.stringify(d) : '{"coupon":true}';
        $.ajax({
            url: url,
            type: 'post',
            data: d,
            contentType: 'application/json',
            xhrFields: {
                withCredentials: true
            },
            dataType: 'json',
            success: function (json) {
                var len;
                if (json.status) {
                    json = json.data;
                    // || {
                    //   comment: 100,
                    //   follow: 5,
                    //   invitation: 8,
                    //   system: 0,
                    //   thumb_up: 2,
                    //   im: 1,
                    //   totalCount: 9
                    // };
                    if(parseInt(announcementCount) > 0){
                        json.totalCount = parseInt(json.totalCount)  + announcementCount
                        var msgListNotice = $('#msgList-notice')
                        // msgCircle.text( parseInt(msgCircleText) + announcementCount).fadeIn()
                        msgListNotice.find('em').text(announcementCount)
                    }
                    if ((len = json.totalCount) > 0) {
                        if (len >= maxNum) {
                            len = '···';
                        }
                        len && docTitle && (docTitle.innerHTML = '(' + len + '条消息)' + docTitleText)
                        msgCircle.html(len).fadeIn();

                        elems.each(function (i, el) {
                            var oClass = $(el).attr('class');
                            var strNum = Number(json[oClass], 10);
                            if (strNum >= maxNum) {
                                strNum = (maxNum - 1) + '+'
                            }
                            strNum && $(el).html(strNum);
                        })
                    }
                    // vip 下拉提醒 ，排除博客皮肤预览页
                    if (json.coupon_order > 0 && window.location.href.indexOf("assign_skin_id")==-1) {
                        msgToastText.addClass('msg-toast--show')
                    }
                }
            },
            error: function (err) {
                // console.error('消息服务错误!', err, err.responseText)
            }
        });
    }
    $(function(){
        // 获取公告cookie
        if (!getCookie("announcement") || JSON.parse(decodeURIComponent(getCookie("announcement"))).announcementUrl === 'undefined') {
            // 不存在去获取
            getReadAnnouncement() //存cookie

        } else {
            // 有cookie
            let announcementObj =  JSON.parse(decodeURIComponent(getCookie("announcement")))
            let announcementUrl = announcementObj.announcementUrl
            let announcementCount = announcementObj.announcementCount
            let isLogin = announcementObj.isLogin
            let userName = getCookie("UserName")
            // 存在赋值
            if (!isLogin && userName) {
                getReadAnnouncement()
            } else {
                getNotice(announcementObj)
                getNews(announcementCount)
                setHrefValue(announcementUrl)
            }
        }
    })
    function setHrefValue (announcementUrl) {
        var msgListNotice = $('#msgList-notice')
        msgListNotice.attr('href', announcementUrl)
        // 登陆跳转到评论页面 没登录跳转到公告
        if (hasLogin) {
          $('#remind').attr('href', 'https://i.csdn.net/#/msg/index');
        } else {
          $('#remind').attr('href', announcementUrl);
        }
    }
    // 获取公告读取情况
    function getReadAnnouncement () {
        var url = 'https://msg.csdn.net/v1/web/message/view/announcement';
        $.ajax({
            url: url,
            type: 'post',
            contentType: 'application/json',
            xhrFields: {
                withCredentials: true
            },
            dataType: 'json',
            success: function (json) {
                if (json.status) {
                    let data = json.data
                    setCookie('announcement', encodeURIComponent(JSON.stringify(data)), data.announcementExpire)
                    getNotice(data)
                    getNews(data.announcementCount)
                    setHrefValue(data.announcementUrl)
                }
            },
            error: function (err) {
                console.error('消息服务错误!', err, err.responseText)
            }
        });
    }
    // 公告已读接口
    function clickReadAnnouncement () {
        var url = 'https://msg.csdn.net/v1/web/message/read_announcement';
        $.ajax({
            url: url,
            type: 'post',
            contentType: 'application/json',
            xhrFields: {
                withCredentials: true
            },
            dataType: 'json',
            success: function (json) {
            },
            error: function (err) {
                console.error('消息服务错误!', err, err.responseText)
            }
        });
    }
    function getNotice (dataObj){
        // 公告提示
        // 判断是否点击
            let msgListNotice = $('#msgList-notice')
                msgListNotice.click(function(){
                    msgListNotice.find('em').empty()
                    dataObj.announcementCount = 0
                    setCookie('announcement', encodeURIComponent(JSON.stringify(dataObj)), dataObj.announcementExpire)
                    clickReadAnnouncement()
                })
            // }
        // }
    }
    // 构建下拉菜单
    function buildDropDownMenu (el) {
      return '\
        <div id="' + el  + '__drop-down-menu" class="drop-down-menu">\
          <dl class="hot-search">\
            <dt class="hot-search-menu">\
              <span>热门搜索</span>\
            </dt>\
          </dl>\
          <dl class="search-history" class="clearfix">\
            <dt class="search-history-menu">\
              <span class="search-history-title">搜索记录</span>\
              <span class="clear">清空</span>\
            </dt>\
          </dl>\
        </div>';
    }
    // 动态创建博客之星标签（生效范围：上线日期-2020-02-29 00:00:00）
    function blogStar () {
      return isFestivalRange('blogStar') ? '<li><a href="https://bss.csdn.net/m/topic/blog_star2019" title="博客之星">博客之星</a></li>' : '';
    }
    /*
       全站新添修改
       @description
         toolbar调整
         gitchat全站通栏广告添加
    */
    $(function () {

        // control bbs toolbar
        function controlToolBarBBs() {
            if ($('#main-home').hasClass('open')) {
                $('#csdn-toolbar').addClass('tb_bbs');
            }
            $('.left_side > span').on('click', function () {
                if ($(this).hasClass('btn_toggle_yc')) {
                    $('#csdn-toolbar').removeClass('tb_bbs');
                }
                if ($(this).hasClass('btn_toggle_xs')) {
                    $('#csdn-toolbar').addClass('tb_bbs');
                }
            });
        }
        // gitchat 广告全站添加
        function advert() {
            var t = 2000,
                cookieTime = 60 * 60 * 24,
                cookieKey = 'is_advert',
                domain = '.csdn.net',
                isStart = false,
                advertDate = {
                    start: '2018/06/07 06:00:00',
                    end: '2018/06/30 00:00:00'
                };

            // contronl start
            isStart = adverControlDate();
            // clear cookie
            if (!isStart) {
                // clear is_advert
                if (getCookie(cookieKey)) {
                    setCookie(cookieKey, '', 0);
                }
                return false;
            }

            dynamicTpl({
                posDom: $('#csdn-toolbar')
            }, function (opts) {
                var optDom, adDom, closeDom, adDomLink;
                optDom = opts.optDom;
                adDom = opts.tplDom;
                closeDom = $('#js_advert_close');
                adDomLink = adDom.find('.advert-a').get(0);

                // 点击关闭
                closeDom.on('click', function () {
                    advertClose(adDom);
                    return false;
                })

                // 验证是否存在
                if (getCookie(cookieKey)) {
                    adDom.addClass('advert-cur');
                    return false;
                } else {
                    adDom.addClass('advert-ex');
                }

                // start animation
                setMove(function () {
                    // 缩小
                    adDom.removeClass('advert-ex');
                    return true;
                }, function (flg) {
                    setMove(function () {
                        adDom.addClass('advert-cur');
                        return true;
                    }, 200)
                    // 写入cookie
                    if (flg) {
                        if (!getCookie(cookieKey)) {
                            setCookie(cookieKey, '', cookieTime);
                        }
                    }
                }, t);
            });

            // 控制动画流程
            function setMove(beforeCallback, callback, t) {
                var flg;
                if (!beforeCallback) {
                    return false;
                }
                if (callback && typeof callback === 'number' && Number(callback, 10) > 0) {
                    t = callback;
                }

                if (!t) {
                    return false;
                }

                setTimeout(function () {
                    flg = beforeCallback();
                    if (flg) {
                        typeof callback === 'function' && callback(flg);
                    }
                }, t);
            }

            // cookie设定
            function setCookie(key, value, t) {
                var oDate = new Date();
                var dayTime = oDate.getDate() + t;
                var v;
                oDate.setDate(dayTime);
                v = value || oDate.toGMTString();
                document.cookie = key + '=' + encodeURIComponent(v) + '; max-age=' + t + '; domain=' + domain;
                return true;
            }

            // 获得cookie
            function getCookie(key) {
                var cookies = document.cookie;
                var a = cookies.split('; '), b, c;
                for (var i = 0; i < a.length; i++) {
                    if (a[i]) {
                        b = a[i].split('=');
                        if (b[0] === key) {
                            c = b[1];
                            break;
                        }
                    }
                }
                return c;
            }

            // close
            function advertClose(adDom) {
                adDom.addClass('hide');
            }

            // control show advert date
            function adverControlDate() {
                var iDate, curTime, advertStart, advertEnd, flg = false;
                iDate = new Date();
                curTime = iDate.getTime();
                advertStart = new Date(advertDate.start).getTime();
                advertEnd = new Date(advertDate.end).getTime();
                if (curTime >= advertStart && curTime < advertEnd) {
                    flg = true;
                }
                return flg;
            }

            // control tpl
            function dynamicTpl(options, callback) {
                var opts, optDom, adDom, styl, tpl;
                opts = options ? options : null;
                if (!opts) {
                    return false;
                }
                optDom = opts.posDom ? opts.posDom instanceof jQuery ? opts.posDom : $(opts.posDom) : null;
                if (!optDom) {
                    return false;
                }
                styl = '<style type="text/css">' +
                    '.hide{display:none;}' +
                    '.advert-bg{background-color:#000;}' +
                    '.advert{width: 1200px;margin: 0 auto;position:relative;line-height: 0;font-size: 0;}' +
                    '.advert .advert-log{display:none;width:90px;height:22px;background-image: url("//csdnimg.cn/public/publick_img/gitchat_logo.png");background-repeat: no-repeat;background-size: contain;position:absolute;top:25px;left:27px;z-index: 10;}' +
                    '.advert .advert-a{display:inline-block;width:100%;height:80px;background-repeat: no-repeat;background-size: contain;background-position: 50% 0;opacity:0;-webkit-transition: all 0.2s linear;-o-transition: all 0.2s linear;-ms-transition: all 0.2s linear;-moz-transition: all 0.2s linear;transition: all 0.2s linear;}' +
                    '.advert-cur .advert-a{background-image: url("//csdnimg.cn/public/publick_img/ad_20180622_toolbar80.jpg") !important;opacity:1;}' +
                    '.advert-ex .advert-a{height:200px;opacity:1;}' +
                    '.csdn-bbs .advert .advert-close, .advert .advert-close{position:absolute;z-index:10;right: 2%;top: 15%;color: #fff;font-size: 22px;}' +
                    '</style>';
                tpl = '<div class="advert-bg"><div class="advert" id="advert"><a href="https://edu.csdn.net/promotion_activity" data-report-query="id=3&utm_source=618qztt" class="advert-log"></a><a href="https://edu.csdn.net/promotion_activity?id=3&utm_source=618qztt" style="background-image: url(//csdnimg.cn/public/publick_img/ad_20180622_toolbar200.jpg);" class="advert-a"></a><a href="" class="advert-close" id="js_advert_close">&times</a></div></div>';

                if (optDom.length <= 0) {
                    return false;
                }

                optDom = optDom[0];
                adDom = $(tpl);
                document.head.insertBefore($(styl)[0], document.head.getElementsByTagName('title')[0]);
                document.body.insertBefore(adDom[0], optDom);
                callback && typeof callback === 'function' && callback({ optDom: $(optDom), tplDom: adDom });
            }
        }
        // run
        controlToolBarBBs();
        advert();
    })
    // hover
    $(function () {
        var moreHover = {
            showMore: function () {
                var $dom = $('.show-more .more');
                if ($dom.is(":animated")) {
                    $dom.stop(true, true).fadeIn(200);
                }
                $dom.stop(true, true).fadeIn(200);
            },
            hideMore: function () {
                var $dom = $('.show-more .more');
                if ($dom.is(":animated")) {
                    $dom.stop(true, true).fadeIn(200);
                }
                $dom.stop(true, true).fadeOut(300);
            }
        }
        var userHover = {
            showMore: function (tagBox, tag) {
                var $dom = $(tagBox);
                if ($dom.is(":animated")) {
                    $dom.stop(true, true).fadeIn(200);
                }
                $dom.stop(true, true).fadeIn(200);
                tag && $(tag).hide()
            },
            hideMore: function (tagBox) {
                var $dom = $(tagBox);
                if ($dom.is(":animated")) {
                    $dom.stop(true, true).fadeIn(200);
                }
                $dom.stop(true, true).fadeOut(300);
            }
        }
        var appHover = {
            showMore: function () {
                var $dom = $(this).find('.appControl');
                if ($dom.is(":animated")) {
                    $dom.stop(true, true).fadeIn(200);
                }
                if (isFestivalClass()) {
                  $(this).find('.link-title').css('color', '');
                } else {
                  $(this).find('.link-title').css('color', '#222429');
                }
                $dom.stop(true, true).fadeIn(200);
            },
            hideMore: function () {
                var $dom = $(this).find('.appControl');
                if ($dom.is(":animated")) {
                    $dom.stop(true, true).fadeIn(200);
                }
                $(this).find('.link-title').css('color', '')
                $dom.stop(true, true).fadeOut(300);
            }
        }
        $('.show-more').hover(moreHover.showMore, moreHover.hideMore)
        $('.userLogin').hover(function () {
            userHover.showMore('.userControl', '.guo_tip_box')
        }, function () {
            userHover.hideMore('.userControl')
        })
        $('.baidu-app-btn').hover(function () {
            userHover.showMore('.baiduControl')
        }, function () {
            userHover.hideMore('.baiduControl')
        })
        $('.app-app').hover(appHover.showMore, appHover.hideMore)
        $('.app-edu').hover(appHover.showMore, appHover.hideMore)
        $('.app-gitchat').hover(appHover.showMore, appHover.hideMore)
        $('.guo_tip_box').hover(function () {
            $(this).css('display', 'none')
            $.get('https://statistic.csdn.net/toolbar/followTipsclose');
        })
        // 消息下拉
        $('#msgBox').hover(function () {
            userHover.showMore('#msgList')
            hasLogin && $("#msgList-other").show()
        }, function () {
            userHover.hideMore('#msgList')
        })
        if (!getCookie('c_adb')) {
            var importCheck = document.createElement('script');
            importCheck.type = "text/javascript";
            importCheck.src = 'https://g.csdnimg.cn/check-adb/1.0.5/check-adb.js';
            document.head.appendChild(importCheck);
        }
    })
    // search
    $(function () {
        // 获取 网站位置
        function getT () {
          var host = window.location.host;
          var title = host.split('.')[0];
          var t = '';
          switch (title) {
            case 'www':
              t = '';
              break;
            case 'blog':
              t = 'blog';
              break;
            case 'blog':
              t = 'codes_snippet';
              break;
            case 'bbs':
              t = 'discuss';
              break;
            case 'download':
              t = 'doc';
              break;
            case 'ask':
              t = 'ask';
              break;
            case 'gitchat':
              t = 'gitchat';
              break;
            case 'geek':
              t = 'news';
              break;
            case 'edu':
              t = 'course';
              break;
            default:
              t = '';
          }
          // 如果是三级域名（个性化域名）时 做一下处理
          if (host.indexOf('.blog.csdn.net') > -1) {
            t = 'blog';
          }
          return t;
        }
        /**
         * 尝试获取用户名，拼接搜索链接
         * @return {[type]} [description]
         */
        function get_user_name() {
            var addusername = "&u=";
            if (is_bloger()) {
                addusername += username;
            }
            return addusername;
        }
        function goFn(obj, txt) {

            var searchTxt = encodeURIComponent(txt),
                blogUrl = "https://blog.csdn.net/xufive/columnpay/9506563?utm_source=payColumnSearch"
                url = "//so.csdn.net/so/search/s.do?q=" + searchTxt + "&t=" + getT() + get_user_name();
            if (txt === searchWordArr[0]) {
               window.open(blogUrl)
               return
            }
            if (searchWordArr.indexOf(txt) > -1) {
                url = "//so.csdn.net/so/search/s.do?q=" + searchTxt + "&t=course" + get_user_name();
            }
            if (searchTxt == '') {
                return false;
            } else {
                window.open(url)
            }
        }
        var searchBtn = $(".btn-search"),
            searchInpt = $(".input_search"),
            _this = this,
            dropDownMenu = $('#search_bar__drop-down-menu');
        //高亮当前导航
        var myNav = document.getElementById("nav-left-menu").getElementsByTagName("a");
        var currenturl = document.location.href;
        currenturl = currenturl.substr(currenturl.indexOf('/'));
        if (currenturl.indexOf('//edu.csdn.net') != -1) {
            input_placeholder = "搜索学院课程"
            $('#toolber-keyword')[0].setAttribute('placeholder', input_placeholder)
        }
        //学院搜索
        function eduSearch(obj, txt) {
            var searchTxt = encodeURIComponent(txt),
                url = "https://so.csdn.net/so/search/s.do?q=" + searchTxt + "&t=course"
            if (searchTxt == '') {
                return false;
            } else {
                window.open(url)
            }
        }
        for (var i = 0; i < myNav.length; i++) {
            var aurl = myNav[i].getAttribute("href");
            aurl = aurl.substr(aurl.indexOf('/'));
            if(currenturl.indexOf("apps/download")>-1){
                myNav[10].className = "active";
            }
            if (currenturl.indexOf(aurl) != -1 && aurl != '//mall.csdn.net') {
                // 包含vip
                myNav[i].className = "active";
                myNav[0].className = "";
            }
            if (currenturl.indexOf('//mall.csdn.net') != -1 && currenturl.indexOf('vip') == -1) {
                if (currenturl.indexOf('//mall.csdn.net/coupon') != -1) {
                    myNav[10].className = "active";
                } else {
                    myNav[8].className = "active";
                }
            }

            if (currenturl.indexOf('//www.csdn.net') != -1 && currenturl.indexOf('app') !== -1) {
                myNav[1].className = "";
            }
        }
        searchBtn.on("click", function (e) {
            if (currenturl.indexOf('//edu.csdn.net') != -1) {
                eduSearch($(this), $(this).prev("input").val())
            }
            updataSearchHistory(searchInpt.val(), dropDownMenu);
            goFn($(this), $(this).prev("input").val());
            e.preventDefault();
        });

        searchInpt.keyup(function (event) {
            var evCode = event.keyCode;
            if (evCode == 13) {
                updataSearchHistory(searchInpt.val(), dropDownMenu);
                var searchTxt = encodeURIComponent($(this).val()),
                    url = "//so.csdn.net/so/search/s.do?q=" + searchTxt + "&t=" + getT() + get_user_name();
                window.open(url)
            }
        });
        $('#toolber-keyword')[0].addEventListener('focus', function () {
            $('#toolber-keyword')[0].setAttribute('placeholder', searchWord)
            $('#toolber-keyword')[0].removeAttribute('value')
            getDropDownMenuData(dropDownMenu);
            if (searchInpt.val() === '') { // 输入框值为空时展示自己的下拉菜单
              dropDownMenu.show();
              buriedPoint({ mod: 'popu_369' });
            }
        });
        $('#toolber-keyword')[0].addEventListener('blur', function () {
            $('#toolber-keyword')[0].setAttribute('placeholder', searchWord)
            $('#toolber-keyword')[0].setAttribute('value', searchWord)
        });

        // 监听搜索框input事件 为空时显示自己的下拉菜单 不为空时显示百度的
        searchInpt.on('input', function () {
          if ($(this).val() !== '') {
            dropDownMenu.hide();
          } else {
            getDropDownMenuData(dropDownMenu);
            dropDownMenu.show();
            buriedPoint({ mod: 'popu_369' });
          }
        });

        // 获取下拉菜单数据（热门+搜索记录）
        function getDropDownMenuData (el) {
          getSearchHistoryData(el);
          getHotSearchData(el);
        }

        // 获取热门搜索数据
        function getHotSearchData (el) {
          $.ajax({
            url: 'https://redisdatarecall.csdn.net/recommend/soHotWord',
            type: 'get',
            data: {},
            contentType: 'application/json',
            dataType: 'json',
            success: function (res) {
              if (res.status) {
                el.find('.hot-search-menu').nextAll().remove();
                var data = res.data,
                    html = '',
                    className = '';
                for (var i = 0; i < data.length; i++) {
                  className = data[i].hot ? ' class="hot"' : '';
                  html += '<dd' + className + '>' + data[i].word + '</dd>';
                }
                el.find('.hot-search').append(html);
              }
            },
            error: function (err) {}
          });
        }

        // 更新搜索记录
        function updataSearchHistory (val, el) {
          addSearchHistory(val);
          getSearchHistoryData(el);
        }

        // 获取搜索记录
        function getSearchHistoryData (el) {
          el.find('.search-history-menu').nextAll().remove();
					var html = '',
							searchHistoryArray = getSearchHistoryArray('searchHistoryArray'),
              len = searchHistoryArray.length > 5 ? 5 : searchHistoryArray.length;
          if (len === 0) {
            el.find('.search-history').hide();
            return;
          }
          for (var i = 0; i < len; i++) {
            html += '<dd>' + searchHistoryArray[i] + '</dd>';
          }
          el.find('.search-history').append(html);
          el.find('.search-history').show();
        }

        // 新增搜索记录 存到cookie里
        // 先查找有没有 如果有的话放到数组最前面 超过5个删除最后一个
        function addSearchHistory (val) {
					val = filterHtmlTags(val);
					var searchHistoryArray = getSearchHistoryArray('searchHistoryArray'),
          		searchIndex = $.inArray(val, searchHistoryArray);
          if (searchIndex > -1) {
            searchHistoryArray.splice(searchIndex, 1);
          }
          if ($.trim(val) !== '') {
            searchHistoryArray.unshift(val);
          }
          if (searchHistoryArray.length > 5) {
            searchHistoryArray.pop();
          }
					updataSearchHistoryArray('searchHistoryArray', searchHistoryArray);
        }

        // 过滤html标签
        function filterHtmlTags (str) {
					return str.replace(/</g,'&lt;').replace(/>/g,'&gt;');
        }

        // 清空搜索记录 searchHistory设置为空数组
        function clearSearchHistory () {
					updataSearchHistoryArray('searchHistoryArray', []);
				}

				// 更新搜索记录 重新设置cookie
				function updataSearchHistoryArray (cookieName, searchHistoryArray) {
					setCookie(cookieName, encodeURIComponent(JSON.stringify(searchHistoryArray)));
				}
				
				// 从cookie里获取搜索记录
				function getSearchHistoryArray (cookieName) {
					var searchHistoryArray = getCookie(cookieName);
					return searchHistoryArray ? JSON.parse(decodeURIComponent(searchHistoryArray)) : [];
				}

				// 埋点（toolbar下拉菜单显示时、点击下拉菜单某一条时触发）
				function buriedPoint (trackdata) {
					try {
						csdn && csdn.report && csdn.report.reportClick(trackdata);
					} catch (error) {
						console.log(error);
					}
				}

        // 点击清空按钮
        dropDownMenu.find('.clear').on('click', function () {
          clearSearchHistory();
          dropDownMenu.find('.search-history').hide();
          dropDownMenu.find('.search-history-menu').nextAll().remove();
        });

        // 点击热门搜索/搜索记录 跳转
        $(document).on('click', '#search_bar__drop-down-menu dl dd', function (e) {
          var target = $(e.target),
              parentClassName = target.parent().attr('class');
          $('#toolber-keyword').val(target.text());
          searchBtn.click();
          dropDownMenu.hide();
          if (parentClassName === 'hot-search') {
            buriedPoint({ mod: 'popu_845' });
          }
          if (parentClassName === 'search-history') {
            buriedPoint({ mod: 'popu_846' });
          }
        });

        // 点击空白区域 隐藏下拉菜单
        $(document).on('click', function (e) {
          var target = $(e.target);
          if (target.closest('.search_bar').length === 0) {
            dropDownMenu.hide();
          }
        });

        // 绑定删除方法
        $(document).on('click', '.toolbar_delete_bar', function () {
            $('.input_search').val(' ')
        })

        $(document).on('click', '.btn_clear', function () {
            $('.input_search').val('');
            transform_search_icon()
            isInputSearch = false
        })
        if (hasLogin) {
            $("#layerd").find("div.layer_close").click(function () {
                if (confirm("成为CSDN VIP会员,即刻享受全站免广告特权,前往购买?")) { window.location.href = "https://mall.csdn.net/vip" }
                return false
            })
            var boxDefault = $("div.box-box-default"),
                boxLarge = $("div.box-box-large"),
                boxAways = $("div.box-box-aways");
            boxDefault.find("a.btn-remove").click(function () {
                if (confirm("成为CSDN VIP会员,即刻享受全站免广告特权,前往购买?")) { window.location.href = "https://mall.csdn.net/vip" }
                return false
            });
            boxLarge.find("a.btn-remove").click(function () {
                if (confirm("成为CSDN VIP会员,即刻享受全站免广告特权,前往购买?")) { window.location.href = "https://mall.csdn.net/vip" }
                return false
            });
            boxAways.find("a.btn-remove").click(function () {
                if (confirm("成为CSDN VIP会员,即刻享受全站免广告特权,前往购买?")) { window.location.href = "https://mall.csdn.net/vip" }
                return false
            });
        }
        function transform_search_icon() {
            if ($('.btn-search').length > 0) {
                $('.btn-search').detach();
                $('.search_bar').append('<a href="javascript:;" class="btn-nobg-noborder btn_clear"><i class="iconfont-toolbar toolbar-guanbi1"></i></a>');
            } else {
                $('.btn_clear').detach();
                $('.search_bar').append('<a href="//so.csdn.net/so/" target="_blank" class="btn-nobg-noborder btn-search"><i class="iconfont-toolbar toolbar-sousuo"></i></a>');
            }
        }
        // toolbar_prompt_hover($('.write-bolg-btn a'),'写博客')
        // toolbar_prompt_hover($('.gitChat a'),'发布Chat')
        // toolbar_prompt_hover($('.search_bar'),'err')
        function toolbar_prompt_hover(e, text) {
            e = e instanceof jQuery ? e : $(e);
            e.css({ 'position': 'relative' })
            var con = {
                    e: e,
                    text: text,
                    isbind: false
                },
                isHave = false;
            this.events = this.events ? this.events : [con];
            for (var i = 0; i < this.events.length; i++) {
                if (this.events[i].e[0] == e[0]) {
                    this.events[i].text = text;
                    this.events[i].e.children('.toolbar-prompt-box').text(this.events[i].text);
                    isHave = true;
                }
            }
            if (!isHave) {
                this.events.push(con);
                isHave = false
            }
            if (!this.events[this.events.length - 1].isbind) {
                toolbar_binding(this.events[this.events.length - 1])
                this.events[this.events.length - 1].isbind = true;
            }
        }
        function toolbar_binding(evens) {
            var even = evens.e;
            t = evens.text;
            even.append(toolbar_tpls(t))
            var even_children = even.children('.toolbar-prompt-box'),
                even_children_w = even_children.width(),
                even_width = even.width();
            even_children.css({ 'left': -((even_children_w + 16 - even_width) / 2) }).children().css({ 'left': (even_children_w + 16) / 2 - 5 })
            even.hover(toolbar_prompt_show, toolbar_prompt_hide)
        }
        function toolbar_tpls(t) {
            var tpl = '<div class="toolbar-prompt-box">\
                  <div class="arrow"></div>\
                  <span>'+ t + '</span>\
                </div>';
            return tpl;
        }
        function children_show(t) {
            $(t).children('.toolbar-prompt-box').fadeIn(500)
        }
        var clearTime;//计时器id
        function toolbar_prompt_show() {
            if ($(this).children('.toolbar-prompt-box').is(":animated")) {
                $(this).children('.toolbar-prompt-box').stop(true, true).css({ 'display': 'block' })
            } else {
                clearTime = setTimeout(children_show, 1000, this);
            }
        }
        function toolbar_prompt_hide() {
            clearTimeout(clearTime)
            $(this).children('.toolbar-prompt-box').fadeOut(500)

        }
        // 阻止
        $(document).on('click', '.prevent_a', function (e) {
            e.preventDefault();
        });
        function loadScript(url, callback) {
            var script = document.createElement("script")
            script.type = "text/javascript";
            if (script.readyState) { //IE
                script.onreadystatechange = function () {
                    if (script.readyState == "loaded" || script.readyState == "complete") {
                        script.onreadystatechange = null;
                        callback();
                    }
                };
            } else { //Others
                script.onload = function () {
                    callback();
                };
            }
            script.src = url;
            document.getElementsByTagName("head")[0].appendChild(script);
        }

        loadScript("//csdnimg.cn/search/baidu_opensug-1.0.1.js", function () {
            BaiduSuggestion.bind("toolber-keyword", {
                "XOffset": "0",
                "YOffset": "-5",
                "fontSize": "14px",		//文字大小
                "width": 260,
                "line-height": "35px",
                "padding": "0 10px",
                'color': "#666C7A",
                "borderColor": "#fff", 	//提示框的边框颜色
                "bgcolorHI": "#F0F1F2",		//提示框高亮选择的颜色
                "sugSubmit": false		//在选择提示词条是是否提交表单
            }, function () {
              searchBtn.click();
            });
        });
    })
})(jQuery);

function throttle(func, wait) {//节流函数
    let lastTime = null;
    let timeout;
    return function() {
        let context = this;
        let now = new Date();
        // 如果上次执行的时间和这次触发的时间大于一个执行周期，则执行
        if (now - lastTime - wait > 0) {
            // 如果之前有了定时任务则清除
            if (timeout) {
                clearTimeout(timeout);
                timeout = null;
            }
            func.apply(context, arguments);
            lastTime = now;
        } else if (!timeout) {
            timeout = setTimeout( function() {
                // 改变执行上下文环境
                func.apply(context, arguments);
            }, wait);
        }
    };
}
function  configuration_tool_parameterv(option){//tollbar暴露给外部调用的接口  参数为对象形式 
    if(Object.prototype.toString.call(option) !='[object Object]'){ //安全验证 非对象传参直接返回
        return
    }
    var need_first_suspend = option.need_first_suspend || false;
    var need_little_suspend = option.need_little_suspend || false;
    var little_tool_id = option.little_tool_id || '';
    var little_need_insert_type = option.little_need_insert_type || '';
    var need_change_function = option.need_change_function || ''
    var $suspend_dom = $("#csdn-toolbar");
    var dom_str = ''
    var $insert_dom = ''
    var $insert_place_dom = ''
    if((need_first_suspend == true)&&(need_little_suspend == true)){
        return 
    }
    if(need_little_suspend && little_tool_id != ''){
        $suspend_dom = $(little_tool_id);
    }
    if(need_little_suspend&&little_tool_id&&little_need_insert_type != '') {
        dom_str = "." + little_need_insert_type;
        $insert_dom = $(dom_str);
    }
    var windowThrottle = throttle(function(){
        var scrollTop = document.documentElement.scrollTop || document.body.scrollTop;
        if(scrollTop>=50){
            $suspend_dom.css({"position":"fixed","top":"0","left":"0","z-index":"9999","width":"100%"});
            if(need_little_suspend){
                if($('.secodn_level_csdn_logo').length){
                    $('.secodn_level_csdn_logo').css({"display":"block"})
                }
            }
            if(need_little_suspend && little_need_insert_type != ''){
                if(little_need_insert_type == 'onlySearch') {
                    $('#csdn_tool_otherPlace').append($insert_dom);
                }else if (little_need_insert_type == 'onlyUser'){
                    $('#csdn_tool_otherPlace').append($insert_dom);
                }else if (little_need_insert_type == 'searchUser'){
                    $('#csdn_tool_otherPlace').append($('.onlySearch'));
                    $('#csdn_tool_otherPlace').append($('.onlyUser'));
                }
            }
            if(typeof need_change_function === 'function'){
                need_change_function('fixed');
            }
        }else {
            $suspend_dom.css({"position":"relative","z-index":""});
            if(need_little_suspend){
                if($('.secodn_level_csdn_logo').length){
                    $('.secodn_level_csdn_logo').css({"display":"none"})
                }
            }
            if(need_little_suspend && little_need_insert_type!= ''){
                if(little_need_insert_type == 'onlySearch') {
                    $('#nav-left-menu').append($insert_dom);
                }else if (little_need_insert_type == 'onlyUser'){
                    $('.pull-right').append($insert_dom);
                }else if (little_need_insert_type == 'searchUser'){
                    $('#nav-left-menu').append($('.onlySearch'));
                    $('#csdn_container_tool').append($('.onlyUser'));
                }
            }
            if(typeof need_change_function === 'function'){
                need_change_function('noFixed');
            }
        }
    },80)
    if((document.documentElement.scrollTop || document.body.scrollTop)>50 ){
        windowThrottle();
    }
    if(need_first_suspend) {
        $(window).on("scroll",windowThrottle) ;//第一栏悬浮,后续操作全部终止
        return 
    }else if(need_little_suspend){
        $(window).on("scroll",windowThrottle) ;
    }

}
window.csdn = window.csdn || {};
window.csdn.configuration_tool_parameterv = configuration_tool_parameterv;

//url_check 
jQuery(function(){var r=[];jQuery("a[href*='?']").each(function(e,n){var t=jQuery(n).attr("href");if(-1===t.indexOf("csdn.net")&&-1===t.indexOf("iteye.com"))return!0;!function(e){var n=["//www.csdn.net","//blog.csdn.net","//me.csdn.net","//edu.csdn.net","//download.csdn.net","//ask.csdn.net","//huiyi.csdn.net","//iteye.com"],t=!1;for(var r in n)if(-1<e.indexOf(n[r])){t=!0;break}return t}(t)||r.push(t)}),0<=r.length&&(jQuery.ajax({url:"https://statistic.csdn.net/seo/spiderfriendly",type:"get",crossDomain:!0,xhrFields:{withCredentials:!1},data:{urls:r}}))});