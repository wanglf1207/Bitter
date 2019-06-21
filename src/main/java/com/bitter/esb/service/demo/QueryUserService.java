package com.bitter.esb.service.demo;

import java.util.Map;

import com.bitter.esb.model.demo.UserRequest;
import com.bitter.esb.model.demo.UserResponse;
import com.bitter.esb.service.ESBService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 查询用户余额
 * @author wanglf
 */
@Component("02002000019_11")
public class QueryUserService implements ESBService<UserRequest, UserResponse> {

	private static Logger logger = LoggerFactory.getLogger(QueryUserService.class);


	public UserResponse perform(UserRequest req) {
	    // 这里改怎么调用服务就怎么调用吧

        UserResponse userResponse = new UserResponse();

        userResponse.setAddress("beijing");
        userResponse.setAge(12);
        userResponse.setAmt(1000);

        return userResponse;
	}

}
