/*
 * Copyright (C) 2019 Globallogic
 */
#define LOG_TAG "vendor.gl.ledcontrol@1.0-service-hikey960"

#include <hidl/HidlSupport.h>
#include <hidl/HidlTransportSupport.h>
//#include <Log.h>
#include "LedControl.h"

using ::android::hardware::configureRpcThreadpool;
using ::android::hardware::joinRpcThreadpool;
using ::android::OK;
using ::android::sp;

using namespace vendor::gl::ledcontrol::V1_0;

int main(int /* argc */, char* /* argv */ []) {
    sp<ILedControl> ledcontrol = new LedControl();
    configureRpcThreadpool(1, true /* will join */);
    if (ledcontrol->registerAsService() != OK) {
        ALOGE("Could not register LedControl 1.0 service");
        return 1;
    } else {
        ALOGI("LedControl 1.0 service registered successfully");
    }
    joinRpcThreadpool();

    ALOGE("Service LedControl exited!");
    return 1;
}
