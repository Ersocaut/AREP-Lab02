package services.impl;

import model.Message;
import org.bson.Document;
import com.google.gson.JsonObject;
import org.eclipse.jetty.http.HttpStatus;
import services.IntConvService;

public class ConvService implements IntConvService {

    @Override
    public Document toDocument(Message message) {
        Document document = new Document();
        document.put("id", message.getId());
        document.put("date", message.getDate());
        document.put("text", message.getText());
        return document;
    }

    @Override
    public JsonObject getError(String e) {
        JsonObject json = new JsonObject();
        json.addProperty("status_code", HttpStatus.BAD_REQUEST_400);
        json.addProperty("description",e);

        return json;
    }
}
