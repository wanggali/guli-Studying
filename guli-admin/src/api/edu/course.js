import request from '@/utils/request'
export default {
    //1 添加课程信息
    addCourseInfo(courseInfo) {
        return request({
            url: '/edu/course/addCourseInfo',
            method: 'post',
            data: courseInfo
        })
    },
    //查询所有讲师
    getListTeacher() {
        return request({
            url: `/edu/teacher/findAll`,
            method: 'get',
        })
    },
    //根据课程id查询课程基本信息
    getCourseInfoId(id) {
        return request({
            url: '/edu/course/getCourseInfo/' + id,
            method: 'get'
        })
    },
    //修改课程信息
    updateCourseInfo(courseInfo) {
        return request({
            url: '/edu/course/updateCourseInfo',
            method: 'post',
            data: courseInfo
        })
    },
    //根据课程id查询
    getPublishCourseInfo(id) {
        return request({
            url: '/edu/course/getPublishCourseInfo' + id,
            method: 'get',
        })
    },
    //课程确认信息显示
    getPublihCourseInfo(id) {
        return request({
            url: '/edu/course/getPublishCourseInfo/' + id,
            method: 'get'
        })
    },
    //课程最终发布
    publishCourse(id) {
        return request({
            url: '/edu/course/publishCourse/'+id,
            method: 'post'
          })
    },
    //TODO 课程列表
    //课程最终发布
    getListCourse() {
        return request({
            url: '/edu/course',
            method: 'get'
          })
    },
    removeCourse(id){
        return request({
            url: `/edu/course/${id}`,
            method: 'delete'
          })
    },
    getCourseListPage(current, limit, courseQuery) {
        return request({
            url: `/edu/course/pageCourseCondition/${current}/${limit}`,
            method: 'post',
            //courseQuery条件对象，后端用requestbody获取数据
            //data表示把对象转换成json数据传递到接口里面
            data: courseQuery
        })
    },

}
