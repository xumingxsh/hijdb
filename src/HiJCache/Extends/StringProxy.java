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
     * ����XML�ļ����µ�����XML����
     * @param path
     * @throws Exception
     */
    public void LoadXMLsByFolder(String path) throws Exception {
        impl.LoadXMLsByFolder(path);
    }
    
    /**
     * ��û����ֵ
     * 		�ڱ�������,�������쳣,ʹ�ö������,��Ȼ������,�����Լ򻯲���'
     * 		������release��,���к�,�û����������޸�XML�ļ��µ��ļ�
     * @param id
     * @return
     */
    public String GetValue(String id){
        return impl.GetValue(id);
    }
	CacheProxy<String> impl = new CacheProxy<String>();
}
