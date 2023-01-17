package com.tiktok.controller;

import com.tiktok.ErrorEnum;
import com.tiktok.Result;
import com.tiktok.ServiceException;
import com.tiktok.utils.AgentUtil;
import com.tiktok.utils.RedisUtil;
import com.wf.captcha.SpecCaptcha;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    @Resource
    private RedisUtil redisUtil;

    @GetMapping("send")
    public Result captcha(HttpServletRequest request) {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        String code = specCaptcha.text().toLowerCase();
        String key = getCaptchaKey(request);
        redisUtil.set(key, code, 5);
        return Result.of(specCaptcha.toBase64());
    }

    @GetMapping("verify/{code}")
    public Result verify(HttpServletRequest request, @PathVariable String code) {
        String key = getCaptchaKey(request);
        String captcha = (String) redisUtil.get(key);
        if (captcha == null) {
            throw new ServiceException(ErrorEnum.CAPTCHA_EXPIRED);
        }
        if (!captcha.equals(code.toLowerCase())) {
            return Result.of("验证码错误");
        }
        return Result.of("验证成功");
    }

    private String getCaptchaKey(HttpServletRequest request) {
        String ip = AgentUtil.getIpAddr(request);
        String userAgent = request.getHeader("User-Agent");
        String key = "user-service:captcha:" + DigestUtils.md5DigestAsHex((ip+userAgent).getBytes());
        return key;
    }
}
