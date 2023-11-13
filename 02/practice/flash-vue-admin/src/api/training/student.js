import request from '@/utils/request'
export default {
    getList:function(params) {
        return request({
            url: '/training/student/list',
            method: 'get',
            params
        })
    },
    add:function(params) {
        return request({
            url: '/training/student',
            method: 'post',
            params
        })
    },
    update:function(params) {
        return request({
            url: '/training/student',
            method: 'PUT',
            params
        })
    },
    remove:function(id) {
        return request({
            url: '/training/student',
            method: 'delete',
            params: {
                id: id
            }
        })
    }
}
