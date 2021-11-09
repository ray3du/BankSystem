package Login;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

import Bank.Dashboard;

public class LoginAPI {
    private String username;
    private String password;
    private JLabel error;
    private Login login;
    private HttpsURLConnection connection;

    public LoginAPI(String username, String password, JLabel error, Login login){
        this.username = username;
        this.password = password;
        this.error = error;
        this.login = login;
        loginAPI();
    }
    private void loginAPI(){
        SwingWorker<String, Void> swingWorker = new SwingWorker<String, Void>(){
            String token;
            @Override
            protected String doInBackground() throws Exception {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("\"email\"", "\"" + username + "\"");
                hashMap.put("\"password\"", "\"" + password + "\"");
                
                try {
                    URL url = new URL("https://cryptotransact.herokuapp.com/v1/login/");
                    connection = (HttpsURLConnection) url.openConnection();
                    connection.setDoOutput(true);
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/json");

                    OutputStream stream = connection.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream, "UTF-8"));
                    String data = JSONConverter(hashMap);
                    writer.write(data);
                    writer.flush();
                    writer.close();
                    stream.close();

                    StringBuilder builder = new StringBuilder();
                   try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String line;
                        while((line = reader.readLine()) != null){
                            builder.append(line);
                        }
                        token = builder.toString();
                   } catch (Exception e) {
                       e.getMessage();
                   } finally {
                       connection.disconnect();
                   }
                } catch (IOException e) {
                    error.setText("Error internet connection not detected");
                    connection.disconnect();
                }
                return token;
            }

            @Override
            protected void done() {
                super.done();
                String response = token;
                if (response.contains("msg")) {
                    String ans = response.substring(8, response.length() - 2);
                    error.setText(ans);
                }else{
                    login.setVisible(false);
                    EventQueue.invokeLater(() -> {
                        new Dashboard().setToken(this.token);
                    });
                }
            }
            
        };
        swingWorker.execute();
    }
    private static String JSONConverter(HashMap<String, String> params){
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry: params.entrySet()){
            if(first){
                first = false;
            }else{
                result.append(",");
            }
            try {
                result.append(entry.getKey());
                result.append(":");
                result.append(entry.getValue());
            } catch (Exception e) {
                e.getMessage();
            }
        }
        result.insert(0, "{");
        result.insert(result.length(), "}");

        return result.toString();
    }
}