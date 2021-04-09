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

    // 根据登录是存的cookie, 显示用户信息

    let user = document.querySelector('#user')
    let role = document.querySelector('#role')
    let graduation = document.querySelector('#graduation')
    let major = document.querySelector('#major')

    const userInfo = sessionStorage.getItem('userInfo')
    const data = JSON.parse(decodeURI(userInfo))
    if(data.name == null) user.parentNode.className='d-none'
    user.innerHTML = data.name
    role.innerHTML = data.role

    const userRole = data.role
    // 根据登录角色，显示相应模块
    if (userRole == '学生') {
        let myAspir = document.querySelector('#myAspir')
        graduation.innerHTML = data.graduation + '届'
        major.innerHTML = data.major + "  " + data.className + '班'
        myAspir.className = 'nav-sub-menu'
    }
    if (userRole == '教师') {
        let teacherCenter = document.querySelector('#teacher-center')
        teacherCenter.className = 'nav-menu'
        graduation.innerHTML = data.jobTitle
        major.innerHTML = data.major + "  " + data.degree
    }
    if (userRole == '管理员') {
        let adminCenter = document.querySelector('#admin-center')
        let userCenter = document.querySelector('#user-center')
        adminCenter.className = 'nav-menu'
        userCenter.className = 'd-none'
    }
    // 创建梳子导航
    crumbNav()

}

function doLogout() {
    axios.get("/logout")
        .then(location.replace("http://localhost/loginReg.html"))
}