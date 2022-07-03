package com.krunal.androxploit.domain.usecase.network.hosts

import android.content.Context
import android.net.DhcpInfo
import android.net.wifi.WifiManager
import android.util.Log
import android.widget.Toast
import com.krunal.androxploit.domain.model.NetworkDetails
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import java.net.InetAddress
import java.net.NetworkInterface
import java.net.UnknownHostException
import javax.inject.Inject

class NetworkDetailsUseCase @Inject constructor(
    @ApplicationContext val context: Context
) {
    private val TAG = "NetworkDetailsUseCase"

    operator fun invoke(): NetworkDetails {

        val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val dhcpInfo: DhcpInfo = wifiManager.dhcpInfo

        if (!wifiManager.isWifiEnabled) {
            Log.d(TAG, "invoke: Wifi is not enable")
            Toast.makeText(context, "Please enable wifi.", Toast.LENGTH_LONG).show()

            // Deprecated in Android Q
            wifiManager.isWifiEnabled = true
        }

        val networkDetails = NetworkDetails(
            dns1 = intToIp(dhcpInfo.dns1),
            dns2 = intToIp(dhcpInfo.dns2),
            gateway = intToIp(dhcpInfo.gateway),
            ipAddress = intToIp(dhcpInfo.ipAddress),
            leaseDuration = dhcpInfo.leaseDuration.toString(),
            serverAddress = intToIp(dhcpInfo.serverAddress)
        )

        try {
            if (networkDetails.ipAddress == "0.0.0.0") {
                networkDetails.ipAddress = "192.168.43.1"
            }
            val inetAddress: InetAddress =
                InetAddress.getByName(networkDetails.ipAddress)
            val networkInterface: NetworkInterface = NetworkInterface.getByInetAddress(inetAddress)

            val address = networkInterface.interfaceAddresses[0]
            networkDetails.netmask = address.networkPrefixLength.toInt()
            Log.d(TAG, "invoke: ${address.networkPrefixLength}")

        } catch (e: UnknownHostException) {
            Log.d(TAG, "invoke: Host error ${e.message}")

        } catch (e: IOException) {
            Log.d(TAG, "invoke: ${e.message}")
        } catch (e: Exception) {
            Log.d(TAG, "invoke: ${e.message}")
        }

        return networkDetails
    }

    private fun intToIp(addr: Int): String {
        var addr = addr
        return (addr and 0xFF).toString() + "." +
                (8.let { addr = addr ushr it; addr } and 0xFF) + "." +
                (8.let { addr = addr ushr it; addr } and 0xFF) + "." +
                (8.let { addr = addr ushr it; addr } and 0xFF)
    }


}