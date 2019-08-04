/**
 * COPYRIGHT (C) 2019 GLOBALLOGIC INC
 * ALL RIGHTS RESERVED
 */

#ifndef vendor_globallogic_ledcontrol_V1_0_LedControl_H_
#define vendor_globallogic_ledcontrol_V1_0_LedControl_H_

#include "vendor/gl/ledcontrol/1.0/ILedControl.h"

namespace vendor {
namespace gl {
namespace ledcontrol {
namespace V1_0 {

using namespace android::hardware;

class LedControl:  public ILedControl {
public:
    LedControl();
    Return<int32_t> setLedState(Leds led, LedState state) override;
};


} // V1_0
} // ledcontrol
} // gl
} // vendor

#endif /* vendor_globallogic_ledcontrol_V1_0_LedControl_H_ */
