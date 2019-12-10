package ddbs.bit.project.dao.mapper;

import ddbs.bit.project.dao.entity.Admin;
import org.junit.jupiter.api.Test;
import org.slf4j.ILoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class adminMapperTest {
    @Autowired
    private AdminMapper adminMapper;

    @Test
    public void InsertTest() {
        Admin admin = new Admin();
        admin.setUsername("lihuichao");
        admin.setEmail("lihuichaolihc@gmail.com");
        admin.setPassword("1229547476");
        admin.setId(1);
        System.out.println(admin.getId());
        adminMapper.insert(admin);
    }
}