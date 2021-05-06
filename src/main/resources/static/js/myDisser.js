import statusList from "./statusList.js";

let i = 1;
let totalPage = 1

// 创建表格节点
function create(did, name, status, path) {
    const tbody = document.querySelector('.tbody')
    let tr = document.createElement('tr')
    let td1 = document.createElement('td')
    let td0 = document.createElement('td')
    let td2 = document.createElement('td')
    let td3 = document.createElement('td')
    let btn0 = document.createElement('button')
    let a = document.createElement('a')
    btn0.className = 'btn btn-primary'
    btn0.innerHTML = '下载'
    td1.innerHTML = did
    a.innerHTML = name
    a.href = '/upload/' + path
    td0.innerHTML = statusList[status]
    td0.className = 'text-info'
    td2.appendChild(a)
    td3.appendChild(btn0)
    if (status == 0) {
        let btn = document.createElement('button')
        btn.className = 'btn btn-danger'
        btn.innerHTML = '删除'
        btn.style.marginLeft = '20px'
        td3.appendChild(btn)
        // 监听删除按钮
        btn.onclick = () => deleteDisser(did, path)
    }
    tr.appendChild(td1)
    tr.appendChild(td2)
    tr.appendChild(td0)
    tr.appendChild(td3)
    tbody.appendChild(tr)
    // 监听下载按钮
    btn0.onclick = () => download(name, path)
}

// 获取数据
window.loadData = function () {
    if (i >= 1 && i < totalPage) ++i
    axios.get('/teacher/' + i + '/3')
        .then(resp => {
            let data = resp.data.data
            data.records.forEach((col) => {
                create(col.did, col.name, col.status, col.path)
            })
            totalPage = data.pages
            const nav = document.querySelector('#nav')
            const a = document.querySelector('.page-link')

            if (totalPage > 1) {
                nav.className = 'd-flex justify-content-center'
            }
            if (i == totalPage) {
                nav.removeChild(a)
                nav.innerHTML = '已加载全部'
            }
        })
}
window.loadData()

// 下载题目文档
function download(name, path) {
    axios.get("/file/disser?path=" + path, {
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
                    let fileName = '题目：' + name + '.pdf';
                    executeDownload(data, fileName);
                }
            };
            reader.readAsText(data);
        })
}

// 删除题目
function deleteDisser(did, path) {
    let params = new FormData()
    params.append("did", did)
    params.append("path", path)
    if (confirm("确认删除Id为" + did + "的课题吗？")) {
        axios.delete("/teacher/", {data: params})
            .then(resp => {
                if (resp.status === 200) {
                    window.location.reload()
                }
            })
    }
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
