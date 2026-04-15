package com.itheima.mapper;

import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    /**
     * 分页查询学生数据
     */
    List<Student> page(StudentQueryParam studentQueryParam);

    /**
     * 查询学生总数
     */
    Integer count(StudentQueryParam studentQueryParam);

    /**
     * 新增学生
     */
    @Insert("insert into student(name, no, clazz_id, gender, phone, id_card, is_college, address, degree, graduation_date, violation_count, violation_score, create_time, update_time) values(#{name},#{no},#{clazzId},#{gender},#{phone},#{idCard},#{isCollege},#{address},#{degree},#{graduationDate},#{violationCount},#{violationScore},#{createTime},#{updateTime})")
    void insert(Student student);

    /**
     * 根据ID查询学生数据
     */
    @Select("select id, name, no, clazz_id as clazzId, gender, phone, id_card as idCard, is_college as isCollege, address, degree, graduation_date as graduationDate, violation_count as violationCount, violation_score as violationScore, create_time as createTime, update_time as updateTime from student where id = #{id}")
    Student getById(Integer id);

    /**
     * 更新学生
     */
    @Update("update student set name = #{name}, no = #{no}, clazz_id = #{clazzId}, gender = #{gender}, phone = #{phone}, id_card = #{idCard}, is_college = #{isCollege}, address = #{address}, degree = #{degree}, graduation_date = #{graduationDate}, violation_count = #{violationCount}, violation_score = #{violationScore}, update_time = #{updateTime} where id = #{id}")
    void update(Student student);

    /**
     * 批量删除学生
     */
    void delete(List<Integer> ids);

    /**
     * 查询所有学生
     */
    List<Student> findAll();

    /**
     * 统计学员学历人数
     */
    @MapKey("name")
    List<Map<String, Object>> countStuDegreeData();

    /**
     * 统计学员性别人数
     */
    @MapKey("name")
    List<Map<String, Object>> countStuGenderData();

    /**
     * 统计各班级学员人数
     */
    @MapKey("name")
    List<Map<String, Object>> countStuClazzData();
}