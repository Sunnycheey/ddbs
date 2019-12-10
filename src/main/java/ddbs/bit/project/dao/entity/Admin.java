package ddbs.bit.project.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: ID 自增
@TableName(value="admin")
public class Admin extends User{

    public Admin() {
        super();
    }
    public Admin(long id, String name, String password, String email) {
        super(id, name, password, email);
    }
}
