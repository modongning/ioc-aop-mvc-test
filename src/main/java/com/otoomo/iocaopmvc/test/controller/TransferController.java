package com.otoomo.iocaopmvc.test.controller;

import com.otoomo.ioc.annotation.Autowired;
import com.otoomo.iocaopmvc.test.pojo.Result;
import com.otoomo.iocaopmvc.test.service.TransferService;
import com.otoomo.iocaopmvc.test.utils.JsonUtils;
import com.otoomo.web.annotation.Controller;
import com.otoomo.web.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    TransferService transferService;

    @RequestMapping
    public void transfer(HttpServletRequest request, HttpServletResponse response, String fromCardNo, String toCardNo, String money) throws IOException {
        Result result = new Result();

        try {
            transferService.transfer(fromCardNo, toCardNo, Integer.parseInt(money));
            result.setStatus("200");
        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus("201");
            result.setMessage(e.toString());
        }

        // 响应
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print(JsonUtils.object2Json(result));
    }
}
