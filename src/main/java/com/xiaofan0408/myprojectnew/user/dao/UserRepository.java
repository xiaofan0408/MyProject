package com.xiaofan0408.myprojectnew.user.dao;

import com.xiaofan0408.myprojectnew.user.bean.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xuzefan  2019/9/23 13:55
 */

@Repository
public interface UserRepository extends ReactiveMongoRepository<User,Long>{
}
