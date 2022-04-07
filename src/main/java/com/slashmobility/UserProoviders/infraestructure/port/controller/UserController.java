package com.slashmobility.UserProoviders.infraestructure.port.controller;

import com.slashmobility.UserProoviders.application.command.CmdCreateUser;
import com.slashmobility.UserProoviders.application.command.CmdEmailConfirmation;
import com.slashmobility.UserProoviders.application.command.CmdResetPassword;
import com.slashmobility.UserProoviders.application.command.CmdUpdateUser;
import com.slashmobility.UserProoviders.application.query.QueryUser;
import com.slashmobility.UserProoviders.application.query.QueryUsers;
import com.slashmobility.UserProoviders.domain.model.User;
import com.slashmobility.UserProoviders.domain.valueobject.ResetPassword;
import com.slashmobility.UserProoviders.infraestructure.config.auth.UserContextUtil;
import com.slashmobility.UserProoviders.infraestructure.mapper.UserMapper;
import com.slashmobility.UserProoviders.infraestructure.type.UserResponse;
import com.slashmobility.UserProoviders.infraestructure.type.UserUpdateRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Api(value = "User Service")
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CmdCreateUser cmdCreateUser;

    @Autowired
    private CmdEmailConfirmation cmdEmailConfirmation;

    @Autowired
    private CmdResetPassword cmdResetPassword;

    @Autowired
    private CmdUpdateUser cmdUpdateUser;

    @Autowired
    private QueryUser queryUser;

    @Autowired
    private QueryUsers queryUsers;

    @GetMapping("/{userName}/email/confirmation")
    public String emailConfirmation(@PathVariable("userName") String userName) {
        if (this.cmdEmailConfirmation.handle(userName)) {
            return "Your email has been confirm";
        }

        return "Your email has not been confirm";
    }

    @GetMapping("/{userName}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("userName") String userName) {
        return this.queryUser.handle(userName)
                .map(user -> new ResponseEntity<>(userMapper.toResponse(user), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));

    }

    @GetMapping("/")
    public ResponseEntity<List<UserResponse>> getUsers() {
        return this.queryUsers.handle()
                .map(users -> new ResponseEntity<>(userMapper.toResponses(users), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @PostMapping("/")
    public ResponseEntity createUser(@RequestBody User user) {
        this.cmdCreateUser.handle(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/password/reset")
    public ResponseEntity resetPassword(@RequestBody ResetPassword resetPassword) {
        if (this.cmdResetPassword.handle(UserContextUtil.getUserName(), resetPassword)) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/")
    public ResponseEntity updateUser(@RequestBody UserUpdateRequest userUpdateRequest) {
        User user = this.userMapper.toDomainOfUpdateRequest(userUpdateRequest);
        user.setUserName(UserContextUtil.getUserName());
        return new ResponseEntity<>(this.cmdUpdateUser.handle(user) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);

    }

}
