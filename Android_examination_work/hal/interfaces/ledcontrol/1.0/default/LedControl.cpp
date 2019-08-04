/**
 * COPYRIGHT (C) 2019 GLOBALLOGIC INC
 * ALL RIGHTS RESERVED
 */

#include "LedControl.h"
#include <log/log.h>
#define LOG_TAG "LedControl_HAL"

namespace vendor {
namespace gl {
namespace ledcontrol {
namespace V1_0 {

LedControl::LedControl()
{
    ALOGI("Initialization");
    for (int i = (int)Leds::LED_GREEN_1; i <= (int)Leds::LED_LAST; ++i) {
        char ledPath[64] = {0};
        if (snprintf(ledPath, sizeof(ledPath), "/sys/class/leds/user_led%d/trigger", i) <= 0) {
            continue;
        }

        FILE* f = fopen(ledPath, "w");
        if (!f) {
            continue;
        }
        fprintf(f, "none\n");
        fclose(f);
    }
}

Return<int32_t> LedControl::setLedState(Leds led, LedState state)
{
    ALOGI("setLedState(%hhu, %hhu)", led, state);
    char ledPath[64] = {};
    if (snprintf(ledPath, sizeof(ledPath), "/sys/class/leds/user_led%hhu/brightness", led) <= 0) {
        return 1;
    }
    FILE* f = fopen(ledPath, "w");
    if (!f) {
        return 1;
    }
    fprintf(f, "%hhu\n", state);
    fclose(f);
    return 0;
}


} // V1_0
} // ledcontrol
} // gl
} // vendor
