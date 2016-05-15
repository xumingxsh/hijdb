package HiJCache;

import HiJCache.Impl.CachMngImpl;

/**
 * �����ļ���������XML�ļ�����Ϣ
 * Ŀ��:
 * 	1: ���������߼��޹صĶ����Ӵ������Ƴ���XML��,����SQL���(����νԪ���)
 *  2: XML�иĶ�,���������������,������
 *  3: �����û��Լ���չҪ�������Ϣ(ֻҪ����Ҫ��)
 * Ҫ��:
 * 	1: XML�ļ���.XML��Ϊ��׺(��Сд������)
 *  2: ������Ԫ��,
 *  		��һ��Ϊ��Ԫ��,
 *  		�ڶ�����Ҫ�Ƿ�����,�������κ��߼�,��Ԫ����Ϣ������,Ҳ�������������
 *  		����������Ҫ��Ϣ�洢��,Ҫ����������Ԫ�ض���һ������Ϊ"id"��Ԫ��,��ȫ��Ψһ
 * 
 * @author ������
 *
 * @param <T>
 */
public final class CacheProxy<T> {
	
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
    public T  GetValue(String id){
        return impl.GetValue(id);
    }
    
    /**
     * ����XML��������
     * @param parse
     */
    public void setParse(IParse<T> parse){
    	impl.setParse(parse);
    }
    
    CachMngImpl<T> impl = new CachMngImpl<T>();
}