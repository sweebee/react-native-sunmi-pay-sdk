const { withAppBuildGradle, withAndroidManifest } = require('@expo/config-plugins');

module.exports = function withSunmiPaySdk(config) {
  // Add the SunmiPaySdk module to the project
  config = withAppBuildGradle(config, (config) => {
    if (config.modResults.language === 'groovy') {
      const implementationLine = `implementation 'com.sunmi:PayLib-release:2.0.18'`;
      // Only add the dependency if it doesn't already exist
      if (!config.modResults.contents.includes(implementationLine)) {
        config.modResults.contents = config.modResults.contents.replace(
          'implementation("com.facebook.react:react-android")', 
          `implementation("com.facebook.react:react-android")\n    implementation 'com.sunmi:PayLib-release:2.0.18'`
        );
      }
    }
    return config;
  });

  // Add the necessary queries to the AndroidManifest.xml
  config = withAndroidManifest(config, async config => {
    const queries = config.modResults.manifest.queries || [];

    // Add the SunmiPaySdk package
    const newPackage = {
      $: {
        'android:name': 'com.sunmi.pay.hardware.aidlv2',
      },
    };

    // Add the package to the queries
    queries.push({
      package: [newPackage],
    });

    config.modResults.manifest.queries = queries;
    return config;
  });

  return config;
};
