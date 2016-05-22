package HiJDB.Impl;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import HiJUtil.HiCBO;
import HiJUtil.Generic.IEventRet8Param;

/**
 * 数据库操作辅助类
 * @author XuminRong
 *
 */
public class DBHelperImpl {

	/**
	 * 取得ResultSet中的值
	 * @param t
	 * @param set
	 * @param columnIndex
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T ReadValue(Class<T> t, ResultSet set, int columnIndex) throws SQLException {
		if (t == null || set == null) {
			return null;
		}
		
		if (t == Object.class) {
			return (T)set.getObject(columnIndex);
		}
		if (t == Integer.class) {
			Object val = set.getInt(columnIndex);
			return (T)val;
		}
		if (t == short.class) {
			Object val = set.getShort(columnIndex);
			return (T)val;
		}
		if (t == Boolean.class) {
			Object val = set.getBoolean(columnIndex);
			return (T)val;
		}
		if (t == long.class) {
			Object val = set.getLong(columnIndex);
			return (T)val;
		}
		if (t == float.class) {
			Object val = set.getFloat(columnIndex);
			return (T)val;
		}
		if (t == double.class) {
			Object val = set.getDouble(columnIndex);
			return (T)val;
		}
		if (t == String.class) {
			Object val = set.getString(columnIndex);
			return (T)val;
		}
		if (t == java.sql.Date.class) {
			Object val = set.getDate(columnIndex);
			return (T)val;
		}
		if (t == java.sql.Time.class) {
			Object val = set.getTime(columnIndex);
			return (T)val;
		}
		if (t == java.sql.Timestamp.class) {
			Object val = set.getTimestamp(columnIndex);
			return (T)val;
		}
		if (t == Byte.class) {
			Object val = set.getByte(columnIndex);
			return (T)val;
		}
		return (T)set.getObject(columnIndex);		
	}
	

	/**
	 * 取得ResultSet中的值
	 * @param t
	 * @param set
	 * @param columns
	 * @param field
	 * @return
	 * @throws SQLException
	 */
	public static <T> T ReadValue(Class<T> t, ResultSet set, Map<String, Integer> columns, String field) throws SQLException {
		if (!columns.containsKey(field)) {
			return null;
		}
		
		int index = columns.get(field);
		return ReadValue(t, set, index);
	}
	
	/**
	 * 取得列名对应的列索引
	 * @param set
	 * @return
	 * @throws SQLException
	 */
	public static Map<String, Integer> GetFieldIndex(ResultSet set) throws SQLException {
		if (set == null) {
			return null;
		}
		ResultSetMetaData meta = set.getMetaData();
		if (meta == null) {
			return null;
		}
		Map<String, Integer> map = new HashMap<String, Integer>();
		int count = meta.getColumnCount();
		for (int i = 0; i < count; i++) {
			map.put(meta.getColumnName(i), i);
		}
		return map;
	}
	
	/**
	 * 根据ResultSet装载对象
	 * @param cls
	 * @param t
	 * @param set
	 * @param columns
	 * @return
	 * @throws SQLException
	 */
	public static <T> boolean FillObject(Class<T> cls, T t, ResultSet set, Map<String, Integer> columns) throws SQLException {
		if (cls == null || t == null || set == null) {
			return false;
		}
		
		if (columns == null) {
			columns = GetFieldIndex(set);
		}
		
		final Map<String, Integer> cols = columns;
		return HiCBO.FillObjectEx(t, cls, new IEventRet8Param<Object, String>(){
			@Override
			public final Object OnEvent(String v) {
				try {
				return ReadValue(Object.class, set, cols, v);
				} catch (Exception ex) {
					ex.printStackTrace();
					return null;
				}
			}			
		});
	}

	/**
	 * 根据ResultSet装载对象
	 * @param cls
	 * @param t
	 * @param set
	 * @return
	 * @throws SQLException
	 */
	public static <T> boolean FillObject(Class<T> cls, T t, ResultSet set) throws SQLException {
		return FillObject(cls, t, set);
	}
	public static <T> T CreateObject(Class<T> cls, ResultSet set) {
		try
		{
			T t = cls.newInstance();
			FillObject(cls, t, set);		
			return t;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 取得Set的列表
	 * @param cls
	 * @param set
	 * @return
	 * @throws SQLException
	 */
	public static <T> List<T> GetResultsList(Class<T> cls, ResultSet set) throws SQLException {
		if (cls == null || set == null) {
			return null;
		}		
		
		Map<String, Integer> columns = GetFieldIndex(set);
		if (columns == null) {
			return null;
		}
		if (!set.first()) {
			return null;
		}
		
		List<T> list = new ArrayList<T>();
		T t = GetFirst(cls, set, columns);
		if (t != null) {
			list.add(t);
		}
		while (set.next()) {
			T it = CreateObject(cls, set);
			if (it != null) {
				list.add(it);
			}
		}
		return list;
	}

	/**
	 * 创建某行的对象
	 * @param cls
	 * @param set
	 * @param index
	 * @return
	 */
	public static <T> T GetResultData(Class<T> cls, ResultSet set, int index) {
		return GetResultData(cls, set, index, null);
	}

	/**
	 * 创建某行的对象
	 * @param cls
	 * @param set
	 * @param index
	 * @param columns
	 * @return
	 */
	public static <T> T GetResultData(Class<T> cls, ResultSet set, int index, Map<String, Integer> columns) {
		if (set == null || cls  == null) {
			return null;
		}
		
		try
		{
			T t = cls.newInstance();
			if (!set.absolute(index)) {
				return null;
			}
			if (!FillObject(cls, t, set, columns)) {
				return null;
			}
			return t;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 取得第一个对象
	 * @param cls
	 * @param set
	 * @return
	 */
	public static <T> T GetFirst(Class<T> cls, ResultSet set) {
		return GetFirst(cls, set, null);
	}
	
	
	/**
	 * 取得第一个对象
	 * @param cls
	 * @param set
	 * @param columns
	 * @return
	 */
	public static <T> T GetFirst(Class<T> cls, ResultSet set, Map<String, Integer> columns) {
		return GetResultData(cls, set, 0, columns);
	}
}
