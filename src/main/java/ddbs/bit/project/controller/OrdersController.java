package ddbs.bit.project.controller;

import ddbs.bit.project.annotation.AdminLoginToken;
import ddbs.bit.project.annotation.UserLoginToken;
import ddbs.bit.project.dao.entity.Orders;
import ddbs.bit.project.service.OrdersService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shardingsphere.core.strategy.keygen.SnowflakeShardingKeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: ddbs
 * @description: RESTful controller of Orders
 * @author: lihuichao
 * @create: 2019-12-16
 **/
public class OrdersController {
    private Logger logger = LogManager.getLogger(GoodsController.class);
    @Autowired
    private OrdersService ordersService;
    @RequestMapping("orders")
    public List<Orders> findGoods() {
        List<Orders> goodsList = ordersService.list();
        if(goodsList == null) {
            logger.warn(String.format("Return empty list"));
        }
        return goodsList;
    }
    @RequestMapping("orders/{id}")
    public Orders findGoodsById(@PathVariable long id) {
        Orders orders = ordersService.getById(id);
        if(orders == null) {
            // 异常处理
            logger.warn(String.format("Cannot find the corresponding orders by orders id %d", id));
        }
        return ordersService.getById(id);
    }
    @UserLoginToken
    @PostMapping(path="orders", consumes = "application/json", produces = "application/json")
    public boolean addGood(@RequestBody Orders order) {
        // 前端提交数据时并不需要提交id
        SnowflakeShardingKeyGenerator snowflakeShardingKeyGenerator = new SnowflakeShardingKeyGenerator();
        order.setId(Long.valueOf(snowflakeShardingKeyGenerator.generateKey().toString()));
        boolean succeed = ordersService.save(order);
        if(!succeed) {
            // 异常处理
            logger.warn(String.format("Save order %s failed", order));
            return false;
        }
        return true;
    }
    @UserLoginToken
    @PutMapping(path="orders/{id}", consumes = "application/json", produces = "application/json")
    public boolean updateOrderById(@RequestBody Orders order, @PathVariable long id) {
        Orders old = ordersService.getById(id);
        boolean succeed = ordersService.updateById(order);
        if(!succeed) {
            logger.warn(String.format("Update order with id %d failed!", id));
            return false;
        }
        return true;
    }
    // 需要登陆的操作
    @AdminLoginToken
    @DeleteMapping(path="orders/{id}")
    public boolean deleteOrderById(@PathVariable long id) {
        boolean succeed = ordersService.removeById(id);
        if(!succeed) {
            logger.warn(String.format("delete order by id %d failed!", id));
            return false;
        }
        return true;
    }
}
