package com.xiaofan0408.myprojectnew.user.service.impl;

import com.xiaofan0408.myprojectnew.common.snowflake.SnowflakeFactory;
import com.xiaofan0408.myprojectnew.user.bean.entity.User;
import com.xiaofan0408.myprojectnew.user.dao.UserRepository;
import com.xiaofan0408.myprojectnew.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author xuzefan  2019/9/23 14:01
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public Flux<User> selectAll() {
        return userRepository.findAll();
    }

    @Override
    public Mono<User> insert(User user) {
        user.setId(SnowflakeFactory.getSnowflakeIdWorker().nextId());
        return userRepository.insert(user);
    }

    @Override
    public Mono<User> update(User user) {
        return userRepository.save(user);
    }

    @Override
    public Mono<Boolean> delete(Long id) {
        return userRepository.deleteById(id).thenReturn(true);
    }
}
