// 初始化页面数据
initData()
// 动态创建card
function newCard(title, text,teacher, date) {
    //绑定父节点
    const cardGroup = document.querySelector('div.card-group')
    //创建元素
    const card = document.createElement('div')
    const cardBody = document.createElement('div')
    const cardTitle = document.createElement('h5')
    const cardText = document.createElement('p')
    const cardFooter = document.createElement('div')
    const showDate = document.createElement('small')

    //添加文本元素
    title = document.createTextNode(title)
    text = document.createTextNode(text)
    date = document.createTextNode(teacher+"  " + date)
    //给元素设置类名
    card.className = "card"
    cardBody.className = "card-body"
    cardTitle.className = "card-title"
    cardText.className = "card-text"
    cardFooter.className = "card-footer"
    showDate.className = "text-muted"

    // 追加子节点
    cardGroup.appendChild(card)
    card.appendChild(cardBody)
    card.appendChild(cardFooter)

    cardBody.appendChild(cardTitle)
    cardBody.appendChild(cardText)
    cardTitle.appendChild(title)
    cardText.appendChild(text)

    cardFooter.appendChild(showDate)
    showDate.appendChild(date)
    showDate.style.marginRight = "1rem"
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
        refreshCard(i)
        // 重置激活按钮
        resetActive()
        li.classList.add('active')
    }
}
function initData() {
    axios.get("/anno/page/1/3")
        .then(resp => {
            data = resp.data.data
            // 生成信息卡片
            data.records.forEach(r => {
                // 格式化后端传来的时间
                let jTiem = new Date(r.updateTime).toJSON()
                let fTime =new Date(+new Date(jTiem)+8*3600*1000).toISOString().replace(/T/g,' ').replace(/\.[\d]{3}Z/,'');

                newCard(r.title, r.content, r.name,fTime)
            })
            //生成翻页下标
            for (let p = 1; p <= data.pages; p++) {
                initPageList(p)
            }
            const nextPage = document.getElementById('next-page')
            nextPage.onclick=()=> toNextPage(data.current,data.pages)
            const pageList = document.querySelector("#page-list")
            pageList.children[1].classList.add('active')
        })
}
// 刷新卡片数据
function refreshCard(i){
    axios.get("/anno/page/" + i + "/3")
        .then(resp => {
            data = resp.data.data
            // 把新数据放到卡片
            const titles = document.querySelectorAll('.card-title')
            let j =0
            data.records.forEach(record=>{
                titles[j].innerHTML = record['title']
                j++
            })
            const cards = document.querySelectorAll(".card")
            switch (data.records.length){
                case 1:
                    cards[2].classList.add('invisible')
                    cards[1].classList.add('invisible')
                    break
                case 2:
                    cards[2].classList.add('invisible')
                    break
                case 3:
                    cards.forEach((card)=>card.className = 'card')
                    break
            }

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
    if(currentPage<totalPage) refreshCard(++currentPage)
    resetActive()
    pages[currentPage].classList.add('active')

}
function toPrevPage(currentPage){
    const pages = document.querySelectorAll('.page-item')
    if(currentPage>1) {
        refreshCard(--currentPage)
        resetActive()
        pages[currentPage].classList.add('active')
    }
}
// 重置激活按钮
function resetActive(){
    let actived = document.querySelector('.active')
    if(actived!=null) actived.classList.remove('active')
}
