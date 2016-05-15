package HiJCache.Extends;

import HiJCache.*;

public final class SQLProxy {
	public SQLProxy(){
		impl.setParse(new SQLPrase());
	}
	
    /**
     * 载入XML文件夹下的所有XML数据
     * @param path
     * @throws Exception
     */
    public void LoadXMLsByFolder(String path) throws Exception {
        impl.LoadXMLsByFolder(path);
    }
    
    /**
     * 获得缓存的值
     * 		在本函数中,隐藏了异常,使用断言替代,虽然不完美,但可以简化操作'
     * 		假设在release下,运行后,用户不会轻易修改XML文件下的文件
     * @param id
     * @return
     */
    public SQLInfo GetValue(String id){
        return impl.GetValue(id);
    }
	CacheProxy<SQLInfo> impl = new CacheProxy<SQLInfo>();
}
