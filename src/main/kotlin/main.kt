import spark.Spark.get
import kotlinx.html.*
import kotlinx.html.stream.createHTML

fun main(args: Array<String>) {
    val text = createHTML().html {
        head {
            meta(charset = "UTF-8")
            title {
                +"hogehoge"
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
    get("/hello") { request, response ->
        text
    }

}