package com.company.common.response;

import com.company.common.model.StatusCode;
import lombok.Data;

/**
 * @author  Mr.Cheng
 */
@Data
public class ObjectResponse implements Response {
    private static final long serialVersionUID = -7443304902819898146L;
    private static final ObjectResponse RESPONSE=new ObjectResponse();
    // api请求类型
    private String action;
    // 状态码
    private StatusCode code=StatusCode.Success;
    // 状态信息
    private String message="操作成功";
    //参数
//    @JsonIgnore
    private Object[] args;
    //结果
    private Object result;

    private ObjectResponse() {}

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

    public static ObjectResponse getInstance(){
        return RESPONSE;
    }

}
