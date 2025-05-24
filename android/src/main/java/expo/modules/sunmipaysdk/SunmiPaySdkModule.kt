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

    // Add more information about the module to help with registration
    Constants(
      "MODULE_NAME" to "SunmiPaySdk",
      "MODULE_VERSION" to "1.0.0"
    )

    OnCreate {
        Log.d("SunmiPaySdk", "Creating SunmiPaySdk module")
        bindPaySDKService()
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

    private fun bindPaySDKService() {
        try {
            Log.d("SunmiPaySdk", "Binding PaySDK service")
            payKernel = SunmiPayKernel.getInstance()

            if (payKernel == null) {
                Log.e("SunmiPaySdk", "Failed to get SunmiPayKernel instance")
                return
            }

            Log.d("SunmiPaySdk", "Initializing PaySDK")
            payKernel?.initPaySDK(appContext.reactContext, object : SunmiPayKernel.ConnectCallback {
                override fun onConnectPaySDK() {
                    Log.d("SunmiPaySdk", "Connected to PaySDK")
                    try {
                        basicOptV2 = payKernel?.mBasicOptV2
                        if (basicOptV2 == null) {
                            Log.e("SunmiPaySdk", "Failed to get BasicOptV2 instance")
                        } else {
                            Log.d("SunmiPaySdk", "Successfully got BasicOptV2 instance")
                        }
                    } catch (e: Exception) {
                        Log.e("SunmiPaySdk", "Error getting BasicOptV2 instance: ${e.message}")
                    }
                }

                override fun onDisconnectPaySDK() {
                    Log.d("SunmiPaySdk", "Disconnected from PaySDK")
                    basicOptV2 = null
                }
            })
        } catch (e: Exception) {
            Log.e("SunmiPaySdk", "Error binding PaySDK service: ${e.message}")
        }
    }
}
