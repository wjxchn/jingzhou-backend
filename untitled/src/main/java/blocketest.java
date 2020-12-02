import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import jdk.internal.jline.internal.TestAccessible;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class blocketest {
    @Test
    public void blockshow() {
        MongoDatabase mongoDatabase = com.buaa.scse.blockchain.blockchainserver.utils.MongoDBUtil.getConnect1();
        MongoCollection<Document> collection = mongoDatabase.getCollection("test");
        long intial = 1L;
        long maxHeight = 1L;
        Bson filter = Filters.eq("Dynamic64Code", intial);
        FindIterable<Document> findIterable = collection.find(filter);
        for (Document document : findIterable) {
            maxHeight = document.getLong("height");
        }

        List<Long> heightlist = new ArrayList();
        Long temp = maxHeight;

        for (int i = 0; i < 5; ++i) {
            heightlist.add(temp);
            temp = temp - 1L;
        }

        List<BlockVisual> results = new ArrayList(5);
        Bson filter1 = Filters.in("height", heightlist);
        FindIterable<Document> findIterable1 = collection.find(filter1);
        for (Document document : findIterable1) {
            if (document.getLong("Dynamic64Code") != 1L) continue;
            ;
            Long Dynamic64Code = document.getLong("Dynamic64Code");
            Long height = document.getLong("height");
            String preSHA256Code = document.getString("preSHA256Code");
            Date time = document.getDate("time");
            String merkleRoot = document.getString("merkleRoot");
            String body = document.getString("body");
            BlockVisual blockVisual = new BlockVisual();
            blockVisual.setDynamic64Code(Dynamic64Code);
            blockVisual.setHeight(height);
            blockVisual.setTime(time);
            blockVisual.setMerkleRoot(merkleRoot);
            blockVisual.setBody(body);
            results.add(blockVisual);
        }
        System.out.println(results);

    }
}
