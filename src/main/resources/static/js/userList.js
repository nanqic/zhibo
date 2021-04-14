loadData(1, true)

function create(username, name, role, enabled) {
    let tbody = document.querySelector('.tbody')
    let tr = document.createElement('tr')
    let td = document.createElement('td')
    let statusText = document.createElement('span')
    let btnSetEnable = document.createElement('button')

    statusText.style.marginRight = '20px'
    if (enabled == 0) {
        statusText.className = 'text text-danger'
        btnSetEnable.className = 'btn btn-primary'
        statusText.innerHTML = '禁用'
        btnSetEnable.innerHTML = '启用'
        btnSetEnable.onclick = () => enableUser(username)
    } else {
        statusText.className = 'text text-success'
        btnSetEnable.className = 'btn btn-warning'
        statusText.innerHTML = '正常'
        btnSetEnable.innerHTML = '禁用'
        btnSetEnable.onclick = () => disableUser(username)
    }

    let btnDelete = document.createElement('button')
    let btnReset = document.createElement('button')
    btnDelete.className = 'btn btn-danger'
    btnReset.className = 'btn btn-success'
    btnDelete.style.marginRight = '20px'
    btnDelete.innerHTML = '删除'
    btnReset.innerHTML = '重置'
    btnDelete.onclick = () => deleteUser(username)
    btnReset.onclick = () => resetUser(username)

    for (let i = 1; i <= 5; i++) {
        tr.appendChild(td.cloneNode(td))
    }
    tr.children[0].innerHTML = username
    tr.children[1].innerHTML = name
    tr.children[2].innerHTML = role
    tr.children[3].appendChild(statusText)
    tr.children[3].appendChild(btnSetEnable)
    tr.children[4].appendChild(btnDelete)
    tr.children[4].appendChild(btnReset)
    tbody.appendChild(tr)
}

// 搜索用户
let i = 1
let total =1
function search() {
    const username = document.querySelector('#username').value
    if(username!=null && username!='')
    doSearch( username)
}

function doSearch(username) {
    axios('/admin/search/' + i + '/10?username=' + username)
        .then(resp => {
            const data = resp.data.data
            total = data.pages
            renderData(data, 1)
        }).then(() => {
        let nav = document.querySelector('#nav')
        let pageList = document.querySelector('#page-list')
        if (pageList!=undefined)nav.removeChild(pageList)

        if (i==1&&i<total){
            let a = document.createElement('a')
            a.className = 'page-link'
            a.innerHTML = '下一页'
            a.onclick=()=>loadMore()
            nav.appendChild(a)
        }else {
            let a = document.querySelector('.page-link')
            a.innerHTML = '已经是最后一页'
            a.onclick = ()=>false
        }


    })
}

function loadMore() {
    if(i<total){
        i++
    }else {
        alert()
    }
    search()
}

// 加载数据
function loadData(i = 1, bool = false) {
    axios.get('/admin/users/' + i + '/7')
        .then(resp => {
            let data = resp.data.data
            renderData(data, i, bool)
            if (i == 1) {
                createPages(data, true)
            } else {
                createPages(data, false)
            }
        })
}

// 渲染数据
function renderData(data, i = 1, bool) {
    let table = document.querySelector('.table')
    let tbody = document.querySelector('.tbody')

    if (tbody != undefined) {
        table.removeChild(tbody)
    }
    let body = document.createElement('tbody')
    body.className = 'tbody'
    table.appendChild(body)
    data.records.forEach(col => {
        create(col.username, col.name, col.roleName, col.enabled)
    })
}

function createPages(data, bool = false) {
    // 初始化翻页下标
    if (bool) {
        for (let p = 1; p <= data.pages; p++) {
            initPageList(p)
            const pageList = document.querySelector("#page-list")
            pageList.children[1].className = "page-item active"
        }
    }
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

// 删除用户
function deleteUser(username) {
    if (confirm("确认删除账号为：" + username + " 的用户吗？")) {
        axios.patch('/admin/delete/' + username)
            .then(alert("删除成功！"))
            .then(location.reload())
    }
}

// 重置用户
function resetUser(username) {
    if (confirm("确认重置用户名为 " + username + " 的用户吗？")) {
        axios({
            method: 'patch',
            url: '/admin/reset/' + username
        })
            .then(resp => {
                if (resp.msg == 'ok') alert('重置成功！')
            })
    }
}

// 禁用用户
function disableUser(username) {
    if (confirm("确认禁用户名为：" + username + " 的用户吗？")) {
        axios.patch('/admin/disable/' + username)
            .then(alert("禁用成功！"))
            .then(location.reload())
    }

}

// 禁用用户,取消禁用用户
function enableUser(username) {
    if (confirm("确认取消禁用账号为：" + username + " 的用户吗？")) {
        axios.patch('/admin/enable/' + username)
            .then(alert("启用成功！"))
            .then(location.reload())
    }

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
        loadData(i)
        // 重置激活按钮
        resetActive()
        li.classList.add('active')
    }

}

function toNextPage(currentPage, totalPage) {
    const pages = document.querySelectorAll('.page-item')
    if (currentPage < totalPage) loadData(++currentPage)
    resetActive()
    pages[currentPage].classList.add('active')

}

function toPrevPage(currentPage) {
    const pages = document.querySelectorAll('.page-item')
    if (currentPage > 1) {
        loadData(--currentPage)
        resetActive()
        pages[currentPage].classList.add('active')
    }
}

// 重置激活按钮
function resetActive() {
    let actived = document.querySelector('.active')
    if (actived != null) actived.classList.remove('active')
}