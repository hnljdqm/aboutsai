//============= 文章详情
// 获取id
var id = document.location.search.substring(3);
var articleInfo = document.querySelector('.article');
console.log(articleInfo)
// axios.get('http://rap2api.taobao.org/app/mock/8027/GET//api/blog/articles/%7Bid%7D')
axios.get('/api/blog/articles/'+id)
  .then(function (response) {
    // console.log(response.data)
    var info = {
      result:response.data
    }
    articleInfo.innerHTML = template("articleInfoTem",info);
  })
  .catch(function (error) {
    console.log(error);
  });

