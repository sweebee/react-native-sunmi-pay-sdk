import SunmiPaySdk from './SunmiPaySdkModule';

export function setScreenMode(mode): string {
  try {
    return SunmiPaySdk.setScreenMode(mode);
  } catch (error) {
    console.error('Error calling setScreenMode:', error);
    return 'Error: Module not available';
  }
}

export function setNavigationBarVisibility(visibility): string {
  try {
    return SunmiPaySdk.setNavigationBarVisibility(visibility);
  } catch (error) {
    console.error('Error calling setNavigationBarVisibility:', error);
    return 'Error: Module not available';
  }
}

export function setStatusBarDropDownMode(mode): string {
  try {
    return SunmiPaySdk.setStatusBarDropDownMode(mode);
  } catch (error) {
    console.error('Error calling setStatusBarDropDownMode:', error);
    return 'Error: Module not available';
  }
}
