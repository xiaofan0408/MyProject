package com.xiaofan0408.myprojectnew.user.bean.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author xuzefan  2019/9/23 11:41
 */

@Getter
@Setter
@Document
public class User {

    @Id
    private long id;

    private String userName;

    private String passWord;

    private String email;

    private String userImg;

    private int sumOfIllust;

    private int SumOfFollow;

    private int beConcerned;

    private String remark;
}
