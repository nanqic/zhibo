initData()
let currentPage=1;
let totalPage;
// 搜索题目
function search(){
    const words = document.querySelector('#words').value
    axios('/search?words='+words)
        .then(resp => {
            resetNodes(resp);
            let nav = document.querySelector('#nav')
            nav.parentNode.removeChild(nav)
        })
}

/*
 创建表格节点
 */
function create(did, teacher, name, status, path) {
    const tbody = document.querySelector('.tbody')
    const tr = document.createElement('tr')
    let td = document.createElement("td");
    for (let i = 1; i <= 4; i++) {
        tr.appendChild(td.cloneNode(true))
    }
    tr.firstChild.innerHTML = did
    tr.children[1].innerHTML = teacher
    tr.children[1].className = 'link-primary'
    tr.children[2].innerHTML = name
    // 监听点击
    tr.children[1].onclick = () => teacherInfo(teacher)
    tr.children[2].onclick = () => showOnline(did, name, path, status)
    tr.children[2].className = 'link-primary'
    tr.children[3].innerHTML = status
    tr.children[3].className = 'text-success'
    tbody.appendChild(tr)
}

/*
 初始化数据，先请求第一页数据，再根据页数生成翻页下标
 */
function initData(i = 1) {
    axios.get('/dissers/' + i + '/5')
        .then(resp => {
            let data = resp.data.data
            totalPage = resp.data.data.pages;
            createTable(data);
            if (totalPage<=1){
                const nav = document.querySelector('#nav')
                nav.className = 'd-none'
            }
            //生成翻页下标
            for (let p = 1; p <= data.pages; p++) {
                initPageList(p)
            }
            const nextPage = document.getElementById('next-page')
            nextPage.onclick = () => toNextPage(currentPage, totalPage)
            const pages = document.querySelectorAll('.page-item')
            pages[1].classList.add('active')
        })
}

// 创建表格
function createTable(data) {
    data.records.forEach((col) => {
        let s = '待选择';
        col.status == 0 ? s = s : s = '已完成'
        create(col.did, col.teacher, col.name, s, col.path)

    })
}

// 在线预览题目
function showOnline(did, name, path, status) {
    // console.log(status)
    let btn = document.querySelector('#submit')
    const userInfo = sessionStorage.getItem('userInfo')
    const data = JSON.parse(decodeURI(userInfo))
    const userRole = data.role
    // 根据登录角色，显示相应模块
    if (userRole == '学生' && status == '待选择') {
        let btn = document.querySelector('#submit')
        btn.className = 'btn btn-success'
    }else {
        btn.className = 'd-none'
    }
    let modalLabel = document.querySelector('#exampleModalLabel')
    let obj = document.querySelector('object')
    modalLabel.innerHTML = name
    obj.data = 'http://localhost/upload/' + path
    let submitAsp = document.querySelector('#submit')
    submitAsp.onclick = () => {
        let data = new FormData()
        data.append("did", did)
        axios.post("/student/save", data)
            .then((resp) => {
                if (resp.data.status == 200) {
                    alert("志愿选择成功！")
                }else {
                    alert("志愿已选过！")
                }

            })
            .then(() => location.reload())
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
    axios.get("/info/" + teacherName)
        .then(resp => {
            // console.log(resp);
            let data = resp.data.data
            name.value = data.name
            dept.value = data.dept
            major.value = data.major
            phone.value = data.phone
            jobTitle.value = data.jobTitle
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

function resetNodes(resp){
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
}
/*
 刷新卡片数据，5为默认每页显示条数
 */
function reloadData(i) {
    axios.get("/dissers/" + i + "/5")
        .then(resp => {
            resetNodes(resp)
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