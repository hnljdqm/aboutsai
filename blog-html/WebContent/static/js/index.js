//============= 文章标题列表 
var articleList = document.querySelector('.article-list');
console.log(articleList)
//axios.get('http://rap2api.taobao.org/app/mock/8027/GET//api/blog/articles')
 axios.get('/api/blog/articles?pageSize=10000&pageNum=1')
  .then(function (response) {
    articleList.innerHTML = template("articleListTem",response.data);
  })

//=============== 文章分类列表
var navUl = document.querySelector('nav ul');
//axios.get('http://rap2api.taobao.org/app/mock/8027/GET//api/blog/catalogs')
 axios.get('/api/blog/catalogs')
  .then(function (response) {
    navUl.innerHTML = template("categoryTem",response.data);
  })