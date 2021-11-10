package Login;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

import Config.URLS;

public class ForgotPasswordAPI {
    private ForgotPassword forgotPassword;
    private JLabel error;
    private String email;
    private String result;

    public ForgotPasswordAPI(String email, JLabel error, ForgotPassword forgotPassword){
        this.email = email;
        this.error = error;
        this.forgotPassword = forgotPassword;
        startAPI();
    }
    private void startAPI(){
        SwingWorker<Void, String> swingWorker = new SwingWorker<Void, String>(){
            @Override
            protected Void doInBackground() throws Exception {
                HashMap<String, String> hashMap = new HashMap<String, String>();
                hashMap.put("\"email\"", "\"" + email + "\"");
                ForgotPassword.getSendButton().setEnabled(false);
                ForgotPassword.getSendButton().setText("sending ..");
                try {
                    URL url = new URL("http://localhost:3000/v1/forgot/");
                   try {
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setDoOutput(true);
                        connection.setRequestMethod("POST");
                        connection.setRequestProperty("Content-type", "application/json");

                        OutputStream stream = connection.getOutputStream();
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stream, "UTF-8"));
                        String json = JSONConverter(hashMap);
                        writer.write(json);
                        writer.flush();
                        writer.close();
                        stream.close();

                        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        StringBuilder builder = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            builder.append(line);
                        }

                        result = builder.toString();
                   } catch (IOException e) {
                        ForgotPassword.getSendButton().setEnabled(true);
                        ForgotPassword.getSendButton().setText("send");
                        error.setText("Error no internet connection");
                   }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void done() {
                super.done();
                if (result.contains("error")) {
                    String errorMsg = result.substring(10, result.length() - 2);
                    ForgotPassword.getSendButton().setEnabled(true);
                    error.setText(errorMsg);
                    ForgotPassword.getSendButton().setText("send");
                } else {
                    ForgotPassword.getSendButton().setEnabled(true);
                    ForgotPassword.getSendButton().setText("send");
                    forgotPassword.dispose();
                    EventQueue.invokeLater(() -> {
                        new NewPassword(email);
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
