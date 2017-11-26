import spark.Spark.get
import kotlinx.html.*
import kotlinx.html.stream.createHTML
import spark.Spark.port

fun main(args: Array<String>) {
    port(getHerokuAssignedPort())
    val text = createHTML().html {
        head {
            meta(charset = "UTF-8")
            title {
                +"Kotlin-HTML-test"
            }
            style {
                +"table,td{border:solid 1px #000000; border-collapse:collapse;}"
            }
        }
        body {
            table {
                for (i in 1..5) {
                    tr {
                        for (j in 1..5) {
                            td { +"$i,$j" }
                        }
                    }
                }
            }
        }
    }
    val index = createHTML().html {
        head {
            meta(charset = "UTF-8")
            title { +"こんにちは" }
        }
        body {
            a(href = "/hello") { +"こちらへ" }
        }
    }
    get("/") { request, response ->
        index
    }
    get("/hello") { request, response ->
        text
    }

}

fun getHerokuAssignedPort(): Int {
    val processBuilder = ProcessBuilder()
    return if (processBuilder.environment()["PORT"] != null) {
        Integer.parseInt(processBuilder.environment()["PORT"])
    } else 4567
//return default port if heroku-port isn't set (i.e. on localhost)
}
