package modules

import modules.logic.CLI

fun main(args: Array<String>) {
    if (args.isNotEmpty() && args[0] == "cli") {
        CLI().boot()
    }
}
