package com.company.common.request;

import lombok.*;

/**
 * @author MR.CHENG
 */
@Data
public class RestfulRequest implements Request {
    private static final long serialVersionUID = -2363877433041183308L;
    // api请求类型
    protected String action;
    // 令牌
    protected String token;
}
