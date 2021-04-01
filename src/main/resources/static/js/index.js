init()
// 重新加载iframe内容
function reloadIframe() {
    let iframe = document.getElementById('iframe');
    iframe.contentWindow.location.reload(true);
}

/*
梳子导航
*/
function crumbNav() {
    const crumbSubMenus = document.querySelectorAll('.nav-sub-menu')
    const menu = document.querySelector('#nav-menu')
    const subMenu = document.querySelector('#nav-sub-menu')
    crumbSubMenus.forEach((s) => {
        s.addEventListener('click', (e) => {
            menu.innerHTML = s.parentNode.previousElementSibling.innerText
            subMenu.innerHTML = s.innerText
        })
    })
}

/*
更换iframe页面
*/

function changeIframe(src) {
    let iframe = document.querySelector('iframe')
    iframe.setAttribute('src', src)
}

function init() {
    // 显示系统时间
    let date = new Date()
    const options = {year: 'numeric', month: 'long', day: 'numeric', hour: 'numeric', minute: 'numeric'};
    document.querySelector('#sys-time').innerHTML = date.toLocaleDateString(undefined, options)

    // 如果检测到登录cookie, 获取用户信息
    if (document.cookie != "") {
        let user = document.querySelector('#user')
        let role = document.querySelector('#role')
        let graduation = document.querySelector('#graduation')
        let major = document.querySelector('#major')
        axios("/user/loginRes")
            .then(resp => {
                const data = resp.data.data
                user.innerHTML = data.name
                role.innerHTML = data.roleName
                graduation.innerHTML = data.graduation
                major.innerHTML = data.major+"  "+data.className
            })
        const userRole = docCookies.getItem("userRole")
        if (userRole=='教师'){
            let teacherCenter = document.querySelector('#teacher-center')
            teacherCenter.className='nav-menu'
        }
        if (userRole=='管理员'){
            let adminCenter = document.querySelector('#admin-center')
            adminCenter.className='nav-menu'
        }
        // 创建梳子导航
        crumbNav()
    }
}
function doLogout() {
    axios.get("/logout")
        .then(location.replace("http://localhost/loginReg.html"))
}