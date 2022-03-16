package services.impl;

import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import model.Message;
import org.bson.Document;
import services.IntConvService;
import services.IntMessageService;
import java.util.ArrayList;
import java.util.List;

public class MessageService implements IntMessageService {

    private MongoClient mongoClient;
    private final IntConvService service = new ConvService();
    private final String db = "mongodb+srv://root:root@cluster0.faziw.mongodb.net/?retryWrites=true&w=majority";

    public MessageService(){
        ConnectionString connectionString = new ConnectionString(db);
        this.mongoClient = MongoClients.create(connectionString);
    }

    @Override
    public void postMessage(Message message) {
        MongoDatabase mongoDatabase = mongoClient.getDatabase("Cluster0");
        MongoCollection<Document> collection = mongoDatabase.getCollection("messages");
        Document doc = service.toDocument(message);
        collection.insertOne(doc);
    }

    @Override
    public List<String> getAllMessages() {
        List<String> mess = new ArrayList<>();
        MongoDatabase mongoDatabase = mongoClient.getDatabase("Cluster0");
        MongoCollection<Document> collection = mongoDatabase.getCollection("messages");
        FindIterable it = collection.find();
        ArrayList<Document> docList = new ArrayList<>();
        it.into(docList);
        docList.forEach(document -> {
            String m = document.toJson();
            mess.add(m);
        });
        return mess;
    }
}
