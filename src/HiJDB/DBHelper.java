package HiJDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import HiJDB.Impl.DBHelperImpl;

/**
 * 数据库相关辅助类
 * @author XuminRong
 *
 */
public final class DBHelper {
	/**
	 * 取得ResultSet中的值
	 * @param t
	 * @param set
	 * @param columnIndex
	 * @return
	 * @throws SQLException
	 */
	public static <T> T ReadValue(Class<T> t, ResultSet set, int columnIndex) throws SQLException {
		return DBHelperImpl.ReadValue(t, set, columnIndex);
	}
	

	/**
	 * 取得ResultSet中的值
	 * @param t
	 * @param set
	 * @param field
	 * @return
	 * @throws SQLException
	 */
	public static <T> T ReadValue(Class<T> t, ResultSet set, String field) throws SQLException {
		return DBHelperImpl.ReadValue(t, set, field);
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
		return DBHelperImpl.FillObject(cls, t, set);
	}

	public static <T> T CreateObject(Class<T> cls, ResultSet set) {
		return DBHelperImpl.CreateObject(cls,set);
	}
	
	/**
	 * 取得Set的列表 
	 * @param cls
	 * @param set
	 * @return
	 * @throws SQLException
	 */
	public static <T> List<T> GetResultsList(Class<T> cls, ResultSet set) throws SQLException {
		return DBHelperImpl.GetResultsList(cls,set);
	}

	/**
	 * 创建某行的对象
	 * @param cls
	 * @param set
	 * @param index
	 * @return
	 */
	public static <T> T GetResultData(Class<T> cls, ResultSet set, int index) {
		return DBHelperImpl.GetResultData(cls,set, index);
	}
	
	/**
	 * 取得第一个对象
	 * @param cls
	 * @param set
	 * @return
	 */
	public static <T> T GetFirst(Class<T> cls, ResultSet set) {
		return DBHelperImpl.GetFirst(cls, set);
	}
}
