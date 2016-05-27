package HiJProvider;

import java.util.List;

import HiJUtil.Generic.IEventRet8Param;

public interface IProvider {
	public <T> List<T> ExecuteQuery(Class<T> cls, String id);
	public <T> List<T> ExecuteQuery(Class<T> cls, String id, IEventRet8Param<String, String> evt);
}
