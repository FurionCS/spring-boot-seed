package com.company.common.response;

import com.company.common.model.StatusCode;
import lombok.Data;

/**
 * @author  Mr.Cheng
 */
@Data
public class ObjectResponse implements Response {
    private static final long serialVersionUID = -7443304902819898146L;
    // api请求类型
    protected String action;
    // 状态码
    protected StatusCode code=StatusCode.Success;
    // 状态信息
    protected String message="操作成功";
    //参数
//    @JsonIgnore
    private Object[] args;
    //结果
    private Object result;

    public ObjectResponse() {
    }

    public ObjectResponse(String action){
       this(action,null);
    }

    public ObjectResponse(String action,Object result){
        this.action=action;
        this.result=result;
    }



    public ObjectResponse(StatusCode code,String message){
        this("",code,message);
    }


    public ObjectResponse(String action, StatusCode code, String message) {
       this(action,code,message,null);
    }

    public ObjectResponse(String action, StatusCode code, String message, Object[] args) {
        this.action = action;
        this.code = code;
        this.message = message;
        this.args = args;
    }
}
