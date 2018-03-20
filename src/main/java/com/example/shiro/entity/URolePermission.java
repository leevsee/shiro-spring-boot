package com.example.shiro.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Leeves123
 * @since 2018-03-18
 */
@TableName("u_role_permission")
public class URolePermission extends Model<URolePermission> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    private Long rid;
    /**
     * 权限ID
     */
    private Long pid;


    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    @Override
    protected Serializable pkVal() {
        return this.rid;
    }

    @Override
    public String toString() {
        return "URolePermission{" +
        ", rid=" + rid +
        ", pid=" + pid +
        "}";
    }
}
