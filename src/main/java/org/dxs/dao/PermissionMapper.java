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

public interface PermissionMapper {
    @Delete({
        "delete from permission",
        "where pid = #{pid,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer pid);

    @Insert({
        "insert into permission (pid, pname, ",
        "rid)",
        "values (#{pid,jdbcType=INTEGER}, #{pname,jdbcType=VARCHAR}, ",
        "#{rid,jdbcType=INTEGER})"
    })
    int insert(Permission record);

    @Select({
        "select",
        "pid, pname, rid",
        "from permission",
        "where pid = #{pid,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="pid", property="pid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="pname", property="pname", jdbcType=JdbcType.VARCHAR),
        @Result(column="rid", property="role", javaType=Role.class,one=@One(select="org.dxs.dao.RoleMapper.selectByPrimaryKey"))
    })
    Permission selectByPrimaryKey(Integer pid);

    @Select({
        "select",
        "pid, pname, rid",
        "from permission"
    })
    @Results({
        @Result(column="pid", property="pid", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="pname", property="pname", jdbcType=JdbcType.VARCHAR),
        @Result(column="rid", property="role", javaType=Role.class,one=@One(select="org.dxs.dao.RoleMapper.selectByPrimaryKey"))
    })
    List<Permission> selectAll();

    @Update({
        "update permission",
        "set pname = #{pname,jdbcType=VARCHAR},",
          "rid = #{rid,jdbcType=INTEGER}",
        "where pid = #{pid,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Permission record);
}