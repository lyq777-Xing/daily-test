import request from '@/utils/request'
export default {
    getList:function(params) {
        return request({
            url: '/book/information/list',
            method: 'get',
            params
        })
    },
   
}
