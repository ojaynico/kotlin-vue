import kotlinx.html.dom.append
import org.w3c.dom.Node
import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.html.*
import kotlin.js.Date

external class Vue(args: dynamic)

fun main() {

    window.onload = {
        document.getElementById("root")?.getBody()

        Vue(object {
            val el = "#root"
            val data = object {
                val message = "Hello Kotlin Vue"
                val hover = "You loaded this page on ${Date().toLocaleString()}"
                val seen = true
                val todos = arrayOf(
                    object { val text = "Learn Kotlin" },
                    object { val text = "Learn Vue" },
                    object { val text = "Learn Javascript" }
                )
                var clickMessage = "Hello VueKotlin.js!"
                var inputValue = "Hello Kotlin"
            }
            val methods = object {
                val reverseMessage = {
                    data.clickMessage = data.clickMessage.split("").reversed().joinToString("")
                }
            }
        })
    }
}

fun Node.getBody() {
    append {
        div {
            h1 {
                +"{{message}}"
            }
            h2 { +"Hover over the sentence below" }
            span {
                attributes["v-bind:title"] = "hover"
                +"Hover your mouse over me for a few seconds to see my dynamically bound title!"
            }
            h2 { +"Toggle true or false in code to hide sentence below" }
            span {
                attributes["v-if"] = "seen"
                +"Now you see me"
            }
            h2 { +"Loop through array to generate list" }
            ul {
                li {
                    attributes["v-for"] = "todo in todos"
                    +"{{todo.text}}"
                }
            }
            h2 { +"Change paragraph text using button click event" }
            p { +"{{clickMessage}}" }
            button {
                attributes["v-on:click"] ="reverseMessage"
                +"Reverse Message"
            }
            h2 { +"Change text in paragraph according to input value" }
            p { +"{{inputValue}}" }
            input {
                attributes["v-model"] = "inputValue"
            }
        }
    }
}
