package HiJDB.Impl;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import HiJUtil.HiCBO;
import HiJUtil.HiTypeHelper;
import HiJUtil.Generic.IEventRet8Param;

/**
 * ���ݿ����������
 * @author XuminRong
 *
 */
public class DBHelperImpl {

	/**
	 * ȡ��ResultSet�е�ֵ
	 * @param t
	 * @param set
	 * @param columnIndex
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public static <T> T ReadValue(Class<T> t, ResultSet set, int columnIndex) throws SQLException {
		if (t == null || set == null) {
			return HiTypeHelper.GetDefault(t);
		}		
		
		if (set.getRow() < 1) {
			return HiTypeHelper.GetDefault(t);
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
		if (t == Boolean.class || t == boolean.class) {
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
	 * ȡ��ResultSet�е�ֵ
	 * @param t
	 * @param set
	 * @param columns
	 * @param field
	 * @return
	 * @throws SQLException
	 */
	public static <T> T ReadValue(Class<T> t, ResultSet set, String field) throws SQLException {
		if (t == null || set == null || field == null) {
			return HiTypeHelper.GetDefault(t);
		}
		int index = set.findColumn(field);
		if (index < 1) {
			return HiTypeHelper.GetDefault(t);
		}
		return ReadValue(t, set, index);
	}
	
	/**
	 * ����ResultSetװ�ض���
	 * @param cls
	 * @param t
	 * @param set
	 * @param columns
	 * @return
	 * @throws SQLException
	 */
	public static <T> boolean FillObject(Class<T> cls, T t, ResultSet set) throws SQLException {
		if (cls == null || t == null || set == null) {
			return false;
		}
		return HiCBO.FillObjectEx(t, cls, new IEventRet8Param<Object, String>(){
			@Override
			public final Object OnEvent(String v) {
				try {
				return ReadValue(Object.class, set,v);
				} catch (Exception ex) {
					ex.printStackTrace();
					return null;
				}
			}			
		});
	}
	
	/**
	 * ������װ�ض�������
	 * @param cls
	 * @param set
	 * @return
	 */
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
	 * ȡ��Set���б�
	 * @param cls
	 * @param set
	 * @return
	 * @throws SQLException
	 */
	public static <T> List<T> GetResultsList(Class<T> cls, ResultSet set) throws SQLException {
		if (cls == null || set == null) {
			return null;
		}		
		
		if (!set.first()) {
			return null;
		}
		
		List<T> list = new ArrayList<T>();
		T t = GetFirst(cls, set);
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
	 * ����ĳ�еĶ���
	 * @param cls
	 * @param set
	 * @param index
	 * @param columns
	 * @return
	 */
	public static <T> T GetResultData(Class<T> cls, ResultSet set, int index) {
		if (set == null || cls  == null) {
			return null;
		}
		
		try
		{
			T t = cls.newInstance();
			if (!set.absolute(index)) {
				return null;
			}
			if (!FillObject(cls, t, set)) {
				return null;
			}
			return t;
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	/**
	 * ȡ�õ�һ������
	 * @param cls
	 * @param set
	 * @return
	 */
	public static <T> T GetFirst(Class<T> cls, ResultSet set) {
		return GetResultData(cls, set, 1);
	}
}
