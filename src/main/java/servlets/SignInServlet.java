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

        UserProfile profile = Main.profiles.get(request.getSession().getId());
        System.out.println("get " + request.getSession().getId());

        //String login = request.getParameter("login");
        String pass = request.getParameter("password");

        // Проверяем на наличие в мапе профиля, если его нет-не авторизуем
        if (profile == null || !profile.getPass().equals(pass)) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().print("Unauthorized");
        }
        // Если профиль есть-выводим "все ок"
        else {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().print("Authorized: " + profile.login);
        }

        //change profile
/*    public void doPut(HttpServletRequest request,
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