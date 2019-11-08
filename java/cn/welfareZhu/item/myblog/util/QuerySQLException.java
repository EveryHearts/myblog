package cn.welfareZhu.item.myblog.util;
/**
 * @author 朱剑飞
 * @name 自定义异常类
 * @date 2019-08-27
 * **/
public class QuerySQLException extends Exception {
    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String printMessage(){
        System.out.println(message);
        return message;
    }
}
