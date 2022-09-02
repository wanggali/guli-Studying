import request from '@/utils/request'
export default {
    //1 生成统计数据
    createStaData(day) {
        return request({
            url: '/sta/sta/registerCount/'+day,
            method: 'post'
          })
    },
    //2 获取统计数据
    getDataSta(searchObj) {
        return request({
            url: `/sta/sta/getShowData/${searchObj.type}/${searchObj.begin}/${searchObj.end}`,
            method: 'get'
          })
    }
}
