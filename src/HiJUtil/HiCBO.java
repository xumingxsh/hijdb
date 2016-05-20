package HiJUtil;

import java.lang.reflect.Method;

import HiJUtil.Generic.IEventRet8Param;

/**
 * 根据数据填充对象
 * @author 徐敏荣
 *
 */
public class HiCBO {	
	
    /**
     * 根据回调填充对象
     * @param obj
     * @param type
     * @param handler
     * @return
     */
    public static <T extends Object> boolean FillObject(T obj, Class<T> type, IEventRet8Param<String, String> handler)
    {
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
    		String value = handler.OnEvent(property);
    		
    		try {
    			if (cls == String.class) {
    				method.invoke(obj, value == null ? "": value); 
    				continue;
    			}
    			if (cls == int.class) {
    				method.invoke(obj, value == null ? -1: Integer.parseInt(value));
    				continue;
    			}
    			if (cls == short.class) {
    				method.invoke(obj, value == null ? -1: Short.parseShort(value));
    				continue;
    			}
    			if (cls == long.class) {
    				method.invoke(obj, value == null ? -1: Long.parseLong(value));
    				continue;
    			}
    			if (cls == float.class) {
    				method.invoke(obj, value == null ? -1: Float.parseFloat(value)); 
    				continue;
    			}
    			if (cls == double.class) {
    				method.invoke(obj, value == null ? -1: Double.parseDouble(value)); 
    				continue;
    			}
    			if (cls == boolean.class) {
    				method.invoke(obj, value == null ? false:  Boolean.parseBoolean(value)); 
    				continue;
    			}
    			
    			if (cls == java.util.Date.class){
    				java.util.Date dt = HiTypeHelper.Convert2Date(value);
    				method.invoke(obj, value == null ? null:  dt); 
    				continue;
    			}
    			
    			if (cls == java.sql.Date.class){
    				java.sql.Date dt = HiTypeHelper.Convert2SqlDate(value);
    				method.invoke(obj, value == null ? null:  dt); 
    				continue;
    			}
				method.invoke(obj, value); 			
    		} catch(Exception ex) {
    			ex.toString();
    		}
    	}
        return true;
    }

	
    /**
     * 根据回调填充对象
     * @param obj
     * @param type
     * @param handler
     * @return
     */
    public static <T extends Object> boolean FillObjectEx(T obj, Class<T> type, IEventRet8Param<Object, String> handler)   {
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
    		Object value = handler.OnEvent(property);
    		
    		try {       
    			Object ret =  HiTypeHelper.Cast(cls, value);
				method.invoke(obj, ret); 			
    		} catch(Exception ex) {
    			ex.printStackTrace();
    		}
    	}
        return true;
    }  
 }
