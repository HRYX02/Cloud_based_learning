package com.sxx.orderservice.controller;


import com.sxx.commonutils.R;
import com.sxx.orderservice.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author SxxStar
 * @since 2023-07-28
 */
@RestController
@RequestMapping("/orderservice/paylog")
public class PayLogController {

    @Autowired
    private PayLogService payLogService;

    /**
     * @description 生成微信支付二维码接口
     * @return 二维码地址以及其他需要的信息
     */
    @GetMapping("createNative/{orderNo}")
    public R createNative(@PathVariable String orderNo) {
        Map map = payLogService.createNatvie(orderNo);
        System.out.println("****返回二维码map集合:"+map);
        return R.ok().data(map);
    }

    /**
     * @description 查询订单支付状态
     * @param orderNo 订单号，根据订单号查询 支付状态
     * @return
     */
    @GetMapping("queryPayStatus/{orderNo}")
    public R queryPayStatus(@PathVariable String orderNo) {
        Map<String,String> map = payLogService.queryPayStatus(orderNo);
        System.out.println("*****查询订单状态map集合:"+map);
        if(map == null) {
            return R.error().message("支付出错了");
        }
        // 如果返回map里面不为空，通过map获取订单状态(支付成功)
        if(map.get("trade_state").equals("SUCCESS")) {
            // 添加记录到支付表，更新订单表订单状态
            payLogService.updateOrdersStatus(map);
            return R.ok().message("支付成功");
        }
        return R.error().code(25000).message("支付中");
    }


}

