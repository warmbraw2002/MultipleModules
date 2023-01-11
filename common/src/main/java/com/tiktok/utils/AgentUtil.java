package com.tiktok.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Cleanup;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class AgentUtil {

    public static String getIpAddr(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
            //LOGGER.error("X-Real-IP:"+ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("http_client_ip");

        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();

        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
//            LOGGER.error("Proxy-Client-IP:"+ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
//            LOGGER.error("WL-Proxy-Client-IP:"+ip);
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
//            LOGGER.error("HTTP_X_FORWARDED_FOR:"+ip);
        }
       // 如果是多级代理，那么取第一个ip为客户ip
        if (ip != null && ip.contains(",")) {
            ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
//            LOGGER.error("ip:"+ip);
        }
        return ip;
    }

    public static String getBrowser(HttpServletRequest request) {
        String agent = request.getHeader("User-Agent");
        if (agent == null || agent.length() == 0) {
            return null;
        }
        if (agent.contains("MSIE")) {
            return "MSIE";
        } else if (agent.contains("Firefox")) {
            return "Firefox";
        } else if (agent.contains("Chrome")) {
            return "Chrome";
        } else if (agent.contains("Safari")) {
            return "Safari";
        } else if (agent.contains("Camino")) {
            return "Camino";
        } else if (agent.contains("Konqueror")) {
            return "Konqueror";
        } else {
            return "UnKnown, More-Info: " + agent;
        }
    }

    public static String getAddress(String ip) {
        OkHttpClient httpClient = new OkHttpClient();
        String url = "http://whois.pconline.com.cn/ipJson.jsp?ip=" + ip +"&json=true";
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = httpClient.newCall(request).execute();
            String result = response.body().string();
            ObjectMapper objectMapper = new ObjectMapper();
            Map resultMap = objectMapper.readValue(result,Map.class);
            return (String) resultMap.get("addr");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
