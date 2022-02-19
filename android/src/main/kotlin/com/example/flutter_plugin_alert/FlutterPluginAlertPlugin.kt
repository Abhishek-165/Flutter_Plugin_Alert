package com.example.flutter_plugin_alert

import androidx.annotation.NonNull

import android.content.Context
import android.app.Activity
import android.widget.Toast
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding

/** FlutterPluginAlertPlugin */
class FlutterPluginAlertPlugin: FlutterPlugin, MethodCallHandler, ActivityAware  {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel

  private lateinit var context: Context
  private lateinit var activity: Activity

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "flutter_plugin_alert")
    channel.setMethodCallHandler(this)

    this.context = flutterPluginBinding.applicationContext
  }

  companion object {
    @JvmStatic
    fun registerWith(registrar: Registrar) {
      val channel = MethodChannel(registrar.messenger(), "flutter_plugin_alert")
      channel.setMethodCallHandler(FlutterPluginAlertPlugin())
    }
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    if (call.method == "getPlatformVersion") {
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
    }
    else if(call.method == "showToast")
    {
      var data = call.arguments() as HashMap<String, Any>

      Toast.makeText(context,data['msg'],Toast.LENGTH_SHORT).show()
    }
    else {
      result.notImplemented()
    }
  }

  override fun onDetachedFromActivity() {
    TODO("Not yet implemented")
}

override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
    TODO("Not yet implemented")
}

override fun onAttachedToActivity(binding: ActivityPluginBinding) {
    activity = binding.activity;
}

override fun onDetachedFromActivityForConfigChanges() {
    TODO("Not yet implemented")
}

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }
}
