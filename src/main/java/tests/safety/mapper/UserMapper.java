package tests.safety.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tests.safety.entity.User;

import java.util.List;

/**
 * Created by yangzexin on 19/06/2017.
 */
@Repository("userMapper")
public interface UserMapper {
    User selectUserById(@Param("id") String id);
    List<User> selectUserByName(@Param("name") String name);
}
