package HiJUtil;

import java.lang.reflect.Method;

import HiJUtil.Generic.IEventRet8Param;
import HiJUtil.Generic.IEventRet8Param2;

/**
 * ��������������
 * @author ������
 *
 */
public class HiCBO {	
	
    /**
     * ���ݻص�������
     * @param obj
     * @param type
     * @param handler
     * @return
     */
    public static <T> boolean FillObject(T obj, Class<T> type, IEventRet8Param<String, String> handler)  {
    	return FillObject(obj, type, new IEventRet8Param2<Object, Class<?>, String>(){
    		public Object OnEvent(Class<?> cls, String property) {
    			return handler.OnEvent(property);
    		}
    	});
    }

	
    /**
     * ���ݻص�������
     * @param obj
     * @param type
     * @param handler
     * @return
     */
    public static <T> boolean FillObjectEx(T obj, Class<T> type, IEventRet8Param<Object, String> handler)   {
    	return FillObject(obj, type, new IEventRet8Param2<Object, Class<?>, String>(){
    		public Object OnEvent(Class<?> cls, String property) {
    			return handler.OnEvent(property);
    		}
    	});
    }  
    
    /**
     * �����ṩ����װ�ض���
     * @param obj
     * @param type
     * @param handler
     * @return
     */
    private static <T> boolean FillObject(T obj, Class<T> type, IEventRet8Param2<Object, Class<?>, String> handler)   {
    	if (obj == null || handler == null) {
    		return false;
    	}
    	
    	Method[] methods = type.getMethods();
    	
    	for (int i = 0; i < methods.length; i++) {
    		Method method = methods[i];
    		String name = method.getName();
    		if (!name.startsWith("set")) {
    			continue;
    		}
    		
    		if (method.getParameters().length != 1) {
    			continue;
    		}
    		
    		Class<?> cls = method.getParameterTypes()[0];
    		if (cls == null) {
    			continue;
    		}
    		String property = name.substring(3, name.length());
    		
    		try {     
        		Object value = handler.OnEvent(cls, property);  
    			Object ret =  HiTypeHelper.Cast(cls, value);
				method.invoke(obj, ret); 			
    		} catch(Exception ex) {
    			ex.printStackTrace();
    		}
    	}
        return true;
    }
 }
