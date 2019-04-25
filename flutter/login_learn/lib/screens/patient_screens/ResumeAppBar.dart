import 'package:flutter/material.dart';
import '../../Theme.dart' as Theme;

class ResumeAppBar extends StatelessWidget{

  @override
  Widget build(BuildContext context) {
    return new Container(
      color: Theme.Colors.appBarGradientStart,
      padding: new EdgeInsets.only(
        top: MediaQuery
            .of(context)
            .padding
            .top
      ),
      child: new Row(
        children: <Widget>[
          new BackButton(
            color: Theme.Colors.appBarIconColor
          ),
          new Center(
            child: new Image(
              alignment: Alignment.center,
              image: new AssetImage("assets/img/logo.jpg"),
            ),
          )
        ],
      ),
    );
  }
}