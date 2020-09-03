package com.jd.poporder.slots.rule;


import com.jd.poporder.constants.RuleConstant;

public abstract class AbstractRule implements Rule{
    private String resource;
    private String limitApp;

    public String getResource() {
        return resource;
    }

    public AbstractRule setResource(String resource) {
        this.resource = resource;
        return this;
    }

    public String getLimitApp() {
        return limitApp;
    }

    public AbstractRule setLimitApp(String limitApp) {
        this.limitApp = limitApp;
        return this;
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }

        if (!(o instanceof AbstractRule)){
            return false;
        }

        AbstractRule that = (AbstractRule)o;

        if (resource != null ? !resource.equals(this.resource) : that.resource != null){
            return false;
        }
        if (!limitAppEquals(limitApp, that.limitApp)) {
            return false;
        }
        return true;
    }

    private boolean limitAppEquals(String str1, String str2){
        if ("".equals(str1)){
            return str2.equals(RuleConstant.LIMIT_APP_DEFAULT);
        }else if (str1.equals(RuleConstant.LIMIT_APP_DEFAULT)){
            return "".equals(str2) || str2 == null || str1.equals(str2);
        }
        if (str1 == null) {
            return str2 == null || RuleConstant.LIMIT_APP_DEFAULT.equals(str2);
        }
        return str1.equals(str2);
    }
    @Override
    public int hashCode() {
        int result = resource != null ? resource.hashCode() : 0;
        if (!("".equals(limitApp) || RuleConstant.LIMIT_APP_DEFAULT.equals(limitApp) || limitApp == null)) {
            result = 31 * result + limitApp.hashCode();
        }
        return result;
    }

}
