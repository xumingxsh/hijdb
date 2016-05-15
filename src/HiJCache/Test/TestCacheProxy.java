package HiJCache.Test;

import org.junit.Test;

import HiJCache.Extends.SQLInfo;
import HiJCache.Extends.SQLProxy;
import junit.framework.Assert;

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
}
