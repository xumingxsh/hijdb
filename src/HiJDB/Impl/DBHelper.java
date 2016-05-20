package HiJDB.Impl;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import HiJUtil.HiCBO;
import HiJUtil.Generic.IEventRet8Param;

final class DBHelper {
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
	 * @param cls
	 * @param t
	 * @param set
	 * @param columns
	 * @return
	 * @throws SQLException
	 */
	public static <T extends Object> boolean FillObject(Class<T> cls, T t, ResultSet set, Map<String, Integer> columns) throws SQLException {
		if (cls == null || t == null || set == null) {
			return false;
		}
		
		if (columns == null) {
			columns = GetFieldIndex(set);
		}
		
		return HiCBO.FillObjectEx(t, cls, new IEventRet8Param<Object, String>(){

			@Override
			public Object OnEvent(String v) {
				// TODO Auto-generated method stub
				return null;
			}
			
		});
	}
}
