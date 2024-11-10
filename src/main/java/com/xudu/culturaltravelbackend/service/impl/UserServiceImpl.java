package com.xudu.culturaltravelbackend.service.impl;

import cn.hutool.core.util.ReUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xudu.culturaltravelbackend.common.ErrorCode;
import com.xudu.culturaltravelbackend.exception.ServiceException;
import com.xudu.culturaltravelbackend.model.dto.userdto.*;
import com.xudu.culturaltravelbackend.model.entity.User;
import com.xudu.culturaltravelbackend.model.vo.UserVO;
import com.xudu.culturaltravelbackend.service.UserService;
import com.xudu.culturaltravelbackend.mapper.UserMapper;
import com.xudu.culturaltravelbackend.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

/**
 * @author xudu
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2024-10-10 22:53:56
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private RedisUtil redisUtil;
    @Autowired
    private QiniuUtil qiniuUtil;

    @Override
    public Long RegisterUser(String userAccount, String userPassword, String checkPassword) throws Exception {
        //校验信息
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "账号或密码为空");
        }
        if (userAccount.length() < 4) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "账号过短");
        }
        if (userPassword.length() < 6 || checkPassword.length() < 6) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "密码过短");
        }
        if (!checkPassword.equals(userPassword)) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "两次密码不一致");
        }

        //校验账号是否包含特殊字符
        if (!ReUtil.contains("^[a-zA-Z0-9]{4,16}$", userAccount)) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "账号不符合规范");
        }

        //校验账号是否重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUserAccount, userAccount);
        if (this.count(queryWrapper) > 0) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "账号已存在");
        }

        //加密密码
        String encrypt = AESUtil.encrypt(userPassword);

        //保存用户
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encrypt);
        this.save(user);

        //返回用户id
        return user.getId();

    }

    @Override
    public UserVO LoginUser(String userAccount, String userPassword) throws Exception {

        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "账号或密码为空");
        }
        if (userAccount.length() < 4) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "账号过短");
        }
        if (userPassword.length() < 6) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "密码过短");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getUserAccount, userAccount);
        User user = this.getOne(queryWrapper);
        if (user == null) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "账号不存在");
        }
        if (!AESUtil.decrypt(user.getUserPassword()).equals(userPassword)) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "密码错误");
        }

        /**
         * 后端在响应头中设置Set-Cookie，将Token作为Cookie值发送给客户端。
         * 浏览器会自动将收到的Cookie保存，并在后续请求同一域名下的资源时自动携带该Cookie。
         */
        //生成token
        String token = JWTUtil.sign(user.getUserAccount());
        log.info("token:" + token);
        // 设置Cookie
        // Cookie cookie = new Cookie("Authorization", token);
        // cookie.setHttpOnly(true); // 增加安全性，防止JavaScript访问
        // cookie.setPath("/");
        // cookie.setMaxAge(120); // 有效期7天
        // HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        // response.addCookie(cookie);

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        String userTags = user.getUserTags();
        Gson gson = new Gson();
        List<String> userTagsList = gson.fromJson(userTags, new TypeToken<List<String>>() {
        }.getType());
        userVO.setUserTags(userTagsList);

        //将token返回赋值给userVO
        userVO.setToken(token);

        //将token存入redis, 设置过期时间, key 为token, value 为userVO
        redisUtil.setRedisContent(token, userVO, 7200L);


        //返回登陆用户脱敏后的信息
        return userVO;
    }

    @Override
    public Page<UserVO> getUserListByPage(SearchUserRequest searchUserRequest, HttpServletRequest request) {
        //判断是否为管理员
        //Boolean b = this.isAdmin(request);
        //if (!b) {
        //    throw new ServiceException(ErrorCode.PARAMS_ERROR, "权限不足");
        //}


        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        //条件组合查询
        Long id = searchUserRequest.getId();
        if (id != null && id > 0) {
            queryWrapper.lambda().eq(User::getId, id);
        }
        String userAccount = searchUserRequest.getUserAccount();
        if (StringUtils.isNotBlank(userAccount)) {
            queryWrapper.lambda().like(User::getUserAccount, userAccount);
        }
        String userName = searchUserRequest.getUserName();
        if (StringUtils.isNotBlank(userName)) {
            queryWrapper.lambda().like(User::getUserName, userName);
        }
        String userPhone = searchUserRequest.getUserPhone();
        if (StringUtils.isNotBlank(userPhone)) {
            queryWrapper.lambda().like(User::getUserPhone, userPhone);
        }
        Integer userRole = searchUserRequest.getUserRole();
        if (userRole != null && userRole > 0) {
            queryWrapper.lambda().eq(User::getUserRole, userRole);
        }
        //todo 待添加用户标签查询
        String userTags = searchUserRequest.getUserTags();
        if (StringUtils.isNotBlank(userTags)) {
           queryWrapper.lambda().like(User::getUserTags, userTags);
        }
        int pageNum = searchUserRequest.getPageNum();
        int pageSize = searchUserRequest.getPageSize();

        Page<User> page = new Page<>(pageNum, pageSize);
        Page<User> userPage = this.page(page, queryWrapper);

        Page<UserVO> userVOPage = new Page<>(userPage.getCurrent(), userPage.getSize(), userPage.getTotal());
        ArrayList<UserVO> userVOList = new ArrayList<>();
        Gson gson = new Gson();
        userPage.getRecords().forEach(user -> {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            String jsonUserTags = user.getUserTags();
            List<String> userTagsList = gson.fromJson(jsonUserTags, new TypeToken<List<String>>() {
            }.getType());
            userVO.setUserTags(userTagsList);
            userVOList.add(userVO);
        });
        userVOPage.setRecords(userVOList);

        return userVOPage;


    }



    @Override
    public Boolean isAdmin() {
        HttpServletRequest request = GetRequestUtil.getRequest();

        //获取token
        String token = request.getHeader("Authorization");
        //String token = TokenUtil.getTokenFromCookie(request);


        //判断token是否为空
        if (StringUtils.isBlank(token)) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "未登录");
        }

        //判断redis中是否存在该token
        if (!redisUtil.isRedisExist(token)) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "token过期或token错误");
        }

        //从redis中获取用户信息
        UserVO redisUserInfo = (UserVO) redisUtil.getRedisContent(token);
        return redisUserInfo.getUserRole() == 1;
    }

    @Override
    public Boolean isLogin() {
        HttpServletRequest request = GetRequestUtil.getRequest();
        //请求头中是否有token，token是否在redis中存在
        String token = request.getHeader("Authorization");
        //String token = TokenUtil.getTokenFromCookie(request);
        //token不能为空且在redis中存在
        return StringUtils.isNotBlank(token) && redisUtil.isRedisExist(token);
    }

    @Override
    public UserVO getLoginUser() {
        HttpServletRequest request = GetRequestUtil.getRequest();

        String token = request.getHeader("Authorization");
        //String token = TokenUtil.getTokenFromCookie(request);
        return (UserVO)redisUtil.getRedisContent(token);
    }

    @Override
    public Boolean updateUser(UpdateUserRequest updateUserRequest) {
        Long id = updateUserRequest.getId();
        if (id == null || id <= 0) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getId, id);
        User dbuser = this.getOne(queryWrapper);

        if (dbuser == null){
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "用户不存在");
        }

        User user = new User();

        BeanUtils.copyProperties(updateUserRequest, user);
        if (updateUserRequest.getUserImage()!=null){
            qiniuUtil.deleteFile(user.getUserImage());
            user.setUserImage(qiniuUtil.upload(updateUserRequest.getUserImage()));
        }
        return this.updateById(user);


    }

    @Override
    public Integer deleteUser(List<Long> ids) {
        //校验id是否合法
        if (CollectionUtils.isEmpty(ids)) {
            throw new ServiceException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        ids.forEach(id -> {
            if (id <= 0) {
                throw new ServiceException(ErrorCode.PARAMS_ERROR, "参数错误");
            }

            User dbuser = this.getOne(new QueryWrapper<User>().lambda().eq(User::getId, id));
            if (dbuser == null){
                throw new ServiceException(ErrorCode.PARAMS_ERROR, id + "用户不存在");
            }
        });

        return this.baseMapper.deleteBatchIds(ids);
    }

    @Override
    public Boolean addUserTags(AddUserTagsRequest addUserTagsRequest) {
        return null;
    }

    @Override
    public Boolean deleteUserTags(DeleteUserTagsRequest deleteUserTagsRequest) {
        return null;
    }

    @Override
    public Boolean updateUserTags(UpdateUserTagsRequest updateUserTagsRequest) {
        return null;
    }
}




