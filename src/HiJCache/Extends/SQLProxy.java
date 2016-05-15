package HiJCache.Extends;

import HiJCache.*;

public final class SQLProxy {
	public SQLProxy(){
		impl.setParse(new SQLPrase());
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
    public SQLInfo GetValue(String id){
        return impl.GetValue(id);
    }
	CacheProxy<SQLInfo> impl = new CacheProxy<SQLInfo>();
}
