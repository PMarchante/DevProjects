/*
 * Author: Cody Martz
 * Class: CMSC 495 6381
 * Date: 02/10/2018
 * Description: This class allows the user to request a new access token if the current is expired.
 * Code Sources used:
 * https://chariotsolutions.com/blog/post/sending-mail-via-gmail-javamail/
 * https://javaee.github.io/javamail/OAuth2
 * https://stackoverflow.com/questions/45550385/javamail-gmail-and-oauth2
 */
package encryptionproject;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TokenValidator {
    // Variables For using Oauth2
    private static String TOKEN_URL = "https://www.googleapis.com/oauth2/v4/token";
    
    private String oauthClientId = "191152592510-lar8g0n3lpr2n1d6n901v7nc5kcoh3bd.apps.googleusercontent.com";
    private String oauthSecret = "HjD-xJRGb-xhr226L7NlaiD8";
    private String refreshToken = "1/iTTPuBUD9E7t4wWijJE4yhkL8bJDDQTjmuYZOVVzsPo";
    private static String accessToken = "ya29.GlteBdcPMeG6AKcnUEUBCLo_REz0CTwLy7osrflB6_iGYkZTuAE7Y"
        + "r3FElpG6IXC5QR7d9kVGf-PAnatv9tKovJuuJUJIOXExeY085lWLnH0Z3K93YsOQYz4q8hp";
    private long tokenExpires = 1458168133864L;

    // getters and setters

    public String getAccessToken() {
        return accessToken;
    }

    public static void setAccessToken(String accessToken) {
        accessToken = accessToken;
    }


    public void renewToken(){

        if(System.currentTimeMillis() > tokenExpires) {

            try {
                // Create URL and HTTP method
                String request = "client_id="+ URLEncoder.encode(oauthClientId, "UTF-8")
                        +"&client_secret="+URLEncoder.encode(oauthSecret, "UTF-8")
                        +"&refresh_token="+URLEncoder.encode(refreshToken, "UTF-8")
                        +"&grant_type=refresh_token";
                HttpURLConnection conn = (HttpURLConnection) new URL(TOKEN_URL).openConnection();
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                PrintWriter out = new PrintWriter(conn.getOutputStream());
                out.print(request);
                out.flush();
                out.close();
                // Execute POST to URL
                conn.connect();

                try {
                    ObjectMapper objMap = new ObjectMapper();
                    // Create a JSON object from the returned HTTP data
                    JsonNode json = objMap.readTree(conn.getInputStream());
                    // Extract new token and expiry
                    accessToken = (String) json.get("access_token").asText();
                    tokenExpires = System.currentTimeMillis()+(((Number)json.get("expires_in").asInt()).intValue()*1000);
                } catch (IOException e) {
                    String line;
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                    while((line = in.readLine()) != null) {
                        System.out.println(line);
                    }
                    System.out.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } // End of renewToken If
    } // End of renewToken method
} // End of TokenValidator class
