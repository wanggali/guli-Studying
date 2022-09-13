import request from '@/utils/request'
export default {
  getListBanner() {
    return request({
      url: '/cms/bannerFront/getAllBanner',
      method: 'get'
    })
  }
}
