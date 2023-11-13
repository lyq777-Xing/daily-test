import request from '@/utils/request'
export default {
    getList:function(params) {
        return request({
            url: '/book/information/list',
            method: 'get',
            params
        })
    },
    update:function(params) {
        return request({
            url: '/book/information',
            method: 'PUT',
            params
        })
    },
    add:function(params) {
        return request({
            url: '/book/information',
            method: 'post',
            params
        })
    },
    remove:function(id) {
        return request({
            url: '/book/information',
            method: 'delete',
            params: {
                id: id
            }
        })
    }
}
