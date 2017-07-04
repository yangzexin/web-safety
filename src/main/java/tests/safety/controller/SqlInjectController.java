package tests.safety.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tests.safety.entity.User;
import tests.safety.mapper.UserMapper;

/**
 * Created by yangzexin on 19/06/2017.
 */
@Controller
@RequestMapping("sqlInject")
public class SqlInjectController {
    private static final Logger logger = LoggerFactory.getLogger(SqlInjectController.class);

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("getUserInfo")
    @ResponseBody
    public Object getUserInfo(String id, String name) {
        Object user = null;
        if (!StringUtils.isEmpty(id)) {
            user = userMapper.selectUserById(id);
        } else if (!StringUtils.isEmpty(name)) {
            user = userMapper.selectUserByName(name);
        }

        return user;
    }
}
