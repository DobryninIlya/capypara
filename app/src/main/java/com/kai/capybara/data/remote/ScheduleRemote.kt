package com.kai.capybara.data.remote

class ScheduleRemote(request: Request) : Remote(request) {

    fun get(groupId: Int, uid: String) =
        processResponse(request.getSchedule(groupId, uid))!!.result.schedule


    fun getWeek() =
        processResponse(request.getWeek()).result.week_parity

    fun getGroupId(groupName: Int, uid: String) =
        processResponse(request.getGroupId(groupName, uid)).result.group_id


}
