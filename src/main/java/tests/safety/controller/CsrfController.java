package tests.safety.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangzexin on 17/06/2017.
 */
@Controller
public class CsrfController {
    private static final Logger logger = LoggerFactory.getLogger(CsrfController.class);

    @RequestMapping("login")
    @ResponseBody
    public Map login(HttpSession session, String username, String password) {
        session.setAttribute("USERNAME", username);

        Map<String, Object> resultData = new HashMap<>();
        resultData.put("result", "1");

        return resultData;
    }

    @RequestMapping("checkLogin")
    @ResponseBody
    public Map checkLogin(HttpSession session) {
        Object username = session.getAttribute("USERNAME");
        Map<String, Object> resultData = new HashMap<>();
        resultData.put("isLogin", username != null);
        resultData.put("username", username);

        return resultData;
    }

    private boolean isLogin(HttpSession session) {
        Object username = session.getAttribute("USERNAME");
        boolean isLogin = username != null;

        return isLogin;
    }

    @RequestMapping("buy")
    @ResponseBody
    public Map buy(HttpSession session, String productId, String vendorId) {
        Object attr = session.getAttribute("USERNAME");
        if (attr == null) {
            throw new RuntimeException("User did not login");
        }
        String username = attr.toString();
        logger.info("Buy product, user: " + username + ", product: " + productId + ", vendorId: " + vendorId);

        Map<String, Object> resultData = new HashMap<>();
        resultData.put("result", "SUCCESS, buy product: " + productId + ", from vendor: " + vendorId);

        return resultData;
    }
}
