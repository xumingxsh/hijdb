package HiJUtil;

import HiJUtil.Generic.IEvent8Param2;

/**
 * 日志类
 * @author XuminRong
 *
 */
public final class HiLog {
    /// <summary>
    /// 日志枚举
    /// </summary>
    public enum LogType {
        LT_Info,
        LT_Debug,
        LT_Alarm,
        LT_Error
    }
    
    /**
     * 设置日志的具体实现
     * @param logfun
     */
    public static void SetLogFun(IEvent8Param2<String, LogType> logfun)  {
        onlog = logfun;
    }
    
    /**
     * 写日志
     * @param script
     * @param type
     */
    public static void Write(String script, LogType type) {
        if (onlog != null) {
            onlog.OnEvent(script, type);
        }
    }
    
    /**
     * 
     * @param script
     */
    public static void Write(String script) {
    	Write(script, LogType.LT_Debug);
    }
    public static void Debug(String script) {
        Write(script, LogType.LT_Debug);
    }
    
    public static void Info(String script){
        Write(script, LogType.LT_Info);
    }
    public static void Alarm(String script) {
        Write(script, LogType.LT_Alarm);
    }
    public static void Error(String format, Object...args){
        Write(String.format(format, args), LogType.LT_Error);
    }
    private static IEvent8Param2<String, LogType> onlog = null;
}
