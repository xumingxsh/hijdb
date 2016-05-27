package HiJCache.Extends;

import HiJCache.*;

public final class SQLProxy {
	
    /**
     * ����XML�ļ����µ�����XML����
     * @param path
     * @throws Exception
     */
    public static void LoadXMLsByFolder(String path) throws Exception {
    	GetImpl().LoadXMLsByFolder(path);
    }
    
    /**
     * ��û����ֵ
     * 		�ڱ�������,�������쳣,ʹ�ö������,��Ȼ������,�����Լ򻯲���'
     * 		������release��,���к�,�û����������޸�XML�ļ��µ��ļ�
     * @param id
     * @return
     */
    public static SQLInfo GetValue(String id){
        return GetImpl().GetValue(id);
    }
    
	static CacheProxy<SQLInfo> impl = null;
	
	private static CacheProxy<SQLInfo> GetImpl() {
		if (impl == null) {
			impl = new CacheProxy<SQLInfo>();
			impl.setParse(new SQLPrase());
		}
		return impl;
	}
}
