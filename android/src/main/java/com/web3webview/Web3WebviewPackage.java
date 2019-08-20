package com.web3webview;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Web3WebviewPackage implements ReactPackage {

    private Web3WebviewModule module;

    @Override
    public List<ViewManager> createViewManagers(
            ReactApplicationContext reactContext) {
        return Collections.<ViewManager>singletonList(
                new Web3WebviewManager(reactContext,this)
        );
    }

    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
        List<NativeModule> modulesList = new ArrayList<>();
        module = new Web3WebviewModule(reactContext);
        modulesList.add(module);
        return modulesList;
    }

    public Web3WebviewModule getModule() {
        return module;
    }


}
