package split.dashdash.com.Util;

import android.content.Context;

import split.dashdash.com.model.BillDao;
import split.dashdash.com.model.DaoMaster;
import split.dashdash.com.model.DaoSession;

public class DatabaseUtil {

    public static BillDao getBillDao(Context context){
        DaoMaster daoMaster = new DaoMaster(new DaoMaster.DevOpenHelper(context, "split-db", null).getWritableDatabase());
        DaoSession session = daoMaster.newSession();
        return session.getBillDao();
    }
}
