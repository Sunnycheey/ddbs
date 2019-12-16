package ddbs.bit.project.controller;

import ddbs.bit.project.annotation.UserLoginToken;
import ddbs.bit.project.dao.entity.Goods;
import ddbs.bit.project.service.GoodsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: ddbs
 * @description: RESTful controller of Goods
 * @author: lihuichao
 * @create: 2019-12-16
 **/
@RestController
public class GoodsController {

    private Logger logger = LogManager.getLogger(GoodsController.class);
    @Autowired
    private GoodsService goodsService;
    @RequestMapping("goods")
    public List<Goods> findGoods() {
        List<Goods> goodsList = goodsService.list();
        if(goodsList == null) {
            logger.warn(String.format("Return empty list"));
        }
        return goodsList;
    }
    @RequestMapping("goods/{id}")
    public Goods findGoodsById(@PathVariable long id) {
        Goods goods = goodsService.getById(id);
        if(goods == null) {
            // 异常处理
            logger.warn(String.format("Cannot find the corresponding goods by goods id %d", id));
        }
        return goodsService.getById(id);
    }
    @UserLoginToken
    @PostMapping(path="goods", consumes = "application/json", produces = "application/json")
    public boolean addGood(@RequestBody Goods good) {
        // 前端提交数据时并不需要提交id
        logger.warn(String.format("Data from request body is %s", good));
        SnowflakeShardingKeyGenerator snowflakeShardingKeyGenerator = new SnowflakeShardingKeyGenerator();
        good.setId(Long.valueOf(snowflakeShardingKeyGenerator.generateKey().toString()));
        boolean succeed = goodsService.save(good);
        if(!succeed) {
            // 异常处理
            logger.warn(String.format("Save good %s failed", good));
            return false;
        }
        return true;
    }
    @UserLoginToken
    @PutMapping(path="goods/{id}", consumes = "application/json", produces = "application/json")
    public boolean updateGoodById(@RequestBody Goods good, @PathVariable long id) {
        Goods old = goodsService.getById(id);
        boolean succeed = goodsService.updateById(good);
        if(!succeed) {
            logger.warn(String.format("Update good with id %d failed!", id));
            return false;
        }
        return true;
    }
    // 需要登陆的操作
    @UserLoginToken
    @DeleteMapping(path="goods/{id}")
    public boolean deleteGoodById(@PathVariable long id) {
        boolean succeed = goodsService.removeById(id);
        if(!succeed) {
            logger.warn(String.format("delete good by id %d failed!", id));
            return false;
        }
        return true;
    }
}
