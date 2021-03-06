package com.wbg.sums.service;

import com.wbg.sums.entity.MinutesOfTheMeeting;
import com.wbg.sums.entity.Notic;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MinutesOfTheMeetingService {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table minutes_of_the_meeting
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer mId);
    int count();
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table minutes_of_the_meeting
     *
     * @mbg.generated
     */
    int insert(MinutesOfTheMeeting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table minutes_of_the_meeting
     *
     * @mbg.generated
     */
    MinutesOfTheMeeting selectByPrimaryKey(Integer mId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table minutes_of_the_meeting
     *
     * @mbg.generated
     */
    List<MinutesOfTheMeeting> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table minutes_of_the_meeting
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(MinutesOfTheMeeting record);
    //    根据上传时间查询  List<MinutesOfTheMeeting> selectDate(@Param("beforeDate") String beforeDate,@Param("afterDate") String afterDate);
    List<MinutesOfTheMeeting> selects(@Param("mTitle") String mTitle, @Param("dId") int dId, @Param("beforeDate") String beforeDate,@Param("afterDate") String afterDate);

    int counts(@Param("mTitle") String mTitle,@Param("dId") int dId, @Param("beforeDate") String beforeDate,@Param("afterDate") String afterDate);

}
