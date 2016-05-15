package HiJUtil;

import HiJUtil.Generic.IEvent8Param2;

public final class HiLog {
    /// <summary>
    /// »’÷æ√∂æŸ
    /// </summary>
    public enum LogType {
        LT_Info,
        LT_Debug,
        LT_Alarm,
        LT_Error
    }
    
    /**
     * @param logfun
     */
    public static void SetLogFun(IEvent8Param2<String, LogType> logfun)  {
        onlog = logfun;
    }
    public static void Write(String script, LogType type) {
        if (onlog != null) {
            onlog.OnEvent(script, type);
        }
    }
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
