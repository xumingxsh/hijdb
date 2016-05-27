package HiJCache.Test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import HiJCache.Extends.ParseSQL;
import HiJCache.Extends.SQLInfo;
import HiJCache.Extends.SQLProxy;
import HiJCache.Extends.SQLStr;
import HiJUtil.Generic.IEventRet8Param;

public class TestCacheProxy {
	@SuppressWarnings("deprecation")
	@Test
	public void Test_IO() {
		String path = System.getProperty("user.dir");
		Assert.assertNotNull(path);
		path += "/xml/sql";
		SQLProxy proxy = new SQLProxy();
		try {
			proxy.LoadXMLsByFolder(path);
			SQLInfo info = proxy.GetValue("DATA.CHARACTERSETS.ADD");
			Assert.assertNotNull(info);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.assertTrue(e.getMessage(), false);
		}
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void Test_Parse_SQL() {
		String sql = "Select @a @b,@c>,@d<,@e!,@f),'@g',@h\r\n,@i-,@j+,@k/, @l";
		List<SQLStr> lst = ParseSQL.Parase(sql);
		/*
		SQLStr param = new SQLStr();
		param.setParam(true);
		param.setText("@a");
		Assert.assertTrue(lst.contains(param));
		param.setText("@b");
		Assert.assertTrue(lst.contains(param));
		param.setText("@c");
		Assert.assertTrue(lst.contains(param));
		param.setText("@d");
		Assert.assertTrue(lst.contains(param));
		param.setText("@e");
		Assert.assertTrue(lst.contains(param));
		param.setText("@f");
		Assert.assertTrue(lst.contains(param));
		param.setText("@g");
		Assert.assertTrue(lst.contains(param));
		param.setText("@h");
		Assert.assertTrue(lst.contains(param));
		param.setText("@i");
		Assert.assertTrue(lst.contains(param));
		param.setText("@j");
		Assert.assertTrue(lst.contains(param));
		param.setText("@k");
		Assert.assertTrue(lst.contains(param));
		param.setText("@l");
		Assert.assertTrue(lst.contains(param));*/
		
		String target = "";
		for (int i = 0; i < lst.size(); i++) {
			target += lst.get(i).getText();
		}
		
		Assert.assertEquals(sql.replace("\r\n", " ").replace("\r", " ").replace("\t", " "), target);
		
		sql = "Select @a @b,@c>,@d<,@e!,@f),'@g',@h\r\n,@i-,@j+,@k/";
		lst = ParseSQL.Parase(sql);
		/*
		param.setText("@a");
		Assert.assertTrue(lst.contains(param));
		param.setText("@b");
		Assert.assertTrue(lst.contains(param));
		param.setText("@c");
		Assert.assertTrue(lst.contains(param));
		param.setText("@d");
		Assert.assertTrue(lst.contains(param));
		param.setText("@e");
		Assert.assertTrue(lst.contains(param));
		param.setText("@f");
		Assert.assertTrue(lst.contains(param));
		param.setText("@g");
		Assert.assertTrue(lst.contains(param));
		param.setText("@h");
		Assert.assertTrue(lst.contains(param));
		param.setText("@i");
		Assert.assertTrue(lst.contains(param));
		param.setText("@j");
		Assert.assertTrue(lst.contains(param));
		param.setText("@k");
		Assert.assertTrue(lst.contains(param));*/
		
		target = "";
		for (int i = 0; i < lst.size(); i++) {
			target += lst.get(i).getText();
		}

		Assert.assertEquals(sql.replace("\r\n", " ").replace("\r", " ").replace("\t", " "), target);
		String sql2 = ParseSQL.CreateSQL(lst, new IEventRet8Param<String, String>(){

			@Override
			public String OnEvent(String v) {
				switch (v) {
				case "a":
				{
					return "a";
				}
				case "b":
				{
					return "b";
				}
				case "c":
				{
					return "c";
				}
				case "d":
				{
					return "d";
				}
				case "e":
				{
					return "e";
				}
				case "f":
				{
					return "f";
				}
				case "g":
				{
					return "g";
				}
				case "h":
				{
					return "h";
				}
				case "i":
				{
					return "i";
				}
				case "j":
				{
					return null;
				}
				case "k":
				{
					return "k";
				}
				default:
				{
					return null;
				}
				}
			}
			
		});
		Assert.assertEquals(sql2, "Select a b,c>,d<,e!,f),'g',h ,i-,+,k/");
	}
	@SuppressWarnings("deprecation")
	@Test
	public void Test_Parse_SQL_2() {
		String sql = "Selecta, b, c, d";
		List<SQLStr> lst = ParseSQL.Parase(sql);
		Assert.assertEquals(lst.size(), 1);
	}
}
