package com.wulizhou.pets.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wulizhou.pets.model.entity.User;
import com.wulizhou.pets.model.mapper.UserMapper;
import com.wulizhou.pets.service.facade.IUserService;
import com.wulizhou.pets.system.common.BaseMapper;
import com.wulizhou.pets.system.common.BaseService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends BaseService<User> implements IUserService {

    private static final String REDIS_TICKET_PREFIX="PET_TICKET_";

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisService redisService;

    @Override
    public BaseMapper<User> getMapper() {
        return userMapper;
    }

    @Override
    public String login(User user) {

        /**
         * 请求地址：http://v.juhe.cn/sms/send
         请求参数：mobile=13763352822&tpl_id=139779&tpl_value=%23code%23%3D431515&dtype=&key=4c7218d28c0ec13c087db316d6df23eb
         请求方式：GET
         通过HTTPclient去调用生成验证码并返回给客户端
         */
        List<User> list = userMapper.select(user);
        if(list != null && list.size() > 0) {
            User userFromDB = list.get(0);
            //登录标识符
            String ticket = DigestUtils.md5Hex(userFromDB.getId() + "" + System.currentTimeMillis());

            String key = REDIS_TICKET_PREFIX + ticket;
            //2、将用户信息设置到redis（设置1小时的生存时间）
            try {
                redisService.set(key, MAPPER.writeValueAsString(userFromDB),2592000L);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return ticket;
        }
        return null;
    }
}
