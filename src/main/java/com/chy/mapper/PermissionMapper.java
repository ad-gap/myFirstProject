package com.chy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chy.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    @Select("SELECT p.* FROM permissions p " +
            "JOIN role_permissions rp ON p.id = rp.permission_id " +
            "WHERE rp.role_id = #{roleId}")
    List<Permission> findPermissionsByRoleId(Long roleId);

    @Select("SELECT p.* FROM permissions p " +
            "JOIN role_permissions rp ON p.id = rp.permission_id " +
            "JOIN user_roles ur ON rp.role_id = ur.role_id " +
            "WHERE ur.user_id = #{userId}")
    List<Permission> findPermissionsByUserId(Long userId);
}