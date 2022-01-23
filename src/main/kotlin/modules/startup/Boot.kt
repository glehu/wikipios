package modules.startup

import kotlinx.coroutines.DelicateCoroutinesApi
import modules.logic.CLI

@DelicateCoroutinesApi
fun main(args: Array<String>) {
    if (args.isNotEmpty() && args[0] == "cli") {
        CLI().boot()
    }
}
