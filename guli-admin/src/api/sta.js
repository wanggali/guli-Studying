import request from '@/utils/request'
export default {
    //1 生成统计数据
    createStaData(day) {
        return request({
            url: '/statistics/daily/registerCount/'+day,
            method: 'post'
          })
    },
    //2 获取统计数据
    getDataSta(searchObj) {
        return request({
            url: `/statistics/daily/getShowData/${searchObj.type}/${searchObj.begin}/${searchObj.end}`,
            method: 'get'
          })
    }
}
