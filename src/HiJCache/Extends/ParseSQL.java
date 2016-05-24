package HiJCache.Extends;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import HiJUtil.Generic.IEventRet8Param;

/**
 * @author XuminRong
 *
 */
public class ParseSQL {
	public static List<SQLStr> Parase(String sql) {
		List<SQLStr> lst = new ArrayList<SQLStr>();
		if (sql == null) {
			return lst;
		}
		int begin = 0;
		int end = sql.indexOf('@');
		while (end >= 0) {
			String text = sql.substring(begin, end);
			SQLStr param1 = new SQLStr();
			if (text.indexOf("@") >= 0) {
				param1.setParam(true);
			} else {
				param1.setParam(false);
			}
			param1.setText(text);
			lst.add(param1);
			begin = end;
			end = GetParamEnd(sql, end);
			if (end != -1) {
				text = sql.substring(begin, end);
				SQLStr param2 = new SQLStr();
				if (text.indexOf("@") >= 0) {
					param2.setParam(true);
				} else {
					param2.setParam(false);
				}
				param2.setText(text);
				lst.add(param2);
			}  else {
				break;
			}
			
			begin = end;
			end = sql.indexOf('@', begin);
		}
		
		if (begin < sql.length()) {
			String text = sql.substring(begin, sql.length());
			SQLStr param = new SQLStr();
			param.setText(text);
			if (text.indexOf("@") >= 0) {
				param.setParam(true);
			} else {
				param.setParam(false);
			}
			lst.add(param);
		}
		return lst;
	}

	static String[] arr = {" ", "\t", "\r", ",", ")", ">", "<", "!", "'", "-", "+", "/"};			
	private static int GetParamEnd(String sql, int begin) {
		int index = -1;
		for (int i = 0; i < arr.length; i++) {
			int pos = sql.indexOf(arr[i], begin);
			if (index == -1 && pos != -1) {
				index = pos;
				continue;
			}
			if (pos != -1 && pos < index) {
				index = pos;
			}
		}
		
		return index;
	}

	/**
	 * 根据回调函数创建对象
	 * @param lst
	 * @param callback
	 * @return
	 */
	public static String CreateSQL(List<SQLStr> lst, IEventRet8Param<String, String> callback) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < lst.size(); i++) {
			SQLStr info = lst.get(i);
			if (!info.isParam()) {
				sb.append(info.getText());
				continue;
			}
			if (callback == null) {
				return "";
			}
			String ret = callback.OnEvent(info.getText());
			sb.append(ret == null? "": ret);
		}
		return sb.toString();
	}
}
