
# react-native-web3-webview

[![npm](https://img.shields.io/npm/v/react-native-web3-webview.svg)](https://npmjs.com/package/react-native-web3-webview) [![npm](https://img.shields.io/npm/dm/react-native-web3-webview.svg)](https://npmjs.com/package/react-native-web3-webview)


A cross platform react native webview with some improvements that allow a javascript injection, which is specifically used by web3 mobile dapp browsers.

The iOS version is based on [react-native-wkwebview-reborn](https://github.com/CRAlpha/react-native-wkwebview) and the Android version is based on [react-native-webview-bridge](https://github.com/alinz/react-native-webview-bridge)

## Getting started

`$ npm install react-native-web3-webview --save`

### Mostly automatic installation

`$ react-native link react-native-web3-webview`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-web3-webview` and add `RNWeb3Webview.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNWeb3Webview.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNWeb3WebviewPackage;` to the imports at the top of the file
  - Add `new RNWeb3WebviewPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-web3-webview'
  	project(':react-native-web3-webview').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-web3-webview/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-web3-webview')
  	```


## Usage
```javascript
import Web3Webview from 'react-native-web3-webview';

...

render(){
  return (
    <Web3Webview
        injectedOnStartLoadingJavaScript={ jsStringToInject }
        onProgress={this.onLoadProgress}
        onMessage={this.onMessage}
        onNavigationStateChange={this.onPageChange}
        ref={this.webview}
        source={{ uri: url }}
        style={baseStyles.flexGrow}
     />
  );  
}


```
  
