package com.komyar.demonstration.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public enum ResultMessage {



    PAGE_WAS_SUCCESSFULLY_UNFOLLOWED(9, "Successfully unfollowed page."),
    PAGE_WAS_SUCCESSFULLY_FOLLOWED(8, "Successfully followed page."),
    POST_WAS_SUCCESSFULLY_UPDATED(7, "Post was successful updated."),
    POST_WAS_SUCCESSFULLY_SAVES(6, "Your post was successful save."),
    PAGE_DELETION_WAS_SUCCESSFUL(5, "Deleting page was successful."),
    PAGE_UPDATE_WAS_SUCCESSFUL(4, "Updating page was successful."),
    PAGE_LOGIN_WAS_SUCCESSFUL(3, "Login was successful."),
    PAGE_REGISTERED_SUCCESSFULLY(2, "Page was successfully registered."),
    SUCCESS_RESULT(1, ""),

    VALIDATION(-1, "Required request body is wrong."),
    ACCESS_DENIED_MESSAGE(-2,"You don't have permission to access this page."),
    TOKEN_CAN_NOT_BE_VERIFIED( -3,"JWT was not verified."),
    PAGE_NOT_FOUND(-4, "Page was not found."),
    METHOD_NOT_ALLOWED(-5, "Method for the http request is wrong."),
    PAGE_IS_BANNED(-6, "User is banned from using this website;"),
    USERNAME_ALREADY_EXISTS(-7, "Username already exists."),
    PHONE_NUMBER_ALREADY_EXISTS(-8, "Phone number already exists."),
    AUTHENTICATION_EXCEPTION(-9, "Username or Password is wrong."),
    PAGE_WAS_NOT_FOUND(-10, "Page with the user name was not found."),
    POST_WAS_NOT_FOUND(-11, "Post was not found."),
    NOT_ALLOWED_TO_UPDATE_POST(-12, "You are not allowed to update this post."),
    EXCEPTION(-100, "");


    private final Integer code;

    private final String message;
}
