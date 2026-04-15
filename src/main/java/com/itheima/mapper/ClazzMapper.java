package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ClazzMapper {
    /**
     * 分页查询班级数据
     */
    List<Clazz> page(ClazzQueryParam clazzQueryParam);

    /**
     * 查询班级总数
     */
    Integer count(ClazzQueryParam clazzQueryParam);

    /**
     * 新增班级
     */
    @Insert("insert into clazz(name, room, begin_date, end_date, master_id, subject, create_time, update_time) values(#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    void insert(Clazz clazz);

    /**
     * 根据ID查询班级数据
     */
    @Select("select id, name, room, begin_date as beginDate, end_date as endDate, master_id as masterId, subject, create_time as createTime, update_time as updateTime from clazz where id = #{id}")
    Clazz getById(Integer id);

    /**
     * 更新班级
     */
    @Update("update clazz set name = #{name}, room = #{room}, begin_date = #{beginDate}, end_date = #{endDate}, master_id = #{masterId}, subject = #{subject}, update_time = #{updateTime} where id = #{id}")
    void update(Clazz clazz);

    /**
     * 删除班级
     */
    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);

    /**
     * 查询所有班级数据（用于下拉选择）
     */
    @Select("select id, name from clazz")
    List<Clazz> findAll();
}