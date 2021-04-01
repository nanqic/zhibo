package utils;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

public class UserIdUtil {
    @Autowired
    private HttpSession session;
    public int getUid() {
        return Integer.parseInt(session.getAttribute("uid").toString());
    }
}
