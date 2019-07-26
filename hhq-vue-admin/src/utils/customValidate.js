import  validate from './validate.js'

export default {
  /*qq号*/
  isQQ: (rule, value, callback) => {
    if((value || '')!='') {
      if(!validate('qq',value)) {
        callback(new Error('您输入的QQ号不正确!'))
      } else {
        callback()
      }
    } else{
      callback();
    }
  },
  /*手机号*/
  isMobile: (rule, value, callback) => {
    if((value || '')!='') {
      if(!validate('mobile',value)) {
        callback(new Error('您输入的手机号不正确!'))
      } else {
        callback()
      }
    } else{
      callback();
    }
  },
  /*座机号*/
  isPhone:(rule, value, callback) => {
    if((value || '')!='') {
      if(!validate('phone',value)) {
        callback(new Error('您输入的座机号不正确!'))
      } else {
        callback()
      }
    } else{
      callback();
    }
  },
  /*身份证号*/
  isCardID:(rule, value, callback) => {
    if((value || '')!='') {
      if(!validate('cardid',value)) {
        callback(new Error('您输入的身份证号不正确!'))
      } else {
        callback()
      }
    } else{
      callback();
    }
  },
  /*数字（正整数和0）*/
  isInteger: (rule, value, callback) => {
    if((value || '')!='') {
      if(!validate('integer',value)) {
        callback(new Error('请输入数字!'))
      } else {
        callback()
      }
    } else{
      callback();
    }
  },
  /*保留两位小数*/
  isMoneynum:(rule, value, callback) => {
    if((value || '')!='') {
      if(!validate('moneynum',value)) {
        callback(new Error('请输入正确的数字，最多保留两位小数!'))
      } else {
        callback()
      }
    } else{
      callback();
    }
  },

}
