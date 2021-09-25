package com.denghb.km.http;

import com.denghb.km.utils.LogUtils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;

public class WebHttpHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        LogUtils.log(path);

        if ("/".equals(path)) {
            path = "/index.html";
        }

        String webFile = String.format("webroot%s", path);

        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(webFile);
             OutputStream out = exchange.getResponseBody()) {

            if (null == in) {
                exchange.sendResponseHeaders(404, 0);
                return;
            } else {
                exchange.sendResponseHeaders(200, 0);
            }

            byte[] buffer = new byte[1024];
            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
        }
    }
}
