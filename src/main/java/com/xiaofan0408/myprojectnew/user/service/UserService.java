package com.xiaofan0408.myprojectnew.user.service;

import com.xiaofan0408.myprojectnew.user.bean.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author xuzefan  2019/9/23 14:01
 */
public interface UserService {

    Flux<User> selectAll();

    Mono<User> insert(User user);

    Mono<User> update(User user);

    Mono<Boolean> delete(Long id);
}
