package entity;

import error.ErrorCode;
import exception.commonException.ValidationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyPage {
    private static Logger logger = LoggerFactory.getLogger(MyPage.class);

    private Integer page;
    private Integer size;

    public MyPage(Integer page, Integer size) {
        this.page = page;
        this.size = size;
        check(page, size);
    }

    public static void check(Integer page, Integer size){
        if (page < 0 || size < 1){
            String msg = String.format(ErrorCode.PAGE_ERROR.getMsg());
            logger.error(msg);
            throw new ValidationException(ErrorCode.PAGE_ERROR.getCode(), msg);
        }
    }


    public Integer getPage() {
        return page;
    }

    public Integer getSize() {
        return size;
    }
}
