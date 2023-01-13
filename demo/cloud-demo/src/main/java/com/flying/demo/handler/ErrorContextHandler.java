package com.flying.demo.handler;

import cn.hutool.core.collection.CollUtil;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author songfeifei
 * @Date 2022/5/19 16:22
 * @Description
 */
public class ErrorContextHandler {

    private static final ThreadLocal<Multimap<String, String>> LOCAL = ThreadLocal.withInitial(ArrayListMultimap::create);

    /**
     * 保存错误
     *
     * @param type    错误类型
     * @param errInfo 错误信息
     */
    public static void putError(String type, String errInfo) {
        LOCAL.get().put(type, errInfo);
    }

    /**
     * 清除当前线程中的数据
     * <p>
     * 【注意】：ThreadLocal使用了WeakReference（弱引用），可能会存在内存泄露问题，
     * 因为 entry对象中只把key(即threadLocal对象)设置成了弱引用，但是value值没有。
     * 所以一般需要在finally代码块中，调用remove方法清理没用的数据。
     */
    public static void clear() {
        LOCAL.remove();
    }

    public static String printMsg() {
        Map<String, Collection<String>> errMap = LOCAL.get().asMap();
        StringBuilder sb = new StringBuilder("系统检测出");
        errMap.forEach((k, v) -> {
            if (CollUtil.isNotEmpty(v)) {
                sb.append(String.format(ErrorEnum.ERROR_MAP.get(k), v));
            }
            sb.append("，请检查");
        });
        return sb.toString();
    }

    @Getter
    public enum ErrorEnum {
        /**
         * 批量导入异常处理
         */
        E_CN_NAME("cnName", "学生%s中文名不能为空"),
        E_GENDER("gender", "学生%s性别不能为空");

        private String type;
        private String format;

        ErrorEnum(String type, String format) {
            this.type = type;
            this.format = format;
        }

        private static final Map<String, String> ERROR_MAP = Arrays.stream(values()).collect(Collectors.toMap(ErrorEnum::getType, ErrorEnum::getFormat));
    }
}
