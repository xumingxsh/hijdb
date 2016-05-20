package HiJDB.Impl;

import java.sql.ResultSet;
import java.sql.SQLException;

final class DBHelper {
	/**
	 * @param t
	 * @param set
	 * @param columnIndex
	 * @return
	 * @throws SQLException
	 */
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
}
