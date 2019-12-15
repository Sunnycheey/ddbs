package ddbs.bit.project.service.Impl;

import com.baomidou.mybatisplus.extension.service.IService;
import ddbs.bit.project.dao.entity.Admin;
import ddbs.bit.project.dao.mapper.AdminMapper;
import ddbs.bit.project.service.AdminService;
import org.apache.ibatis.annotations.Select;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {

    private static final Logger logger = LogManager.getLogger(AdminServiceImpl.class);

    @Autowired
    private AdminMapper adminMapper;
    @Resource
    private DataSource dataSource;
    @Override
    public List<Admin> getAllAdmin() {
        logger.info(String.format("Get all admin from datasource %s", dataSource));
        return adminMapper.selectList(null);
    }

    @Override
    public int insertAdmin(Admin admin) {
        logger.info(String.format("Insert %s to datasource %s", admin, dataSource));
        return adminMapper.insert(admin);
    }

    @Override
    public Admin getAdminById(long id) {
        logger.info(String.format("Get admin by id %d from datasource %s", id, dataSource));
        return adminMapper.selectById(id);
    }
}
