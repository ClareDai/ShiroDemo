package org.dxs.dao;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;
import org.dxs.entity.Role;

public interface RoleMapper {
    @Delete({
        "delete from role",
        "where rid = #{rid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer rid);

    @Insert({
        "insert into role (rid, rname)",
        "values (#{rid,jdbcType=INTEGER}, #{rname,jdbcType=VARCHAR})"
    })
    int insert(Role record);

    @Select({
        "select",
        "rid, rname",
        "from role",
        "where rid = #{rid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="rid", property="rid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="rname", property="rname", jdbcType=JdbcType.VARCHAR)
    })
    Role selectByPrimaryKey(Integer rid);

    @Select({
        "select",
        "rid, rname",
        "from role"
    })
    @Results({
        @Result(column="rid", property="rid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="rname", property="rname", jdbcType=JdbcType.VARCHAR)
    })
    List<Role> selectAll();

    @Update({
        "update role",
        "set rname = #{rname,jdbcType=VARCHAR}",
        "where rid = #{rid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Role record);
}