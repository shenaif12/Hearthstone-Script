package club.xiaojiawei.custom;

import lombok.extern.slf4j.Slf4j;
import java.lang.reflect.Field;
/**
 * 自定义 toString方法：跳过 null、""、0、false的属性的输出
 * @author 肖嘉威 xjw580@qq.com
 * @date 2023/11/9 13:06
 */
@Slf4j
public class CustomToStringGenerator {
    public static String generateToString(Object obj) {
        Class<?> clazz = obj.getClass();
        StringBuilder sb = new StringBuilder(clazz.getSimpleName() + "{");
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                Object value = field.get(obj);
                if (value != null && !isDefaultValue(value)) {
                    sb.append(field.getName()).append("=");
                    sb.append(value).append(", ");
                }
            } catch (IllegalAccessException e) {
                log.error("生成toString失败", e);
            }
        }
        if (sb.charAt(sb.length() - 2) == ',') {
            // Remove the trailing comma and space
            sb.setLength(sb.length() - 2);
        }
        sb.append("}");
        return sb.toString();
    }
    private static boolean isDefaultValue(Object value) {
        if (value instanceof Byte || value instanceof Short || value instanceof Integer || value instanceof Long) {
            return ((Number) value).longValue() == 0;
        } else if (value instanceof Float || value instanceof Double) {
            return ((Number) value).doubleValue() == 0.0;
        } else if (value instanceof Character) {
            return ((Character) value) == '\u0000';
        } else if (value instanceof Boolean) {
            return !((Boolean) value);
        }
        return false;
    }
}
