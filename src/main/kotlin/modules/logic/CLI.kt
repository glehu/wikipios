package modules.logic

import com.github.ajalt.mordant.rendering.AnsiLevel
import com.github.ajalt.mordant.rendering.TextColors
import com.github.ajalt.mordant.table.table
import com.github.ajalt.mordant.terminal.Terminal
import kotlinx.coroutines.DelicateCoroutinesApi
import modules.com.Server
import kotlin.system.exitProcess

@DelicateCoroutinesApi
class CLI {
    var terminal: Terminal = Terminal(ansiLevel = AnsiLevel.TRUECOLOR)

    fun boot() {
        var terminated = false
        var inputArgs: List<String>
        terminal.info.updateTerminalSize()
        terminal.cursor.move {
            clearScreen()
        }
        terminal.println(
            table {
                header {
                    row("OS/Version", "Wiki Pi OS", "v0.0.1-Alpha")
                }
                body {
                    row("Author", "Luca Goldhausen", "Wiki")
                    row("Written in", "Pure Kotlin", "JVM")
                }
                captionBottom(TextColors.gray("An OpenSource OS for Bullseye Linux on the Raspberry Pi"))
            })
        while (!terminated) {
            cliPrepareUserInput()
            //Wait for user input
            inputArgs = (readLine() ?: "").split(" ")
            terminated = cliHandleInput(inputArgs)
        }
        cliExit()
    }

    private fun cliPrepareUserInput() {
        terminal.print(
            "\n${TextColors.brightMagenta("pi")}:" + TextColors.brightBlue("> ")
        )
    }

    /**
     * Handles the user input.
     * @return true if the software needs to terminate.
     */
    private fun cliHandleInput(inputArgs: List<String>): Boolean {
        var terminated = false
        when (inputArgs[0]) {
            "exit" -> terminated = true
            "help" -> cliHelp(inputArgs)
            "start" -> cliStart(inputArgs)
        }
        return terminated
    }

    private fun cliStart(args: List<String>) {
        if (args.size < 2 || args[1].isEmpty()) {
            cliErrorNotEnoughArguments(args)
        } else {
            when (args[1]) {
                "server" -> Server.serve()
            }
        }
    }

    private fun cliErrorNotEnoughArguments(args: List<String>) {
        terminal.println(
            TextColors.red(
                "ERROR: Not enough arguments! Type ${TextColors.white("help ${args[0]}")} for a list of arguments."
            )
        )
    }

    private fun cliExit() {
        Server.stop()
        terminal.println(
            "\n\n${TextColors.green("System successfully terminated by user.")}"
        )
        exitProcess(0)
    }

    /**
     * Shows useful information to the user.
     */
    private fun cliHelp(uArgs: List<String>) {
        val args = if (uArgs.size < 2 || uArgs[1].isEmpty()) {
            listOf(uArgs[0], "")
        } else uArgs
        val help = "help ${TextColors.gray("{option}")} -> shows help"
        val exit =
            "exit -> terminates the software and shuts down the servers if they are running."
        //****************************************************
        //******************* HELP TEXTS *********************
        //****************************************************
        val start =
            "start ${TextColors.gray("[argument]")} -> starts ${TextColors.gray("[argument]")}"
        val startDetail = "$start\n" +
                "\t${TextColors.gray("server")} -> starts the server\n" +
                "\t${TextColors.gray("telnet")} -> starts the telnet server"

        //****************************************************
        val helpText = when (args[1]) {
            "help" -> help
            "exit" -> exit
            "start" -> startDetail
            else -> {
                exit +
                        "\n$start"
            }
        }
        terminal.println(helpText)
    }
}
