package servlets;

import accounts.AccountService;
import accounts.UserProfile;
import main.Main;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {

    private static final long serialVersionUID = 4817984263766719476L;

    @SuppressWarnings({"FieldCanBeLocal", "UnusedDeclaration"}) //todo: remove after module 2 home work
    private final AccountService accountService;

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    //  get public user profile
    /*  public void doGet(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        //todo: module 2 home work
    } */

    //sign up @pass@
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {

        //String sessionId = request.getSession().getId();
        //UserProfile profile = accountService.getUserBySessionId(sessionId);

        if(request.getSession(false)==null) {
            UserProfile profile = Main.profiles.get(request.getParameter("login"));

            if (profile != null) {
                if(profile.getPass().equals(request.getParameter("password"))) {
                    request.getSession();
                    response.setContentType("text/html;charset=utf-8");
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().print("Authorized: " + profile.login);
                } else {
                    response.setContentType("text/html;charset=utf-8");
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().print("Password incorrect");
                }
            } else {
                response.setContentType("text/html;charset=utf-8");
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().print("User not found");
            }
        } else {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().print("Session already exist");
        }



        //System.out.println("get " + sessionId);
        //String login = request.getParameter("login");
        //String pass = request.getParameter("password");

        // Проверяем на наличие в мапе профиля, если его нет-не авторизуем

        //change profile
/*   public void doPut(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        //todo: module 2 home work
    }

    //unregister
    public void doDelete(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        //todo: module 2 home work
    }*/
    }
}