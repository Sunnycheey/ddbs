package ddbs.bit.project.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ddbs.bit.project.dao.entity.Orders;
import ddbs.bit.project.dao.mapper.OrdersMapper;
import ddbs.bit.project.service.OrdersService;
import org.springframework.stereotype.Service;

/**
 * @program: ddbs
 * @description: Implementation of interface OrdersService
 * @author: lihuichao
 * @create: 2019-12-16
 **/
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {
}
