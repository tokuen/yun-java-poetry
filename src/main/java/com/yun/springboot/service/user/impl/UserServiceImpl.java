package com.yun.springboot.service.user.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yun.springboot.mapper.user.UserMapper;
import com.yun.springboot.model.entity.user.User;
import com.yun.springboot.service.user.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yun
 * @since 2020-05-30
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
