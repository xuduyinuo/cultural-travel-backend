package com.xudu.culturaltravelbackend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xudu.culturaltravelbackend.model.dto.userdto.*;
import com.xudu.culturaltravelbackend.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xudu.culturaltravelbackend.model.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author xudu
* @description 针对表【user】的数据库操作Service
* @createDate 2024-10-10 22:53:56
*/
public interface UserService extends IService<User> {

    /**
     * 注册用户
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @param checkPassword 确认密码
     * @return 注册成功的用户id
     */
    Long RegisterUser(String userAccount, String userPassword, String checkPassword) throws Exception;

    /**
     * 登录用户
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @return 脱敏后的用户信息
     */
    UserVO LoginUser(String userAccount, String userPassword) throws Exception;

    /**
     * 获取用户分页列表
     *
     * @param searchUserRequest 查询条件
     * @param request           请求
     * @return 用户列表
     */
    Page<UserVO> getUserListByPage(SearchUserRequest searchUserRequest, HttpServletRequest request);

    /**
     * 判断是否为管理员
     * @return 是否为管理员
     */
    Boolean isAdmin();

    /**
     * 判断是否登录
     * @return 是否登录
     */
    Boolean isLogin();

    /**
     * 获取登录用户信息
     * @return 登录用户信息
     */
    UserVO getLoginUser();

    /**
     * 更新用户信息
     * @param updateUserRequest 更新用户信息请求
     * @return 是否更新成功
     */
    Boolean updateUser(UpdateUserRequest updateUserRequest);


    /**
     * 删除用户
     *
     * @param ids 用户id
     * @return 成功删除用户数量
     */
    Integer deleteUser(List<Long> ids);

    /**
     * 封禁用户
     */
    Boolean banUser(Long id);

    /**
     * 添加用户标签
     */
    Boolean addUserTags(AddUserTagsRequest addUserTagsRequest);

    /**
     * 删除用户标签
     */
    Boolean deleteUserTags(DeleteUserTagsRequest deleteUserTagsRequest);

    /**
     * 更新用户标签
     */
    Boolean updateUserTags(UpdateUserTagsRequest updateUserTagsRequest);



}
