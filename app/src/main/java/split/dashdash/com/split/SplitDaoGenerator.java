package split.dashdash.com.split;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

/**
 * Created by hli on 3/16/15.
 */
public class SplitDaoGenerator {

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(2, "split.dashdash.com.model");
        Entity entity = schema.addEntity("Bill");
        entity.addIdProperty();
        entity.addStringProperty("partyMember");
        entity.addStringProperty("time");
        new DaoGenerator().generateAll(schema, "/Users/hli/Documents/MyApplication/app/src/main/java/");
    }
}
