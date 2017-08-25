package com.company.project.jwt;


import java.util.Date;
import java.util.List;

/**
 * session信息模型
 *
 * @author Mr.Cheng
 */

public class AuthTokenDetails {
    private Long id;// 用户ID
    private String username;// 用户登录名
    private String ip;// 用户IP
    private List<String> roleNames;
    private Date expirationDate;//过期时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public List<String> getRoleNames() {
        return roleNames;
    }

    public void setRoleNames(List<String> roleNames) {
        this.roleNames = roleNames;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
