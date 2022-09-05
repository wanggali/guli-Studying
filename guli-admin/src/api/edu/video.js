import request from '@/utils/request'
export default {

    //添加小节
    addVideo(video) {
        return request({
            url: '/edu/video/addVideo',
            method: 'post',
            data: video
          })
    },

    //删除小节同时删除视频
    deleteVideo(id) {
        return request({
            url: `/edu/video/${id}`,
            method: 'delete'
          })
    },
    //更新小节
    updateVideo(id) {
        return request({
      url: `/edu/video/updateVideo`,
      method: 'post'
    })
  },
}
