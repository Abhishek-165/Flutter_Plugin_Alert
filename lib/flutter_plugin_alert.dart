
import 'dart:async';

import 'package:flutter/services.dart';

class FlutterPluginAlert {
  static const MethodChannel _channel = MethodChannel('flutter_plugin_alert');

  static Future<String?> get platformVersion async {
    final String? version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }
  static Future<void> showToast(String msg) async
  {
    Map<dynamic,dynamic> args = {};
    args.putIfAbsent("msg", () => msg);
    await _channel.invokeMethod('showToast',args);
  }
}
