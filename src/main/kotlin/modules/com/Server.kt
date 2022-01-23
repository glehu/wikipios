package modules.com

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import modules.configureRouting
import sysdat.serverEnginge
import sysdat.serverJob

@DelicateCoroutinesApi
class Server {
    fun serve() {
        serverEnginge = getEngine()
        serverJob = GlobalScope.launch {
            serverEnginge!!.start(wait = true)
        }
    }

    fun stop() {
        serverJob!!.cancel()
    }

    private fun getEngine(): NettyApplicationEngine {
        return embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
            configureRouting()
        }
    }
}
