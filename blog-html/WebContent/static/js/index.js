//============= 文章标题列表 
var articleList = document.querySelector('div.article-list ul');
// axios.get('http://rap2api.taobao.org/app/mock/8027/GET//api/blog/articles')
 axios.get(Blog.appUrl + '/api/blog/articles?pageSize=10000&pageNum=1')
  .then(function (response) {
    articleList.innerHTML = template("articleListTem",response.data);    
  })

//=============== 文章分类列表
var navUl = document.querySelector('nav ul');
// axios.get('http://rap2api.taobao.org/app/mock/8027/GET//api/blog/catalogs')
 axios.get(Blog.appUrl + '/api/blog/catalogs')
  .then(function (response) {
    navUl.innerHTML = template("categoryTem",response.data);
    var lis = navUl.querySelectorAll('li');
    console.log(lis);
    for(var i=0; i<lis.length; i++){
      lis[i].index = i;
      lis[i].id = lis[i].getAttribute("data-category-id");
      lis[i].onclick = function(){
        // axios.get('http://rap2api.taobao.org/app/mock/8027/GET//api/blog/articles')
     axios.get(Blog.appUrl + '/api/blog/articles?pageSize=10000&pageNum=1&catalogId='+this.id)
      .then(function (response) {
          articleList.innerHTML = template("articleListTem",response.data);        
        })
      }
    }
  })