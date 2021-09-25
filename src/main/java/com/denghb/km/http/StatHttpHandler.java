package com.denghb.km.http;

import com.denghb.km.Km;
import com.denghb.km.stat.StatReqModel;
import com.denghb.km.stat.StatService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 统计服务
 *
 * @author denghb
 * @since 2021/09/10 14:49
 */
public class StatHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        httpExchange.sendResponseHeaders(200, 0);
        httpExchange.getResponseHeaders().add("Content-Type", "application/json;charset=UTF-8");
        Map<String, String> params = getParameters(httpExchange);

        StatReqModel req = new StatReqModel();
        req.setDay(params.get("day"));
        req.setTimeStart(params.get("timeStart"));
        req.setTimeEnd(params.get("timeEnd"));

        String lastTime = params.get("lastTime");
        if (null != lastTime && lastTime.length() > 0) {
            req.setLastTime(Integer.parseInt(lastTime));
        }

        Map<String, Object> res = new HashMap<>();
        res.put(Km.KEYBOARD.name(), StatService.stat(Km.KEYBOARD, req));
        res.put(Km.MOUSE.name(), StatService.stat(Km.MOUSE, req));
        JSONObject json = new JSONObject(res);

        OutputStream os = httpExchange.getResponseBody();
        os.write(String.valueOf(json.toString()).getBytes());
        os.close();
    }

    private Map<String, String> getParameters(HttpExchange httpExchange) {
        String query = httpExchange.getRequestURI().getQuery();
        Map<String, String> result = new HashMap<>();
        if (query == null || query.trim().length() == 0) {
            return result;
        }
        String[] items = query.split("&");
        Arrays.stream(items).forEach(item -> {
            String[] keyAndVal = item.split("=");
            if (keyAndVal.length == 2) {
                try {
                    String key = URLDecoder.decode(keyAndVal[0], "utf8");
                    String val = URLDecoder.decode(keyAndVal[1], "utf8");
                    result.put(key, val);
                } catch (UnsupportedEncodingException e) {
                }
            }
        });
        return result;
    }
}
