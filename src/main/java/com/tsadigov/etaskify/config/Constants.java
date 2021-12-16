package com.tsadigov.etaskify.config;

public class Constants {

    public static final String MASTER_ASSIGNED_ERROR = "Master already assigned to a business!";
    public static final String BAD_CREDENTIALS = "Bad credentials";
    public static final String TOKEN_CREATED = "Token created";

    public static final String SUCCESS = "Success";
    public static final String CREATED = "Created";
    public static final String UPDATED = "Updated";
    public static final String DELETED = "Deleted";
    public static final int DELETED_CODE = 204;
    public static final int UPDATED_CODE = 203;
    public static final int CREATED_CODE = 201;
    public static final int SUCCESS_CODE = 200;
    public static final int BAD_REQUEST_CODE = 400;
    public static final int UNAUTHORIZED_CODE = 401;
    public static final int FORBIDDEN_CODE = 403;
    public static final int NOT_FOUND_CODE = 404;
    public static final int RELATED_RECORD_EXISTS_CODE = 409;
    public static final int INTERNAL_SERVER_ERROR_CODE = 500;
    public static final int PAGEABLE_DEFAULT_SIZE = 5;
    public static final String PAGEABLE_DEFAULT_FIELD = "id";
    public static final String NOT_FOUND_MESSAGE = "Not found";
    public static final String USER_ADMIN = "Admin";
    public static final String USER_ADMIN_PASSWORD = "P@ssw0rd123!";
    public static final String TOKEN_BEARER = "Bearer ";
    public static final String SECRET_KEY = "secret_key";
    public static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/webjars/**"
    };
    public static final String[] PERMIT_ALL_STRINGS = {
            "/root/login",
            "/api/security/**",
            "/api/login/**",
            "/api/security/refreshToken",
            "/swagger-ui/**"
    };
    public static final String ROLES = "roles";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String ROOT_ACCESS_TOKEN = "root_access_token";
    public static final String REFRESH_TOKEN = "refresh_token";
    public static final String ROOT_REFRESH_TOKEN = "root_refresh_token";
    public static final String REFRESH_TOKEN_IS_MISSING = "Refresh token is missing";
    public static final int REFRESH_TOKEN_DURATION = 30 * 60 * 60 * 1000;
    public static final int ACCESS_TOKEN_DURATION = 10 * 60 * 60 * 1000;
    public static final int ROOT_ACCESS_TOKEN_DURATION = 30 * 60 * 60 * 1000;

    /**
     * Error Messages
     */
    public static final String SHOULD_NOT_BE_LESS_THAN_8_CHARACTER = "Should not be less than 8 character!";
    public static final String INVALID_MOBILE = "Mobile is not valid!";
    public static final String USER_NOT_FOUND = "User does not exist!";
    public static final String ALREADY_EXISTS = "Account already exists!";
    public static final String FILE_DIRECTORY_ERROR_MESSAGE = "Could not create the directory where the uploaded files will be stored!";
    public static final String INVALID_MONTH = "Month is not valid!";
    public static final String RELATED_RECORD_EXISTS_MESSAGE = "Related records found during process!";


    /**
     * Validation patterns
     */
    public static final String MOBILE_PATTERN = "^994(?:50|51|55|70|77|99)[2-9][0-9]{6}";
    public static final String MONTH_PATTERN = "^([1-9]|1[012])$";


}
