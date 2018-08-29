package main;

import accounts.AccountService;
import accounts.UserProfile;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.SignInServlet;
import servlets.SignOutServlet;
import servlets.SignUpServlet;

import java.util.ArrayList;
import java.util.HashMap;
//import servlets.SignInServlet;

public class Main {

    public static HashMap<String, UserProfile> profiles;
    //public static ArrayList<Object> profiles;

    public static void main(String[] args) throws Exception {

        profiles = new HashMap<>();
        //profiles = new ArrayList<Object>();

        AccountService accountService = new AccountService();

        // Добавляем пользователей (не работает-нужнодобавлять в мапу)
        accountService.addNewUser(new UserProfile("admin"));
        accountService.addNewUser(new UserProfile("test"));

         //profiles.add(accountService);

        //profiles.put(String.valueOf(accountService), new UserProfile("test"));
        //profiles.put(String.valueOf(accountService), new UserProfile("admin"));

        //profiles.put("test", new UserProfile("test"));
        //profiles.put("admin", new UserProfile("admin"));

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SignInServlet(accountService)),"/signin"); //???/signup  /api/v1/users
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)),"/signup"); ///signin   /api/v1/session
        context.addServlet(new ServletHolder(new SignOutServlet(accountService)),"/signout"); ///signin   /api/v1/session

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("src/public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] {resource_handler, context});

        Server server = new Server(8080);
        server.setHandler(handlers);

        server.start();
        java.util.logging.Logger.getGlobal().info("Server started");
        server.join();
    }
}