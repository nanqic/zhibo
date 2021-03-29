initData()
// 创建表格节点
function create(did, name, path) {
    const tbody = document.querySelector('.tbody')
    let tr = document.createElement('tr')
    let td1 = document.createElement('td')
    let td2 = document.createElement('td')
    let td3 = document.createElement('td')
    let btn0 = document.createElement('button')
    let btn = document.createElement('button')
    let input = document.createElement('input')
    input.type = 'hidden'
    input.value = path
    btn0.className = 'btn btn-primary'
    btn0.innerHTML = '下载'
    btn.className = 'btn btn-danger'
    btn.innerHTML = '删除'
    btn.style.marginLeft = '20px'
    td1.innerHTML = did
    td2.innerHTML = name
    td3.appendChild(btn0)
    td3.appendChild(input)
    td3.appendChild(btn)
    tr.appendChild(td1)
    tr.appendChild(td2)
    tr.appendChild(td3)
    tbody.appendChild(tr)
    // 监听下载按钮
    btn0.onclick = ()=> download(name,path)
    // 监听删除按钮
    btn.onclick = ()=> deleteDisser(did,path)
}
// 创建表格
function createTable(data){
    data.records.forEach((col) => {
        create(col.did,col.name,col.path)
    })
}
// 下载题目文档
function download(name,path){
    console.log(name);
    axios.get("/disser/download?path=" + path,{
        responseType: 'blob'
    })
        .then(res => {
            let reader = new FileReader();
            let data = res.data;
            reader.onload = e => {
                if (e.target.result.indexOf('Result') != -1 && JSON.parse(e.target.result).Result == false) {
                    // 进行错误处理
                } else {
                    let contentDisposition = res.headers['content-disposition'];
                    // 以题目为文件命名
                    let fileName = '题目：'+name +'.pdf';
                    executeDownload(data, fileName);
                }
            };
            reader.readAsText(data);
        })
}
// 删除题目
function deleteDisser(did,path){
    if (confirm("确认删除Id为"+did+"的课题吗？")) {
        axios.post("/disser/delete?did="+parseInt(did)+"&path="+path)
            .then(resp => {
                if (resp.status ===200){
                    window.location.reload()
                }
            })
    }
}
// 获取数据
function initData() {
    // 读取cookie信息
    let userInfo = docCookies.getItem('user')
    let uid = JSON.parse(userInfo).uid
    axios.get('/disser/my/1/7?uid='+uid)
        .then(resp => {
            let data = resp.data.data
            // console.log(data);
            data.records.forEach((col) => {
                create(col.did, col.name,col.path)
            })
            //生成翻页下标
            for (let p = 1; p <= data.pages; p++) {
                initPageList(p)
            }
        })
}
// 初始化翻页下标
function initPageList(i) {
    const pageList = document.querySelector("#page-list")
    const next = document.querySelector("#next-page")
    const li = document.createElement('li')
    const a = document.createElement('a')
    li.className = 'page-item'
    a.className = 'page-link'
    a.innerHTML = i
    li.appendChild(a)
    pageList.insertBefore(li, next)

    // 添加监听点击事件
    a.onclick = () => {
        reloadData(i)
        // 重置激活按钮
        resetActive()
        li.classList.add('active')
    }
}
// 刷新卡片数据
function reloadData(i){
    axios.get("/disser/list/" + i + "/7")
        .then(resp => {
            let data = resp.data.data
            // 移除旧的节点
            const table = document.querySelector('.table')
            const tbody = document.querySelector('.tbody')
            if(tbody!=undefined){
                table.removeChild(tbody)
            }
            // 生成新的节点
            const body = document.createElement('tbody')
            body.className = 'tbody'
            table.appendChild(body)
            createTable(data)
            // 监听上下翻页
            currentPage = data.current;
            totalPage = data.pages;

            const prevPage = document.getElementById('prev-page')
            const nextPage = document.getElementById('next-page')
            currentPage==1?prevPage.className=('page-item disabled'):prevPage.className=('page-item')
            currentPage==totalPage?nextPage.classList.add('disabled'):nextPage.className=('page-item')
            nextPage.onclick=()=> toNextPage(currentPage,totalPage)
            prevPage.onclick=()=> toPrevPage(currentPage)
        })
}
function toNextPage(currentPage,totalPage){
    const pages = document.querySelectorAll('.page-item')
    if(currentPage<totalPage) reloadData(++currentPage)
    resetActive()
    pages[currentPage].classList.add('active')

}
function toPrevPage(currentPage){
    const pages = document.querySelectorAll('.page-item')
    if(currentPage>1) {
        reloadData(--currentPage)
        resetActive()
        pages[currentPage].classList.add('active')
    }
}
// 重置激活按钮
function resetActive(){
    let actived = document.querySelector('.active')
    if(actived!=null) actived.classList.remove('active')
}
//  模拟点击a 标签进行下载
function executeDownload(data, fileName) {
    if (!data) {
        return
    }
    let url = window.URL.createObjectURL(new Blob([data]));
    let link = document.createElement('a');
    link.style.display = 'none';
    link.href = url;
    link.setAttribute('download', fileName);
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
}
