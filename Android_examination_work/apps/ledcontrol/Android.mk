LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional

LOCAL_SRC_FILES := $(call all-java-files-under, java) \
	aidl/com/gl/ledcontrolsrvc/ILCService.aidl

LOCAL_AIDL_FILES := $(call all-aidl-files-under, aidl)
LOCAL_RESOURCE_DIR := $(LOCAL_PATH)/res
LOCAL_PACKAGE_NAME := LedControl

LOCAL_USE_AAPT2 := true
LOCAL_RESOURCE_DIR := $(LOCAL_PATH)/res
LOCAL_STATIC_ANDROID_LIBRARIES := \
	android-support-v7-appcompat \
	android-support-constraint-layout

LOCAL_STATIC_JAVA_LIBRARIES += android-support-constraint-layout-solver \
							   vendor.gl.ledcontrol-V1.0-java
LOCAL_PRIVATE_PLATFORM_APIS := true


include $(BUILD_PACKAGE)


