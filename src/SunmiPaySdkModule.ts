import { NativeModulesProxy, requireNativeModule } from 'expo-modules-core';

// First try to get the module from NativeModulesProxy
let SunmiPaySdk;

// Try to get the module from NativeModulesProxy first
SunmiPaySdk = NativeModulesProxy.SunmiPaySdk;

// If that doesn't work, fall back to requireNativeModule
if (!SunmiPaySdk) {
  SunmiPaySdk = requireNativeModule('SunmiPaySdk');
}

export default SunmiPaySdk;
