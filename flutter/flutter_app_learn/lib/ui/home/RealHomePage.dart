import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'CustomAppBar.dart';
import 'PatientList.dart';

class RealHomePage extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: HomePageBody(),
    );
  }

}

class HomePageBody extends StatefulWidget{
  @override
  _HomePageBodyState createState() => new _HomePageBodyState();
}

class _HomePageBodyState extends State<HomePageBody>{

  @override
  Widget build(BuildContext context) {

    SystemChrome.setPreferredOrientations([DeviceOrientation.landscapeRight,DeviceOrientation.landscapeLeft]);
    return new Column(

      children: <Widget>[
        new CustomAppBar("Ergolife"),
        new PatientList(),
      ],
    );
  }

}