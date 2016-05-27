package HiJCache.Test;

import org.junit.Assert;
import org.junit.Test;

import HiJCache.Extends.SQLInfo;
import HiJCache.Extends.SQLProxy;

@SuppressWarnings("deprecation")
public class TestSQLProxy {
	@SuppressWarnings("deprecation")
	@Test
	public void Test_Normal() {
		boolean ret = Init("xml/sql");
		Assert.assertTrue(ret);
		Assert.assertTrue(SQLProxy.GetValue("DATA.COLLATIONS.GETDATASOURCE") != null);
        SQLInfo info = SQLProxy.GetValue("DATA.INNODBLOCKS.GET8PAGE");
        Assert.assertTrue(info != null);
        Assert.assertTrue(info.getCountSQL() != null);
	}
	
	@Test
    public void LoadXMLs_NoXMLFile()   {
		boolean ret = Init("xml/sqlNoXMLFile");
		Assert.assertTrue(!ret);
    }
	
	@Test
    public void LoadXMLs_NoFolder()   {
		boolean ret = Init("xml/sqlNotExist");
		Assert.assertTrue(!ret);
    }
	
	@Test
    public void LoadXMLs_XMLError()  {
		boolean ret = Init("xml/sqlXmlError");
		Assert.assertTrue(!ret);
    }
	@Test
    public void LoadXMLs_NodeRepeate()  {
		boolean ret = Init("xml/sqlNodeRepeate");
		Assert.assertTrue(!ret);
    }
	
	private String getCurrentPath(){  
		 //当前目录的上级目录路径  
	       String rootPath=getClass().getResource("/").getPath();  
	       int pos = rootPath.lastIndexOf("/", rootPath.length() - 2);
	          
	       return rootPath.substring(0, pos) + "/"; 
	   
	   }
	
	private boolean Init(String folder){

		String path = getCurrentPath();
		try {
			SQLProxy.LoadXMLsByFolder(path + folder);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
