package HiJDB.Test;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Test;

import HiJDB.DBOperate;
import HiJDB.ICreator;
import HiJDB.Creator.MySQLCreator;
import HiJUtil.Generic.IEventRet;

public class TestDBHelper {
	String url = "jdbc:mysql://localhost:3306/information_schema";
	public TestDBHelper() {
		DBOperate.AddDBCreator(DBOperate.MySQL, new IEventRet<ICreator>(){

			@Override
			public ICreator OnEvent() {
				// TODO Auto-generated method stub
				return new MySQLCreator();
			}
			
		});
	}
	@Test
	public void Test_DBHelper_ReadValue() {
		DBOperate db = new DBOperate(url, "root", "root", DBOperate.MySQL);
        String obj;
		try {
			obj = db.ExecuteScalar(String.class, "select table_name from tables limit 1");
	        Assert.assertEquals(obj, "CHARACTER_SETS");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
