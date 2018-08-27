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
import servlets.SignUpServlet;
import sun.java2d.cmm.Profile;

import java.util.HashMap;
//import servlets.SignInServlet;

public class Main {

    public static HashMap<String, UserProfile> profiles;

    public static void main(String[] args) throws Exception {

        profiles = new HashMap<>();

        AccountService accountService = new AccountService();

        accountService.addNewUser(new UserProfile("admin"));
        accountService.addNewUser(new UserProfile("test"));

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new SignInServlet(accountService)),"/signin"); //???/signup  /api/v1/users
        context.addServlet(new ServletHolder(new SignUpServlet(accountService)),"/signup"); ///signin   /api/v1/session

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("src/public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(8080);
        server.setHandler(handlers);

        server.start();
        java.util.logging.Logger.getGlobal().info("Server started");
        server.join();
    }
}