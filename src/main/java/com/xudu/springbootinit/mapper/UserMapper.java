package com.xudu.springbootinit.mapper;

import com.xudu.springbootinit.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author xudu
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-10-10 22:53:56
* @Entity com.xudu.springbootinit.model.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




