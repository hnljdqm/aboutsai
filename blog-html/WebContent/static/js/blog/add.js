
//CKEDITOR.replace();   

CKEDITOR.replace("article", {
    toolbarGroups : [
        { name: 'clipboard',   groups: [ 'clipboard', 'undo' ] },
        { name: 'editing',     groups: [ 'find', 'selection', 'spellchecker' ] },
        { name: 'links' },
        { name: 'insert' },
        { name: 'forms' },
        { name: 'tools' },
        { name: 'document',    groups: [ 'mode', 'document', 'doctools' ] },
        { name: 'others' },
        '/',
        { name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
        { name: 'paragraph',   groups: [ 'list', 'indent', 'blocks', 'align', 'bidi' ] },
        { name: 'styles' },
        { name: 'colors' },
        { name: 'about' }
    ]
});

//=============== 文章分类列表
var sel = document.getElementById('select-classify');
//axios.get('http://rap2api.taobao.org/app/mock/8027/GET//api/blog/catalogs')
  axios.get(Blog.appUrl + '/api/blog/catalogs')
  .then(function (response) {
    sel.innerHTML = template("categoryTem",response.data);
        catalogId = sel.value;
        console.log(catalogId)
        sel.onchange = function(){
            catalogId = this.value;
        }
  })

//============== createBtn 提交文章
var titleInput = document.getElementById('artName');
var titleValue = '';
var articleContent = '';
var articleType = '';
var isRecommend = '';
var articleLabel = '';
var catalogId = '';
titleInput.onchange = function(){
    titleValue = titleInput.value;
    console.log(titleValue);
};
// document.getElementById('articleContent').onchange = function(){
//     // articleContent = this.value;
//     console.log(1111);
// };
articleContent = CKEDITOR.instances.article.getData()

articleType = document.getElementById('resource').value;
document.getElementById('resource').onchange = function(){
    articleType = this.value;
}

isRecommend = document.getElementById('tuijian').value;
document.getElementById('tuijian').onchange = function(){
    isRecommend = this.value;
}

document.getElementById('articleLabel').onchange = function(){
    articleLabel = this.value;
};

var createBtn = document.querySelector('.createBtn');
createBtn.onclick = function (){
    axios.post(
//        'http://rap2api.taobao.org/app/mock/8027/POST//api/blog/articles',
         Blog.appUrl + '/api/blog/articles',
        {
            title:titleValue, // 主题
            articleContent:CKEDITOR.instances.article.getData(), // 内容
            articleType:articleType, // 来源
            isRecommend:isRecommend, // 是否推荐, 0：不推荐，1：推荐
            articleLabel:articleLabel, // 标签
            catalogId:catalogId // 分类
    
        }
    )
    //  axios.post()
      .then(function (response) {
        //   alert('发表成功');
        // sel.innerHTML = template("categoryTem",response.data);
        location.href="../index.html"
      })
}

   