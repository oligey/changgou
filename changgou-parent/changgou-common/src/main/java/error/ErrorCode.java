package error;

import io.swagger.models.auth.In;

public enum ErrorCode {
    GOODS_BRAND_NOT_FOUND_ERROR(404001,"不存在id为:%s的品牌"),
    GOODS_BRAND_NAME_EXIST_ERROR(500001,"已存在品牌名:%s"),
    PAGE_ERROR(500002,"page不可小于0,pageSize不可小于1"),
    ;

    Integer code;
    String msg;

    ErrorCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
