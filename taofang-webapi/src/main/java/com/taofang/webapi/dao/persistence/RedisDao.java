package com.taofang.webapi.dao.persistence;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Desc
 * @Author Remilia
 * @Create 2016-04-11
 */
//@Repository
public class RedisDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(RedisDao.class);
//    @Autowired
    private JedisPool jedisPool;

    public String setKeyValue(String key, String value){
        return null;
    }

    public Optional<String> getValueByKey(String key){
        Optional<String> stringOptional = Optional.absent();
        Jedis jedis = jedisPool.getResource();
        try{
            String value = jedis.get(key);
            if(Strings.isNullOrEmpty(value)){
                stringOptional = Optional.of(value);
            }
        }catch(Exception e){
            LOGGER.error("从Redis[" + jedis + "]中获取key[" + key + "]对应的值 ==> error ==> " + e.getMessage(), e);
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }

        return stringOptional;
    }
}
