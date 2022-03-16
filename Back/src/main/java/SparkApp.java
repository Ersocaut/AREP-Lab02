import model.Message;
import org.eclipse.jetty.http.HttpStatus;
import org.json.JSONObject;
import services.IntMessageService;
import services.impl.MessageService;

import static spark.Spark.*;

public class SparkApp {

    private static final IntMessageService service = new MessageService();

    public static void main(String[] args) {
        port(getPort());

        after((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Methods", "GET,PUT,POST,DELETE,OPTIONS");
        });

        get("/hello", (req, res) -> "Hello world!");

        post("/mess", (req, res) -> {
            JSONObject jsonpObject = new JSONObject(req.body());
            Message message = new Message(jsonpObject.getString("text"));
            service.postMessage(message);

            res.status(HttpStatus.OK_200);
            jsonpObject.put("status_code", HttpStatus.OK_200);
            jsonpObject.put("description", "Request Successfully");
            return jsonpObject;
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

}
