package ddbs.bit.project.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ddbs.bit.project.dao.entity.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper<UserInfo> {
    @Select("select * from usertest")
    List<UserInfo> getUserByName(String username);
    @Insert("insert into usertest values(#{userID}, #{userName}, #{password}, #{email})")
    int insert(UserInfo userInfo);
}
