package com.hhq.gateway.user.vo;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.List;

@Data
public class Route {
    private String path;
    private String name;
    private String redirect;
    private JSONObject meta;
    private List<Route> children;
}
