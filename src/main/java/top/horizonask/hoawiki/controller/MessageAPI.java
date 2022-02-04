package top.horizonask.hoawiki.controller;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MessageAPI {

    @GetMapping("/message")
    public JSONObject messageApi(){
        JSONObject message = JSONUtil.createObj();
        message.set("message","From spring backend!");
        message.set("resultCode",200);
        return message;
    }
}
