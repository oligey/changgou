package valid;

public enum ValidConstant {

    ;
    private String pattern;
    private String msg;

    ValidConstant(String pattern, String msg) {
        this.pattern = pattern;
        this.msg = msg;
    }

    public String getPattern() {
        return pattern;
    }

    public String getMsg() {
        return msg;
    }

    public String getMsg(String pattern) {
        ValidConstant[] values = values();
        for (ValidConstant value : values) {
            if (pattern.equals(value.pattern)){
                return value.msg;
            }
        }
        return null;   //todo  throw new ...
    }
}
