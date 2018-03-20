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
@TableName("u_user_role")
public class UUserRole extends Model<UUserRole> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long uid;
    /**
     * 角色ID
     */
    private Long rid;


    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    @Override
    public String toString() {
        return "UUserRole{" +
        ", uid=" + uid +
        ", rid=" + rid +
        "}";
    }

    @Override
    protected Serializable pkVal() {
        return this.rid;
    }
}
