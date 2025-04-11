const base = {
    get() {
        return {
            url : "http://localhost:8080/jubenshaguanli/",
            name: "jubenshaguanli",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/jubenshaguanli/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "剧本杀预约系统"
        } 
    }
}
export default base
