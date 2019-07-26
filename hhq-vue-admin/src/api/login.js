import request from '@/utils/request'

export function login(userName, password) {
  return request({
    url: 'login',
    method: 'post',
    data: {
      userName,
      password
    }
  })
}

export function getInfo(token) {
  return request({
    url: '/user/getUserInfo',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/logOut',
    method: 'post'
  })
}
