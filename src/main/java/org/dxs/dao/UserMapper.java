package org.dxs.dao;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;
import org.dxs.entity.Permission;
import org.dxs.entity.Role;
import org.dxs.entity.User;

public interface UserMapper {
    @Delete({
        "delete from user",
        "where uid = #{uid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer uid);

    @Insert({
        "insert into user (uid, uname, ",
        "upwd, rid)",
        "values (#{uid,jdbcType=INTEGER}, #{uname,jdbcType=VARCHAR}, ",
        "#{upwd,jdbcType=VARCHAR}, #{rid,jdbcType=INTEGER})"
    })
    int insert(User record);

    @Select({
        "select",
        "uid, uname, upwd, rid",
        "from user",
        "where uid = #{uid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="uid", property="uid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="uname", property="uname", jdbcType=JdbcType.VARCHAR),
        @Result(column="upwd", property="upwd", jdbcType=JdbcType.VARCHAR),
        @Result(column="rid", property="role", javaType=Role.class,one=@One(select="org.dxs.dao.RoleMapper.selectByPrimaryKey"))
    })
    User selectByPrimaryKey(Integer uid);

    @Select({
        "select",
        "uid, uname, upwd, rid",
        "from user"
    })
    @Results({
        @Result(column="uid", property="uid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="uname", property="uname", jdbcType=JdbcType.VARCHAR),
        @Result(column="upwd", property="upwd", jdbcType=JdbcType.VARCHAR),
        @Result(column="rid", property="role", javaType=Role.class,one=@One(select="org.dxs.dao.RoleMapper.selectByPrimaryKey"))
    })
    List<User> selectAll();

    @Update({
        "update user",
        "set uname = #{uname,jdbcType=VARCHAR},",
          "upwd = #{upwd,jdbcType=VARCHAR},",
          "rid = #{rid,jdbcType=INTEGER}",
        "where uid = #{uid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(User record);
    @Select("select * from user where uname=#{uname}")
    @Results({
        @Result(column="uid", property="uid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="uname", property="uname", jdbcType=JdbcType.VARCHAR),
        @Result(column="upwd", property="upwd", jdbcType=JdbcType.VARCHAR),
        @Result(column="rid", property="role", javaType=Role.class,one=@One(select="org.dxs.dao.RoleMapper.selectByPrimaryKey"))
    })
    User selectByName(String uname);
    
    @Select("select p.* from user u inner join role r on r.rid=u.rid inner join permission p on r.rid=p.rid where u.uname=#{uname}")
    @Results({
        @Result(column="uid", property="uid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="uname", property="uname", jdbcType=JdbcType.VARCHAR),
        @Result(column="upwd", property="upwd", jdbcType=JdbcType.VARCHAR),
        @Result(column="rid", property="role", javaType=Role.class,one=@One(select="org.dxs.dao.RoleMapper.selectByPrimaryKey"))
    })
    List<Permission> selectPermission(String uname);
}