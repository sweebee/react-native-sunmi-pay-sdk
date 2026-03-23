import SunmiPaySdk from './SunmiPaySdkModule';

export const ScreenMode = {
  NORMAL: 0,
  FULLSCREEN: 1,
} as const;

export const NavigationBarVisibility = {
  VISIBLE: 1,
  HIDDEN: -1,
} as const;

export const StatusBarDropDownMode = {
  ENABLED: 0,
  DISABLED: 1,
} as const;

type ScreenModeValue = typeof ScreenMode[keyof typeof ScreenMode];
type NavigationBarVisibilityValue = typeof NavigationBarVisibility[keyof typeof NavigationBarVisibility];
type StatusBarDropDownModeValue = typeof StatusBarDropDownMode[keyof typeof StatusBarDropDownMode];

export async function connect(): Promise<boolean> {
  return SunmiPaySdk.connect();
}

export function setScreenMode(mode: ScreenModeValue) {
  SunmiPaySdk.setScreenMode(mode);
}

export function setNavigationBarVisibility(visibility: NavigationBarVisibilityValue) {
  SunmiPaySdk.setNavigationBarVisibility(visibility);
}

export function setStatusBarDropDownMode(mode: StatusBarDropDownModeValue) {
  SunmiPaySdk.setStatusBarDropDownMode(mode);
}
