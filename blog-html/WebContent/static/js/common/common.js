var Blog = {
	//appUrl : 'http://www.aboutsai.com:8080/app',
	appUrl : 'localhost:8080/blog-web',
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
	},
	//头部信息
	header : function() {
		var webRoot = Blog.webRoot();
		var header = '';
		header += '<header>';
			header += '<h1>';
				header += '<img src="'+webRoot+'/static/images/java.png" alt="" width="150"><br />';
				header += '<a href="'+webRoot+'/index.html">邓宏誉的个人博客</a>';
			header += '</h1>';
			header += '<ul>';
				header += '<li><a href="'+webRoot+'/index.html">Blog</a></li>';
				header += '<li><a href="'+webRoot+'/pages/about.html">About</a></li>';
				header += '<li><a target="_blank" href="https://github.com/hnljdqm">Github</a></li>';
			header += '</ul>';
			header += '<div><input type="text"><span></span></div>';
		header += '</header>';
		return header;
	},
	//尾部信息
	footer : function() {
		var footer = '';
		footer += '<footer>';
			footer += '<p>COPYRIGHT © ABOUTSAI.COM 苏ICP备16027294号-1</p>';
		footer += '</footer>';
		return footer;
	}
}
