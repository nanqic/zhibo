function doRegister() {
    const name = document.getElementById('reg-name').value
    const username = document.getElementById('reg-account').value
    const password = document.getElementById('reg-password').value

    axios({
        method: 'post',
        url: '/register',
        data: {
            name: name,
            username: username,
            password: password
        },
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        }
    })
        .then(resp => {
            let regInfo = document.querySelector('#reg-info')
            regInfo.innerHTML = ""
            if (resp.data.status == 200) {
                regInfo.style.color = "#0dcaf0"
                regInfo.innerHTML = "注册成功，2秒后自动关闭"
                refresh()

                async function refresh() {
                    await new Promise(() => {
                        setTimeout(() => {
                            location.replace('http://localhost/loginReg.html')
                        }, 2000)
                    })
                }
            } else {
                // 如果注册不成功，提示错误消息
                regInfo.style.color = 'red'
                regInfo.innerHTML = resp.data.msg
            }
        })
}

function doLogin() {
    // 先将表单数据存到对象中
    const params = new FormData()
    params.append("username", document.getElementById('login-account').value)
    params.append("password", document.getElementById('login-password').value)
    axios({
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        method: 'post',
        url: '/login',
        data: params
    })
        .then(resp => {
            let loginInfo = document.querySelector('#login-info')
            loginInfo.innerHTML = ""
            console.log(resp);
            if (resp.data == 'error') {

                loginInfo.style.color = 'red'
                loginInfo.innerHTML = "登录失败，用户名或密码错误"

            } else {
                loginInfo.style.color = "#0dcaf0"
                loginInfo.innerHTML = "登录成功！ " + "2秒后自动跳转到首页"
                // 登录成功后跳转到首页
                refresh()

                async function refresh() {
                    await new Promise(() => {
                        setTimeout(() => {
                            window.location.replace('http://localhost')
                        }, 2000)
                    })
                }
            }
        })
}
