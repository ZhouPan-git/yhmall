package com.yhmall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yhmall.VO.ToEmail;
import com.yhmall.bean.User;
import com.yhmall.dao.UserDao;
import com.yhmall.service.JwtUserDetailsService;
import com.yhmall.service.MailService;
import com.yhmall.util.VerCodeGenerateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Resource
    private MailService mailService;


    @RequestMapping("/sendEmail")
    public AjaxResult sendEmail(ToEmail toEmail, HttpServletRequest request, String username , HttpSession session) {
        if(toEmail == null || toEmail.getTos() == null ) {
            return AjaxResult.fail(-1, "参数错误!");
        }
        toEmail.setSubject("你本次的验证码是");
        // 获取验证码
        String verCode = VerCodeGenerateUtil.generateVerCode();

        String content = "您好:\n"
                + "\n本次请求的邮件验证码为:" + verCode + ",本验证码 5 分钟内效，请及时输入。（请勿泄露此验证码）\n"
                + "\n如非本人操作，请忽略该邮件。\n(这是一封通过自动发送的邮件，请不要直接回复）";
        toEmail.setContent(content);
        session.setAttribute("code",verCode);
        System.out.println(verCode);
        Boolean check = mailService.sendTextMail(toEmail.getTos(), toEmail.getSubject(), toEmail.getContent());
        if(check) {
            return AjaxResult.successed(200, "发送成功");
        } else {
            return AjaxResult.fail(-2, "发送失败");
        }

    }
}
