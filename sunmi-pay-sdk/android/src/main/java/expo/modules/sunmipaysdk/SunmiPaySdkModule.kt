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
    // Sets the name of the module that JavaScript code will use to refer to the module. Takes a string as an argument.
    // Can be inferred from module's class name, but it's recommended to set it explicitly for clarity.
    // The module will be accessible from `requireNativeModule('SunmiPaySdk')` in JavaScript.
    Name("SunmiPaySdk")

    OnCreate {
        bindPaySDKService()
    }

    Function("setScreenMode") { mode: Int ->
        basicOptV2?.setScreenMode(mode)
    }

    Function("setNavigationBarVisibility") { visibility: Int ->
        basicOptV2?.setNavigationBarVisibility(visibility)
    }

  }

    private fun bindPaySDKService() {
        payKernel = SunmiPayKernel.getInstance()
        payKernel?.initPaySDK(appContext.reactContext, object : SunmiPayKernel.ConnectCallback {
            override fun onConnectPaySDK() {
                Log.e("MySunmiPay", "Connected to PaySDK")
                basicOptV2 = payKernel?.mBasicOptV2
            }

            override fun onDisconnectPaySDK() {
                Log.e("MySunmiPay", "Disconnected from PaySDK")
                basicOptV2 = null
            }
        })
    }
}
