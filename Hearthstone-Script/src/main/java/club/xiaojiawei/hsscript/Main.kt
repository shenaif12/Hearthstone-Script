package club.xiaojiawei.hsscript

import ch.qos.logback.classic.LoggerContext
import ch.qos.logback.classic.joran.JoranConfigurator
import club.xiaojiawei.hsscript.bean.DBCard
import club.xiaojiawei.hsscript.utils.DBUtil
import javafx.application.Application
import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.datasource.DriverManagerDataSource
import java.io.File
import java.nio.file.Path
import java.sql.ResultSet
import kotlin.io.path.Path

/**
 * @author 肖嘉威
 * @date 2024/10/14 17:42
 */
var PROGRAM_ARGS: List<String> = emptyList()

private fun setLogPath() {
    try {
        val logbackConfigFile = File("logback.xml")
        if (logbackConfigFile.exists()) {
            val context = LoggerFactory.getILoggerFactory() as LoggerContext
            val configurator = JoranConfigurator()
            configurator.context = context
            context.reset()
            configurator.doConfigure(logbackConfigFile)
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun main(args: Array<String>) {

    System.setProperty("jna.library.path", "lib")

    setLogPath()

    PROGRAM_ARGS = args.toList()

    Application.launch(MainApplication::class.java, *args)
}