package ddbs.bit.project.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ddbs.bit.project.dao.entity.Goods;
import ddbs.bit.project.dao.entity.User;
import ddbs.bit.project.dao.mapper.GoodsMapper;
import ddbs.bit.project.service.GoodsService;
import org.springframework.stereotype.Service;

/**
 * @program: ddbs
 * @description: Implementation of interface GoodsService
 * @author: lihuichao
 * @create: 2019-12-16
 **/
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {
    @Override
    public User getUserByGoodId(long id) {
        return null;
    }
}
