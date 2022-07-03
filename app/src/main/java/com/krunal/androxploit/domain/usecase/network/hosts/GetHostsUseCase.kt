package com.krunal.androxploit.domain.usecase.network.hosts

import android.util.Log
import com.jaredrummler.ktsh.Shell
import com.krunal.androxploit.R
import com.krunal.androxploit.domain.model.Host
import com.krunal.androxploit.domain.model.NetworkDetails
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import java.util.concurrent.TimeUnit
import java.util.regex.Matcher
import java.util.regex.Pattern
import javax.inject.Inject

class GetHostsUseCase @Inject constructor(
    private val networkDetailsUseCase: NetworkDetailsUseCase,
    private val loadNmapUseCase: LoadNmapUseCase
) {

    private val TAG = "GetHostsUseCase"
    lateinit var networkDetails: NetworkDetails

    suspend operator fun invoke(): Flow<Host> = callbackFlow {

        val nmap: String = loadNmapUseCase()

        networkDetails = networkDetailsUseCase()
        val command =
            "$nmap -sn -T 5  --system-dns ${networkDetails.ipAddress}/${networkDetails.netmask}"

//        val command =
//            "$nmap -sn -T 5 --min-parallelism 40 --system-dns ${networkDetails.ipAddress}/24"

        val shell = getShell()
        lateinit var host: Host

        val outListner = object : Shell.OnLineListener {
            override fun onLine(line: String) {
                Log.d(TAG, "onLine: $line")

                if (line.startsWith("Nmap scan report for")) {
                    parseIp(line)?.let { ip ->
                        host = Host(ip = ip)
                    }
                    parseName(line)?.let { name ->
                        host.name = name
                    }
                    parseImgRes(host)?.let { resId ->
                        host.imgRes = resId
                    }
                } else if (line.startsWith("MAC")) {
                    parseMac(line)?.let { mac ->
                        host.mac = mac
                    }
                    parseLable(line)?.let { lable ->
                        if (!lable.equals("unknown", true)) {
                            host.lable = lable
                        }
                    }
                } else if (line.startsWith("Host is up")) {
                    if (host.ip == networkDetails.ipAddress) {
                        host.lable = "${host.lable} (You)"
                    } else if (host.ip == networkDetails.gateway) {
                        host.lable = "${host.lable} (Gateway)"
                    }
                    Log.d(TAG, "invoke: Host $host")
                    trySend(host)
                }
            }
        }
        shell.addOnStdoutLineListener(outListner)
        shell.run(command)
        awaitClose { shell.removeOnStdoutLineListener(outListner) }
    }

    private fun parseIp(s: String): String? {
        val p: Pattern =
            Pattern.compile("(\\d{1,4}[.]?){4}")
        val matcher: Matcher = p.matcher(s)

        if (matcher.find()) {
            return matcher.group()
        }
        return null
    }

    private fun parseMac(s: String): String? {
        val p: Pattern =
            Pattern.compile("((\\w{2}:){5}\\w{2})")
        val matcher: Matcher = p.matcher(s)

        if (matcher.find()) {
            return matcher.group()
        }
        return null
    }

    private fun parseLable(s: String): String? {
        val p: Pattern = Pattern.compile("[(](.)+[)]")
        val matcher: Matcher = p.matcher(s)

        if (matcher.find()) {
            return matcher.group().replace("(", "").replace(")", "")
        }
        return null
    }

    private fun parseName(s: String): String? {

        s.split(" ").let {
            Log.d(TAG, "parseName: $it")
            if (it.size == 6) {
                return it[4]
            }
        }
        return null
    }

    private fun parseImgRes(host: Host): Int? {

        if (host.name.contains("andro", true)) {
            return R.drawable.android

        } else if (host.name.contains("lapt", true) || host.name.contains("desk", true)) {
            return R.drawable.windows

        } else if (host.name.contains("rout", true) || host.ip == networkDetails.gateway) {
            return R.drawable.host_router

        } else if (host.name.contains("switch", true)) {
            return R.drawable.network_switch

        }

        return null
    }

    private fun getShell(): Shell {
        val shell: Shell = try {
            Shell.SU.run("ls") {
                timeout = Shell.Timeout(16, TimeUnit.SECONDS)
            }.let { result ->
                Log.d(TAG, "Result time: ${result.details.elapsed}")

                if (result.isSuccess) {
                    Log.d(TAG, "invoke: SuperUser permission granted")
                    Shell.SU

                } else {
                    Log.d(TAG, "invoke: No SuperUser access, changing to normal shell")
                    Shell.SH
                }
            }
        } catch (e: Shell.NotFoundException) {
            Log.d(TAG, "invoke: No SuperUser access, changing to normal shell")
            Shell.SH
        }
        return shell
    }
}
