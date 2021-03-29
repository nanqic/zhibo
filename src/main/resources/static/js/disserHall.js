initData()

// 创建表格
function create(did, teacher, name, count, status, path) {
    const tbody = document.querySelector('.tbody')
    const tr = document.createElement('tr')
    let td = document.createElement("td");
    let span = document.createElement('span')
    span.className = 'text-danger'
    span.innerHTML = count + '人'
    for (let i = 1; i <= 5; i++) {
        tr.appendChild(td.cloneNode(true))
    }
    tr.firstChild.innerHTML = did
    tr.children[1].innerHTML = teacher
    tr.children[1].className = 'link-primary'
    tr.children[2].innerHTML = name
    // 监听点击
    tr.children[1].onclick = () => teacherInfo(teacher)
    tr.children[2].onclick = () => showOnline(did, name, path)
    tr.children[2].className = 'link-primary'
    tr.children[3].appendChild(span)
    tr.children[4].innerHTML = status
    tbody.appendChild(tr)
}

// 加载数据
function initData(i = 1) {
    axios.get('/disser/list/' + i + '/10')
        .then(resp => {
            let data = resp.data.data
            createTable(data);
            //生成翻页下标
            for (let p = 1; p <= data.pages; p++) {
                initPageList(p)
            }
        })
}

// 创建表格
function createTable(data) {
    data.records.forEach((col) => {
        let s = '进行中';
        col.status == 0 ? s = s : s = '已完成'
        create(col.did, col.teacher, col.name, col.count, s, col.path)
    })
}

// 在线预览题目
function showOnline(did, name, path) {
    let modalLabel = document.querySelector('#exampleModalLabel')
    let obj = document.querySelector('object')
    modalLabel.innerHTML = name
    obj.data = 'http://localhost/' + path
    let submitAsp = document.querySelector('#submit')
    submitAsp.onclick = () => {
        // 读取cookie信息
        let userInfo = docCookies.getItem('user')
        let uid = JSON.parse(userInfo).uid
        // console.log(did,uid)
        let data = new FormData()
        data.append("did", did)
        data.append("uid", uid)
        axios.post("/disser/submit", data)
            .then(() => {
                alert("志愿选择成功！")
            })
    }
    let myModal = new bootstrap.Modal(document.getElementById('exampleModal'), {
        keyboard: false
    })
    myModal.show()
}

// 显示老师信息
function teacherInfo(teacherName) {
    const name = document.querySelector('#name')
    const dept = document.querySelector('#dept')
    const major = document.querySelector('#major')
    const phone = document.querySelector('#phone')
    const jobTitle = document.querySelector('#job-title')
    const degree = document.querySelector('#degree')
    axios.get("/user/teacherInfo/" + teacherName)
        .then(resp => {
            // console.log(resp);
            let data = resp.data.data
            name.value = data.name
            dept.value = data.dept
            major.value = data.major
            phone.value = data.phone
            jobTitle.value = data.zhicheng
            degree.value = data.degree
        })

    let t = new bootstrap.Modal(document.getElementById('teacherModal'))
    t.show()
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
function reloadData(i) {
    axios.get("/disser/list/" + i + "/10")
        .then(resp => {
            let data = resp.data.data
            // 移除旧的节点
            const table = document.querySelector('.table')
            const tbody = document.querySelector('.tbody')
            if (tbody != undefined) {
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
            currentPage == 1 ? prevPage.className = ('page-item disabled') : prevPage.className = ('page-item')
            currentPage == totalPage ? nextPage.classList.add('disabled') : nextPage.className = ('page-item')
            nextPage.onclick = () => toNextPage(currentPage, totalPage)
            prevPage.onclick = () => toPrevPage(currentPage)
        })
}

function toNextPage(currentPage, totalPage) {
    const pages = document.querySelectorAll('.page-item')
    if (currentPage < totalPage) reloadData(++currentPage)
    resetActive()
    pages[currentPage].classList.add('active')

}

function toPrevPage(currentPage) {
    const pages = document.querySelectorAll('.page-item')
    if (currentPage > 1) {
        reloadData(--currentPage)
        resetActive()
        pages[currentPage].classList.add('active')
    }
}

// 重置激活按钮
function resetActive() {
    let actived = document.querySelector('.active')
    if (actived != null) actived.classList.remove('active')
}