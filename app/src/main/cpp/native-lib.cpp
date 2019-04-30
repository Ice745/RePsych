#include <jni.h>
#include "AshesiAsALivingLab.h"
#include <string>
using namespace std;

extern "C" JNIEXPORT jstring JNICALL
Java_com_example_repsych_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    string hello = "Welcome to RePsych";
    return env->NewStringUTF(hello.c_str());
}
