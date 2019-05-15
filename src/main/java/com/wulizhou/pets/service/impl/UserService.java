package com.wulizhou.pets.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wulizhou.pets.model.entity.*;
import com.wulizhou.pets.model.mapper.*;
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
import java.util.*;

@Service
public class UserService extends BaseService<User> implements IUserService {
    //TODO 所有的setUserId  都需要重新改 目前是hard code

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private PetsMapper petsMapper;

    @Autowired
    private PetSuppliesMapper petSuppliesMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CollectPetsMapper collectPetsMapper;

    @Autowired
    private LikePetsMapper likePetsMapper;

    @Autowired
    private CollectPetSuppliesMapper collectPetSuppliesMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public BaseMapper<User> getMapper() {
        return userMapper;
    }

    @Override
    public String login(String phone, String code, HttpServletRequest request) {
        String genCode = (String) redisService.get(Constants.SMS_PREFIX + phone);
        if (genCode.equals(code)) {
            Example example = new Example(User.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("phone",phone);
            List<User> list = userMapper.selectByExample(example);
            if(list != null && list.size() > 0) {
                return getToken(list,request);
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
                return getToken(list2, request);
            }
        }
        return null;
    }

    private String getToken(List<User> list, HttpServletRequest request) {
        User userFromDB = list.get(0);
        request.setAttribute("user",userFromDB);
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
    public int like(Integer id, Integer operation, HttpServletRequest request) {
        int result = 0;
//        User user = (User)request.getAttribute("user");
        User user = null;
        System.out.println("从session中获取的用户：" + user);
        //点赞
        if (operation == 1) {
            LikePets likePets = new LikePets();
            likePets.setUserId(1);//TODO
            likePets.setPetId(id);
            likePets.setCreateTime(new Date());
            likePets.setUpdateTime(new Date());
            result = likePetsMapper.insert(likePets);
            //取消点赞
        }else if (operation == 0){
            Example example = new Example(LikePets.class);
            Example.Criteria criteria = example.createCriteria();
            criteria.andEqualTo("userId",1).andEqualTo("petId",id);//TODO
            result = likePetsMapper.deleteByExample(example);
        }
        return result;
    }

    /**
     * 收藏 宠物或者宠物用品
     * 需要联合两个表查询两个表的数据
     * @param id - 是petId或者petSupplyId
     * @param operation l：收藏 ，u：取消收藏
     * @param type 类型：判断是宠物还是用品 pet ,supplies
     * @param request
     * @return
     */
    @Override
    public Integer collect(Integer id, Integer operation, String type, HttpServletRequest request) {
        int result = 0;
//        User user = (User)request.getAttribute("user");
        User user = null;
        System.out.println("从session中获取的用户：" + user);
        //收藏宠物
        if (type.equals(Constants.PET)) {
            //点赞
            if (operation == 1) {
                CollectPets collectPets = new CollectPets();
                collectPets.setUserId(1);//TODO
                collectPets.setPetId(id);
                collectPets.setCreateTime(new Date());
                collectPets.setUpdateTime(new Date());
                result = collectPetsMapper.insert(collectPets);
                //取消点赞
            }else if (operation == 0){
                Example example = new Example(CollectPets.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("userId",1).andEqualTo("petId",id);//TODO
                result = collectPetsMapper.deleteByExample(example);
            }
            //收藏用品
        }else if (type.equals(Constants.SUPPLIES)) {
            //收藏
            if (operation == 1) {
                CollectPetSupplies collectPetSupplies = new CollectPetSupplies();
                collectPetSupplies.setUserId(1);//TODO
                collectPetSupplies.setPetSupplyId(id);
                collectPetSupplies.setCreateTime(new Date());
                collectPetSupplies.setUpdateTime(new Date());
                result = collectPetSuppliesMapper.insert(collectPetSupplies);
            //取消收藏
            }else if (operation == 0){
                Example example = new Example(CollectPetSupplies.class);
                Example.Criteria criteria = example.createCriteria();
                criteria.andEqualTo("userId",1).andEqualTo("petSupplyId",id);//TODO
                result = collectPetSuppliesMapper.deleteByExample(example);
            }
        }
        return result;
    }

    @Override
    public Integer getLikeCount(HttpServletRequest request) {
//        User user = (User)request.getAttribute("user");
//        Example example = new Example(CollectPetSupplies.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("userId",user.getUserId());
        return likePetsMapper.selectCount(new LikePets().setUserId(1));//TODO
    }

    @Override
    public Integer getCollectCount(HttpServletRequest request) {
//        User user = (User)request.getAttribute("user");
//      Example example = new Example(CollectPetSupplies.class);
//      Example.Criteria criteria = example.createCriteria();
//      criteria.andEqualTo("userId",user.getUserId());
        return collectPetSuppliesMapper.selectCount(new CollectPetSupplies().setUserId(1)) +//TODO
                collectPetsMapper.selectCount(new CollectPets().setUserId(1));
    }

    @Override
    public List<Pets> getLike(HttpServletRequest request) {
//        User user = (User)request.getAttribute("user");
        //先查询中间表获取petId
        List<LikePets> collectPets = likePetsMapper.select(new LikePets().setUserId(1));//TODO
        List<Integer> petIds = new ArrayList<>();
        Iterator<LikePets> iterator = collectPets.iterator();
        while (iterator.hasNext()){
            LikePets next = iterator.next();
            petIds.add(next.getPetId());
        }
        //再通过petId去查询对应的实体
        Example example = new Example(LikePets.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("petId",petIds);
        return petsMapper.selectByExample(example);
    }

    @Override
    public List<Object> getCollect(HttpServletRequest request) {
        List<Object> result = new ArrayList<>();
        //User user = (User)request.getAttribute("user");
        //1.先查询宠物的
        List<CollectPets> collectPets = collectPetsMapper.select(new CollectPets().setUserId(1));
        List<Integer> petIds = new ArrayList<>();
        Iterator<CollectPets> iterator = collectPets.iterator();
        while (iterator.hasNext()){
            CollectPets next = iterator.next();
            petIds.add(next.getPetId());
        }
        //再通过petId去查询对应的实体
        Example petExample = new Example(CollectPets.class);
        Example.Criteria petCriteria = petExample.createCriteria();
        petCriteria.andIn("petId",petIds);
        List<Pets> pets = petsMapper.selectByExample(petExample);

        //2.查询用品的
        List<CollectPetSupplies> collectPetSupplies = collectPetSuppliesMapper.select(new CollectPetSupplies().setUserId(1));
        List<Integer> petSupplyIds = new ArrayList<>();
        Iterator<CollectPetSupplies> iterator1 = collectPetSupplies.iterator();
        while (iterator1.hasNext()){
            CollectPetSupplies next = iterator1.next();
            petSupplyIds.add(next.getPetSupplyId());
        }
        //再通过petSupplyId去查询对应的实体
        Example petSuppliesExample = new Example(CollectPetSupplies.class);
        Example.Criteria petSuppliesCriteria = petSuppliesExample.createCriteria();
        petSuppliesCriteria.andIn("petSupplyId",petSupplyIds);
        List<PetSupplies> petSupplies = petSuppliesMapper.selectByExample(petSuppliesExample);
        //3.合并两个
        result.addAll(pets);
        result.addAll(petSupplies);
        return result;
    }

}
