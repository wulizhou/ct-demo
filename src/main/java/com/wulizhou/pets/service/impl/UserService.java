package com.wulizhou.pets.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wulizhou.pets.model.entity.User;
import com.wulizhou.pets.model.entity.UsersPets;
import com.wulizhou.pets.model.mapper.UserMapper;
import com.wulizhou.pets.model.mapper.UsersPetSuppliesMapper;
import com.wulizhou.pets.model.mapper.UsersPetsMapper;
import com.wulizhou.pets.service.facade.Constants;
import com.wulizhou.pets.service.facade.IUserService;
import com.wulizhou.pets.system.common.BaseMapper;
import com.wulizhou.pets.system.common.BaseService;
import com.wulizhou.pets.system.utils.SMSUtil;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService extends BaseService<User> implements IUserService {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UsersPetsMapper usersPetsMapper;

    @Autowired
    private UsersPetSuppliesMapper usersPetSuppliesMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public BaseMapper<User> getMapper() {
        return userMapper;
    }

    @Override
    public String login(String phone, String code) {
        String genCode = (String) redisService.get(Constants.SMS_PREFIX + phone);
        if (genCode.equals(code)) {
            Example example = new Example(User.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("phone",phone);
            List<User> list = userMapper.selectByExample(example);
            if(list != null && list.size() > 0) {
                return getToken(list);
            }else {
                User user = new User();
                user.setCreateTime(new Date());
                user.setUpdateTime(new Date());
                user.setPhone(phone);
                user.setUserName(phone);
                userMapper.insertSelective(user);
                Example example1 = new Example(User.class);
                Example.Criteria criteria1 = example1.createCriteria();
                criteria1.andEqualTo("phone",phone);
                List<User> list2 = userMapper.selectByExample(example1);
                return getToken(list2);
            }
        }
        return null;
    }

    private String getToken(List<User> list) {
        User userFromDB = list.get(0);
        //登录标识符
        String token = DigestUtils.md5Hex(userFromDB.getUserId() + "" + System.currentTimeMillis());
        String key = Constants.REDIS_TOKEN_PREFIX + token;
        //2、将用户信息设置到redis（设置1个月的生存时间）
        try {
            redisService.set(key, MAPPER.writeValueAsString(userFromDB),2592000L);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return token;
    }

    @Override
    public String getVerificationCode(String phone) {
        String verificationCode = SMSUtil.getCode();
        Map<String,Object> data = new HashMap<>();
        data.put("mobile",phone);
        data.put("tpl_id",139779);
        data.put("tpl_value","#code#=" + verificationCode + "&#m#=1");
        data.put("dtype","");
        data.put("key","4c7218d28c0ec13c087db316d6df23eb");
        String queryParam = SMSUtil.urlencode(data);
        ResponseEntity<String> entity = restTemplate.getForEntity(Constants.URL + "?" + queryParam, String.class);//跨服务器访问
        JSONObject jsonObject = JSONObject.fromObject(entity.getBody());
        if (jsonObject.getInt("error_code")== 0) {
            redisService.set(Constants.SMS_PREFIX + phone, verificationCode,60L);//60s 用户应该登录完成
            return verificationCode;
        }
        return null;
    }


    @Override
    public User queryUserByToken(String token) {
        if (StringUtils.isNotBlank(token)) {
            //根据ticket到redis中查询用户信息
            String key = Constants.REDIS_TOKEN_PREFIX + token;
            try {
                //获取用户信息；说明用户当前是活跃状态，需要重新设置redis中ticket对应的用户信息的生存时间
                String userJsonStr = (String) redisService.get(key);
                //设置redis中ticket对应的用户信息的生存时间
                redisService.set(key,userJsonStr, 2592000L);
                return MAPPER.readValue(userJsonStr, User.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public int like(Integer petId, String operation, HttpServletRequest request) {
        int result = 0;
        User user = (User)request.getAttribute("user");
        //点赞
        if (operation.equals(Constants.LIKE)) {
            UsersPets usersPets = new UsersPets();
            usersPets.setUserId(user.getUserId());
            usersPets.setPetId(petId);
            usersPets.setCreateTime(new Date());
            usersPets.setUpdateTime(new Date());
            result = usersPetsMapper.insert(usersPets);
        //取消点赞
        }else if (operation.equals(Constants.UNLIKE)){
            Example example = new Example(UsersPets.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("userId",user.getUserId()).andEqualTo("petId",petId);
            result = usersPetsMapper.deleteByExample(example);
        }
        return result;
    }
}
