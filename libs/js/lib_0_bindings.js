// JavaScript doesn't have namespaces, so we'll just use function name prefixes

/*

 This file contains all the bindings for native functions in the NativeModHook class instance,
 provided by the context in PixelyEngine.
 The class instance(or object) is called "ntv," and it is registered in global scope.
 To see all the methods that can be called in NativeModHook, see PixelyEngine source.

 The reason to have these native methods instead of completely writing this in JavaScript is so
 that we can use libraries written inside Java, and it could potentially be faster.

 */

// Used for debugging
var ntv_UsedNative = false;

function ntv_InitNativeCall() {
	ntv_UsedNative = true;
}

function ntv_Submit(mod) {
	ntv_InitNativeCall();
	ntv.submitJS(mod);
}

function ntv_Kick(mod) {
	ntv_InitNativeCall();
	ntv.kickJS(mod);
}

function ntv_Info(msg) {
	ntv_InitNativeCall();
	ntv.logInfo(msg);
}

function ntv_Warning(msg) {
	ntv_InitNativeCall();
	ntv.logWarning(msg);
}

function ntv_Severe(msg) {
	ntv_InitNativeCall();
	ntv.logSevere(msg);
}

function ntv_UsedNativeCall() {
	return ntv_UsedNative;
}