package club.xiaojiawei.hsscript.status

import club.xiaojiawei.config.log
import club.xiaojiawei.enums.ModeEnum

/**
 * 游戏当前模式（界面）
 * @author 肖嘉威
 * @date 2022/11/25 0:09
 */
object Mode {

    var currMode: ModeEnum? = null
        set(value) {
            field?.modeStrategy?.afterLeave()
            prevMode = field
            field = value
            field?.modeStrategy?.entering()
        }

    var prevMode: ModeEnum? = null

    fun reset() {
        currMode = null
        log.info { "已重置模式状态" }
    }
}