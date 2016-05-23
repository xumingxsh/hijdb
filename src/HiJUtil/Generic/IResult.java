package HiJUtil.Generic;

/**
 * ���ڴ������෵������
 * @author XuminRong
 *
 * @param <T>
 */
public interface IResult<T> {
	public boolean GetIsSuccess();
	public boolean SetIsSuccess();
	public T Get();
	public void Set(T t);
}
