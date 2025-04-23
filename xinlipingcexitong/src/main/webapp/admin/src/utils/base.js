const base = {
    get() {
        return {
            url : "http://localhost:8080/xinlipingcexitong/",
            name: "xinlipingcexitong",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/xinlipingcexitong/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "心理测评系统"
        } 
    }
}
export default base
