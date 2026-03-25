import { NativeModulesProxy, requireNativeModule } from 'expo-modules-core';

let SunmiPaySdk: any = null;

try {
  SunmiPaySdk = NativeModulesProxy.SunmiPaySdk;
  if (!SunmiPaySdk) {
    SunmiPaySdk = requireNativeModule('SunmiPaySdk');
  }
} catch (e) {
  // Module not available on this device/build variant — provide no-op stubs
  SunmiPaySdk = new Proxy({}, {
    get: () => () => Promise.resolve(false),
  });
}

export default SunmiPaySdk;
