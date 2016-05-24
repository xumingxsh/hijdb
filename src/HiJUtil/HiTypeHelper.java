package HiJUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 类型住哪换辅助类
 * @author XuminRong
 *
 */
public final class HiTypeHelper {

    /**
     * 字符串转换成日期
     * @param text
     * @return
     */
    public static java.util.Date Convert2Date(String text) {
    	if (text == null || text.trim() == "") {
    		return null; 
    	}
		String format = "yyyy-MM-dd";
		String str = text.trim();
		if (str.contains(":")) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);  
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
    }
    
    /**
     * 字符串转换为日期
     * @param text
     * @return
     */
    public static java.sql.Date Convert2SqlDate(String text) {
		java.util.Date dt = Convert2Date(text);
		return new java.sql.Date(dt.getTime());
    }
    
    
    /**
     * 日期转换为字符串
     * @param date
     * @return
     */
    public static String ToShortString(java.util.Date date) {
    	if (date == null) {
    		return null;
    	}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		return sdf.format(date);
    }
    
    /**
     * 日期转换为字符串
     * @param date
     * @return
     */
    public static String ToLongString(java.util.Date date) {
    	if (date == null) {
    		return null;
    	}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		return sdf.format(date);
    }
    /**
     * 日期转换为字符串
     * @param date
     * @return
     */
    public static String ToShortString(java.sql.Date date) {
    	if (date == null) {
    		return null;
    	}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
		return sdf.format(date);
    }
    
    /**
     * 日期转换为字符串
     * @param date
     * @return
     */
    public static String ToLongString(java.sql.Date date) {
    	if (date == null) {
    		return null;
    	}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		return sdf.format(date);
    }
    
    /**
     * 获取数据类型的默认值
     * @param t
     * @return
     */
    @SuppressWarnings("unchecked")
	public static <T> T GetDefault(Class<T> t) {
		if (t == Object.class) {
			return null;
		}
		if (t == Integer.class) {
			return t.cast(-1);
		}
		if (t == String.class) {
			return t.cast("");
		}
		if (t == boolean.class) {
			Object ret = false;
			return (T)ret;
		}
		if (t == Boolean.class) {
			Object ret = false;
			return (T)ret;
		}
		if (t == BigDecimal.class) {
			return (T)(new BigDecimal(-1));
		}
		Class<?> superClass = t.getSuperclass();
		if (superClass == null) {
			Object val = -1;
			return (T)val; 
		}
		if (t.getSuperclass().equals(Number.class)) {
			Object val = -1;
			return (T)val; 
		}
		return null;
    }
    
    /**
     * 类型转换
     * @param t
     * @param value
     * @return
     */
    @SuppressWarnings("unchecked")
	public static <T> T Cast(Class<T> t, Object value) {
    	if (value == null) {
    		return GetDefault(t);
    	}
		if (t == Object.class) {
			return (T)value;	
		}
		
		try
		{
			return Cast_i(t, value);
		} catch(Exception ex) {
			ex.printStackTrace();
    		return GetDefault(t);
		}
    }
    
    /**
     * 是否数据
     * @param t
     * @return
     */
    public static boolean IsNumer(Class<?> t) {
    	if (t == null) {
    		return false;
    	}
    	if (t == Object.class) {
    		return false;
    	}
    	if (t == void.class) {
    		return false;
    	}
    	Class<?> superClass = t.getSuperclass();
    	if (superClass == null) {
    		return true;
    	}
    	return superClass == Number.class;
    }

	@SuppressWarnings("unchecked")
	private static <T> T Cast_i(Class<T> t, Object value) {
    	Class<?> objClass = value.getClass();
		if (t == String.class) {
			return (T)GetStr(objClass, value); 
		}
		if (t == int.class) {
			if (objClass == int.class) {
				return (T)value;
			} 
			String text = GetStr(objClass, value);
			Object ret = Integer.parseInt(text); 	 
			return (T)ret; 	 
		}
		if (t == Integer.class) {
			if (objClass == Integer.class) {
				return (T)value;
			} 
			if (objClass == int.class) {
				return (T)value;
			} 
			String text = GetStr(objClass, value);
			Object ret = Integer.parseInt(text); 	 
			return (T)ret; 	 
		}
		if (t == short.class) {
			if (objClass == int.class) {
				return (T)value;
			} 
			String text = GetStr(objClass, value);
			Object ret = Short.parseShort(text); 	 
			return (T)ret; 	 
		}
		if (t == boolean.class || t == Boolean.class) {
			if (objClass == int.class) {
				Object ret = (int)value >= 1? true:false;
				return (T)ret;
			} 
			if (objClass == Integer.class) {
				Object ret = (Integer)value >= 1? true:false;
				return (T)ret;
			} 
			String text = GetStr(objClass, value);
			Object ret = Integer.parseInt(text); 	
			ret = (int)ret >= 1? true:false;
			return (T)ret; 
		}
		if (t == long.class) {
			if (objClass == long.class) {
				return (T)value;
			} 
			String text = GetStr(objClass, value);
			Object ret = Long.parseLong(text); 	 
			return (T)ret; 	 
		}
		if (t == byte.class) {
			if (objClass == byte.class) {
				return (T)value;
			} 
			String text = GetStr(objClass, value);
			Object ret = Byte.parseByte(text); 	 
			return (T)ret; 	 
		}
		if (t == double.class) {
			if (objClass == double.class) {
				return (T)value;
			} 
			String text = GetStr(objClass, value);
			Object ret = Double.parseDouble(text); 	 
			return (T)ret; 	  
		}
		if (t == float.class) {
			if (objClass == float.class) {
				return (T)value;
			} 
			String text = GetStr(objClass, value);
			Object ret = Float.parseFloat(text); 	 
			return (T)ret; 	 
		}
		if (t == BigDecimal.class) {
			if (objClass == BigDecimal.class) {
				return (T)value;
			} 
			String text = GetStr(objClass, value);
			return (T)new BigDecimal(text); 	
		}
		
		if (t == java.util.Date.class){
			if (objClass == java.util.Date.class) {
				return (T)value;
			} 
			String text = GetStr(objClass, value);
			return (T)HiTypeHelper.Convert2Date(text); 	
		}
		
		if (t == java.sql.Date.class){
			if (objClass == java.sql.Date.class) {
				return (T)value;
			} 
			String text = GetStr(objClass, value);
			return (T)HiTypeHelper.Convert2SqlDate(text); 	 
		}
		if (t == Time.class) {
			if (objClass == Time.class) {
				return (T)value;
			} 
			String text = GetStr(objClass, value);
			Object ret = HiTypeHelper.Convert2Date(text).getTime(); 	 
			return (T)ret; 	 
		}
		if (t == Timestamp.class) {
			if (objClass == Timestamp.class) {
				return (T)value;
			} 
			String text = GetStr(objClass, value);
			return (T)Timestamp.valueOf(text); 	 
		}
		return (T)value;
	}
    
    private static String GetStr(Class<?> t, Object value) {
    	if (value == null) {
    		return "";
    	}
    	if (t == String.class){	 
			return (String)value; 	 
		}else {	 
			return value.toString();	  
		}
    }
    
    public static <T> String ToString(Class<T> t, T obj) {
    	if (t == null || obj == null) {
    		return "";
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	sb.append("type:");
    	sb.append(t.getName());
    	sb.append("\r\n");
    	if (IsNumer(t)) {
        	sb.append("value:");
        	sb.append(GetStr(t, obj));
        	return sb.toString();
    	}
    	
    	Method[] methods = t.getMethods();
    	
    	for (int i = 0; i < methods.length; i++) {
    		Method method = methods[i];
    		String name = method.getName();
    		if (!name.startsWith("get")) {
    			continue;
    		}
    		
    		if (method.getParameters().length > 0) {
    			continue;
    		}
    		
    		Class<?> etType = method.getReturnType();
    		if (etType == void.class || etType == Void.class) {
    			continue;
    		}
    		Object val;
			try {
				val = method.invoke(obj);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				continue;
			} 

    		String property = name.substring(3, name.length());
        	sb.append(property);
        	sb.append(":");
        	sb.append(GetStr(etType, val));
        	sb.append(",");
    	}
    	
    	return sb.toString();
    }
}
