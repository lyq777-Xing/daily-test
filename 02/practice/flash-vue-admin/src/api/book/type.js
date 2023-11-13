import request from '@/utils/request'
export default {
    getList:function(params) {
        return request({
            url: '/book/type/list',
            method: 'get',
            params
        })
    },
    update:function(params) {
        return request({
            url: '/book/type',
            method: 'PUT',
            params
        })
    },
    add:function(params) {
        return request({
            url: '/book/type',
            method: 'post',
            params
        })
    },
    remove:function(id) {
        return request({
            url: '/book/type',
            method: 'delete',
            params: {
                id: id
            }
        })
    }
}
