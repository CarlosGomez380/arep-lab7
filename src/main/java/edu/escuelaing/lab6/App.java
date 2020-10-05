package edu.escuelaing.lab6;

import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Route;

import java.math.BigInteger;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class App 
{
    static Map<String, Object> model = new HashMap<>();
    public static void main( String[] args ) {

        model.put("root", getSHA512("root"));
        port(getPort());
        System.out.println(model.get("root"));

        secure("keystore/ecikeystore.p12", "matrixa033", null, null);
        get("/", (req, res) -> logIn(req, res));

        before("/calcular",new Filter() {
            @Override
            public void handle(Request request, Response response) throws NoSuchAlgorithmException {
                String user = request.queryParams("Usuario");
                String password = getSHA512(request.queryParams("Contraseña"));
                System.out.println(user);
                System.out.println(password);
                if (!(model.containsKey(user) && password.equals(model.get(user)))) {
                    halt(401, "Usuario o password incorrecta");
                }
            }
        });

        get("/calcular",(req, res) -> inputDataPage(req, res));
    }

    public static String getSHA512(String input){

        String toReturn = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-512");
            digest.reset();
            digest.update(input.getBytes("utf8"));
            toReturn = String.format("%0128x", new BigInteger(1, digest.digest()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return toReturn;
    }

    private static String logIn(Request req, Response res) {
        String pageContent
                = "<!DOCTYPE html>"
                + "<html>"
                + "<body>"
                + "<h2>Login</h2>"
                + "<form action=\"/calcular\">"
                + "  Usuario:"
                + "  <input type=\"text\" name=\"Usuario\">"
                + "  <br><br>"
                + "  Passwors:<br>"
                + "  <input type=\"text\" name=\"Contraseña\">"
                + "  <input type=\"submit\" value=\"Ingresar\">"
                + "</form>"
                + "</body>"
                + "</html>";
        return pageContent;
    }

    private static String inputDataPage(Request req, Response res) {
        String pageContent
                = "<!DOCTYPE html>"
                + "<html>"
                + "<body>"
                + "<h2>Media y Desviacion estandar</h2>"
                + "<form action=\"/results\">"
                + "  Numero a ingresar:<br>"
                + "  <input type=\"text\" name=\"number\">"
                + "  <br><br>"
                + "  Operacion a ingresar:<br>"
                + "  <input type=\"text\" name=\"operacion\">"
                + "  <input type=\"submit\" value=\"Calcular\">"
                + "</form>"
                + "</body>"
                + "</html>";
        return pageContent;
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5000; //returns default port if heroku-port isn't set (i.e. on localhost)
    }
}
