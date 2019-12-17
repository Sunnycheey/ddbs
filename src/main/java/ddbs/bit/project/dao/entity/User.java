package ddbs.bit.project.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.apache.ibatis.type.BlobTypeHandler;
import org.apache.ibatis.type.ByteArrayTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.h2.jdbc.JdbcBlob;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@TableName(value = "user")
public class User {
    @TableId(value = "id", type=IdType.INPUT) private long id;
    private String username;
    @TableField(value = "password")
    private String hash;
    @TableField(exist = false)
    private String password;
    private String email;

    public User() {

    }

    public User(long id, String username, String hash, String password, String email) {
        this.id = id;
        this.username = username;
        this.hash = hash;
        this.password = password;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getHash() {
        return hash;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", hash=" + hash + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
