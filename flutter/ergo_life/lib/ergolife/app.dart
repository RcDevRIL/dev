import 'dart:async';


import 'package:flutter/cupertino.dart';
import 'package:flutter/foundation.dart' show defaultTargetPlatform;
import 'package:flutter/material.dart';
import 'package:flutter/scheduler.dart' show timeDilation;
import 'package:url_launcher/url_launcher.dart';
import 'package:scoped_model/scoped_model.dart';
import 'package:ergo_life/model/app_state_model.dart';

import 'demos.dart';
import 'home.dart';
import 'options.dart';
import 'scales.dart';
import 'themes.dart';

class ErgoLife extends StatefulWidget{
  const ErgoLife({
    Key key,
    this.enablePerformanceOverlay = true,
    this.enableRasterCacheImagesCheckerboard = true,
    this.enableOffscreenLayersCheckerboard = true,
    this.onSendFeedback,
    this.testMode = false,
  }) : super(key: key);

  final bool enablePerformanceOverlay;
  final bool enableRasterCacheImagesCheckerboard;
  final bool enableOffscreenLayersCheckerboard;
  final VoidCallback onSendFeedback;
  final bool testMode;

  @override
  _ErgoLifeState createState() => _ErgoLifeState();
}

class _ErgoLifeState extends State<ErgoLife> {
  ErgoLifeOptions _options;
  Timer _timeDilationTimer;
  AppStateModel model;

  Map<String, WidgetBuilder> _buildRoutes(){
    return Map<String, WidgetBuilder>.fromIterable(
      kAllGalleryDemos,
      key: (dynamic demo) => '${demo.routeName}',
      value: (dynamic demo) => demo.buildRoute,
    );
  }

  @override
  void initState(){
    super.initState();
    _options = ErgoLifeOptions(
      theme: kLightGalleryTheme,
      textScaleFactor: kAllGalleryTextScaleValues[0],
      timeDilation: timeDilation,
      platform: defaultTargetPlatform,
    );
    model = AppStateModel()..loadPatients();
  }

  @override
  void dispose(){
    _timeDilationTimer?.cancel();
    _timeDilationTimer = null;
    super.dispose();
  }

  void _handleOptionsChanged(ErgoLifeOptions newOptions){
    setState((){
      if(_options.timeDilation != newOptions.timeDilation){
        _timeDilationTimer?.cancel();
        _timeDilationTimer = null;
        if(newOptions.timeDilation > 1.0) {
          // We delay the time dilation change long enough that the user can see
          // that UI has started reacting and then we slam on the brakes so that
          // they see that the time is in fact now dilated.
          _timeDilationTimer = Timer(const Duration(milliseconds: 150), () {
            timeDilation = newOptions.timeDilation;
          });
        } else{
          timeDilation = newOptions.timeDilation;
        }
      }
      _options = newOptions;
    });
  }

  Widget _applyTextScaleFactor(Widget child){
    return Builder(
      builder: (BuildContext context){
        return MediaQuery(
          data: MediaQuery.of(context).copyWith(
            textScaleFactor: _options.textScaleFactor.scale,
          ),
          child: child,
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    Widget home = ErgoLifeHome(
      testMode: widget.testMode,
      optionsPage: ErgoLifeOptionsPage(
        options: _options,
        onOptionsChanged: _handleOptionsChanged,
        onSendFeedback: widget.onSendFeedback ?? (){
          launch('https://github.com/flutter/flutter/issues/new/choose', forceSafariVC: false);
        },
      ),
    );

    return ScopedModel<AppStateModel>(
      model: model,
      child: MaterialApp(
        theme: _options.theme.data.copyWith(platform: _options.platform),
        title: 'ErgoLife',
        color: Colors.grey,
        showPerformanceOverlay: _options.showPerformanceOverlay,
        checkerboardOffscreenLayers: _options.showOffscreenLayersCheckerboard,
        checkerboardRasterCacheImages: _options.showRasterCacheImagesCheckerboard,
        routes: _buildRoutes(),
        builder: (BuildContext context, Widget child){
          return Directionality(
            textDirection: _options.textDirection,
            child: _applyTextScaleFactor(
              // Specifically use a blank Cupertino theme here and do not transfer
              // over the Material primary color etc except the brightness to
              // showcase standard iOS looks.
              CupertinoTheme(
                data: CupertinoThemeData(
                  brightness: _options.theme.data.brightness,
                ),
                child: child,
              ),

            ),
          );
        },
        home: home,
      ),
    );
  }
}
