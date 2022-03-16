package services;

import model.Message;
import org.bson.Document;
import com.google.gson.JsonObject;

public interface IntConvService {

    public Document toDocument (Message message);

    public JsonObject getError(String e);
}
