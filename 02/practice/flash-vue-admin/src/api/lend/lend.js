import request from '@/utils/request'
export default {
    getList:function(params) {
        return request({
            url: '/lend/lendlist/list',
            method: 'get',
            params
        })
    },
    // update:function(params) {
    //     return request({
    //         url: '/book/information',
    //         method: 'PUT',
    //         params
    //     })
    // },
    // add:function(params) {
    //     return request({
    //         url: '/lend/lendlist',
    //         method: 'post',
    //         params
    //     })
    // },
    // remove:function(id) {
    //     return request({
    //         url: '/lend/lendlist',
    //         method: 'delete',
    //         params: {
    //             id: id
    //         }
    //     })
    // }
}
