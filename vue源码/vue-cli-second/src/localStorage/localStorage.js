// 重写localStorage，并设置过期时间
let getItem = localStorage.getItem.bind(localStorage)
let setItem = localStorage.setItem.bind(localStorage)
let removeItem = localStorage.removeItem.bind(localStorage)
localStorage.getItem = function (keyName) {
  let expires = getItem(keyName + '_expires')
  let curTime=new Date().getTime()
  if (expires && (curTime > expires)) {
    removeItem(keyName)
    removeItem(keyName + '_expires')
  }
  return getItem(keyName)
}
localStorage.setItem = function (keyName, keyValue, expires) {
  if (typeof expires !== 'undefined') {
    let expiresDate = new Date(expires).valueOf()
    setItem(keyName + '_expires', expiresDate)
  }
  return setItem(keyName, keyValue)
}