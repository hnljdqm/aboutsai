var Blog = {
//	appUrl : 'http://localhost:8080/blog_web',
	appUrl : 'https://www.aboutsai.com/blog-web',
//	appUrl : 'http://www.aboutsai.com:8080/app',
	//js获取项目根路径，如： http://localhost:8080/blog-html/index.html
	webRoot : function() {
		// js webcontext
		/*var webroot = document.location.href;
		webroot = webroot.substring(webroot.indexOf('//') + 2, webroot.length);
		webroot = webroot.substring(webroot.indexOf('/') + 1, webroot.length);
		webroot = webroot.substring(0, webroot.indexOf('/'));
		var rootpath = "/" + webroot;
		console.log(rootpath);
		return rootpath;*/
		
		
		return "";
//		return "/blog-html";
	},
	//头部信息
	header : function() {
		var webRoot = Blog.webRoot();
		var header = '';
		header += '<header>';
//			header += '<button class="login">login</button>';
//			header += '<div class="dialog">username:<input type="text"><br />password:<input type="password"><br /><button class="confirm">确定</button><button class="cancel">取消</button></div>'
			header += '<h1>';
				header += '<img src="'+webRoot+'/static/images/java.png" alt="" width="150"><br />';
				header += '<a href="'+webRoot+'/index.html">邓宏誉的个人博客</a>';
			header += '</h1>';
			header += '<ul>';
				header += '<li><a href="'+webRoot+'/index.html">Blog</a></li>';
				header += '<li><a href="'+webRoot+'/pages/about.html">About</a></li>';
				header += '<li><a target="_blank" href="https://github.com/hnljdqm">Github</a></li>';
			header += '</ul>';
			header += '<div class="search"><input type="text"><span></span></div>';
		header += '</header>';
		return header;
	},
	//尾部信息
	footer : function() {
		var footer = '';
		footer += '<footer>';
			footer += '<p>COPYRIGHT © ABOUTSAI.COM <a href="http://jscainfo.miitbeian.gov.cn" target="_blank" class="ml14">苏ICP备19006764号</a></p>';
		footer += '</footer>';
		return footer;
	}
}

// 点击登陆
//var loginBtn = document.querySelector('header button.login');
//var dialog = document.querySelector('header div.dialog');
//var cancel = document.querySelector('header .dialog button.cancel');
//var confirm = document.querySelector('header .dialog button.confirm');
//var username = document.querySelectorAll('header input')[0];
//var password = document.querySelectorAll('header input')[1];

//loginBtn.onclick = function (){
//	dialog.style.display = 'block';	
//};
//cancel.onclick = function (){
//	dialog.style.display = 'none';
//};
//confirm.onclick = function (){
//	axios.post(Blog.appUrl + '/api/blog/login', {
//		userName: username.value,
//		password: password.value
//	  })
//	.then(function (response) {
//		if(response.data.success){
//			var webRoot = Blog.webRoot();
//			console.log(webRoot)
//			location.href = "./pages/backPage.html"
//		}else{
//			dialog.style.display = 'none';
//		}
//	})
//}
