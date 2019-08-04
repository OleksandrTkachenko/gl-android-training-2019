LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

LOCAL_PRIVATE_PLATFORM_APIS := true
LOCAL_CERTIFICATE := platform

LOCAL_SRC_FILES := $(call all-java-files-under, java) aidl/com/gl/ledcontrolsrvc/ILCService.aidl

LOCAL_AIDL_FILES := $(call all-aidl-files-under, aidl)
LOCAL_RESOURCE_DIR := $(LOCAL_PATH)/res
LOCAL_PACKAGE_NAME := LedControlSrvc
LOCAL_STATIC_JAVA_LIBRARIES += vendor.gl.ledcontrol-V1.0-java
LOCAL_PRIVATE_PLATFORM_APIS := true

include $(BUILD_PACKAGE)


