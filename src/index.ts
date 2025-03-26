import SunmiPaySdk from './SunmiPaySdkModule';

export function setScreenMode(mode): string {
  return SunmiPaySdk.setScreenMode(mode);
}

export function setNavigationBarVisibility(visibility): string {
  return SunmiPaySdk.setNavigationBarVisibility(visibility);
}

export function setStatusBarDropDownMode(mode):string {
  return SunmiPaySdk.setStatusBarDropDownMode(mode);
}
