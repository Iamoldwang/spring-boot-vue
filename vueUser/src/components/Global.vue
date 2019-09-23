<script>

let httpRequestUrl = null
let websocketUrl = null

if (process.env.NODE_ENV == 'development') {
  httpRequestUrl = 'http://localhost:8088/user'
  websocketUrl = 'ws://127.0.0.1:8002'
} else if (process.env.NODE_ENV == 'debug') {
  httpRequestUrl = 'http://localhost:8088/ibs'
  websocketUrl = 'ws://127.0.0.1:8002'
} else if (process.env.NODE_ENV == 'production') {
  httpRequestUrl = 'http://ibs.dechnic.com:9090/ibs'
  websocketUrl = 'ws://ibs.dechnic.com:8002'
}
const showFileUrl = httpRequestUrl + '/file/showFile?path=';

export default {
  httpRequestUrl,
  websocketUrl,
  showFileUrl

}
</script>
