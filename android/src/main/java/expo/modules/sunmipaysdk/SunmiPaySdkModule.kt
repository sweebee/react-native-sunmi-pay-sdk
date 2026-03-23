package expo.modules.sunmipaysdk

import android.util.Log
import com.sunmi.pay.hardware.aidlv2.system.BasicOptV2
import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition
import sunmi.paylib.SunmiPayKernel

class SunmiPaySdkModule : Module() {

  private var payKernel: SunmiPayKernel? = null
  private var basicOptV2: BasicOptV2? = null

  override fun definition() = ModuleDefinition {
    Name("SunmiPaySdk")

    AsyncFunction("connect") { promise: expo.modules.kotlin.Promise ->
        payKernel = SunmiPayKernel.getInstance()

        // If the PayNL SDK (or another module) already connected to the
        // Sunmi PayHardwareService, the singleton already has mBasicOptV2.
        // Re-calling initPaySDK won't fire the callback, so just reuse it.
        val existing = payKernel?.mBasicOptV2
        if (existing != null) {
            Log.d("MySunmiPay", "Already connected, reusing existing connection")
            basicOptV2 = existing
            promise.resolve(true)
            return@AsyncFunction
        }

        payKernel?.initPaySDK(appContext.reactContext, object : SunmiPayKernel.ConnectCallback {
            override fun onConnectPaySDK() {
                Log.d("MySunmiPay", "Connected to PaySDK")
                basicOptV2 = payKernel?.mBasicOptV2
                promise.resolve(true)
            }

            override fun onDisconnectPaySDK() {
                Log.d("MySunmiPay", "Disconnected from PaySDK")
                basicOptV2 = null
            }
        })
    }

    Function("setScreenMode") { mode: Int ->
        basicOptV2?.setScreenMode(mode)
    }

    Function("setNavigationBarVisibility") { visibility: Int ->
        basicOptV2?.setNavigationBarVisibility(visibility)
    }

    Function("setStatusBarDropDownMode") { mode: Int ->
        basicOptV2?.setStatusBarDropDownMode(mode)
    }

  }
}
