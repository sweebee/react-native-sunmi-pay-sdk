import SunmiPaySdk from './SunmiPaySdkModule';

export function setScreenMode(mode): string {
  return SunmiPaySdk.setScreenMode(mode);
}

export function setNavigationBarVisibility(visibility): string {
  return SunmiPaySdk.setNavigationBarVisibility(visibility);
}
