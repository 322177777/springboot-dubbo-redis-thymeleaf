package com.neusoft.service.impl;

import com.neusoft.mapper.StudentMapper;
import com.neusoft.model.Student;
import com.neusoft.service.StudentService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

@DubboService(interfaceClass = StudentService.class ,version = "1.0" ,timeout = 5000)
public class StudentServiceImpl implements StudentService {
    @Resource
    private StudentMapper studentMapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public int addStudent(Student student) {
        /**
         * 0:  注册失败
         * 1： 注册成功
         * 2 ： 手机号已经存在,注册失败
         */
        System.out.println("手机号:"+student.getPhone());
        int count = studentMapper.selectPhone(student.getPhone());
        if(count > 0){
            return 2;
        }else {
            try {
                int ret = studentMapper.insertStudent(student);
                if(ret > 0){
                    return 1;
                }else {
                    return 0;
                }
            }catch (Exception e){
                e.printStackTrace();
                return 0;
            }
        }
    }

    /**
     * 缓存穿透
     * @param id
     * @return
     */
    @Override
    public Student queryStudent(Integer id) {
        //json反序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(Student.class));
        Student obj = (Student) redisTemplate.opsForValue().get(id.toString());
        if (obj == null){
            //查询mysql数据库
            Student student = studentMapper.selectStudent(id);
            if(student != null) {
                //添加数据到redis(k,v)
                redisTemplate.opsForValue().set(id.toString(), student);
                return student;
            }else {
                //解决缓存穿透问题
                //数据库查不到,在redis添加一个id默认值
                redisTemplate.opsForValue().set(id.toString(), Student.defaultStudent());
                return obj;
            }

        }else{
            return obj;
        }
    }
}
