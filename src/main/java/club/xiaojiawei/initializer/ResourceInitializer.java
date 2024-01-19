package club.xiaojiawei.initializer;

import club.xiaojiawei.data.ScriptStaticData;
import club.xiaojiawei.data.SpringData;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Objects;

import static club.xiaojiawei.data.ScriptStaticData.TEMP_DIR;
import static club.xiaojiawei.data.ScriptStaticData.TEMP_PATH;

/**
 * 删除下载的新版本等临时文件
 * @author 肖嘉威
 * @date 2023/9/17 1:59
 */
@Component
@Slf4j
public class ResourceInitializer extends AbstractInitializer{

    @Resource
    private SpringData springData;

    @Override
    protected void exec() {
        if (new File(springData.getResourcePath() + ScriptStaticData.MAIN_ICO_NAME).exists()){
            return;
        }
        File resourceDir = new File(springData.getResourcePath());
        if (!resourceDir.exists()){
            if (!resourceDir.mkdirs()){
                log.warn("资源文件夹创建失败");
            }
        }
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(Objects.requireNonNull(ResourceInitializer.class.getResourceAsStream("/fxml/img/" + ScriptStaticData.MAIN_ICO_NAME)));
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(resourceDir.getAbsolutePath() + File.separator + ScriptStaticData.MAIN_ICO_NAME))
        ){
            byte[] bytes = new byte[1024];
            int size;
            while ((size = bufferedInputStream.read(bytes)) > 0){
                bufferedOutputStream.write(bytes, 0, size);
            }
            log.info("资源文件复制成功");
        } catch (IOException e) {
            log.error("资源文件复制异常", e);
        }
    }

}