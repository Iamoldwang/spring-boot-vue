
import commonApi from '../axios/axios'


//日志
export function recordLogs(content) {

  let userType = sessionStorage.getItem("userType")
  let type = ''
  if(userType == 'student'){
    type = 1
  }else if(userType == 'teacher'){
    type = 2
  }else if(userType == 'admin'){
    type = 3
  }
  let params = {
    personId: sessionStorage.getItem("userId"),
    personName: sessionStorage.getItem("name"),
    content: content,
    type:type
  }

  commonApi.post('/log/insert', params, response => {

  });
}

/**
 * 同步获取数据
 */
export function syncApi(uri, param) {
  return new Promise((resolve, reject) =>{
    commonApi.post(
      uri,
      param,
      function(response) {
        if (0 === response.data.code) {
          resolve(response.data.content);
        }
        reject([]);
      }
    );
  });
}


//获取当前时间，支持多种格式
export function getCurrentFormatDate(format){
  var date = new Date();
  var year = date.getFullYear();
  var month = date.getMonth() + 1;
  var day = date.getDate();
  var hour = date.getHours();
  var min = date.getMinutes();
  var sec = date.getSeconds();
  if (month >= 1 && month <= 9) {
      month = "0" + month;
  }
  if (day >= 1 && day <= 9) {
    day = "0" + day;
  }
  if (hour >= 1 && hour <= 9) {
    hour = "0" + hour;
  }
  if (min >= 1 && min <= 9) {
    min = "0" + min;
  }
  if (sec >= 1 && sec <= 9) {
    sec = "0" + sec;
  }
  var currentdate = '';
  if(format == 'yyyy'){
    currentdate = year;
  }else if(format == 'yyyy-MM-dd'){
    currentdate = year + "-" + month + "-" + day
  }else if(format == 'yyyy-MM-dd HH:mm:ss'){
    currentdate = year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + sec;
  }else if(format == 'yyyyMMddHHmmss'){
    currentdate = year + "" + month + "" + day + "" + hour + "" + min + "" + sec;
  }
  return currentdate;
}

export function generateRandomArr(startNum, endNum, count) {
  var ret = [];
  for (var i = 0; i < count; i++) {
    ret[i] = Math.floor(Math.random() * Math.floor(endNum));
  }
  return ret;
}


//时间比较（yyyy-MM-dd）
export function compareDate(startDate, endDate) {
  var arrStart = startDate.split("-");
  var startTime = new Date(arrStart[0], arrStart[1], arrStart[2]);
  var startTimes = startTime.getTime();
  var arrEnd = endDate.split("-");
  var endTime = new Date(arrEnd[0], arrEnd[1], arrEnd[2]);
  var endTimes = endTime.getTime();
  if (endTimes < startTimes) {
    return false;
  }
  return true;
}
//时间比较（yyyy-MM-dd HH:mm:ss）
export function compareTime(startTime, endTime) {
  var startTimes = startTime.substring(0, 10).split('-');
  var endTimes = endTime.substring(0, 10).split('-');
  startTime = startTimes[1] + '-' + startTimes[2] + '-' + startTimes[0] + ' ' + startTime.substring(10, 19);
  endTime = endTimes[1] + '-' + endTimes[2] + '-' + endTimes[0] + ' ' + endTime.substring(10, 19);
  var thisResult = (Date.parse(endTime) - Date.parse(startTime)) / 3600 / 1000;
  if (thisResult < 0) {
    return true;
  } else {
    return false;
  }
}

export function GMTToStr_YMD(time) {
  let date = new Date(time)
  let Str = date.getFullYear() + "-" +
    (date.getMonth() + 1) + "-" +
    date.getDate()
  return Str
}
export function GMTToStr_YMDHMS(time) {
  let date = new Date(time)
  let Str = date.getFullYear() + "-" +
    (date.getMonth() + 1) + "-" +
    date.getDate() + " " +
    date.getHours() + ":" +
    date.getMinutes() + ":" +
    date.getSeconds()
  return Str
}

export function StrToGMT(time) {
  let GMT = new Date(time)
  return GMT
}

export default {
  recordLogs,
  getCurrentFormatDate,
  generateRandomArr,
  compareTime,
  compareDate,
  GMTToStr_YMD,
  GMTToStr_YMDHMS,
  StrToGMT
}
