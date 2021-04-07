import java.io.Serializable;

public class AuthResponse implements Serializable {
    private String token_type;

    private String access_token;

    private int expires_in;

    private int consented_on;

    private String scope;

    public void setToken_type(String token_type){
        this.token_type = token_type;
    }
    public String getToken_type(){
        return this.token_type;
    }
    public void setAccess_token(String access_token){
        this.access_token = access_token;
    }
    public String getAccess_token(){
        return this.access_token;
    }
    public void setExpires_in(int expires_in){
        this.expires_in = expires_in;
    }
    public int getExpires_in(){
        return this.expires_in;
    }
    public void setConsented_on(int consented_on){
        this.consented_on = consented_on;
    }
    public int getConsented_on(){
        return this.consented_on;
    }
    public void setScope(String scope){
        this.scope = scope;
    }
    public String getScope(){
        return this.scope;
    }
}
