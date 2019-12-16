package ddbs.bit.project.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// TODO: 全局UUID
// TODO: 增加token

@TableName(value="admin")
public class Admin extends User implements Serializable {
    public Admin() {
        super();
    }
    public Admin(long id, String name, String password, String email) {
        super(id, name, password, email);
    }
}