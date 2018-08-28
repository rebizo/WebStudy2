package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import main.Main;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {

    private static final long serialVersionUID = 4904134333591464003L;

    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    //sign in
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

/*        String login = request.getParameter("login");
        String pass = request.getParameter("password");*/

        String login = request.getParameter("login");
        String pass = request.getParameter("password");

        if (login.isEmpty() || pass.isEmpty()) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
            //response.getWriter().print("fill fields");
        }

        //UserProfile profile = accountService.getUserByLogin(login);
        String sessionId = request.getSession().getId();
        // Проверяем есть ли такой пользователь
        UserProfile profile = Main.profiles.get(request.getSession().getId());//////////////
        //UserProfile profile = accountService.getUserBySessionId(sessionId);
        System.out.println("check " + request.getSession().getId());////////////////
        if (profile != null/* || profile.getPass().equals(pass)*/) { //
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().print("Already registered");
        }
        // Если такого пользователя нет, добавляем его
        else {
            UserProfile p = new UserProfile(login, pass);
            Main.profiles.put(request.getSession(true).getId(), p);
            System.out.println("put " + request.getSession().getId());
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("Authorized: " + login); // ЗАДАНИЕ profile.login
            //response.getWriter().print("Done");
        }

 /*     accountService.addSession(request.getSession().getId(), profile);
        Gson gson = new Gson();
        String json = gson.toJson(profile);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(json);
        response.setStatus(HttpServletResponse.SC_OK);*/
    } /**/

    //sign out
/*    public void doDelete(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String sessionId = request.getSession().getId();
        UserProfile profile = accountService.getUserBySessionId(sessionId);
        if (profile == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            accountService.deleteSession(sessionId);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Goodbye!");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().print(profile.getPass());
        }
    }*/
}