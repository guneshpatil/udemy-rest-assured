package api.oauth;

import data.Constants;

import java.util.HashMap;
import java.util.Map;

public class OAuthHelper {
    public static Map<String, String> getCodeOAuthParams() {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("scope", "https://www.googleapis.com/auth/userinfo.email");
        queryParams.put("auth_url", "https://accounts.google.com/o/oauth2/v2/auth");
        queryParams.put("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com");
        queryParams.put("response_type", "code");
        queryParams.put("redirect_uri", Constants.URL_RSA_GET_COURSE_PHP);
        return queryParams;
    }

    public static Map<String, String> getTokenOAuthParams(String auth_code) {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("code", auth_code);
        queryParams.put("client_secret", "erZOWM9g3UtwNRj340YYaK_W");
        queryParams.put("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com");
        queryParams.put("grant_type", "authorization_code");
        queryParams.put("redirect_uri", Constants.URL_RSA_GET_COURSE_PHP);
        return queryParams;
    }
}
