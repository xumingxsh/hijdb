package HiJCache.Extends;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import HiJCache.CacheProxy;
import HiJCache.IParse;

public class StringProxy {

	public StringProxy(){
		impl.setParse(new IParse<String>(){

			@Override
			public String Parse(Element node) {
				NodeList nodes = node.getChildNodes(); 
				if (nodes == null || nodes.getLength() < 1) {
					return null;
				}
				
				for (int i = 0; i < nodes.getLength(); i++) {
					Node child = nodes.item(i);
					if (child.getNodeName().toLowerCase() == "text") {
						return child.getNodeValue();
					}
			}
			return null;
		} });
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
    public String GetValue(String id){
        return impl.GetValue(id);
    }
	CacheProxy<String> impl = new CacheProxy<String>();
}
