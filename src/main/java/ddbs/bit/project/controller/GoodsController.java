package ddbs.bit.project.controller;

import com.auth0.jwt.JWT;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import ddbs.bit.project.annotation.AdminLoginToken;
import ddbs.bit.project.annotation.UserLoginToken;
import ddbs.bit.project.dao.entity.Admin;
import ddbs.bit.project.dao.entity.Goods;
import ddbs.bit.project.dao.entity.User;
import ddbs.bit.project.service.AdminService;
import ddbs.bit.project.service.GoodsService;
import ddbs.bit.project.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;
import java.sql.Date;

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
    @Autowired

    @RequestMapping("/goods")
    public List<Goods> findGoods() {
        List<Goods> goodsList = goodsService.list();
        if (goodsList == null) {
            logger.warn(String.format("Return empty list"));
        }
        return goodsList;
    }

    @RequestMapping("/goods/{id}")
    public Goods findGoodById(@PathVariable long id) {
        Goods goods = goodsService.getById(id);
        if (goods == null) {
            // 异常处理
            logger.warn(String.format("Cannot find the corresponding goods by goods id %d", id));
        }
        return goodsService.getById(id);
    }

    @UserLoginToken
    @PostMapping(path = "/goods", consumes = "application/json", produces = "application/json")
    public boolean addGood(@RequestHeader("token") String token, @RequestBody Goods good) {
        // 前端提交数据时并不需要提交id
        // 因为已经进行过http请求的截断，因此此时的id是有效的
        long userId = Long.parseLong(JWT.decode(token).getAudience().get(0));
        logger.info(String.format("Data from request body is %s", good));
        SnowflakeShardingKeyGenerator snowflakeShardingKeyGenerator = new SnowflakeShardingKeyGenerator();
        good.setUserId(userId);
        good.setId(Long.valueOf(snowflakeShardingKeyGenerator.generateKey().toString()));
        Date date = new Date(Calendar.getInstance().getTime().getTime());
        good.setUploadTime(date);
        boolean succeed = goodsService.save(good);
        if (!succeed) {
            // 异常处理
            logger.warn(String.format("Save good %s failed", good));
            return false;
        }
        return true;
    }

    @UserLoginToken
    @PutMapping(path = "/goods/{id}", consumes = "application/json", produces = "application/json")
    public boolean updateGoodById(@RequestHeader("token") String token, @RequestBody Goods good, @PathVariable long id) {
        long userId = Long.parseLong(JWT.decode(token).getAudience().get(0));
        // 只有发布商品的用户有权修改相应内容
        Goods old = goodsService.getById(id);
        if (old.getUserId() != userId) {
            logger.warn(String.format("Can't update goods information by other user"));
            // 异常处理
        }
        UpdateWrapper<Goods> updateWrapper = new UpdateWrapper<>();
        boolean succeed = goodsService.update(good, updateWrapper.eq("id", old.getId()));
        if (!succeed) {
            logger.warn(String.format("Update good with id %d failed!", id));
            return false;
        }
        return true;
    }

    // 需要登陆的操作
    @UserLoginToken
    @DeleteMapping(path = "/goods/{id}")
    public boolean deleteGoodById(@RequestHeader("token") String token, @PathVariable long id) {
        long userId = Long.parseLong(JWT.decode(token).getAudience().get(0));
        // 只有发布商品的用户有权修改相应内容
        Goods old = goodsService.getById(id);
        if (old.getUserId() != userId) {
            logger.warn(String.format("Can't delete goods information by other user"));
            // 异常处理
        }
        boolean succeed = goodsService.removeById(id);
        if (!succeed) {
            logger.warn(String.format("delete good by id %d failed!", id));
            return false;
        }
        return true;
    }

    @RequestMapping(path = "/goods/users/{id}")
    public List<Goods> findGoodsByUserId(@PathVariable long id) {
        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
        List<Goods> goodsList = goodsService.list(queryWrapper.eq("user_id", id));
        if (goodsList == null) {
            logger.warn(String.format("Empty goods list for user id %d"), id);
        }
        return goodsList;
    }

    // admin不能新增以及修改商品信息，但能够删除商品
    @AdminLoginToken
    @DeleteMapping("adminDelGoods/{id}")
    public boolean adminDeleteGoodById(@PathVariable long id) {
        return goodsService.removeById(id);
    }
}
